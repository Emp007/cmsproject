package com.cms.app.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DummyDataUtil {


  private static final ObjectMapper mapper = new ObjectMapper();

  private static JsonNode dataNode = null;

  private static void readData() {



    try {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      dataNode = mapper.readTree(loader.getResourceAsStream("product.json"));
    } catch (JsonProcessingException e) {
    } catch (IOException e) {
    }
  }


  public static String getAuthToken() {

    if (dataNode == null) {
      readData();
    }

    return dataNode.get("authToken").toString();
  }

  public static String getProductCategories() {
    if (dataNode == null) {
      readData();
    }
    return dataNode.get("categories").toString();
  }

  public static String getProduct() {
    if (dataNode == null) {
      readData();
    }
    return dataNode.get("products").toString();
  }

}
