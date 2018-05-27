package com.airtel.africa.airtel.money.service;

import com.airtel.africa.airtel.money.exceptions.AitelMoneyException;
import com.airtel.africa.airtel.money.helpers.AMBarUnbarRequest;
import com.airtel.africa.airtel.money.helpers.AMResponse;

/**
 * @author Hem Chand
 *
 */
public interface AirtelMoneyService {

	public AMResponse registerOnAM(AMBarUnbarRequest aMBarUnbarRequest) throws AitelMoneyException;
}
