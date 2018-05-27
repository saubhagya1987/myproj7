package com.airtel.kyc_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.airtel.kyc.business.UserManagement;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.CommissionRuleDto;
import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.helpers.dto.EncryptedResponseDto;
import com.airtel.kyc.helpers.dto.HelpAndSupportDto;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.helpers.dto.TokenResponse;
import com.airtel.kyc.helpers.response.UserResponse;
import com.airtel.kyc.repository.dao.AuditLogDao;
import com.airtel.kyc.repository.entity.AuditLog;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.user.helper.constant.ConfigConstants;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = { "/api" })
@SuppressWarnings("unchecked")
public class UserRestController {


	@Autowired
	private ObjectWriter objectWriter;	

	@Autowired
	private UserManagement managementService;
	
	@Autowired
	Gson gson;
	
	/*@Autowired
	private MessageSource messageSource;*/
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AesUtil aesUtil;
	
	@Autowired
	AuditLogDao auditLogDao;
	
	@Autowired	
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	/*@RequestMapping(value = "/users/{slot}/{startIndex}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> fetchUser(Authentication authentication, @PathVariable int slot,
			@PathVariable int startIndex, @RequestBody SearchParamDto searchParamBean)
			throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.searchUser(searchParamBean, slot, startIndex);
	}*/
	
	/*@RequestMapping(value = "/users/{slot}/{startIndex}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> fetchUser(Authentication authentication, @PathVariable int slot,
			@PathVariable int startIndex, @RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchParamDto searchParamBean = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchParamDto.class);
			return (BaseResponse<UserResponse>) this.managementService.searchUser(searchParamBean, slot, startIndex);
		}
		else{
			return (BaseResponse<UserResponse>) this.managementService.searchUser(null, 0, 0);
		}
		
	}
	*/
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users/fetchUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto fetchUser(Authentication authentication, @RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			SearchParamDto searchParamBean = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchParamDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>) this.managementService.searchUser(searchParamBean, searchParamBean.getSlot(), searchParamBean.getStartIndex());
			
			//String encResponse=responseDecryption.encryptData(baseResponse.toString());	
			
