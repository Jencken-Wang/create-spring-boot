package com.wzg.util;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecutionResult {
    String message() default "未传入任何信息";
    String value() default "";
}
