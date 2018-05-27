package com.airtel.user.persistence.config.dao;

import java.util.Date;
import java.util.List;

import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.UserDetails;
import com.airtel.user.persistence.entities.UserIdDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

public interface UserRepositry {

	public Users saveOrUpdateUser(Users users) throws UserDaoException;

	public UserDetails saveOrUpdateUserDetails(UserDetails userDetails)
			throws UserDaoException;
	
	

	public List<Users> searchUser(SearchParamDto parameterMap, int offSet,
			int startIndex) throws UserDaoException;
	
	public long getTotalUser(SearchParamDto parameterMap) throws UserDaoException;

	public Users getUserById(int id) throws UserDaoException;


	public List<UserDetails> getUserDetails(int userId, Integer isActive) throws UserDaoException;
	
	public List<UserIdDetails> getUserIdDetails(int userDetailsId, Integer isOldIdDetails) throws UserDaoException;

	public void assignRole(int uid, Role role) throws UserDaoException;

	public List<Role> getRoles(int uid) throws UserDaoException ;
	
	public Users getUserByName(String userName) throws UserDaoException;
	
	//public Users getUserByUserName(String userName)throws UserDaoException;
	
	public Users getUserByMsisdn(String userName) throws UserDaoException;
	
	public Users getUserByAuuId(String auuid) throws UserDaoException;
	

	public boolean saveClientUser(ClientOauthDetails clientOauthDetails) throws UserDaoException;

	public Role getRoleByUserId(Integer roleId)throws UserDaoException;

	public UserDetails getUserDetailByMsisdn(String msisdn);

	public UserDetails saveOrUpdateUser(UserDetails userDetail)throws UserDaoException;
	
	public UserIdDetails saveOrUpdateUserIdDetails(UserIdDetails userIdDetails)
			throws UserDaoException;

	public UserDetails getUserDetails(Date dob, String idNumber, String msisdn)throws UserDaoException;

	public void evict2ndLevelCache();
	

	

}
