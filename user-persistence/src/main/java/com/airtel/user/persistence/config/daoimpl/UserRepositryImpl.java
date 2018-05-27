package com.airtel.user.persistence.config.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.persistence.config.dao.UserRepositry;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.UserDetails;
import com.airtel.user.persistence.entities.UserIdDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@Repository
@Qualifier("userRepositry")
public class UserRepositryImpl extends UserPersistenceDaoServiceImpl<Users> implements UserRepositry {

	@Override
	public List<Users> searchUser(SearchParamDto parameterMap, int offSet, int startIndex) throws UserDaoException {
		List<Users> list = new ArrayList<Users>();
		// TODO Auto-generated method stub
		String methodName = "searchUser::";
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Users.class);
			criteria.setCacheable(true);
			criteria = getSearchCriteria(parameterMap, criteria);
			if (startIndex > 0)
				criteria.setFirstResult(startIndex);
			if (offSet > 0)
				criteria.setMaxResults(offSet);
			list = criteria.list();
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
	public Users getUserById(int id) throws UserDaoException {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("userId", id);
		Users users = (Users)getEntityByCriteria(Users.class, parameterMap);
		if (users != null) {
			users.setUserDetails(null);
		}

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetails> getUserDetails(int userId, Integer isActive) throws UserDaoException {
		List<UserDetails> list = new ArrayList<UserDetails>();
		// TODO Auto-generated method stub
		
		Users users = new Users();
		users.setUserId(userId);
		
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("users", users);
		
		if (isActive != null && (isActive == UserConstants.TRUE || isActive == UserConstants.FALSE)) {
			parameterMap.put("isOldUserDetails", isActive);
		}
		
		list = (List<UserDetails>)findByCriteria(UserDetails.class, parameterMap);
		
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserIdDetails> getUserIdDetails(int userDeatilsId, Integer isOldIdDetails) throws UserDaoException {
		List<UserIdDetails> list = new ArrayList<UserIdDetails>();
		// TODO Auto-generated method stub
		
		UserDetails userDetails = new UserDetails();
		userDetails.setUserDetailsId(userDeatilsId);
		
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("userDetails", userDetails);
		
		if (isOldIdDetails != null && (isOldIdDetails == UserConstants.TRUE || isOldIdDetails == UserConstants.FALSE)) {
			parameterMap.put("isOldIdDetails", isOldIdDetails);
		}
		
		list = (List<UserIdDetails>)findByCriteria(UserIdDetails.class, parameterMap);
		
		return list;
	}

	@Override
	public void assignRole(int uid, Role role) throws UserDaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Role> getRoles(int uid) throws UserDaoException {
		
		String namedQuery="rom role where role_id in (select role_id from user_roles where user_id =:uid ) ";
		
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("uid", uid);
		
		List<Role> list = findByNamedQuery(namedQuery, parameterMap);
				
		return list;
	}

	@Override
	public Users saveOrUpdateUser(Users users) throws UserDaoException {
		return (Users) saveOrUpdateEntity(users);
	}

	@Override
	public UserDetails saveOrUpdateUserDetails(UserDetails userDetails) throws UserDaoException {
		return (UserDetails) saveOrUpdateEntity(userDetails);

	}

	@Override
	public long getTotalUser(SearchParamDto parameterMap) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Users.class);
			criteria = getSearchCriteria(parameterMap, criteria);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        criteria.setProjection(Projections.rowCount());
	        resultCount = (Long)criteria.uniqueResult();
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
	
	
	private Criteria getSearchCriteria(SearchParamDto parameterMap, Criteria criteria) {
		// Criterion criterion = Restrictions.
		if (parameterMap.getUid() > 0) {
			criteria.add(org.hibernate.criterion.Expression.eq("userId", parameterMap.getUid()));
		}
		criteria.add(org.hibernate.criterion.Expression.eq("isDeleted", 0));
		
		criteria.createAlias("userDetails", "userDetails");
		if (parameterMap.getIsOldDetail() != -99) {
			criteria.add(Restrictions.eq("userDetails.isOldUserDetails", parameterMap.getIsOldDetail()));
		}
		if (parameterMap.getEmail() != null && parameterMap.getEmail().length() >0) {
			criteria.add(Restrictions.like("userDetails.email", parameterMap.getEmail(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getName() != null && parameterMap.getName().length() >0) {
			Criterion lhs = Restrictions.ilike("userDetails.firstName", parameterMap.getName(), MatchMode.ANYWHERE);
			Criterion rhs = Restrictions.ilike("userDetails.lastName", parameterMap.getName(), MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(lhs, rhs));
			/*
			 * criteria.add(org.hibernate.criterion.Expression.a(
			 * "userDetails.isOldUserDetails", parameterMap.getIsOldDetail()));
			 */
		}
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().length() >0) {
			criteria.add(Restrictions.like("userDetails.msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().length() >0) {
			criteria.add(Restrictions.like("userDetails.msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		if(parameterMap.getRoleId() !=null){
			
		}
		return criteria;
	}

	@Override
	public Users getUserByName(String userName) throws UserDaoException {
		List<Users> list = new ArrayList<Users>();
		// TODO Auto-generated method stub
		String methodName = "searchUser::";
		Session session = null;
		Transaction transaction = null;
		Users users = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(org.hibernate.criterion.Expression.eq("userName", userName));
			list = criteria.list();
			if (list != null && list.size() > 0) {
				users = list.get(0);
				List<UserDetails> userDetails = users.getUserDetails();
				users.setUserDetails(users.getUserDetails());
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
		return users;
	}
	
	@Override
	public Users getUserByMsisdn(String userName) throws UserDaoException {
		String methodName = "searchUser::";
		Session session = null;
		Transaction transaction = null;
		Users users = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(org.hibernate.criterion.Expression.eq("userName", userName));
			users = (Users) criteria.uniqueResult();
			
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
		return users;
	}

	@Override
	public boolean saveClientUser(ClientOauthDetails clientOauthDetails) throws UserDaoException {
		ClientOauthDetails clientOauthDetails2 = (ClientOauthDetails) saveOrUpdateEntity(clientOauthDetails);
		if(clientOauthDetails2 == null)
			return false;
		return true;
	}

	@Override
	public Role getRoleByUserId(Integer roleId) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		Role role = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			String queryName="select r from Role r where roleId=:roleId";
			Query query =session.createQuery(queryName).setCacheable(true);			
			query.setParameter("roleId", roleId);
			role=(Role) query.uniqueResult();

			//return   (Role) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			//transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			//transaction.rollback();
		} finally {
			session.close();
		}
		return role;
	}

	@Override
	public UserDetails getUserDetailByMsisdn(String msisdn) {
		Session session = null;
		Transaction transaction = null;
		UserDetails userDetails = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			String queryName="select u from UserDetails u where msisdn=:msisdn";
			Query query =session.createQuery(queryName);			
			query.setParameter("msisdn", msisdn);
			userDetails=(UserDetails) query.uniqueResult();

			//return   (Role) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			//transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			//transaction.rollback();
		} finally {
			session.close();
		}
		return userDetails;
	}

	@Override
	public UserDetails saveOrUpdateUser(UserDetails userDetail) throws UserDaoException {
		return (UserDetails) saveOrUpdateEntity(userDetail);
		
	}

	@Override
	public Users getUserByAuuId(String auuid) throws UserDaoException {
		//Users Users = new Users();		
		String methodName = "searchUser::";
		Session session = null;
		Transaction transaction = null;
		Users users = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(org.hibernate.criterion.Expression.eq("auuid", auuid));
			users = (Users) criteria.uniqueResult();
			
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
		return users;
	}

	@Override
	public UserDetails getUserDetails(Date dob, String idCardNo, String msisdn) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		UserDetails userDetails = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			String queryName="select u from UserDetails u where idCardNo=:idCardNo and dob=:dob and msisdn=:msisdn";
			Query query =session.createQuery(queryName);			
			query.setParameter("dob", dob);
			query.setParameter("idCardNo", idCardNo);
			query.setParameter("msisdn", msisdn);
			userDetails=(UserDetails) query.uniqueResult();

			//return   (Role) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			//transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			//transaction.rollback();
		} finally {
			session.close();
		}
		return userDetails;
	}

	/**
	 * Evicts all second level cache hibernate entites. This is generally only
	 * needed when an external application modifies the databaase.
	 */
	public void evict2ndLevelCache() {
		Session session = null;
	    try {
	        Map<String, ClassMetadata> classesMetadata = sessionFactory.getAllClassMetadata();
	        for (String entityName : classesMetadata.keySet()) {
	            System.out.println("Evicting Entity from 2nd level cache: " + entityName);
	            session = this.sessionFactory.openSession();
	            session.evict(entityName);
	        }
	    } catch (Exception e) {
	    	System.out.println("Error evicting 2nd level hibernate cache entities: ");
	    }
	}

	@Override
	public UserIdDetails saveOrUpdateUserIdDetails(UserIdDetails userIdDetails) throws UserDaoException {
		return (UserIdDetails) saveOrUpdateEntity(userIdDetails);
	}

	private UserIdDetails saveOrUpdateEntity(UserIdDetails userIdDetails) {
		return (UserIdDetails) saveOrUpdateEntity(userIdDetails);
	}

	/*@Override
	public Users getUserByUserName(String user_name) throws UserDaoException {
		
		Session session = null;
		Transaction transaction = null;
		SQLQuery query = null;
		//List list1 = null;
		Integer id=0;
		Users Users=null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String sql = "select user_name from users u where user_name = "	+ user_name + " or auuid = " + user_name ;

			query = session.createSQLQuery(sql);
			
			query.addEntity(Users.class);
			Users = (Users) query.uniqueResult();

			
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return Users;
	}*/

	
	
	

	
	

}
