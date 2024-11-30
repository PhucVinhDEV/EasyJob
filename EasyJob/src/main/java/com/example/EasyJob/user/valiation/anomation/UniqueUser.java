package com.example.EasyJob.user.valiation.anomation;

import com.example.EasyJob.user.valiation.validator.UniqueUserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UniqueUser {
    String message() default "{user.existed}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
