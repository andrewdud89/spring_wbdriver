package base.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CarsTest {

    /**
     * default file  is /testData/default.json
     *
     * @return testData folder path to CarsTest config file
     */
    String value() default "default";
}
