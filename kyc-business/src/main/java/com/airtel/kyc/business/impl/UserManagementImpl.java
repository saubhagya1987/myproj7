package com.airtel.kyc.business.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

//import com.airtel.kyc.business.SubscriberManagementService;
import com.airtel.kyc.business.UserManagement;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.business.service.HelperDataManagementService;
import com.airtel.kyc.business.service.UserManagementService;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ExceptionCodes;
import com.airtel.kyc.enumData.ResponseCodes;
import com.airtel.kyc.enumData.UserExceptionCodes;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.CommissionRuleDto;
import com.airtel.kyc.helpers.dto.HelpAndSupportDto;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.helpers.dto.SubscriberIdDetailsDto;
import com.airtel.kyc.helpers.dto.TokenResponse;
import com.airtel.kyc.helpers.dto.UserSearchResponseDto;
import com.airtel.kyc.helpers.response.UserResponse;
import com.airtel.kyc.repository.dao.AppVersionDao;
import com.airtel.kyc.repository.dao.CMDao;
import com.airtel.kyc.repository.dao.ClientOauthDetailsDao;
import com.airtel.kyc.repository.dao.DeviceIdDao;
import com.airtel.kyc.repository.dao.DistrictDao;
import com.airtel.kyc.repository.dao.ProvinceDao;
import com.airtel.kyc.repository.dao.VillageDao;
import com.airtel.kyc.repository.entity.AppVersionMaster;
import com.airtel.kyc.repository.entity.Assignment;
import com.airtel.kyc.repository.entity.AssignmentTracker;
import com.airtel.kyc.repository.entity.DeviceId;
import com.airtel.kyc.repository.entity.District;
import com.airtel.kyc.repository.entity.Province;
import com.airtel.kyc.repository.entity.Village;
import com.airtel.kyc.utils.FileFolderUtils;
import com.airtel.kyc.utils.KycResponseUtils;
import com.airtel.kyc.utils.Utility;
import com.airtel.user.helper.constant.ConfigConstants;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.LocationDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UserIdDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.config.dao.UserRepositry;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.entities.UserIdDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class UserManagementImpl implements UserManagement, KycConstants {
	private static final String SECURITY_CODE_INVALID = "security.code.invalid";
	private static final String LDAP_PASSWORD = "ldap.password";
	private static final String BLOCKED_TIME_PERIOD = "5";
	private static final String PREV_BLOCKED_TIME_PERIOD = "prev.blocked.time.period";
	@Autowired
	private UserManagementService userService;

	//@Autowired
	//private SubscriberManagementService subscriberManagementService;
	
	@Autowired	
	private HelperDataManagementService helperDataManagementService;
	
	

	@Autowired
	private Environment env;
	
	@Autowired
	private ObjectWriter objectWriter;	
	
	@Autowired
	private ClientOauthDetailsDao clientOauthDetailsDao;
	
	@Autowired
	private DeviceIdDao deviceIdDao;
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private VillageDao villageDao;
	
	@Autowired
	private DistrictDao districtDao;
	
	@Autowired
	private AppVersionDao appVersionDao;
		
	@Autowired
	CMDao cmDao;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    private TokenStore tokenStore;

	
	@Qualifier("userRepositry")
	@Autowired
	private UserRepositry userRepositry;

	@Override
	public BaseResponse<?> searchUser(SearchParamDto bean, int slot, int startIndex)
			throws BusinessException, SystemException {
		long l =System.currentTimeMillis();
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		try {
			UserSearchResponseDto searchResponseDto = this.userService.searchUser(bean, slot, startIndex);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			
			userResponse.setSearchResponseDto(searchResponseDto);
		} catch (Exception cause) {
			cause.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"User Does Not Exist");
		}
		userResponse.setResponseDto(responseDto);
		System.out.println(" Time taken : "+(System.currentTimeMillis() - l)/1000+" ms");
		return userResponse;
	}
	
	
	@Override
	public BaseResponse<?> provisionUser(UsersDto bean) throws BusinessException, SystemException, IOException {

		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.provisionUser(bean);
			try {
				boolean apiAcess = this.userService.provisionAPIAcsess(userDto);
				if(userDto.getAuuId()!=null && userDto.getAuuId().length()>0){
					boolean ldpAcess = this.userService.provisionLDAPAcsess(userDto);	
				}
				if(userDto.getSubRole()!=null){
				for(RoleDto roleDto : userDto.getSubRole()){
					if(roleDto.getRoleId().intValue() == 29 || roleDto.getRoleId().intValue() ==30 || roleDto.getRoleId().intValue() ==31){
						Assignment topSeqAssign = cmDao.getAssignment("TOP");
						AssignmentTracker assignmentTracker = cmDao.getAssignmentTracker();
						Assignment bottomSeqAssign = cmDao.getAssignment("BOTTOM");
						if(assignmentTracker==null){
							topSeqAssign = new Assignment();
							topSeqAssign.setCount(0);
							topSeqAssign.setSequence(1);
							topSeqAssign.setNextAssignSequence(1);
							topSeqAssign.setNextAssignUserId(userDto.getUserId());
							topSeqAssign.setUserId(userDto.getUserId());
							cmDao.saveObject(topSeqAssign);
							
							assignmentTracker = new AssignmentTracker();
							assignmentTracker.setAssignmenTrackerId(100);
							assignmentTracker.setCurrentAssign(userDto.getUserId());
							assignmentTracker.setMaxSequence(1);
							assignmentTracker.setNextAssign(userDto.getUserId());
							cmDao.saveObject(assignmentTracker);
						}
						else if(topSeqAssign.getSequence().intValue() == bottomSeqAssign.getSequence().intValue()) {
							Integer maxSeq = assignmentTracker.getMaxSequence();
							maxSeq = maxSeq +1;
							assignmentTracker.setMaxSequence(maxSeq);
							assignmentTracker.setAssignmenTrackerId(100);
							assignmentTracker.setNextAssign(userDto.getUserId());
							cmDao.saveObject(assignmentTracker);
							
							topSeqAssign.setNextAssignSequence(2);
							topSeqAssign.setNextAssignUserId(userDto.getUserId());
							topSeqAssign.setSequence(1);
							topSeqAssign.setUserId(topSeqAssign.getUserId());
							cmDao.saveObject(topSeqAssign);
							
							Assignment assignmentNext = new Assignment();
							
							assignmentNext.setCount(0);
							assignmentNext.setNextAssignSequence(1);
							assignmentNext.setNextAssignUserId(topSeqAssign.getUserId());
							assignmentNext.setSequence(2);
							assignmentNext.setUserId(userDto.getUserId());
							cmDao.saveObject(assignmentNext);
							
						}
						else{
							Integer maxSeq = assignmentTracker.getMaxSequence();
							maxSeq = maxSeq +1;
							assignmentTracker.setAssignmenTrackerId(100);
							assignmentTracker.setMaxSequence(maxSeq);
							cmDao.saveObject(assignmentTracker);
							
							topSeqAssign.setNextAssignSequence(maxSeq);
							topSeqAssign.setNextAssignUserId(userDto.getUserId());
							topSeqAssign.setSequence(topSeqAssign.getSequence());
							topSeqAssign.setUserId(topSeqAssign.getUserId());
							cmDao.saveObject(topSeqAssign);
							
							Assignment assignmentNext = new Assignment();
							
							assignmentNext.setCount(0);
							assignmentNext.setNextAssignSequence(1);
							assignmentNext.setNextAssignUserId(bottomSeqAssign.getUserId());
							assignmentNext.setSequence(maxSeq);
							assignmentNext.setUserId(userDto.getUserId());
							cmDao.saveObject(assignmentNext);
						}
					}
				}
			  }
				
			} catch (Exception e) {
				e.printStackTrace();;
			}
			userDto.setPassword(null);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDto(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.DUPLICATE_USER_NAME.getExceptionCode(),
					UserExceptionCodes.DUPLICATE_USER_NAME.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<?> isMSISDNAvailable(String msisdn) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		/*@SuppressWarnings("rawtypes")
		BaseResponse baseResponse = new BaseResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		Map<String , Boolean> map = new HashMap<>();
		boolean b = false;
		responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
		try {
			userDto = this.userService.getUserByName(msisdn);
			if(userDto != null) {
				b = false ;
				responseDto.setResponseDescription("User already exsist in the system with same MSISDN.");
			}
		} catch (UserException e) {
			b = true;
			responseDto.setResponseDescription("MSISDN Available");
		}
		
		baseResponse.setResponseDto(responseDto);
		map.put("isMSISDNAvailable", b);
		baseResponse.setBody(map);
		return baseResponse;*/
		
		@SuppressWarnings("rawtypes")
		BaseResponse baseResponse = new BaseResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		Map<String , Object> map = new HashMap<>();
		boolean b = false;
		responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
		try {
			userDto = this.userService.getUserByName(msisdn);
			if(userDto != null && userDto.getUserFlag()==0) {
				b = false ;
				responseDto.setResponseDescription("MSISDN Available");
			}
			else{
				b = true ;
				responseDto.setResponseDescription("User already exsist in the system with same MSISDN.");
			}
		} catch (UserException e) {
			b = true;
			responseDto.setResponseDescription("MSISDN Available");
		}
		
		baseResponse.setResponseDto(responseDto);
		map.put("isMSISDNAvailable", b);
		if(!b && userDto.getIdCardNo()!=null){
			map.put("idCardNo", userDto.getIdCardNo());
		}
		baseResponse.setBody(map);
		return baseResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<?> isMsisdnAvailable(String msisdn) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
				@SuppressWarnings("rawtypes")
				BaseResponse baseResponse = new BaseResponse();
				ResponseDto responseDto = null;
				UsersDto userDto = null;
				Map<String , Boolean> map = new HashMap<>();
				boolean b = false;
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
				try {
					userDto = this.userService.isMsisdnAvailable(msisdn);
					if(userDto != null) {
						b = false ;
						responseDto.setResponseDescription("User already exsist in the system with same MSISDN.");
					}					
				} catch (UserException e) {
					b = true;
					responseDto.setResponseDescription("MSISDN Available");
				}
				
				baseResponse.setResponseDto(responseDto);
				map.put("isMSISDNAvailable", b);				
				baseResponse.setBody(map);
				return baseResponse;
	}


	@Override
	public BaseResponse<?> deleteUser(UsersDto userData) throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		List<UsersDto> userDto = null;
		try {
			userDto = this.userService.deleteUser(userData);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDtoList(userDto);
		} catch (UserException e) {
			
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@Override
	public BaseResponse<?> blockUser(UsersDto userData) throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.blockUser(userData);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDto(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}
	
	@Override
	public ResponseEntity<byte[]> registerBlukUser(MultipartFile file)
			throws BusinessException, SystemException {
    try {
    	
        //-------------------------------------      	
    	//-----------------------------------------
    	
			
			InputStream inputStream = file.getInputStream();
			String outputUrl = FileFolderUtils.extractAll(inputStream, this.env.getProperty("com.airtel.user.location.dir"));
			outputUrl = outputUrl + "bulk_user";//+ File.separator ;
			//List<Map<String, Object>> list = Utility.getMapFromExcel(inputStream);
			
			//FileInputStream fileInputStream = new FileInputStream(outputUrl+"bulk_subscriber_template.csv");
			//List<Map<String, Object>> list = Utility.getMapFromExcel(fileInputStream);
			
			System.out.println(" Output folder : "+outputUrl);
			String url=outputUrl+"/";
			FileInputStream fileInputStream = new FileInputStream(url+"bulk_user_template.csv");
			//List<Map<String, Object>> list = Utility.getMapFromExcel(fileInputStream);
			List<Map<String, String>> list =  Utility.getMapFromCSV(fileInputStream);
			
			//List<Map<String, String>> list =  Utility.getMapFromCSV(inputStream);
			
			if (list == null || list.isEmpty()) {
				throw new BusinessException(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION);
			}
			List<Map<String, Object>> responseList = new ArrayList<>();
			for (Map<String, String> map : list) {
				/*if(map.get(TEMPLATE_MSISDN) == null) {
					throw new BusinessException(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION);
				}*/
				//NumberFormat formatter = new DecimalFormat("#0");     
				String msisdn = map.get(TEMPLATE_MSISDN);
				Map<String, Object> temp = new LinkedHashMap<>();
				UsersDto dto = null;
				try{
					dto = getUserDtoFromMap(map,url);
					System.out.println(" dto : "+dto);
					if(dto != null) {
						BaseResponse baseResponse2 = provisionUser(dto);
						temp.put(TEMPLATE_MSISDN, msisdn+"");
						temp.put(RESPONSE_CODE, baseResponse2.getResponseDto().getResponseCode()+"");
						temp.put(RESPONSE_DESCRIPTION, baseResponse2.getResponseDto().getResponseDescription());
						temp.put("time", new Timestamp(System.currentTimeMillis())+"");
						responseList.add(temp);	
					}
				} catch (UserException e) {
					temp.put(TEMPLATE_MSISDN, msisdn+"");
					temp.put(RESPONSE_CODE, e.getExceptionCode()+"");
					temp.put(RESPONSE_DESCRIPTION, e.getExceptionMessage());
					temp.put("time", new Timestamp(System.currentTimeMillis())+"");
					responseList.add(temp);	
				}
			}
			String displayName = TEMPLATE_BULK_USER_RESPONSE + System.currentTimeMillis()+".xlsx";
			FileOutputStream outputStream = new FileOutputStream(new File(this.env.getProperty(TEMP_FILE_LOCATION_USER) + displayName));
			// create report
			Utility.generateExcel(responseList, displayName, outputStream);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Disposition", "attachment; filename=\"" + displayName);
			headers.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
			byte[] media = IOUtils.toByteArray(new FileInputStream(new File(this.env.getProperty(TEMP_FILE_LOCATION_USER) + displayName)));
			return new ResponseEntity<byte[]>(media, headers, HttpStatus.CREATED);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	private UsersDto getUserDtoFromMap(Map<String, String> map,String outputUrl) throws UserException {
		if(map == null || map.isEmpty()) {
			return null;
		}
		UsersDto dto = new UsersDto();
		if(map.get(TEMPLATE_MSISDN) == null ) {
			throw new UserException(UserExceptionCodes.INVALID_MISISDN);
		}
		if(map.get(TEMPLATE_ROLES) == null) {
			throw new UserException(UserExceptionCodes.INVALID_ROLES);
		}
		/*if(map.get(TEMPLATE_DEPARTMENTS) == null) {
			throw new UserException(UserExceptionCodes.INVALID_DEPARTMENTS);
		}*/
		
		String departments = map.get(TEMPLATE_DEPARTMENTS)+"";
		String role = map.get(TEMPLATE_ROLES)+"";
		
		
		if(role != null) {
			role = role.trim();
		}
		RoleDto roleDto = this.helperDataManagementService.getRoleBySubRoleName(role);
		if(roleDto == null) {
			throw new UserException(UserExceptionCodes.ROLE_NOT_VALID);
		}
		DepartmentDto departmentDto = this.helperDataManagementService.getDepartmentByName(departments);
		if(departmentDto == null) {
			throw new UserException(UserExceptionCodes.DEPARTMENT_NOT_VALID);
		}		
		
		Set<UserIdDetailsDto> userIdDetails = new HashSet<>();
		UserIdDetailsDto idDetail = new UserIdDetailsDto();
		
		String cpImage=map.get(TEMPLATE_CP_IMAGE);
		if(cpImage!=null && cpImage.length()>0)
		{			
			String cpImageId = outputUrl+TEMPLATE_CP_IMAGE+File.separator+cpImage+".jpg";						
			File cpImageIdObj = new File(cpImageId);			
			System.out.println(": idImageFrontIdObj fetched : "+cpImageIdObj);
			try {				
				idDetail.setIdImage(FileFolderUtils.encodeFileToBase64Binary(cpImageIdObj));
				idDetail.setIdImageName(cpImage);
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}			
		}
		
		userIdDetails.add(idDetail);
		
		
		String mobileNumber = map.get(TEMPLATE_MSISDN)+"";
		
		List<UserDetailsDto> detailsList = new ArrayList<>();
		UserDetailsDto details = new UserDetailsDto();
		details.setMsisdn(mobileNumber);
		details.setFirstName(map.get(TEMPLATE_FIRSTNAME)+"");
		details.setLastName(map.get(TEMPLATE_LASTNAME)+"");
		details.setEmail(map.get(TEMPLATE_EMAIL)+"");
		details.setAuuid(map.get(TEMPLATE_AUUID)+"");
		details.setDob(map.get(TEMPLATE_dOB)+"");
		details.setCommissionMobileNo(map.get(TEMPLATE_COMMISSION_PAYMENT_MOBILE_NO)+"");
		details.setIdCardNo(map.get(TEMPLATE_ID_CARD_NUMBER)+"");
		details.setDeviceName(map.get(TEMPLATE_DEVICE_NAME)+"");
		details.setEditInfoAccess(map.get(TEMPLATE_EDIT_INFO_ACCESS)+"");
		details.setSimSwapAccess(map.get(TEMPLATE_SIM_SWAP_ACCESS)+"");
		details.setDistributorName(map.get(TEMPLATE_DISTRIBUTOR_NAME)+"");
		details.setChannelPartnerCellId(map.get(TEMPLATE_CHANNEL_PARTNER_CELL_ID)+"");
		details.setPermissableRadius(map.get(TEMPLATE_PERMISSABLE_RADIUS)+"");
		details.setTsmAuuid(map.get(TEMPLATE_DM_AUUID)+"");
		details.setCpContract(map.get(TEMPLATE_CP)+"");
		details.setAirtelHandsetImei(map.get(TEMPLATE_AIRTEL_HANDSET_IMEI)+"");
		details.setAirtelHandsetName(map.get(TEMPLATE_AIRTEL_HANDSET_PROVIDED)+"");
		
		details.setUserIdDetail(userIdDetails);
		
		
		
		
		
		List<DepartmentDto> userDepartment = new ArrayList<>();
		userDepartment.add(departmentDto);
		details.setUserDepartment(userDepartment);
		
		detailsList.add(details);
		
		
		
		
		LocationDto locationDto = new LocationDto();
		String provinceName=map.get(PROVINCE);	
		if(!provinceName.equals("") || provinceName!=null){
			Province province=provinceDao.findByProvince(provinceName);
			if(province!=null){			
				locationDto.getProvinceIds().get(0).setProvinceId(province.getProvinceId());			
			}
		}
		String villageName=map.get(VILLAGE);
		if(!villageName.equals("") || villageName!=null){
			Village village=villageDao.findByVillageName(villageName);
			if(village!=null){
				locationDto.getVillageIds().get(0).setVillageId(village.getVillageId());
			}
		}
		
		
		String districtName=map.get(DISTRICT);
		if(!districtName.equals("") || districtName!=null)
		{
			District district=districtDao.findByDistrictName(districtName);
			if(district!=null){
				locationDto.getDistrictIds().get(0).setDistrictId(district.getDistrictId());
			}
		}
		
		
		
		
		List<RoleDto> roles = new ArrayList<>();
		roles.add(roleDto);
		
		dto.setUserName(mobileNumber);
		dto.setBulkEnabled(TRUE+"");
		dto.setUserDetail(details);
		dto.setUserDetails(detailsList);
		dto.setLocation(locationDto);
		dto.setRoles(roles);
		dto.setSubRole(roles);
		dto.setUserTransactionId(String.valueOf(Math.random()));
		return dto;
	}
	
	@Override
	public BaseResponse<?> updateUserPassword(int uid, String password, String newPassword, String confirmPassword)
			throws BusinessException, SystemException, UserException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = this.userService.getUserById(uid);
		if (userDto == null) {
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(KycConstants.PASSWORD_LENGTH);
		// System.out.println(cryptPasswordEncoder.matches("Airtel@123",
		// "$2a$11$MgWCIu2vepR1e7C02DoH5OasUEhBoZYAohWw9gaSVMJyMg9Z7G0nW"));
	     if(password!=null && password.length()!=0){
			if (!cryptPasswordEncoder.matches(password, userDto.getPassword())) {
				responseDto = KycResponseUtils.getKycResponse(
						UserExceptionCodes.CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH.getExceptionCode(),
						UserExceptionCodes.CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH.getExceptionDescription());
				userResponse.setResponseDto(responseDto);
				return userResponse;
			}
	     }
		if (cryptPasswordEncoder.matches(newPassword, userDto.getPassword())) {
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.OLD_AND_NEW_PASSWORD_CAN_NOT_SAME.getExceptionCode(),
					UserExceptionCodes.OLD_AND_NEW_PASSWORD_CAN_NOT_SAME.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		if (!newPassword.equalsIgnoreCase(confirmPassword)) {
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.NEW_AND_CONFIRM_PASSWORD_NOT_SAME.getExceptionCode(),
					UserExceptionCodes.NEW_AND_CONFIRM_PASSWORD_NOT_SAME.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		try {
			userDto.setPassword(cryptPasswordEncoder.encode(newPassword));
			userDto.setIsPasswordModified(KycConstants.TRUE);
			this.userService.updatePassword(uid, userDto);
			
			String client_id=String.valueOf(uid);
			
			ClientOauthDetails clientOauthDetails=clientOauthDetailsDao.getClientDetails(client_id);
			if(clientOauthDetails!=null){
				clientOauthDetails.setClient_secret(cryptPasswordEncoder.encode(newPassword));	
				
				clientOauthDetails.setAccess_token_validity(clientOauthDetails.getAccess_token_validity());
				clientOauthDetails.setAuthorized_grant_types(clientOauthDetails.getAuthorized_grant_types());
				clientOauthDetails.setAutoapprove(clientOauthDetails.getAutoapprove());
				clientOauthDetails.setRefresh_token_validity(clientOauthDetails.getRefresh_token_validity());
				clientOauthDetails.setResource_ids(clientOauthDetails.getResource_ids());
				clientOauthDetails.setScope(clientOauthDetails.getScope());
				clientOauthDetails.setAuthorities(clientOauthDetails.getAuthorities());
				
				clientOauthDetailsDao.saveOrUpdate(clientOauthDetails);
			}
			try {
				boolean apiAcess = this.userService.provisionAPIAcsess(userDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			// userResponse.setUserDto(userDto);
		} catch (UserException e) {
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<?> isUserAvailbleByAuuId(String auuid) throws BusinessException, SystemException {
		@SuppressWarnings("rawtypes")
		BaseResponse baseResponse = new BaseResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		Map<String , Boolean> map = new HashMap<>();
		boolean b = false;	
		String ldapPwd=null;
		responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
		try {
			userDto = this.userService.authenticateUserByAuuId(auuid, ldapPwd);
			//userDto = new UsersDto();
			if(userDto.getAuuIdFlag()) {
				b = true ;
				responseDto.setResponseDescription("Valid AuuId");
			}
			else{
				b = false ;
				responseDto.setResponseDescription("Invalid AuuId");
			}
		} catch (UserException e) {
			b = false;
			responseDto.setResponseDescription("Invalid AuuId");
		}
		
		baseResponse.setResponseDto(responseDto);
		map.put("isAuuIdAvailable", b);
		baseResponse.setBody(map);
		return baseResponse;
	}


	@Override
	public BaseResponse<?> generatePassword(UsersDto userData) throws BusinessException, SystemException, IOException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.generatePassword(userData);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDto(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.INVALID_MISISDN.getExceptionCode(),
					UserExceptionCodes.INVALID_MISISDN.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<?> validateOtp(UsersDto userData) throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.validateOtp(userData);
			//if(!userDto.getMessage().equals(messageSource.getMessage(SECURITY_CODE_INVALID, null, Locale.getDefault())))
			if(!userDto.getMessage().equals(env.getProperty(SECURITY_CODE_INVALID)))
				{
				responseDto = KycResponseUtils.getKycResponse(
						HttpStatus.OK,
						userDto.getMessage());
				responseDto.setBody(userDto.getMsisdn());
				}
			else{
				responseDto = KycResponseUtils.getKycResponse(
						UserExceptionCodes.SECURITY_CODE_EXPIRED.getExceptionCode(),
						userDto.getMessage());				
			}
			
			//userResponse.setBody(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.SECURITY_CODE_EXPIRED.getExceptionCode(),
					UserExceptionCodes.SECURITY_CODE_EXPIRED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		userResponse.setTotalCountDto(null);
		return userResponse;
	}


	@Override
	public TokenResponse validateUser(UsersDto userData) throws BusinessException, SystemException, JsonProcessingException, UserDaoException {		
		

		
		String sqlQuery = "select CLIENT_ID,REFRESH_TOKEN from OAUTH_ACCESS_TOKEN where CLIENT_ID='"+userData.getAuuId()+"'";
		ResultSet rs = null;
		Connection conn=null;
		Statement st =null;
		Statement st1 = null;
		Statement st2 =null;
		try {
			conn= dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);

			if(rs!=null){
				while(rs.next()){
					String sqlQueryCID = "delete from OAUTH_ACCESS_TOKEN where CLIENT_ID='"+rs.getString(1)+"'";
					st1 = conn.createStatement();
					st1.execute(sqlQueryCID);
					String sqlQueryTID = "delete from OAUTH_REFRESH_TOKEN where TOKEN_ID='"+rs.getString(2)+"'";
					st2 = conn.createStatement();
					st2.execute(sqlQueryTID);
				}
			}
		} catch (SQLException e1) {
			System.out.println("validate User SQLException block token");			
			e1.printStackTrace();
		}
		finally{
			try {
				
				if(rs!=null)
					rs.close();
				if(st1!=null)
					st1.close();
				if(st2!=null)
					st2.close();				
				if(st!=null)
					st.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("validate User SQLException block connection");
				e.printStackTrace();
			}
		}
		AppVersionMaster appVersionMaster= null;
		UsersDto userDto = null;
		Users userDtoObj = null;
		DeviceId deviceId=null;
		boolean b = false;
		TokenResponse tokenResponse=null;		
		RestTemplate restTemplate = new RestTemplate();	
		//String url=messageSource.getMessage(ConfigConstants.OAUTH_TOKEN_URL, null, Locale.getDefault());
		String url=env.getProperty(ConfigConstants.OAUTH_TOKEN_URL);
		//String url="http://127.0.0.1:8081/kycug/oauth/token";					
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);		
		try {
			userDtoObj = this.userService.validateUser(userData);
			if(userDtoObj==null && userData.getModelName()!=null ){
				tokenResponse=new TokenResponse();
				tokenResponse.setMobileAccessFlag(0);
				tokenResponse.setUserExist(false);
				tokenResponse.setUnauthorized("false");
				tokenResponse.setAccess_token("404");
				tokenResponse.setStatus(UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionCode());
				return tokenResponse;
			}
			
			if(userDtoObj==null  ){
				tokenResponse=new TokenResponse();				
				tokenResponse.setMobileAccessFlag(0);
				tokenResponse.setUserExist(false);
				tokenResponse.setUnauthorized("false");
				tokenResponse.setAccess_token("404");
				tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
				return tokenResponse;
			}
			
			/*else if(userDtoObj!=null && userDtoObj.getRoles().get(0).getMobileAccessFlag()==0){
				tokenResponse=new TokenResponse();
				tokenResponse.setMobileAccessFlag(0);
				tokenResponse.setUserExist(false);
				tokenResponse.setUnauthorized("true");
				tokenResponse.setAccess_token("404");
			}*/
			
			if(userData.getModelName() != null){
				deviceId=deviceIdDao.getMacIdStatus(userData.getModelName());
				/*if(deviceId==null){
					tokenResponse=new TokenResponse();
					tokenResponse.setMobileAccessFlag(0);
					tokenResponse.setUserExist(false);
					tokenResponse.setUnauthorized("false");
					tokenResponse.setAccess_token("404");
				}*/
				userDto = this.userService.authenticateUserByAuuId(userData.getAuuId(),userData.getPassword());
				//userDto=new UsersDto();
				//userDto.setAuuIdFlag(false);
				if(deviceId!=null && userDtoObj.getRoles().get(0).getMobileAccessFlag()==1  && userDtoObj.getIsBlocked()==1 && userDtoObj.getIsDeleted()==0){//Appp
					appVersionMaster= appVersionDao.getAppVersion("android");
					if(appVersionMaster!=null && appVersionMaster.getAppVersion().equals(userData.getAppVersion())){
					//userDto=new UsersDto();
					//userDto.setAuuIdFlag(false);
					if(userDto != null)
					{
						//b = true ;
						//b = false;
						if(userDto.getAuuIdFlag())
						{							
							MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
							map.add( "grant_type", "password");
							map.add( "client_id",userData.getAuuId());
							map.add( "client_secret", userData.getAuuId());
							map.add( "username",userData.getAuuId());
							map.add( "password",userData.getAuuId());
							map.add( "ldapPassword",userData.getPassword());
							HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);							
						    try{
						    	 ResponseEntity<TokenResponse> response = restTemplate.postForEntity( url, request , TokenResponse.class );				    	 
						    	 tokenResponse=response.getBody();
						    	 tokenResponse.setSubRoleId(userDtoObj.getRoles().get(0).getRoleId());
						    	 tokenResponse.setRoleId(userDtoObj.getRoles().get(0).getParentRoleId());
						    	 tokenResponse.setMobileAccessFlag(1);
						    	 tokenResponse.setMacIdStatus(true);
						    	 tokenResponse.setUserExist(true);
						    	 tokenResponse.setIsPasswordModified(String.valueOf(userDtoObj.getIsPasswordModified()));
						    	 
						    	//added for app version related data for device end
						    	 tokenResponse.setAppUrl(appVersionMaster.getAppUrl());
						    	 tokenResponse.setAppVersion(appVersionMaster.getAppVersion());
						    	 tokenResponse.setLocationDataUrl(appVersionMaster.getLocationDataUrl());
						    	 tokenResponse.setLocationDataVersion(appVersionMaster.getLocationDataVersion());
						    	 
						    	 if(tokenResponse!=null){
						    		 Users users= userRepositry.getUserByAuuId(userDtoObj.getAuuid());
						    		 users.setLastLogin(new Timestamp(System.currentTimeMillis()));
						    		 users.setNotificationToken(userData.getNotificationToken());
						    		 users.setRetryAttempt(0);
						    		 users.setOtpCount(0);
						    		 users = this.userRepositry.saveOrUpdateUser(users);
						    	 }
						    }
						    catch(RestClientException e)
						    {
						    	tokenResponse=new TokenResponse();
						    	Users users= userRepositry.getUserByAuuId(userDtoObj.getAuuid());	
						    	
						    	if(users.getRetryAttempt()<4){
						    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
						    		users.setRetryAttempt(users.getRetryAttempt()+1);
						    		users = this.userRepositry.saveOrUpdateUser(users);
						    		
						    		tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
						    	}
						    	else{
						    		
						    		if(users.getRetryAttempt()>4){
						    			//for my internal purpose
						    			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
										Date current_timeStamp = new Date(timestamp.getTime());	
										Date last_login_timeStamp=null;
										if(users.getLastLogin()!=null){
											last_login_timeStamp= new Date(users.getLastLogin().getTime());
										}
										else{
											last_login_timeStamp= new Date(timestamp.getTime());
										}
										
										long timeDiff = current_timeStamp.getTime() - last_login_timeStamp.getTime();				
										long diffMinute = timeDiff / (60 * 1000) % 60;
										//int blockingPeriod = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
										int blockingPeriod = Integer.parseInt(BLOCKED_TIME_PERIOD);
										//for my internal purpose
										
										
										if (diffMinute > blockingPeriod) {
											users.setRetryAttempt(1);
											users.setLastLogin(null);
											users = this.userRepositry.saveOrUpdateUser(users);
											
											tokenResponse.setMobileAccessFlag(0);
											tokenResponse.setUserExist(false);
											tokenResponse.setUnauthorized("false");
											tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
											tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
											tokenResponse.setAccess_token("404");
										}
										else{
											users.setRetryAttempt(users.getRetryAttempt()+1);
											users.setLastLogin(new Timestamp(System.currentTimeMillis()));
											users = this.userRepositry.saveOrUpdateUser(users);
											
											tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");																    	
											tokenResponse.setMobileAccessFlag(0);
											tokenResponse.setUserExist(false);
											tokenResponse.setUnauthorized("false");
											tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
											tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
											tokenResponse.setAccess_token("404");
										}
											
										
						    		}
						    		else{
						    			users.setRetryAttempt(users.getRetryAttempt()+1);
						    			users.setLastLogin(new Timestamp(System.currentTimeMillis()));
						    			users = this.userRepositry.saveOrUpdateUser(users);
						    			
						    			
						    			tokenResponse.setFailedAttempt("You Have Entered Wrong Password for 5 times ");					    	
										tokenResponse.setMobileAccessFlag(0);
										tokenResponse.setUserExist(false);
										tokenResponse.setUnauthorized("false");
										tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
										tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
										tokenResponse.setAccess_token("404");
						    		}
						    	}
						    	
						    	}			   
					     }
						 else{
							//for my internal purpose
				    			/*Timestamp timestampObj = new Timestamp(System.currentTimeMillis());
								Date current_timeStampObj = new Date(timestampObj.getTime());	
								Date last_login_timeStampObj=null;
								if(userDtoObj.getLastLogin()!=null){
									last_login_timeStampObj= new Date(userDtoObj.getLastLogin().getTime());
								}
								else{
									last_login_timeStampObj= new Date(timestampObj.getTime());
								}
								
								long timeDiffObj = current_timeStampObj.getTime() - last_login_timeStampObj.getTime();				
								long diffMinuteObj = timeDiffObj / (60 * 1000) % 60;
								int blockingPeriodObj = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
								//for my internal purpose
							 
							 if(userDtoObj.getRetryAttempt()>0 && diffMinuteObj < blockingPeriodObj){
								    
								    tokenResponse=new TokenResponse();
								    tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");																    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								 
							 }
							 else{*/
								 MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
									map.add( "grant_type", "password");
									map.add( "client_id",userData.getAuuId());
									map.add( "client_secret", userData.getPassword());
									map.add( "username",userData.getAuuId());
									map.add( "password",userData.getPassword());	
									HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);							
								    try{
								    	 ResponseEntity<TokenResponse> response = restTemplate.postForEntity( url, request , TokenResponse.class );				    	 
								    	 tokenResponse=response.getBody();	
								    	 tokenResponse.setSubRoleId(userDtoObj.getRoles().get(0).getRoleId());
								    	 tokenResponse.setRoleId(userDtoObj.getRoles().get(0).getParentRoleId());
								    	 tokenResponse.setMobileAccessFlag(1);
								    	 tokenResponse.setMacIdStatus(true);
								    	 tokenResponse.setUserExist(true);
								    	 tokenResponse.setIsPasswordModified(String.valueOf(userDtoObj.getIsPasswordModified()));
								    	 
								    	//added for app version related data for device end
								    	 tokenResponse.setAppUrl(appVersionMaster.getAppUrl());
								    	 tokenResponse.setAppVersion(appVersionMaster.getAppVersion());
								    	 tokenResponse.setLocationDataUrl(appVersionMaster.getLocationDataUrl());
								    	 tokenResponse.setLocationDataVersion(appVersionMaster.getLocationDataVersion());
								    	 if(tokenResponse!=null){
								    		 Users users= userRepositry.getUserByName(userDtoObj.getUserName());
								    		 users.setNotificationToken(userData.getNotificationToken());
								    		 users.setRetryAttempt(0);
								    		 users.setLastLogin(new Timestamp(System.currentTimeMillis()));
								    		 users.setOtpCount(0);
								    		 users = this.userRepositry.saveOrUpdateUser(users);
								    	 }
								    }
								    catch(RestClientException e)
								    {
								     tokenResponse=new TokenResponse();
								     Users users= userRepositry.getUserByName(userDtoObj.getUserName());	
								    	
								    	if(users.getRetryAttempt()<4){
								    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
								    		users.setRetryAttempt(users.getRetryAttempt()+1);
								    		users = this.userRepositry.saveOrUpdateUser(users);
								    		
								    		tokenResponse.setMobileAccessFlag(0);
											tokenResponse.setUserExist(false);
											tokenResponse.setUnauthorized("false");
											tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
											tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
											tokenResponse.setAccess_token("404");
								    	}
								    	else{
								    		
								    		if(users.getRetryAttempt()>4){
								    			//for my internal purpose
								    			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
												Date current_timeStamp = new Date(timestamp.getTime());	
												Date last_login_timeStamp=null;
												if(users.getLastLogin()!=null){
													last_login_timeStamp= new Date(users.getLastLogin().getTime());
												}
												else{
													last_login_timeStamp= new Date(timestamp.getTime());
												}
												
												long timeDiff = current_timeStamp.getTime() - last_login_timeStamp.getTime();				
												long diffMinute = timeDiff / (60 * 1000) % 60;
												//int blockingPeriod = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
												int blockingPeriod = Integer.parseInt(BLOCKED_TIME_PERIOD);
												//for my internal purpose
												
												
												if (diffMinute > blockingPeriod) {
													users.setRetryAttempt(1);
													users.setLastLogin(null);
													users = this.userRepositry.saveOrUpdateUser(users);
													
													tokenResponse.setMobileAccessFlag(0);
													tokenResponse.setUserExist(false);
													tokenResponse.setUnauthorized("false");
													tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
													tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
													tokenResponse.setAccess_token("404");
												}
												else{
													users.setRetryAttempt(users.getRetryAttempt()+1);
													users.setLastLogin(new Timestamp(System.currentTimeMillis()));
													users = this.userRepositry.saveOrUpdateUser(users);
													
													tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");																    	
													tokenResponse.setMobileAccessFlag(0);
													tokenResponse.setUserExist(false);
													tokenResponse.setUnauthorized("false");
													tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
													tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
													tokenResponse.setAccess_token("404");
												}
													
												
								    		}
								    		else{
								    			users.setRetryAttempt(users.getRetryAttempt()+1);
								    			users.setLastLogin(new Timestamp(System.currentTimeMillis()));
								    			users = this.userRepositry.saveOrUpdateUser(users);
								    			
								    			
								    			tokenResponse.setFailedAttempt("You Have Entered Wrong Password for 5 times ");					    	
												tokenResponse.setMobileAccessFlag(0);
												tokenResponse.setUserExist(false);
												tokenResponse.setUnauthorized("false");
												tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
												tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
												tokenResponse.setAccess_token("404");
								    		}
								    	}
							 }
							
						    	
						    	
						    	//}			 
							
						}
					}
					
					
					}
					else{
						tokenResponse=new TokenResponse();	
						tokenResponse.setMacIdStatus(false);
						tokenResponse.setMobileAccessFlag(0);
						tokenResponse.setUserExist(false);
						tokenResponse.setUnauthorized("false");
						tokenResponse.setAccess_token("409");
						tokenResponse.setStatus(409);
						tokenResponse.setAppUrl(appVersionMaster.getAppUrl());
						
					}
					
				}
				
				else if(userData.getModelName() != null && userDtoObj.getRoles().get(0).getMobileAccessFlag()==0  && userDtoObj.getIsBlocked()==1 && userDtoObj.getIsDeleted()==0){
					tokenResponse=new TokenResponse();	
					tokenResponse.setMacIdStatus(false);
					tokenResponse.setMobileAccessFlag(0);
					tokenResponse.setUserExist(false);
					tokenResponse.setUnauthorized("true");
					tokenResponse.setAccess_token("404");
				}
				
				else if(userData.getModelName() != null && userDtoObj.getRoles().get(0).getMobileAccessFlag()==1  && userDtoObj.getIsBlocked()!=1 && userDtoObj.getIsDeleted()!=0){
										
					tokenResponse=new TokenResponse();
					tokenResponse.setMobileAccessFlag(0);
					tokenResponse.setUserExist(false);
					tokenResponse.setUnauthorized("false");
					tokenResponse.setAccess_token("404");
					tokenResponse.setStatus(UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionCode());
					return tokenResponse;
				}
				
				
				else{
					tokenResponse=new TokenResponse();	
					tokenResponse.setMacIdStatus(false);
					tokenResponse.setMobileAccessFlag(0);
					tokenResponse.setUserExist(false);
					tokenResponse.setUnauthorized("false");
					tokenResponse.setAccess_token("404");
					
				}
		
				
			}
			
			else{
				if(userDtoObj!=null && userDtoObj.getRoles().get(0).getWebAccessFlag()==1  && userDtoObj.getIsBlocked()==1 && userDtoObj.getIsDeleted()==0){
				//if(userDtoObj!=null){
					userDto = this.userService.authenticateUserByAuuId(userData.getAuuId(),userData.getPassword());
					
					//userDto=new UsersDto();
					//userDto.setAuuIdFlag(false);
					if(userDto != null)
					{
						//b = true ;
						//b = false;
						if(userDto.getAuuIdFlag())
						{							
							MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
							map.add( "grant_type", "password");
							map.add( "client_id",userData.getAuuId());
							map.add( "client_secret", userData.getAuuId());
							map.add( "username",userData.getAuuId());
							map.add( "password",userData.getAuuId());
							map.add( "ldapPassword",userData.getPassword());
							HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);							
						    try{
						    	 ResponseEntity<TokenResponse> response = restTemplate.postForEntity( url, request , TokenResponse.class );				    	 
						    	 tokenResponse=response.getBody();
						    	 tokenResponse.setSubRoleId(userDtoObj.getRoles().get(0).getRoleId());
						    	 tokenResponse.setRoleId(userDtoObj.getRoles().get(0).getParentRoleId());
						    	 tokenResponse.setMobileAccessFlag(1);
						    	 tokenResponse.setUserExist(true);
						    	 tokenResponse.setIsPasswordModified(String.valueOf(userDtoObj.getIsPasswordModified()));
						    	 if(tokenResponse!=null){
						    		 Users users= userRepositry.getUserByAuuId(userDtoObj.getAuuid());
						    		 users.setLastLogin(new Timestamp(System.currentTimeMillis()));
						    		 users.setRetryAttempt(0);
						    		 users.setOtpCount(0);
						    		 users = this.userRepositry.saveOrUpdateUser(users);
						    	 }
						    }
						    catch(RestClientException e)
						    {
						    	

						    	tokenResponse=new TokenResponse();
						    	Users users= userRepositry.getUserByAuuId(userDtoObj.getAuuid());	
						    	
						    	if(users.getRetryAttempt()<4){
						    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
						    		users.setRetryAttempt(users.getRetryAttempt()+1);
						    		users = this.userRepositry.saveOrUpdateUser(users);
						    		
						    		tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
						    	}
						    	else{
						    		
						    		if(users.getRetryAttempt()>4){
						    			//for my internal purpose
						    			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
										Date current_timeStamp = new Date(timestamp.getTime());	
										Date last_login_timeStamp=null;
										if(users.getLastLogin()!=null){
											last_login_timeStamp= new Date(users.getLastLogin().getTime());
										}
										else{
											last_login_timeStamp= new Date(timestamp.getTime());
										}
										
										long timeDiff = current_timeStamp.getTime() - last_login_timeStamp.getTime();				
										long diffMinute = timeDiff / (60 * 1000) % 60;
										//int blockingPeriod = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
										int blockingPeriod = Integer.parseInt(BLOCKED_TIME_PERIOD);
										//for my internal purpose
										
										
										if (diffMinute > blockingPeriod) {
											users.setRetryAttempt(1);
											users.setLastLogin(null);
											users = this.userRepositry.saveOrUpdateUser(users);
											
											tokenResponse.setMobileAccessFlag(0);
											tokenResponse.setUserExist(false);
											tokenResponse.setUnauthorized("false");
											tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
											tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
											tokenResponse.setAccess_token("404");
										}
										else{
											users.setRetryAttempt(users.getRetryAttempt()+1);
											users.setLastLogin(new Timestamp(System.currentTimeMillis()));
											users = this.userRepositry.saveOrUpdateUser(users);
											
											tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");																    	
											tokenResponse.setMobileAccessFlag(0);
											tokenResponse.setUserExist(false);
											tokenResponse.setUnauthorized("false");
											tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
											tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
											tokenResponse.setAccess_token("404");
										}
											
										
						    		}
						    		else{
						    			users.setRetryAttempt(users.getRetryAttempt()+1);
						    			users.setLastLogin(new Timestamp(System.currentTimeMillis()));
						    			users = this.userRepositry.saveOrUpdateUser(users);
						    			
						    			
						    			tokenResponse.setFailedAttempt("You Have Entered Wrong Password for 5 times ");					    	
										tokenResponse.setMobileAccessFlag(0);
										tokenResponse.setUserExist(false);
										tokenResponse.setUnauthorized("false");
										tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
										tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
										tokenResponse.setAccess_token("404");
						    		}
						    	}
						    	
						    	
						    	
						    	/*
						       //e.printStackTrace();
						    	tokenResponse=new TokenResponse();
						    	Users users= userRepositry.getUserByAuuId(userDtoObj.getAuuid());
						    	
						    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								Date current_timeStamp = new Date(timestamp.getTime());	
								Date last_login_timeStamp=null;
								if(users.getLastLogin()!=null){
									last_login_timeStamp= new Date(users.getLastLogin().getTime());
								}
								else{
									last_login_timeStamp= new Date(timestamp.getTime());
								}
								
								long timeDiff = current_timeStamp.getTime() - last_login_timeStamp.getTime();				
								long diffMinute = timeDiff / (60 * 1000) % 60;
											
								int blockingPeriod = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
								if (diffMinute <= blockingPeriod) {
									if(users.getRetryAttempt()<4){
							    		users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(users.getRetryAttempt()+1);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    	}
							    	else{
							    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(4);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    		tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");
							    	}
						    		
							    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								} 
								else if (diffMinute <= blockingPeriod &&  users.getRetryAttempt()<4) {
									if(users.getRetryAttempt()<4){
							    		users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(users.getRetryAttempt()+1);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    	}
							    	else{
							    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(4);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    		tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");
							    	}
						    		
							    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								}
								else if (diffMinute > blockingPeriod &&  users.getRetryAttempt()<4) {
									if(users.getRetryAttempt()<4){
							    		users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(users.getRetryAttempt()+1);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    	}
							    	else{
							    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(4);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    		tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");
							    	}
						    		
							    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								}
								
								else {
									tokenResponse.setFailedAttempt("You Have Entered Wrong Password for More Than 5 times ");					    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								}	    	
						    	
						    	
						    	
						    */}			   
					     }
						 else{
							 
							    //for my internal purpose
				    			/*Timestamp timestampObj = new Timestamp(System.currentTimeMillis());
								Date current_timeStampObj = new Date(timestampObj.getTime());	
								Date last_login_timeStampObj=null;
								if(userDtoObj.getLastLogin()!=null){
									last_login_timeStampObj= new Date(userDtoObj.getLastLogin().getTime());
								}
								else{
									last_login_timeStampObj= new Date(timestampObj.getTime());
								}
								
								long timeDiffObj = current_timeStampObj.getTime() - last_login_timeStampObj.getTime();				
								long diffMinuteObj = timeDiffObj / (60 * 1000) % 60;
								int blockingPeriodObj = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
								//for my internal purpose
							 
							 if(userDtoObj.getRetryAttempt()>0 && diffMinuteObj < blockingPeriodObj){
								    
								    tokenResponse=new TokenResponse();
								    tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");																    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								 
							 }
							 else{*/
								 MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
									map.add( "grant_type", "password");
									map.add( "client_id",userData.getAuuId());
									map.add( "client_secret", userData.getPassword());
									map.add( "username",userData.getAuuId());
									map.add( "password",userData.getPassword());	
									HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);							
								    try{
								    	 ResponseEntity<TokenResponse> response = restTemplate.postForEntity( url, request , TokenResponse.class );				    	 
								    	 tokenResponse=response.getBody();	
								    	 tokenResponse.setSubRoleId(userDtoObj.getRoles().get(0).getRoleId());
								    	 tokenResponse.setRoleId(userDtoObj.getRoles().get(0).getParentRoleId());
								    	 tokenResponse.setMobileAccessFlag(1);
								    	 tokenResponse.setUserExist(true);
								    	 tokenResponse.setIsPasswordModified(String.valueOf(userDtoObj.getIsPasswordModified()));
								    	 if(tokenResponse!=null){
								    		 Users users= userRepositry.getUserByName(userDtoObj.getUserName());
								    		 users.setLastLogin(new Timestamp(System.currentTimeMillis()));
								    		 users.setRetryAttempt(0);
								    		 users.setOtpCount(0);
								    		 users = this.userRepositry.saveOrUpdateUser(users);
								    	 }
								    }
								    catch(RestClientException e)
								    {

									     tokenResponse=new TokenResponse();
									     Users users= userRepositry.getUserByName(userDtoObj.getUserName());	
									    	
									    	if(users.getRetryAttempt()<4){
									    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
									    		users.setRetryAttempt(users.getRetryAttempt()+1);
									    		users = this.userRepositry.saveOrUpdateUser(users);
									    		
									    		tokenResponse.setMobileAccessFlag(0);
												tokenResponse.setUserExist(false);
												tokenResponse.setUnauthorized("false");
												tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
												tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
												tokenResponse.setAccess_token("404");
									    	}
									    	else{
									    		
									    		if(users.getRetryAttempt()>4){
									    			//for my internal purpose
									    			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
													Date current_timeStamp = new Date(timestamp.getTime());	
													Date last_login_timeStamp=null;
													if(users.getLastLogin()!=null){
														last_login_timeStamp= new Date(users.getLastLogin().getTime());
													}
													else{
														last_login_timeStamp= new Date(timestamp.getTime());
													}
													
													long timeDiff = current_timeStamp.getTime() - last_login_timeStamp.getTime();				
													long diffMinute = timeDiff / (60 * 1000) % 60;
													//int blockingPeriod = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
													int blockingPeriod = Integer.parseInt(BLOCKED_TIME_PERIOD);
													//for my internal purpose
													
													
													if (diffMinute > blockingPeriod) {
														users.setRetryAttempt(1);
														users.setLastLogin(null);
														users = this.userRepositry.saveOrUpdateUser(users);
														
														tokenResponse.setMobileAccessFlag(0);
														tokenResponse.setUserExist(false);
														tokenResponse.setUnauthorized("false");
														tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
														tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
														tokenResponse.setAccess_token("404");
													}
													else{
														users.setRetryAttempt(users.getRetryAttempt()+1);
														users.setLastLogin(new Timestamp(System.currentTimeMillis()));
														users = this.userRepositry.saveOrUpdateUser(users);
														
														tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes");																    	
														tokenResponse.setMobileAccessFlag(0);
														tokenResponse.setUserExist(false);
														tokenResponse.setUnauthorized("false");
														tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
														tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
														tokenResponse.setAccess_token("404");
													}
														
													
									    		}
									    		else{
									    			users.setRetryAttempt(users.getRetryAttempt()+1);
									    			users.setLastLogin(new Timestamp(System.currentTimeMillis()));
									    			users = this.userRepositry.saveOrUpdateUser(users);
									    			
									    			
									    			tokenResponse.setFailedAttempt("You Have Entered Wrong Password for 5 times ");					    	
													tokenResponse.setMobileAccessFlag(0);
													tokenResponse.setUserExist(false);
													tokenResponse.setUnauthorized("false");
													tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
													tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
													tokenResponse.setAccess_token("404");
									    		}
									    	}
							 }
							
						 
						    	
						    	
						    	/*
						    	tokenResponse=new TokenResponse();	
						    	userRepositry.evict2ndLevelCache();
						    	Users users= userRepositry.getUserByName(userDtoObj.getUserName());
						    	
						    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
								Date current_timeStamp = new Date(timestamp.getTime());	
								Date last_login_timeStamp=null;
								if(users.getLastLogin()!=null){
									last_login_timeStamp= new Date(users.getLastLogin().getTime());
								}
								else{
									last_login_timeStamp= new Date(timestamp.getTime());
								}
								
								long timeDiff = current_timeStamp.getTime() - last_login_timeStamp.getTime();				
								long diffMinute = timeDiff / (60 * 1000) % 60;
											
								int blockingPeriod = Integer.parseInt(messageSource.getMessage(BLOCKED_TIME_PERIOD, null,Locale.getDefault()));
						    	
						    	
								if (diffMinute <= blockingPeriod) {
									if(users.getRetryAttempt()<4){
										userRepositry.evict2ndLevelCache();
							    		users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(users.getRetryAttempt()+1);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    	}
							    	else{
							    		userRepositry.evict2ndLevelCache();
							    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(4);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    		tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes ");
							    	}
						    		
							    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								} 
								else if (diffMinute <= blockingPeriod && users.getRetryAttempt()<4) {
									if(users.getRetryAttempt()<4){
										userRepositry.evict2ndLevelCache();
							    		users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(users.getRetryAttempt()+1);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    	}
							    	else{
							    		userRepositry.evict2ndLevelCache();
							    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(4);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    		tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes ");
							    	}
						    		
							    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								}
								else if (diffMinute > blockingPeriod && users.getRetryAttempt()<4) {
									if(users.getRetryAttempt()<4){
										userRepositry.evict2ndLevelCache();
							    		users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(users.getRetryAttempt()+1);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    	}
							    	else{
							    		userRepositry.evict2ndLevelCache();
							    		//users.setLastLogin(new Timestamp(System.currentTimeMillis()));
							    		users.setRetryAttempt(4);
							    		users = this.userRepositry.saveOrUpdateUser(users);
							    		tokenResponse.setFailedAttempt("Your account has been suspended for 5 minutes ");
							    	}
						    		
							    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								}
								
								else {
									tokenResponse.setFailedAttempt("You Have Entered Wrong Password for More Than 5 times ");						    	
									tokenResponse.setMobileAccessFlag(0);
									tokenResponse.setUserExist(false);
									tokenResponse.setUnauthorized("false");
									tokenResponse.setStatus(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionCode());
									tokenResponse.setResponseMessage(UserExceptionCodes.USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED.getExceptionDescription());
									tokenResponse.setAccess_token("404");
								}
								
						    */
								    //}			 
							
						}
					}
					else{
						tokenResponse=new TokenResponse();	
						tokenResponse.setStatus(UserExceptionCodes.USER_OR_PASSWORD_NOT_EXISTS.getExceptionCode());
						tokenResponse.setMacIdStatus(false);
						tokenResponse.setMobileAccessFlag(0);
						tokenResponse.setUserExist(false);
						tokenResponse.setUnauthorized("false");
						tokenResponse.setAccess_token("404");
					
					}
					
					
				}				
				
				
				else{
					tokenResponse=new TokenResponse();	
					tokenResponse.setStatus(UserExceptionCodes.INACTIVE_USER.getExceptionCode());					
					tokenResponse.setMacIdStatus(false);
					tokenResponse.setMobileAccessFlag(0);
					tokenResponse.setUserExist(false);
					tokenResponse.setUnauthorized("false");
					tokenResponse.setAccess_token("404");
					return tokenResponse;
				
				}
			}
			
			
			
		} catch (UserException e) {
			e.printStackTrace();
			
		}
		
		
		return tokenResponse;
	}


	@Override
	public BaseResponse<?> forgotPassword(UserDetailsDto userDetailsDto) throws BusinessException, SystemException, IOException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UserDetailsDto userDto = null;
		UserResponse userObj = null;
		try {
			userDto = this.userService.forgotPassword(userDetailsDto);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			userObj=new UserResponse();
			userObj.setUserDetailsDto(userDto);
			userResponse.setBody(userObj);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionCode(),
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@Override
	public BaseResponse<?> resetPassword(UsersDto userData) throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.resetPassword(userData);
			if(userDto.getNotAllowed()){
				responseDto = KycResponseUtils.getKycResponse(
						UserExceptionCodes.NOT_ALLOWED_TO_CHANGE_PWD.getExceptionCode(),
						UserExceptionCodes.NOT_ALLOWED_TO_CHANGE_PWD.getExceptionDescription());
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
				userResponse.setUserDto(userDto);
			}
			
			
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.INVALID_MISISDN.getExceptionCode(),
					UserExceptionCodes.INVALID_MISISDN.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@Override
	public BaseResponse<?> saveCommissionRule(CommissionRuleDto commissionRuleDto) throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		CommissionRuleDto commissionRuleObj = null;
		try {
			commissionRuleObj = this.helperDataManagementService.saveCommissionRule(commissionRuleDto);				
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);			
		} catch (BusinessException e) {
			
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionCode(),
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse<UserResponse> getCommissionRule(CommissionRuleDto commissionRuleDto)throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		CommissionRuleDto commissionRuleObj = null;
		try {
			commissionRuleObj = this.helperDataManagementService.getCommissionRule(commissionRuleDto);
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setBody(commissionRuleObj);
			//userResponse.setCommissionRuleDto(commissionRuleObj);
			
			
		} catch (BusinessException e) {
			// e.printStackTrace();
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionCode(),
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionDescription());
		}
		userResponse.setTotalCountDto(null);
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@Override
	public BaseResponse<UserResponse> saveAndSendHelpAndSupportData(HelpAndSupportDto helpAndSupport) {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		HelpAndSupportDto helpAndSupportDto = null;
		try {
			helpAndSupportDto = this.helperDataManagementService.saveAndSendHelpAndSupportData(helpAndSupport);				
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK);			
		} catch (BusinessException e) {
			
			responseDto = KycResponseUtils.getKycResponse(
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionCode(),
					UserExceptionCodes.USER_NOT_AVAILABLE.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}
	
	/*@Override
	public BaseResponse<?> getUserById(int uid) throws BusinessException, SystemException {

		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.getUserById(uid);
			userDto.setPassword(null);
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDto(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}

	

	@Override
	public BaseResponse<?> updateUser(int uid, UsersDto bean) throws BusinessException, SystemException {

		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.updateUser(uid, bean);
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDto(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}

	@Override
	public BaseResponse<?> updateBypass(Integer isByPassflag) throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		try {
			this.userService.updateBypass(isByPassflag);
			userResponse.setIsByPassflag(isByPassflag);
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
		} catch (UserException e) {

		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}

	@Override
	public BaseResponse<?> getBypassStatus() throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		Integer isByPassflag = 0;
		try {
			isByPassflag = this.userService.getBypassStatus(null);
			userResponse.setIsByPassflag(isByPassflag);
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
		} catch (UserException e) {

		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	

	@Override
	public BaseResponse<?> updateUserPassword(int uid, String password, String newPassword, String confirmPassword)
			throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = this.userService.getUserById(uid);
		if (userDto == null) {
			responseDto = KycugResponseUtils.getKyctnzResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(KycConstants.PASSWORD_LENGTH);
		// System.out.println(cryptPasswordEncoder.matches("Airtel@123",
		// "$2a$11$MgWCIu2vepR1e7C02DoH5OasUEhBoZYAohWw9gaSVMJyMg9Z7G0nW"));

	@Override
	public BaseResponse<?> updateUserPassword(int uid, String password, String newPassword, String confirmPassword)
			throws BusinessException, SystemException {
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = this.userService.getUserById(uid);
		if (userDto == null) {
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(KycConstants.PASSWORD_LENGTH);
		// System.out.println(cryptPasswordEncoder.matches("Airtel@123",
		// "$2a$11$MgWCIu2vepR1e7C02DoH5OasUEhBoZYAohWw9gaSVMJyMg9Z7G0nW"));




		if (!cryptPasswordEncoder.matches(password, userDto.getPassword())) {
			responseDto = KycugResponseUtils.getKyctnzResponse(
					UserExceptionCodes.CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH.getExceptionCode(),
					UserExceptionCodes.CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		if (cryptPasswordEncoder.matches(newPassword, userDto.getPassword())) {
			responseDto = KycugResponseUtils.getKyctnzResponse(
					UserExceptionCodes.OLD_AND_NEW_PASSWORD_CAN_NOT_SAME.getExceptionCode(),
					UserExceptionCodes.OLD_AND_NEW_PASSWORD_CAN_NOT_SAME.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		if (!newPassword.equalsIgnoreCase(confirmPassword)) {
			responseDto = KycugResponseUtils.getKyctnzResponse(
					UserExceptionCodes.NEW_AND_CONFIRM_PASSWORD_NOT_SAME.getExceptionCode(),
					UserExceptionCodes.NEW_AND_CONFIRM_PASSWORD_NOT_SAME.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		try {
			userDto.setPassword(cryptPasswordEncoder.encode(newPassword));
			userDto.setIsPasswordModified(KycConstants.TRUE);
			this.userService.updatePassword(uid, userDto);
			try {
				boolean apiAcess = this.userService.provisionAPIAcsess(userDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			responseDto = KycugResponseUtils.getKyctnzResponse(HttpStatus.OK);
			// userResponse.setUserDto(userDto);
		} catch (UserException e) {
			responseDto = KycugResponseUtils.getKyctnzResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


		if (!cryptPasswordEncoder.matches(password, userDto.getPassword())) {
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH.getExceptionCode(),
					UserExceptionCodes.CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		if (cryptPasswordEncoder.matches(newPassword, userDto.getPassword())) {
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.OLD_AND_NEW_PASSWORD_CAN_NOT_SAME.getExceptionCode(),
					UserExceptionCodes.OLD_AND_NEW_PASSWORD_CAN_NOT_SAME.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		if (!newPassword.equalsIgnoreCase(confirmPassword)) {
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.NEW_AND_CONFIRM_PASSWORD_NOT_SAME.getExceptionCode(),
					UserExceptionCodes.NEW_AND_CONFIRM_PASSWORD_NOT_SAME.getExceptionDescription());
			userResponse.setResponseDto(responseDto);
			return userResponse;
		}
		try {
			userDto.setPassword(cryptPasswordEncoder.encode(newPassword));
			userDto.setIsPasswordModified(KycConstants.TRUE);
			this.userService.updatePassword(uid, userDto);
			try {
				boolean apiAcess = this.userService.provisionAPIAcsess(userDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
			// userResponse.setUserDto(userDto);
		} catch (UserException e) {
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}


	@Override
	public BaseResponse<?> getUserByName(String userName) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		TotalCountDto totalCountDto = new TotalCountDto();
		UserResponse userResponse = new UserResponse();
		ResponseDto responseDto = null;
		UsersDto userDto = null;
		try {
			userDto = this.userService.getUserByName(userName);
			userDto.setPassword(null);
			// this.userService.
			totalCountDto = subscriberManagementService.getSubscriberCount(0,null);
			//long subscriberCount = subscriberManagementService.getSubscriberCount();
			//totalCountDto.setSubscriberCount(totalCountDto.getSubscriberCount());
			userResponse.setTotalCountDto(totalCountDto);
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
			userResponse.setUserDto(userDto);
		} catch (UserException e) {
			// e.printStackTrace();
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionCode(),
					UserExceptionCodes.USER_UNABLE_TO_REGISTERED.getExceptionDescription());
		}
		userResponse.setResponseDto(responseDto);
		return userResponse;
	}

	

	@Override
	public BaseResponse<?> updateUserActiveStatus(int uid, boolean isActive) throws BusinessException, SystemException {
		BaseResponse<T> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = null;
		UsersDto userDto = new UsersDto();
		userDto.setUserId(uid);
		int isActiveD = (isActive) ? TRUE  : FALSE;
		userDto.setIsActive(isActiveD);		
		try {
			userDto = this.userService.updateUser(uid, userDto);
			responseDto = KycugResponseUtils.getKycResponse(HttpStatus.OK);
			Map<String , String> map = new HashMap<>();
			if(userDto == null) {
				responseDto = KycugResponseUtils.getKycResponse(
						UserExceptionCodes.UNABLE_TO_UPDATE_USER.getExceptionCode(),
						UserExceptionCodes.UNABLE_TO_UPDATE_USER.getExceptionDescription());
			}
			map.put("isactive", isActive+"");
			responseDto = KycugResponseUtils.getKycResponse(ResponseCodes.SUCCESS_RESPONSE_CODE.getResponseCode(),
					ResponseCodes.SUCCESS_RESPONSE_CODE.getResponseDescription());
		} catch (UserException e) {
			responseDto = KycugResponseUtils.getKycResponse(
					UserExceptionCodes.UNABLE_TO_UPDATE_USER.getExceptionCode(),
					UserExceptionCodes.UNABLE_TO_UPDATE_USER.getExceptionDescription());
		}
		return baseResponse;
	}
*/
}
