package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.SubscriberIdDetailsDao;
import com.airtel.kyc.repository.entity.SubscriberIdDetails;
@Repository
public class SubscriberIdDetailsDaoImpl extends GenericDaoImpl<SubscriberIdDetails, Integer> implements SubscriberIdDetailsDao{

	@Override
	public List<SubscriberIdDetails> getSubscriberIdDetails(Integer subscriberDetailsId, Integer isOldIdDetails) 
	{
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberDetailsId", subscriberDetailsId);
		map.put("isOldIdDetails", isOldIdDetails);			
		return findResultsByNamedQuery("SubscriberIdDetails.findBySubscriberDetailsIdAndIsOldIdDetails", map);
	}

	@Override
	public SubscriberIdDetails getSubscriberIdDetailsObj(Integer susbscriberDetailsId, Integer isOldIdDetails) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberDetailsId", susbscriberDetailsId);
		map.put("isOldIdDetails", isOldIdDetails);			
		return findSingleResultByNamedQuery("SubscriberIdDetails.findBySubscriberDetailsIdAndIsOldIdDetails", map);
	}

	@Override
	public List<SubscriberIdDetails> getSubscriberIdDetails(String idNumber) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("idNumber", idNumber);
		map.put("isOldIdDetails", 0);
		return findResultsByNamedQuery("SubscriberIdDetails.findByIdNumber", map);
	}
}
