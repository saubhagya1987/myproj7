package com.airtel.kyc_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.airtel.africa.airtel.ema.helpers.EMAResponse;
import com.airtel.kyc.business.SubscriberManagement;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.controller.utils.EncrytptionUtils;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.helpers.dto.EncryptedResponseDto;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberIdDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberUserDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.helpers.response.SIMValidationResponse;
import com.airtel.kyc.helpers.response.SubscriberResponse;
import com.airtel.kyc.helpers.response.TotalCountResponse;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.dao.AuditLogDao;
import com.airtel.kyc.repository.entity.AuditLog;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.security.web.WebDecryption;
import com.airtel.kyc.utils.DateUtils;
import com.airtel.user.helper.constant.ConfigConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
@RequestMapping(value = { "/api/subscriber" })
@SuppressWarnings("unchecked")
public class SubscriberController {
	
	@Autowired
	private SubscriberManagement kycBusinessService;
	
	/*@Autowired
	private MessageSource messageSource;*/
	
	@Autowired
	private Environment env;
	
	@Autowired
	ObjectWriter objectWriter;
	
	@Autowired
	EncryptionRSA encryptionRSA;
	
	@Autowired
	WebDecryption webDecryption;
	
	@Autowired
	EncrytptionUtils encrytptionUtils;
	
	@Autowired
	AesUtil aesUtil;
	
	@Autowired
	AuditLogDao auditLogDao;
	
	@Autowired
	IntegrationService integrationService; 
	
	@RequestMapping(value = "/registerNormalSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize ("hasRole('ROLE_SSO') or hasRole('ROLE_OWN_DEVICE_RETAILER') or hasRole('ROLE_OWN_DEVICE_CHABEBA') or hasRole('ROLE_CHABEBA') or hasRole('ROLE_BOOTH') or hasRole('ROLE_MINISHOP') or hasRole('ROLE_AIRTEL_MONEY_EXPRESS_SHOP') or hasRole('ROLE_COFO_AIRTEL_SHOP')or hasRole('ROLE_COCO_AIRTEL_SHOP') or hasRole('ROLE_AGENCY') or hasRole('ROLE_CHAIN_STORES') or hasRole('ROLE_OWN_DEVICE_CHAIN_STORES')")
	public @ResponseBody EncryptedResponseDto registerNormalSubscriber(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto, @RequestParam (required = false) boolean isReRegistation) throws BusinessException, SystemException, UserException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		BaseResponse<SubscriberResponse> response =null;
		SubscriberUserDto subscriberUserDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberUserDto.class);
		
