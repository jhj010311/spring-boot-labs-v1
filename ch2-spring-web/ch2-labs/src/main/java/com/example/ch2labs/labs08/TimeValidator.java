package com.example.ch2labs.labs08;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<ValidTimeRange, ReservationRequest> {
    @Override
    public boolean isValid(ReservationRequest request, ConstraintValidatorContext context) {

        if (request.getStartTime() == null) return false;
        if (request.getEndTime() == null) return false;
        return request.getStartTime().isBefore(request.getEndTime());
    }
}
