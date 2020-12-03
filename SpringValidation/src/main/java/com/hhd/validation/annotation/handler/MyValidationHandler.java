package com.hhd.validation.annotation.handler;

import com.hhd.validation.annotation.MyValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/12/3 14:07
 */
public class MyValidationHandler implements ConstraintValidator<MyValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.startsWith("hhd");
    }
}
