package com.niki.validation;

import static com.niki.utils.constants.Constants.TITLE_CONSTRAINT;

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
    return contactField != null && contactField.matches(TITLE_CONSTRAINT);
  }
}
