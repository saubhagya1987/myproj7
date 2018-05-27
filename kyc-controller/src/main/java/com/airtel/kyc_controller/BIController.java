package com.airtel.kyc_controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.airtel.kyc.business.SubscriberManagement;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.utils.DateUtils;

@Controller
//@EnableScheduling
public class BIController {
	
	@Autowired
	private SubscriberManagement subscriberManagement;
	
	@Autowired
	private AppConstants appConstants;
	
	private static Logger logger = Logger.getLogger(BIController.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();
	
	@Scheduled(cron = "${bi.runme.task}")
	public void autoAssignCase() {
		if("true".equals(appConstants.cronBIFlag)){
			logger.debug("BIDUMP");
			logger.info("BIDUMP");
			Date date=new Date();
			String currentDate=DateUtils.getFormatDate(date, "yyyyMMdd");
			int time=date.getHours();
			String hour=String.valueOf(time);
			String finalTime=currentDate+"_"+hour;
			subscriberManagement.getApprovedSubscriber(finalTime);
		}
		
	}
	
	
}