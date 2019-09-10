package com.niki.utils.helper;

import static com.niki.utils.constants.Constants.CREATION_DATE;
import static com.niki.utils.constants.Constants.DISPLAY_NAME;
import static com.niki.utils.constants.Constants.IS_ANSWERED;
import static com.niki.utils.constants.Constants.ITEMS;
import static com.niki.utils.constants.Constants.LINK;
import static com.niki.utils.constants.Constants.OWNER;
import static com.niki.utils.constants.Constants.PROFILE_IMAGE;
import static com.niki.utils.constants.Constants.QUESTION_ID;
import static com.niki.utils.constants.Constants.TITLE;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niki.entity.Author;
import com.niki.entity.StackExchangeQuestion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.SneakyThrows;

public class ParseResponseHelper {

  private ParseResponseHelper() {
  }

  @SneakyThrows
  public static List<StackExchangeQuestion> parseApiResponse(String responseString) {
    List<StackExchangeQuestion> questions = new ArrayList<>();
    JsonNode response = new ObjectMapper().readTree(responseString);
    JsonNode stackExchangeQuestions = response.findPath(ITEMS);

    stackExchangeQuestions.forEach(question -> {
      Author author = parseAuthor(question.get(OWNER));
      Boolean isAnswered = question.has(IS_ANSWERED) && question.get(IS_ANSWERED).asBoolean();
      long creationDate = question.has(CREATION_DATE) ? question.get(CREATION_DATE).asLong() : 0L;
      Long questionId = question.has(QUESTION_ID) ? question.get(QUESTION_ID).asLong() : null;
      String link = question.has(LINK) ? question.get(LINK).asText() : "";
      String title = question.has(TITLE) ? question.get(TITLE).asText() : "";

      questions.add(StackExchangeQuestion.builder()
          .questionId(questionId)
          .creationDate(new Date(creationDate * 1000))
          .title(title)
          .author(author)
          .answered(isAnswered)
          .link(link)
          .build());
    });
    return questions;
  }

  private static Author parseAuthor(JsonNode author) {
    String profileImage = author.has(PROFILE_IMAGE) ? author.get(PROFILE_IMAGE).asText() : "";
    String name = author.has(DISPLAY_NAME) ? author.get(DISPLAY_NAME).asText() : "";
    String link = author.has(LINK) ? author.get(LINK).asText() : "";
    return Author.builder()
        .profileImage(profileImage)
        .name(name)
        .link(link)
        .build();
  }
}
