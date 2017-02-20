package com.cms.app.util;


import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.cms.app.exception.CMSException;
import com.cms.model.Constants;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SSLContextUtil {

  private static final Logger logger = LoggerFactory.getLogger(SSLContextUtil.class);

  private static final String KEY_STORE_PATH = "/home/orange/Desktop/sherlock-desktop/keystore/";

  private static final String KEY_STORE_NAME_EIC = Constants.KEY_STORE_NAME_EIC;
  private static final String KEY_STORE_NAME_ADFS = Constants.KEY_STORE_NAME_ADFS;

  private static final List<String> KEY_STORE_LIST = Arrays.asList(KEY_STORE_NAME_EIC,
      KEY_STORE_NAME_ADFS);

  @Value("${key.store.password}")
  private String keyStorePassword;

  @Value("${adfs.key.store.password}")
  private String adfsKeyStorePassword;

  private static Map<String, SSLContext> sslContextMap = new HashMap<>();

  public SSLContext getSSLContext(String keyStoreName) {

    if (CollectionUtils.isEmpty(sslContextMap)) {
       loadKeyStores();
    }
    return sslContextMap.get(keyStoreName);
  }

  private void loadKeyStores() {
    KEY_STORE_LIST.forEach(keyName -> {

      // ClassLoader loader = Thread.currentThread().getContextClassLoader();
      // String path = loader.getResource(keyName).getPath();
        String path = KEY_STORE_PATH + keyName;

        try (FileInputStream inStream = new FileInputStream(new File(path))) {
          KeyStore trustStore = KeyStore.getInstance("JKS");
          String keyPassword = getKeyStorePassword(keyName);
          if (StringUtils.isEmpty(keyPassword)) {
            logger.error("No password configured for keystore {}", keyName);
            return;
          }
          trustStore.load(inStream, keyPassword.toCharArray());
          sslContextMap.put(keyName,
              SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                  .build());
        } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException
            | KeyManagementException e) {
          logger.error("Error while loading ssl context", e);
          throw new CMSException("Error while loading ssl context", e);
        }
      });
  }

  private String getKeyStorePassword(String keyName) {
    String keyPassword = null;
    if (KEY_STORE_NAME_EIC.equals(keyName)) {
      keyPassword = keyStorePassword;
    } else if (KEY_STORE_NAME_ADFS.equals(keyName)) {
      keyPassword = adfsKeyStorePassword;
    }
    return keyPassword;
  }
}
