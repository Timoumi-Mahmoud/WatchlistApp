package com.example.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RaitingValidator  implements ConstraintValidator<Raiting,Integer> {


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return (value < 10 && value > 0);
    }
}