package com.hhd.validation.annotation;

import com.hhd.validation.annotation.handler.MyValidationHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解
 *
 * @author hengda.hu
 * @date 2020/12/3 14:06
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MyValidationHandler.class)
public @interface MyValidation {
    String message() default "自定义校验注解默认返回msg";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};
}
