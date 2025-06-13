package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return false;

        boolean lengthCheck = value.length() >= 8;
        boolean hasUpperCase = value.matches(".*[A-Z].*");
        boolean hasLowerCase = value.matches(".*[a-z].*");
        boolean hasDigits = value.matches(".*[0-9].*");
        boolean hasSpecial = value.matches(".*[!@#$%^&*()].*");

        return lengthCheck && hasUpperCase && hasLowerCase && hasDigits && hasSpecial;
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
