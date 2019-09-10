package com.niki.utils.helper;

import com.niki.entity.SearchParams;
import com.niki.entity.enums.Site;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class ControllerHelper {

  private ControllerHelper() {
  }

  public static void addDefaultModelAttributes(Model model) {
    model.addAttribute("searchParams", new SearchParams());
    model.addAttribute("sites", Site.values());
  }

  public static void validateRequest(BindingResult bindingResult, Model model) {
    model.addAttribute("errors",
        bindingResult.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList())
    );
    addDefaultModelAttributes(model);
  }
}
