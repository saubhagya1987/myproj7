package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.RequestLogDao;
import com.airtel.kyc.repository.entity.task.RequestLog;

@Repository
public class RequestLogDaoImpl extends GenericDaoImpl<RequestLog, Integer> implements RequestLogDao{

	@Override
	public RequestLog getReqObj(Long request_id) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("request_id", request_id);
		return findSingleResultByNamedQuery("RequestLog.findByReqId", map);
	}

}
