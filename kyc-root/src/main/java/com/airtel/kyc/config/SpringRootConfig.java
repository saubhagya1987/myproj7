package com.airtel.kyc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

@Configuration
@ComponentScan(basePackages = {"com.airtel"})
//@PropertySource(value = {"classpath:kyc.properties"})
@PropertySource(value = { "file:/africa_agile/opt/kyc_zm/kyc.properties" })
//@PropertySource(value = { "file:/C:/Users/user1/Desktop/kyc_zm/encyption/kyc.properties" })
public class SpringRootConfig {
	
	@Bean
	public Gson gson() {
		return new Gson();
	}

	//@Bean(name = "objectWriter")
	@Bean
	public ObjectWriter getObjectWriter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper.writerWithDefaultPrettyPrinter();
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {

		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		//source.setBasenames("messages", "notification");
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    return converter;
	}
	
	
}
