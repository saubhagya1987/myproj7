package com.airtel.africa.sms.service;

import java.util.Map;

import com.airtel.africa.sms.constant.HTTPParam;
import com.airtel.africa.sms.exceptions.SMSException;

/**
 * @author Hem Chand
 * 
 */
public interface SMSService {

	public void sendSMS(HTTPParam httpParam, Map<String, Object> modelMap,
			String requestTemplate) throws SMSException;

}
