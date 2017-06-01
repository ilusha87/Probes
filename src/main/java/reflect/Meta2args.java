package reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by admin on 05.06.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno2args {
    String str();
    int val();
}

public class Meta2args {

    // myMeth now has two arguments.
    @MyAnno(str = "Two Parameters", val = 19)
    public static void myMeth(String str, int i) {
        Meta2args ob = new Meta2args();
        try {
            Class<?> c = ob.getClass();
            // Here, the parameter types are specified.
            Method m = c.getMethod("myMeth", String.class, int.class);
            MyAnno anno = m.getAnnotation(MyAnno.class);
            System.out.println(anno.str() + " " + anno.val());
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }

    public static void main(String args[]) {
        myMeth("test", 10);


    }
}
