package com.airtel.africa.am.serviceimpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.airtel.africa.am.exceptions.AitelMoneyException;
import com.airtel.africa.am.exceptions.AitelMoneyExceptionCodes;
import com.airtel.africa.am.helpers.AMBarUnbarRequest;
import com.airtel.africa.am.helpers.AMBarUnbarResponse;
import com.airtel.africa.am.helpers.AMChurnRequest;
import com.airtel.africa.am.helpers.AMChurnResponse;
import com.airtel.africa.am.helpers.AMResponse;
import com.airtel.africa.am.helpers.AMUserDTO;
import com.airtel.africa.am.helpers.AirtelMoneyConstants;
import com.airtel.africa.am.services.AirtelMoneyService;
import com.airtel.africa.utils.HttpUtils;
import com.airtel.comviva.api.MobServiceAPIService;
import com.airtel.comviva.api.MobServiceAPIServiceLocator;
import com.airtel.comviva.api.MobServiceAPIServiceSoapBindingStub;
import com.airtel.comviva.services.ModificationRequest;
import com.airtel.comviva.services.ModificationResponse;
import com.airtel.comviva.services.RegistrationRequest;
import com.airtel.comviva.services.RegistrationResponse;



@org.springframework.stereotype.Service
public class AirtelMoneyServiceImpl implements AirtelMoneyService, AirtelMoneyConstants 
{

	private static Log log = LogFactory.getLog(AirtelMoneyServiceImpl.class);
	private static boolean isInfoEnabled = log.isInfoEnabled();
	private static boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private Environment environment;
	private String url;

