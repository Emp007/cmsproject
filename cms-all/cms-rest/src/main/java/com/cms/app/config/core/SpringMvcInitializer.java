package com.cms.app.config.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.cms.app.config.AppConfiguration;


public class SpringMvcInitializer

implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(AppConfiguration.class);
    ctx.setServletContext(servletContext);

    Dynamic addServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    addServlet.addMapping("/");
    addServlet.setLoadOnStartup(1);
  }
}