			String encJson = objectWriter.writeValueAsString(baseResponse);
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);	*/		
			return finalResponse;
		}		
		
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;			
			
		}	
		
		
	}
	
	/*@RequestMapping(value = "/provision/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> addUser(Authentication authentication, @RequestBody UsersDto user)
			throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.provisionUser(user);
	}*/
	
	@SuppressWarnings("rawtypes")	
	@PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_SND') or hasRole('ROLE_RBM') or hasRole('ROLE_TBM') or hasRole('ROLE_ZBM') or hasRole('ROLE_SND_NATIONAL_TEAM') or hasRole('ROLE_TSM') or hasRole('ROLE_DM_AM_URBAN') or hasRole('ROLE_SND_DIRECTOR') ")	
	@RequestMapping(value = "/provision/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto addUser(Authentication authentication,  @RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		
		UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);
        //for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(usersDto.getUserName());
		auditLog.setIpAddress(usersDto.getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("add/editUser");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);		
		
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.provisionUser(usersDto);
			String encJson = objectWriter.writeValueAsString(baseResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("FALSE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	
	/*@RequestMapping(value = "/user/isMsisdnAvailable/{msisdn}", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize ("hasRole('ROLE_AIRTEL_SHOP')")
	public @ResponseBody BaseResponse<?> isMsisdnAvailable(Authentication authentication,@PathVariable String msisdn)
			throws BusinessException, SystemException {
		try{
		//return (BaseResponse<?>) this.managementService.isMsisdnAvailable(msisdn);
		return (BaseResponse<?>) this.managementService.isMSISDNAvailable(msisdn);
		//return (BaseResponse<?>) this.managementService.isMsisdnAvailable(msisdn);
		}
		catch(Exception e){
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
	}*/
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_SND') or hasRole('ROLE_RBM') or hasRole('ROLE_TBM') or hasRole('ROLE_DM_URBAN') or hasRole('ROLE_DM_RURAL') or hasRole('ROLE_DM_AM_URBAN') or hasRole('ROLE_SND_DIRECTOR') ")	
	@RequestMapping(value = "/user/isMsisdnAvailable", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto isMsisdnAvailable(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.isMSISDNAvailable(usersDto.getMsisdn());
			String encJson = objectWriter.writeValueAsString(baseResponse);
				
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}	
		
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	
	/*@RequestMapping(value = "/user/isavailable/{msisdn}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> isUserAvailbleByMSISDN(Authentication authentication,@PathVariable String msisdn)
			throws BusinessException, SystemException {
		//return (BaseResponse<?>) this.managementService.isMSISDNAvailable(msisdn);
		return (BaseResponse<?>) this.managementService.isMsisdnAvailable(msisdn);
	}*/
	@SuppressWarnings("rawtypes")
	@PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_SND') or hasRole('ROLE_RBM') or hasRole('ROLE_TBM') or hasRole('ROLE_DM_URBAN') or hasRole('ROLE_DM_RURAL') or hasRole('ROLE_DM_AM_URBAN') or hasRole('ROLE_SND_DIRECTOR') ")	
	@RequestMapping(value = "/user/isavailable", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto isUserAvailbleByMSISDN(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>) this.managementService.isMsisdnAvailable(usersDto.getMsisdn());
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);	*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	//new
	/*@RequestMapping(value = "/user/deleteUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> deleteUser(Authentication authentication,@RequestBody UsersDto userData)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.managementService.deleteUser(userData);
	}*/
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/user/deleteUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto deleteUser(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		
		UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);
		
		//for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(usersDto.getUserName());
		auditLog.setIpAddress(usersDto.getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("deleteUser");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);		
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			//UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.deleteUser(usersDto);
			String encJson = objectWriter.writeValueAsString(baseResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
				
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("FALSE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	//new
	/*@RequestMapping(value = "/user/blockUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> blockUser(Authentication authentication,@RequestBody UsersDto userData)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.managementService.blockUser(userData);
	}*/
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize ("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/user/blockUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto blockUser(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		
		UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);
		 //for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(usersDto.getUserName());
		auditLog.setIpAddress(usersDto.getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("add/editUser");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);	
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
						
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)(BaseResponse<?>) this.managementService.blockUser(usersDto);
			String encJson = objectWriter.writeValueAsString(baseResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}		
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("FALSE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}

	/*@RequestMapping(value = "/user/bulk/register", headers = ("content-type=multipart/*") )
	public ResponseEntity<byte[]> registerBulkNormalUser(Authentication authentication,@RequestParam(value = "file", required = false) MultipartFile file) throws BusinessException, SystemException {
		return this.managementService.registerBlukUser(file);

	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/bulk/register", method = RequestMethod.POST,  consumes="multipart/form-data" )
	public @ResponseBody ResponseDto registerBulkUser(Authentication authentication,@RequestParam("file") CommonsMultipartFile file ) throws BusinessException, SystemException {
		ResponseDto responseDto=null;
		ResponseEntity<byte[]> byteArray=this.managementService.registerBlukUser(file);
		String encResponse = null;		
		try {			
			responseDto=new ResponseDto();
			encResponse = new String(Base64.encodeBase64(byteArray.getBody()), "UTF-8");			
			responseDto.setBody(encResponse);
			responseDto.setResponseCode(HttpStatus.OK.value());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return responseDto;

	}
	
	
	
	/*@RequestMapping(value = "/bulk/register", headers = ("content-type=multipart/*"))
	public ResponseEntity<byte[]> registerBulkNormalSubscriber(Authentication authentication,@RequestParam(value = "file", required = false) MultipartFile file)throws BusinessException, SystemException 
	{
		return this.kycBusinessService.registerbulkSubscriber(file);

	}*/
	
	/*@RequestMapping(value = "/user/password/{uid}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody BaseResponse<?> changeUserPassword(Authentication authentication,
			@PathVariable int uid, @RequestParam String oldPassword, @RequestParam String newPassword,
			@RequestParam String confirmPassword) throws BusinessException, SystemException, UserException {
		return this.managementService.updateUserPassword(uid, oldPassword, newPassword,
				confirmPassword);
	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/password", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto changeUserPassword(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, SystemException, UserException, IOException {
		
		UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);
		 //for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(usersDto.getUid().toString());
		auditLog.setIpAddress(usersDto.getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("changeUserPassword");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);	
		
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			//UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.updateUserPassword(usersDto.getUid(), usersDto.getOldPassword(), usersDto.getNewPassword(),usersDto.getConfirmPassword());
			String encJson = objectWriter.writeValueAsString(baseResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUid().toString(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);			
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}		
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(usersDto.getUserName(), usersDto.getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("FALSE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	
	/*@RequestMapping(value = "/user/isUserAvailbleByAuuId/{auuid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> isUserAvailbleByAuuId(Authentication authentication,@PathVariable String auuid)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.managementService.isUserAvailbleByAuuId(auuid);
	}*/
	@SuppressWarnings("rawtypes")
	@PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_SND') or hasRole('ROLE_RBM') or hasRole('ROLE_TBM') or hasRole('ROLE_DM_URBAN') or hasRole('ROLE_DM_RURAL') or hasRole('ROLE_DM_AM_URBAN') or hasRole('ROLE_SND_DIRECTOR') ")	
	@RequestMapping(value = "/user/isUserAvailbleByAuuId", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto isUserAvailbleByAuuId(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException, UserException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.isUserAvailbleByAuuId(usersDto.getAuuId());
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	

	/*@RequestMapping(value = "/user/generateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> generateOtp(@RequestBody UsersDto userData)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.managementService.generatePassword(userData);
	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/generateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto generateOtp(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.generatePassword(usersDto);
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/user/validateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> validateOtp(@RequestBody UsersDto userData)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.managementService.validateOtp(userData);
	}*/
	@RequestMapping(value = "/user/validateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto validateOtp(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.validateOtp(usersDto);
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);	*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}		
		else{
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/user/validateUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> validateUser(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, UserDaoException, IOException {
		ResponseEntity<TokenResponse> responseEntity=null;
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto userData = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);        
			TokenResponse response=this.managementService.validateUser(userData);
			
			Boolean userExist=response.getUserExist();
			String existValue=String.valueOf(userExist);
			if(response!=null &&  response.getMobileAccessFlag()==1 && existValue.equals("true")){			
				//return new ResponseEntity<TokenResponse>( response, HttpStatus.OK );
				responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.OK);
			}		
			else if(response!=null &&  response.getMobileAccessFlag()==1 && existValue.equals("false")){			
				//return new ResponseEntity<TokenResponse>(response, HttpStatus.NO_CONTENT);
				responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.OK);
			}		
			else if(response.getMobileAccessFlag()==0 && existValue.equals("false") && response.getUnauthorized().equals("false")){			
				//return new ResponseEntity<TokenResponse>( response, HttpStatus.OK );
				responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.NO_CONTENT);
			}		
			else if(response.getMobileAccessFlag()==0 && response.getUnauthorized().equals("true")){			
				//return new ResponseEntity<TokenResponse>( response, HttpStatus.FORBIDDEN );
				responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.FORBIDDEN);
			}		
			else if(!encryptedJsonDto.getTokenValue().equals("abcd")){			
				//return new ResponseEntity<TokenResponse>( response, HttpStatus.UNAUTHORIZED );
				responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.UNAUTHORIZED);
			}
		}
		else{
			TokenResponse response=null;
			responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.UNAUTHORIZED);	
		}
        
		return responseEntity;
		 
	}*/
	
	/*@RequestMapping(value = "/user/validateUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> validateUser(@RequestBody UsersDto userData)
			throws BusinessException, SystemException, JsonProcessingException, UserDaoException {
		//return (ResponseEntity<?>) this.managementService.validateUser(userData);
		TokenResponse response=this.managementService.validateUser(userData);
		Boolean userExist=response.getUserExist();
		String existValue=String.valueOf(userExist);
		if(response!=null &&  response.getMobileAccessFlag()==1 && existValue.equals("true")){
			//return new ResponseEntity<TokenResponse>( this.managementService.validateUser(userData), HttpStatus.OK );
			return new ResponseEntity<TokenResponse>( response, HttpStatus.OK );
		}
		
		if(response!=null &&  response.getMobileAccessFlag()==1 && existValue.equals("false")){
			//return new ResponseEntity<TokenResponse>( this.managementService.validateUser(userData), HttpStatus.NO_CONTENT);
			return new ResponseEntity<TokenResponse>(response, HttpStatus.NO_CONTENT);
		}
		
		else if(response.getMobileAccessFlag()==0 && existValue.equals("false") && response.getUnauthorized().equals("false")){
			//return new ResponseEntity<TokenResponse>( this.managementService.validateUser(userData), HttpStatus.OK );
			return new ResponseEntity<TokenResponse>( response, HttpStatus.OK );
		}
		
		else if(response.getMobileAccessFlag()==0 && response.getUnauthorized().equals("true")){
			//return new ResponseEntity<TokenResponse>( this.managementService.validateUser(userData), HttpStatus.FORBIDDEN );
			return new ResponseEntity<TokenResponse>( response, HttpStatus.FORBIDDEN );
		}
		
		else{
			//return new ResponseEntity<TokenResponse>( this.managementService.validateUser(userData), HttpStatus.UNAUTHORIZED );
			return new ResponseEntity<TokenResponse>( response, HttpStatus.UNAUTHORIZED );
		}
		 
	}*/
	
	@RequestMapping(value = "/user/validateUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> validateUser(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		
		ResponseEntity<TokenResponse> responseEntity=null;
		TokenResponse finalResponse=new TokenResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		UsersDto userData = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);  
		
		//for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		if(userData.getAuuId()!=null){
			auditLog.setMobileNumber(userData.getAuuId());
		}
		else{
			auditLog.setMobileNumber(userData.getUserName());
		}
		auditLog.setIpAddress(userData.getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("validateUser");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);		
		
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			      
			TokenResponse response=this.managementService.validateUser(userData);
			
			Boolean userExist=response.getUserExist();
			String existValue=String.valueOf(userExist);
			if(response!=null &&  response.getMobileAccessFlag()==1 && existValue.equals("true")){				
				String encJson = objectMapper.writeValueAsString(response);
				
				//for audit logging
				AuditLog responseaudit=auditLogDao.getAuditlog(userData.getAuuId(), userData.getIpAddress(),time);				
				responseaudit.setResponseMessage(encJson);
				responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				responseaudit.setErrorTrace("TRUE");
				auditLogDao.saveOrUpdate(responseaudit);
				
				String finalEnc=aesUtil.encrypt(encJson);
				finalResponse.setEncryptedValue(finalEnc);
				String json = objectMapper.writeValueAsString(finalResponse);
				TokenResponse responseObj = new ObjectMapper().readValue(json, TokenResponse.class);				
				responseEntity=new ResponseEntity<TokenResponse>(responseObj, HttpStatus.OK);
			}		
			else if(response!=null &&  response.getMobileAccessFlag()==1 && existValue.equals("false")){	
				
				//for audit logging
				
				String encJson = objectMapper.writeValueAsString(response);
				AuditLog responseaudit=auditLogDao.getAuditlog(userData.getAuuId(), userData.getIpAddress(),time);			
				responseaudit.setResponseMessage(encJson);
				responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				responseaudit.setErrorTrace("FALSE");
				auditLogDao.saveOrUpdate(responseaudit);
				
				return new ResponseEntity<TokenResponse>(response, HttpStatus.NO_CONTENT);
			}		
			else if(response.getMobileAccessFlag()==0 && existValue.equals("false") && response.getUnauthorized().equals("false")){	
				
				//for audit logging
				String encJson = objectMapper.writeValueAsString(response);
				AuditLog responseaudit=auditLogDao.getAuditlog(userData.getAuuId(), userData.getIpAddress(),time);
				responseaudit.setResponseMessage(encJson);
				responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				responseaudit.setErrorTrace("FALSE");
				auditLogDao.saveOrUpdate(responseaudit);
				
				
				return new ResponseEntity<TokenResponse>( response, HttpStatus.OK );
			}		
			else if(response.getMobileAccessFlag()==0 && response.getUnauthorized().equals("true")){	
				

				//for audit logging
				String encJson = objectMapper.writeValueAsString(response);
				AuditLog responseaudit=auditLogDao.getAuditlog(userData.getAuuId(), userData.getIpAddress(),time);
				responseaudit.setResponseMessage(encJson);
				responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				responseaudit.setErrorTrace("FALSE");
				auditLogDao.saveOrUpdate(responseaudit);
				
				return new ResponseEntity<TokenResponse>( response, HttpStatus.FORBIDDEN );
			}		
			else if(!encryptedJsonDto.getTokenValue().equals("abcd")){		

				//for audit logging
				String encJson = objectMapper.writeValueAsString(response);
				AuditLog responseaudit=auditLogDao.getAuditlog(userData.getAuuId(), userData.getIpAddress(),time);
				responseaudit.setResponseMessage(encJson);
				responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				responseaudit.setErrorTrace("FALSE");
				auditLogDao.saveOrUpdate(responseaudit);
				
				return new ResponseEntity<TokenResponse>( response, HttpStatus.UNAUTHORIZED );
			}
		}
		else{
			TokenResponse response=null;
			responseEntity=new ResponseEntity<TokenResponse>(response, HttpStatus.UNAUTHORIZED);	
		}
        
		return responseEntity;
		 
	}
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/forgotPassword", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> forgotPassword(@RequestBody @Valid UserDetailsDto userDetailsDto, BindingResult result)
			throws BusinessException, SystemException {
		if (result.hasErrors()) {
			//throw new BusinessException(UserExceptionCodes.INVALID_REQUEST);
			BaseResponse response =new BaseResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(UserExceptionCodes.INVALID_REQUEST.getExceptionCode());
			responseDto.setResponseDescription(result.getFieldError().getDefaultMessage());
			responseDto.setKey(result.getFieldError().getField());
			response.setResponseDto(responseDto);
			return response;
			
		}
		else{
			return (BaseResponse<?>) this.managementService.forgotPassword(userDetailsDto);
		}
		
		
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/forgotPassword", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto forgotPassword(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UserDetailsDto userDetailsDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UserDetailsDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.forgotPassword(userDetailsDto);
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
		
		
	}
	
	/*@RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> resetPassword(@RequestBody  UsersDto userData)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.managementService.resetPassword(userData);
	}*/
	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto resetPassword(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.resetPassword(usersDto);
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	/*
	@RequestMapping(value = "/saveCommissionRule", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> saveCommissionRule(@RequestBody  CommissionRuleDto commissionRuleDto)
			throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.saveCommissionRule(commissionRuleDto);
	}*/
	@RequestMapping(value = "/saveCommissionRule", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto saveCommissionRule(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			CommissionRuleDto commissionRuleDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), CommissionRuleDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.saveCommissionRule(commissionRuleDto);
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);	*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/getCommissionRule", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> getCommissionRule(@RequestBody  CommissionRuleDto commissionRuleDto)
			throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.getCommissionRule(commissionRuleDto);
	}*/
	@RequestMapping(value = "/getCommissionRule", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getCommissionRule(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			CommissionRuleDto commissionRuleDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), CommissionRuleDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.getCommissionRule(commissionRuleDto);
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/	
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/saveAndSendHelpAndSupportData", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> saveAndSendHelpAndSupportData(@RequestBody  HelpAndSupportDto helpAndSupportDto)
			throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.saveAndSendHelpAndSupportData(helpAndSupportDto);
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveAndSendHelpAndSupportData", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto saveAndSendHelpAndSupportData(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue() !=null && encryptedJsonDto.getTokenValue().equals("abc")){
			HelpAndSupportDto helpAndSupportDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), HelpAndSupportDto.class);			
			BaseResponse<UserResponse> baseResponse=(BaseResponse<UserResponse>)this.managementService.saveAndSendHelpAndSupportData(helpAndSupportDto);
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
			BaseResponse<UserResponse> userResponse=new BaseResponse<UserResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	@RequestMapping(value = "/user/encryptedJson", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> encryptedJson(@RequestBody  EncryptedJsonDto encryptedJsonDto,Authentication authentication)
			throws BusinessException, SystemException {
		if(encryptedJsonDto.getTokenValue().equals("abc"))
		{
			System.out.println("test controller");
			System.out.println("data "+encryptedJsonDto.getEncyptedValue());
		}
		else{
			System.out.println("test controller unauthorized");
			System.out.println("data "+ HttpStatus.UNAUTHORIZED);
		}
		//EncryptedJsonDto dto=gson.fromJson(encryptedJsonDto, EncryptedJsonDto.class);
		return null;
	}
	
	@RequestMapping(value = "/user/getUserImage/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getSubscriberImage(
			
			@PathVariable(value = "hostname") String hostname,
			@PathVariable(value = "year") String year,
			@PathVariable(value = "month") String month,
			@PathVariable(value = "day") String day,
			@PathVariable(value = "tr_id") String tr_id,
			@PathVariable(value = "timestr") String timestr,
			@PathVariable(value = "imagename") String imagename,
			@PathVariable(value = "imageType") String imageType
			//,
			//HttpServletRequest request,
			//HttpServletResponse response
			) throws Exception {

			timestr=hostname+"/"+year+"/"+month+"/"+day+"/"+timestr;
			System.out.println("start  hai ");
			String actfilepath=env.getProperty(ConfigConstants.UPLOAD_FOLDER)+env.getProperty(ConfigConstants.USER_DIRECTORY)+timestr+"/"+tr_id+"/";
			
			imagename=imagename+"."+imageType;
			actfilepath=actfilepath+imagename;
			/*ServletOutputStream oStream = response.getOutputStream();
			String responceContentType=ImageUtility.getReturnContentType(imageType,request);
			response.setContentType(responceContentType);
			
			if (responceContentType.equals("image/jpg")){
				System.err.println("for jpg format...");
				response.setHeader("Content-Disposition","attachment;filename=" + imagename + ";");
			}else if (responceContentType.equals("image/png")){
				System.err.println("for png format...");
				response.setHeader("Content-Disposition","attachment;filename=" + imagename + ";");
			}
			else if (responceContentType.equals("image/wsq")){
				System.err.println("for png format...");
				response.setHeader("Content-Disposition","attachment;filename=" + imagename + ";");
			}*/
			 
			FileInputStream stream = null;
			InputStream stream1=null;
			try{
				System.err.println("actual path  " + actfilepath);
				if(hostname!=null && hostname.equals(env.getProperty(ConfigConstants.HOST_NAME))){
					File f = new File(actfilepath);
				 	FileInputStream inStream = new FileInputStream(f);
				 	
				 	stream=inStream;
				 	
				 	/*byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = inStream.read(buffer)) != -1) {
			        	oStream.write(buffer, 0, bytesRead);
			        }
			        inStream.close();
			        oStream.close(); */
				}else{
					String remoteURL=env.getProperty(ConfigConstants.PEER_URL)+"getUserImage"+"/"+tr_id+"/"+timestr+"/"+imagename;
					
					File f = new File(remoteURL);
				 	FileInputStream inStream = new FileInputStream(f);
				 	
				 	stream=inStream;
				 	
					//response.sendRedirect(remoteURL);
				}
			}catch(Exception ex){
			  System.out.println("error simswap image ");
			}
			finally
			{
				/*if (oStream != null)
				{
					//stream.close();
					oStream.close();
				}
				if (oStream != null)
				{
					//stream1.close();
					oStream.close();
				}*/
			}
			
			
			HttpHeaders headers = new HttpHeaders();
		    byte[] media = IOUtils.toByteArray(stream);
			return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);
	   }

	
	
