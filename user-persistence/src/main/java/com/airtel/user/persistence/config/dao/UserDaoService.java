package com.airtel.user.persistence.config.dao;

import java.util.List;
import java.util.Map;

import com.airtel.user.persistence.entities.BaseEntity;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

public interface UserDaoService<T extends BaseEntity> {

	public BaseEntity saveOrUpdateEntity(BaseEntity baseEntity)
			throws UserDaoException;

	public void updateEntity(BaseEntity baseEntity) throws UserDaoException;

	public void removeEntity(BaseEntity baseEntity) throws UserDaoException;	

	public long getEntitySize(Class<T> clazz, Map<String, Object> params)
			throws UserDaoException;

	public List<T> findByCriteria(Class<T> clazz,
			Map<String, Object> parameterMap) throws UserDaoException;
	
	/*public BaseEntity getEntityByCriteria(Class<T> clazz,
			Map<String, Object> parameterMap) throws UserDaoException;*/
	
	public BaseEntity getEntityByCriteria(Class<T> clazz,
			Map<String, Object> parameterMap) throws UserDaoException;
	
	public List<T> findByCriteria(Class<T> clazz, Map<String, Object> params,
			int offSet, int startIndex) throws UserDaoException;
	
	public Long findByCriteria(Class<?> clazz) throws UserDaoException;

	public BaseEntity getEntityByNamedQuery( String namedQuery,
			Map<String, Object> params) throws UserDaoException;

	public List<T> findByNamedQuery(String namedQuery,
			Map<String, Object> params) throws UserDaoException;

	public List<Role> findRoleList() throws UserDaoException;
	
	public List<Role> findRole() throws UserDaoException;

	public List<T> findSubRoleList(Integer roleId)throws UserDaoException;

	public List<Role> getRolesWithRoleIds()throws UserDaoException;
	
	public List<Role> getSubRolesWithRoleIds(int parentRoleId)throws UserDaoException;

	public Users getUsers(String auuId)throws UserDaoException;

	public List<Users> findByAuuId(String userName)throws UserDaoException;

	public Users findRoleByUserName(String userName)throws UserDaoException;
	
	public Users findRoleByUserId(Integer userId)throws UserDaoException;

	
	
}
