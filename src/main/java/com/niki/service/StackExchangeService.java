package com.niki.service;

import static com.niki.utils.constants.Constants.STACK_EXCHANGE_API_PATH_SEARCH;
import static com.niki.utils.helper.PropertyHelper.getProperties;

import com.niki.entity.SearchParams;
import com.niki.entity.StackExchangeQuestion;
import com.niki.handler.ApiQueryHandler;
import com.niki.handler.ParseResponseHandler;
import com.niki.utils.helper.QueryParamsHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StackExchangeService {

  private final ApiQueryHandler apiQueryHandler;
  private final ParseResponseHandler parseResponseHandler;

  public List<StackExchangeQuestion> queryApi(SearchParams searchParams) {
    String apiPath = getProperties().getProperty(STACK_EXCHANGE_API_PATH_SEARCH);
    String params = QueryParamsHelper.appendQueryParams(searchParams);
    String responseString = apiQueryHandler.executeQuery(apiPath + "?" + params);
    return parseResponseHandler.parseResponse(responseString);
  }
}
