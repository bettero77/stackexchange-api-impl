package com.niki.entity;


import com.niki.entity.enums.Site;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchParams {

  @NotBlank
  @Size(min = 1, max = 20)
  private String title;
  private Site site;
}
