package com.airtel.africa.singleview.service;

import com.airtel.africa.singleview.exceptions.SingleviewException;
import com.airtel.africa.singleview.helpers.SVResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarRequest;

/**
 * @author Hem Chand
 *
 */
public interface SingleviewService {

	
	
	public boolean barMSISDN(String msisdn) throws SingleviewException;

	public String getSimNumber(String msisdn) throws SingleviewException;

	public String getMSISDNStatus(String msisdn) throws SingleviewException;

	public boolean isSimValid(String msisdn, String simNumber) throws SingleviewException;

	public SVResponse isSimserialMSISDNCorrect(String msisdn, String simNumber) throws SingleviewException;
	
	public SVResponse updateDemographicsDetails(UpdateCustomerDemographicsReq updateCustomerDemographicsReq);
	
	public SVResponse reconnectBarringRequest(ReconnectBarringRequest barringRequest);
	
	public boolean isMSISDNActive(String msisdn) throws SingleviewException;

	public void updateMSISDNDetails(UpdateCustomerDemographicsReq updateCustomerDemographicsReq)
			throws SingleviewException;

}
