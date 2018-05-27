package com.airtel.africa.tt.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtel.africa.tt.service.TimingTaskService;
import com.airtel.kyc.constants.AppConstants;

public class AutoAssigningCM  implements Runnable{
	private static Logger logger = Logger.getLogger(AutoAssigningCM.class);

	public AppConstants appconstants;

	@Autowired
	public void setAppconstants(AppConstants appconstants) {
		this.appconstants = appconstants;
	}

	@Autowired
	TimingTaskService timingTaskService;
	
	private String ThreadName = null;


	@Override
	public void run() {
		ThreadName = Thread.currentThread().getName() + ":" + Thread.currentThread().getId();
		logger.debug(ThreadName + " Started");
		while (!Thread.currentThread().isInterrupted()) {
			try {
				timingTaskService.autoAssign();
				
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				logger.debug(ThreadName + " Thread Interrupted");
				break;
			} catch (Exception e) {
				logger.error(ThreadName + " Ended with Error! Exception " + e.getMessage());
			}
		}
		logger.debug(ThreadName + " Thread");
	}
}
