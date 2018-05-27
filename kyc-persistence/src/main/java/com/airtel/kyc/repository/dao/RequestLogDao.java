package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.task.RequestLog;

public interface RequestLogDao extends GenericDao<RequestLog, Integer> {
	
	RequestLog getReqObj(Long reqId);

}
