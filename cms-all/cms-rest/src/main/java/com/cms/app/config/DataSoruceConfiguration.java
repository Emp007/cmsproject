package com.cms.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.MongoClient;

@Configuration
@EnableTransactionManagement
/*@PropertySource(value =
	 "file:///Users/virendra/Documents/cms-static/cms/props/datasource.properties")*/
@PropertySource(value =
 "file:///home/orange/Desktop/sherlock-desktop/props/datasource.properties")
@EnableMongoRepositories(mongoTemplateRef = "mongoTemplate" ,basePackages = "com.cms.app.dao")
public class DataSoruceConfiguration {
	
	
	@Value("${spring.data.mongodb.host}")
    private String hostBO;

    @Value("${spring.data.mongodb.database}")
    private String databaseBO;

    @Bean
    public MongoDbFactory mongobFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(hostBO), databaseBO);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongobFactory());
    }

    
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
    
 /* private static final String CHARSET_UTF8 = " UTF-8";

  @Value("${db.driver}")
  private String PROPERTY_NAME_DATABASE_DRIVER;

  @Value("${db.password}")
  private String PROPERTY_NAME_DATABASE_PASSWORD;

  @Value("${db.url}")
  private String PROPERTY_NAME_DATABASE_URL;

  @Value("${db.username}")
  private String PROPERTY_NAME_DATABASE_USERNAME;

  @Value("${hibernate.dialect}")
  private String PROPERTY_NAME_HIBERNATE_DIALECT;

  @Value("${hibernate.show_sql}")
  private String PROPERTY_NAME_HIBERNATE_SHOW_SQL;

  @Value("${entitymanager.packages.to.scan}")
  private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;

  @Value("${hibernate.hbm2ddl.auto}")
  private String PROPERTY_DDL_AUTO;

  private static final String PROPERTY_UNICODE = "hibernate.connection.useUnicode";
  private static final String PROPERTY_CHAR_ENCODING = "hibernate.connection.characterEncoding";
  private static final String PROPERTY_CHAR_SET = "hibernate.connection.charSet";


  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
    dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
    dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
    dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();

    entityManagerFactoryBean.setDataSource(dataSource());

    entityManagerFactoryBean.setEntityManagerInterface(HibernateEntityManager.class);

    entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

    entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);

    entityManagerFactoryBean.setJpaProperties(hibProperties());

    return entityManagerFactoryBean;

  }

  private Properties hibProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
    properties.put("hibernate.show_sq", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
    properties.put("hibernate.show_sq", PROPERTY_DDL_AUTO);
    properties.put(PROPERTY_UNICODE, Boolean.TRUE);
    properties.put(PROPERTY_CHAR_ENCODING, CHARSET_UTF8);
    properties.put(PROPERTY_CHAR_SET, CHARSET_UTF8);
    return properties;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;

  }*/
  
  
  
  
  

}
