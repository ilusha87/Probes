package reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by admin on 05.06.2016.
 */
// A single-member annotation.
@Retention(RetentionPolicy.RUNTIME)
@interface MySingle {
    int value(); // this variable name must be value
}

@interface MySingle2 {
    int value(); // this variable name must be value
    int number() default 100;
    String someString() default "default";
}


public class Single {

    @MySingle2(100)
    public static void myMeth2() {}

    @MySingle2(value = 100,number=99,someString = "asd")
    public static void myMeth3() {}

    // Annotate a method using a marker.
    @MySingle(100)
    public static void myMeth() {
        Single ob = new Single();
        try {
            Method m = ob.getClass().getMethod("myMeth");
            MySingle anno = m.getAnnotation(MySingle.class);
            System.out.println(anno.value()); // displays 100
        } catch (NoSuchMethodException exc) {
            System.out.println("Method Not Found.");
        }
    }

    public static void main(String args[]) {
        myMeth();
    }
}
