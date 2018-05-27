package com.airtel.user.persistence.config.daoimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.airtel.user.persistence.config.dao.CustomHibernateDaoSupport;
import com.airtel.user.persistence.config.dao.UserDaoService;
import com.airtel.user.persistence.entities.BaseEntity;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@Repository
@Qualifier("userDaoTemplate")
public class UserDaoServiceImpl <T extends BaseEntity> extends CustomHibernateDaoSupport implements UserDaoService {

	@Override
	public BaseEntity saveOrUpdateEntity(BaseEntity baseEntity)
			throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateEntity(BaseEntity baseEntity) throws UserDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntity(BaseEntity baseEntity) throws UserDaoException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public BaseEntity getEntityByCriteria(Class clazz, Map parameterMap) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getEntitySize(Class clazz, Map params) throws UserDaoException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List findByCriteria(Class clazz, Map parameterMap) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List findByCriteria(Class clazz, Map params, int offSet, int startIndex) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BaseEntity getEntityByNamedQuery(String namedQuery, Map params) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List findByNamedQuery(String namedQuery, Map params) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findByCriteria(Class clazz) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List findRoleList() throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List findSubRoleList(Integer roleId) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List getRolesWithRoleIds() throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List getSubRolesWithRoleIds(int parentRoleId) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Users getUsers(String auuId) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List findByAuuId(String userName) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List findRole() throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Users findRoleByUserName(String userName) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Users findRoleByUserId(Integer userId) throws UserDaoException {
		// TODO Auto-generated method stub
		return null;
	}}
