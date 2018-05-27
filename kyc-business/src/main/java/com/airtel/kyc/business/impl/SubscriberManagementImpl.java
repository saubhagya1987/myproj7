package com.airtel.kyc.business.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
/*import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
*/import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.airtel.kyc.business.SubscriberManagement;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.business.service.SubscriberManagementService;
import com.airtel.kyc.business.service.impl.TaskManagementServiceImpl;
import com.airtel.kyc.business.service.impl.UserManagementServiceImpl;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ResponseCodes;
import com.airtel.kyc.enumData.SubscriberDocumentType;
import com.airtel.kyc.enumData.SubscriberExceptionCodes;
import com.airtel.kyc.enumData.TaskEnum;
import com.airtel.kyc.enumData.UserExceptionCodes;
import com.airtel.kyc.helpers.dto.AddressDto;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberDocumentsDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberIdDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberSearchResponseDto;
import com.airtel.kyc.helpers.dto.SubscriberUserDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.helpers.dto.TotalCountDto;
import com.airtel.kyc.helpers.response.SubscriberResponse;
import com.airtel.kyc.helpers.response.TotalCountResponse;
import com.airtel.kyc.repository.dao.CMDao;
import com.airtel.kyc.repository.dao.ConfigDataDAO;
import com.airtel.kyc.repository.dao.CountryDao;
import com.airtel.kyc.repository.dao.DistrictDao;
import com.airtel.kyc.repository.dao.SubscriberDao;
import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.entity.Assignment;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.repository.entity.Country;
import com.airtel.kyc.repository.entity.District;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.utils.FileFolderUtils;
import com.airtel.kyc.utils.KycResponseUtils;
import com.airtel.kyc.utils.Utility;
import com.airtel.user.helper.constant.ConfigConstants;
import com.airtel.user.helper.dto.UsersDto;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Service
public class SubscriberManagementImpl implements SubscriberManagement, KycConstants {
	private static final String SECURITY_CODE_INVALID = "security.code.invalid";
	private static final String BI_FILE_PATH = "bi.file.path";
	
	@Autowired
	private SubscriberManagementService subscriberManagementService;
	
	@Autowired
	private SubscriberDao subscriberDao;
	
	@Autowired
	private SubscriberDetailsDao subscriberDetailsDao;
	
	@Autowired
	private ConfigDataDAO configDataDAO;
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Environment env;
	
	@Autowired
	DistrictDao districtDao;
	
	@Autowired
	CountryDao countryDao;
	
	@Autowired
	private AppConstants appConstants;
	
	@Autowired
	private CMDao cMDao;

	/*private final Log logger = LogFactory.getLog(SubscriberManagementImpl.class);
	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberManagementImpl.class);
	private final boolean isDebugEnabled = logger.isDebugEnabled();*/
	private static Logger logger = Logger.getLogger(SubscriberManagementImpl.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();

	@Override
	public BaseResponse<?> registerSubscriber(SubscriberUserDto subscriberUserDto, boolean isReRegistation)
			throws BusinessException, SystemException, UserException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		SubscriberDto subscriberDto=subscriberUserDto.getSubscriberData();
		
		String ipAddress=subscriberUserDto.getSubscriberData().getIpAddress();
		String handsetImei=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getImei();
		String placeOfBirth=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getPlaceOfBirth();
		
		UsersDto usersDto=subscriberUserDto.getUsersDto();
		
		String idPath=null;
		String idImage=null;
		Set<SubscriberIdDetailsDto> subscriberIdDetails = subscriberDto.getSubscriberDetails().get(0).getSubscriberIdDetails();
		for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberIdDetails) {
			idPath=subscriberIdDetailsDto.getIdPath();	
			idImage=subscriberIdDetailsDto.getIdImageName();
			
		}
		if(idPath!=null && idImage!=null){
		RestTemplate restTemplate = new RestTemplate();	
		//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
		//String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());//KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
		String url=env.getProperty(ConfigConstants.BASEIMAGE_URL);
	    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
	    		.path("/")
	    		.path(idPath)
	    		.path("/")
	    		.path(subscriberDto.getPrevKycTansactionId())
	    		.path("/")
	    		.path(idImage)
	    		.path(".jpg")
	    	    .build()
	    	    .toUri();
	    System.out.println("targetUrl:"+targetUrl);		
	    HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
	    //System.out.println("body:"+response.getBody());
	    byte[] body = response.getBody();
	    
	    System.out.println("body:"+body);
	 
	    //String base64 = new String(Base64.encodeBase64(bytes));
	    String base64 = null;
		try {
			base64 = new String(Base64.encodeBase64(body), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("base64:"+base64);
	    
	    for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberIdDetails) {
	    	if(subscriberIdDetailsDto.getSubscriberImageId()==null)
	    	{
	    		subscriberIdDetailsDto.setSubscriberImageId(base64);	
	    	}
	    	
		}
		}
		/*
		 * check if subscriber is already in system with the same msisdn & sim
		 * serial
		 */
		//Subscriber susbscriber=subscriberDao.getSubscriberObj(subscriberDto.getMsisdn());
		List<SubscriberDetails> SubscriberDetails=subscriberDetailsDao.getSubscriberDetailsByMobileNo(subscriberDto.getMsisdn());
		for (SubscriberDetails subscriberDetails : SubscriberDetails) {
			if(subscriberDetails.getIsOldUserDetails()==0){
				if (!isReRegistation && SubscriberDetails!=null && !subscriberDetails.getFinalStatus().equals("REJECTED")) {
					responseDto = KycResponseUtils.getKycResponse(
							ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseCode(),
							ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseDescription()
									+ subscriberDto.getMsisdn());
					subscriberRespons.setResponseDto(responseDto);
					return subscriberRespons;
				}
			}
			
		}
		
		Integer count = 0;
		String idNumber =subscriberDto.getSubscriberDetails().get(0).getSubscriberIdDetails().iterator().next().getIdNumber();
		List<SubscriberIdDetailsDto> subscriberIdDetailsList = this.subscriberManagementService.getUserByName(idNumber);
		if(subscriberIdDetailsList != null) {
			count=subscriberIdDetailsList.size();
			ConfigData data=configDataDAO.getNoOFConnectionPerId(KycConstants.SIM_COUNT);
			if(count>=Integer.valueOf(data.getConfigDataValue())){
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM.getResponseCode(),
						ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM.getResponseDescription()
						+ "for Id Number : "  + idNumber);
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
						
		}
		
		
		
		
		try {
			
			if(isReRegistation) {
				SubscriberDto oldDto = new SubscriberDto();
				//update Subscriber
				oldDto.setSubscriberId(subscriberDto.getSubscriberId());
				//oldDto.setIsOldSubscriber(KycConstants.TRUE);				
				this.subscriberManagementService.updateSubscriber(oldDto,null,ipAddress,handsetImei,placeOfBirth);
				
				//add user
				subscriberDto.setParentSubscriberId(subscriberDto.getSubscriberId().intValue());
				subscriberDto.setSubscriberId(null);
				//subscriberDto.setIsOldSubscriber(KycConstants.FALSE);
				subscriberDto = (SubscriberDto) subscriberManagementService.addSubscriber(subscriberDto,usersDto,subscriberUserDto,ipAddress,handsetImei,placeOfBirth);
			} else {
				// Add subscriber
				//subscriberDto.setIsOldSubscriber(KycConstants.FALSE);
				subscriberDto = (SubscriberDto) subscriberManagementService.addSubscriber(subscriberDto,usersDto,subscriberUserDto,ipAddress,handsetImei,placeOfBirth);
			}
			
			
			if (subscriberDto == null) {
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Unable to create subscriber");
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
			
			
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data save sucessfully");
		} catch (BusinessException cause) {
			responseDto = KycResponseUtils.getKycResponse(cause.getExceptionCode(), cause.getMessage());
		}

		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}
	
	@Override
	public BaseResponse<?> getSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
				searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
				
				SubscriberSearchResponseDto searchResponseDto = subscriberManagementService
						.getSubscriber(searchSubscriberDto, actionType);
				
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscribers data fetched sucessfully");
				subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
			//}
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@Override
	public BaseResponse<?> getCMSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
				searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
				searchSubscriberDto.setAction("PENDING");
				
				if("CM".equals(searchSubscriberDto.getRoleName())){
					Assignment assignment = cMDao.getAssignmentDetail(searchSubscriberDto.getUserId()+"");
					SubscriberResponse subscriberResponse=null;
					if(assignment.getCount()==0){
						subscriberResponse = (SubscriberResponse) assignToMe(searchSubscriberDto, actionType);
					}
				}
				SubscriberSearchResponseDto searchResponseDto= subscriberManagementService
						.getCMSubscriber(searchSubscriberDto, actionType);
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscribers data fetched sucessfully");
				subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
			//}
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			cause.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@Override
	public BaseResponse<?> assignToMe(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
				searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
				searchSubscriberDto.setAction("PENDING");
				
				SubscriberSearchResponseDto searchResponseDto = subscriberManagementService
						.assignToMe(searchSubscriberDto, actionType);
				
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscribers assigned sucessfully");
				subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
			//}
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			cause.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@Override
	public BaseResponse<?> getSubscriberBySubscriberId(Long subscriberId) throws BusinessException {
		// TODO Auto-generated method stub
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			SubscriberDto subscriberDto = (SubscriberDto) subscriberManagementService
					.getSubscriberBySubscriberId(subscriberId, KycConstants.FALSE);

			// responseDto = new
			// ResponseDto(ResponseCodes.SUCCESS_RESPONSE_CODE.getResponseCode(),
			// ResponseCodes.SUCCESS_RESPONSE_CODE.getResponseDescription());
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
			subscriberRespons.setSubscriberDto(subscriberDto);
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");

		}

		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<SubscriberResponse> actionOnSubscriber(SubscriberWorkFlowDto subscriberWorkFlowDto)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			subscriberWorkFlowDto = (SubscriberWorkFlowDto) subscriberManagementService
					.actionOnSubscriber(subscriberWorkFlowDto);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber Asigned sucessfully");
		} catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}
	
