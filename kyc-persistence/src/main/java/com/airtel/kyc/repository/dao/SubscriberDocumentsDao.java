package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.SubscriberDocuments;

public interface SubscriberDocumentsDao extends GenericDao<SubscriberDocuments, Integer>{

	List<SubscriberDocuments> getSubscriberDocumentsList(Integer subscriberDetailId, Integer isOldUserDetails);

}
