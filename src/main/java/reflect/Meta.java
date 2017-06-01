package reflect;

/**
 * Created by admin on 03.06.2016.
 */

import java.lang.annotation.*;
import java.lang.reflect.Method;





class Meta {
    /**
     * test
     */
    @MyAnno(str = " Пример аннотации", val=100)
    public static void myMeth1() {}
    @MyAnno(str = " Пример аннотации")
    public static void myMeth2() {}
    @MyAnno(val=100)
    public static void myMeth3() {}
    @MyAnno()
    public static void myMeth4() {}


    @MyAnno(str = " Пример аннотации", val=100)
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
            MyAnno anno = m.getAnnotation(MyAnno.class);
            // и наконец , вывести значения членов аннотации
            System.out.println(anno.str() + " " + anno.val());
        } catch (NoSuchMethodException е) {
            System.out.println("Meтoд не найден.");
        }
    }
    public static void main(String args[]) {
        myMeth();
    }
}