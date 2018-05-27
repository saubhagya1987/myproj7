package com.airtel.kyc.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//@Order(1)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(WebInitializer.class);

	public WebInitializer() {
		LOG.info("Web Initializer loading");
		System.out.println("Spring MVC Configuration loading. . . .");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringMVCConfiguration.class,HibernateConfig.class,AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	
	
	@Override
    protected Filter[] getServletFilters() {
        return new Filter[] {              
             new DelegatingFilterProxy( new CORSFilter()),   
             new DelegatingFilterProxy("requestFilter") 
        };
    }
	
	
 
}