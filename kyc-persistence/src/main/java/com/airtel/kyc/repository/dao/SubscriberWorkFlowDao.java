package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.SubscriberWorkFlow;

public interface SubscriberWorkFlowDao extends GenericDao<SubscriberWorkFlow, Integer>{
	
	SubscriberWorkFlow getSubscriberWorkFlowObj(Integer subscriberWorkFlowId);
	
	SubscriberWorkFlow getSubscriberWorkFlowData(Integer subscriberDetailsId);

}
