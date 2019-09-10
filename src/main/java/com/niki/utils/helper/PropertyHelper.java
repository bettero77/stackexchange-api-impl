package com.niki.utils.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertyHelper {

  private PropertyHelper() {
  }

  public static Properties getProperties() {
    Properties properties = new Properties();
    String rootPath =
        Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    String appConfigPath = rootPath + "application.properties";
    try (InputStream inputStream = new FileInputStream(appConfigPath)) {
      properties.load(inputStream);
    } catch (IOException e) {
      log.error("Can't load properties", e);
    }
    return properties;
  }

}
