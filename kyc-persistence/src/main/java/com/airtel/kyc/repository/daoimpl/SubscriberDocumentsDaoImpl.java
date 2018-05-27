package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.SubscriberDocumentsDao;
import com.airtel.kyc.repository.entity.SubscriberDocuments;
@Repository
public class SubscriberDocumentsDaoImpl extends GenericDaoImpl<SubscriberDocuments, Integer> implements SubscriberDocumentsDao{

	@Override
	public List<SubscriberDocuments> getSubscriberDocumentsList(Integer subscriberDetailsId,Integer isOldIdDetails) 
	{
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberDetailsId", subscriberDetailsId);
		map.put("isOldImageDetails", isOldIdDetails);			
		return findResultsByNamedQuery("SubscriberDocuments.findBySubscriberDetailsIdAndIsOldIdDetails", map);
	}

}
