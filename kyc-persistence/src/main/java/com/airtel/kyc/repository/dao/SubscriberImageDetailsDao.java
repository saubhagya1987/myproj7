package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.SubscriberImageDetail;

public interface SubscriberImageDetailsDao extends GenericDao<SubscriberImageDetail, Integer>{

	List<SubscriberImageDetail> getSubscriberImageDetail(Integer subscriberDetailsId, Integer isOldData);

}
