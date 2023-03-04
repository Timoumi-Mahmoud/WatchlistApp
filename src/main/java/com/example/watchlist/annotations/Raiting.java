package com.example.watchlist.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RaitingValidator.class)
public @interface Raiting {


    String message() default "please enter  number between 1 and 10 ";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
