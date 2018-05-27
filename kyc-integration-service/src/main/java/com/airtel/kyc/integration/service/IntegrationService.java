package com.airtel.kyc.integration.service;

import java.io.IOException;

import com.airtel.africa.airtel.ema.helpers.EMAResponse;
import com.airtel.africa.am.exceptions.AitelMoneyException;
import com.airtel.africa.am.helpers.AMResponse;
//import com.airtel.africa.airtel.money.exceptions.AitelMoneyException;
//import com.airtel.africa.airtel.money.helpers.AMResponse;
import com.airtel.africa.singleview.exceptions.SingleviewException;
import com.airtel.africa.singleview.helpers.SVResponse;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.response.NotificationResponse;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.repository.entity.SubscriberDetails;

public interface IntegrationService {

	public void notify(String msisdn,String message) throws IntegrationServiceException, IOException;

	public NotificationResponse notify(NotificationDto notificationDto) throws IntegrationServiceException, IOException;

	public SVResponse updateSingleView(SubscriberDto subscriberDto) throws SingleviewException;

	public Boolean authenticateUser(String userName, String userPassword);
	
	//public HPResponse unbarMSISDN(String msisdn,String serial) throws HPException;
	
	//public HPResponse barMSISDN(String msisdn,String serial) throws HPException;
	
	//public HPResponse putOnHotLine(String msisdn,String serial) throws HPException;
	
	// update EMA baring
	public boolean barOnEMAALL(String msisdn) throws IntegrationServiceException;

		// update EMA baring
	public boolean barOnEMA(String msisdn) throws IntegrationServiceException;

		// update EMA unbaring
	public boolean unbarOnEMA(String msisdn) throws IntegrationServiceException;

		// update EMA unbaring
	public EMAResponse unbar(String msisdn);
	
	public EMAResponse simValidation(String msisdn);

		// update EMA baring
	public EMAResponse bar(String msisdn);
		
		// update EMA unbaring for bulk
	public EMAResponse unbarforBulk(String msisdn);
	
	//public SVResponse getMSISDNStatus(String msisdn) throws SingleviewException;
	
	//public SVResponse getIMSI(String msisdn) throws SingleviewException;

	public AMResponse updateOnAM(SubscriberDto subscriberDto)throws AitelMoneyException;
	
	public SVResponse activateOnSingleView(String msisdn) throws SingleviewException;

	public SVResponse updateSingleViewSubsDetails(SubscriberDetails subscriberDetail) throws SingleviewException;

}
