package com.airtel.kyc.repository.dao;

import java.sql.Timestamp;
import java.util.List;

import com.airtel.kyc.repository.entity.SubscriberDetails;

public interface SubscriberDetailsDao extends GenericDao<SubscriberDetails, Integer> {

	List<SubscriberDetails> getSubscriberDetails(Long subscriberId, Integer isOldUserDetails);
	
	
	List<SubscriberDetails> getSubscriberDetailsByMobileNo(String msisdn);

	SubscriberDetails getSubscriberDetailsByMsisdn(String msisdn, Integer isOldUserDetails,String finalStatus);
	
	SubscriberDetails getSubscriberDetails(String msisdn, Integer isOldUserDetails,String finalStatus);

	SubscriberDetails getSubscriberDetailsAndUpdatedOnObj(Long subscriberId,  Timestamp updatedOn);
	
	List<SubscriberDetails> getSubscriberDetailsAndUpdatedOn(Long subscriberId,  Timestamp updatedOn);
	
	List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnApproved(Long subscriberId,  Timestamp updatedOn);
	
	List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnAuto(Long subscriberId,  Timestamp updatedOn);
	

	List<SubscriberDetails> getSubscriberDetailsApproved(Long subscriberId);
	
	List<SubscriberDetails> getSubscriberDetailsBarred(Long subscriberId);

	List<SubscriberDetails> getSubscriberDetailsPendingAll(Long subscriberId);
	
	List<SubscriberDetails> getSubscriberDetailsPendingAllWithNew();
	
	List<SubscriberDetails> getSubscriberDetailsPendingAllWithEdit();
	
	List<SubscriberDetails> getSubscriberDetailsPendingAllWithExisting();
	
	List<SubscriberDetails> getSubscriberDetailsPendingAllWithAm();

	List<SubscriberDetails> getSubscriberDetailsClosedAll(Long subscriberId);

	List<SubscriberDetails> getSubscriberDetailsHotline(Long subscriberId);


	List<SubscriberDetails> getSubscriberDetailObj(Long subscriberId, String action, int isOldUserDetails);


	List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnObject(Long subscriberId, Timestamp updatedOn);
	
	List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnObjectForUpdate(Long subscriberId, Timestamp updatedOn);
	
	SubscriberDetails getSubscriberDetailsAndUpdatedOnObjForUpdate(Long subscriberId,  Timestamp updatedOn);


	SubscriberDetails getSubscriberDetailsBySImValidationFlag(String msisdn, Integer false1, Integer simValidationFlag);


	SubscriberDetails getSubscriberDetailsStatus(String msisdn, Integer isOldUserDetails);
}