		//for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(subscriberUserDto.getSubscriberData().getMsisdn());
		auditLog.setIpAddress(subscriberUserDto.getSubscriberData().getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("registerNormalSubscriber");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);	
		
		
		
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			
			BaseResponse<SubscriberResponse> baseResponse= (BaseResponse<SubscriberResponse>) kycBusinessService.registerSubscriber(subscriberUserDto, isReRegistation);
			String encJson = objectWriter.writeValueAsString(baseResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("FALSE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
		

		

		
	}
	
	/*@RequestMapping(value = "action/getSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getPendingSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getSubscriber(searchSubscriberDto, searchSubscriberDto.getActionType());

		return responseBean;	
	}*/
	
	@RequestMapping(value = "action/getSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto getPendingSubscribers(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getSubscriber(searchSubscriberDto, searchSubscriberDto.getActionType());
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "action/getCMSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getCMSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getCMSubscriber(searchSubscriberDto, searchSubscriberDto.getActionType());

		return responseBean;
	}*/
	@RequestMapping(value = "action/getCMSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto getCMSubscribers(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, JsonParseException, JsonMappingException, IOException, SystemException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			int userId = searchSubscriberDto.getUserId();
			
			//kycBusinessService.updateLastLogin(userId);
			
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getCMSubscriber(searchSubscriberDto, searchSubscriberDto.getActionType());
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/	
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/{subscriberId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> getSubscriberBySubscriberId(Authentication authentication,
			@PathVariable Long subscriberId) throws BusinessException, SystemException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getSubscriberBySubscriberId(subscriberId);

		return responseBean;
	}*/
	@RequestMapping(value = "/subscriberId", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getSubscriberBySubscriberId(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getSubscriberBySubscriberId(subscriberDto.getSubscriberId());
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	
	/*@RequestMapping(value = "/getAllSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> fetchSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, JsonProcessingException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getSubscriber(searchSubscriberDto);
		System.out.println("json:"+objectWriter.writeValueAsString(responseBean));

		return responseBean;
	}*/
	@RequestMapping(value = "/getAllSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto fetchSubscribers(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getSubscriber(searchSubscriberDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/getAllSubscriberList/{msisdn}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getAllSubscriberList(Authentication authentication,
			@PathVariable String msisdn) throws BusinessException, JsonProcessingException {
		SearchSubscriberDto searchSubscriberDto =new SearchSubscriberDto();
		searchSubscriberDto.setSlot(1);
		searchSubscriberDto.setStartIndex(0);
		searchSubscriberDto.setMsisdn(msisdn);
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getSubscriber(searchSubscriberDto);
		System.out.println("json:"+objectWriter.writeValueAsString(responseBean));

		return responseBean;
	}*/
	@RequestMapping(value = "/getAllSubscriberList/msisdn", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getAllSubscriberList(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberDto.class);
			SearchSubscriberDto searchSubscriberDto =new SearchSubscriberDto();
			searchSubscriberDto.setSlot(1);
			searchSubscriberDto.setStartIndex(0);
			searchSubscriberDto.setMsisdn(subscriberDto.getMsisdn());
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getSubscriber(searchSubscriberDto);
			
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	@RequestMapping(value = "/getSearchSubscriberList/msisdn", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getSearchSubscriberList(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberDto.class);
			SearchSubscriberDto searchSubscriberDto =new SearchSubscriberDto();
			searchSubscriberDto.setSlot(1);
			searchSubscriberDto.setStartIndex(0);
			searchSubscriberDto.setMsisdn(subscriberDto.getMsisdn());
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getSearchSubscriberList(searchSubscriberDto);
			
			String encJson = objectWriter.writeValueAsString(responseBean);
			System.out.println("encJson"+encJson);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	@RequestMapping(value = "/getTotalCount", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<TotalCountResponse> getSubscriberCount(@RequestParam int userId,@RequestParam String roleName)
			throws BusinessException {
		BaseResponse<TotalCountResponse> responseBean = (BaseResponse<TotalCountResponse>) kycBusinessService
				.getSubscriberCount(userId,roleName);

		return responseBean;
	}
	
	/*@RequestMapping(value = "/getTotalCount", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<TotalCountResponse> getSubscriberCount(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, JsonParseException, JsonMappingException, IOException {
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubDto.class);
			BaseResponse<TotalCountResponse> responseBean = (BaseResponse<TotalCountResponse>) kycBusinessService
					.getSubscriberCount(subscriberDto.getUserId(),subscriberDto.getRoleName());
			return responseBean;
		}
		else if(!encryptedJsonDto.getTokenValue().equals("abc")){
			EncryptedJsonDto dto=decryption(encryptedJsonDto);
			SubDto subscriberDto = null;
			if(dto.getTokenValue().equals("abc")){
				subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubDto.class);
				
			}
			BaseResponse<TotalCountResponse> responseBean = (BaseResponse<TotalCountResponse>) kycBusinessService
					.getSubscriberCount(subscriberDto.getUserId(),subscriberDto.getRoleName());
			return responseBean;
		}
		
		else{
			BaseResponse<TotalCountResponse> totalCountResponse=new BaseResponse<TotalCountResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			totalCountResponse.setResponseDto(responseDto);
			return totalCountResponse;
			
		}	
	}*/
	
	/*@RequestMapping(value = "/getSubscriberImage/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
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
			String actfilepath=env.getProperty(ConfigConstants.UPLOAD_FOLDER)+env.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY)+timestr+"/"+tr_id+"/";
			
			imagename=imagename+"."+imageType;
			actfilepath=actfilepath+imagename;
			ServletOutputStream oStream = response.getOutputStream();
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
			}
			 
			FileInputStream stream = null;
			InputStream stream1=null;
			try{
				System.err.println("actual path  " + actfilepath);
				if(hostname!=null && hostname.equals(env.getProperty(ConfigConstants.HOST_NAME))){
					File f = new File(actfilepath);
				 	FileInputStream inStream = new FileInputStream(f);
				 	
				 	stream=inStream;
				 	
				 	byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = inStream.read(buffer)) != -1) {
			        	oStream.write(buffer, 0, bytesRead);
			        }
			        inStream.close();
			        oStream.close(); 
				}else{
					String remoteURL=env.getProperty(ConfigConstants.PEER_URL)+"getSubscriberImage"+"/"+tr_id+"/"+timestr+"/"+imagename;
					
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
				if (oStream != null)
				{
					//stream.close();
					oStream.close();
				}
				if (oStream != null)
				{
					//stream1.close();
					oStream.close();
				}
			}
			
			
			HttpHeaders headers = new HttpHeaders();
		    byte[] media = IOUtils.toByteArray(stream);
			return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);
	   }*/
	
	@RequestMapping(value = "/getSubscriberImage/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getSubscriberImage(            

                  @PathVariable(value = "hostname") String hostname,
                  @PathVariable(value = "year") String year,
                  @PathVariable(value = "month") String month,
                  @PathVariable(value = "day") String day,
                  @PathVariable(value = "tr_id") String tr_id,
                  @PathVariable(value = "timestr") String timestr,
                  @PathVariable(value = "imagename") String imagename,
                  @PathVariable(value = "imageType") String imageType
                  

    ) throws Exception {

           String methodName = "getSubscriberImage";

           timestr = hostname + "/" + year + "/" + month + "/" + day + "/" + timestr;

           //String actfilepath = KyctnzConstants.uploadFolder + KyctnzConstants.subscriberDir + timestr + "/" + tr_id + "/";
           String actfilepath=env.getProperty(ConfigConstants.UPLOAD_FOLDER)+env.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY)+timestr+"/"+tr_id+"/";

           imagename = imagename + "." + imageType;
           actfilepath = actfilepath + imagename;
          

           byte[] media = null;

           FileInputStream stream = null;

           try {                

                  if (hostname != null && hostname.equals(env.getProperty(ConfigConstants.HOST_NAME))) {

                        stream = new FileInputStream(new File(actfilepath));

                        media = IOUtils.toByteArray(stream);

                  } else {

                        String remoteURL = env.getProperty(ConfigConstants.PEER_URL) + "getSubscriberImage" + "/"+timestr  + "/" + tr_id + "/"+ imagename;                        

                        URL url = new URL(remoteURL);

                        InputStream is = url.openStream();

                        media = IOUtils.toByteArray(is);                      


            } 
           }

           catch (FileNotFoundException e) {
        	   
           }

           catch (Exception ex) {

                 
           } finally {

                  if(stream != null)

                        stream.close();

           }

           HttpHeaders headers = new HttpHeaders();

           return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);

    }

	/*@RequestMapping(value = "/getSubscriberImage1/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
	public byte[] getSubscriberImage1(
			
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
			String actfilepath=env.getProperty(ConfigConstants.UPLOAD_FOLDER)+env.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY)+timestr+"/"+tr_id+"/";
			
			imagename=imagename+"."+imageType;
			actfilepath=actfilepath+imagename;
			ServletOutputStream oStream = response.getOutputStream();
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
			}
			 
			FileInputStream stream = null;
			InputStream stream1=null;
			try{
				System.err.println("actual path  " + actfilepath);
				if(hostname!=null && hostname.equals(env.getProperty(ConfigConstants.HOST_NAME))){
					File f = new File(actfilepath);
				 	FileInputStream inStream = new FileInputStream(f);
				 	
				 	stream=inStream;
				 	
				 	byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = inStream.read(buffer)) != -1) {
			        	oStream.write(buffer, 0, bytesRead);
			        }
			        inStream.close();
			        oStream.close(); 
				}else{
					String remoteURL=env.getProperty(ConfigConstants.PEER_URL)+"getSubscriberImage"+"/"+tr_id+"/"+timestr+"/"+imagename;
					
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
				if (oStream != null)
				{
					//stream.close();
					oStream.close();
				}
				if (oStream != null)
				{
					//stream1.close();
					oStream.close();
				}
			}
			
			
			HttpHeaders headers = new HttpHeaders();
		    byte[] media = IOUtils.toByteArray(stream);
			return media;
	   }

*/	
	@RequestMapping(value = "/getSubscriberImage1/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getSubscriberImage1(            

                  @PathVariable(value = "hostname") String hostname,
                  @PathVariable(value = "year") String year,
                  @PathVariable(value = "month") String month,
                  @PathVariable(value = "day") String day,
                  @PathVariable(value = "tr_id") String tr_id,
                  @PathVariable(value = "timestr") String timestr,
                  @PathVariable(value = "imagename") String imagename,
                  @PathVariable(value = "imageType") String imageType
                  

    ) throws Exception {

           String methodName = "getSubscriberImage";

           timestr = hostname + "/" + year + "/" + month + "/" + day + "/" + timestr;

           //String actfilepath = KyctnzConstants.uploadFolder + KyctnzConstants.subscriberDir + timestr + "/" + tr_id + "/";
           String actfilepath=env.getProperty(ConfigConstants.UPLOAD_FOLDER)+env.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY)+timestr+"/"+tr_id+"/";

           imagename = imagename + "." + imageType;
           actfilepath = actfilepath + imagename;
          

           byte[] media = null;

           FileInputStream stream = null;

           try {                

                  if (hostname != null && hostname.equals(env.getProperty(ConfigConstants.HOST_NAME))) {

                        stream = new FileInputStream(new File(actfilepath));

                        media = IOUtils.toByteArray(stream);

                  } else {

                        String remoteURL = env.getProperty(ConfigConstants.PEER_URL) + "getSubscriberImage" + "/"+timestr  + "/" + tr_id + "/"+ imagename;                        

                        URL url = new URL(remoteURL);

                        InputStream is = url.openStream();

                        media = IOUtils.toByteArray(is);                      


            } 
           }

           catch (FileNotFoundException e) {
        	   
           }

           catch (Exception ex) {

                 
           } finally {

                  if(stream != null)

                        stream.close();

           }

           HttpHeaders headers = new HttpHeaders();

           return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);

    }
	
	/*@RequestMapping(value = "action/OnSubscriber", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> takeActionOnSubscriber(Authentication authentication,
			@RequestBody SubscriberWorkFlowDto subscriberWorkFlowDto) throws BusinessException, SystemException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.actionOnSubscriber(subscriberWorkFlowDto);

		return responseBean;
	}*/
	@RequestMapping(value = "action/OnSubscriber", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto actionOnSubscriber(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberWorkFlowDto subscriberWorkFlowDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberWorkFlowDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.actionOnSubscriber(subscriberWorkFlowDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	@RequestMapping(value = "action/assignToMe", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto assignToMe(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, JsonParseException, JsonMappingException, IOException, SystemException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			int userId = searchSubscriberDto.getUserId();
			
			//kycBusinessService.updateLastLogin(userId);
			
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.assignToMe(searchSubscriberDto, searchSubscriberDto.getActionType());
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/	
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}

	
	/*@RequestMapping(value = "/getBarredSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getBarredSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getBarredSubscribers(searchSubscriberDto);

		return responseBean;
	}*/
	@RequestMapping(value = "/getBarredSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize ("hasRole('ROLE_SSO') or hasRole('ROLE_OWN_DEVICE_RETAILER') or hasRole('ROLE_OWN_DEVICE_CHABEBA') or hasRole('ROLE_CHABEBA') or hasRole('ROLE_BOOTH') or hasRole('ROLE_MINISHOP') or hasRole('ROLE_AIRTEL_MONEY_EXPRESS_SHOP') or hasRole('ROLE_COFO_AIRTEL_SHOP')or hasRole('ROLE_COCO_AIRTEL_SHOP') or hasRole('ROLE_AGENCY') or hasRole('ROLE_CHAIN_STORES') or hasRole('ROLE_OWN_DEVICE_CHAIN_STORES')")
	public @ResponseBody EncryptedResponseDto getBarredSubscribers(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getBarredSubscribers(searchSubscriberDto);
			
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
			
	}
	/*@RequestMapping(value = "/updateSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> updateSubscriber(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.updateSubscriber(subscriberUserDto, isReRegistation);

		return responseBean;
	}*/
	@RequestMapping(value = "/updateSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	//@PreAuthorize ("hasRole('ROLE_SSO','ROLE_AM_AGENT','ROLE_AIRTEL_SHOP')")
	public @ResponseBody EncryptedResponseDto updateSubscriber(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException, JsonParseException, JsonMappingException, IOException {
		
		SubscriberUserDto subscriberUserDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberUserDto.class);
		//for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(subscriberUserDto.getSubscriberData().getMsisdn());
		auditLog.setIpAddress(subscriberUserDto.getSubscriberData().getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("updateSubscriber");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);	
		
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.updateSubscriber(subscriberUserDto, isReRegistation);
			String encJson = objectWriter.writeValueAsString(responseBean);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("FALSE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/checkEntriesForIdNo/{idNumber}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> checkEntriesForIdNo(Authentication authentication,@PathVariable String idNumber)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.kycBusinessService.checkEntriesForIdNo(idNumber);
	}*/
	@RequestMapping(value = "/checkEntriesForIdNo/idNumber", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize ("hasRole('ROLE_SSO') or hasRole('ROLE_OWN_DEVICE_RETAILER') or hasRole('ROLE_OWN_DEVICE_CHABEBA') or hasRole('ROLE_CHABEBA') or hasRole('ROLE_BOOTH') or hasRole('ROLE_MINISHOP') or hasRole('ROLE_AIRTEL_MONEY_EXPRESS_SHOP') or hasRole('ROLE_COFO_AIRTEL_SHOP')or hasRole('ROLE_COCO_AIRTEL_SHOP') or hasRole('ROLE_AGENCY') or hasRole('ROLE_CHAIN_STORES') or hasRole('ROLE_OWN_DEVICE_CHAIN_STORES')")
	public @ResponseBody EncryptedResponseDto checkEntriesForIdNo(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberIdDetailsDto subscriberIdDetailsDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberIdDetailsDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>)this.kycBusinessService.checkEntriesForIdNo(subscriberIdDetailsDto.getIdNumber());
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/generateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> generateOtp(Authentication authentication,@RequestBody SubscriberDto subscriberDto)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.kycBusinessService.generatePassword(subscriberDto);
	}*/
	@RequestMapping(value = "/generateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto generateOtp(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) this.kycBusinessService.generatePassword(subscriberDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	
	
	/*@RequestMapping(value = "/validateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> validateOtp(Authentication authentication,@RequestBody SubscriberDto subscriberDto)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.kycBusinessService.validateOtp(subscriberDto);
	}*/
	@RequestMapping(value = "/validateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto validateOtp(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>)  this.kycBusinessService.validateOtp(subscriberDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/getApprovedSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getApprovedSubscriber(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, SystemException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getApprovedSubscribers(searchSubscriberDto);		

		return responseBean;
	}*/
	@RequestMapping(value = "/getApprovedSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")	
	@PreAuthorize ("hasRole('ROLE_SSO')  or hasRole('ROLE_ADMIN') or hasRole('ROLE_OWN_DEVICE_RETAILER') or hasRole('ROLE_OWN_DEVICE_CHABEBA') or hasRole('ROLE_CHABEBA') or hasRole('ROLE_BOOTH') or hasRole('ROLE_MINISHOP') or hasRole('ROLE_AIRTEL_MONEY_EXPRESS_SHOP') or hasRole('ROLE_COFO_AIRTEL_SHOP')or hasRole('ROLE_COCO_AIRTEL_SHOP') or hasRole('ROLE_AGENCY') or hasRole('ROLE_CHAIN_STORES') or hasRole('ROLE_OWN_DEVICE_CHAIN_STORES')")
	public @ResponseBody EncryptedResponseDto getApprovedSubscriber(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, SystemException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getApprovedSubscribers(searchSubscriberDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/editRegisterSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> editRegisterSubscriber(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.editRegisterSubscriber(subscriberUserDto, isReRegistation);

		return responseBean;
	}*/
	@RequestMapping(value = "/editRegisterSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto editRegisterSubscriber(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberUserDto subscriberUserDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberUserDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.editRegisterSubscriber(subscriberUserDto, isReRegistation);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	
	/*@RequestMapping(value = "/registerExistingSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> registerExistingSubscriber(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.registerExistingSubscriber(subscriberUserDto, isReRegistation);

		return responseBean;
	}*/
	@RequestMapping(value = "/registerExistingSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize ("hasRole('ROLE_SSO') or hasRole('ROLE_OWN_DEVICE_RETAILER') or hasRole('ROLE_OWN_DEVICE_CHABEBA') or hasRole('ROLE_CHABEBA') or hasRole('ROLE_BOOTH') or hasRole('ROLE_MINISHOP') or hasRole('ROLE_AIRTEL_MONEY_EXPRESS_SHOP') or hasRole('ROLE_COFO_AIRTEL_SHOP')or hasRole('ROLE_COCO_AIRTEL_SHOP') or hasRole('ROLE_AGENCY') or hasRole('ROLE_CHAIN_STORES') or hasRole('ROLE_OWN_DEVICE_CHAIN_STORES')")
	public @ResponseBody EncryptedResponseDto registerExistingSubscriber(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException, JsonParseException, JsonMappingException, IOException {
		
		SubscriberUserDto subscriberUserDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberUserDto.class);
		//for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(subscriberUserDto.getSubscriberData().getMsisdn());
		auditLog.setIpAddress(subscriberUserDto.getSubscriberData().getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("registerExistingSubscriber");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);	
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			//SubscriberUserDto subscriberUserDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberUserDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.registerExistingSubscriber(subscriberUserDto, isReRegistation);
			String encJson = objectWriter.writeValueAsString(responseBean);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	/*@RequestMapping(value = "/registerExistingSubscriberWithNewData", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> registerExistingSubscriberWithNewData(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.registerExistingSubscriberWithNewData(subscriberUserDto, isReRegistation);

		return responseBean;
	}*/
	@RequestMapping(value = "/registerExistingSubscriberWithNewData", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize ("hasRole('ROLE_SSO') or hasRole('ROLE_OWN_DEVICE_RETAILER') or hasRole('ROLE_OWN_DEVICE_CHABEBA') or hasRole('ROLE_CHABEBA') or hasRole('ROLE_BOOTH') or hasRole('ROLE_MINISHOP') or hasRole('ROLE_AIRTEL_MONEY_EXPRESS_SHOP') or hasRole('ROLE_COFO_AIRTEL_SHOP')or hasRole('ROLE_COCO_AIRTEL_SHOP') or hasRole('ROLE_AGENCY') or hasRole('ROLE_CHAIN_STORES') or hasRole('ROLE_OWN_DEVICE_CHAIN_STORES')")
	public @ResponseBody EncryptedResponseDto registerExistingSubscriberWithNewData(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException, JsonParseException, JsonMappingException, IOException {
		
		SubscriberUserDto subscriberUserDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberUserDto.class);
		//for audit logging
		final Timestamp time=new Timestamp(System.currentTimeMillis());
		AuditLog auditLog=new AuditLog();
		auditLog.setMobileNumber(subscriberUserDto.getSubscriberData().getMsisdn());
		auditLog.setIpAddress(subscriberUserDto.getSubscriberData().getIpAddress());
		auditLog.setRequestMessage(encryptedJsonDto.getEncyptedValue());
		auditLog.setResponseMessage(null);		
		auditLog.setActivityName("registerExistingSubscriberWithNewData");
		auditLog.setCreatedOn(time);		
		auditLogDao.saveOrUpdate(auditLog);	
				
		
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.registerExistingSubscriberWithNewData(subscriberUserDto, isReRegistation);
			String encJson = objectWriter.writeValueAsString(responseBean);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			
			//for audit logging
			AuditLog responseaudit=auditLogDao.getAuditlog(subscriberUserDto.getSubscriberData().getMsisdn(), subscriberUserDto.getSubscriberData().getIpAddress(),time);				
			responseaudit.setResponseMessage(encJson);
			responseaudit.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			responseaudit.setErrorTrace("TRUE");
			auditLogDao.saveOrUpdate(responseaudit);
			
			
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/updateSubscriberForm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> updateSubscriberForm(Authentication authentication,
			@RequestBody SubscriberDto subscriberDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.updateSubscriberForm(subscriberDto, isReRegistation);

		return responseBean;
	}*/
	@RequestMapping(value = "/updateSubscriberForm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto updateSubscriberForm(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException, JsonParseException, JsonMappingException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SubscriberDto subscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.updateSubscriberForm(subscriberDto, isReRegistation);

			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/getOldApprovedSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getOldApprovedSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, JsonProcessingException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getOldApprovedSubscribers(searchSubscriberDto);
		return responseBean;
	}*/
	@RequestMapping(value = "/getOldApprovedSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto getOldApprovedSubscribers(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getOldApprovedSubscribers(searchSubscriberDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	/*@RequestMapping(value = "/getNewApprovedSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getNewApprovedSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, JsonProcessingException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getNewApprovedSubscribers(searchSubscriberDto);
		return responseBean;
	}*/
	@RequestMapping(value = "/getNewApprovedSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody EncryptedResponseDto getNewApprovedSubscribers(Authentication authentication,
			@RequestBody EncryptedJsonDto encryptedJsonDto) throws BusinessException, IOException {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			SearchSubscriberDto searchSubscriberDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), SearchSubscriberDto.class);
			BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
					.getNewApprovedSubscribers(searchSubscriberDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<SubscriberResponse> subscriberResponse=new BaseResponse<SubscriberResponse>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			subscriberResponse.setResponseDto(responseDto);
			String encJson=objectWriter.writeValueAsString(subscriberResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	
	@PostMapping(value = "/bulk/register", headers = ("content-type=multipart/*"))
	public @ResponseBody ResponseDto registerBulkNormalSubscriber(Authentication authentication,@RequestParam(value = "file", required = false) MultipartFile file)throws BusinessException, SystemException 
	{
		//return this.kycBusinessService.registerbulkSubscriber(file);		
		ResponseDto responseDto=null;
		ResponseEntity<byte[]> byteArray=this.kycBusinessService.registerbulkSubscriber(file);
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

		
	/*@RequestMapping(value = "/registerNormalSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> registerNormalSubscriber(Authentication authentication,
			@RequestBody  @Valid SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation,BindingResult result) throws BusinessException, SystemException, UserException {
		BaseResponse<SubscriberResponse> response =null;
		if (result.hasErrors()) {			
			response =new BaseResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(UserExceptionCodes.INVALID_REQUEST.getExceptionCode());
			responseDto.setResponseDescription(result.getFieldError().getDefaultMessage());
			response.setResponseDto(responseDto);
			return response;
			
		}
		else{
			response = (BaseResponse<SubscriberResponse>) kycBusinessService.registerSubscriber(subscriberUserDto, isReRegistation);
		}

		

		return response;
	}
	
	@RequestMapping(value = "action/getSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getPendingSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getSubscriber(searchSubscriberDto, searchSubscriberDto.getActionType());

		return responseBean;	}
	
	@RequestMapping(value = "action/getCMSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getCMSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getCMSubscriber(searchSubscriberDto, searchSubscriberDto.getActionType());

		return responseBean;
	}
	
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> getSubscriberBySubscriberId(Authentication authentication,
			@PathVariable Long subscriberId) throws BusinessException, SystemException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getSubscriberBySubscriberId(subscriberId);

		return responseBean;
	}

	
	@RequestMapping(value = "/getAllSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> fetchSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, JsonProcessingException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getSubscriber(searchSubscriberDto);
		System.out.println("json:"+objectWriter.writeValueAsString(responseBean));

		return responseBean;
	}
	
	@RequestMapping(value = "/getAllSubscriberList/{msisdn}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getAllSubscriberList(Authentication authentication,
			@PathVariable String msisdn) throws BusinessException, JsonProcessingException {
		SearchSubscriberDto searchSubscriberDto =new SearchSubscriberDto();
		searchSubscriberDto.setSlot(1);
		searchSubscriberDto.setStartIndex(0);
		searchSubscriberDto.setMsisdn(msisdn);
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getSubscriber(searchSubscriberDto);
		System.out.println("json:"+objectWriter.writeValueAsString(responseBean));

		return responseBean;
	}
	@RequestMapping(value = "/getTotalCount", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<TotalCountResponse> getSubscriberCount(Authentication authentication,@RequestParam int userId,@RequestParam String roleName)
			throws BusinessException {
		BaseResponse<TotalCountResponse> responseBean = (BaseResponse<TotalCountResponse>) kycBusinessService
				.getSubscriberCount(userId,roleName);

		return responseBean;
	}
	
	@RequestMapping(value = "/getSubscriberImage/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
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
			String actfilepath=messageSource.getMessage(ConfigConstants.UPLOAD_FOLDER, null, Locale.getDefault())+messageSource.getMessage(ConfigConstants.SUBSCRIBER_DIRECTORY, null, Locale.getDefault())+timestr+"/"+tr_id+"/";
			
			imagename=imagename+"."+imageType;
			actfilepath=actfilepath+imagename;
			ServletOutputStream oStream = response.getOutputStream();
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
			}
			 
			FileInputStream stream = null;
			InputStream stream1=null;
			try{
				System.err.println("actual path  " + actfilepath);
				if(hostname!=null && hostname.equals(messageSource.getMessage(ConfigConstants.HOST_NAME, null, Locale.getDefault()))){
					File f = new File(actfilepath);
				 	FileInputStream inStream = new FileInputStream(f);
				 	
				 	stream=inStream;
				 	
				 	byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = inStream.read(buffer)) != -1) {
			        	oStream.write(buffer, 0, bytesRead);
			        }
			        inStream.close();
			        oStream.close(); 
				}else{
					String remoteURL=messageSource.getMessage(ConfigConstants.PEER_URL, null, Locale.getDefault())+"getSubscriberImage"+"/"+tr_id+"/"+timestr+"/"+imagename;
					
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
				if (oStream != null)
				{
					//stream.close();
					oStream.close();
				}
				if (oStream != null)
				{
					//stream1.close();
					oStream.close();
				}
			}
			
			
			HttpHeaders headers = new HttpHeaders();
		    byte[] media = IOUtils.toByteArray(stream);
			return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);
	   }

	@RequestMapping(value = "/getSubscriberImage1/{hostname}/{year}/{month}/{day}/{timestr}/{tr_id}/{imagename}.{imageType}", method = RequestMethod.GET)
	public byte[] getSubscriberImage1(
			
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
			String actfilepath=messageSource.getMessage(ConfigConstants.UPLOAD_FOLDER, null, Locale.getDefault())+messageSource.getMessage(ConfigConstants.SUBSCRIBER_DIRECTORY, null, Locale.getDefault())+timestr+"/"+tr_id+"/";
			
			imagename=imagename+"."+imageType;
			actfilepath=actfilepath+imagename;
			ServletOutputStream oStream = response.getOutputStream();
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
			}
			 
			FileInputStream stream = null;
			InputStream stream1=null;
			try{
				System.err.println("actual path  " + actfilepath);
				if(hostname!=null && hostname.equals(messageSource.getMessage(ConfigConstants.HOST_NAME, null, Locale.getDefault()))){
					File f = new File(actfilepath);
				 	FileInputStream inStream = new FileInputStream(f);
				 	
				 	stream=inStream;
				 	
				 	byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			        while ((bytesRead = inStream.read(buffer)) != -1) {
			        	oStream.write(buffer, 0, bytesRead);
			        }
			        inStream.close();
			        oStream.close(); 
				}else{
					String remoteURL=messageSource.getMessage(ConfigConstants.PEER_URL, null, Locale.getDefault())+"getSubscriberImage"+"/"+tr_id+"/"+timestr+"/"+imagename;
					
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
				if (oStream != null)
				{
					//stream.close();
					oStream.close();
				}
				if (oStream != null)
				{
					//stream1.close();
					oStream.close();
				}
			}
			
			
			HttpHeaders headers = new HttpHeaders();
		    byte[] media = IOUtils.toByteArray(stream);
			return media;
	   }

	
	
	@RequestMapping(value = "action/OnSubscriber", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> takeActionOnSubscriber(Authentication authentication,
			@RequestBody SubscriberWorkFlowDto subscriberWorkFlowDto) throws BusinessException, SystemException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.actionOnSubscriber(subscriberWorkFlowDto);

		return responseBean;
	}
	
	@RequestMapping(value = "/getBarredSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getBarredSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getBarredSubscribers(searchSubscriberDto);

		return responseBean;
	}
	@RequestMapping(value = "/updateSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> updateSubscriber(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.updateSubscriber(subscriberUserDto, isReRegistation);

		return responseBean;
	}
	
	@RequestMapping(value = "/checkEntriesForIdNo/{idNumber}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<?> checkEntriesForIdNo(Authentication authentication,@PathVariable String idNumber)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.kycBusinessService.checkEntriesForIdNo(idNumber);
	}
	
	@RequestMapping(value = "/generateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> generateOtp(Authentication authentication,@RequestBody SubscriberDto subscriberDto)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.kycBusinessService.generatePassword(subscriberDto);
	}
	
	@RequestMapping(value = "/validateOtp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<?> validateOtp(Authentication authentication,@RequestBody SubscriberDto subscriberDto)
			throws BusinessException, SystemException {
		return (BaseResponse<?>) this.kycBusinessService.validateOtp(subscriberDto);
	}
	
	@RequestMapping(value = "/getApprovedSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getApprovedSubscriber(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, SystemException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getApprovedSubscribers(searchSubscriberDto);		

		return responseBean;
	}
	
	@RequestMapping(value = "/editRegisterSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> editRegisterSubscriber(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.editRegisterSubscriber(subscriberUserDto, isReRegistation);

		return responseBean;
	}
	
	@RequestMapping(value = "/registerExistingSubscriber", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> registerExistingSubscriber(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.registerExistingSubscriber(subscriberUserDto, isReRegistation);

		return responseBean;
	}
	@RequestMapping(value = "/registerExistingSubscriberWithNewData", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> registerExistingSubscriberWithNewData(Authentication authentication,
			@RequestBody SubscriberUserDto subscriberUserDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.registerExistingSubscriberWithNewData(subscriberUserDto, isReRegistation);

		return responseBean;
	}
	
	@RequestMapping(value = "/updateSubscriberForm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> updateSubscriberForm(Authentication authentication,
			@RequestBody SubscriberDto subscriberDto, @RequestParam (required = false) boolean isReRegistation ) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.updateSubscriberForm(subscriberDto, isReRegistation);

		return responseBean;
	}
	
	@RequestMapping(value = "/getOldApprovedSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getOldApprovedSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, JsonProcessingException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getOldApprovedSubscribers(searchSubscriberDto);
		return responseBean;
	}
	
	@RequestMapping(value = "/getNewApprovedSubscribers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> getNewApprovedSubscribers(Authentication authentication,
			@RequestBody SearchSubscriberDto searchSubscriberDto) throws BusinessException, JsonProcessingException {
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService
				.getNewApprovedSubscribers(searchSubscriberDto);
		return responseBean;
	}
	
	@RequestMapping(value = "/bulk/register", headers = ("content-type=multipart/*"))
	public ResponseEntity<byte[]> registerBulkNormalSubscriber(Authentication authentication,@RequestParam(value = "file", required = false) MultipartFile file)throws BusinessException, SystemException 
	{
		return this.kycBusinessService.registerbulkSubscriber(file);

	}*/
	
	@RequestMapping(value = "/activateAm", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SubscriberResponse> activateAm(Authentication authentication,@RequestBody SubscriberDto subscriberDto) throws BusinessException, SystemException, UserException {

		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.activateAm(subscriberDto);

		return responseBean;
	}
	
	@RequestMapping(value = "/validateSIM", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponse<SIMValidationResponse> validateSIM(@RequestBody SubscriberDto subscriberDto){
		EMAResponse eMAResponse=  integrationService.simValidation(subscriberDto.getMsisdn());
		
		/*EMAResponse eMAResponse = new EMAResponse();
		eMAResponse.setUnbar(true);
		eMAResponse.setResponse("RESP:0:HLRProfile,114:IMSI,645017740649977:SIM,8926001031106499777;");*/
		
		SIMValidationResponse simValidationResponse = new SIMValidationResponse();
		
		BaseResponse<SIMValidationResponse> baseResponse = new BaseResponse<SIMValidationResponse>();
		ResponseDto responseDto = new ResponseDto();
		if (eMAResponse.isUnbar() && eMAResponse.getResponse().contains(subscriberDto.getSimSerialNumber())) {
			simValidationResponse.setSIMValid(eMAResponse.isUnbar());
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("SIM validated successfully!");
			baseResponse.setBody(simValidationResponse);
			baseResponse.setResponseDto(responseDto);
		} else {
			simValidationResponse.setSIMValid(Boolean.FALSE);
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("Validation failed!");
			baseResponse.setResponseDto(responseDto);
			
		}
		return baseResponse;
	}
	
	@RequestMapping(value = "/biData", method = RequestMethod.GET, produces = "application/json")
	public@ResponseBody BaseResponse<SubscriberResponse> biData()
			throws Exception {		
		Date date=new Date();
		String currentDate=DateUtils.getFormatDate(date, "yyyyMMdd");
		int time=date.getHours();
		String hour=String.valueOf(time);
		String finalTime=currentDate+"_"+hour;
		BaseResponse<SubscriberResponse> responseBean = (BaseResponse<SubscriberResponse>) kycBusinessService.getApprovedSubscriber(finalTime);

		return responseBean; 		
	}

}
