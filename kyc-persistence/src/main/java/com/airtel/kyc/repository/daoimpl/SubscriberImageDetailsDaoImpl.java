package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.SubscriberImageDetailsDao;
import com.airtel.kyc.repository.entity.SubscriberImageDetail;
@Repository
public class SubscriberImageDetailsDaoImpl extends GenericDaoImpl<SubscriberImageDetail, Integer> implements SubscriberImageDetailsDao{

	@Override
	public List<SubscriberImageDetail> getSubscriberImageDetail(Integer subscriberDetailsId, Integer isOldImageDetails) 
		{
			HashMap<String, Object> map = new HashMap<>();		
			map.put("subscriberDetailsId", subscriberDetailsId);
			map.put("isOldImageDetails", isOldImageDetails);			
			return findResultsByNamedQuery("SubscriberImageDetail.findBySubscriberDetailsIdAndIsOldImageDetails", map);
		}

}
