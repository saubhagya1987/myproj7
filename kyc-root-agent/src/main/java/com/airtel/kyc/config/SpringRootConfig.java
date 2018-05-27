package com.airtel.kyc.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.airtel.kyc.enumData.TaskEnum;
import com.airtel.kyc.task.threads.AMActivateAgent;
import com.airtel.kyc.task.threads.EMABarAgent;
import com.airtel.kyc.task.threads.EMAUnbarAgent;
import com.airtel.kyc.task.threads.EMAUnbarForBulkAgent;
import com.airtel.kyc.task.threads.SVStatusAgent;
import com.airtel.kyc.task.threads.SVUpdateAgent;
import com.airtel.kyc.task.threads.SVValidateAgent;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

@Configuration
@ComponentScan(basePackages = {"com.airtel"})
//@PropertySource(value = {"classpath:kyc_resource.properties"})
@PropertySource(value = { "file:/africa_agile/opt/kyc_zm/kyc_resource.properties" })
//@PropertySource(value = { "file:/C:/Users/user1/Desktop/kyc_zm/encyption/kyc_resource.properties" })
public class SpringRootConfig {
	
	@Bean
	public Gson gson() {
		return new Gson();
	}

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
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}
	
	/*@Bean
	public SVIMSIFetchAgent SVIMSIFetchAgent(){
		return new SVIMSIFetchAgent();
	}*/

	@Bean
	public SVValidateAgent validateSVAgent(){
		return new SVValidateAgent();
	}
	
	/*@Bean
	public HPUnbarAgent HPUnbarAgent(){
		return new HPUnbarAgent();
	}*/
	
	@Bean
	public EMAUnbarAgent EMAUnbarAgent(){
		return new EMAUnbarAgent();
	}
	
	@Bean
	public SVUpdateAgent SVUpdateAgent() {
		return new SVUpdateAgent();
	}
	
	@Bean
	public AMActivateAgent AMActivateAgent() {
		return new AMActivateAgent();
	}
	
	/*@Bean
	public HPBarAgent HPBarAgent() {
		return new HPBarAgent();
	}*/
	
	@Bean
	public EMABarAgent EMABarAgent() {
		return new EMABarAgent();
	}
	
	
	@Bean
	public EMAUnbarForBulkAgent EMAUnbarForBulkAgent() {
		return new EMAUnbarForBulkAgent();
	}
	
	@Bean
	public SVStatusAgent SVStatusAgent(){
		return new SVStatusAgent();
	}
	
	@Bean
	public Map<Integer, Object> tmap() {
		/*Map<Integer, Object> map = new HashMap<>();
		map.put(TaskEnum.SV_IMSI_FETCH.getTaskCode(), SVIMSIFetchAgent());
		map.put(TaskEnum.HP_UNBARING.getTaskCode(), HPUnbarAgent());
		map.put(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(), SVUpdateAgent());
		map.put(TaskEnum.AM_ACTIVATION.getTaskCode(), AMActivateAgent());
		map.put(TaskEnum.HP_BARING.getTaskCode(), HPBarAgent());
		map.put(TaskEnum.SV_STATUS_UPDATION.getTaskCode(), SVStatusAgent());*/		
		Map<Integer, Object> map = new HashMap<>();
		map.put(TaskEnum.SIM_VALIDATION.getTaskCode(), validateSVAgent());
		map.put(TaskEnum.EMA_UNBARING.getTaskCode(), EMAUnbarAgent());
		map.put(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(), SVUpdateAgent());
		map.put(TaskEnum.AM_ACTIVATION.getTaskCode(), AMActivateAgent());
		
		map.put(TaskEnum.EMA_BARING.getTaskCode(),  EMABarAgent());
		map.put(TaskEnum.SV_STATUS_UPDATION.getTaskCode(), SVStatusAgent());
		map.put(TaskEnum.EMA_UNBARING_FOR_BULK.getTaskCode(), EMAUnbarForBulkAgent());
		return map;
	}	
}