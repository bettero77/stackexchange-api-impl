package com.niki.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

  private String name;
  private String profileImage;
  private String link;
}