	@Override
	public BaseResponse<?> getSubscriber(SearchSubscriberDto searchSubscriberDto) throws BusinessException {

		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			SubscriberSearchResponseDto searchResponseDto = subscriberManagementService.getSubscriber(searchSubscriberDto,KycConstants.FALSE);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
			subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
		} catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<TotalCountResponse> getSubscriberCount(int userId, String roleName) throws BusinessException {
		TotalCountDto totalCountDto = new TotalCountDto();
		ResponseDto responseDto = null;
		TotalCountResponse totalCountResponse = new TotalCountResponse();
		try {
			totalCountDto = subscriberManagementService.getSubscriberCount(userId, roleName);

			totalCountResponse.setTotalCountDto(totalCountDto);

			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
		} catch (Exception cause) {
			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		
		totalCountResponse.setResponseDto(responseDto);
		return totalCountResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<SubscriberResponse> getBarredSubscribers(SearchSubscriberDto searchSubscriberDto)
			throws BusinessException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			SubscriberSearchResponseDto searchResponseDto = subscriberManagementService
					.getBarredSubscribers(searchSubscriberDto,KycConstants.FALSE);
			if(searchResponseDto.getRows().size()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
				subscriberResponse.setSubscriberSearchResponseDto(searchResponseDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Subscriber data Not Exist");
				subscriberResponse.setSubscriberSearchResponseDto(searchResponseDto);
			}
		} catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<SubscriberResponse> updateSubscriber(SubscriberUserDto subscriberUserDto,boolean isReRegistation) throws BusinessException, SystemException, UserException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		String caseType=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getCaseType();
		String newMsisdn=subscriberUserDto.getSubscriberData().getNewMsisdn();
		String imageEdited=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getIsImageEdited();
		String proxyForRegistration=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getProxyForRegistration();
		
		String submitedOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSubmitedOn();
		String syncedOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSyncedOn();
		String registeredOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getRegisteredOn();
		String onlineOfflineFlag=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getOnlineOfflineFlag();
		String ipAddress=subscriberUserDto.getSubscriberData().getIpAddress();
		String handsetImei=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getImei();
		String placeOfBirth=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getPlaceOfBirth();
		try{
			this.subscriberManagementService.updateSubscriber(subscriberUserDto.getSubscriberData(),imageEdited,ipAddress,handsetImei,placeOfBirth);
			
			SubscriberDto subscriberDto=subscriberUserDto.getSubscriberData();
			UsersDto usersDto=subscriberUserDto.getUsersDto();
			if(subscriberDto.getNewMsisdn()!=null){
				Subscriber subscriber=subscriberDao.getSubscriberBySubscriberId(subscriberDto.getSubscriberId());
				subscriberDto.setKycTansactionId(subscriber.getKycTansactionId());
				subscriberManagementService.addExistingSubscriberWithNewdata(subscriberDto,usersDto,subscriberUserDto,caseType,newMsisdn,proxyForRegistration,submitedOn,syncedOn,onlineOfflineFlag,registeredOn,null,ipAddress,handsetImei,placeOfBirth);
			}
			
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber updated sucessfully");
		}
		catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<?> checkEntriesForIdNo(String idNumber) throws BusinessException, SystemException {
		@SuppressWarnings("rawtypes")
		BaseResponse baseResponse = new BaseResponse();
		ResponseDto responseDto = null;		
		Map<String , Integer> map = new HashMap<>();
		Integer count = 0;
		responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
		List<SubscriberIdDetailsDto> subscriberIdDetailsList = this.subscriberManagementService.getUserByName(idNumber);
		if(subscriberIdDetailsList != null) {
			count=subscriberIdDetailsList.size();
			ConfigData data=configDataDAO.getNoOFConnectionPerId(KycConstants.SIM_COUNT);
			if(count<=Integer.valueOf(data.getConfigDataValue())){
				responseDto.setResponseDescription("SubScriber is able to take new SIM.");
			}
			else{
				responseDto.setResponseDescription("SubScriber has already taken allotted SIM.");	
			}			
		}
		else{
			responseDto.setResponseDescription("SubScriber is able to take new SIM.");
		}
		
		baseResponse.setResponseDto(responseDto);
		map.put("idNumberCount", count);
		baseResponse.setBody(map);
		return baseResponse;
	}

	@Override
	public BaseResponse<?> generatePassword(SubscriberDto subscriberData) throws BusinessException, SystemException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		SubscriberDto subscriberDto = null;
		try {
			subscriberDto = this.subscriberManagementService.generatePassword(subscriberData);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			subscriberResponse.setSubscriberDto(subscriberDto);
		} catch (Exception e) {			
			responseDto = KycResponseUtils.getKycResponse(
					SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionCode(),
					SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionDescription());
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@Override
	public BaseResponse<?> validateOtp(SubscriberDto subscriberData) throws BusinessException, SystemException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		SubscriberDto subscriberDto = null;
		try {
			subscriberDto = this.subscriberManagementService.validateOtp(subscriberData);
			//if(!subscriberDto.getMessage().equals(messageSource.getMessage(SECURITY_CODE_INVALID, null, Locale.getDefault())))
			if(!subscriberDto.getMessage().equals(env.getProperty(SECURITY_CODE_INVALID)))
			{
			    responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK,subscriberDto.getMessage());
			    responseDto.setBody(subscriberDto.getMsisdn());
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(UserExceptionCodes.SECURITY_CODE_EXPIRED.getExceptionCode(),subscriberDto.getMessage());				
			}
			/*responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			subscriberResponse.setSubscriberDto(subscriberDto);*/
		} catch (Exception e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					SubscriberExceptionCodes.SECURITY_CODE_EXPIRED.getExceptionCode(),
					SubscriberExceptionCodes.SECURITY_CODE_EXPIRED.getExceptionDescription());
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<SubscriberResponse> getApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)	throws BusinessException, SystemException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			/*SubscriberSearchResponseDto searchResponseDto = subscriberManagementService.getApprovedSubscribers(searchSubscriberDto,KycConstants.FALSE,KycConstants.FINAL_STATUS);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
			subscriberResponse.setSubscriberSearchResponseDto(searchResponseDto);*/
			SubscriberSearchResponseDto searchResponseDto = subscriberManagementService.getApprovedSubscribers(searchSubscriberDto,KycConstants.FALSE,KycConstants.FINAL_STATUS);
			if(searchResponseDto.getRows().size()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
				subscriberResponse.setSubscriberSearchResponseDto(searchResponseDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Subscriber data Not Exist");
				subscriberResponse.setSubscriberSearchResponseDto(searchResponseDto);
			}
		} catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;	
		}

