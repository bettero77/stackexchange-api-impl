package com.niki.entity;


import com.niki.validation.annotation.SpecialSymbolConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchParams {

  @NotBlank
  @Size(min = 1, max = 20)
  @SpecialSymbolConstraint
  private String title;
  private String site;
}
