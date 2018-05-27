package com.airtel.user.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.helpers.dto.EncryptedResponseDto;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.security.web.WebDecryption;
import com.airtel.user.business.UserBusinessService;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.dto.ResponseDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.util.BaseMessageSourceAware;
import com.airtel.user.persistence.exception.UserDaoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
@RequestMapping(value = { "/api" })
public class RoleController extends BaseMessageSourceAware{

	
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(RoleController.class);

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
	
	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces= "application/json")
	public @ResponseBody ResponseBeanDto<List<RoleDto>> getRoles(Authentication authentication
			 ) throws UserBusinessException, UserDaoException  {
		return (ResponseBeanDto<List<RoleDto>>) userBusinessService.getRolesWithRoleIds();
	}*/
	
	@RequestMapping(value = "/roles", method = RequestMethod.POST, produces= "application/json")
	public @ResponseBody EncryptedResponseDto getRoles(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto ) throws UserBusinessException, UserDaoException, IOException  {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ResponseBeanDto<List<RoleDto>> baseResponse= (ResponseBeanDto<List<RoleDto>>) userBusinessService.getRolesWithRoleIds();
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ResponseBeanDto<List<RoleDto>> userResponse=new ResponseBeanDto<List<RoleDto>>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
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
	@RequestMapping(value = "/role/{roleId}", method = RequestMethod.GET, produces= "application/json")
	public @ResponseBody ResponseBeanDto<List<RoleDto>> getRole(Authentication authentication,
			@PathVariable int roleId ) throws UserBusinessException  {
		return (ResponseBeanDto<List<RoleDto>>) userBusinessService.getRoles(roleId);
	}*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/role/roleId", method = RequestMethod.POST, produces= "application/json")
	public @ResponseBody EncryptedResponseDto getRole(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto) throws UserBusinessException, IOException, UserDaoException  {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			RoleDto roleDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), RoleDto.class);
			ResponseBeanDto<List<RoleDto>> baseResponse=(ResponseBeanDto<List<RoleDto>>) userBusinessService.getSubRolesWithRoleIds(roleDto.getRoleId());
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ResponseBeanDto<List<RoleDto>> userResponse=new ResponseBeanDto<List<RoleDto>>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
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
	@RequestMapping(value = "/subRoles/{parentRoleId}", method = RequestMethod.GET, produces= "application/json")
	public @ResponseBody ResponseBeanDto<List<RoleDto>> getSubRoles(Authentication authentication, @PathVariable int parentRoleId 
			 ) throws UserBusinessException, UserDaoException  {
		
		return (ResponseBeanDto<List<RoleDto>>) userBusinessService.getSubRolesWithRoleIds(parentRoleId);
	}
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/subRoles/parentRoleId", method = RequestMethod.POST, produces= "application/json")
	public @ResponseBody EncryptedResponseDto getSubRoles(Authentication authentication, @RequestBody EncryptedJsonDto encryptedJsonDto  
			 ) throws UserBusinessException, UserDaoException, IOException  {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			RoleDto roleDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), RoleDto.class);
			ResponseBeanDto<List<RoleDto>> baseResponse=(ResponseBeanDto<List<RoleDto>>)userBusinessService.getSubRolesWithRoleIds(roleDto.getParentRoleId());
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ResponseBeanDto<List<RoleDto>> userResponse=new ResponseBeanDto<List<RoleDto>>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;			
		}
	}

	
	
	
}
