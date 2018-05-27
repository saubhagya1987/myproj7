package com.airtel.africa.airtel.money.service.impl;

import java.net.URI;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.airtel.africa.airtel.money.exceptions.AitelMoneyException;
import com.airtel.africa.airtel.money.helpers.AMBarUnbarRequest;
import com.airtel.africa.airtel.money.helpers.AMBarUnbarResponse;
import com.airtel.africa.airtel.money.helpers.AMResponse;
import com.airtel.africa.airtel.money.helpers.AirtelMoneyConstants;
import com.airtel.africa.airtel.money.service.AirtelMoneyService;

/**
 * @author Hem Chand
 *
 */
@PropertySource({ "classpath:" + AirtelMoneyConstants.CONFIG_FILE })
@org.springframework.stereotype.Service
public class AirtelMoneyServiceImpl implements AirtelMoneyService, AirtelMoneyConstants {

	private static Log loger = LogFactory.getLog(AirtelMoneyServiceImpl.class);

	@Autowired
	private Environment environment;
	
	@Override
	public AMResponse registerOnAM(AMBarUnbarRequest aMBarUnbarRequest) throws AitelMoneyException {
		loger.info("*****registerOnAM*****");
		AMResponse amResponse = new AMResponse();
		AMBarUnbarResponse amBarUnbarResponse = new AMBarUnbarResponse();		
		RestTemplate restTemplate = new RestTemplate();	
		String url=this.environment.getProperty(AM_BASE_URL);	
	    URI targetUrl= UriComponentsBuilder.fromUriString(url)
	    		.queryParam("cmd", this.environment.getProperty(AM_CMD_REGISTER))
	    	    .queryParam("custtype",aMBarUnbarRequest.getCusttype() )
	    	    .queryParam("firstname",aMBarUnbarRequest.getFirstname())
	    	    .queryParam("lastname", aMBarUnbarRequest.getLastname())
	    	    .queryParam("idtype",aMBarUnbarRequest.getIdtype())
	    	    .queryParam("regid",aMBarUnbarRequest.getRegid())
	    	    .queryParam("retailerMSISDN",aMBarUnbarRequest.getRetailerMSISDN())
	    	    .queryParam("pin", aMBarUnbarRequest.getPin())
	    	    .queryParam("MSISDN",aMBarUnbarRequest.getMSISDN())
	    	    .queryParam("custmdn", aMBarUnbarRequest.getCustmdn())
	    	    .build()
	    	    .toUri();
	    amResponse.setRequest(targetUrl.toString());
	    HttpEntity<String> response = restTemplate.exchange(targetUrl, HttpMethod.POST, null, String.class);
	    HttpHeaders responseHeader = response.getHeaders();
  	    List<String> obj=responseHeader.get("Oboresponse");
	    String responseCode=obj.get(0);
	    String body=response.getBody();
	    amBarUnbarResponse.setBody(body);
	    amBarUnbarResponse.setResponseCode(responseCode);
	    
	    if("0".equals(responseCode)){
	    	amResponse.setResult(Boolean.TRUE);
	    	amResponse.setResponse(body);
	    }else if("1".equals(responseCode) && (body.contains("already") && body.contains("exists"))){
	    	amResponse.setResult(Boolean.TRUE);
	    	amResponse.setResponse(body);
	    }else{
	    	amResponse.setResult(Boolean.FALSE);
	    	amResponse.setResponse(body);
	    }
		return amResponse;
	}
	
	
	public static void main(String[] args) throws AitelMoneyException {
		AirtelMoneyServiceImpl airtelMoneyServiceImpl=new AirtelMoneyServiceImpl();		
		AMBarUnbarRequest aMBarUnbarRequest = null;
		airtelMoneyServiceImpl.registerOnAM(aMBarUnbarRequest);
		
	}
}