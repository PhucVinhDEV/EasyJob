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
    public boolean isValid(Object uuid, ConstraintValidatorContext context) {
        if (uuid == null) {
            return true; // null hợp lệ nếu bạn cho phép null là giá trị hợp lệ
        }

        if (uuid instanceof String uuidString) {
            String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
            return uuidString.matches(regex); // Kiểm tra chuỗi có phải là UUID hợp lệ không
        }

        if (uuid instanceof UUID) {
            // Nếu là UUID hợp lệ, luôn trả về true
            return true;
        }

        return false; // Nếu không phải String hoặc UUID thì coi như không hợp lệ
    }

}
