package com.niki.handler;

import static com.niki.utils.constants.Constants.ITEMS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niki.entity.StackExchangeQuestion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseResponseHandler {

  private final ObjectMapper jsonMapper;

  @SneakyThrows
  public List<StackExchangeQuestion> parseResponse(String responseString) {
    JsonNode response = jsonMapper.readTree(responseString);
    JsonNode stackExchangeQuestions = response.findPath(ITEMS);
    return jsonMapper.readValue(
        jsonMapper.treeAsTokens(stackExchangeQuestions),
        new TypeReference<List<StackExchangeQuestion>>() {
        });
  }
}
