package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.SubscriberWorkFlowDao;
import com.airtel.kyc.repository.entity.SubscriberWorkFlow;
@Repository
public class SubscriberWorkFlowDaoImpl extends GenericDaoImpl<SubscriberWorkFlow, Integer> implements SubscriberWorkFlowDao{

	@Override
	public SubscriberWorkFlow getSubscriberWorkFlowObj(Integer subscriberWorkFlowId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberWorkFlowId", subscriberWorkFlowId);					
		return findSingleResultByNamedQuery("SubscriberWorkFlow.findByWorkFlowId", map);
		
	}

	@Override
	public SubscriberWorkFlow getSubscriberWorkFlowData(Integer subscriberDetailsId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberDetailsId", subscriberDetailsId);					
		return findSingleResultByNamedQuery("SubscriberWorkFlow.findBySubscriberworkflowId", map);
	}

}
