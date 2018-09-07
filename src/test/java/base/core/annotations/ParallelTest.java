package base.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParallelTest {

//    XmlSuite.ParallelMode parallel();

    int threads() default 1;

    int verbose() default 0;

    int dataProviderThreadsCount() default 1;
}
