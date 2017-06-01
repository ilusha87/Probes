package reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by admin on 05.06.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno2 {
    String str();
    int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What {  String description(); }

@What(description = "An annotation test class")
@MyAnno2(str = "GetAllAnnotations", val = 99)
public class GetAllAnnotations {
        @What(description = "An annotation test method")
        @MyAnno(str = "myMeth", val = 100)
        public static void myMeth() {
            GetAllAnnotations ob = new GetAllAnnotations();
            try {
                Annotation annos[] = ob.getClass().getAnnotations();
                // Display all annotations for Meta2.
                System.out.println("All annotations for Meta2:");
                for(Annotation a : annos)
                    System.out.println(a);
                System.out.println();
                // Display all annotations for myMeth.
                Method m = ob.getClass( ).getMethod("myMeth");
                annos = m.getAnnotations();
                System.out.println("All annotations for myMeth:");
                for(Annotation a : annos)
                    System.out.println(a);
            } catch (NoSuchMethodException exc) {
                System.out.println("Method Not Found.");
            }
        }
        public static void main(String args[]) {  myMeth();  }
}
