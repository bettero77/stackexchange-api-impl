package com.niki.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiQueryHandler {

  public String executeQuery(String uri) {
    HttpURLConnection conn = null;
    try {
      URL url = new URL(uri);
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept-Encoding", "gzip");
      return getResponse(conn);
    } catch (IOException e) {
      log.error("Exception in query execution", e);
      return null;
    } finally {
      if (conn != null) {
        conn.disconnect();
      }
    }
  }

  private static String getResponse(HttpURLConnection conn) throws IOException {
    StringBuilder response = new StringBuilder();
    InputStream inputStream = new GZIPInputStream(conn.getInputStream());
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
    }
    return response.toString();
  }
}
