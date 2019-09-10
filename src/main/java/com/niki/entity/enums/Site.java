package com.niki.entity.enums;

public enum Site {

  GAMEDEV("gamedev"),
  WEBMASTERS("webmasters"),
  STACKOVERFLOW("stackoverflow");

  private String name;

  Site(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