/*	@RequestMapping(value = "/user/{uid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> getUserById(Authentication authentication, @PathVariable int uid)
			throws BusinessException, SystemException {
		UserResponse response = (UserResponse) this.managementService.getUserById(uid);
		return response;
	}

	@RequestMapping(value = "/user/{uid}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> updateUser(Authentication authentication,
			@RequestBody UsersDto user, @PathVariable int uid) throws BusinessException, SystemException {
		user.setUserId(uid);
		return (BaseResponse<UserResponse>) this.managementService.updateUser(uid, user);
	}
	
	@RequestMapping(value = "/user/status/{uid}", method = RequestMethod.PUT)
	public @ResponseBody BaseResponse<UserResponse> updateUserActive(Authentication authentication,
			@PathVariable int uid , @RequestParam boolean isActive) throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.updateUserActiveStatus(uid, isActive);
	}
	
	
	

	@RequestMapping(value = "/user/getBypass", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> getBypassStatus(Authentication authentication)
			throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.getBypassStatus();
	}

	@RequestMapping(value = "/user/updateBypass", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<UserResponse> updateBypass(Authentication authentication,
			@RequestBody UsersDto user) throws BusinessException, SystemException {
		return (BaseResponse<UserResponse>) this.managementService.updateBypass(user.getBypassStatus());
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> getUserData(Authentication authentication)
			throws BusinessException, SystemException {
		String userName=null;
		userName=(String) authentication.getPrincipal();
		UserResponse response = (UserResponse) this.managementService.getUserByName(userName);
		return response;
	}
	
	
	*/
}
