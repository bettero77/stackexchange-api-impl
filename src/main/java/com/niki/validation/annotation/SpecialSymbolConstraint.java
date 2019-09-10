package com.niki.validation.annotation;

import com.niki.validation.SpecialSymbolValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = SpecialSymbolValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialSymbolConstraint {

  String message() default "Invalid symbol";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
