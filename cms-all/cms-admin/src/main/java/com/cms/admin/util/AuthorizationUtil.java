package com.cms.admin.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.cms.admin.CMSAdminException;
import com.cms.admin.service.cache.CacheService;
import com.cms.model.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class AuthorizationUtil {

  private final static Logger logger = LoggerFactory.getLogger(AuthorizationUtil.class);

  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private final String scbRestUser;
  private final String password;
  private final HttpUtil httpUtil;
  private final CacheService<String, Object> cacheService;

  @Autowired
  public AuthorizationUtil(@Value("admin@gmail.com") String scbRestUser,
      @Value("test1234!") String password, HttpUtil httpUtil,
      @Qualifier("cacheService") CacheService<String, Object> cacheService) {
    this.scbRestUser = scbRestUser;
    this.password = password;
    this.httpUtil = httpUtil;
    this.cacheService = cacheService;
  }

  public String generateAuthToken() {

    Map<String, String> headers = new HashMap<>();
    headers.put(CMSConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    String response =
        httpUtil.postOrPut(URLConstants.AUTHORIZATION_URL, getAuthPayload(), headers,
            HttpMethod.POST);

    JsonNode responseData = null;
    try {
      responseData = parseResponse(response);
    } catch (IOException e) {
      logger.error("Error while parsing authorization token response", e);
      throw new CMSAdminException("Error while parsing authorization token response", e);
    }

    if (responseData == null) {
      logger.error("No response obtained from Authorization api");
      throw new CMSAdminException("No response obtained from Authorization api");
    }

    int statusCode = Integer.parseInt(responseData.get(CMSConstant.STATUS_CODE).toString());

    if (statusCode != 200) {
      String msg =
          String.format("API responded with error code : %d and message : %s", statusCode,
              responseData.get(CMSConstant.MESSAGE).toString());
      logger.error(msg);
      throw new CMSAdminException(msg);
    }
    JsonNode dataNode = responseData.get(CMSConstant.DATA);
    String authorizationToken = dataNode.get(Constants.AUTH_TOKEN).asText();
    cacheService.put(CMSConstant.AUTH_HEADER, authorizationToken);
    return authorizationToken;
  }

  private JsonNode parseResponse(String response) throws JsonParseException, JsonMappingException,
      IOException {
    return OBJECT_MAPPER.readTree(response);
  }

  private Map<String, String> getAuthPayload() {
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put(CMSConstant.EMAIL, scbRestUser);
    dataMap.put(CMSConstant.PASSWORD, password);
    return dataMap;
  }

}
