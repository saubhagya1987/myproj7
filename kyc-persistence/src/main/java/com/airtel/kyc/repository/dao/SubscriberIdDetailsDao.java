package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.SubscriberIdDetails;

public interface SubscriberIdDetailsDao extends GenericDao<SubscriberIdDetails, Integer>{

	List<SubscriberIdDetails> getSubscriberIdDetails(Integer subscriberDetailsId, Integer isOldData);

	SubscriberIdDetails getSubscriberIdDetailsObj(Integer susbscriberDetailsId, Integer isOldIdDetails);
	
	List<SubscriberIdDetails> getSubscriberIdDetails(String idNumber);

}
