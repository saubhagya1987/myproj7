package com.airtel.africa.tt.controller._cron;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.airtel.africa.tt.service.TimingTaskService;
import com.airtel.kyc.constants.AppConstants;

@Controller
//@EnableScheduling
public class CronController {
	
	@Autowired
	TimingTaskService timingTaskService;
	
	@Autowired
	private AppConstants appConstants;
	/**
	 * void
	 */
	@Scheduled(cron = "${kyc.auto.assign.task.timer}")
	public void autoAssignCase() {
		//System.out.println("autoAssignCase starting...");
		if("true".equals(appConstants.cronAutoassignFlag)){
			timingTaskService.autoAssign();
		}
		//System.out.println("time----"+new Date());
	}
	
	@Scheduled(cron = "${kyc.blacklist.task.timer}")
	public void blacklistSubscriber() {//admin will upload file
		//System.out.println("blacklistSubscriber starting...");
		if("true".equals(appConstants.cronBlacklistFlag)){
			timingTaskService.blacklistSubscriber();
		}
		//System.out.println("time----"+new Date());
	}
	
	@Scheduled(cron = "${kyc.auto.bar.task.timer}")
	public void barSubscriber() throws IOException {//autobar if hotline status for three days after registration 
		//System.out.println("barSubscriber starting...");
		if("true".equals(appConstants.cronBarFlag)){
			timingTaskService.barSubscriber();
		}
		//System.out.println("time----"+new Date());
	}

	@Scheduled(cron = "${kyc.auto.rejection.task.timer}")
	public void autoReject() {//auto reject if status of customer is not approved after 4 days of registration 
		//System.out.println("autoReject starting...");
		if("true".equals(appConstants.cronRejectFlag)){
			timingTaskService.autoReject();
		}
		//System.out.println("time----"+new Date());
	}
	
}