	@SuppressWarnings("deprecation")
	private MobServiceAPIServiceSoapBindingStub getMobServiceAPISoapBindingStub() throws AitelMoneyException {
		MobServiceAPIServiceSoapBindingStub bindingStub = null;
		log.info("saubhagya : beforeee" + this.environment.getProperty("com.airtel.africa.am.soap.url"));
		log.debug("saubhagya : beforeee" + this.environment.getProperty("com.airtel.africa.am.soap.url"));
		if (bindingStub == null) {
			MobServiceAPIService service;
			MobServiceAPIServiceLocator locator = new MobServiceAPIServiceLocator();
			try {
				service = (MobServiceAPIService) locator.getCall();
			} catch (ServiceException e) {
				log.info("saubhagya : exception" + this.environment.getProperty("com.airtel.africa.am.soap.url"));
				log.debug("saubhagya : exception" + this.environment.getProperty("com.airtel.africa.am.soap.url"));
				throw new AitelMoneyException(AitelMoneyExceptionCodes.ENDPOINT_NOT_CONNECTED);
			}
			log.info("saubhagya : " + this.environment.getProperty("com.airtel.africa.am.soap.url"));
			log.debug("saubhagya : " + this.environment.getProperty("com.airtel.africa.am.soap.url"));
			this.url = this.environment.getProperty("com.airtel.africa.am.soap.url");
			URL endpointURL = null;
			try {
				endpointURL = new URL(url);
				log.info("saubhagya : endpointURL " + endpointURL);
				log.debug("saubhagya : endpointURL " + endpointURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				log.info("saubhagya : MalformedURLException " + e);
				log.debug("saubhagya : MalformedURLException" + e);
			}
			try {
				bindingStub = new MobServiceAPIServiceSoapBindingStub(endpointURL, service);
				log.info("saubhagya : bindingStub " + bindingStub);
				log.debug("saubhagya : bindingStub " + bindingStub);
			} catch (AxisFault e) {
				log.info("saubhagya : axis faultexception" + this.environment.getProperty("com.airtel.africa.am.soap.url"));
				log.debug("saubhagya : axis faultexception" + this.environment.getProperty("com.airtel.africa.am.soap.url"));
				throw new AitelMoneyException(AitelMoneyExceptionCodes.ENDPOINT_NOT_CONNECTED);
			}
		}
		return bindingStub;
	}

	@Override
	public AMUserDTO registerUser(AMUserDTO userDTO) throws AitelMoneyException {
		ModificationRequest registrationRequest = new ModificationRequest();
		org.springframework.beans.BeanUtils.copyProperties(userDTO, registrationRequest);
		try {
			ModificationResponse registrationResponse = getMobServiceAPISoapBindingStub()
					.modification(registrationRequest);
			System.out.println(registrationResponse);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return userDTO;
	}

	@Override
	public AMBarUnbarResponse barUser(String msisdn, String transactionId) throws AitelMoneyException {

		AMBarUnbarResponse amResponse = new AMBarUnbarResponse();
		AMBarUnbarRequest amBarUnbarRequest = new AMBarUnbarRequest();
		amBarUnbarRequest.setTRID(transactionId);
		amBarUnbarRequest.setTYPE(MBARUSR);
		amBarUnbarRequest.setUSERTYPE(this.environment.getProperty(AM_XML_USERTYPE));
		amBarUnbarRequest.setBARTYPE(this.environment.getProperty(AM_XML_BARTYPE));
		amBarUnbarRequest.setPROVIDER(this.environment.getProperty(AM_XML_PROVIDER));
		amBarUnbarRequest.setREASON(this.environment.getProperty(AM_XML_REASON));
		amBarUnbarRequest.setMSISDN(msisdn);
		amBarUnbarRequest.setREMARK("AGILEKYC");

		String url = this.environment.getProperty(AM_XML_ENDPOINT);
		if (url == null) {
			throw new AitelMoneyException(AitelMoneyExceptionCodes.INVALID_ENDPOINT);
		}
		// xml to bean
		String xmlReqest = amBarUnbarRequest.toXml();
		if (isDebugEnabled)
			log.debug("Request :  " + xmlReqest);

		Map<String, String> parameterMap = getAMRequestParameters();
		String responseMsg = HttpUtils.fireHttpRequest(url, "POST", "text/xml", xmlReqest, parameterMap);
		if (responseMsg == null) {
			if (isInfoEnabled) {
				log.info("Response is null");
			}
			throw new AitelMoneyException(AitelMoneyExceptionCodes.UNABLE_TO_FETCH_RESPONSE);
		}
		amResponse = (AMBarUnbarResponse) HttpUtils.unmarshal(responseMsg, AMBarUnbarResponse.class);
		return amResponse;
	}

	@Override
	public AMBarUnbarResponse unBarUser(String msisdn, String transactionId) throws AitelMoneyException {
		AMBarUnbarRequest amBarUnbarRequest = new AMBarUnbarRequest();
		amBarUnbarRequest.setTRID(transactionId);
		amBarUnbarRequest.setTYPE(MUBARUSR);
		amBarUnbarRequest.setPROVIDER(environment.getProperty(AM_XML_PROVIDER));
		String body = amBarUnbarRequest.toXml();
		AMBarUnbarResponse response = null;
		try {
			String url = this.environment.getProperty(AM_XML_ENDPOINT);
			if (url == null) {
				throw new AitelMoneyException(AitelMoneyExceptionCodes.INVALID_ENDPOINT);
			}
			Map<String, String> parameterMap = getAMRequestParameters();
			String responseText = HttpUtils.fireHttpRequest(url, "POST", "text/xml", body, parameterMap);
			if (responseText == null) {
				throw new AitelMoneyException(AitelMoneyExceptionCodes.UNABLE_TO_FETCH_RESPONSE);
			}
			response = (AMBarUnbarResponse) HttpUtils.unmarshal(responseText, AMBarUnbarResponse.class);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> getAMRequestParameters() {
		Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("LOGIN", "Ussd_Bearer1");
		parameterMap.put("PASSWORD", "MPtc1ToayCkCMZZeHUu0snA3aUaPbSFQ9UzIkNGbVRU=");
		parameterMap.put("REQUEST_GATEWAY_CODE", "CELLUSSD");
		parameterMap.put("REQUEST_GATEWAY_TYPE", "USSD");
		return parameterMap;
	}

	private Map<String, String> getChurnParameters() {
		Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("LOGIN", "MNP");
		parameterMap.put("PASSWORD", "4bcdsgf565ed941");
		parameterMap.put("REQUEST_GATEWAY_CODE", "CELLUSSD");
		parameterMap.put("REQUEST_GATEWAY_TYPE", "USSD");
		return parameterMap;
	}

	@Override
	public AMBarUnbarResponse blockUser(String msisdn, String transactionId) throws AitelMoneyException {

		AMBarUnbarResponse amResponse = new AMBarUnbarResponse();
		AMBarUnbarRequest amBarUnbarRequest = new AMBarUnbarRequest();
		amBarUnbarRequest.setTRID(transactionId);
		amBarUnbarRequest.setTYPE(BLKSRVREQ);
		amBarUnbarRequest.setSERVICE(RESETPIN);
		amBarUnbarRequest.setMSISDN(msisdn);
		amBarUnbarRequest.setPROVIDER(this.environment.getProperty(AM_XML_PROVIDER));

		// amBarUnbarRequest.setUSERTYPE(this.environment.getProperty(AM_XML_USERTYPE));
		// amBarUnbarRequest.setBARTYPE(this.environment.getProperty(AM_XML_BARTYPE));
		// amBarUnbarRequest.setREASON(this.environment.getProperty(AM_XML_REASON));
		// amBarUnbarRequest.setREMARK("AGILEKYC");

		String url = this.environment.getProperty(AM_XML_ENDPOINT);
		if (url == null) {
			throw new AitelMoneyException(AitelMoneyExceptionCodes.INVALID_ENDPOINT);
		}
		// xml to bean
		String xmlReqest = amBarUnbarRequest.toXml();
		if (isDebugEnabled)
			log.debug("Request :  " + xmlReqest);

		Map<String, String> parameterMap = getAMRequestParameters();
		String responseMsg = HttpUtils.fireHttpRequest(url, "POST", "text/xml", xmlReqest, parameterMap);
		if (responseMsg == null) {
			if (isInfoEnabled) {
				log.info("Response is null");
			}
			throw new AitelMoneyException(AitelMoneyExceptionCodes.UNABLE_TO_FETCH_RESPONSE);
		}
		amResponse = (AMBarUnbarResponse) HttpUtils.unmarshal(responseMsg, AMBarUnbarResponse.class);
		return amResponse;
	}

	@Override
	public AMBarUnbarResponse resetPin(String msisdn, String transactionId) throws AitelMoneyException {

		AMBarUnbarResponse amResponse = new AMBarUnbarResponse();
		AMBarUnbarRequest amBarUnbarRequest = new AMBarUnbarRequest();
		amBarUnbarRequest.setTRID(transactionId);
		amBarUnbarRequest.setTYPE(RESETPIN);
		amBarUnbarRequest.setSERVICE(RESETPIN);
		amBarUnbarRequest.setMSISDN(msisdn);
		amBarUnbarRequest.setPROVIDER(this.environment.getProperty(AM_XML_PROVIDER));

		// amBarUnbarRequest.setUSERTYPE(this.environment.getProperty(AM_XML_USERTYPE));
		// amBarUnbarRequest.setBARTYPE(this.environment.getProperty(AM_XML_BARTYPE));
		// amBarUnbarRequest.setREASON(this.environment.getProperty(AM_XML_REASON));
		// amBarUnbarRequest.setREMARK("AGILEKYC");

		String url = this.environment.getProperty(AM_XML_ENDPOINT);
		if (url == null) {
			throw new AitelMoneyException(AitelMoneyExceptionCodes.INVALID_ENDPOINT);
		}
		// xml to bean
		String xmlReqest = amBarUnbarRequest.toXml();
		if (isDebugEnabled)
			log.debug("Request :  " + xmlReqest);

		Map<String, String> parameterMap = getAMRequestParameters();
		String responseMsg = HttpUtils.fireHttpRequest(url, "POST", "text/xml", xmlReqest, parameterMap);
		if (responseMsg == null) {
			if (isInfoEnabled) {
				log.info("Response is null");
			}
			throw new AitelMoneyException(AitelMoneyExceptionCodes.UNABLE_TO_FETCH_RESPONSE);
		}
		amResponse = (AMBarUnbarResponse) HttpUtils.unmarshal(responseMsg, AMBarUnbarResponse.class);
		return amResponse;
	}

	@Override
	public AMBarUnbarResponse userQuery(String msisdn, String transactionId) throws AitelMoneyException {

		AMBarUnbarResponse amResponse = new AMBarUnbarResponse();
		AMBarUnbarRequest amBarUnbarRequest = new AMBarUnbarRequest();
		amBarUnbarRequest.setTRID(transactionId);
		amBarUnbarRequest.setTYPE(USRENQ);
		amBarUnbarRequest.setMSISDN(msisdn);
		// amBarUnbarRequest.setPROVIDER(this.environment.getProperty(AM_XML_PROVIDER));
		// amBarUnbarRequest.setUSERTYPE(this.environment.getProperty(AM_XML_USERTYPE));
		// amBarUnbarRequest.setBARTYPE(this.environment.getProperty(AM_XML_BARTYPE));
		// amBarUnbarRequest.setREASON(this.environment.getProperty(AM_XML_REASON));
		// amBarUnbarRequest.setREMARK("AGILEKYC");
		String url = AM_XML_ENDPOINT;
		if (url == null) {
			throw new AitelMoneyException(AitelMoneyExceptionCodes.INVALID_ENDPOINT);
		}
		// xml to bean
		String xmlReqest = amBarUnbarRequest.toXml();
		if (isDebugEnabled)
			log.debug("Request :  " + xmlReqest);

		Map<String, String> parameterMap = getAMRequestParameters();
		String responseMsg = HttpUtils.fireHttpRequest(url, "POST", "text/xml", xmlReqest, parameterMap);
		if (responseMsg == null) {
			if (isInfoEnabled) {
				log.info("Response is null");
			}
			throw new AitelMoneyException(AitelMoneyExceptionCodes.UNABLE_TO_FETCH_RESPONSE);
		}
		amResponse = (AMBarUnbarResponse) HttpUtils.unmarshal(responseMsg, AMBarUnbarResponse.class);
		return amResponse;
	}

	@Override
	public AMChurnResponse charn(String msisdn, String transactionId) throws AitelMoneyException {

		AMChurnResponse amResponse = new AMChurnResponse();
		AMChurnRequest amBarUnbarRequest = new AMChurnRequest();
		amBarUnbarRequest.setTYPE(TYPE_WTCWTREQ);
		amBarUnbarRequest.setINTERFACEID(INTERFACEID_CHURN);
		amBarUnbarRequest.setMSISDN(msisdn);
		amBarUnbarRequest.setEXTTRID(transactionId);
		String url = this.environment.getProperty(AM_XML_ENDPOINT_CHURN);
		if (url == null) {
			throw new AitelMoneyException(AitelMoneyExceptionCodes.INVALID_ENDPOINT);
		}
		// xml to bean
		String xmlReqest = amBarUnbarRequest.toXml();
		if (isDebugEnabled)
			log.debug("Request :  " + xmlReqest);

		Map<String, String> parameterMap = getChurnParameters();
		String responseMsg = HttpUtils.fireHttpRequest(url, "POST", "text/xml", xmlReqest, parameterMap);
		if (responseMsg == null) {
			if (isInfoEnabled) {
				log.info("Response is null");
			}
			throw new AitelMoneyException(AitelMoneyExceptionCodes.UNABLE_TO_FETCH_RESPONSE);
		}
		amResponse = (AMChurnResponse) HttpUtils.unmarshal(responseMsg, AMChurnResponse.class);
		return amResponse;
	}

	public static void main(String[] d) {

	}

	@Override
	public AMResponse registerAM(AMUserDTO userDTO) {
		AMResponse amResponse = new AMResponse();
		amResponse.setResult(false);
		RegistrationRequest registrationRequest = new RegistrationRequest();
		org.springframework.beans.BeanUtils.copyProperties(userDTO, registrationRequest);
		try {

			String username = this.environment.getProperty(API_USERNAME);
			String password = this.environment.getProperty(API_PASSWORD);
			registrationRequest.setAPIUSERNAME(username);
			registrationRequest.setAPIPASSWORD(password);
			log.info("saubhagya : before"+registrationRequest);
			log.debug("saubhagya : before"+registrationRequest);
			RegistrationResponse response = getMobServiceAPISoapBindingStub().registration(registrationRequest);
			log.info("saubhagya : after demo call "+response);
			log.info("saubhagya : req"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
			log.info("saubhagya : response"+response.get_call().getMessageContext().getResponseMessage().getSOAPPartAsString());
			amResponse.setResponseObject(response);
			amResponse.setERRCODE(response.getERRCODE());
			if (response.get_call() != null && response.get_call().getMessageContext() != null) {
				if (response.get_call().getMessageContext().getRequestMessage() != null)
					amResponse.setRequest(
							response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
				if (response.get_call().getMessageContext().getResponseMessage() != null)
					amResponse.setResponse(
							response.get_call().getMessageContext().getResponseMessage().getSOAPPartAsString());
			}
			if ("200".equalsIgnoreCase(response.getERRCODE())) {
				amResponse.setResult(true);
				
				log.info("saubhagya : if"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
				log.debug("saubhagya : if"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
								
			} else if ("00050".equalsIgnoreCase(response.getERRCODE())) {
				ModificationRequest modificationRequest = new ModificationRequest();
				String [] ignoreProperties={"TYPE","REGTYPEID"};
				BeanUtils.copyProperties(registrationRequest, modificationRequest, ignoreProperties);
				//BeanUtils.copyProperties(registrationRequest, modificationRequest);
				modificationRequest.setAPIUSERNAME(username);
				modificationRequest.setAPIPASSWORD(password);
				modificationRequest.setTYPE("MREGIST");
				modificationRequest.setREGTYPEID("NO_KYC");
				
				ModificationResponse modificationReturn = getMobServiceAPISoapBindingStub()
						.modification(modificationRequest);
				
				amResponse.setResponseObject(modificationReturn);	
				amResponse.setERRCODE(modificationReturn.getERRCODE());
				
				if (modificationReturn.get_call() != null && modificationReturn.get_call().getMessageContext() != null) {
					if (modificationReturn.get_call().getMessageContext().getRequestMessage() != null)
						amResponse.setRequest(
								modificationReturn.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
					if (modificationReturn.get_call().getMessageContext().getResponseMessage() != null)
						amResponse.setResponse(
								modificationReturn.get_call().getMessageContext().getResponseMessage().getSOAPPartAsString());
				}
				if (modificationReturn != null && "200".equalsIgnoreCase(modificationReturn.getERRCODE())) {
					amResponse.setResult(true);
				}
				
				log.info("saubhagya : else if"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
				log.debug("saubhagya : else if"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
			} 
			
		} catch (RemoteException | AitelMoneyException e) {
			
			log.info("saubhagya : exception"+e);
			log.debug("saubhagya : exception"+e);
			e.printStackTrace();
			amResponse.setExceptionOccured(true);
			amResponse.setException(e);
		}catch (Exception e) {
			
			log.info("saubhagya : exception"+e);
			log.debug("saubhagya : exception"+e);
			e.printStackTrace();
			amResponse.setExceptionOccured(true);
			amResponse.setException(e);
		}
		return amResponse;
	}
}
