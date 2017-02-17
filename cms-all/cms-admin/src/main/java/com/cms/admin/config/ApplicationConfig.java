package com.cms.admin.config;

import java.net.MalformedURLException;
import javax.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.cms.admin.Application;
import com.cms.admin.CMSAdminException;
import com.cms.model.Constants;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
class ApplicationConfig {


  private static final String APPLICATION_PROPS = "application.properties";

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
      ServletContext servletContext) {

    String configLocation = servletContext.getInitParameter(Constants.CONFIG_LOCATION);

    PropertySourcesPlaceholderConfigurer propertyConfig =
        new PropertySourcesPlaceholderConfigurer();

    try {
      propertyConfig.setLocation(new UrlResource(String.format(Constants.PROP_LOCATION_FROMAT,
          configLocation, APPLICATION_PROPS)));
    } catch (MalformedURLException e) {
      throw new CMSAdminException("Error while loading props file from location : "
          + configLocation, e);
    }
    return propertyConfig;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(50 * 1024 * 1024);
    return multipartResolver;
  }
}
