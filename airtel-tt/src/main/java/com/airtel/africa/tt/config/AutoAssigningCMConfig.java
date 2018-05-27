/*package com.airtel.africa.tt.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airtel.africa.tt.tasks.AutoAssigningCM;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;

@Component
public class AutoAssigningCMConfig implements KycConstants {
	private static Logger logger = Logger.getLogger(AutoAssigningCMConfig.class);

	public com.airtel.kyc.constants.AppConstants appconstants;

	public AppConstants getAppconstants() {
		return appconstants;
	}

	@Autowired
	public void setAppconstants(AppConstants appconstants) {
		this.appconstants = appconstants;
	}

	@PostConstruct
	public void init() {
		logger.info("----AutoAssigningCM-------Thread started------------");

		AutoAssigningCM agentAutoAssign = new AutoAssigningCM();
		Thread agentAutoAssignt = new Thread((Runnable) agentAutoAssign);
		agentAutoAssignt.setName("AUTO_ASSIGN_CM");
		agentAutoAssignt.start();
	}
}
*/