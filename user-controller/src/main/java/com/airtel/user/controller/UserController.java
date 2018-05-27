package com.airtel.user.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.helpers.dto.EncryptedResponseDto;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.security.web.WebDecryption;
import com.airtel.user.business.UserBusinessService;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.PermissionDto;
import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.dto.ResponseDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.SearchDto;
import com.airtel.user.helper.dto.SearchResponseDto;
import com.airtel.user.helper.dto.TotalCountResponseDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.helper.util.BaseMessageSourceAware;
import com.airtel.user.persistence.exception.UserDaoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
@RequestMapping(value = { "/api" })
public class UserController extends BaseMessageSourceAware {
	
	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserBusinessService userBusinessService;
	
	@Autowired
	EncryptionRSA encryptionRSA;
	
	@Autowired
	WebDecryption webDecryption;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ObjectWriter objectWriter;
	
	@Autowired
	AesUtil aesUtil;
	
	@RequestMapping(value = "/user/registerUser", method = RequestMethod.POST, consumes = "application/json" , produces= "application/json")
	public @ResponseBody ResponseBeanDto<UsersDto> registerUser(Authentication authentication,
			@RequestBody UsersDto userDto) throws UserBusinessException  {
		String methodName = " User Register::";

		logger.error(methodName + " Start");
	
		ResponseBeanDto<UsersDto> responseBean = (ResponseBeanDto<UsersDto>) userBusinessService.registerUser(userDto);			

		return responseBean;
	}
	
	@RequestMapping(value = "/user/registerRole", method = RequestMethod.POST, consumes = "application/json", produces= "application/json")
	public @ResponseBody ResponseBeanDto<UsersDto> addRole(Authentication authentication,
			@RequestBody RoleDto roleDto) throws UserBusinessException  {
		 String methodName = " Role added in master  ::";
		logger.error(methodName + " Start");
		ResponseBeanDto<UsersDto> responseBean = (ResponseBeanDto<UsersDto>) userBusinessService.registerRole(roleDto);			
		return responseBean;
	}
		
	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json", produces= "application/json")
	public @ResponseBody ResponseBeanDto<SearchResponseDto> getUsers(Authentication authentication, @RequestParam int startIndex,
			@RequestParam int slotSize,
			@RequestBody SearchDto searchDto) throws UserBusinessException  {
		String methodName = " User Register::";
		ResponseBeanDto<SearchResponseDto> responseBean = new ResponseBeanDto<SearchResponseDto>();
		
		logger.error(methodName + " Start");
		this.userBusinessService.SearchUser(startIndex, slotSize
				, searchDto);
	
		//	ResponseBean<SearchDto> responseBean = (ResponseBean<UsersDto>) userBusinessService.registerUser(userDto);			

		return responseBean;
	}
	
	@RequestMapping(value = "/registerDepartment", method = RequestMethod.POST, consumes = "application/json", produces= "application/json")
	public @ResponseBody ResponseBeanDto<UsersDto> addDepartment(Authentication authentication,
			@RequestBody DepartmentDto departmentDto) throws UserBusinessException  {
		 String methodName = " Role added in master  ::";

		logger.error(methodName + " Start");
	
		ResponseBeanDto<UsersDto> responseBean = (ResponseBeanDto<UsersDto>) userBusinessService.registerDepartment(departmentDto);			

		return responseBean;
	}
	
	@RequestMapping(value = "/registerPermission", method = RequestMethod.POST, consumes = "application/json", produces= "application/json")
	public @ResponseBody ResponseBeanDto<UsersDto> addPermission(Authentication authentication,
			@RequestBody PermissionDto permissionDto) throws UserBusinessException  {
		 String methodName = " Role added in master  ::";

		logger.error(methodName + " Start");
	
		ResponseBeanDto<UsersDto> responseBean = (ResponseBeanDto<UsersDto>) userBusinessService.registerPermission(permissionDto);			

		return responseBean;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "users/getAllRoles/{slot}/{startIndex}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseBeanDto<SearchResponseDto> fetchRoles(Authentication authentication, @PathVariable int slot,
			@PathVariable int startIndex
			,@RequestBody RoleDto roleDto) throws UserBusinessException{ 
			// @RequestBody RoleDto roleDto) throws UserBusinessException{
			
		String methodName = " User fetchRoles::";
		ResponseBeanDto<SearchResponseDto> responseBean = new ResponseBeanDto<SearchResponseDto>();
		logger.error(methodName + " Start");
		responseBean = (ResponseBeanDto<SearchResponseDto>) this.userBusinessService.getRoles(roleDto,slot,startIndex);
		return responseBean;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "users/getAllDepartments/{slot}/{startIndex}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseBeanDto<SearchResponseDto> fetchDepartments(Authentication authentication, @PathVariable int slot,
			@PathVariable int startIndex
			,@RequestBody DepartmentDto departmentDto) throws UserBusinessException{ 
			// @RequestBody RoleDto roleDto) throws UserBusinessException{
			
		String methodName = " User fetchRoles::";
		ResponseBeanDto<SearchResponseDto> responseBean = new ResponseBeanDto<SearchResponseDto>();
		logger.error(methodName + " Start");
		responseBean = (ResponseBeanDto<SearchResponseDto>) this.userBusinessService.getDepartments(departmentDto, slot, startIndex);
		return responseBean;
	}
	
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userTotalCount", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseBeanDto<TotalCountResponseDto> fetchTotalCount(Authentication authentication			
		    ) throws UserBusinessException {
		String methodName = " User fetchTotalCount::";
		ResponseBeanDto<TotalCountResponseDto> responseBean = new ResponseBeanDto<TotalCountResponseDto>();
		logger.error(methodName + " Start");
		responseBean = (ResponseBeanDto<TotalCountResponseDto>) this.userBusinessService.getUserCount();
		return responseBean;
	}*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userTotalCount", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto fetchTotalCount(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto) throws UserBusinessException, JsonProcessingException, UnsupportedEncodingException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){						
			ResponseBeanDto<TotalCountResponseDto> responseBean = new ResponseBeanDto<TotalCountResponseDto>();			
			responseBean = (ResponseBeanDto<TotalCountResponseDto>) this.userBusinessService.getUserCount();
			
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);	*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}	
		
		else{
			ResponseBeanDto<TotalCountResponseDto> totalCountResponseDto=new ResponseBeanDto<TotalCountResponseDto>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			totalCountResponseDto.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(totalCountResponseDto);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllRolesWithSubRoles", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseBeanDto<List<RoleDto>> getAllRolesWithSubRoles(Authentication authentication) throws UserBusinessException, UserDaoException {
		return (ResponseBeanDto<List<RoleDto>>) userBusinessService.getAllRoles();
	}*/
	
	@RequestMapping(value = "/users/getAllRolesWithSubRoles", method = RequestMethod.GET, produces= "application/json")
	public @ResponseBody ResponseBeanDto<List<RoleDto>> getAllRolesWithSubRoles(Authentication authentication) throws UserBusinessException, UserDaoException  {
		return (ResponseBeanDto<List<RoleDto>>) userBusinessService.getAllRoles();
	}
	
	
	
	
}
