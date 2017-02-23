package com.cms.app.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.cms.app.exception.CMSException;
import com.cms.model.Constants;



@Component
public class HttpUtil {

  private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

  private static final String CHARSET_UTF8 = "UTF-8";

  @Autowired
  private SSLContextUtil sslContextUtil;

  public String get(String url, Map<String, String> headers, Map<String, Object> parameters,
      boolean isSelfSignedCertificateRequest) throws URISyntaxException {

    if (StringUtils.isEmpty(url)) {
      logger.info("URL cannot be empty/null");
      throw new IllegalArgumentException("URL cannot be empty/null");
    }

    CloseableHttpClient httpClient = null;

    if (isSelfSignedCertificateRequest) {
      SSLContext sslcontext = sslContextUtil.getSSLContext(Constants.KEY_STORE_NAME_EIC);
      SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext);
      httpClient = HttpClients.custom().setSSLSocketFactory(factory).build();
    } else {
      httpClient = HttpClientBuilder.create().build();
    }

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    if (!CollectionUtils.isEmpty(parameters)) {
      parameters.forEach((paramName, paramValue) -> {
        params.add(new BasicNameValuePair(paramName, String.valueOf(paramValue)));
      });
    }

    URI uri =
        new URIBuilder(url).setCharset(Charset.forName(CHARSET_UTF8)).addParameters(params).build();

    HttpGet getRequest = new HttpGet(uri);

    if (!CollectionUtils.isEmpty(headers)) {
      headers.forEach((headerName, headerValue) -> {
        getRequest.setHeader(headerName, headerValue);
      });
    }

    HttpResponse response = null;
    try {
      response = httpClient.execute(getRequest);
      if (response == null) {
        logger.error(String.format("No response obtained for url request : %s ", url));
        throw new CMSException(String.format("No response obtained for url request : %s ", url));
      }
      String parsedResponse = parseHttpResponse(response, url);
      System.out.println(parsedResponse);
      httpClient.close();
      return parsedResponse;
    } catch (IOException e) {
      logger.error("Error while obtaining response from url : %s", url);
      throw new CMSException(String.format("Error while obtaining response from url : %s", url));
    }
  }

  private String parseHttpResponse(HttpResponse response, String url) {
    try (BOMInputStream in = new BOMInputStream(response.getEntity().getContent());) {
      ByteOrderMark bom = in.getBOM();
      String charsetName = bom == null ? CHARSET_UTF8 : bom.getCharsetName();
      return IOUtils.toString(in, charsetName);
    } catch (UnsupportedOperationException | IOException e) {
      logger.error("Error while parsing response for URL : {}", url);
      throw new CMSException("Error while parsing response for URL : " + url, e);
    }
  }

}
