package com.airtel.user.persistence.config.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.airtel.user.persistence.config.dao.ClientOauthDetailsRepository;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@Repository
@Qualifier("clientOauthDetailsRepository")
public class ClientOauthDetailsRepositoryImpl extends UserPersistenceDaoServiceImpl<Users> implements ClientOauthDetailsRepository{

	@Override
	public ClientOauthDetails getByClientId(String clientId) throws UserDaoException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;

		ClientOauthDetails clientOauthDetails = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(ClientOauthDetails.class);
			criteria.add(Restrictions.eq("client_id", clientId));
			clientOauthDetails = (ClientOauthDetails) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new UserDaoException(e);
		}catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e);
		} 
		finally {
			session.close();
		}
		return clientOauthDetails;
	}

	@Override
	public ClientOauthDetails getByDeviceId(String deviceId) throws UserDaoException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;

		ClientOauthDetails clientOauthDetails = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(ClientOauthDetails.class);
			criteria.add(Restrictions.eq("user.deviceId", deviceId));
			clientOauthDetails = (ClientOauthDetails) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new UserDaoException(e);
		}catch (Exception e) {
			transaction.rollback();
			throw new UserDaoException(e);
		} 
		finally {
			session.close();
		}
		return clientOauthDetails;
	}



}
