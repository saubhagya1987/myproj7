/**
 * 
 */
package com.airtel.kyc.business.service;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.repository.entity.task.AgentLog;
import com.airtel.kyc.repository.entity.task.RequestLog;

/**
 * @author B0079855
 *
 */
public interface TaskManagementService {

	public Long addTask(int taskId,Long number_id,String kyc_id)throws BusinessException;
	
	public RequestLog saveupdateRequest(RequestLog request) throws BusinessException;

	public AgentLog saveupdateAgentRun(AgentLog log) throws BusinessException;

	public AgentLog processRequests(AgentLog agent) throws BusinessException;

	public String sendSMS(String msisdn,String sms_text);
}
