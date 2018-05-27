package com.airtel.africa.singleview.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.airtel.africa.singleview.exceptions.SingleviewException;
import com.airtel.africa.singleview.exceptions.SingleviewExceptionCodes;
import com.airtel.africa.singleview.helpers.SVConstants;
import com.airtel.africa.singleview.helpers.SVResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.BarSubscriberRequest;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.KYCLocator;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummary;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INLocator;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INSoap11BindingStub;
import com.airtel.africa.singleview.service.SingleviewService;


//@PropertySource({"classpath:sv.properties"})

@org.springframework.stereotype.Service
public class SingleviewServiceImpl implements SingleviewService, SVConstants {

	private static Logger log = Logger.getLogger(SingleviewServiceImpl.class);
	private static boolean isInfoEnabled = log.isInfoEnabled();
	private static boolean isDebugEnabled = log.isDebugEnabled();

	private USSD_INSoap11BindingStub ussdBindingStub;

	@Autowired
	private Environment environment;

	private String url;

	public KYCSoap11BindingStub getBindingStub() throws SingleviewException   {
		try {
			return getKYCSoap11BindingStub();
		} catch (AxisFault | MalformedURLException | ServiceException e) {
			throw new SingleviewException();
		}
	}
	
	@SuppressWarnings("deprecation")
	private KYCSoap11BindingStub getKYCSoap11BindingStub() throws MalformedURLException, AxisFault, ServiceException  {
		URL endpointURL = null;
		KYCSoap11BindingStub bindingStub = null;
		String url = this.environment.getProperty(SV_KYC_ENDPOINT);
		if (url != null) {
			endpointURL = new URL(url);
			KYCLocator kycLocator = new KYCLocator();
			Service service  = (Service) kycLocator.getCall();
			bindingStub = new KYCSoap11BindingStub(endpointURL, service);
		}
		return bindingStub;
	}
	
	public USSD_INSoap11BindingStub getUssdBindingStub() {
		try {
			ussdBindingStub = getUSSDSoap11BindingStub();
		} catch (SingleviewException e) {
			if (isDebugEnabled) {
				log.debug(e.getExceptionCode() + " : " + e.getExceptionMessage());
			}
		}
		return ussdBindingStub;
	}
	
	@SuppressWarnings("deprecation")
	private USSD_INSoap11BindingStub getUSSDSoap11BindingStub() throws SingleviewException {
		URL endpointURL = null;
		String url = this.environment.getProperty(SV_USSD_ENDPOINT);
		if (ussdBindingStub == null) {
			try {
				endpointURL = new URL(url);
			} catch (MalformedURLException e1) {
				if (isDebugEnabled) {
					e1.printStackTrace();
				}
				throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
			}
			USSD_INLocator ussdLocator = new USSD_INLocator();
			Service service = null;
			try {
				service = (Service) ussdLocator.getCall();
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}

			try {
				ussdBindingStub = new USSD_INSoap11BindingStub(endpointURL, service);
			} catch (AxisFault e) {
				e.printStackTrace();
			}
		}
		return ussdBindingStub;
	}




