package com.cms.app.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cms.app.exception.CMSException;
import com.cms.model.Constants;



@Component
public class AuthenticationUtil {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationUtil.class);

  private static final String ADFS_URL =
      "https://adfs3.se.scb.co.th/adfs/ls/idpinitiatedsignon.aspx";
  private static final String USER_PREFIX = "SE\\%s";
  private static final String USER_NAME = "UserName";
  private static final String PASSWORD = "Password";

  private static final String RELYING_PARTY = "RelyingParty";
  private static final String RELYING_PARTY_VALUE = "7d9c54b0-317b-e611-8126-005056840be4";

  private static final String SIGN_IN_OTHER_SITE = "SignInOtherSite";

  private static final String SIGN_IN_SUBMIT = "SignInSubmit";
  private static final String SIGN_IN_SUBMIT_VALUE = "Sign in";

  private static final String SINGLE_SIGN_OUT = "SingleSignOut";

  private static final String AUTH_METHOD = "AuthMethod";
  private static final String AUTH_METHOD_VALUE = "FormsAuthentication";

  private static final String PATH = "/adfs";
  private static final String DOMAIN = "adfs3.se.scb.co.th";

  private static final String KEY_SET_COOKIE = "Set-Cookie";

  private static final String MSISSAMLREQUEST = "MSISSamlRequest";
  private static final String MSISSAUTH = "MSISAuth";

  @Autowired
  private SSLContextUtil sslContextUtil;


  public String authenticate(String userName, String password) {

    if (StringUtils.isEmpty(userName)) {
      logger.info("User name cannot be null/empty");
      throw new CMSException("User name cannot be null/empty");
    }

    if (StringUtils.isEmpty(password)) {
      logger.info("Password cannot be null/empty");
      throw new CMSException("Password cannot be null/empty");
    }

    HttpResponse response = makeRequest(getFormParams(userName, password), null);

    Header[] headers = response.getHeaders(KEY_SET_COOKIE);
    CookieStore cookieStore = getCookieStore(headers, userName);
    isUserValidated(cookieStore);
    HttpResponse httpResponse = get(headers, cookieStore);

    try {
      String responseAsString = EntityUtils.toString(httpResponse.getEntity());
      Document parseHTML = parseHTML(responseAsString);
      String samlResponse = null;
      for (Element input : parseHTML.select("input")) {
        if (input.attr("name").equalsIgnoreCase("SAMLResponse")) {
          samlResponse = input.attr("value");
          break;
        }
      }
      return samlResponse;
    } catch (ParseException | IOException e) {
      logger.error("Error while generating auth token for user : {}", userName, e);
      throw new CMSException("Error while generating auth token for user : " + userName, e);
    }
  }

  private HttpResponse get(Header[] headers, CookieStore cookieStore) {

    SSLContext sslcontext = sslContextUtil.getSSLContext(Constants.KEY_STORE_NAME_ADFS);
    SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext);
    try {
      CloseableHttpClient httpClient =
          HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLSocketFactory(factory)
              .build();
      HttpGet httpGet = new HttpGet(ADFS_URL);
      return httpClient.execute(httpGet);
    } catch (Exception e) {
      logger.error("Error while fetching saml response", e);
      throw new CMSException("Error while fetching saml response", e);
    }
  }

  private void isUserValidated(CookieStore cookieStore) {
    boolean isUserValid = false;
    boolean isUserAuthenticated = false;
    for (Cookie cookie : cookieStore.getCookies()) {

      if (MSISSAMLREQUEST.equals(cookie.getName())) {
        isUserValid = true;
      } else if (MSISSAUTH.equals(cookie.getName())) {
        isUserAuthenticated = true;
      }
    }

    if (!isUserValid || !isUserAuthenticated) {
      logger.error("Invalid user credentials.");
      throw new BadCredentialsException("Invalid user credentials.");
    }
  }

  private CookieStore getCookieStore(Header[] headers, String userName) {
    CookieStore cookieStore = new BasicCookieStore();
    BasicClientCookie cookie = null;
    String cookieValue = null;
    for (Header hd : headers) {
      if (hd.getValue().contains(MSISSAMLREQUEST)) {

        cookieValue = hd.getValue().split(";")[0];
        if (StringUtils.isEmpty(cookieValue)) {
          logger.error("Some error occured while logging user : {}", userName);
          throw new CMSException("Some error occured while logging user : " + userName);
        }
        cookie =
            new BasicClientCookie(MSISSAMLREQUEST, cookieValue.replace("MSISSamlRequest=", ""));
      } else if (hd.getValue().contains(MSISSAUTH)) {
        cookieValue = hd.getValue().split(";")[0];
        if (StringUtils.isEmpty(cookieValue)) {
          logger.error("Bad credentials for user : {}", userName);
          throw new BadCredentialsException("Bad credentials for user : " + userName);
        }
        cookie = new BasicClientCookie(MSISSAUTH, cookieValue.replace("MSISAuth=", ""));
      }
      cookie.setPath(PATH);
      cookie.setDomain(DOMAIN);
      cookie.setSecure(Boolean.TRUE);
      cookieStore.addCookie(cookie);
    }
    return cookieStore;
  }

  private HttpResponse makeRequest(List<NameValuePair> params, Header header) {

    SSLContext sslcontext = sslContextUtil.getSSLContext(Constants.KEY_STORE_NAME_ADFS);
    SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext);
    CloseableHttpClient httpClient = null;

    try {
      HttpPost httpPost = new HttpPost(ADFS_URL);
      httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
      HttpResponse response = null;
      httpClient = HttpClients.custom().setSSLSocketFactory(factory).build();
      response = httpClient.execute(httpPost);

      if (response == null) {
        logger.error(String.format("No response obtained for url request : %s ", ADFS_URL));
        throw new CMSException(
            String.format("No response obtained for url request : %s ", ADFS_URL));
      }
      return response;
    } catch (IOException e) {
      logger.error("Error while obtaining response from url : %s", ADFS_URL);
      throw new CMSException(
          String.format("Error while obtaining response from url : %s", ADFS_URL));
    }
  }

  private List<NameValuePair> getFormParams(String userName, String password) {
    List<NameValuePair> formparams = new ArrayList<>();
    formparams.add(new BasicNameValuePair(AUTH_METHOD, AUTH_METHOD_VALUE));
    formparams.add(new BasicNameValuePair(PASSWORD, password));
    formparams.add(new BasicNameValuePair(USER_NAME, String.format(USER_PREFIX, userName)));
    formparams.add(new BasicNameValuePair(RELYING_PARTY, RELYING_PARTY_VALUE));
    formparams.add(new BasicNameValuePair(SIGN_IN_OTHER_SITE, SIGN_IN_OTHER_SITE));
    formparams.add(new BasicNameValuePair(SIGN_IN_SUBMIT, SIGN_IN_SUBMIT_VALUE));
    formparams.add(new BasicNameValuePair(SINGLE_SIGN_OUT, SINGLE_SIGN_OUT));
    return formparams;
  }

  private Document parseHTML(String html) {
    return Jsoup.parse(html);
  }

}
