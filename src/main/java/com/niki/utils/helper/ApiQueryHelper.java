package com.niki.utils.helper;

import static com.niki.utils.constants.Constants.STACK_EXCHANGE_API_PATH_SEARCH;
import static com.niki.utils.helper.PropertyHelper.getProperties;

import com.niki.entity.SearchParams;

public class ApiQueryHelper {

  private ApiQueryHelper() {
  }

  public static String queryApi(SearchParams searchParams) {
    String apiPath = getProperties().getProperty(STACK_EXCHANGE_API_PATH_SEARCH);
    String params = QueryParamsHelper.appendQueryParams(searchParams);
    return ApiConnectionHelper.executeQuery(apiPath + "?" + params);
  }
}
