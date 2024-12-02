package com.example.EasyJob.common.validate.annotation;

import com.example.EasyJob.common.validate.validator.UUIDConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UUIDConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface UUIDConstraint {
    String message() default "{user.existed}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