	@Override
	public BaseResponse<SubscriberResponse> editRegisterSubscriber(SubscriberUserDto subscriberUserDto,
			boolean isReRegistation) throws BusinessException, SystemException, UserException {
		SubscriberDto subscriber=this.subscriberManagementService.getSubscriberData(subscriberUserDto.getSubscriberData().getMsisdn());
		
		return null;
	}

	@Override
	public BaseResponse<?> registerExistingSubscriber(SubscriberUserDto subscriberUserDto,
			boolean isReRegistation) throws BusinessException, SystemException, UserException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		SubscriberDto subscriberDto=subscriberUserDto.getSubscriberData();
		UsersDto usersDto=subscriberUserDto.getUsersDto();	
		String amAccount=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getAmAccount();
		String isMinor=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getIsMinor();
		String latitude=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getLatitude();
		String longitude=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getLongitude();
		String channelPartnerCellId=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getChannelPartnerCellId();
		String occupation=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getOccupation();
		String minorName=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getMinorName();
		String caseType=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getCaseType();
		String proxyForRegistration=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getProxyForRegistration();
		
		String submitedOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSubmitedOn();
		String syncedOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSyncedOn();
		String registeredOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getRegisteredOn();
		String onlineOfflineFlag=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getOnlineOfflineFlag();
		String simSerialNo=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSimSerialNumber();
		String ipAddress=subscriberUserDto.getSubscriberData().getIpAddress();
		String handsetImei=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getImei();		
		String idImageBackData=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSubscriberIdDetails().iterator().next().getIdImageBackData();
		String placeOfBirth=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getPlaceOfBirth();
		
		
		Subscriber susbscriber=subscriberDao.getSubscriberObj(subscriberDto.getMsisdn());
		
		//Subscriber newSusbscriber=subscriberDao.getSubscriberObj(subscriberDto.getNewMsisdn());
		
		/*if (!isReRegistation && newSusbscriber!=null) {
			responseDto = KycResponseUtils.getKycResponse(
					ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseCode(),
					ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseDescription()
							+ subscriberDto.getMsisdn());
			subscriberRespons.setResponseDto(responseDto);
			return subscriberRespons;
		}*/
		
		List<SubscriberDetails> SubscriberDetails=subscriberDetailsDao.getSubscriberDetailsByMobileNo(subscriberDto.getNewMsisdn());
		for (SubscriberDetails subscriberDetails : SubscriberDetails) {
			if(subscriberDetails.getIsOldUserDetails()==0){
				if (!isReRegistation && SubscriberDetails!=null && !subscriberDetails.getFinalStatus().equals("REJECTED")) {
					responseDto = KycResponseUtils.getKycResponse(
							ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseCode(),
							ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseDescription()
									+ subscriberDto.getMsisdn());
					subscriberRespons.setResponseDto(responseDto);
					return subscriberRespons;
				}
			}
			
		}
		
		
		Integer count = 0;
		String idNumber =subscriberDto.getSubscriberDetails().get(0).getSubscriberIdDetails().iterator().next().getIdNumber();
		List<SubscriberIdDetailsDto> subscriberIdDetailsList = this.subscriberManagementService.getUserByName(idNumber);
		if(subscriberIdDetailsList != null) {
			count=subscriberIdDetailsList.size();
			ConfigData data=configDataDAO.getNoOFConnectionPerId(KycConstants.SIM_COUNT);
			if(count>=Integer.valueOf(data.getConfigDataValue())){
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM.getResponseCode(),
						ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM.getResponseDescription()
						+ "for Id Number : "  + idNumber);
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
						
		}
		
		
		
