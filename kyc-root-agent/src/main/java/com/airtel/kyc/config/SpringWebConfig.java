package com.airtel.kyc.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.airtel" })
//@PropertySource(value = {"classpath:kyc_resource.properties","classpath:kyc.properties","classpath:am.properties","classpath:sv.properties","classpath:ema.properties"})
@PropertySource(value = {"file:/africa_agile/opt/kyc_zm/kyc_resource.properties","file:/africa_agile/opt/kyc_zm/kyc.properties","file:/africa_agile/opt/kyc_zm/am.properties","file:/africa_agile/opt/kyc_zm/sv.properties","file:/africa_agile/opt/kyc_zm/ema.properties"})
//@PropertySource(value = {"file:/C:/Users/user1/Desktop/kyc_zm/encyption/kyc_resource.properties","file:/C:/Users/user1/Desktop/kyc_zm/encyption/kyc.properties","file:/C:/Users/user1/Desktop/kyc_zm/encyption/am.properties","file:/C:/Users/user1/Desktop/kyc_zm/encyption/sv.properties","file:/C:/Users/user1/Desktop/kyc_zm/encyption/ema.properties"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	static Logger logger = Logger.getLogger(SpringWebConfig.class.getName());

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

	/*
	 Bean name must be "multipartResolver", by default Spring uses method name
	 as bean name.
	*/
	/*@Bean
	@JsonIgnore
	public MultipartResolver multipartResolver() {
		MultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}*/
}
