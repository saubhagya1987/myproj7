package com.airtel.kyc.business.service;



import java.io.IOException;
import java.util.List;

import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.helpers.dto.UserSearchResponseDto;
import com.airtel.user.helper.dto.LocationDto;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.entities.Users;

public interface UserManagementService {

	public UserSearchResponseDto searchUser(SearchParamDto bean, int slot, int startIndex) throws UserException;
	public List<UserDetailsDto> getUserDetails(UsersDto usersDto,Integer isActive) throws UserException;
	//public LocationDto getLocataion(Integer uid) throws UserException;
	public UsersDto provisionUser(UsersDto bean) throws UserException, IOException ;
	public boolean provisionAPIAcsess(UsersDto usersDto) throws UserException;
	public boolean provisionLDAPAcsess(UsersDto usersDto) throws UserException;
	public UsersDto getUserByName(String userName) throws UserException;
	public List<UsersDto> deleteUser(UsersDto userData)throws UserException;
	public UsersDto blockUser(UsersDto userData)throws UserException;	
	
	public UsersDto getUserById(int uid) throws UserException;
	public UsersDto updatePassword(int uid, UsersDto bean) throws UserException;
	public UsersDto authenticateUserByAuuId(String auuid, String password)throws UserException;
	public UsersDto generatePassword(UsersDto userData)throws UserException, IOException;
	public UsersDto validateOtp(UsersDto userData)throws UserException;
	public Users validateUser(UsersDto userData)throws UserException;
	public UserDetailsDto forgotPassword(UserDetailsDto userDetailsDto)throws UserException, IOException;
	public UsersDto resetPassword(UsersDto userData)throws UserException;
	
	public boolean updateAssignment(UsersDto userData)throws UserException;
	public UsersDto isMsisdnAvailable(String msisdn)throws UserException;
	
	/*public UsersDto getUserById(int uid) throws UserException;	

	public UsersDto updateUser(int uid, UsersDto bean) throws UserException ;	
	
	public Integer updateBypass(Integer isByPassflag) throws UserException;
	
	public Integer getBypassStatus(Integer isByPassflag) throws UserException;	
	
	public UsersDto transformSubscriberToUser(SubscriberDto subscriberDto) throws UserException;
	
	public UsersDto updatePassword(int uid, UsersDto bean) throws UserException ;*/
	
}
