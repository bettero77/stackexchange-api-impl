package com.niki.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StackExchangeQuestion {

  private Long questionId;
  private Date creationDate;
  private String title;
  private Author author;
  private Boolean answered;
  private String link;
}
