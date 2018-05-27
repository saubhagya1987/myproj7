package com.airtel.kyc.repository.daoimpl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.CMDao;
import com.airtel.kyc.repository.entity.Assignment;
import com.airtel.kyc.repository.entity.AssignmentTracker;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.exception.KycDaoException;

@Repository
public class CMDaoImpl extends GenericDaoImpl<Object, Serializable> implements CMDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCMUsersByLastLogin(String timeSpanFormat,String loggedinTime,String loggedInUser) throws KycDaoException{
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(
				"select u.user_id from users u ,user_roles ur, "
				+ "role r where u.user_id=ur.user_id and "
				+ "ur.role_id=r.role_id and((SYSDATE-cast(u.last_login as Date))"
				+ timeSpanFormat
				+ ")<"
				+ loggedinTime
				+ " and r.role_id in"
				+ "("
				+ loggedInUser
				+ ")");

		List<Integer> roleList = (List<Integer>)(Object)findResultsBySQLQuery(sqlQuery.toString(),null);
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Assignment> getAssignmentDetails(String userIds,String inClause) throws KycDaoException{
		
		StringBuilder sqlQuery = new StringBuilder();
		if("in".equals(inClause)){
			sqlQuery.append("select * from assignment where user_id "
					+ inClause
					+ " ("
					+ userIds
					+ ") and count=0 order by sequence ");
		}
		else{
			sqlQuery.append("select * from assignment where user_id "
					+ inClause
					+ " ("
					+ userIds
					+ ") order by sequence ");
		}
		
		List<Assignment> assignmentList = (List<Assignment>)(Object)findResultsBySQLQuery(sqlQuery.toString(),new Assignment());
		return assignmentList;
	}
	
	@Override
	public AssignmentTracker getAssignmentTracker() throws KycDaoException{
		HashMap<String, Object> map = new HashMap<>();		
		return (AssignmentTracker)findSingleResultByNamedQuery("AssignmentTracker.findNextAssignment", map);
	}

	public Assignment getAssignmentDetail(String userId) throws KycDaoException{
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select * from assignment where user_id ="
				+ userId);
		
		Assignment assignment = (Assignment)(Object)findResultBySQLQuery(sqlQuery.toString(),new Assignment());
		return assignment;
	}
	
	public Object saveObject(Object object) throws KycDaoException{
		return saveOrUpdateEntity(object);
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Assignment> getAllAssignmentDetails() throws KycDaoException{
		
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select * from assignment order by sequence ");
		
		List<Assignment> assignmentList = (List<Assignment>)(Object)findResultsBySQLQuery(sqlQuery.toString(),new Assignment());
		return assignmentList;
	}*/
	
	public Subscriber getSubscriberByMSISDN(String msisdn) throws KycDaoException{
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);				
		Subscriber subscriber = (Subscriber)findSingleResultByNamedQuery("Subscriber.findByMsisdn", map);
		return subscriber;
		
	}
	
	public Assignment getAssignment(String assignmentStr) throws KycDaoException{
		HashMap<String, Object> map = new HashMap<>();
		String sqlQuery = "";
		if("TOP".equals(assignmentStr)){
			sqlQuery = "select * from  assignment where sequence = (select max(sequence) from assignment)";
		}
		else{
			sqlQuery = "select * from  assignment where sequence = (select min(sequence) from assignment)";
		}
		Assignment assignment = (Assignment)(Object)findResultBySQLQuery(sqlQuery.toString(),new Assignment());
		return assignment;
		
	}
	
	public ConfigData getConfigDataByDataId(Integer dataId) throws KycDaoException{
		HashMap<String, Object> map = new HashMap<>();
		map.put("configDataId", dataId);
		return (ConfigData)findSingleResultByNamedQuery("ConfigData.findByDataId",map);
	}
}
