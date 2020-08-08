package de.cgrotz.spring.status.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Status {
    StatusValue[] value() default {};
    String additionalInfo() default "";
}
