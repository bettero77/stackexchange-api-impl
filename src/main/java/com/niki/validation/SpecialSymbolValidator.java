package com.niki.validation;

import com.niki.validation.annotation.SpecialSymbolConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpecialSymbolValidator implements
    ConstraintValidator<SpecialSymbolConstraint, String> {

  @Override
  public void initialize(SpecialSymbolConstraint contactNumber) {
  }

  @Override
  public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
    return contactField != null && contactField.matches("[a-zA-Z0-9]+");
  }
}
