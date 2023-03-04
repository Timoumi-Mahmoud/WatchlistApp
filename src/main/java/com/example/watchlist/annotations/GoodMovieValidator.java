package com.example.watchlist.annotations;

import com.example.watchlist.entity.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext constraintValidatorContext) {
     //   return false;77
        return !( Integer.valueOf(value.getRating()) >=8 &&
                "L".equals(value.getPriority().trim().toUpperCase()));

    }
}