	public boolean isSimValid(String msisdn, String simNumber) throws SingleviewException {
		if (isInfoEnabled) {
			log.debug("Is Sim valid for : " + msisdn);
		}
		if (simNumber == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_SIM_NUMBER);

		}
		String exsitingSimNumber = getSimNumber(msisdn);
		if (simNumber.equalsIgnoreCase(exsitingSimNumber)) {
			if (isInfoEnabled) {
				log.debug("Is Sim valid for : " + msisdn + " : true");
			}
			return true;
		}
		return false;
	}

	public boolean isMSISDNActive(String msisdn) throws SingleviewException {
		if (isInfoEnabled) {
			log.debug("Is Sim valid for : " + msisdn);
		}
		String status = getMSISDNStatus(msisdn);
		if ("Active".equalsIgnoreCase(status)) {
			if (isInfoEnabled) {
				log.debug("Is Sim valid for : " + msisdn + " : true");
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean barMSISDN(String msisdn) throws SingleviewException {
		if (msisdn == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
		}
		BarSubscriberRequest parameters = new BarSubscriberRequest();
		parameters.setPSubno(msisdn);
		try {
			getBindingStub().barSubscriberRequest_Update(parameters);
			return true;
		} catch (RemoteException e) {
			throw new SingleviewException(SingleviewExceptionCodes.ENDPOINT_NOT_CONNECTED);
		}
	}

	@Override
	public String getSimNumber(String msisdn) throws SingleviewException {
		if (msisdn == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
		}
		/*GetSIMNumberRequest parameters = new GetSIMNumberRequest();
		parameters.setSUBSCRIBER_NO(msisdn);
		GetSIMNumberResponse getSIMNumberResponse;
		
		try {
			getSIMNumberResponse = getBindingStub().getSIMNumberRequest_Fetch(parameters);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw new SingleviewException(SingleviewExceptionCodes.ENDPOINT_NOT_CONNECTED);
		}
		if (getSIMNumberResponse.getRESULT() == null || getSIMNumberResponse.getRESULT().getSIM_NO() == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
		}
		return getSIMNumberResponse.getRESULT().getSIM_NO();*/
		
		FetchSubscriberSIMSummary parameters=new FetchSubscriberSIMSummary();
		parameters.setMSISDN(msisdn);
		FetchSubscriberSIMSummaryRes getSIMNumberResponse;
		try {
			getSIMNumberResponse = getUssdBindingStub().fetchSubscriberSIMSummary_Fetch(parameters);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw new SingleviewException(SingleviewExceptionCodes.ENDPOINT_NOT_CONNECTED);
		}
		String simSerial =getSIMNumberResponse.getICCID();
		if (simSerial == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
		}
		
		return simSerial;
	}

	@Override
	public String getMSISDNStatus(String msisdn) throws SingleviewException {
		if (msisdn == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
		}
		GetMSISDNStatusRequest msisdnStatusRequest = new GetMSISDNStatusRequest();
		msisdnStatusRequest.setSUBSCRIBER_NO(msisdn);
		GetMSISDNStatusResponse getMSISDNStatusResponse;
		try {
			getMSISDNStatusResponse = getBindingStub().getMSISDNStatusRequest_Fetch(msisdnStatusRequest);
		} catch (RemoteException e) {
			// e.printStackTrace();
			throw new SingleviewException(SingleviewExceptionCodes.ENDPOINT_NOT_CONNECTED);
		}
		if (getMSISDNStatusResponse.getRESULT() == null || getMSISDNStatusResponse.getRESULT().getSTATUS() == null) {
			throw new SingleviewException(SingleviewExceptionCodes.INVALID_MSISDN);
		}
		return getMSISDNStatusResponse.getRESULT().getSTATUS();

	}

	@Override
	public void updateMSISDNDetails(UpdateCustomerDemographicsReq updateCustomerDemographicsReq)
			throws SingleviewException {
		try {
			getBindingStub().updateCustomerDemographicsReq_Update(updateCustomerDemographicsReq);
		} catch (RemoteException e) {
			throw new SingleviewException(SingleviewExceptionCodes.UPDATE_DEMOGRAPHIC_DETAIL_FAIL);
		}
	}

	@Override
	public SVResponse isSimserialMSISDNCorrect(String msisdn, String simNumber)  {
		SVResponse svresponse = new SVResponse();
		FetchSubscriberSIMSummary parameters=new FetchSubscriberSIMSummary();
		parameters.setMSISDN(msisdn);
		FetchSubscriberSIMSummaryRes getSIMNumberResponse = null;
		try {
			getSIMNumberResponse = getUssdBindingStub().fetchSubscriberSIMSummary_Fetch(parameters);
			svresponse.setResponseObject(getSIMNumberResponse);
			if(getSIMNumberResponse.get_call() != null && getSIMNumberResponse.get_call().getMessageContext() != null) {
				if(getSIMNumberResponse.get_call().getMessageContext().getRequestMessage() != null)
					svresponse.setRequest(getSIMNumberResponse.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
				if(getSIMNumberResponse.get_call().getMessageContext().getResponseMessage() != null)
					svresponse.setResponse(getSIMNumberResponse.get_call().getMessageContext().getResponseMessage().getSOAPPartAsString());
			}
		} catch (RemoteException e) {
			svresponse.setExceptionOccured(true);
			svresponse.setException(e);
		}catch(Exception e){
			svresponse.setExceptionOccured(true);
			svresponse.setException(e);
		}
		
		if(getSIMNumberResponse != null) {
			String simSerial =getSIMNumberResponse.getICCID();
			if(simNumber.equalsIgnoreCase(simSerial)) {
				svresponse.setResult(true);
			} else {
				svresponse.setResult(false);
			}
		}
		else
			svresponse.setResult(false);
		
		return svresponse;
	}
	
	public static void main(String[] strings) {
		FetchSubscriberSIMSummary parameters=new FetchSubscriberSIMSummary();
		parameters.setMSISDN("699837409");
		FetchSubscriberSIMSummaryRes getSIMNumberResponse;
		URL endpointURL = null;
		
		try {
			
			try {
				endpointURL = new URL("http://172.23.36.100:8137/services/USSD_IN.USSD_INHttpSoap11Endpoint");
			} catch (MalformedURLException e1) {
				if (isDebugEnabled) {
					e1.printStackTrace();
				}
			}
			USSD_INLocator ussdLocator = new USSD_INLocator();
			Service service = null;
			try {
				service = (Service) ussdLocator.getCall();
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			getSIMNumberResponse = new USSD_INSoap11BindingStub(endpointURL, service).fetchSubscriberSIMSummary_Fetch(parameters);
			//System.out.println(getSIMNumberResponse.getICCID());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public SVResponse updateDemographicsDetails(UpdateCustomerDemographicsReq updateCustomerDemographicsReq) {
		SVResponse  responseSV = new SVResponse();
		responseSV.setResult(false);
		try {
			UpdateCustomerDemographicsResponse response;
			try {
				
				/////////
				
		       /* SOAPEnvelope env =null;
		        Object[] abc =  new java.lang.Object[] {updateCustomerDemographicsReq};
		        int i=0;
		        for(i=0; abc!=null && i< abc.length;i++){
		        	//if(! (abc[i] instanceof SOAPBodyElement)) break;
		        	if(abc!=null && abc.length>0 && i==abc.length){
		        		env=new org.apache.axis.message.SOAPEnvelope(Constants.DEFAULT_SOAP_VERSION,SchemaVersion2001.SCHEMA_2001);
		        	}
		        	if(abc!=null && abc.length>0){
		        		env=new org.apache.axis.message.SOAPEnvelope(Constants.DEFAULT_SOAP_VERSION,SchemaVersion2001.SCHEMA_2001);
		        	}
		        	
		        	SOAPBodyElement temp= new SOAPBodyElement();
	        		temp.addAttribute("http://services.comviva.com", "abc", "abcd");
	        		env.addBodyElement(temp);
		        	//for(i=0;i<abc.length;i++){
		        		SOAPBodyElement temp1= new SOAPBodyElement();
		        		//temp1.addAttribute(, updateCustomerDemographicsReq.getRETAILER_MOBILE_NO());
		        		//temp1.addChildElement(updateCustomerDemographicsReq.getRETAILER_MOBILE_NO());
		        		temp1.addAttribute("aaaaa", "bbb", updateCustomerDemographicsReq.getRETAILER_MOBILE_NO());
		        		env.addBodyElement(temp1);
		        	//}
		        	
		        	Message msg = new Message(env);
		        	
		        	System.out.println("msg"+msg.getSOAPPartAsString());
		        	*/
		        	
		        //}

				/////////
				updateCustomerDemographicsReq.setRETAILER_MOBILE_NO(this.environment.getProperty("com.airtel.africa.sv.retailer.no"));
				log.info("saubhagya : setRETAILER_MOBILE_NO"+this.environment.getProperty("com.airtel.africa.sv.retailer.no"));
				log.info("saubhagya : setRETAILER_MOBILE_NO"+updateCustomerDemographicsReq);
				log.debug("saubhagya : setRETAILER_MOBILE_NO"+this.environment.getProperty("com.airtel.africa.sv.retailer.no"));
				log.debug("saubhagya : setRETAILER_MOBILE_NO"+updateCustomerDemographicsReq);
				response = getBindingStub().updateCustomerDemographicsReq_Update(updateCustomerDemographicsReq);
				log.info("saubhagya : setRETAILER_MOBILE_NO"+response);
				log.debug("saubhagya : setRETAILER_MOBILE_NO"+response);
				responseSV.setResponseObject(response);
				
				if(response.getRESULT() != null && 0 == response.getRESULT().getRETURN_CODE().intValue() ) {
					responseSV.setResult(true);
				}
				if(response.get_call() != null && response.get_call().getMessageContext() != null) {
					log.info("saubhagya : if"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
					log.debug("saubhagya : if"+response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
					if(response.get_call().getMessageContext().getRequestMessage() != null)
						responseSV.setRequest(response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
					if(response.get_call().getMessageContext().getResponseMessage() != null)
						responseSV.setResponse(response.get_call().getMessageContext().getResponseMessage().getSOAPPartAsString());
				}
			} catch (SingleviewException e) {
				log.info("saubhagya : SingleviewException");
				log.info("saubhagya : SingleviewException");
				responseSV.setExceptionOccured(true);
				responseSV.setException(e);
			}
		} catch (RemoteException e) {
			log.info("saubhagya : RemoteException");
			log.info("saubhagya : RemoteException"+updateCustomerDemographicsReq);
			responseSV.setExceptionOccured(true);
			responseSV.setException(e);
		}
		catch (Exception e) {
			log.info("saubhagya : Exception");
			log.info("saubhagya : Exception");
			responseSV.setExceptionOccured(true);
			responseSV.setException(e);
		}
		return responseSV;
	}

	@Override
	public SVResponse reconnectBarringRequest(ReconnectBarringRequest barringRequest) {
		SVResponse  responseSV = new SVResponse();
		responseSV.setResult(false);
		try {
			ReconnectBarringResponse response =  getBindingStub().reconnectBarringRequest_Update(barringRequest);
			responseSV.setResponseObject(response);
			if(response.getRESULT() != null && (0 == response.getRESULT().getRETURN_CODE() || 103021 == response.getRESULT().getRETURN_CODE()) ) {
				responseSV.setResult(true);
			}
			if(response.get_call() != null && response.get_call().getMessageContext() != null) {
				if(response.get_call().getMessageContext().getRequestMessage() != null)
					responseSV.setRequest(response.get_call().getMessageContext().getRequestMessage().getSOAPPartAsString());
				if(response.get_call().getMessageContext().getResponseMessage() != null)
					responseSV.setResponse(response.get_call().getMessageContext().getResponseMessage().getSOAPPartAsString());
			}
			
		} catch (RemoteException | SingleviewException e) {
			responseSV.setExceptionOccured(true);
			responseSV.setException(e);
			log.debug("Exception occured : "+e.getMessage());
			if(isDebugEnabled)
				e.printStackTrace();
		}	
		return responseSV;
	}

}
