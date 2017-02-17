package com.cms.app.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.cms.app.exception.CMSException;




@Configuration
@Import(value = {WebSecurityConfiguration.class, DataSoruceConfiguration.class, WebConfig.class})
public class AppConfiguration {


  private static final String CONFIG_LOCATION = "config.location";

  private static final String APPLICATION_PROPS = "application.properties";
  private static final String DATA_SORUCE_PROPS = "datasource.properties";
  private static final String SECRET_PROPS = "secret.properties";

  private static final List<String> PROP_FILES = Arrays.asList(APPLICATION_PROPS,
      DATA_SORUCE_PROPS, SECRET_PROPS);

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
      ServletContext context) {
    String configLocation = context.getInitParameter(CONFIG_LOCATION);
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
        new PropertySourcesPlaceholderConfigurer();


    Resource[] resources = new Resource[PROP_FILES.size()];
    int i = 0;

    for (String props : PROP_FILES) {
      try {
        resources[i++] = new UrlResource("file:" + configLocation + props);
      } catch (Exception e) {
        throw new CMSException("Error while loading props file from location : " + configLocation,
            e);
      }
    }
    propertySourcesPlaceholderConfigurer.setLocations(resources);
    return propertySourcesPlaceholderConfigurer;
  }
}