		try {
			BeanUtils.copyProperties(susbscriber, subscriberDto);
			if(isReRegistation) {
				SubscriberDto oldDto = new SubscriberDto();
				//update Subscriber
				oldDto.setSubscriberId(subscriberDto.getSubscriberId());
				//oldDto.setIsOldSubscriber(KycConstants.TRUE);				
				this.subscriberManagementService.updateSubscriber(oldDto,null,ipAddress,handsetImei,placeOfBirth);
				
				subscriberDto = (SubscriberDto) subscriberManagementService.addExistingSubscriber(subscriberDto,usersDto,subscriberUserDto,amAccount,isMinor,latitude,longitude,channelPartnerCellId,occupation,minorName,caseType,proxyForRegistration,submitedOn,syncedOn,onlineOfflineFlag,registeredOn,idImageBackData,simSerialNo,ipAddress,handsetImei,placeOfBirth);
			} else {
				// Add subscriber				
				subscriberDto = (SubscriberDto) subscriberManagementService.addExistingSubscriber(subscriberDto,usersDto,subscriberUserDto,amAccount,isMinor,latitude,longitude,channelPartnerCellId,occupation,minorName,caseType,proxyForRegistration,submitedOn,syncedOn,onlineOfflineFlag,registeredOn,idImageBackData,simSerialNo,ipAddress,handsetImei,placeOfBirth);
			}
			
			
			if (subscriberDto == null) {
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Unable to create subscriber");
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
			
			
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data save sucessfully");
		} catch (BusinessException cause) {
			responseDto = KycResponseUtils.getKycResponse(cause.getExceptionCode(), cause.getMessage());
		}

		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@Override
	public BaseResponse<?> registerExistingSubscriberWithNewData(SubscriberUserDto subscriberUserDto,
			boolean isReRegistation) throws BusinessException, SystemException, UserException {

		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		SubscriberDto subscriberDto=subscriberUserDto.getSubscriberData();
		UsersDto usersDto=subscriberUserDto.getUsersDto();	
		String caseType=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getCaseType();
		String newMsisdn=subscriberUserDto.getSubscriberData().getNewMsisdn();
		String proxyForRegistration=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getProxyForRegistration();
		
		String submitedOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSubmitedOn();
		String syncedOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSyncedOn();
		String registeredOn=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getRegisteredOn();
		String onlineOfflineFlag=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getOnlineOfflineFlag();
		String physicalFormId=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getSubscriberIdDetails().iterator().next().getPhysicalFormId();
		String ipAddress=subscriberUserDto.getSubscriberData().getIpAddress();
		String handsetImei=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getImei();	
		String placeOfBirth=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getPlaceOfBirth();
		
		
		Subscriber susbscriber=subscriberDao.getSubscriberObj(subscriberDto.getMsisdn());	
		//Subscriber newSusbscriber=subscriberDao.getSubscriberObj(subscriberDto.getNewMsisdn());
		/*if (!isReRegistation && newSusbscriber!=null) {
			responseDto = KycResponseUtils.getKycResponse(
					ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseCode(),
					ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseDescription()
							+ subscriberDto.getMsisdn());
			subscriberRespons.setResponseDto(responseDto);
			return subscriberRespons;
		}*/
		List<SubscriberDetails> SubscriberDetails=subscriberDetailsDao.getSubscriberDetailsByMobileNo(subscriberDto.getNewMsisdn());
		for (SubscriberDetails subscriberDetails : SubscriberDetails) {
			if(subscriberDetails.getIsOldUserDetails()==0){
				if (!isReRegistation && SubscriberDetails!=null && !subscriberDetails.getFinalStatus().equals("REJECTED")) {
					responseDto = KycResponseUtils.getKycResponse(
							ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseCode(),
							ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseDescription()
									+ subscriberDto.getMsisdn());
					subscriberRespons.setResponseDto(responseDto);
					return subscriberRespons;
				}
			}
			
		}
		
		Integer count = 0;
		String idNumber =subscriberDto.getSubscriberDetails().get(0).getSubscriberIdDetails().iterator().next().getIdNumber();
		List<SubscriberIdDetailsDto> subscriberIdDetailsList = this.subscriberManagementService.getUserByName(idNumber);
		if(subscriberIdDetailsList != null) {
			count=subscriberIdDetailsList.size();
			ConfigData data=configDataDAO.getNoOFConnectionPerId(KycConstants.SIM_COUNT);
			if(count>=Integer.valueOf(data.getConfigDataValue())){
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM.getResponseCode(),
						ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM.getResponseDescription()
						+ "for Id Number : "  + idNumber);
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
						
		}
		
		
		try {
			BeanUtils.copyProperties(susbscriber, subscriberDto);
			if(isReRegistation) {
				SubscriberDto oldDto = new SubscriberDto();
				//update Subscriber
				oldDto.setSubscriberId(subscriberDto.getSubscriberId());
				//oldDto.setIsOldSubscriber(KycConstants.TRUE);				
				this.subscriberManagementService.updateSubscriber(oldDto,null,ipAddress,handsetImei,placeOfBirth);
				
				subscriberDto = (SubscriberDto) subscriberManagementService.addExistingSubscriberWithNewdata(subscriberDto,usersDto,subscriberUserDto,caseType,newMsisdn,proxyForRegistration,submitedOn,syncedOn,onlineOfflineFlag,registeredOn,physicalFormId,ipAddress,handsetImei,placeOfBirth);
			} else {
				// Add subscriber				
				subscriberDto = (SubscriberDto) subscriberManagementService.addExistingSubscriberWithNewdata(subscriberDto,usersDto,subscriberUserDto,caseType,newMsisdn,proxyForRegistration,submitedOn,syncedOn,onlineOfflineFlag,registeredOn,physicalFormId,ipAddress,handsetImei,placeOfBirth);
			}
			
			
			if (subscriberDto == null) {
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Unable to create subscriber");
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
			
			
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data save sucessfully");
		} catch (BusinessException cause) {
			responseDto = KycResponseUtils.getKycResponse(cause.getExceptionCode(), cause.getMessage());
		}

		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<SubscriberResponse> updateSubscriberForm(SubscriberDto subscriberDto, boolean isReRegistation)
			throws BusinessException, SystemException, UserException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		try{
			this.subscriberManagementService.updateSubscriberForm(subscriberDto);
			
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber updated sucessfully");
		}
		catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@Override
	public BaseResponse<SubscriberResponse> getOldApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			//searchSubscriberDto.setIsOldSubscriber(KycConstants.FALSE);
			searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
			
			SubscriberSearchResponseDto searchResponseDto = subscriberManagementService.getOldApprovedSubscriber(searchSubscriberDto);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscribers data fetched sucessfully");
			subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@Override
	public BaseResponse<SubscriberResponse> getNewApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			//searchSubscriberDto.setIsOldSubscriber(KycConstants.FALSE);
			searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
			
			SubscriberSearchResponseDto searchResponseDto = subscriberManagementService.getNewApprovedSubscribers(searchSubscriberDto);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscribers data fetched sucessfully");
			subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}
	
	@SuppressWarnings({  "rawtypes" })
	@Override
	public ResponseEntity<byte[]> registerbulkSubscriber(MultipartFile  template)
			throws BusinessException, SystemException {
		String methodName = "registerblukSubscriber(MultipartFile  template) ";
		BaseResponse baseResponse = new BaseResponse<>();
		try {
			InputStream inputStream = template.getInputStream();
			String outputUrl = FileFolderUtils.extractAll(inputStream, this.env.getProperty("com.airtel.location.dir"));
			outputUrl = outputUrl + "bulk_subscriber";//+ File.separator ;
			///D:/bulk_upload/subscriber1502092045922bulk_subscriber\
			//outputUrl = outputUrl + File.separator ;
			logger.debug(methodName+": Output folder : "+outputUrl);
			String url=outputUrl+"/";
			FileInputStream fileInputStream = new FileInputStream(url+"bulk_subscriber_template.csv");
			//List<Map<String, Object>> list = Utility.getMapFromExcel(fileInputStream);
			List<Map<String, String>> list =  Utility.getMapFromCSV(fileInputStream);
			
			if (list == null || list.isEmpty()) {
				ResponseDto dto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE_UPLOAD_FILE_EMPTY.getResponseCode(),
						ResponseCodes.FAILURE_RESPONSE_CODE_UPLOAD_FILE_EMPTY.getResponseDescription());
				baseResponse.setResponseDto(dto);
				// return baseResponse;
			}
			logger.debug(methodName+":MSISDN's to register : "+list.size());
			List<Map<String, Object>> responseList = new ArrayList<>();
			for (Map<String, String> map : list) {
				Map<String, Object> temp = new LinkedHashMap<>();
				SubscriberDto dto = getSubscriberObject(map, url);
				SubscriberUserDto subscriberUserDto=new SubscriberUserDto();
				subscriberUserDto.setSubscriberData(dto);
				if (dto != null) {
					dto.setKycMode("api");
					BaseResponse baseResponse2 =null;
					try{
						 baseResponse2 = registerBulkSubscriber(subscriberUserDto, false);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					temp.put(TEMPLATE_MSISDN, dto.getMsisdn());
					//temp.put(TEMPLATE_SIM_SERIAL_NUMBER, dto.getSimSerialNumber());
					temp.put("response_code", baseResponse2.getResponseDto().getResponseCode());
					temp.put("response_description", baseResponse2.getResponseDto().getResponseDescription());
					//logger.debug(methodName+": MSISDN : "+dto.getMsisdn()+": SIM : "+dto.getSimSerialNumber());
					logger.debug(methodName+": response_code : "+baseResponse2.getResponseDto().getResponseCode()+": response_description : "+baseResponse2.getResponseDto().getResponseDescription());
					responseList.add(temp);
				}
			}
			String displayName = "bulkresponse_" + System.currentTimeMillis() + ".xlsx";
			FileOutputStream outputStream = new FileOutputStream(
					new File(this.env.getProperty(TEMP_FILE_LOCATION) + displayName));
			// create report
			Utility.generateExcel(responseList, displayName, outputStream);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Disposition", "attachment; filename=\"" + displayName);
			headers.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
			byte[] media = IOUtils.toByteArray(new FileInputStream(new File(this.env.getProperty(TEMP_FILE_LOCATION)+ displayName)));
			return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	private SubscriberDto getSubscriberObject(Map<String, String> map, String outputUrl) {
		String methodName = " getSubscriberObject(Map<String, String> map, String outputUrl) ";
		if (map == null || map.isEmpty()) {
			return null;
		}
		SubscriberDto dto = new SubscriberDto();
		String msisdn = map.get(TEMPLATE_MSISDN);
		
		if (msisdn != null)
			dto.setMsisdn(msisdn);
		
		dto.setKycTansactionId(System.currentTimeMillis()+"");
		// Subscriber detail set up
		List<SubscriberDetailsDto> detailsDtos = new ArrayList<>();
		SubscriberDetailsDto detailsDto = new SubscriberDetailsDto();
		detailsDto.setSimSerialNumber((String) map.get(TEMPLATE_SIM_SERIAL_NUMBER));
		detailsDto.setFirstName((String) map.get(TEMPLATE_FIRSTNAME));
		//detailsDto.setMiddleName((String) map.get(TEMPLATE_MIDDLENAME));
		detailsDto.setLastName((String) map.get(TEMPLATE_LASTNAME));
		detailsDto.setAlternateNo((String) map.get(TEMPLATE_ALTERNATE_NO));
		detailsDto.setEmail((String) map.get(TEMPLATE_EMAIL_ID));
		detailsDto.setAmAccount((String) map.get(TEMPLATE_AM_ACCOUNT));
		detailsDto.setIsMinor((String) map.get(TEMPLATE_IS_MINOR));
		detailsDto.setChannelPartnerCellId((String) map.get(TEMPLATE_CELL_ID));
		detailsDto.setLatitude((String) map.get(TEMPLATE_LATITUDE));
		detailsDto.setLongitude((String) map.get(TEMPLATE_LONGITUDE));
		detailsDto.setGender((String) map.get(TEMPLATE_GENDER));
		detailsDto.setBulkSubscriberFlag(Integer.parseInt(map.get(TEMPLATE_BULK_SUBSCRIBER_FLAG)));
		detailsDto.setProxyForRegistration((String) map.get(TEMPLATE_PROXY_FOR_REGISTRATION));
		detailsDto.setPlaceOfBirth((String) map.get(TEMPLATE_PLACE_OF_BIRTH));
		String dob=(String) map.get(TEMPLATE_dOB);
		
		if(dob!=null)
			detailsDto.setDateOfBirth(dob );
		
		// subscriber Document set up
		List<SubscriberDocumentsDto> documentsDtos = new ArrayList<>();
		
		
		String certNo= map.get(TEMPLATE_CERTIFICATE_INC_NUMBER);
		
		if(certNo!=null && certNo.length()>0)
		{
			SubscriberDocumentsDto documents1 = new SubscriberDocumentsDto();
			//documents1.setDocumentImageData(documentImageData);
			documents1.setDocumentNoType(SubscriberDocumentType.CERTIFICATE_INCORPORATATION.toString());
			documents1.setDocumentNo(map.get(TEMPLATE_CERTIFICATE_INC_NUMBER));
			documents1.setDocumentType(TEMPLATE_CERTIFICATE_INC_NUMBER);
			//String ciNumber = outputUrl+TEMPLATE_CERTIFICATE_INC_NUMBER+File.separator+msisdn+".jpg";
			String ciNumber = outputUrl+TEMPLATE_CERTIFICATE_INC_NUMBER+File.separator+certNo+".jpg";
			
			File file = new File(ciNumber);
			try {
				if(file != null)
					documents1.setDocumentName(file.getName());
				documents1.setDocumentImageData(FileFolderUtils.encodeFileToBase64Binary(file));
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file : ");
			}
			
			documents1.setIsOldIdDetails(KycConstants.FALSE);
			documentsDtos.add(documents1);
		}
		
	
		String busNO= map.get(TEMPLATE_BUSINESS_LICENCE_NUMBER);
		
		if(busNO!=null && busNO.length()>0)
		{
			logger.debug(methodName+": set business licence : "+map.get(TEMPLATE_BUSINESS_LICENCE_NUMBER));
			SubscriberDocumentsDto documents2 = new SubscriberDocumentsDto();
			documents2.setDocumentNoType(SubscriberDocumentType.BUSSNISS_NUMBER.toString());
			documents2.setDocumentNo(map.get(TEMPLATE_BUSINESS_LICENCE_NUMBER));
			documents2.setDocumentType(TEMPLATE_BUSINESS_LICENCE_NUMBER);
			//String blNumber = outputUrl+TEMPLATE_BUSINESS_LICENCE_NUMBER+File.separator+msisdn+".jpg";
			String blNumber = outputUrl+TEMPLATE_BUSINESS_LICENCE_NUMBER+File.separator+busNO+".jpg";
			File file1 = new File(blNumber);
			try {
				if(file1 != null)
					documents2.setDocumentName(file1.getName());
				documents2.setDocumentImageData(FileFolderUtils.encodeFileToBase64Binary(file1));
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file : "+blNumber);
			}
			documents2.setIsOldIdDetails(KycConstants.FALSE);
			documentsDtos.add(documents2);
		}
		
		
		String tin= map.get(TEMPLATE_TIN_NUMBER);
		
		if(tin!=null && tin.length()>0)
		{
			SubscriberDocumentsDto documents3 = new SubscriberDocumentsDto();
			documents3.setDocumentNoType(SubscriberDocumentType.TIN.toString());
			documents3.setDocumentNo(map.get(TEMPLATE_TIN_NUMBER));
			documents3.setDocumentType(TEMPLATE_TIN_NUMBER);
			//String tinNumber = outputUrl+TEMPLATE_TIN_NUMBER+File.separator+msisdn+".jpg";
			String tinNumber = outputUrl+TEMPLATE_TIN_NUMBER+File.separator+tin+".jpg";
			File fileTin = new File(tinNumber);
			logger.debug(methodName+": Tin documnet fetched : "+tinNumber);
			try {
				if(fileTin != null)
					documents3.setDocumentName(fileTin.getName());
				documents3.setDocumentImageData(FileFolderUtils.encodeFileToBase64Binary(fileTin));
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file.");
			}
			
			documents3.setIsOldIdDetails(KycConstants.FALSE);
			documentsDtos.add(documents3);
		}
		
		

		// subscriber Id details set up
		Set<SubscriberIdDetailsDto> subscriberIdDetails = new HashSet<>();
		SubscriberIdDetailsDto idDetail = new SubscriberIdDetailsDto();
		/*String idNumber=map.get(TEMPLATE_IDENTIFICATION_NUMBER);
		if(idNumber!=null && idNumber.length()>0)
		{
			
			
			idDetail.setIdNumber(idNumber);
			
			//String ifront = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+msisdn+"_front.jpg";
			//String iback = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+msisdn+"_front.jpg";
			
			
			String ifront = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+idNumber+"_front.jpg";
			String iback = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+idNumber+"_back.jpg";
			
			
			File ifrontFront = new File(ifront);
			File ifrontback = new File(iback);
			logger.debug(methodName+": ID documnet fetched : "+ifrontFront);
			try {
				
				idDetail.setIdImageBackData(FileFolderUtils.encodeFileToBase64Binary(ifrontback));
				idDetail.setIdImageBack(map.get(TEMPLATE_IDENTIFICATION_TYPE)+"_back");
				
				idDetail.setIdImageFrontData(FileFolderUtils.encodeFileToBase64Binary(ifrontFront));
				idDetail.setIdImageFront(map.get(TEMPLATE_IDENTIFICATION_TYPE)+"_front");
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file.");
			}

			idDetail.setIdType(map.get(TEMPLATE_IDENTIFICATION_TYPE));
			
			
			//subscriberIdDetails.add(idDetail);
		}*/
		
		String idNumber=map.get(TEMPLATE_IDENTIFICATION_NUMBER);
		idDetail.setIdNumber(idNumber);
		idDetail.setIdType(map.get(TEMPLATE_IDENTIFICATION_TYPE));
		
		String idImageFront=map.get(TEMPLATE_ID_IMAGE_FRONT);
		if(idImageFront!=null && idImageFront.length()>0)
		{
			
			
			String idImageFrontId = outputUrl+TEMPLATE_ID_IMAGE_FRONT+File.separator+idImageFront+".jpg";
			//String iback = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+idNumber+"_front.jpg";
			
			
			File idImageFrontIdObj = new File(idImageFrontId);
			//File ifrontback = new File(iback);
			logger.debug(methodName+": idImageFrontIdObj fetched : "+idImageFrontIdObj);
			try {
				
				//idDetail.setIdImageBackData(FileFolderUtils.encodeFileToBase64Binary(ifrontback));
				
				
				idDetail.setIdImageFrontData(FileFolderUtils.encodeFileToBase64Binary(idImageFrontIdObj));
				idDetail.setIdImageFront(map.get(TEMPLATE_ID_IMAGE_FRONT)+"_front");
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file.");
			}

			
		}
		
		
		String idImageBack=map.get(TEMPLATE_ID_IMAGE_BACK);
		if(idImageBack!=null && idImageBack.length()>0)
		{
			
			
			String idImageBackId = outputUrl+TEMPLATE_ID_IMAGE_BACK+File.separator+idImageBack+".jpg";
			//String iback = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+idNumber+"_front.jpg";
			
			
			File idImageBackIdObj = new File(idImageBackId);
			//File ifrontback = new File(iback);
			logger.debug(methodName+": idImageFrontIdObj fetched : "+idImageBackIdObj);
			try {
				
				//idDetail.setIdImageBackData(FileFolderUtils.encodeFileToBase64Binary(ifrontback));
				
				
				idDetail.setIdImageBackData(FileFolderUtils.encodeFileToBase64Binary(idImageBackIdObj));
				idDetail.setIdImageBack(map.get(TEMPLATE_ID_IMAGE_BACK)+"_front");
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file.");
			}

			
		}
		
		
		String physicalForm=map.get(TEMPLATE_PHYSICAL_FORM);
		if(physicalForm!=null && physicalForm.length()>0)
		{
			
			String physicalFormId = outputUrl+TEMPLATE_PHYSICAL_FORM+File.separator+physicalForm+".jpg";
			//String iback = outputUrl+TEMPLATE_IDENTIFICATION_NUMBER+File.separator+idNumber+"_front.jpg";
			
			
			File physicalFormIdObj = new File(physicalFormId);
			//File ifrontback = new File(iback);
			logger.debug(methodName+": Physical Form fetched : "+physicalFormIdObj);
			try {
				
				//idDetail.setIdImageBackData(FileFolderUtils.encodeFileToBase64Binary(ifrontback));
				
				
				idDetail.setPhysicalFormId(FileFolderUtils.encodeFileToBase64Binary(physicalFormIdObj));
				
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug(methodName+": unable to get binary from file.");
			}

			
			
			
			//subscriberIdDetails.add(idDetail);
		}
		subscriberIdDetails.add(idDetail);
		
		District dist=districtDao.findByDistrictName(map.get(DISTRICT));
		
		Country country=countryDao.getCountryByCountryName(map.get(COUNTRY));
		
		// Subscriber address set
		AddressDto addressDto = new AddressDto();
		addressDto.setPermanentAddress(map.get(TEMPLATE_PERMANENT_ADDRESS));
		addressDto.setHouseNo(map.get(TEMPLATE_RESIDENTIAL_HOUSE_NO));
		addressDto.setWard(map.get(TEMPLATE_RESIDENTIAL_WARD));
		addressDto.setVillageId(map.get(TEMPLATE_RESIDENTIAL_VILLAGE));
		addressDto.setPostalAddress(map.get(TEMPLATE_RESIDENTIAL_POSTAL_ADDRESS));
		addressDto.setPostalCode(map.get(TEMPLATE_RESIDENTIAL_POSTAL_CODE));
		addressDto.setAddress(map.get(TEMPLATE_RESIDENTIAL_ADDRESS));
		if(dist==null){
			addressDto.setDistrictId(null);	
		}
		else{
			addressDto.setDistrictId(dist.getDistrictId());	
		}
		if(country==null){
			addressDto.setCountryId(null);	
		}		
		else{
			addressDto.setCountryId(country.getCountryId());	
		}
		
		
		
		detailsDto.setSubscriberIdDetails(subscriberIdDetails);
		detailsDto.setAddress(addressDto);
		//detailsDto.setSubscriberIdDetails(subscriberIdDetails);
		detailsDto.setSubscriberDocuments(documentsDtos);
		detailsDto.setIsOldUserDetails(KycConstants.FALSE);
		
		detailsDtos.add(detailsDto);
		dto.setSubscriberDetails(detailsDtos);
		//dto.setIsOldSubscriber(KycConstants.FALSE);
		//dto.setBulkSubscriberFlag(KycConstants.TRUE);
		dto.setRegistrationFrom("web");
		dto.setRegistrationMode("Online");
		dto.setRegistrationProcess("new_registration");
		dto.setRegistrationType("normal");
		
		return dto;
	}
	
	
	public BaseResponse<?> registerBulkSubscriber(SubscriberUserDto subscriberUserDto, boolean isReRegistation)
			throws BusinessException, SystemException, UserException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		SubscriberDto subscriberDto=subscriberUserDto.getSubscriberData();
		//String ipAddress=subscriberUserDto.getSubscriberData().getIpAddress();
		UsersDto usersDto=subscriberUserDto.getUsersDto();
		
		String idPath=null;
		String idImage=null;
		Set<SubscriberIdDetailsDto> subscriberIdDetails = subscriberDto.getSubscriberDetails().get(0).getSubscriberIdDetails();
		for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberIdDetails) {
			idPath=subscriberIdDetailsDto.getIdPath();	
			idImage=subscriberIdDetailsDto.getIdImageName();
			
		}
		
		/*
		 * check if subscriber is already in system with the same msisdn & sim
		 * serial
		 */
		Subscriber susbscriber=subscriberDao.getSubscriberObj(subscriberDto.getMsisdn());
		if (!isReRegistation && susbscriber!=null) {
			responseDto = KycResponseUtils.getKycResponse(
					ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseCode(),
					ResponseCodes.FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST.getResponseDescription()
							+ subscriberDto.getMsisdn());
			subscriberRespons.setResponseDto(responseDto);
			return subscriberRespons;
		}
		
		
		
		
		try {
			
			if(isReRegistation) {
				SubscriberDto oldDto = new SubscriberDto();
				//update Subscriber
				oldDto.setSubscriberId(subscriberDto.getSubscriberId());
				//oldDto.setIsOldSubscriber(KycConstants.TRUE);				
				this.subscriberManagementService.updateSubscriber(oldDto,null,null,null,null);
				
				//add user
				subscriberDto.setParentSubscriberId(subscriberDto.getSubscriberId().intValue());
				subscriberDto.setSubscriberId(null);
				//subscriberDto.setIsOldSubscriber(KycConstants.FALSE);
				subscriberDto = (SubscriberDto) subscriberManagementService.addBulkSubscriber(subscriberDto,usersDto,subscriberUserDto);
			} else {
				// Add subscriber
				//subscriberDto.setIsOldSubscriber(KycConstants.FALSE);
				subscriberDto = (SubscriberDto) subscriberManagementService.addBulkSubscriber(subscriberDto,usersDto,subscriberUserDto);
			}
			
			
			if (subscriberDto == null) {
				responseDto = KycResponseUtils.getKycResponse(
						ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Unable to create subscriber");
				subscriberRespons.setResponseDto(responseDto);
				return subscriberRespons;
			}
			
			
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data save sucessfully");
		} catch (BusinessException cause) {
			responseDto = KycResponseUtils.getKycResponse(cause.getExceptionCode(), cause.getMessage());
		}

		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}

	@Override
	public BaseResponse<SubscriberResponse> activateAm(SubscriberDto subscriberDto) throws BusinessException, SystemException {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			SubscriberUserDto subscriberUserDto=new SubscriberUserDto();
			subscriberUserDto.setSubscriberData(subscriberDto);
			//this.subscriberManagementService.updateSubscriber(subscriberUserDto.getSubscriberData(),"YES");
			subscriberManagementService.activateAm(subscriberDto);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Airtel Money Will be be activated shortly");
			subscriberResponse.setSubscriberSearchResponseDto(null);
		} catch (Exception cause) {
			// TODO Auto-generated catch block
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseResponse<SubscriberResponse> getApprovedSubscriber(String finalTime) {
		SubscriberResponse subscriberResponse = new SubscriberResponse();
		ResponseDto responseDto = null;
		String fileName = null;
		try {
			List<SubscriberDto> dataList=subscriberDao.getApprovedSubscriber();
			if(dataList.size()>0){
				//Delimiter used in CSV file
				final String COMMA_DELIMITER = "|";
				final String NEW_LINE_SEPARATOR = "\n";
				
				
				//CSV file header
				final String FILE_HEADER = "msisdn|first_name|last_name|date_of_birth|district_name|country_name|gsm_reg_status|am_reg_status|territory|region|site_id|id_type|id_number|gender|physical_address|is_minor|latitude|longitude|channel_partner_cell_id|agent_auuid|agent_msisdn|agent_imei|agent_fname|agent_lname|cp_type|timestamp";
				FileWriter fileWriter = null;
				
				try {
					
					 String filepath = env.getProperty(BI_FILE_PATH);
					 //String filepath ="D:/";
					 fileName = filepath+"KYC_AGILE_ZAMBIA_"+finalTime+".csv";
					 logger.debug("BIDUMP fileName"+fileName);
					 logger.info("BIDUMP fileName"+fileName);
					 
					 logger.debug("BIDUMP filepath"+filepath);
					 logger.info("BIDUMP filepath"+filepath);
					 
					fileWriter = new FileWriter(fileName);

					//Write the CSV file header
					fileWriter.append(FILE_HEADER.toString());
					
					//Add a new line separator after the header
					fileWriter.append(NEW_LINE_SEPARATOR);
					
					//Write a new student object list to the CSV file
					for (SubscriberDto objects : dataList) {
						fileWriter.append(objects.getMsisdn());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getFirst_name());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getLast_name());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getDate_of_birth());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getDistrict_name());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getCountry_name());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getGsm_reg_status());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getAm_reg_status());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getTerritory());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getRegion());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getSite_id());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getId_type());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getId_number());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getGender());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getPhysical_address());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getIs_minor());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getLatitude());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getLongitude());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getChannel_partner_cell_id());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getAgent_auuid());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getAgent_msisdn());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getAgent_imei());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getAgent_fname());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getAgent_lname());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getCp_type());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(objects.getTimestamp());
						fileWriter.append(NEW_LINE_SEPARATOR);	                
					}

					
					
					System.out.println("CSV file was created successfully !!!");
					logger.debug("BIDUMP CSV file was created successfully");
					logger.info("BIDUMP CSV file was created successfully");
					 logger.debug("BIDUMP CSV file was created successfully");
					 
					
				} catch (Exception e) {
					System.out.println("Error in CsvFileWriter !!!");
					logger.debug("BIDUMP error");
					logger.info("BIDUMP error");
					 logger.debug("BIDUMP error CSV file was created successfully");
					e.printStackTrace();
				} finally {
					
					try {
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e) {
						System.out.println("Error while flushing/closing fileWriter !!!");
						logger.debug("BIDUMP IOException error");
						logger.info("BIDUMP IOException error");
						 logger.debug("BIDUMP error CSV file was created successfully");
		                e.printStackTrace();
		                
					}
					
				}
			
			}
			JSch jsch = new JSch();
			Session session = null;
			//session = jsch.getSession("AGILE_KYC","172.27.129.121",922);
			session = jsch.getSession(appConstants.biDumpDestUser,appConstants.biDumpDestIp,appConstants.biDumpDestport);
			//session.setPassword("UvXYq3jk");
			session.setPassword(appConstants.biDumpDestPassword);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			ChannelSftp channel = null;
			channel = (ChannelSftp)session.openChannel("sftp");
			channel.connect();
			File localFile = new File(fileName);
			//If you want you can change the directory using the following line.
			//channel.cd("/ds_datafs/BI_LZ1/AirtelZM/LandDir/AGILE_KYC");
			channel.cd(appConstants.biDumpDestFileLocation);
			channel.put(new FileInputStream(localFile),localFile.getName());
			channel.disconnect();
			session.disconnect();
			/*if(localFile.exists()){
				localFile.delete();
			}*/
			
		} catch (Exception cause) {		
			logger.debug("BIDUMP Exception error");
			logger.info("BIDUMP Exception error");
			 logger.debug("BIDUMP error CSV file was created successfully");
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),
					cause.getMessage());
		}
		subscriberResponse.setResponseDto(responseDto);
		return subscriberResponse;
	}

	@Override
	public BaseResponse<SubscriberResponse> getSearchSubscriberList(SearchSubscriberDto searchSubscriberDto)
			throws BusinessException {
		SubscriberResponse subscriberRespons = new SubscriberResponse();
		ResponseDto responseDto = null;
		try {
			SubscriberSearchResponseDto searchResponseDto = subscriberManagementService.getSearchSubscriber(searchSubscriberDto,KycConstants.FALSE);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Subscriber data fetched sucessfully");
			subscriberRespons.setSubscriberSearchResponseDto(searchResponseDto);
		} catch (Exception cause) {
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Subscriber Does Not Exist");
		}
		subscriberRespons.setResponseDto(responseDto);
		return subscriberRespons;
	}
}