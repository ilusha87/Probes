package reflect;

import java.lang.annotation.*;

/**
 * Created by admin on 06.06.2016.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface MyAnno {
    String str() default "default";
    int val() default 8080;
}
