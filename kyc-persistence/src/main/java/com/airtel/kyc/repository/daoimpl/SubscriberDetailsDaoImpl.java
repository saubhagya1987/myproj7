package com.airtel.kyc.repository.daoimpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.entity.SubscriberDetails;
@Repository
public class SubscriberDetailsDaoImpl extends GenericDaoImpl<SubscriberDetails, Integer> implements SubscriberDetailsDao{

	@Override
	public List<SubscriberDetails> getSubscriberDetails(Long subscriberId, Integer isOldUserDetails) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);
		map.put("isOldUserDetails", isOldUserDetails);		
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdAndIsOldUserDetails", map);
	}

	@Override
	public SubscriberDetails getSubscriberDetailsByMsisdn(String msisdn, Integer isOldUserDetails,String finalStatus) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);
		map.put("isOldUserDetails", isOldUserDetails);	
		map.put("finalStatus", finalStatus);	
		return findSingleResultByNamedQuery("SubscriberDetails.findByMsisdnAndIsOldUserDetails", map);
	}

	@Override
	public SubscriberDetails getSubscriberDetails(String msisdn, Integer isOldUserDetails,String finalStatus) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);
		map.put("isOldUserDetails", isOldUserDetails);	
		map.put("finalStatus", finalStatus);	
		return findSingleResultByNamedQuery("SubscriberDetails.findByMsisdnAndIsOldUserDetailsAndFinalStatus", map);
	}
	

	@Override
	public List<SubscriberDetails> getSubscriberDetailsAndUpdatedOn(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOn", map);
	}
	
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnApproved(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOnApproved", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnObject(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOnObject", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnObjectForUpdate(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOnObjectForUpdate", map);
	}
	
	@Override
	public SubscriberDetails getSubscriberDetailsAndUpdatedOnObjForUpdate(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findSingleResultByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOnObjectForUpdate", map);
	}
	
	
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsAndUpdatedOnAuto(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOn", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsApproved(Long subscriberId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdApproved", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsBarred(Long subscriberId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdBarred", map);
	}
	@Override
	public List<SubscriberDetails> getSubscriberDetailsPendingAll(Long subscriberId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdPendingAll", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsPendingAllWithNew() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("isOldUserDetails", 0);
		map.put("finalStatus", "PENDING");
		return findResultsByNamedQuery("SubscriberDetails.findPendingAllWithNew", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsPendingAllWithEdit() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("isOldUserDetails", 0);
		map.put("finalStatus", "PENDING");
		return findResultsByNamedQuery("SubscriberDetails.findPendingAllWithEdit", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsPendingAllWithExisting() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("isOldUserDetails", 0);
		map.put("finalStatus", "PENDING");
		return findResultsByNamedQuery("SubscriberDetails.findPendingAllWithExisting", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsPendingAllWithAm() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("isOldUserDetails", 0);
		map.put("finalStatus", "PENDING");
		return findResultsByNamedQuery("SubscriberDetails.findPendingAllWithAm", map);
	}
	@Override
	public List<SubscriberDetails> getSubscriberDetailsClosedAll(Long subscriberId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		return findResultsByNamedQuery("SubscriberDetails.findBySubscriberIdClosedAll", map);
	}
	
	@Override
	public List<SubscriberDetails> getSubscriberDetailsHotline(Long subscriberId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		return findResultsByNamedQuery("SubscriberDetails.getSubscriberDetailsHotline", map);
	}

	@Override
	public List<SubscriberDetails> getSubscriberDetailsByMobileNo(String msisdn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);		
		return findResultsByNamedQuery("SubscriberDetails.findByMsisdn", map);
	}

	@Override
	public List<SubscriberDetails> getSubscriberDetailObj(Long subscriberId, String action, int isOldUserDetails) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);	
		map.put("isOldUserDetails", isOldUserDetails);	
		map.put("action", action);	
		return findResultsByNamedQuery("SubscriberDetails.getSubscriberDetailsObj", map);
	}

	@Override
	public SubscriberDetails getSubscriberDetailsAndUpdatedOnObj(Long subscriberId, Timestamp updatedOn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);		
		map.put("updatedOn", updatedOn);	
		return findSingleResultByNamedQuery("SubscriberDetails.findBySubscriberIdAndUpdatedOnObj", map);
	}

	@Override
	public SubscriberDetails getSubscriberDetailsBySImValidationFlag(String msisdn, Integer isOldUserDetails,
			Integer simValidationFlag) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);
		map.put("isOldUserDetails", isOldUserDetails);	
		map.put("simValidationFlag", simValidationFlag);	
		return findSingleResultByNamedQuery("SubscriberDetails.findByMsisdnAndSimValidationFlag", map);
	}
	
	@Override
	public SubscriberDetails getSubscriberDetailsStatus(String msisdn, Integer isOldUserDetails) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);
		map.put("isOldUserDetails", isOldUserDetails);			
		return findSingleResultByNamedQuery("SubscriberDetails.findByMsisdnAndIsOldUserDetail", map);
	}

	
}
