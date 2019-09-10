package com.niki.utils.helper;

import static com.niki.utils.constants.Constants.INTITLE;
import static com.niki.utils.constants.Constants.SITE;

import com.niki.entity.SearchParams;

public class QueryParamsHelper {

  private QueryParamsHelper() {
  }

  public static String appendQueryParams(SearchParams searchParams) {
    StringBuilder builder = new StringBuilder();
    appendParam(INTITLE, searchParams.getTitle(), builder);
    appendParam(SITE, searchParams.getSite(), builder);
    builder.delete(builder.length() - 1, builder.length());
    return builder.toString();
  }

  private static void appendParam(String paramName, Object value, StringBuilder builder) {
    builder.append(paramName).append("=").append(value).append("&");
  }
}
