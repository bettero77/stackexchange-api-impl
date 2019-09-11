package com.niki.controller;

import com.niki.Application;
import com.niki.entity.enums.Site;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class StackExchangeControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  @SneakyThrows
  public void successful_GetPage() {
    mvc.perform(
        MockMvcRequestBuilders.get("/search")
    ).andExpect(MockMvcResultMatchers.status().isOk());
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

  @Test
  @SneakyThrows
  public void invalidParam_PostToStackExchange() {
    mvc.perform(
        MockMvcRequestBuilders.post("/search")
            .param("title", "c#")
            .param("site", Site.STACKOVERFLOW.getName())
    ).andExpect(MockMvcResultMatchers.model().attributeExists("errors"));
  }

}
