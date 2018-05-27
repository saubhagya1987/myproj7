package com.airtel.kyc.repository.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
//import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
//import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.airtel.kyc.repository.dao.GenericDao;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.persistence.entities.Users;

/**
 * @author Saubhagya
 *
 * @param <E>
 * @param <K>
 */

@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends E> daoType;

	private Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

	/**
	 * By defining this class as abstract, we prevent Spring from creating
	 * instance of this class If not defined as abstract,
	 * getClass().getGenericSuperClass() would return Object. There would be
	 * exception because Object class does not have a constructor with
	 * parameters.
	 */
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void add(E entity) {
		currentSession().save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveOrUpdate(E entity) {
		try {
			//currentSession().clear();
			//currentSession().delete(entity);
			//currentSession().evict(entity);			
			currentSession().saveOrUpdate(entity);
			//currentSession().flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void update(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public void remove(E entity) {
		currentSession().delete(entity);
	}

	@Override
	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	@Override
	public List<E> getAll() {
		return currentSession().createCriteria(daoType).list();
	}

	@Override
	@Transactional
	public E findSingleResultByNamedQuery(String queryName, Map<String, Object> fieldValue) {
		Session session = sessionFactory.openSession();
		try {
			Query query = (Query) session.getNamedQuery(queryName).setCacheable(true);
			Set<Entry<String, Object>> entrySet = fieldValue.entrySet();

			for (Entry<String, Object> entry : entrySet)
				query.setParameter(entry.getKey(), entry.getValue());

			return (E) query.uniqueResult();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public List<E> findResultsByNamedQuery(String queryName, Map<String, Object> fieldValue) {
		Session session = sessionFactory.openSession();
		try {
			Query query = (Query) session.getNamedQuery(queryName).setCacheable(true);
			Set<Entry<String, Object>> entrySet = fieldValue.entrySet();

			for (Entry<String, Object> entry : entrySet)
				query.setParameter(entry.getKey(), entry.getValue());

			return query.list();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return Collections.emptyList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public E findSingleResultByHqlQuery(String queryName, Map<String, Object> fieldValue) {
		Session session = sessionFactory.openSession();
		try {
			Query query = (Query) session.createQuery(queryName).setCacheable(true);
			Set<Entry<String, Object>> entrySet = fieldValue.entrySet();

			for (Entry<String, Object> entry : entrySet)
				query.setParameter(entry.getKey(), entry.getValue());

			return (E) query.uniqueResult();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public List<E> findResultsByHQLQuery(String queryName, Map<String, Object> fieldValue) {
		Session session = sessionFactory.openSession();
		try {
			Query query = (Query) session.createQuery(queryName).setCacheable(true);
			Set<Entry<String, Object>> entrySet = fieldValue.entrySet();

			for (Entry<String, Object> entry : entrySet)
				query.setParameter(entry.getKey(), entry.getValue());

			return query.list();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return Collections.emptyList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	@Transactional
	public List<E> findResultsByHQLINQuery(String queryName, List<Integer> list) {
		Session session = sessionFactory.openSession();
		try {
			Query query = (Query) session.createQuery(queryName).setCacheable(true);
			query.setParameterList("list", list);
			return query.list();

		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return Collections.emptyList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	public List<Users> findPaginatedResultsByNamedQuery(SearchParamDto parameterMap, Integer startOffset,
			Integer maxResult) {
		Session session = sessionFactory.openSession();
		List<Users> list = new ArrayList<Users>();
		try {
			Criteria criteria = session.createCriteria(Users.class);
			criteria.setCacheable(true);
			criteria = getSearchCriteria(parameterMap, criteria);
			if (startOffset > 0)
				criteria.setFirstResult(startOffset);
			if (maxResult > 0)
				criteria.setMaxResults(maxResult);
			list = criteria.list();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return Collections.emptyList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return list;

	}

	private Criteria getSearchCriteria(SearchParamDto parameterMap, Criteria criteria) {
		// Criterion criterion = Restrictions.
		if (parameterMap.getUid() > 0) {
			criteria.add(org.hibernate.criterion.Expression.eq("userId", parameterMap.getUid()));
		}
		criteria.createAlias("userDetails", "userDetails");
		if (parameterMap.getIsOldDetail() != -99) {
			criteria.add(Restrictions.eq("userDetails.isOldUserDetails", parameterMap.getIsOldDetail()));
		}
		if (parameterMap.getEmail() != null && parameterMap.getEmail().length() > 0) {
			criteria.add(Restrictions.like("userDetails.email", parameterMap.getEmail(), MatchMode.ANYWHERE));
		}
		if (parameterMap.getName() != null && parameterMap.getName().length() > 0) {
			Criterion lhs = Restrictions.ilike("userDetails.firstName", parameterMap.getName(), MatchMode.ANYWHERE);
			Criterion rhs = Restrictions.ilike("userDetails.lastName", parameterMap.getName(), MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(lhs, rhs));
			/*
			 * criteria.add(org.hibernate.criterion.Expression.a(
			 * "userDetails.isOldUserDetails", parameterMap.getIsOldDetail()));
			 */
		}
		if (parameterMap.getMsisdn() != null && parameterMap.getMsisdn().length() > 0) {
			criteria.add(Restrictions.like("userDetails.msisdn", parameterMap.getMsisdn(), MatchMode.ANYWHERE));
		}
		return criteria;
	}

	@Override
	public E findSingleResultBySQLQuery(String queryName) {
		Session session = sessionFactory.openSession();
		try {
			Query query = (Query) session.createSQLQuery(queryName);
			/*
			 * Set<Entry<String, Object>> entrySet = fieldValue.entrySet();
			 * 
			 * for (Entry<String, Object> entry : entrySet)
			 * query.setParameter(entry.getKey(), entry.getValue());
			 */
			return (E) query.uniqueResult();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<E> findResultsBySQLQuery(String queryName,Object entity) {
		Session session = sessionFactory.openSession();
		try {
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryName);
			if(entity!=null){
				query.addEntity(entity.getClass());
			}
			return query.list();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public E findResultBySQLQuery(String queryName,Object entity) {
		Session session = sessionFactory.openSession();
		try {
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryName);
			if(entity!=null){
				query.addEntity(entity.getClass());
			}
			return (E) query.uniqueResult();
		} catch (NoResultException ex) {
			LOGGER.debug("EXCEPTION ENTITY CLASS " + ExceptionUtils.getStackTrace(ex));
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public E saveOrUpdateEntity(Object object){
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		E en = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			en = (E) session.merge(object);
			transaction.commit();

		} catch (ConstraintViolationException e) {
			transaction.rollback();
		} catch (HibernateException e) {
			// logger.error(methodName + e.getStackTrace());
			transaction.rollback();
		} catch (Exception e) {
			// logger.error(methodName + e.getStackTrace());
			transaction.rollback();
		} finally {
			session.close();
		}
		return en;
	}

}
