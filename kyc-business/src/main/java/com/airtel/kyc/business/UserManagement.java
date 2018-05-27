package com.airtel.kyc.business;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.CommissionRuleDto;
import com.airtel.kyc.helpers.dto.HelpAndSupportDto;
import com.airtel.kyc.helpers.dto.TokenResponse;
import com.airtel.kyc.helpers.response.UserResponse;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserManagement {

	public BaseResponse<?> searchUser(SearchParamDto bean, int slot,
			int startIndex) throws BusinessException,SystemException ;

	public  BaseResponse<?> provisionUser(UsersDto bean) throws BusinessException,SystemException, IOException ;
	
	public BaseResponse<?> isMSISDNAvailable(String msisdn) throws BusinessException,SystemException ;

	public BaseResponse<?> deleteUser(UsersDto userData)throws BusinessException,SystemException ;

	public BaseResponse<?> blockUser(UsersDto userData)throws BusinessException,SystemException ;
	
	public ResponseEntity<byte[]> registerBlukUser(MultipartFile file) throws BusinessException,SystemException;
	
	public BaseResponse<?> updateUserPassword(int uid, String oldPassword, String newPassword, String confirmPassword ) throws BusinessException,SystemException, UserException;

	public BaseResponse<?> isUserAvailbleByAuuId(String auuid)throws BusinessException,SystemException;

	public BaseResponse<?> generatePassword(UsersDto userData)throws BusinessException,SystemException, IOException;

	public BaseResponse<?> validateOtp(UsersDto userData)throws BusinessException,SystemException;

	public TokenResponse validateUser(UsersDto userData)throws BusinessException,SystemException, JsonProcessingException, UserDaoException;

	public BaseResponse<?> forgotPassword(UserDetailsDto userDetailsDto)throws BusinessException,SystemException, IOException;

	public BaseResponse<?> resetPassword(UsersDto userData)throws BusinessException,SystemException;

	public BaseResponse<?> saveCommissionRule(CommissionRuleDto commissionRuleDto)throws BusinessException,SystemException;

	public BaseResponse<UserResponse> getCommissionRule(CommissionRuleDto commissionRuleDto)throws BusinessException,SystemException;

	public BaseResponse<UserResponse> saveAndSendHelpAndSupportData(HelpAndSupportDto helpAndSupportDto);
	
	public BaseResponse<?> isMsisdnAvailable(String msisdn)throws BusinessException,SystemException ;
	
	
	
	/*public BaseResponse<?> getUserById(int uid) throws BusinessException,SystemException ;	

	public BaseResponse<?> updateUser(int uid, UsersDto bean) throws BusinessException,SystemException;
	
	public BaseResponse<?> updateUserActiveStatus(int uid, boolean isActive) throws BusinessException,SystemException;	
	
	public BaseResponse<?> getBypassStatus() throws BusinessException,SystemException;

	public BaseResponse<?> updateBypass(Integer isByPassflag) throws BusinessException,SystemException;
	
	public BaseResponse<?> getUserByName(String userName) throws BusinessException,SystemException ;
	
	*/
	
	
}
