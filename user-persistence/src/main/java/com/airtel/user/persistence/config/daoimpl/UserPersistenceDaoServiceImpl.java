package com.airtel.user.persistence.config.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.persistence.config.dao.CustomHibernateDao;
import com.airtel.user.persistence.config.dao.UserDaoService;
import com.airtel.user.persistence.entities.BaseEntity;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@SuppressWarnings("rawtypes")
@Repository
@Qualifier("userDaoSession")
public class UserPersistenceDaoServiceImpl<T extends BaseEntity> extends
		CustomHibernateDao implements UserDaoService {

	private static Logger logger = Logger
			.getLogger(UserPersistenceDaoServiceImpl.class);

	@Override
	public BaseEntity saveOrUpdateEntity(BaseEntity baseEntity)
			throws UserDaoException {// TODO Auto-generated method stub
		String methodName = "saveOrUpdateEntity ::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		BaseEntity base = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			// session.saveOrUpdate(baseEntity);
			base = (BaseEntity) session.merge(baseEntity);
			transaction.commit();

		} catch (ConstraintViolationException e) {
			transaction.rollback();
			e.printStackTrace();
			logger.error(methodName + e.getMessage());
			logger.error(methodName + e.getCause());
			throw new UserDaoException(e.getCause(), "Data already exist.");
		} catch (HibernateException e) {
			// logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			logger.error(methodName + e.getMessage());
			logger.error(methodName + e.getCause());
			e.printStackTrace();
			throw new UserDaoException(e.getCause(), e.getMessage());
		} catch (Exception e) {
			// logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e.getCause(), e.getMessage());
		} finally {
			session.close();

		}
		return base;
	}


	@Override
	public void updateEntity(BaseEntity baseEntity) throws UserDaoException {
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
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void removeEntity(BaseEntity baseEntity) throws UserDaoException {
		String methodName = "saveEntity ::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			baseEntity.setStatusFlag(UserConstants.FALSE);
			baseEntity.setDeleteFlag(UserConstants.TRUE);
			session.merge(baseEntity);
			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getEntitySize(Class clazz, Map params)
			throws UserDaoException {
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
				Set<String> keySet=params.keySet();
				
				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, params.get(eachParam)));
				}
			}
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        criteria.setProjection(Projections.rowCount());
	        resultCount = (Long)criteria.uniqueResult();
	        
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}
		logger.debug(methodName + "ends.");
		return resultCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List findByCriteria(Class clazz, Map parameterMap)
			throws UserDaoException {
		String methodName = "findByCriteria::" + clazz;
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;

		List<? extends BaseEntity> baseEntity = null;
		
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			
			if (parameterMap !=null && !parameterMap.isEmpty()) {
				Set<String> keySet=parameterMap.keySet();
				
				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
				}
			}
			
			baseEntity = criteria.list();
			
			transaction.commit();

		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		logger.debug(methodName + "ends.");
		return (List<? extends BaseEntity>) baseEntity;
	}

	@Override
	public BaseEntity getEntityByCriteria(Class clazz, Map parameterMap)
			throws UserDaoException {
		// TODO Auto-generated method stub
		String methodName = "getEntityByCriteria::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		BaseEntity baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			if (parameterMap != null &&  !parameterMap.isEmpty()) {
				Set<String> keySet=parameterMap.keySet();
				for (String eachParam : keySet) {
					criteria.add(Restrictions.eq(eachParam, parameterMap.get(eachParam)));
				}
			}
			baseEntity = (BaseEntity) criteria.uniqueResult();
			transaction.commit();
		} 
		catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		logger.debug(methodName + "ends.");
		return baseEntity;
	}

	@Override
	public List<? extends BaseEntity> findByCriteria(Class clazz, Map parameterMap,
			int offSet, int startIndex) throws UserDaoException {
		String methodName = "getCustomersByTransaction::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			
			if (parameterMap != null && !parameterMap.isEmpty()) {
				Set<String> keySet=parameterMap.keySet();
				
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
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return baseEntity;
	}


	@Override
	public BaseEntity getEntityByNamedQuery(String namedQuery, Map parameterMap) throws UserDaoException {
		String methodName = "getCustomersByTransaction::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		BaseEntity baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.getNamedQuery(namedQuery);
			
			if (parameterMap != null &&  !parameterMap.isEmpty()) {
				Set<String> keySet=parameterMap.keySet();
				for (String eachParam : keySet) {
					query.setParameter(eachParam, parameterMap.get(eachParam));
				}
			}
			
			baseEntity = (BaseEntity) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return baseEntity;
	}


	@Override
	public List findByNamedQuery(String namedQuery, Map parameterMap) throws UserDaoException {
		
		String methodName = "getCustomersByTransaction::";
		logger.debug(methodName + "starts.");
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> baseEntity = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Query query = session.getNamedQuery(namedQuery);
			
			if (parameterMap != null &&  !parameterMap.isEmpty()) {
				Set<String> keySet=parameterMap.keySet();
				for (String eachParam : keySet) {
					query.setParameter(eachParam, parameterMap.get(eachParam));
				}
			}
			
			baseEntity = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} catch (Exception e) {
			logger.error(methodName + e.getStackTrace());
			transaction.rollback();
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return baseEntity;
	}


	@Override
	public Long findByCriteria(Class clazz) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		Long resultCount = 0L;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
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


	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoleList() throws UserDaoException {
		
		Session session = null;
		//Transaction transaction = null;
		List<Role> roleList = new ArrayList<Role>();
		try {
			session = this.sessionFactory.openSession();
			//transaction = session.beginTransaction();
			//String queryname ="select * from Role where parent_role_id<1 or parent_role_id in(37,38,43) ";
			String queryname ="select * from Role where parent_role_id<1 or parent_role_id in(37,38,43) ";
			
			Query query = session.createSQLQuery(queryname).addEntity(Role.class);	
			
			roleList = query.list();

			//transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRole() throws UserDaoException {
		
		Session session = null;
		//Transaction transaction = null;
		List<Role> roleList = new ArrayList<Role>();
		try {
			session = this.sessionFactory.openSession();
			//transaction = session.beginTransaction();
			String queryname ="select * from Role where parent_role_id>0";
			
			Query query = session.createSQLQuery(queryname).addEntity(Role.class);	
			
			roleList = query.list();

			//transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return roleList;
	}


	@Override
	public List findSubRoleList(Integer roleId) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> roleList = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryname ="Select r from Role r where parentRoleId =:roleId";
			
			Query query = session.createQuery(queryname);
			query.setParameter("roleId", roleId);
			
			roleList = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return roleList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List getRolesWithRoleIds() throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> roleList = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			//String queryname ="Select r from Role r where parentRoleId =:roleId";
			
			//Query query = session.createQuery(queryname);
            String queryname ="select * from role where  ROLE_ID!=21 and PARENT_ROLE_ID=0  or ROLE_ID=PARENT_ROLE_ID or ROLE_ID=43 or ROLE_ID=38 or ROLE_ID=39 or ROLE_ID=40 or ROLE_ID=41 or ROLE_ID=42";
			//String queryname ="select * from role where  PARENT_ROLE_ID=0  or ROLE_ID=PARENT_ROLE_ID ";
			
			Query query = session.createSQLQuery(queryname).addEntity(Role.class);	
			//query.setParameter("roleId", 0);
			
			roleList = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getSubRolesWithRoleIds(int parentRoleId) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> roleList = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryname ="Select r from Role r where parentRoleId !=:roleId and parentRoleId=:parentRoleId";
			
			Query query = session.createQuery(queryname);
			query.setParameter("roleId", 0);
			query.setParameter("parentRoleId", parentRoleId);
			
			roleList = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return roleList;
	}


	@Override
	public Users getUsers(String auuid) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		Users user = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryname ="Select u from Users u where auuid=:auuid";
			
			Query query = session.createQuery(queryname);
			query.setParameter("auuid", auuid);
			
			user = (Users) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return user;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List findByAuuId(String auuid) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		List<? extends BaseEntity> userList = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryname ="Select u from Users u where auuid=:auuid";
			
			Query query = session.createQuery(queryname);
			query.setParameter("auuid", auuid);
			
			userList = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return userList;
	}


	@Override
	public Users findRoleByUserName(String userName) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		Users user = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryname ="Select u from Users u where userName=:userName";
			
			Query query = session.createQuery(queryname);
			query.setParameter("userName", userName);
			
			user = (Users) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return user;
	}


	@Override
	public Users findRoleByUserId(Integer userId) throws UserDaoException {
		Session session = null;
		Transaction transaction = null;
		Users user = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			String queryname ="Select u from Users u where userId=:userId";
			
			Query query = session.createQuery(queryname);
			query.setParameter("userId", userId);
			
			user = (Users) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			
			throw new UserDaoException(e);
		} catch (Exception e) {
			
			throw new UserDaoException(e);
		} finally {
			session.close();
		}

		return user;
	}

	
}
