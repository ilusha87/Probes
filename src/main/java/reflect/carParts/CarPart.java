package reflect.carParts;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by admin on 06.06.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CarPart {
    String partName() default "nameOfAApart";
    String partCode() default "originalNumber";
    String partReplacements();
    String partPrice();
}
