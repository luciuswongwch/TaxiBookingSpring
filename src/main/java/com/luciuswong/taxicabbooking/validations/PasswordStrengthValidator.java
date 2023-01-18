package com.luciuswong.taxicabbooking.validations;

import com.luciuswong.taxicabbooking.annotation.PasswordValidator;
import com.luciuswong.taxicabbooking.config.CustomProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
    List<String> weakPasswords = Arrays.asList("12345", "password");

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false;
        for (String pwd : weakPasswords) {
            if (s.toLowerCase().contains(pwd)) return false;
        }
        return true;
    }
}
