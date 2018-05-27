package com.airtel.kyc.task.threads;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.airtel.kyc.business.service.TaskManagementService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.enumData.TaskEnum;
import com.airtel.kyc.repository.entity.task.AgentLog;


public class AMActivateAgent implements Runnable, DisposableBean {

	private static Logger logger = Logger.getLogger(AMActivateAgent.class);

	@Autowired
	public TaskManagementService appservice;

	public AppConstants appconstants;

	@Autowired
	public void setAppconstants(AppConstants appconstants) {
		this.appconstants = appconstants;
	}

	public AMActivateAgent() {
	}

	public AMActivateAgent(TaskManagementService appservice, AppConstants appconstants) {
		this.appservice = appservice;
		this.appconstants = appconstants;
	}

	public AMActivateAgent(TaskManagementService appservice, AppConstants appconstants, int repeatCount) {
		this.appservice = appservice;
		this.appconstants = appconstants;
		this.repeatCount = repeatCount;
	}

	private int repeatCount = 1;

	private String ThreadName = null;

	@Override
	public void run() {
		ThreadName = Thread.currentThread().getName() + ":" + Thread.currentThread().getId();
		//System.out.println(ThreadName + " Started");
		logger.debug(ThreadName + " Started");
		while (!Thread.currentThread().isInterrupted()) {
			AgentLog agentlog = new AgentLog();
			agentlog.setAgent_id(TaskEnum.AM_ACTIVATION.getTaskCode());
			try {
				if ("true".equals(appconstants.am_activate_enabled)) {
					agentlog.setRun_status("Started");
					agentlog = appservice.saveupdateAgentRun(agentlog);
					agentlog = appservice.processRequests(agentlog);
					agentlog = appservice.saveupdateAgentRun(agentlog);
				}
				if (repeatCount == 0)
					break;
				Thread.sleep(appconstants.APPLICATION_THREAD_AM_UPDATION_SLEEP);
			} catch (InterruptedException consumed) {
				logger.debug(ThreadName + " Thread Interrupted");
				break;
			} catch (Exception e) {
				logger.error(ThreadName + " Ended with Error! Exception " + e.getMessage());
			}
		}
		logger.debug(ThreadName + " Thread");
	}

	@Override
	public void destroy() throws Exception {
		logger.debug(ThreadName + " Thread Destoyed");
		appservice = null;
		appconstants = null;
	}
}
