package com.niki.controller;

import static com.niki.utils.helper.ControllerHelper.addDefaultModelAttributes;
import static com.niki.utils.helper.ControllerHelper.validateRequest;
import static com.niki.utils.helper.ParseResponseHelper.parseApiResponse;

import com.niki.entity.SearchParams;
import com.niki.entity.StackExchangeQuestion;
import com.niki.utils.helper.ApiQueryHelper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "search")
public class StackExchangeController {

  @GetMapping
  public String getMainPage(Model model) {
    addDefaultModelAttributes(model);
    return "main";
  }

  @PostMapping
  public String searchInStackExchange(@Valid @ModelAttribute("searchParams") SearchParams searchParams,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      validateRequest(bindingResult, model);
      return "main";
    }
    String response = ApiQueryHelper.queryApi(searchParams);
    List<StackExchangeQuestion> questions = parseApiResponse(response);
    model.addAttribute("questions", questions);
    addDefaultModelAttributes(model);
    return "main";
  }
}
