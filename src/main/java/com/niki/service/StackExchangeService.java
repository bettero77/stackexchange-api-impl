package com.niki.service;

import com.niki.entity.SearchParams;
import com.niki.entity.StackExchangeQuestion;
import com.niki.handler.ApiQueryHandler;
import com.niki.handler.ParseResponseHandler;
import com.niki.utils.helper.QueryParamsHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StackExchangeService {

  @Value("${stack-exchange.api.path.search}")
  private String searchApiPath;

  private final ApiQueryHandler apiQueryHandler;
  private final ParseResponseHandler parseResponseHandler;

  public List<StackExchangeQuestion> queryApi(SearchParams searchParams) {
    String params = QueryParamsHelper.appendQueryParams(searchParams);
    String responseString = apiQueryHandler.executeQuery(searchApiPath + "?" + params);
    return parseResponseHandler.parseResponse(responseString);
  }
}
