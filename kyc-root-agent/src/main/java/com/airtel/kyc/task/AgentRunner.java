package com.airtel.kyc.task;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airtel.kyc.business.service.TaskManagementService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.task.threads.AMActivateAgent;
import com.airtel.kyc.task.threads.EMABarAgent;
import com.airtel.kyc.task.threads.EMAUnbarAgent;
import com.airtel.kyc.task.threads.EMAUnbarForBulkAgent;
import com.airtel.kyc.task.threads.SVStatusAgent;
import com.airtel.kyc.task.threads.SVUpdateAgent;
import com.airtel.kyc.task.threads.SVValidateAgent;

@Component
public class AgentRunner implements KycConstants {
	private static Logger logger = Logger.getLogger(AgentRunner.class);

	@Autowired
	TaskManagementService appservice;

	public com.airtel.kyc.constants.AppConstants appconstants;

	@Autowired
	public Map<Integer, Object> tmap;

	public AppConstants getAppconstants() {
		return appconstants;
	}

	@Autowired
	public void setAppconstants(AppConstants appconstants) {
		this.appconstants = appconstants;
	}

	private Map<Integer, Thread> THREAD_POOL;

	@PostConstruct
	public void init() {
		logger.info("-----------Thread started------------" + tmap.keySet());
		if (tmap != null) {
			THREAD_POOL = new HashMap<Integer, Thread>();
			for (Map.Entry<Integer, Object> entry : tmap.entrySet()) {
				int key = entry.getKey();
				switch (key) {
				/*case 0:
					if (appconstants.sv_imsi_fetch_enabled.equals("true")) {
						SVIMSIFetchAgent agent0 = (SVIMSIFetchAgent) entry.getValue();
						Thread agent0T = new Thread(agent0);
						agent0T.setName(THREAD_NAME_SV_IMSI_FETCH);
						agent0T.start();
						THREAD_POOL.put(key, agent0T);
					}
					break;*/
				case 0:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_SV_VALIDATION : isEnabled : " + appconstants.sv_validation_enabled);
					if (appconstants.sv_validation_enabled.equals("true")) {
						SVValidateAgent agent0 = (SVValidateAgent) entry.getValue();
						Thread agent0T = new Thread(agent0);
						agent0T.setName(THREAD_NAME_SV_VALIDATION);
						agent0T.start();
						THREAD_POOL.put(key, agent0T);
					}
					break;
					
				case 1:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_EMA_UNBAR : isEnabled : " + appconstants.ema_unbar_enabled);
					if (appconstants.ema_unbar_enabled.equals("true")) {
						EMAUnbarAgent agent1 = (EMAUnbarAgent) entry.getValue();
						Thread agent1T = new Thread(agent1);
						agent1T.setName(THREAD_NAME_EMA_UNBAR);
						agent1T.start();
						THREAD_POOL.put(key, agent1T);
					}
					break;

				case 2:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_SV_UPDATION : isEnabled : " + appconstants.sv_updation_enabled);
					if (appconstants.sv_updation_enabled.equals("true")) {
						SVUpdateAgent agent2 = (SVUpdateAgent) entry.getValue();
						Thread agent2T = new Thread(agent2);
						agent2T.setName(THREAD_NAME_SV_DEMPGRAPHIC_UPDATION);
						agent2T.start();
						THREAD_POOL.put(key, agent2T);
					}
					break;

				case 3:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_AM_UPDATION : isEnabled : " + appconstants.am_activate_enabled);
					if (appconstants.am_activate_enabled.equals("true")) {
						AMActivateAgent agent3 = (AMActivateAgent) entry.getValue();
						Thread agent3T = new Thread(agent3);
						agent3T.setName(THREAD_NAME_AM_UPDATION);
						agent3T.start();
						THREAD_POOL.put(key, agent3T);						
						
					}
					break;

				/*case 4:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_USER_PROVISION : isEnabled : " + appconstants.provision_user_enabled);
					if (appconstants.provision_user_enabled.equals("true")) {
						ProvisionUserAgent agent4 = (ProvisionUserAgent) entry.getValue();
						Thread agent4T = new Thread(agent4);
						agent4T.setName(THREAD_NAME_USER_PROVISION);
						agent4T.start();
						THREAD_POOL.put(key, agent4T);
					}
					break;*/

				case 4:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_EMA_BAR : isEnabled : " + appconstants.ema_bar_enabled);
					if (appconstants.ema_bar_enabled.equals("true")) {
						EMABarAgent agent4 = (EMABarAgent) entry.getValue();
						Thread agent4T = new Thread(agent4);
						agent4T.setName(THREAD_NAME_EMA_BAR);
						agent4T.start();
						THREAD_POOL.put(key, agent4T);
					}
					break;
				case 5:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_SV_STATUS_UPDATION : isEnabled : " + appconstants.sv_status_activate_enabled);
					if (appconstants.sv_status_activate_enabled.equals("true")) {
						SVStatusAgent agent5 = (SVStatusAgent) entry.getValue();
						Thread agent5T = new Thread(agent5);
						agent5T.setName(THREAD_NAME_SV_STATUS_UPDATION);
						agent5T.start();
						THREAD_POOL.put(key, agent5T);
					}
					break;	
					
				case 6:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_EMA_UNBAR_BULK : isEnabled : " + appconstants.ema_unbar_bulk_enabled);
					if (appconstants.ema_unbar_bulk_enabled.equals("true")) {
						EMAUnbarForBulkAgent agent6 = (EMAUnbarForBulkAgent) entry.getValue();
						Thread agent6T = new Thread(agent6);
						agent6T.setName(THREAD_NAME_EMA_UNBAR_BULK);
						agent6T.start();
						THREAD_POOL.put(key, agent6T);
					}
					break;	
				/*case 1:
					if (appconstants.hp_unbar_enabled.equals("true")) {
						HPUnbarAgent agent1 = (HPUnbarAgent) entry.getValue();
						Thread agent1T = new Thread(agent1);
						agent1T.setName(THREAD_NAME_HP_UNBAR);
						agent1T.start();
						THREAD_POOL.put(key, agent1T);
					}
					break;

				case 2:
					if (appconstants.sv_updation_enabled.equals("true")) {
						SVUpdateAgent agent2 = (SVUpdateAgent) entry.getValue();
						Thread agent2T = new Thread(agent2);
						agent2T.setName(THREAD_NAME_SV_DEMPGRAPHIC_UPDATION);
						agent2T.start();
						THREAD_POOL.put(key, agent2T);
					}
					break;

				case 3:
					if (appconstants.am_activate_enabled.equals("true")) {
						AMActivateAgent agent3 = (AMActivateAgent) entry.getValue();
						Thread agent3T = new Thread(agent3);
						agent3T.setName(THREAD_NAME_AM_UPDATION);
						agent3T.start();
						THREAD_POOL.put(key, agent3T);
					}
					break;
				case 4:
					if (appconstants.hp_bar_enabled.equals("true")) {
						HPBarAgent agent4 = (HPBarAgent) entry.getValue();
						Thread agent4T = new Thread(agent4);
						agent4T.setName(THREAD_NAME_HP_BAR);
						agent4T.start();
						THREAD_POOL.put(key, agent4T);
					}
					break;
				case 5:
					if(logger.isDebugEnabled())
						logger.debug("THREAD_NAME_SV_STATUS_UPDATION : isEnabled : " + appconstants.sv_status_activate_enabled);
					if (appconstants.sv_status_activate_enabled.equals("true")) {
						SVStatusAgent agent5 = (SVStatusAgent) entry.getValue();
						Thread agent5T = new Thread(agent5);
						agent5T.setName(THREAD_NAME_SV_STATUS_UPDATION);
						agent5T.start();
						THREAD_POOL.put(key, agent5T);
					}
					break;	*/

				default:
					break;
				}
			}
		}
	}

