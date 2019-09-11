package com.niki.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.niki.Application;
import com.niki.entity.enums.Site;
import com.niki.handler.ApiQueryHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StackExchangeIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ApiQueryHandler apiQueryHandler;

  @Before
  public void setUp() {
    when(apiQueryHandler.executeQuery(anyString()))
        .thenReturn(getFile("response.json"));
  }

  @Test
  @SneakyThrows
  public void successful_PostToStackExchange() {
    mvc.perform(
        MockMvcRequestBuilders.post("/search")
            .param("title", "java")
            .param("site", Site.STACKOVERFLOW.getName())
    ).andExpect(MockMvcResultMatchers.model().attributeExists("questions"));
  }

  @SneakyThrows
  private String getFile(String fileName) {
    Path path = Paths.get(getClass().getClassLoader()
        .getResource(fileName).toURI());
    return Files.lines(path).collect(Collectors.joining("\n"));
  }
}
