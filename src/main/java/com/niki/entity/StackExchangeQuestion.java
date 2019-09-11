package com.niki.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.niki.deserializer.CustomDateDeserializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StackExchangeQuestion {

  @JsonProperty("question_id")
  private Long questionId;
  @JsonProperty("creation_date")
  @JsonDeserialize(using = CustomDateDeserializer.class)
  private Date creationDate;
  private String title;
  @JsonProperty("owner")
  private Author author;
  @JsonProperty("is_answered")
  private Boolean answered;
  private String link;
}