	public void killThreadByid(int tid) {
		if (THREAD_POOL != null) {
			switch (tid) {
			case 0:
				Thread agent0T = THREAD_POOL.get(tid);
				if (agent0T.isAlive())
					agent0T.interrupt();
				break;
			case 1:
				Thread agent1T = THREAD_POOL.get(tid);
				if (agent1T.isAlive())
					agent1T.interrupt();
				break;

			case 2:
				Thread agent2T = THREAD_POOL.get(tid);
				if (agent2T.isAlive())
					agent2T.interrupt();
				break;

			case 3:
				Thread agent3T = THREAD_POOL.get(tid);
				if (agent3T.isAlive())
					agent3T.interrupt();
				break;

			case 4:
				Thread agent4T = THREAD_POOL.get(tid);
				if (agent4T.isAlive())
					agent4T.interrupt();
				break;

			case 5:
				Thread agent5T = THREAD_POOL.get(tid);
				if (agent5T.isAlive())
					agent5T.interrupt();
				break;
				
			case 6:
				Thread agent6T = THREAD_POOL.get(tid);
				if (agent6T.isAlive())
					agent6T.interrupt();
				break;

			default:
				break;
			}
		}
	}

	public boolean startAgent(int agentid, int reapeatcount) {
		boolean result = false;

		switch (agentid) {
		case 0:
			if (appconstants.sv_validation_enabled.equals("true")) {
				SVValidateAgent agent0 = new SVValidateAgent(appservice, appconstants, reapeatcount);
				Thread agent0T = new Thread(agent0);
				agent0T.start();
				if (THREAD_POOL == null) {
					THREAD_POOL = new HashMap<Integer, Thread>();
				}
				THREAD_POOL.put(0, agent0T);
				result = true;
			}
			break;
		/*case 0:
			if (appconstants.sv_imsi_fetch_enabled.equals("true")) {
				SVIMSIFetchAgent agent0 = new SVIMSIFetchAgent(appservice, appconstants, reapeatcount);
				Thread agent0T = new Thread(agent0);
				agent0T.start();
				if (THREAD_POOL == null) {
					THREAD_POOL = new HashMap<Integer, Thread>();
				}
				THREAD_POOL.put(0, agent0T);
				result = true;
			}
			break;*/

		default:
			break;
		}

		return result;
	}

	public Map<String, Boolean> fetchAgentStatus() {
		Map<String, Boolean> statusmap = new HashMap<String, Boolean>();
		if (THREAD_POOL != null) {
			for (Map.Entry<Integer, Thread> entry : THREAD_POOL.entrySet()) {
				Thread agentxT = entry.getValue();
				if (agentxT.isAlive())
					statusmap.put(agentxT.getName(), true);
				else
					statusmap.put(agentxT.getName(), false);
			}
		}
		return statusmap;
	}
}