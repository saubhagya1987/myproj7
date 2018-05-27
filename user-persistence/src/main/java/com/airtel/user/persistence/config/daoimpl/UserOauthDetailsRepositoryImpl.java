package com.airtel.user.persistence.config.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.airtel.user.persistence.config.dao.UserOauthDetailsRepository;
import com.airtel.user.persistence.entities.UserDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@Repository
@Qualifier("userOauthDetailsRepository")
public class UserOauthDetailsRepositoryImpl extends UserPersistenceDaoServiceImpl<Users> implements UserOauthDetailsRepository {

	@Override
	public Users loadUserByName(String userName) throws UserDaoException {
		// TODO Auto-generated method stub
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

}
