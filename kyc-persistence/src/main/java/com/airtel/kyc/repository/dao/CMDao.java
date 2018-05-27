package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Assignment;
import com.airtel.kyc.repository.entity.AssignmentTracker;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.exception.KycDaoException;

public interface CMDao {

	public List<Integer> getCMUsersByLastLogin(String timeSpanFormat,String loggedinTime,String loggedInUser) throws KycDaoException;
	
	public List<Assignment> getAssignmentDetails(String userIds,String inClause) throws KycDaoException;
	
	//public List<Assignment> getAllAssignmentDetails() throws KycDaoException;
	
	public Assignment getAssignmentDetail(String userId) throws KycDaoException;
	
	public AssignmentTracker getAssignmentTracker() throws KycDaoException;

	public Subscriber getSubscriberByMSISDN(String msisdn) throws KycDaoException;
	
	public Object saveObject(Object object) throws KycDaoException;
	
	public Assignment getAssignment(String assignment) throws KycDaoException;
	
	public ConfigData getConfigDataByDataId(Integer dataId) throws KycDaoException;
}
