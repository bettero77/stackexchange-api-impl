package com.niki.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Date;

public class CustomDateDeserializer extends StdDeserializer<Date> {

  public CustomDateDeserializer() {
    this(null);
  }

  public CustomDateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException {
    String dateString = jsonparser.getText();
    return new Date(Long.parseLong(dateString) * 1000);
  }
}

