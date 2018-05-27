package com.airtel.africa.am.services;

import com.airtel.africa.am.exceptions.AitelMoneyException;
import com.airtel.africa.am.helpers.AMBarUnbarResponse;
import com.airtel.africa.am.helpers.AMChurnResponse;
import com.airtel.africa.am.helpers.AMResponse;
import com.airtel.africa.am.helpers.AMUserDTO;


public interface AirtelMoneyService {

	public AMUserDTO registerUser(AMUserDTO userDTO) throws AitelMoneyException;

	public AMResponse registerAM(AMUserDTO userDTO);

	
	public AMBarUnbarResponse barUser(String msisdn, String transactionId) throws AitelMoneyException;

	public AMBarUnbarResponse unBarUser(String msisdn, String transactionId) throws AitelMoneyException;

	// charn
	public AMChurnResponse charn(String msisdn, String transactionId) throws AitelMoneyException;

	// block
	public AMBarUnbarResponse blockUser(String msisdn, String transactionId) throws AitelMoneyException;

	// reset
	public AMBarUnbarResponse resetPin(String msisdn, String transactionId) throws AitelMoneyException;
	
	// reset
	public AMBarUnbarResponse userQuery(String msisdn, String transactionId) throws AitelMoneyException;
}
