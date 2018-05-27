package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.repository.entity.Subscriber;

public interface SubscriberDao extends GenericDao<Subscriber, Integer>{

	Subscriber getSubscriberData(String msisdn, String simSerialNo, Integer isOldData);
	
	Subscriber getApprovedSubscriberData(String msisdn, Integer isOldData,String finalStatus);

	Subscriber getSubscriberObj(String msisdn);

	Subscriber getSubscriberBySubscriberId(Long subscriberId);

	Subscriber getSubscriberObject(String msisdn, Integer isOldData);

	List<SubscriberDto> getApprovedSubscriber();

	

}
