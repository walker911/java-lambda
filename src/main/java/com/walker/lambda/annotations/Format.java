package com.walker.lambda.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/30
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Format {

    String pattern() default "yyyy-MM-dd HH:mm:ss";

    String timezone() default "GMT+8";

}
