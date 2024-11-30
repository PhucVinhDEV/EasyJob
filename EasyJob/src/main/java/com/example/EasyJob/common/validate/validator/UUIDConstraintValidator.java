package com.example.EasyJob.common.validate.validator;

import com.example.EasyJob.common.validate.annotation.UUIDConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDConstraintValidator implements ConstraintValidator<UUIDConstraint, Object> {
    private String message;
    @Override
    public void initialize(UUIDConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object uuid, ConstraintValidatorContext constraintValidatorContext) {
        if (uuid == null) {
            return false;
        }
        if (uuid.getClass() == String.class || uuid.getClass() == UUID.class) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
            return uuid.toString().matches(regex);
        }
        return false;
    }

}
