package com.airtel.kyc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
@ComponentScan(basePackages ="com.airtel")
//@PropertySource(value = {"classpath:kyc.properties"})
@PropertySource(value = { "file:/africa_agile/opt/kyc_zm/kyc.properties" })
//@PropertySource(value = { "file:/C:/Users/user1/Desktop/kyc_zm/encyption/kyc.properties" })

public class SpringMVCConfiguration extends WebMvcConfigurerAdapter{

	private static final Logger LOG = LoggerFactory.getLogger(SpringMVCConfiguration.class);
	
	public SpringMVCConfiguration() {
		//LOG.info("Spring MVC Configuration loading. . . .");
		System.out.println("Spring MVC Configuration loading. . . .");
	}
	
	
	
	@Bean
	public Gson gson() {
		return new Gson();
	}

	
	@Bean
	public ObjectWriter getObjectWriter(){
	  return new ObjectMapper().writerWithDefaultPrettyPrinter();
	 }
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {

		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();	
		source.setBasenames("kyc", "kyc");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver url = new InternalResourceViewResolver();
		url.setViewClass(JstlView.class);
		url.setPrefix("/WEB-INF/view/");
		url.setSuffix(".jsp");
		return url;
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	@Bean
	@JsonIgnore
	public MultipartResolver multipartResolver() {
		MultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}
	
}

