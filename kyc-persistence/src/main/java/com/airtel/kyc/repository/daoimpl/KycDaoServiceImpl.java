package com.airtel.kyc.repository.daoimpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.repository.dao.CustomHibernateDao;
import com.airtel.kyc.repository.dao.KycDaoService;
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
import com.airtel.user.persistence.config.dao.UserDaoService;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@SuppressWarnings("rawtypes")
@Repository
@Qualifier("kycDaoSession")
public class KycDaoServiceImpl<T extends BaseEntity> extends CustomHibernateDao implements KycDaoService {

	private static Logger logger = Logger.getLogger(KycDaoServiceImpl.class);
	
	@Autowired
	private AppConstants appConstants;
	
	/*@Autowired
	UserService userService;*/
	
	@Autowired
	@Qualifier("userDaoSession")
	UserDaoService userDaoService;

	@Override	
	//@Transactional
	public BaseEntity saveOrUpdateEntity(BaseEntity baseEntity) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "saveOrUpdateEntity ::";
		// logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		BaseEntity base = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//session.evict(baseEntity);
			//session.clear();
			 //session.saveOrUpdate(baseEntity);
			base = (BaseEntity) session.merge(baseEntity);
			transaction.commit();

		} catch (ConstraintViolationException e) {
			transaction.rollback();
			logger.error(methodName + e.getMessage());
			logger.error(methodName + e.getCause());
			throw new KycDaoException(e.getCause(), "Data already exist.");
		} catch (HibernateException e) {
			// logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			logger.error(methodName + e.getMessage());
			logger.error(methodName + e.getCause());
			throw new KycDaoException(e.getCause(), e.getMessage());
		} catch (Exception e) {
			// logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e.getCause(), e.getMessage());
		} finally {
			session.close();
		}
		return base;
	}

	@Override
	public void updateEntity(BaseEntity baseEntity) throws KycDaoException {
		// TODO Auto-generated method stub

		String methodName = "saveEntity ::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.merge(baseEntity);
			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void removeEntity(BaseEntity baseEntity) throws KycDaoException {
		// TODO Auto-generated method stub

		String methodName = "saveEntity ::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			baseEntity.setStatusFlag(KycConstants.FALSE);
			baseEntity.setDeleteFlag(KycConstants.TRUE);
			session.merge(baseEntity);
			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();

		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscriber> getSubscriber(SearchSubscriberDto searchSubscriberDto) throws KycDaoException {
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub
		String methodName = "getSubscriber::";
		Integer userFlag = 0;

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);
			criteria.setCacheable(true);

			criteria = getSearchCriteria(searchSubscriberDto, criteria);

			// criteria.add(org.hibernate.criterion.Expression.eq("userFlag",
			// userFlag));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<SubscriberDetails> getSubscriberDetails(Long subscriberId, Integer isOldUserDetails)
			throws KycDaoException {
		// TODO Auto-generated method stub

		List<SubscriberDetails> list = new ArrayList<SubscriberDetails>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SubscriberDetails.class);
			criteria.setCacheable(true);
			criteria.add(org.hibernate.criterion.Expression.eq("subscriber.subscriberId", subscriberId));
			if (isOldUserDetails != null
					&& (isOldUserDetails == KycConstants.TRUE || isOldUserDetails == KycConstants.FALSE)) {
				criteria.add(org.hibernate.criterion.Expression.eq("isOldUserDetails", isOldUserDetails));
			}
			criteria.addOrder(Order.desc("subscriberDetailsId"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	/*@Override
	public List<Subscriber> getSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class, "subscriber");

			if (searchSubscriberDto.getKycTansactionId() != null
					&& searchSubscriberDto.getKycTansactionId().trim().length() > 0) {
				criteria.add(Restrictions.like("kycTansactionId", searchSubscriberDto.getKycTansactionId(),
						MatchMode.ANYWHERE));
			}
			if (searchSubscriberDto.getMsisdn() != null) {
				criteria.add(Restrictions.eq("msisdn", searchSubscriberDto.getMsisdn()));
			}

			criteria = getSearchCriteria(searchSubscriberDto, criteria, actionType);
			criteria.setCacheable(true);

			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
*/
	@Override
	public List<Subscriber> getSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			/*session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class, "subscriber");

			if (searchSubscriberDto.getKycTansactionId() != null
					&& searchSubscriberDto.getKycTansactionId().trim().length() > 0) {
				criteria.add(Restrictions.like("kycTansactionId", searchSubscriberDto.getKycTansactionId(),
						MatchMode.ANYWHERE));
			}
			if (searchSubscriberDto.getMsisdn() != null) {
				criteria.add(Restrictions.eq("msisdn", searchSubscriberDto.getMsisdn()));
			}

			criteria = getSearchCriteria(searchSubscriberDto, criteria, actionType);
			criteria.setCacheable(true);

			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();*/
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getCMSearchCriteria(searchSubscriberDto, criteria,actionType);
			criteria.setCacheable(true);
			/*if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
*/
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Subscriber> getPendingSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getPendingCMSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			

			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subscriber> getCmSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getCMNewSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	
	@Override
	public List<Subscriber> getClosedSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getClosedCMSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Subscriber> getApprovedSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getApprovedCMSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Subscriber> getBarredSubscriberClosed(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getBarredCMSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Subscriber> getRejectedSubscriberClosed(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getRejectedCMSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Subscriber> getHotlinedSubscriberClosed(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getHotlinedCMSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);
			
			if (!"AUTO_ASSIGN".equals(actionType) && !"AUTO_REJECT".equals(actionType)
					&& !"AUTO_BAR".equals(actionType)) {
				if (searchSubscriberDto.getStartIndex() > 0)
					criteria.setFirstResult(searchSubscriberDto.getStartIndex());
				if (searchSubscriberDto.getSlot() > 0)
					criteria.setMaxResults(searchSubscriberDto.getSlot());
			}
			criteria.addOrder(Order.desc("createdOn"));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			//criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	private Criteria getClosedCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		
		
		
		if (parameterMap.getFinalStatus() != null && parameterMap.getFinalStatus().trim().length() > 0) {
			System.out.println("parameterMap.getFinalStatus()==" + parameterMap.getFinalStatus());
			
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.finalStatus", "BARRED"), Restrictions.eq("subscriberDetails.finalStatus","APPROVED") ,Restrictions.eq("subscriberDetails.finalStatus", "REJECTED") ));
			
			
				
			
		}
		
		
		if (parameterMap.getAction().equals("PENDING")) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.finalStatus", "BARRED"), Restrictions.eq("subscriberDetails.finalStatus","APPROVED") ,Restrictions.eq("subscriberDetails.finalStatus", "REJECTED") ));
			}
			if (parameterMap.getCaseType() == null) {
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType","EDIT") ,Restrictions.eq("subscriberDetails.caseType", "EXISTING") ));
			}			
			
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.action", "PENDING"),Restrictions.eq("subscriberDetails.action", "BARRED"),Restrictions.eq("subscriberDetails.action", "APPROVED"), Restrictions.isNull("subscriberDetails.action"),Restrictions.eq("subscriberDetails.caseType", "AM")));
			if (parameterMap.getUserId() != 0) {
				Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				else{
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}
				
			}
		}
		
				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}
	
	private Criteria getApprovedCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		
		
		
		if (parameterMap.getFinalStatus() != null && parameterMap.getFinalStatus().trim().length() > 0) {
			System.out.println("parameterMap.getFinalStatus()==" + parameterMap.getFinalStatus());
			
			criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "APPROVED"));			
					
			
		}
		
		
		if (parameterMap.getAction().equals("PENDING")) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "APPROVED") );
			}
			if (parameterMap.getCaseType() == null) {
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType","EDIT") ,Restrictions.eq("subscriberDetails.caseType", "EXISTING"),Restrictions.eq("subscriberDetails.caseType", "AM") ));
			}			
			
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.action", "APPROVED"), Restrictions.isNull("subscriberDetails.action")));
			if (parameterMap.getUserId() != 0) {
				Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				else{
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}
				
			}
			
			
		}
		
				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}
	
	private Criteria getBarredCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {	
		String BARRED = "BARRED";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -4);
		Date dateBefore4Days = cal.getTime();
		System.out.println("dateBefore4Days:" + dateBefore4Days);
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		//criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		
		
		
		if (parameterMap.getFinalStatus() != null && parameterMap.getFinalStatus().trim().length() > 0) {
			System.out.println("parameterMap.getFinalStatus()==" + parameterMap.getFinalStatus());
			
			criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "BARRED"));
			
					
			
		}
		
		
		if (parameterMap.getAction().equals("PENDING")) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "BARRED") );
			}
			if (parameterMap.getCaseType() == null) {
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType","EDIT") ,Restrictions.eq("subscriberDetails.caseType", "EXISTING"),Restrictions.eq("subscriberDetails.caseType", "AM")  ));
			}			
			
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.action", "BARRED"), Restrictions.isNull("subscriberDetails.action")));
			if (parameterMap.getUserId() != 0) {
				Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				else{
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}
				
			}
			
			
		}
		
				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		criteria.add(Restrictions.eq("subscriberWorkFlow.action", BARRED));

		criteria.add(Restrictions.ge("subscriberWorkFlow.updatedOn", dateBefore4Days));
		criteria.add(Restrictions.lt("subscriberWorkFlow.updatedOn", new Date()));
		
		return criteria;
	}
	
	
	private Criteria getRejectedCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		
		
		
		if (parameterMap.getFinalStatus() != null && parameterMap.getFinalStatus().trim().length() > 0) {
			System.out.println("parameterMap.getFinalStatus()==" + parameterMap.getFinalStatus());
			
			criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "REJECTED"));
			
					
			
		}
		
		
		if (parameterMap.getAction().equals("PENDING")) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "REJECTED") );
			}
			if (parameterMap.getCaseType() == null) {
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType","EDIT") ,Restrictions.eq("subscriberDetails.caseType", "EXISTING"),Restrictions.eq("subscriberDetails.caseType", "AM")  ));
			}			
			
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.action", "REJECTED"), Restrictions.isNull("subscriberDetails.action")));
			if (parameterMap.getUserId() != 0) {
				Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				else{
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}
				
			}
			
			
		}
		
				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}

	
	private Criteria getHotlinedCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		
		
		
		
		
		
		if (parameterMap.getAction().equals("PENDING")) {
			
			if (parameterMap.getCaseType() == null) {
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType","EDIT") ,Restrictions.eq("subscriberDetails.caseType", "EXISTING"),Restrictions.eq("subscriberDetails.caseType", "AM")  ));
			}			
			
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.hotlineFlag",1), Restrictions.isNull("subscriberDetails.hotlineFlag")));
			
			if (parameterMap.getUserId() != 0) {
				Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				else{
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}
				
			}
			
			
		}
		
				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}
	
	@Override
	public List<Subscriber> getSubscriberAutoAssign(SearchSubscriberDto searchSubscriberDto,String actionType) throws KycDaoException {

		logger.debug("getSubscriberAutoAssign...executing...");
		StringBuilder sqlQuery = new StringBuilder();

		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			sqlQuery.append("select * from subscriber s inner join subscriber_details sd"
					+ " on s.subscriber_id=sd.subscriber_id left outer join subscriber_work_flow sdwf"
					+ " on sd.subscriber_details_id=sdwf.subscriber_details_id where"
					+ " sdwf.subscriber_details_id is null and (( sd.is_old_user_details=0"
					+ " and sd.final_status='PENDING' )  or ( sd.is_old_user_details=1"
					+ " and to_char(s.updated_on,'dd/MM/yyyy hh12:mi')=to_char(sd.created_on,'dd/MM/yyyy hh12:mi')"
					+ " )) ");
			logger.debug("QUERY:::::::::::::::::: " + sqlQuery.toString());

			SQLQuery query = (SQLQuery) session.createSQLQuery(sqlQuery.toString());
			query.addEntity(Subscriber.class);
			return query.list();
		} catch (NoResultException ex) {
			logger.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Subscriber> getSubscriber(String sql) throws KycDaoException {

		logger.debug("getSubscriber...executing...");

		Session session = null;
		try {
			session = this.sessionFactory.openSession();

			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			query.addEntity(Subscriber.class);
			return (List<Subscriber>) query.list();
		} catch (NoResultException ex) {
			logger.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<SubscriberIdDetails> getSubscriberIdDetails(Integer subscriberDetailsId, Integer isOldIdDetails)
			throws KycDaoException {
		// TODO Auto-generated method stub
		List<SubscriberIdDetails> list = new ArrayList<SubscriberIdDetails>();
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;

		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SubscriberIdDetails.class);
			criteria.setCacheable(true);

			criteria.add(org.hibernate.criterion.Expression.eq("subscriberDetails.subscriberDetailsId",
					subscriberDetailsId));

			if (isOldIdDetails != null
					&& (isOldIdDetails == KycConstants.TRUE || isOldIdDetails == KycConstants.FALSE)) {
				criteria.add(org.hibernate.criterion.Expression.eq("isOldIdDetails", isOldIdDetails));
			}

			criteria.addOrder(Order.asc("idNo"));

			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<SubscriberImageDetail> getSubscriberImageDetail(Integer subscriberDetailsId, Integer isOldImageDetails)
			throws KycDaoException {

		List<SubscriberImageDetail> list = new ArrayList<SubscriberImageDetail>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SubscriberImageDetail.class);
			criteria.setCacheable(true);

			criteria.add(org.hibernate.criterion.Expression.eq("subscriberDetails.subscriberDetailsId",
					subscriberDetailsId));

			if (isOldImageDetails != null
					&& (isOldImageDetails == KycConstants.TRUE || isOldImageDetails == KycConstants.FALSE)) {
				criteria.add(org.hibernate.criterion.Expression.eq("isOldImageDetails", isOldImageDetails));
			}

			criteria.addOrder(Order.asc("imageId"));

			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public long getTotalSubscriber(SearchSubscriberDto parameterMap) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);
			criteria.setCacheable(true);
			criteria = getSearchCriteria(parameterMap, criteria);
			// criteria.add(org.hibernate.criterion.Expression.isNotNull("activationTime"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}

	private Criteria getSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) {

		criteria.createAlias("subscriberDetails", "subscriberDetails");

		if (parameterMap.getKycTansactionId() != null && parameterMap.getKycTansactionId().trim().length() > 0) {
			criteria.add(Restrictions.like("kycTansactionId", parameterMap.getKycTansactionId(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getEmail() != null && parameterMap.getEmail().trim().length() > 0) {
			criteria.add(Restrictions.like("subscriberDetails.email", parameterMap.getEmail(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		/////
		if (parameterMap.getStatus() != null && parameterMap.getStatus().trim().length() > 0) {
			System.out.println("parameterMap.getStatus()==" + parameterMap.getStatus());
			criteria.add(
					Restrictions.like("subscriberDetails.finalStatus", parameterMap.getStatus(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getCreatedOn() != null) {
			System.out.println("parameterMap.getCreatedOn()==" + parameterMap.getCreatedOn());
			criteria.add(Restrictions.le("subscriberDetails.createdOn", parameterMap.getCreatedOn()));
		}
		/*if (parameterMap.getCreatedBy() != null) {			
			criteria.add(Restrictions.eq("subscriberDetails.createdBy", parameterMap.getCreatedBy()));
		}*/
		criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", 0));

		return criteria;
	}

	@Override
	public long getTotalSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class, "subscriber");
			criteria.setCacheable(true);
			criteria = getSearchCriteria(parameterMap, criteria, actionType);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}

	@Override
	public long getTotalClosedSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);
			criteria = getClosedCMSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			//criteria = getSearchCriteria(parameterMap, criteria, actionType);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}
	
	@Override
	public long getTotalPendingSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getPendingCMSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}

	
	@Override
	public long getTotalApprovedSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getApprovedCMSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}
	
	@Override
	public long getTotalBarredSubscriberClosed(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getBarredCMSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}
	
	@Override
	public long getTotalRejectedSubscriberClosed(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getRejectedCMSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}
	
	
	@Override
	public long getTotalHotlinedSubscriberClosed(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getHotlinedCMSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}
	
	@Override
	public long getTotalCmSubscriber(SearchSubscriberDto parameterMap, String actionType) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {			
			
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);
			
			criteria = getCMNewSearchCriteria(parameterMap, criteria);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}
	
	private Criteria getSearchCriteria(SearchSubscriberDto searchSubscriberDto, Criteria criteria, String actionType) {

		criteria.createAlias("subscriberDetails", "subscriberDetails");
		
		if("KYC-SUPERVISOR".equals(searchSubscriberDto.getRoleName())){
			if ("PENDING_ALL".equals(searchSubscriberDto.getActionType())) {
				criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "PENDING"));
				
				if (searchSubscriberDto.getCaseType() != null && searchSubscriberDto.getCaseType().trim().length() > 0) {
					criteria.add(Restrictions.like("subscriberDetails.caseType", searchSubscriberDto.getCaseType(),
							MatchMode.ANYWHERE));
				}
				if (searchSubscriberDto.getName() != null && searchSubscriberDto.getName().trim().length() > 0) {
					Disjunction or = Restrictions.disjunction();
					or.add(Restrictions.like("subscriberDetails.firstName", searchSubscriberDto.getName(),MatchMode.ANYWHERE));
					or.add(Restrictions.like("subscriberDetails.lastName", searchSubscriberDto.getName(),MatchMode.ANYWHERE));
					criteria.add(or);
				}
				if (searchSubscriberDto.getIdNumber() != null && searchSubscriberDto.getIdNumber().trim().length() > 0) {
					criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
					criteria.add(Restrictions.like("subscriberIdDetails.idNumber", searchSubscriberDto.getIdNumber(),
							MatchMode.ANYWHERE));
				}
			}
			else{
				if(searchSubscriberDto.getFinalStatus()!=null && searchSubscriberDto.getFinalStatus().trim().length()>0){
					if("HOTLINE".equals(searchSubscriberDto.getFinalStatus())){
						criteria.add(Restrictions.eq("subscriberDetails.hotlineFlag", 1));
					}
					else{
						criteria.add(Restrictions.eq("subscriberDetails.finalStatus", searchSubscriberDto.getFinalStatus()));
					}
				}else{
					Disjunction or = Restrictions.disjunction();
					or.add(Restrictions.eq("subscriberDetails.finalStatus", "BARRED"));
					or.add(Restrictions.eq("subscriberDetails.finalStatus", "APPROVED"));
					or.add(Restrictions.eq("subscriberDetails.finalStatus", "REJECTED"));
					or.add(Restrictions.eq("subscriberDetails.hotlineFlag", 1));
					criteria.add(or);
				}
				
				Date parsedFromDate = null;
				Date parsedToDate = null;
				if(searchSubscriberDto.getFromDate() != null && searchSubscriberDto.getFromDate().trim().length()>0){
					SimpleDateFormat dateFormat = new SimpleDateFormat(appConstants.dateFormat);
					try {
						parsedFromDate = dateFormat.parse(searchSubscriberDto.getFromDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				if(searchSubscriberDto.getToDate() != null && searchSubscriberDto.getToDate().trim().length()>0){
					SimpleDateFormat dateFormat = new SimpleDateFormat(appConstants.dateFormat);
					try {
						parsedToDate = dateFormat.parse(searchSubscriberDto.getToDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(parsedFromDate!=null && parsedToDate!=null){
					Disjunction or = Restrictions.disjunction();
					or.add(Restrictions.ge("subscriberDetails.updatedOn", new Timestamp(parsedFromDate.getTime())));
					or.add(Restrictions.le("subscriberDetails.updatedOn",new Timestamp(parsedToDate.getTime())));
					criteria.add(or);
				}
				else if(parsedFromDate!=null && parsedToDate==null){
					criteria.add(Restrictions.ge("updatedOn", new Timestamp(parsedFromDate.getTime())));
				}
				else if(parsedToDate!=null && parsedFromDate==null){
					criteria.add(Restrictions.le("updatedOn", new Timestamp(parsedToDate.getTime())));
				}
			}
		}
		else{
			if ("AUTO_BAR".equals(actionType)) {
				criteria.add(Restrictions.eq("subscriberDetails.hotlineFlag", 1));
				criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", 0));
				criteria.add(Restrictions.ne("subscriberDetails.finalStatus", "BARRED"));
				criteria.add(Restrictions.le("subscriberDetails.updatedOn", searchSubscriberDto.getUpdatedOn()));
			} else {
	
				if (searchSubscriberDto.getEmail() != null && searchSubscriberDto.getEmail().trim().length() > 0) {
					criteria.add(Restrictions.like("subscriberDetails.email", searchSubscriberDto.getEmail(),
							MatchMode.ANYWHERE));
				}
				criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow",
						CriteriaSpecification.LEFT_JOIN);
	
				if ("AUTO_ASSIGN".equals(actionType)) {
					criteria.add(Restrictions.isNull("subscriberWorkFlow.subscriberDetails.subscriberDetailsId"));
				} else if (KycConstants.PENDING_STATUS.equals(actionType)) {
					criteria.add(Restrictions.eq("subscriberWorkFlow.action", actionType));
				} else {
					if ("AUTO_REJECT".equals(actionType)) {
						criteria.add(Restrictions.eq("subscriberWorkFlow.action", searchSubscriberDto.getStatus()));
					} else if ("BARRED".equals(actionType) || "APPROVED".equals(actionType)) {
						criteria.add(Restrictions.eq("subscriberWorkFlow.action", actionType));
						criteria.add(Restrictions.eq("subscriberWorkFlow.roleName", searchSubscriberDto.getRoleName()));
					}
				}
				if (searchSubscriberDto.getUserId() != 0) {
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", searchSubscriberDto.getUserId()));
				}
	
				if (searchSubscriberDto.getUpdatedOn() != null) {
					if ("AUTO_REJECT".equals(actionType)) {
						criteria.add(Restrictions.le("subscriberDetails.updatedOn", searchSubscriberDto.getUpdatedOn()));
					} 
				}
				if (searchSubscriberDto.getStatus() != null) {
					criteria.add(Restrictions.eq("subscriberDetails.finalStatus", searchSubscriberDto.getStatus()));
				}
				if (searchSubscriberDto.getCaseType() != null && searchSubscriberDto.getCaseType().trim().length() > 0) {
					criteria.add(Restrictions.like("subscriberDetails.caseType", searchSubscriberDto.getCaseType(),
							MatchMode.ANYWHERE));
				}
	
				if ("AUTO_ASSIGN".equals(actionType) || KycConstants.PENDING_STATUS.equals(actionType)
						|| "AUTO_REJECT".equals(actionType)) {
					Criterion c1 = null;
					if("AUTO_REJECT".equals(actionType)){
						c1 = Restrictions.and(Restrictions.eq("subscriberDetails.isOldUserDetails", 0),
							Restrictions.eq("subscriberDetails.finalStatus", "BARRED"));
					}
					else{
						c1 = Restrictions.and(Restrictions.eq("subscriberDetails.isOldUserDetails", 0),
								Restrictions.eq("subscriberDetails.finalStatus", KycConstants.PENDING_STATUS));
					}
					Criterion c2 = Restrictions.and(Restrictions.eq("subscriberDetails.isOldUserDetails", 1),
							Restrictions.eqProperty("updatedOn", "subscriberDetails.updatedOn"));
					criteria.add(Restrictions.or(c1, c2));
				} else {
					if (!"BARRED".equals(actionType) && !"APPROVED".equals(actionType)){
						criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", 0));
					}
				}
			}
		}
		return criteria;
	}

	@Override
	public List<VerificationByPass> getBypassStatus(Integer isByPassflag) throws KycDaoException {
		// TODO Auto-generated method stub
		List<VerificationByPass> verificationByPass = new ArrayList<VerificationByPass>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(VerificationByPass.class);
			criteria.setCacheable(true);
			// criteria.setFirstResult(0);
			criteria.addOrder(Order.desc("bypassId"));

			if (isByPassflag != null && (isByPassflag == KycConstants.TRUE || isByPassflag == KycConstants.FALSE)) {
				criteria.add(org.hibernate.criterion.Expression.eq("bypassStatus", isByPassflag));
			}

			verificationByPass = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return verificationByPass;
	}

	@Override
	public long getEntitySize(Class clazz, Map params) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "getEntitySize::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;

		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);

			if (!params.isEmpty()) {
				Set<String> keySet = params.keySet();

				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, params.get(eachParam)));
				}
			}

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();

			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}
		logger.debug(methodName + "ends.");
		return resultCount;
	}

	@Override
	public BaseEntity getEntityByCriteriaQuery(Class clazz, Map parameterMap) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "findByCriteria::" + clazz;
		// logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;

		BaseEntity baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.setCacheable(true);

			if (parameterMap != null && !parameterMap.isEmpty()) {
				if (parameterMap.get("hotlineFlag") != null) {
					criteria.createAlias("subscriberDetails", "subscriberDetails");
					Set<String> keySet = parameterMap.keySet();
					for (String eachParam : keySet) {
						if (parameterMap.get("hotlineFlag") != null) {
							criteria.add(
									Restrictions.eq("subscriberDetails.hotlineFlag", parameterMap.get("hotlineFlag")));
						} else {
							criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
						}
					}
				} else {
					Set<String> keySet = parameterMap.keySet();
					for (String eachParam : keySet) {
						criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
					}
				}
			}

			baseEntity = (BaseEntity) criteria.uniqueResult();
			transaction.commit();

			if (clazz.getName().equals("com.airtel.kyc.repository.entity.Subscriber")) {
				Subscriber subs = (Subscriber) baseEntity;
				logger.debug("*********************************" + subs.getSubscriberDetails().get(0).getFirstName());
			}

		} catch (HibernateException e) {
			logger.error(e.getMessage() + ":CAUSE_getEntityByCriteria:" + e.getCause());
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + "CAUSE_getEntityByCriteria:" + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}

		// logger.debug(methodName + "ends.");
		return baseEntity;
	}

	@Override
	public BaseEntity getEntityByCriteria(Class clazz, Map parameterMap) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "findByCriteria::" + clazz;
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;

		BaseEntity baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.setCacheable(true);
			if (parameterMap != null && !parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();
				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
				}
			}
			baseEntity = (BaseEntity) criteria.uniqueResult();
			transaction.commit();

		} catch (HibernateException e) {
			logger.error("exception occured in dao"+ExceptionUtils.getStackTrace(e));
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}

		logger.debug(methodName + "ends.");
		return baseEntity;
	}

	@Override
	public BaseEntity getEntityBySQL(Class clazz, Map parameterMap) throws KycDaoException {
		logger.debug("getEntityBySQL...executing...");
		Long subscriberId = (Long) parameterMap.get("subscriberId");
		logger.debug("getEntityBySQL...executing...subscriberId..." + subscriberId);
		StringBuilder sqlQuery = new StringBuilder();

		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			sqlQuery.append("select * from subscriber s where s.subscriber_id=" + subscriberId);

			logger.debug("QUERY:::::::::::::::::: " + sqlQuery.toString());

			SQLQuery query = (SQLQuery) session.createSQLQuery(sqlQuery.toString());
			if (clazz != null) {
				query.addEntity(clazz);
			}
			return (BaseEntity) query.uniqueResult();
		} catch (NoResultException ex) {
			logger.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List findByCriteria(Class clazz, Map parameterMap, int offSet, int startIndex) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "getCustomersByTransaction::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.setCacheable(true);

			if (parameterMap != null && !parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();

				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
				}
			}

			if (startIndex > 0)
				criteria.setFirstResult(startIndex);
			if (offSet > 0)
				criteria.setMaxResults(offSet);

			baseEntity = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}

		return baseEntity;
	}

	@Override
	public BaseEntity getEntityByNamedQuery(String namedQuery, Map parameterMap) throws KycDaoException {
		String methodName = "getCustomersByTransaction::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		BaseEntity baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = session.getNamedQuery(namedQuery);

			if (parameterMap != null && !parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();
				for (String eachParam : keySet) {
					query.setParameter(eachParam, parameterMap.get(eachParam));
				}
			}

			baseEntity = (BaseEntity) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}

		return baseEntity;
	}

	@Override
	public List findByNamedQuery(String namedQuery, Map parameterMap) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "getCustomersByTransaction::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = session.getNamedQuery(namedQuery);

			if (parameterMap != null && !parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();
				for (String eachParam : keySet) {
					query.setParameter(eachParam, parameterMap.get(eachParam));
				}
			}

			baseEntity = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}

		return baseEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List findByCriteria(Class clazz, Map parameterMap) throws KycDaoException {
		String methodName = "findByCriteria::" + clazz;
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			if (parameterMap != null && !parameterMap.isEmpty()) {
				Set<String> keySet = parameterMap.keySet();
				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
				}
			}
			baseEntity = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new KycDaoException(e);
		} finally {
			session.close();
		}

		logger.debug(methodName + "ends.");
		return (List<? extends BaseEntity>) baseEntity;
	}

	@Override
	public Long findByCriteria(Class clazz) throws KycDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.setCacheable(true);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(Projections.rowCount());
			resultCount = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return resultCount;
	}

	@Override
	public List getSubscriberAddress(Integer subscriberDetailsId) throws KycDaoException {
		// TODO Auto-generated method stub
		Integer isOldDetails = 0;
		Session session = null;
		Transaction transaction = null;
		SQLQuery query = null;
		List list1 = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String sql = "select sub.ADDRESS_ID from SUBSCRIBER_DETAILS sub where sub.SUBSCRIBER_DETAILS_ID = "
					+ subscriberDetailsId + "AND IS_OLD_USER_DETAILS = " + isOldDetails;

			query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			list1 = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list1;
	}
	
	
	@Override
	public List getSubscriberAddressNew(Integer subscriberDetailsId) throws KycDaoException {
		// TODO Auto-generated method stub
		Integer isOldDetails = 1;
		Session session = null;
		Transaction transaction = null;
		SQLQuery query = null;
		List list1 = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String sql = "select sub.ADDRESS_ID from SUBSCRIBER_DETAILS sub where sub.SUBSCRIBER_DETAILS_ID = "
					+ subscriberDetailsId + "AND IS_OLD_USER_DETAILS = " + isOldDetails;

			query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			list1 = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list1;
	}

	@Override
	public List<RequestLog> getRequestsByType(Integer requestType, String hostName) throws KycDaoException {
		Session session = null;
		String MethodName = "getRequestsByType:";
		List<RequestLog> reqests = null;
		try {
			session = sessionFactory.openSession();
			StringBuffer queryString = new StringBuffer();
			SQLQuery sql = null;
			queryString.append("Select * from request_log where  ");
			queryString.append(
					" trunc(req_due_time)>=trunc(SYSTIMESTAMP)-30 and trunc(req_due_time)<=trunc(SYSTIMESTAMP) ");
			queryString
					.append(" and agent_id=:agent_id and request_status=:request_status and request_by=:request_by ");
			// queryString.append(" and agent_id=:agent_id and
			// request_status=:request_status and request_by=:request_by and
			// req_due_time<= SYSTIMESTAMP");
			sql = session.createSQLQuery(queryString.toString());
			sql.setParameter("agent_id", requestType);
			sql.setParameter("request_by", hostName);
			sql.setParameter("request_status", "New");

			sql.addEntity(RequestLog.class);
			reqests = sql.list();
		} catch (HibernateException e) {
			logger.error(MethodName + e.getMessage());
			throw new KycDaoException(e.getMessage());
		} catch (Exception e) {
			logger.error(MethodName + e.getMessage());
			throw new KycDaoException(e.getMessage());
		} finally {
			session.close();
		}

		return reqests;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscriber> getBarredSubscriber(SearchSubscriberDto searchSubscriberDto) throws KycDaoException {
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub
		String methodName = "getSubscriber::";
		Integer userFlag = 0;

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getBarredSearchCriteria(searchSubscriberDto, criteria);
			criteria.setCacheable(true);

			// criteria.add(org.hibernate.criterion.Expression.eq("userFlag",
			// userFlag));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	private Criteria getBarredSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) {
		String BARRED = "BARRED";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -4);
		Date dateBefore4Days = cal.getTime();
		System.out.println("dateBefore4Days:" + dateBefore4Days);

		criteria.createAlias("subscriberDetails", "subscriberDetails");
		criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getEmail().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getEmail().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}

		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(Restrictions.like("caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("subscriberWorkFlow.action", BARRED));

		criteria.add(Restrictions.ge("subscriberWorkFlow.updatedOn", dateBefore4Days));
		criteria.add(Restrictions.lt("subscriberWorkFlow.updatedOn", new Date()));

		if (parameterMap.getIsOldUserDetails() != -99) {
			criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", parameterMap.getIsOldUserDetails()));
		}

		return criteria;
	}	
	private Criteria getCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria,String actionType) {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		//criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}		
		if (parameterMap.getFinalStatus() != null && parameterMap.getFinalStatus().trim().length() > 0) {
			System.out.println("parameterMap.getFinalStatus()==" + parameterMap.getFinalStatus());
			criteria.add(
					Restrictions.eq("subscriberDetails.finalStatus", parameterMap.getFinalStatus()));
		}		
		if (parameterMap.getAction() != null && parameterMap.getAction().trim().length() > 0) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				criteria.add(Restrictions.eq("subscriberDetails.finalStatus","PENDING"));
			}
			if (parameterMap.getCaseType() == null) {
				
				//criteria.add(Restrictions.eq("subscriberDetails.caseType", "NEW"));
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType","NEW"), Restrictions.eq("subscriberDetails.caseType","EDIT"), Restrictions.eq("subscriberDetails.caseType","EXISTING")));
			}	
			if("AUTO_ASSIGN".equals(actionType)){
				criteria.add(Restrictions.isNull("subscriberDetails.assignmentFlag"));
				criteria.add(Restrictions.eq("subscriberDetails.simValidationFlag",1));
			}
			else{
				criteria.add(Restrictions.or(Restrictions.eqOrIsNull("subscriberDetails.action", "PENDING"), Restrictions.isNull("subscriberDetails.action")));
			}
						
		}				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}

	
	private Criteria getPendingCMSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		//criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}		
		if (parameterMap.getFinalStatus() != null && parameterMap.getFinalStatus().trim().length() > 0) {
			System.out.println("parameterMap.getFinalStatus()==" + parameterMap.getFinalStatus());
			criteria.add(
					Restrictions.eq("subscriberDetails.finalStatus", parameterMap.getFinalStatus()));
		}		
		if (parameterMap.getAction() != null && parameterMap.getAction().trim().length() > 0) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				criteria.add(Restrictions.eq("subscriberDetails.finalStatus","PENDING"));
				criteria.add(Restrictions.eq("subscriberDetails.simValidationFlag",1));
			}
			if (parameterMap.getCaseType() == null) {
				
				//criteria.add(Restrictions.eq("subscriberDetails.caseType", "NEW"));
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType", "EDIT"), Restrictions.eq("subscriberDetails.caseType", "EXISTING")));
			}			
			criteria.add(Restrictions.or(Restrictions.eqOrIsNull("subscriberDetails.action", "PENDING"), Restrictions.isNull("subscriberDetails.action")));
						
			if (parameterMap.getUserId() != 0) {
				Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				else{
					//criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}
				
			}
		}				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}
	
	private Criteria getCMNewSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) throws UserDaoException {		
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		//criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
		criteria.createAlias("subscriberDetails.subscriberIdDetails", "subscriberIdDetails");
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getFirstName() != null && parameterMap.getFirstName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.firstName", parameterMap.getFirstName(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getLastName() != null && parameterMap.getLastName().trim().length() > 0) {
			criteria.add(
					Restrictions.like("subscriberDetails.lastName", parameterMap.getLastName(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getIdNumber() != null && parameterMap.getIdNumber().trim().length() > 0) {			
			criteria.add(Restrictions.like("subscriberIdDetails.idNumber", parameterMap.getIdNumber(),
					MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(
					Restrictions.like("subscriberDetails.caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}	
		
		if (parameterMap.getAction() != null && parameterMap.getAction().trim().length() > 0) {
			System.out.println("parameterMap.getAction()==" + parameterMap.getAction());
			if (parameterMap.getFinalStatus() == null ) {
				
				//criteria.add(Restrictions.eq("subscriberDetails.finalStatus","PENDING"));
				
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.finalStatus","PENDING"), Restrictions.eq("subscriberDetails.finalStatus","APPROVED")));
			}
			if (parameterMap.getCaseType() == null) {
				
				//criteria.add(Restrictions.eq("subscriberDetails.caseType", "NEW"));
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.caseType", "NEW"), Restrictions.eq("subscriberDetails.caseType", "EDIT"), Restrictions.eq("subscriberDetails.caseType", "EXISTING")));
				criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.isImageEdited", "YES"), Restrictions.eq("subscriberDetails.isImageEdited", "NO")));
				//criteria.add(Restrictions.eq("subscriberDetails.isImageEdited", "YES"));
			}			
			
			//criteria.add(Restrictions.or(Restrictions.eqOrIsNull("subscriberDetails.action", "PENDING"), Restrictions.isNull("subscriberDetails.action")));
			criteria.add(Restrictions.or(Restrictions.eq("subscriberDetails.assignmentFlag", 1)));
			if (parameterMap.getUserId() != 0 && "CM".equals(parameterMap.getRoleName())) {
				criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
				criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				if("PENDING".equals(parameterMap.getAction())){
					criteria.add(Restrictions.eq("subscriberWorkFlow.action", parameterMap.getAction()));
				}
				/*Users users=userDaoService.findRoleByUserId(parameterMap.getUserId());
				if(users.getRoles().get(0).getRoleId()==34 || users.getRoles().get(0).getRoleId()==35 || users.getRoles().get(0).getRoleId()==36){
					
				}
				if(parameterMap.getAction().equals("PENDING")){
					
				}
				else{
					criteria.createAlias("subscriberDetails.subscriberWorkFlow", "subscriberWorkFlow");
					criteria.add(Restrictions.eq("subscriberWorkFlow.userId", parameterMap.getUserId()));
				}*/
			}
			
			
		}
				
		criteria.add(Restrictions.ne("subscriberDetails.isOldUserDetails", 1));
		criteria.add(Restrictions.ne("subscriberIdDetails.isOldIdDetails", 1));

		
		
		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subscriber> getOldApprovedSubscriber(SearchSubscriberDto searchSubscriberDto) throws KycDaoException {
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub
		String methodName = "getSubscriber::";
		Integer userFlag = 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -2);
		Date dateBefore2Days = cal.getTime();
		System.out.println("dateBefore2Days:" + dateBefore2Days);

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);
			criteria.setCacheable(true);

			criteria = getOldApprovedSearchCriteria(searchSubscriberDto, criteria);

			// criteria.add(org.hibernate.criterion.Expression.isNotNull("activationTime"));
			criteria.add(org.hibernate.criterion.Expression.eq("userFlag", userFlag));
			// criteria.add(org.hibernate.criterion.Expression.eq("physicalFormStatus",
			// physicalFormStatus));

			criteria.add(Restrictions.ge("createdOn", dateBefore2Days));
			criteria.add(Restrictions.lt("createdOn", new Date()));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	private Criteria getOldApprovedSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) {

		String physicalFormStatus = "PENDING";
		criteria.createAlias("subscriberDetails", "subscriberDetails");
		System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());

		if (parameterMap.getKycTansactionId() != null && parameterMap.getKycTansactionId().trim().length() > 0) {
			criteria.add(Restrictions.like("kycTansactionId", parameterMap.getKycTansactionId(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getEmail() != null && parameterMap.getEmail().trim().length() > 0) {
			criteria.add(Restrictions.like("subscriberDetails.email", parameterMap.getEmail(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(Restrictions.like("caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", 0));

		criteria.add(Restrictions.eq("subscriberDetails.physicalFormStatus", physicalFormStatus));

		return criteria;
	}

	@Override
	public List getNewApprovedSubscribers(SearchSubscriberDto searchSubscriberDto) throws KycDaoException {
		List<Subscriber> list = new ArrayList<Subscriber>();
		// TODO Auto-generated method stub
		String methodName = "getSubscriber::";
		Integer userFlag = 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -2);
		Date dateBefore2Days = cal.getTime();
		System.out.println("dateBefore2Days:" + dateBefore2Days);

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);
			criteria.setCacheable(true);

			criteria = getNewApprovedSearchCriteria(searchSubscriberDto, criteria);

			// criteria.add(org.hibernate.criterion.Expression.isNotNull("activationTime"));
			criteria.add(org.hibernate.criterion.Expression.eq("userFlag", userFlag));
			// criteria.add(org.hibernate.criterion.Expression.eq("physicalFormStatus",
			// physicalFormStatus));

			criteria.add(Restrictions.lt("createdOn", dateBefore2Days));
			// criteria.add(Restrictions.lt("createdOn", new Date()));

			if (searchSubscriberDto.getStartIndex() > 0)
				criteria.setFirstResult(searchSubscriberDto.getStartIndex());
			if (searchSubscriberDto.getSlot() > 0)
				criteria.setMaxResults(searchSubscriberDto.getSlot());

			criteria.addOrder(Order.desc("createdOn"));
			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	private Criteria getNewApprovedSearchCriteria(SearchSubscriberDto parameterMap, Criteria criteria) {
		String physicalFormStatus = "PENDING";

		criteria.createAlias("subscriberDetails", "subscriberDetails");
		System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());

		if (parameterMap.getKycTansactionId() != null && parameterMap.getKycTansactionId().trim().length() > 0) {
			criteria.add(Restrictions.like("kycTansactionId", parameterMap.getKycTansactionId(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().trim().length() > 0) {
			criteria.add(Restrictions.like("msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getEmail() != null && parameterMap.getEmail().trim().length() > 0) {
			criteria.add(Restrictions.like("subscriberDetails.email", parameterMap.getEmail(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getCaseType() != null && parameterMap.getCaseType().trim().length() > 0) {
			System.out.println("parameterMap.getCaseType()==" + parameterMap.getCaseType());
			criteria.add(Restrictions.like("caseType", parameterMap.getCaseType(), MatchMode.ANYWHERE));
		}
		/*
		 * if (parameterMap.getIsOldSubscriber() != null)
		 * criteria.add(Restrictions.eq("isOldSubscriber",
		 * parameterMap.getIsOldSubscriber()));
		 */
		/*
		 * if (parameterMap.getIsOldUserDetails() != -99) {
		 * criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails",
		 * parameterMap.getIsOldUserDetails())); }
		 */
		criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", 0));
		criteria.add(Restrictions.eq("subscriberDetails.physicalFormStatus", physicalFormStatus));

		return criteria;
	}

	@Override
	public SubscriberDetails getApprovedSubscriber(Map paramMap) throws KycDaoException {
		// TODO Auto-generated method stub
		String methodName = "getApprovedSubscriber::";

		SubscriberDetails subscriberDetail = null;

		Subscriber subscriber = new Subscriber();

		logger.debug(methodName + " getApprovedSubscriber paramMap subsdetailsid " + paramMap);

		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Subscriber.class);

			criteria = getApprovedSearchCriteria(paramMap, criteria);

			subscriber = (Subscriber) criteria.uniqueResult();
			for (SubscriberDetails subscriberDetails : subscriber.getSubscriberDetails()) {
				if (subscriberDetails.getIsOldUserDetails() == 0
						&& "APPROVED".equals(subscriberDetails.getFinalStatus())) {
					subscriberDetail = subscriberDetails;
					logger.debug(methodName + " getApprovedSubscriber if subsdetailsid "
							+ subscriberDetails.getSubscriberDetailsId());
					break;
				} else {
					logger.debug(methodName + " getApprovedSubscriber else subsdetailsid "
							+ subscriberDetails.getSubscriberDetailsId());
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return subscriberDetail;
	}

	private Criteria getApprovedSearchCriteria(Map<String, Object> paramMap, Criteria criteria) {

		logger.debug("getApprovedSubscriber:");

		criteria.createAlias("subscriberDetails", "subscriberDetails");
		criteria.add(Restrictions.eq("subscriberId", paramMap.get("subscriberId")));
		criteria.add(Restrictions.eq("subscriberDetails.isOldUserDetails", 0));
		criteria.add(Restrictions.eq("subscriberDetails.finalStatus", "APPROVED"));

		return criteria;
	}

	public int updateQuery(SubscriberDetails subscriberDetail) throws KycDaoException {

		logger.debug("updateQuery..getApprovedSubscriber.executing...");
		int updateCount = 0;
		Session session = null;
		try {
			session = this.sessionFactory.openSession();

			SQLQuery query = (SQLQuery) session.createSQLQuery(
					"update subscriber_details set is_old_user_details=" + subscriberDetail.getIsOldUserDetails()
							+ " where subscriber_details_id=" + subscriberDetail.getSubscriberDetailsId());
			updateCount = query.executeUpdate();
			logger.debug("updateCount...updateCount.getApprovedSubscriber.." + updateCount);
		} catch (NoResultException ex) {
			logger.debug("EXCEPTION ENTITY CLASS getApprovedSubscriber" + ExceptionUtils.getStackTrace(ex));
			return 0;
		} catch (Exception ex) {
			logger.debug("EXCEPTION ENTITY CLASS getApprovedSubscriber " + ExceptionUtils.getStackTrace(ex));
			ex.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
		return updateCount;
	}

	@Override
	public UserDistrict getUserDistrict(Integer userId) throws KycDaoException {		
		/*Session session = null;		
		UserDistrict userDistrict=null;
		
		try{
		session = this.sessionFactory.openSession();
	    //String queryName="select u from UserDistrict u where userId=:userId";
		Query query = (Query) session.getNamedQuery("UserDistrict.findUserById");
		query.setParameter(userId, userId);

		userDistrict=(UserDistrict) query.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userDistrict;*/
		
		Session session = null;
		Transaction transaction = null;
		SQLQuery query = null;
		//List list1 = null;
		Integer id=0;
		UserDistrict userDistrict=null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String sql = "select * from user_district sub where sub.user_id = "	+ userId ;

			query = session.createSQLQuery(sql);
			//Query sqlQueryObject = session.createSQLQuery(sql);
			query.addEntity(UserDistrict.class);
			userDistrict = (UserDistrict) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return userDistrict;
		
	}

	@Override
	public UserProvince getUserProvince(Integer userId) throws KycDaoException {
		/*Session session = null;		
		UserProvince userProvince=null;
		try{
		session = this.sessionFactory.openSession();
	    //String queryName="select u from UserProvince u where userId=:userId";
		Query query = (Query) session.getNamedQuery("UserProvince.findUserById");
		query.setParameter(userId, userId);

		userProvince=(UserProvince) query.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userProvince;*/
		Session session = null;
		Transaction transaction = null;
		SQLQuery query = null;
		//List list1 = null;
		Integer id=0;
		UserProvince userProvince=null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String sql = "select * from user_province sub where user_id = "	+ userId ;

			query = session.createSQLQuery(sql);
			//Query sqlQueryObject = session.createSQLQuery(sql);
			query.addEntity(UserProvince.class);
			userProvince = (UserProvince) query.uniqueResult();

					/*.addScalar("provinceId", IntegerType.INSTANCE)
									
					.setResultTransformer(Transformers.aliasToBean(ProvinceIds.class));
			id= (Integer) sqlQueryObject.uniqueResult();*/
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return userProvince;
	}

	@Override
	public UserVillage getUserVillage(Integer userId) throws KycDaoException {
		/*Session session = null;		
		UserVillage userVillage=null;
		try{
		session = this.sessionFactory.openSession();
	    //String queryName="select u from UserVillage u where userId=:userId";
		Query query = (Query) session.getNamedQuery("UserVillage.findUserById");
		query.setParameter(userId, userId);

		userVillage=(UserVillage) query.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userVillage;*/
		Session session = null;
		Transaction transaction = null;
		SQLQuery query = null;
		//List list1 = null;
		Integer id=0;
		UserVillage userVillage=null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String sql = "select * from user_village sub where sub.user_id = "	+ userId ;

			query = session.createSQLQuery(sql);
			//Query sqlQueryObject = session.createSQLQuery(sql);
			query.addEntity(UserVillage.class);
			userVillage = (UserVillage) query.uniqueResult();

					/*.addScalar("provinceId", IntegerType.INSTANCE)
									
					.setResultTransformer(Transformers.aliasToBean(ProvinceIds.class));
			id= (Integer) sqlQueryObject.uniqueResult();*/
			
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return userVillage;
	}
	
	
	
	
}