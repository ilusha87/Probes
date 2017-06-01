package reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by admin on 03.06.2016.
 */
@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name();
    String type() default  "string";
    int val();
}

class Meta2 {
    /**
     * test
     */
    @MyAnnotation(name = " Пример аннотации", val=100)
    public static void myMeth() {
        Meta meta = new Meta();
        //получить аннотацию из ме тода
        // и вывести значения ее членов
        try {
            //сначала получить объект типа Class ,
            //представляющий данный класс
            Class<?> c = meta.getClass();
            //затем получить объект типа Мethod,
            //представляющий данный метод
            Method m = c.getMethod("myMeth");
            //далее получить аннотацию для данного класса
            MyAnnotation anno = m.getAnnotation(MyAnnotation.class);
            // и наконец , вывести значения членов аннотации
            System.out.println(anno.name() + " " + anno.val());
        } catch (NoSuchMethodException е) {
            System.out.println("Meтoд не найден.");
        }
    }
    public static void main(String args[]) {
        myMeth();
    }
}
