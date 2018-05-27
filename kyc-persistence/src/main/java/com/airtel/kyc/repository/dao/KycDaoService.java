package com.airtel.kyc.repository.dao;

import java.util.List;
import java.util.Map;

import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.repository.entity.BaseEntity;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.SubscriberIdDetails;
import com.airtel.kyc.repository.entity.SubscriberImageDetail;
import com.airtel.kyc.repository.entity.UserDistrict;
import com.airtel.kyc.repository.entity.UserProvince;
import com.airtel.kyc.repository.entity.UserVillage;
import com.airtel.kyc.repository.entity.VerificationByPass;
import com.airtel.kyc.repository.entity.task.RequestLog;
import com.airtel.kyc.repository.exception.KycDaoException;

public interface  KycDaoService<T extends BaseEntity> {
	
	public BaseEntity saveOrUpdateEntity(BaseEntity baseEntity) throws KycDaoException;


	public void updateEntity(BaseEntity baseEntity) throws KycDaoException;

	public void removeEntity(BaseEntity baseEntity) throws KycDaoException;

	public long getEntitySize(Class<T> clazz, Map<String, Object> params)
			throws KycDaoException;

	public List<T> findByCriteria(Class<T> clazz,
			Map<String, Object> parameterMap) throws KycDaoException;
	
	public BaseEntity getEntityByCriteria(Class<T> clazz,
			Map<String, Object> parameterMap) throws KycDaoException;
	
	public BaseEntity getEntityByCriteriaQuery(Class<T> clazz,
			Map<String, Object> parameterMap) throws KycDaoException;
	
	public List<T> findByCriteria(Class<T> clazz, Map<String, Object> params,
			int offSet, int startIndex) throws KycDaoException;

	public BaseEntity getEntityByNamedQuery( String namedQuery,
			Map<String, Object> params) throws KycDaoException;

	public List<T> findByNamedQuery(String namedQuery,
			Map<String, Object> params) throws KycDaoException;
	
	/*public List<? extends BaseEntity> findByCriteria(Class<? extends BaseEntity> clazz,
			Map<String, Object> parameterMap) throws KycDaoException;*/
	
	
	public List<Subscriber> getSubscriber(SearchSubscriberDto searchSubscriberDto) throws KycDaoException;
	
	public List<Subscriber> getOldApprovedSubscriber(SearchSubscriberDto searchSubscriberDto) throws KycDaoException;
	
	public List<Subscriber> getSubscriber(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<Subscriber> getPendingSubscriber(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<Subscriber> getCmSubscriber(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	public List<Subscriber> getClosedSubscriber(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<Subscriber> getApprovedSubscriber(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<Subscriber> getBarredSubscriberClosed(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<Subscriber> getRejectedSubscriberClosed(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<Subscriber> getHotlinedSubscriberClosed(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;
	
	public List<SubscriberDetails> getSubscriberDetails(Long subscriberId,Integer isOldUserDetails) throws KycDaoException;
	
	public List<SubscriberIdDetails> getSubscriberIdDetails(Integer subscriberDetailsId,Integer isOldIdDetails) throws KycDaoException;
	
	public List<SubscriberImageDetail> getSubscriberImageDetail(Integer subscriberDetailsId,Integer isOldImageDetails) throws KycDaoException;
	
	public List<Subscriber> getBarredSubscriber(SearchSubscriberDto searchSubscriberDto) throws KycDaoException;

	//public Subscriber getSubscriberBySubscriberId(int subscriberId) throws KycDaoException;
	
	public long getTotalSubscriber(SearchSubscriberDto parameterMap) throws KycDaoException;
	
	public long getTotalSubscriber(SearchSubscriberDto parameterMap,String actionType) throws KycDaoException;
	
	public List<VerificationByPass> getBypassStatus(Integer isByPassflag) throws KycDaoException;
	
	public Long findByCriteria(Class<?> clazz) throws KycDaoException;
	
	public List getSubscriberAddress(Integer subscriberDetailsId) throws KycDaoException;
	
	public List getSubscriberAddressNew(Integer subscriberDetailsId) throws KycDaoException;
	
	public List<RequestLog> getRequestsByType(Integer requestType,String hostName) throws KycDaoException;


	public List<Subscriber> getNewApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)throws KycDaoException;
	
	public BaseEntity getEntityBySQL(Class clazz, Map parameterMap) throws KycDaoException;
	
	public List<Subscriber> getSubscriber(String sql) throws KycDaoException;
	
	public SubscriberDetails getApprovedSubscriber(Map<String, Object> paramMap) throws KycDaoException;
	
	public UserDistrict getUserDistrict(Integer userid) throws KycDaoException;
	
	public UserProvince getUserProvince(Integer userid) throws KycDaoException;
	
	public UserVillage getUserVillage(Integer userid) throws KycDaoException;
	
	public int updateQuery(SubscriberDetails subscriberDetail) throws KycDaoException;
	
	public List<Subscriber> getSubscriberAutoAssign(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException;


	long getTotalClosedSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;


	long getTotalPendingSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;


	long getTotalApprovedSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;


	long getTotalBarredSubscriberClosed(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;


	long getTotalRejectedSubscriberClosed(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;


	long getTotalHotlinedSubscriberClosed(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;


	long getTotalCmSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException;
}
