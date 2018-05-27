package com.airtel.kyc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@PropertySource(value = { "classpath:persistence.properties" })
@PropertySource(value = { "file:/africa_agile/opt/kyc_zm/persistence.properties" })
//@PropertySource(value = { "file:/C:/Users/user1/Desktop/kyc_zm/encyption/persistence.properties" })

@ComponentScan("com.airtel")
public class HibernateConfig {

	@Autowired
	private Environment environment;

	@Bean
	@Primary
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		//sessionFactory.setPackagesToScan(new String[] { "com.airtel" });
		sessionFactory.setPackagesToScan(environment.getProperty("package.name"));
		sessionFactory.setHibernateProperties(additionalProperties());
		
		return sessionFactory;
	}

	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	
	@Bean
	public DataSource dataSource() {
	/*	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
		dataSource.setUrl(environment.getProperty("datasource.url"));
		dataSource.setUsername(environment.getProperty("datasource.username"));
		dataSource.setPassword(environment.getProperty("datasource.password"));
		Properties properties = new Properties();
		properties.setProperty("useUnicode", "true");
		properties.setProperty("characterEncoding", "UTF-8");
		properties.setProperty("charSet", "UTF-8");
		properties.setProperty("hibernate.default_schema",environment.getProperty("hibernate.default_schema"));		
		dataSource.setConnectionProperties(properties);
		return dataSource;*/
		
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
		dataSource.setUrl(environment.getProperty("datasource.url"));
		dataSource.setUsername(environment.getProperty("datasource.username"));
		dataSource.setPassword(environment.getProperty("datasource.password"));
		dataSource.setInitialSize(Integer.parseInt(environment.getProperty("datasource.initsize")));
		dataSource.setMaxActive(Integer.parseInt(environment.getProperty("datasource.maxActive")));
		dataSource.setValidationQuery(environment.getProperty("datasource.validationQuery"));
		//dataSource.setMaxIdle(Integer.parseInt(environment.getProperty("datasource.maxIdle")));
		dataSource.setMaxWait(Integer.parseInt(environment.getProperty("datasource.maxWait")));
		//dataSource.setPoolPreparedStatements(true);
		dataSource.setPoolPreparedStatements(new Boolean(environment.getProperty("datasource.preparedStatements.active.flag")));		
		return dataSource;
	}
    
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.connection.useUnicode", "true");
		properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
		properties.setProperty("hibernate.connection.charSet", "UTF-8");
		properties.setProperty("hibernate.default_schema",environment.getProperty("hibernate.default_schema"));
		properties.setProperty("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.event.merge.entity_copy_observer","allow");
		//properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		properties.setProperty("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.EhCacheRegionFactory");
		properties.setProperty("hibernate.cache.use_second_level_cache", "true");
		properties.setProperty("hibernate.cache.use_query_cache", "true");
		//properties.setProperty("hibernate.generate_statistics", "true");
		
		

		return properties;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

}

