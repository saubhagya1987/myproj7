package com.airtel.kyc.business.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SubscriberException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.business.service.HelperDataManagementService;
import com.airtel.kyc.business.service.SubscriberManagementService;
import com.airtel.kyc.business.service.TaskManagementService;
import com.airtel.kyc.business.service.UserManagementService;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ActionCodes;
import com.airtel.kyc.enumData.SubscriberExceptionCodes;
import com.airtel.kyc.enumData.TaskEnum;
import com.airtel.kyc.enumData.UserExceptionCodes;
import com.airtel.kyc.helpers.dto.AddressDto;
import com.airtel.kyc.helpers.dto.BaseDto;
import com.airtel.kyc.helpers.dto.Data;
import com.airtel.kyc.helpers.dto.DistrictDto;
import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberDocumentsDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberIdDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberImageDetailDto;
import com.airtel.kyc.helpers.dto.SubscriberSearchResponseDto;
import com.airtel.kyc.helpers.dto.SubscriberUserDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.helpers.dto.TotalCountDto;
import com.airtel.kyc.helpers.dto.VillageDto;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.dao.AddressDao;
import com.airtel.kyc.repository.dao.CMDao;
import com.airtel.kyc.repository.dao.DistrictDao;
import com.airtel.kyc.repository.dao.GKYCStatusDao;
import com.airtel.kyc.repository.dao.KycDaoService;
import com.airtel.kyc.repository.dao.SubscriberDao;
import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.dao.SubscriberIdDetailsDao;
import com.airtel.kyc.repository.dao.SubscriberWorkFlowDao;
import com.airtel.kyc.repository.dao.SubscriberWorkFlowHistoryDao;
import com.airtel.kyc.repository.dao.TemplatesDao;
import com.airtel.kyc.repository.dao.VillageDao;
import com.airtel.kyc.repository.entity.Address;
import com.airtel.kyc.repository.entity.Assignment;
import com.airtel.kyc.repository.entity.District;
import com.airtel.kyc.repository.entity.Location;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.SubscriberDocuments;
import com.airtel.kyc.repository.entity.SubscriberIdDetails;
import com.airtel.kyc.repository.entity.SubscriberImageDetail;
import com.airtel.kyc.repository.entity.SubscriberUser;
import com.airtel.kyc.repository.entity.SubscriberWorkFlow;
import com.airtel.kyc.repository.entity.SubscriberWorkFlowHistory;
import com.airtel.kyc.repository.entity.Templates;
import com.airtel.kyc.repository.entity.UserDistrict;
import com.airtel.kyc.repository.entity.UserProvince;
import com.airtel.kyc.repository.entity.VerificationByPass;
import com.airtel.kyc.repository.entity.Village;
import com.airtel.kyc.repository.exception.KycDaoException;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.utils.DateUtils;
import com.airtel.kyc.utils.Utility;
import com.airtel.user.helper.constant.ConfigConstants;
import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.DistrictIds;
import com.airtel.user.helper.dto.LocationDto;
import com.airtel.user.helper.dto.ProvinceIds;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.helper.util.MaskUtils;
import com.airtel.user.persistence.config.dao.UserRepositry;
import com.airtel.user.persistence.entities.Department;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.UserDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;


@Service
public class SubscriberManagementServiceImpl implements SubscriberManagementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberManagementServiceImpl.class);
	private static final String SECURITY_CODE_EXPIRY_TIME = "security.code.expiry.time";
	private static final String SECURITY_CODE_EXPIRED = "security.code.expired";
	private static final String SECURITY_CODE_INVALID = "security.code.invalid";
	private static final String SECURITY_CODE_VALIDATED = "security.code.validated";
	public static final String BASEIMAGE_URL="base.img.url";
	public static final String PERMISSABLE_RADIUS="permissable.radius";
	public static final String SMS_OTP_SUBSCRIBER = "SMS_OTP_SUBSCRIBER";
	public static final String SMS_OTP_FAILED_SUBSCRIBER = "SMS_OTP_FAILED_SUBSCRIBER";
	
	
	

	@SuppressWarnings("rawtypes")
	@Autowired
	private KycDaoService kycDaoService;
	
	@Autowired	
	private HelperDataManagementService helperDataManagementService;
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private TaskManagementService taskManagementService;
	
	/*@Autowired
	private MessageSource messageSource;*/
	
	@Autowired
	private SubscriberDao subscriberDao;
	
	@Autowired
	private TemplatesDao templatesDao;
	
	@Autowired
	private DistrictDao districtDao;
	
	@Autowired
	private SubscriberIdDetailsDao subscriberIdDetailsDao;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private SubscriberDetailsDao subscriberDetailsDao;
	
	@Autowired
	private IntegrationService integrationService;
	
	@Qualifier("userRepositry")
	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	private VillageDao villageDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private SubscriberWorkFlowDao subscriberWorkFlowDao;
	
	@Autowired
	private SubscriberWorkFlowHistoryDao subscriberWorkFlowHistoryDao;

	@Autowired
	private CMDao cMDao;
	
	@Autowired
	private AesUtil aesUtil;
	

	@Autowired
	GKYCStatusDao gKYCStatusDao;
	
	private static Log log = LogFactory.getLog(SubscriberManagementServiceImpl.class);
	private static boolean isInfoEnabled = log.isInfoEnabled();
	private static boolean isDebugEnabled = log.isDebugEnabled();
	private static final String IS_VALIDATION_ENABLED = "validation.enabled";
	public static final String SMS_BARRED_BY_CM = "SMS_BARRED_BY_CM";
	
	//Boolean isValidationEnabled=Boolean.valueOf(environment.getProperty(IS_VALIDATION_ENABLED));
	
	@SuppressWarnings("unchecked")
	@Override
	public SubscriberSearchResponseDto getSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		//searchSubscriberDto.setIsByPassflag(getBypassStatus());
		try {
			subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto, actionType);

			for (Subscriber subscriber : subscriberList) {
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);

				List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
						KycConstants.FALSE, false);

				subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
				subscriberDto.setOperactionOnSubscriber(null);
				subscriberDto.setActionOnSubscriber(null);
				//subscriberDto.setSubscriberWorkFlow(null);

				subscriberDtoList.add(subscriberDto);
			}
			long totalCount;

			totalCount = this.kycDaoService.getTotalSubscriber(searchSubscriberDto, actionType);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SubscriberSearchResponseDto getCMSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		try {
			if("PENDING_ALL".equals(actionType)){
				subscriberList = (List<Subscriber>) this.kycDaoService.getPendingSubscriber(searchSubscriberDto, actionType);	
			}
			else if("CLOSED_ALL".equals(actionType)){
				if(searchSubscriberDto.getKey()==null){
				     subscriberList = (List<Subscriber>) this.kycDaoService.getClosedSubscriber(searchSubscriberDto, actionType);
				}
				
				else if(searchSubscriberDto.getKey().equals("APPROVED")){
					subscriberList = (List<Subscriber>) this.kycDaoService.getApprovedSubscriber(searchSubscriberDto, actionType);
				}
				else if(searchSubscriberDto.getKey().equals("BARRED")){
					subscriberList = (List<Subscriber>) this.kycDaoService.getBarredSubscriberClosed(searchSubscriberDto, actionType);
				}
				else if(searchSubscriberDto.getKey().equals("REJECTED")){
					subscriberList = (List<Subscriber>) this.kycDaoService.getRejectedSubscriberClosed(searchSubscriberDto, actionType);
				}
				else if(searchSubscriberDto.getKey().equals("HOTLINE")){
					subscriberList = (List<Subscriber>) this.kycDaoService.getHotlinedSubscriberClosed(searchSubscriberDto, actionType);
				}				
				
			}
			else{
				subscriberList = (List<Subscriber>) this.kycDaoService.getCmSubscriber(searchSubscriberDto, actionType);
			}

			for (Subscriber subscriber : subscriberList) {
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);
				List<SubscriberDetails> subscriberDetailsList = subscriber.getSubscriberDetails();
				GKYCStatusResponseDto caseAssignedNumber =null;
				
				
				List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
				if("PENDING_ALL".equals(actionType)){
					for(SubscriberDetails subDetails:subscriberDetailsList){
						if("PENDING".equals(subDetails.getFinalStatus()) && (0==subDetails.getIsOldUserDetails() 
								|| (1==subDetails.getIsOldUserDetails() && subscriber.getUpdatedOn().equals(subDetails.getUpdatedOn())))){
							SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subDetails.getSubscriberDetailsId());
							if(workflow!=null){
								caseAssignedNumber = gKYCStatusDao.getAuuId(workflow.getUserId());
							}							
							SubscriberDetailsDto subscriberDetailsDto =new SubscriberDetailsDto();
							BeanUtils.copyProperties(subDetails,subscriberDetailsDto);
							if(caseAssignedNumber!=null){
								subscriberDetailsDto.setCaseAssignedNumber(caseAssignedNumber.getAuuid());
								subscriberDetailsDto.setUserName(caseAssignedNumber.getUserName());
							}							
							subscriberDetailsDtoList.add(subscriberDetailsDto);
						}
					}
				}else if("CLOSED_ALL".equals(actionType)){
					for(SubscriberDetails subDetails:subscriberDetailsList){
						if(("APPROVED".equals(subDetails.getFinalStatus()) || "BARRED".equals(subDetails.getFinalStatus()) && 
								(0==subDetails.getIsOldUserDetails()))){
							SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subDetails.getSubscriberDetailsId());
							if(workflow!=null){
								caseAssignedNumber = gKYCStatusDao.getAuuId(workflow.getUserId());
							}	
							SubscriberDetailsDto subscriberDetailsDto =new SubscriberDetailsDto();
							BeanUtils.copyProperties(subDetails,subscriberDetailsDto);
							if(caseAssignedNumber!=null){
								subscriberDetailsDto.setCaseAssignedNumber(caseAssignedNumber.getAuuid());	
								subscriberDetailsDto.setUserName(caseAssignedNumber.getUserName());
							}	
							subscriberDetailsDtoList.add(subscriberDetailsDto);
						}
					}
				}else if("APPROVED".equals(actionType)){
					for(SubscriberDetails subDetails:subscriberDetailsList){
						if(("APPROVED".equals(subDetails.getFinalStatus()) && 
								(0==subDetails.getIsOldUserDetails()))){
							SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subDetails.getSubscriberDetailsId());
							if(workflow!=null){
								caseAssignedNumber = gKYCStatusDao.getAuuId(workflow.getUserId());
							}	
							SubscriberDetailsDto subscriberDetailsDto =new SubscriberDetailsDto();
							BeanUtils.copyProperties(subDetails,subscriberDetailsDto);
							if(caseAssignedNumber!=null){
								subscriberDetailsDto.setCaseAssignedNumber(caseAssignedNumber.getAuuid());	
								subscriberDetailsDto.setUserName(caseAssignedNumber.getUserName());
							}	
							subscriberDetailsDtoList.add(subscriberDetailsDto);
						}
					}
				}else if("BARRED".equals(actionType)){
					for(SubscriberDetails subDetails:subscriberDetailsList){
						if("BARRED".equals(subDetails.getFinalStatus()) && (0==subDetails.getIsOldUserDetails() 
								|| (1==subDetails.getIsOldUserDetails() && subscriber.getUpdatedOn().equals(subDetails.getUpdatedOn())))){
							SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subDetails.getSubscriberDetailsId());
							if(workflow!=null){
								caseAssignedNumber = gKYCStatusDao.getAuuId(workflow.getUserId());
							}	
							SubscriberDetailsDto subscriberDetailsDto =new SubscriberDetailsDto();
							BeanUtils.copyProperties(subDetails,subscriberDetailsDto);
							if(caseAssignedNumber!=null){
								subscriberDetailsDto.setCaseAssignedNumber(caseAssignedNumber.getAuuid());	
								subscriberDetailsDto.setUserName(caseAssignedNumber.getUserName());
							}
							subscriberDetailsDtoList.add(subscriberDetailsDto);
						}
					}
				}else if("REJECTED".equals(actionType)){
					for(SubscriberDetails subDetails:subscriberDetailsList){
						if("REJECTED".equals(subDetails.getFinalStatus()) && (0==subDetails.getIsOldUserDetails() 
								|| (1==subDetails.getIsOldUserDetails() && subscriber.getUpdatedOn().equals(subDetails.getUpdatedOn())))){
							SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subDetails.getSubscriberDetailsId());
							if(workflow!=null){
								caseAssignedNumber = gKYCStatusDao.getAuuId(workflow.getUserId());
							}
							SubscriberDetailsDto subscriberDetailsDto =new SubscriberDetailsDto();
							BeanUtils.copyProperties(subDetails,subscriberDetailsDto);
							if(caseAssignedNumber!=null){
								subscriberDetailsDto.setCaseAssignedNumber(caseAssignedNumber.getAuuid());
								subscriberDetailsDto.setUserName(caseAssignedNumber.getUserName());
							}
							subscriberDetailsDtoList.add(subscriberDetailsDto);
						}
					}
				}else if("HOTLINE".equals(actionType)){
					for(SubscriberDetails subDetails:subscriberDetailsList){
						if(subDetails.getHotlineFlag()==1 && (0==subDetails.getIsOldUserDetails() 
								|| (1==subDetails.getIsOldUserDetails() && subscriber.getUpdatedOn().equals(subDetails.getUpdatedOn())))){
							SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subDetails.getSubscriberDetailsId());
							if(workflow!=null){
								caseAssignedNumber = gKYCStatusDao.getAuuId(workflow.getUserId());
							}
							SubscriberDetailsDto subscriberDetailsDto =new SubscriberDetailsDto();
							BeanUtils.copyProperties(subDetails,subscriberDetailsDto);
							if(caseAssignedNumber!=null){
								subscriberDetailsDto.setCaseAssignedNumber(caseAssignedNumber.getAuuid());
								subscriberDetailsDto.setUserName(caseAssignedNumber.getUserName());
							}
							subscriberDetailsDtoList.add(subscriberDetailsDto);
						}
					}
				}
				
				subscriberDto.setSubscriberDetail(subscriberDetailsDtoList);
				subscriberDtoList.add(subscriberDto);
			}
			
			long totalCount = 0;


			if("PENDING_ALL".equals(actionType)){
				totalCount =  this.kycDaoService.getTotalPendingSubscriber(searchSubscriberDto, actionType);	
			}
			else if("CLOSED_ALL".equals(actionType)){
				if(searchSubscriberDto.getKey()==null){
					totalCount =  this.kycDaoService.getTotalClosedSubscriber(searchSubscriberDto, actionType);
				}
				
				else if(searchSubscriberDto.getKey().equals("APPROVED")){
					totalCount =  this.kycDaoService.getTotalApprovedSubscriber(searchSubscriberDto, actionType);
				}
				else if(searchSubscriberDto.getKey().equals("BARRED")){
					totalCount =  this.kycDaoService.getTotalBarredSubscriberClosed(searchSubscriberDto, actionType);
				}
				else if(searchSubscriberDto.getKey().equals("REJECTED")){
					totalCount =  this.kycDaoService.getTotalRejectedSubscriberClosed(searchSubscriberDto, actionType);
				}
				else if(searchSubscriberDto.getKey().equals("HOTLINE")){
					totalCount =  this.kycDaoService.getTotalHotlinedSubscriberClosed(searchSubscriberDto, actionType);
				}				
				
			}
			else{
				totalCount =  this.kycDaoService.getTotalCmSubscriber(searchSubscriberDto, actionType);
			}

			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);
		} catch (KycDaoException e) {
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public SubscriberSearchResponseDto assignToMe(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		try {
			
			//subscriberList = (List<Subscriber>) this.kycDaoService.getCmSubscriber(searchSubscriberDto, actionType);
			
			searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
			searchSubscriberDto.setAction("PENDING");
			subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto, "AUTO_ASSIGN");

			if(subscriberList!=null && subscriberList.size()>0){
				Subscriber subscriber = subscriberList.get(0);
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);
				List<SubscriberDetails> subscriberDetailsList = subscriber.getSubscriberDetails();
				
				List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
				for(SubscriberDetails subDetails:subscriberDetailsList){
					if("PENDING".equals(subDetails.getFinalStatus()) && (0==subDetails.getIsOldUserDetails() 
							|| (1==subDetails.getIsOldUserDetails() && subscriber.getUpdatedOn().equals(subDetails.getUpdatedOn())))){
						SubscriberWorkFlowDto subscriberWorkFlowDto = new SubscriberWorkFlowDto();
						SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
						
						subscriberDetailsList.get(0).setWorkFlowStatus("PENDING");
						subscriberDetailsList.get(0).setAssignmentFlag(1);
						subscriberDetailsList.get(0).setFinalStatusReason("PENDING FOR CM APPROVAL");
						subscriberDetailsDto.setSubscriberDetailsId(subscriberDetailsList.get(0).getSubscriberDetailsId());
	
						subscriberWorkFlowDto.setSubscriberDetailsDto(subscriberDetailsDto);
						
						//kycDaoService.saveOrUpdateEntity(subscriber);
						
						subscriberWorkFlowDto.setAction("PENDING");
						subscriberWorkFlowDto.setRoleName("CM");
						subscriberWorkFlowDto.setStatusReason("PENDING FOR CM APPROVAL");
						subscriberWorkFlowDto.setSubscriberId(subscriber.getSubscriberId());
						subscriberWorkFlowDto.setUserId(searchSubscriberDto.getUserId());
						
						Assignment assignment = cMDao.getAssignmentDetail(subscriberWorkFlowDto.getUserId().toString());
						assignment.setCount(1);
						cMDao.saveObject(assignment);
						
						subscriberWorkFlowDto = (SubscriberWorkFlowDto)autoAssignSubscriber(subscriberWorkFlowDto,subscriberDetailsList);
					}
				}
					
				subscriberDto.setSubscriberDetail(subscriberDetailsDtoList);
				subscriberDtoList.add(subscriberDto);
				
				
				subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
				subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
				subscriberSearchResponseDto.setTotalRecord(1);
				subscriberSearchResponseDto.setRows(subscriberDtoList);
			}
		} catch (KycDaoException e) {
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public SubscriberSearchResponseDto getSubscriber(SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		
		StringBuilder sqlQuery = new StringBuilder();
		
		try {
			//subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(sqlQuery.toString());
			
			subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto, KycConstants.PENDING_STATUS);

			for (Subscriber subscriber : subscriberList) {
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);

				List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
						KycConstants.FALSE, false);

				subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
				subscriberDto.setOperactionOnSubscriber(null);
				subscriberDto.setActionOnSubscriber(null);
				//subscriberDto.setSubscriberWorkFlow(null);

				subscriberDtoList.add(subscriberDto);
			}
			long totalCount;

			totalCount = subscriberList.size();//this.kycDaoService.getTotalSubscriber(searchSubscriberDto, actionType);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	}

	public Integer getBypassStatus() throws BusinessException {
		List<VerificationByPass> list = null;
		Integer isByPassflag = 0;
		try {
			list = this.kycDaoService.getBypassStatus(KycConstants.TRUE);
			if (list != null && list.size() > 0)
				isByPassflag = list.get(0).getBypassStatus();
		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isByPassflag;
	}

	@SuppressWarnings("unchecked")
	public List<SubscriberDetailsDto> getSubscriberDetails(SubscriberDto subscriberDto, Integer isOldUserDetails,
			boolean isIdAndImage) throws BusinessException {
		// TODO Auto-generated method stub
		List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
		List<SubscriberDetails> subscriberDetailsList = null;
		try {
			subscriberDetailsList = (List<SubscriberDetails>) this.kycDaoService
					.getSubscriberDetails(subscriberDto.getSubscriberId(), KycConstants.FALSE);
			for (SubscriberDetails subscriberDetails : subscriberDetailsList) {
				SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
				BeanUtils.copyProperties(subscriberDetails, subscriberDetailsDto);
				if(subscriberDetails.getIsMinor()==1)
				 {
					subscriberDetailsDto.setIsMinor("Yes");
				 }
				else{
					subscriberDetailsDto.setIsMinor("No");
				}
				if(subscriberDetails.getAmAccount()==1)
				 {
					subscriberDetailsDto.setAmAccount("Yes");
				 }
				else{
					subscriberDetailsDto.setAmAccount("No");
				}
				subscriberDetailsDto.setFinalStatus(subscriberDetails.getFinalStatus());
				subscriberDetailsDto.setSimSerialNumber(subscriberDetails.getSimSerialNumber());
				//subscriberDetailsDto.setCountryId(subscriberDetails.getCountryId());
				if(subscriberDetails.getProxyForRegistration().equals("Yes")||subscriberDetails.getProxyForRegistration()==1){
					subscriberDetailsDto.setProxyForRegistration("Yes");
				}
				if(subscriberDetails.getProxyForRegistration().equals("No")||subscriberDetails.getProxyForRegistration()==0){
					subscriberDetailsDto.setProxyForRegistration("No");
				}
				
				
				/*List list = this.kycDaoService.getSubscriberAddress(subscriberDetailsDto.getSubscriberDetailsId());
				
				   
			    Object AddressId = ((HashMap) list.get(0)).get("ADDRESS_ID");		    
				
				AddressDto addressDto = subscriberDetailsDto.getAddress();
				Integer districtId = addressDto.getDistrict().getDistrictId();
				Integer addressId = ((BigDecimal) AddressId).intValue();*/
			 
				

				
				AddressDto addressDto=new AddressDto();				
				BeanUtils.copyProperties(subscriberDetails.getAddress(), addressDto);
				DistrictDto districtDto=new DistrictDto();
				districtDto.setDistrictId(subscriberDetails.getAddress().getDistrictId());
				addressDto.setDistrict(districtDto);
				addressDto.setCountryId(subscriberDetails.getAddress().getCountryId());
				subscriberDetailsDto.setDateOfBirth(DateUtils.getFormatDate(subscriberDetails.getDateOfBirth(),"dd-MM-yyyy"));

				/*Map<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("subscriberDetails", subscriberDetails);
				List<SubscriberIdDetails> details = this.kycDaoService.findByCriteria(SubscriberIdDetails.class,parameterMap);*/
				Integer susbscriberDetailsId=subscriberDetails.getSubscriberDetailsId();
				List<SubscriberIdDetails> details = subscriberIdDetailsDao.getSubscriberIdDetails(susbscriberDetailsId, KycConstants.FALSE);
				if(details.size()>0)
				{
					Set<SubscriberIdDetailsDto> detailsDtos = null;
					if (details != null && details.size() > 0) {
						detailsDtos = new HashSet<>();
						for (SubscriberIdDetails details2 : details) {
							SubscriberIdDetailsDto target = new SubscriberIdDetailsDto();
							BeanUtils.copyProperties(details2, target);
							
							
							if(details2.getIdPath()!=null && details2.getIdImageFront()!=null){
								RestTemplate restTemplate = new RestTemplate();	
								String url=environment.getProperty(BASEIMAGE_URL);
								//String url=messageSource.getMessage(BASEIMAGE_URL, null,Locale.getDefault());//"http://localhost:8081/kycug/api/subscriber/getSubscriberImage1";
							    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
							    		.path("/")
							    		.path(details2.getIdPath())
							    		.path("/")
							    		.path(subscriberDto.getKycTansactionId())
							    		.path("/")
							    		.path(details2.getIdImageFront())
							    		.path(".jpg")
							    	    .build()
							    	    .toUri();
							    System.out.println("targetUrl:"+targetUrl);	
							    HttpEntity<byte[]> response =null;
							    byte[] body = null;
							    try{
							    	response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
							    	body = response.getBody();
							    }
							    catch(RestClientException e){
							    	e.printStackTrace();
							    	
							    }
							    //System.out.println("body:"+response.getBody());
							    
							    
							    System.out.println("body:"+body);
							 
							    //String base64 = new String(Base64.encodeBase64(bytes));
							    String base64 = null;
								try {
									if(body!=null){
										base64 = new String(Base64.encodeBase64(body), "UTF-8");
									}
									
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    System.out.println("base64:"+base64);
							    
							
							//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
							    if(base64!=null){
							    	target.setIdImageFrontData(base64);
							    }
							
							
							
						}
							
							if(details2.getIdPath()!=null && details2.getPhysicalFormId()!=null){
								RestTemplate restTemplate = new RestTemplate();	
								
								//String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
								String url=environment.getProperty(BASEIMAGE_URL);
							    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
							    		.path("/")
							    		.path(details2.getIdPath())
							    		.path("/")
							    		.path(subscriberDto.getKycTansactionId())
							    		.path("/")
							    		.path(details2.getPhysicalFormId())
							    		.path(".jpg")
							    	    .build()
							    	    .toUri();
							    System.out.println("targetUrl:"+targetUrl);	
							    byte[] body = null;
							    try{
							    	HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);	
							    	body = response.getBody();
							    }
							    catch(RestClientException e){
							    	e.printStackTrace();
							    }
							    
							    //System.out.println("body:"+response.getBody());
							    
							    
							    System.out.println("body:"+body);
							 
							    //String base64 = new String(Base64.encodeBase64(bytes));
							    String physicalFormBase64 = null;
								try {
									if(body!=null){
										physicalFormBase64 = new String(Base64.encodeBase64(body), "UTF-8");
									}
									
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							   // System.out.println("base64:"+base64);
							    
							
							//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
								if(physicalFormBase64!=null){
									target.setPhysicalFormId(physicalFormBase64);
								}
							
							
							}
							
							/*if(details2.getIdPath()!=null && details2.getSubscriberImageId()!=null){
								RestTemplate restTemplate = new RestTemplate();	
								//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
								String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
							    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
							    		.path("/")
							    		.path(details2.getIdPath())
							    		.path("/")
							    		.path(subscriberDto.getKycTansactionId())
							    		.path("/")
							    		.path(details2.getSubscriberImageId())
							    		.path(".jpg")
							    	    .build()
							    	    .toUri();
							    System.out.println("targetUrl:"+targetUrl);	
							    byte[] body =null;
							    try{
							    	HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
							    	body = response.getBody();
							    }
							    catch(RestClientException e){
							    	e.printStackTrace();
							    }
							    //System.out.println("body:"+response.getBody());						    
							    
							    System.out.println("body:"+body);
							 
							    //String base64 = new String(Base64.encodeBase64(bytes));
							    String subImageBase64 = null;
								try {
									if(body!=null){
										subImageBase64 = new String(Base64.encodeBase64(body), "UTF-8");	
									}
									
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    //System.out.println("base64:"+base64);
							    
							
							//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
								if(subImageBase64!=null){
									target.setSubscriberImageId(subImageBase64);
								}
							
							
							}*/
							
							
							if(details2.getIdPath()!=null && details2.getIdImageBack()!=null){
								RestTemplate restTemplate = new RestTemplate();	
								//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
								//String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
								String url=environment.getProperty(BASEIMAGE_URL);
							    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
							    		.path("/")
							    		.path(details2.getIdPath())
							    		.path("/")
							    		.path(subscriberDto.getKycTansactionId())
							    		.path("/")
							    		.path(details2.getIdImageBack())
							    		.path(".jpg")
							    	    .build()
							    	    .toUri();
							    System.out.println("targetUrl:"+targetUrl);	
							    byte[] body =null;
							    try{
							    	HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
							    	body = response.getBody();
							    }
							    catch(RestClientException e){
							    	e.printStackTrace();
							    }
							    //System.out.println("body:"+response.getBody());						    
							    
							    System.out.println("body:"+body);
							 
							    //String base64 = new String(Base64.encodeBase64(bytes));
							    String idImageBack = null;
								try {
									if(body!=null){
										idImageBack = new String(Base64.encodeBase64(body), "UTF-8");	
									}
									
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    //System.out.println("base64:"+base64);
							    
							
							//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
								if(idImageBack!=null){
									target.setIdImageBackData(idImageBack);
								}
							
							
							}
							
									
							detailsDtos.add(target);
					}				
						
					}
						
					subscriberDetailsDto.setSubscriberIdDetails(detailsDtos);
					//SubscriberDetails subscriberDetailsObj=this.kycDaoService.
					//subscriberDetailsDto.setAddress(getSubscriberAddress(subscriberDetails.getSubscriberDetailsId()));
										
					subscriberDetailsDto.setAddress(addressDto);
					subscriberDetailsDto.setAddress(getSubscriberAddress(subscriberDetails.getSubscriberDetailsId()));
					subscriberDetailsDto.setSubscriberImageDetail(null);
					subscriberDetailsDto.setSubscriberDocuments(
							getSubscriberDocuments(subscriberDetails.getSubscriberDetailsId(), isOldUserDetails));
					if (isIdAndImage) {
						subscriberDetailsDto
								.setSubscriberIdDetails(getSubscriberIdDetails(subscriberDetailsDto, isOldUserDetails));
						subscriberDetailsDto
								.setSubscriberImageDetail(getSubscriberImageDetail(subscriberDetailsDto, isOldUserDetails));
					}
				}
				subscriberDetailsDtoList.add(subscriberDetailsDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subscriberDetailsDtoList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SubscriberDetailsDto> getSubscriberDetailsWithoutImage(SubscriberDto subscriberDto, Integer isOldUserDetails,
			boolean isIdAndImage) throws BusinessException {
		// TODO Auto-generated method stub
		List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
		List<SubscriberDetails> subscriberDetailsList = null;
		try {
			subscriberDetailsList = (List<SubscriberDetails>) this.kycDaoService
					.getSubscriberDetails(subscriberDto.getSubscriberId(), KycConstants.FALSE);
			for (SubscriberDetails subscriberDetails : subscriberDetailsList) {
				SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
				BeanUtils.copyProperties(subscriberDetails, subscriberDetailsDto);
				if(subscriberDetails.getIsMinor()==1)
				 {
					subscriberDetailsDto.setIsMinor("Yes");
				 }
				else{
					subscriberDetailsDto.setIsMinor("No");
				}
				if(subscriberDetails.getAmAccount()==1)
				 {
					subscriberDetailsDto.setAmAccount("Yes");
				 }
				else{
					subscriberDetailsDto.setAmAccount("No");
				}
				
				subscriberDetailsDto.setSimSerialNumber(subscriberDetails.getSimSerialNumber());
				//subscriberDetailsDto.setCountryId(subscriberDetails.getCountryId());
				if(subscriberDetails.getProxyForRegistration().equals("Yes")||subscriberDetails.getProxyForRegistration()==1){
					subscriberDetailsDto.setProxyForRegistration("Yes");
				}
				if(subscriberDetails.getProxyForRegistration().equals("No")||subscriberDetails.getProxyForRegistration()==0){
					subscriberDetailsDto.setProxyForRegistration("No");
				}
				
				

				
				AddressDto addressDto=new AddressDto();				
				BeanUtils.copyProperties(subscriberDetails.getAddress(), addressDto);
				DistrictDto districtDto=new DistrictDto();
				districtDto.setDistrictId(subscriberDetails.getAddress().getDistrictId());
				addressDto.setDistrict(districtDto);
				addressDto.setCountryId(subscriberDetails.getAddress().getCountryId());
				subscriberDetailsDto.setDateOfBirth(DateUtils.getFormatDate(subscriberDetails.getDateOfBirth(),"dd-MM-yyyy"));

				
				Integer susbscriberDetailsId=subscriberDetails.getSubscriberDetailsId();
				List<SubscriberIdDetails> details = subscriberIdDetailsDao.getSubscriberIdDetails(susbscriberDetailsId, KycConstants.FALSE);
				if(details.size()>0)
				{
					Set<SubscriberIdDetailsDto> detailsDtos = null;
					if (details != null && details.size() > 0) {
						detailsDtos = new HashSet<>();
						for (SubscriberIdDetails details2 : details) {
							SubscriberIdDetailsDto target = new SubscriberIdDetailsDto();
							BeanUtils.copyProperties(details2, target);				
							
									
							detailsDtos.add(target);
					}				
						
					}
						
					subscriberDetailsDto.setSubscriberIdDetails(detailsDtos);
					
										
					subscriberDetailsDto.setAddress(addressDto);
					subscriberDetailsDto.setAddress(getSubscriberAddress(subscriberDetails.getSubscriberDetailsId()));
					subscriberDetailsDto.setSubscriberImageDetail(null);
					subscriberDetailsDto.setSubscriberDocuments(
							getSubscriberDocuments(subscriberDetails.getSubscriberDetailsId(), isOldUserDetails));
					if (isIdAndImage) {
						subscriberDetailsDto
								.setSubscriberIdDetails(getSubscriberIdDetails(subscriberDetailsDto, isOldUserDetails));
						subscriberDetailsDto
								.setSubscriberImageDetail(getSubscriberImageDetail(subscriberDetailsDto, isOldUserDetails));
					}
				}
				subscriberDetailsDtoList.add(subscriberDetailsDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subscriberDetailsDtoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SubscriberDetailsDto> getCMSubscriberDetails(SubscriberDto subscriberDto, Integer isOldUserDetails,
			boolean isIdAndImage,String actionType, SearchSubscriberDto searchSubscriberDto) throws BusinessException {
		List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
		List<SubscriberDetails> subscriberDetailsList = null;
		
		   /*subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailObj(subscriberDto.getSubscriberId(),"APPROVED",0);
		   if(subscriberDetailsList!=null && subscriberDetailsList.size()>0)
		   for (SubscriberDetails subscriberDetails : subscriberDetailsList) {
			SubscriberWorkFlow subscriberWorkFlow=subscriberWorkFlowDao.getSubscriberWorkFlowObj(subscriberDetails.getSubscriberDetailsId());
			if(subscriberWorkFlow!=null){
				subscriberWorkFlow.setAction("APPROVED");
				subscriberWorkFlow.setSubscriberDetails(subscriberDetails);
				subscriberWorkFlowDao.saveOrUpdateEntity(subscriberWorkFlow);
			}		     
			
		   }*/
		try {
			if(ActionCodes.PENDING.toString().equals(actionType)){
				subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnObject(subscriberDto.getSubscriberId(),subscriberDto.getUpdatedOn());
			}
			else if(ActionCodes.APPROVED.toString().equals(actionType)){
				subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsApproved(subscriberDto.getSubscriberId());
			}
			else if(ActionCodes.BARRED.toString().equals(actionType)){
				subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsBarred(subscriberDto.getSubscriberId());
			}
			else if("PENDING_ALL".equals(actionType)){
				subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsPendingAll(subscriberDto.getSubscriberId());
				
				/*if(searchSubscriberDto.getCaseType()==null || searchSubscriberDto.getCaseType().equals("NEW"))
				{
					subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsPendingAllWithNew();
				}
				else if(searchSubscriberDto.getCaseType().equals("EDIT"))
				{
					subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsPendingAllWithEdit();
				}
				else if(searchSubscriberDto.getCaseType().equals("EXISTING"))
				{
					subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsPendingAllWithExisting();
				}
				else if(searchSubscriberDto.getCaseType().equals("AM"))
				{
					subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsPendingAllWithAm();
				}*/
				
			}
			else if("CLOSED_ALL".equals(actionType)){
				subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsClosedAll(subscriberDto.getSubscriberId());
			
			}
			for (SubscriberDetails subscriberDetails : subscriberDetailsList) {
				SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
				AddressDto addressDto=new AddressDto();				
				BeanUtils.copyProperties(subscriberDetails.getAddress(), addressDto);
				DistrictDto districtDto=new DistrictDto();
				districtDto.setDistrictId(subscriberDetails.getAddress().getDistrictId());
				addressDto.setDistrict(districtDto);
				addressDto.setCountryId(subscriberDetails.getAddress().getCountryId());
				subscriberDetailsDto.setDateOfBirth(DateUtils.getFormatDate(subscriberDetails.getDateOfBirth(),"dd-MM-yyyy"));
				subscriberDetailsDto.setMsisdn(subscriberDetails.getMsisdn());
				
				BeanUtils.copyProperties(subscriberDetails, subscriberDetailsDto);
				if(subscriberDetails.getIsMinor()==1)
				 {
					subscriberDetailsDto.setIsMinor("Yes");
				 }
				else{
					subscriberDetailsDto.setIsMinor("No");
				}
				if(subscriberDetails.getAmAccount()==1)
				 {
					subscriberDetailsDto.setAmAccount("Yes");
				 }
				else{
					subscriberDetailsDto.setAmAccount("No");
				}
				
				subscriberDetailsDto.setSimSerialNumber(subscriberDetails.getSimSerialNumber());
				//subscriberDetailsDto.setCountryId(subscriberDetails.getCountryId());
				if(subscriberDetails.getProxyForRegistration() !=null && subscriberDetails.getProxyForRegistration().equals("Yes")||subscriberDetails.getProxyForRegistration()==1){
					subscriberDetailsDto.setProxyForRegistration("Yes");
				}
				else{
					subscriberDetailsDto.setProxyForRegistration("No");
				}
				subscriberDetailsDto.setAddress(addressDto);

				subscriberDetailsDtoList.add(subscriberDetailsDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subscriberDetailsDtoList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SubscriberDetailsDto> getBarredSubscriberDetails(SubscriberDto subscriberDto, Integer isOldUserDetails,
			boolean isIdAndImage) throws BusinessException {
		// TODO Auto-generated method stub
		List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
		List<SubscriberDetails> subscriberDetailsList = null;
		try {
			subscriberDetailsList = (List<SubscriberDetails>) this.kycDaoService
					.getSubscriberDetails(subscriberDto.getSubscriberId(), KycConstants.FALSE);
			for (SubscriberDetails subscriberDetails : subscriberDetailsList) {
				SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
				BeanUtils.copyProperties(subscriberDetails, subscriberDetailsDto);
				if(subscriberDetails.getIsMinor()==1)
				 {
					subscriberDetailsDto.setIsMinor("Yes");
				 }
				else{
					subscriberDetailsDto.setIsMinor("No");
				}
				if(subscriberDetails.getAmAccount()==1)
				 {
					subscriberDetailsDto.setAmAccount("Yes");
				 }
				else{
					subscriberDetailsDto.setAmAccount("No");
				}
				subscriberDetailsDto.setSimSerialNumber(subscriberDetails.getSimSerialNumber());
				//subscriberDetailsDto.setCountryId(subscriberDetails.getCountryId());
				if(subscriberDetails.getProxyForRegistration().equals("Yes")||subscriberDetails.getProxyForRegistration()==1){
					subscriberDetailsDto.setProxyForRegistration("Yes");
				}
				if(subscriberDetails.getProxyForRegistration().equals("No")||subscriberDetails.getProxyForRegistration()==0){
					subscriberDetailsDto.setProxyForRegistration("No");
				}
				
				subscriberDetailsDto.setDateOfBirth(DateUtils.getFormatDate(subscriberDetails.getDateOfBirth(),"dd-MM-yyyy"));

				Map<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("subscriberDetails", subscriberDetails);
				/*List<SubscriberIdDetails> details = this.kycDaoService.findByCriteria(SubscriberIdDetails.class,
						parameterMap);*/
				List<SubscriberIdDetails> details = this.kycDaoService.getSubscriberIdDetails(subscriberDetails.getSubscriberDetailsId(), isOldUserDetails);
				Set<SubscriberIdDetailsDto> detailsDtos = null;
				if (details != null && details.size() > 0) {
					detailsDtos = new HashSet<>();
					for (SubscriberIdDetails details2 : details) {
						SubscriberIdDetailsDto target = new SubscriberIdDetailsDto();
						BeanUtils.copyProperties(details2, target);
						
						if(details2.getIdPath()!=null && details2.getIdImageFront()!=null){
							RestTemplate restTemplate = new RestTemplate();	
							
							//String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());//"http://localhost:8081/kycug/api/subscriber/getSubscriberImage1";
							String url=environment.getProperty(ConfigConstants.BASEIMAGE_URL);
							URI  targetUrl= UriComponentsBuilder.fromUriString(url)
						    		.path("/")
						    		.path(details2.getIdPath())
						    		.path("/")
						    		.path(subscriberDto.getKycTansactionId())
						    		.path("/")
						    		.path(details2.getIdImageFront())
						    		.path(".jpg")
						    	    .build()
						    	    .toUri();
						    System.out.println("targetUrl:"+targetUrl);	
						    HttpEntity<byte[]> response =null;
						    byte[] body =null;
						    try{
						    	response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
						    	  body = response.getBody();
						    }
						    catch(RestClientException e){
						    	e.printStackTrace();
						    	
						    }
						    //System.out.println("body:"+response.getBody());
						   
						    
						    System.out.println("body:"+body);
						 
						    //String base64 = new String(Base64.encodeBase64(bytes));
						    String base64 = null;
							try {
								if(body!=null){
									base64 = new String(Base64.encodeBase64(body), "UTF-8");
								}
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    System.out.println("base64:"+base64);
						    
						
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
						    if(base64!=null){
						    	target.setIdImageFrontData(base64);
						    }
						
						
						}
						if(details2.getIdPath()!=null && details2.getPhysicalFormId()!=null){
							RestTemplate restTemplate = new RestTemplate();	
							
							//String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
							String url=environment.getProperty(ConfigConstants.BASEIMAGE_URL);
						    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
						    		.path("/")
						    		.path(details2.getIdPath())
						    		.path("/")
						    		.path(subscriberDto.getKycTansactionId())
						    		.path("/")
						    		.path(details2.getPhysicalFormId())
						    		.path(".jpg")
						    	    .build()
						    	    .toUri();
						    System.out.println("targetUrl:"+targetUrl);	
						    byte[] body = null;
						    try{
						    	HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);	
						    	body = response.getBody();
						    }
						    catch(RestClientException e){
						    	e.printStackTrace();
						    }
						    
						    //System.out.println("body:"+response.getBody());
						    
						    
						    System.out.println("body:"+body);
						 
						    //String base64 = new String(Base64.encodeBase64(bytes));
						    String physicalFormBase64 = null;
							try {
								if(body!=null){
									physicalFormBase64 = new String(Base64.encodeBase64(body), "UTF-8");
								}
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						   // System.out.println("base64:"+base64);
						    
						
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
							if(physicalFormBase64!=null){
								target.setPhysicalFormId(physicalFormBase64);
							}
						
						
						}
						if(details2.getIdPath()!=null && details2.getIdImageBack()!=null){
							RestTemplate restTemplate = new RestTemplate();	
							//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
							//String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
							String url=environment.getProperty(ConfigConstants.BASEIMAGE_URL);
						    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
						    		.path("/")
						    		.path(details2.getIdPath())
						    		.path("/")
						    		.path(subscriberDto.getKycTansactionId())
						    		.path("/")
						    		.path(details2.getIdImageBack())
						    		.path(".jpg")
						    	    .build()
						    	    .toUri();
						    System.out.println("targetUrl:"+targetUrl);	
						    byte[] body =null;
						    try{
						    	HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
						    	body = response.getBody();
						    }
						    catch(RestClientException e){
						    	e.printStackTrace();
						    }
						    //System.out.println("body:"+response.getBody());						    
						    
						    System.out.println("body:"+body);
						 
						    //String base64 = new String(Base64.encodeBase64(bytes));
						    String idImageBack = null;
							try {
								if(body!=null){
									idImageBack = new String(Base64.encodeBase64(body), "UTF-8");	
								}
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    //System.out.println("base64:"+base64);
						    
						
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
							if(idImageBack!=null){
								target.setIdImageBackData(idImageBack);
							}
						
						
						}
						detailsDtos.add(target);
					}
				}				
				
				subscriberDetailsDto.setSubscriberIdDetails(detailsDtos);
				//SubscriberDetails subscriberDetailsObj=this.kycDaoService.
				subscriberDetailsDto.setAddress(getSubscriberAddress(subscriberDetails.getSubscriberDetailsId()));
				subscriberDetailsDto.setSubscriberImageDetail(null);
				subscriberDetailsDto.setSubscriberDocuments(
						getSubscriberDocuments(subscriberDetails.getSubscriberDetailsId(), isOldUserDetails));
				if (isIdAndImage) {
					subscriberDetailsDto
							.setSubscriberIdDetails(getSubscriberIdDetails(subscriberDetailsDto, isOldUserDetails));
					subscriberDetailsDto
							.setSubscriberImageDetail(getSubscriberImageDetail(subscriberDetailsDto, isOldUserDetails));
				}
				subscriberDetailsDtoList.add(subscriberDetailsDto);
			}

		} catch (KycDaoException e) {
			e.printStackTrace();
		}

		return subscriberDetailsDtoList;
	}
	
	
	public AddressDto getSubscriberAddress(Integer subscriberDetailsId)
			throws BusinessException {
			AddressDto addressDto = new AddressDto();
			

		try {
		
			//HashMap<String, Object> parameterMap = new HashMap<>();
			//parameterMap.put("subscriberDetailsId", subscriberDetailsId);
			//parameterMap.put("isOldUserDetails", KycConstants.FALSE);
			System.out.println("subscriberDetailsId::"+subscriberDetailsId);
			/*SubscriberDetails subscriberDetails=(SubscriberDetails)this.kycDaoService.getEntityByCriteria(SubscriberDetails.class,
					parameterMap);*/
			
            List list = this.kycDaoService.getSubscriberAddress(subscriberDetailsId);			
		    
		    Object addressObj = ((HashMap) list.get(0)).get("ADDRESS_ID");			
			
			Integer addressId = ((BigDecimal) addressObj).intValue();			
			
			/*System.out.println("addressId::"+addressId);
			HashMap<String, Object> map = new HashMap<>();
			map.put("addressId",addressId);*/
			
			
			
	    	Address address=addressDao.getAddress(addressId);
	    	DistrictDto districtDto=new DistrictDto();
	    	VillageDto villageDto=new VillageDto();
	    	if(address!=null){
	    		districtDto.setDistrictId(address.getDistrictId());	  
	    	}
	    	  	
	    	
	    	
	    	District district=districtDao.findByDistrictId(address.getDistrictId());
	    	if(district!=null){
	    		districtDto.setDistrictName(district.getDistrictName());
	    	}
	    	
	    	
	    	if(address.getVillageId()!=null){
		    	Village village=villageDao.findByDistrictId(address.getVillageId());
		    	if(village!=null){
		    		villageDto.setVillageName(village.getVillageName());
			    	villageDto.setVillageId(village.getVillageId());
			    	addressDto.setVillage(villageDto);
		    	}
		    	
	    	}
	    	
	    	String[] ignoreProperties = {address.getDistrictId().toString()};
			BeanUtils.copyProperties(address.getDistrictId(), addressDto,ignoreProperties );
			addressDto.setPermanentAddress(address.getPermanentAddress());
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCountryId(address.getCountryId());
	    	//BeanUtils.copyProperties(address, addressDto);
	    	
	    	
	    	addressDto.setDistrict(districtDto);
	    	
			

		}catch (KycDaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
				}
		return addressDto;
	}
	@SuppressWarnings("unchecked")
	public List<SubscriberDocumentsDto> getSubscriberDocuments(Integer subscriberDetailId, Integer isOldUserDetails)
			throws BusinessException {
		List<SubscriberDocumentsDto> subscriberDocumentsDtoList = new ArrayList<>();
		List<SubscriberDocuments> subscriberDocumentsList = null;
		try {
			Map<String, Object> parameterMap = new HashMap<>();
			SubscriberDetails subscriberDetails = new SubscriberDetails();
			subscriberDetails.setSubscriberDetailsId(subscriberDetailId);
			parameterMap.put("subscriberDetails", subscriberDetails);
			parameterMap.put("isOldIdDetails", isOldUserDetails);
			subscriberDocumentsList = (List<SubscriberDocuments>) this.kycDaoService
					.findByCriteria(SubscriberDocuments.class, parameterMap);
			if (subscriberDocumentsList == null || subscriberDocumentsList.isEmpty()) {
				return null;
			}
			for (SubscriberDocuments documents : subscriberDocumentsList) {
				SubscriberDocumentsDto temp = new SubscriberDocumentsDto();
				BeanUtils.copyProperties(documents, temp);
				subscriberDocumentsDtoList.add(temp);
			}
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return subscriberDocumentsDtoList;
	}
	
	@SuppressWarnings("unchecked")
	public Set<SubscriberIdDetailsDto> getSubscriberIdDetails(SubscriberDetailsDto subscriberDetailsDto,
			Integer isOldData) throws BusinessException {
		// TODO Auto-generated method stub
		Set<SubscriberIdDetailsDto> subscriberIdDetailsDtoList = new HashSet<SubscriberIdDetailsDto>();
		List<SubscriberIdDetails> subscriberIdDetailsList = null;
		try {
			subscriberIdDetailsList = (List<SubscriberIdDetails>) this.kycDaoService
					.getSubscriberIdDetails(subscriberDetailsDto.getSubscriberDetailsId(), isOldData);
			if (subscriberIdDetailsList != null)
				for (SubscriberIdDetails subscriberIdDetails : subscriberIdDetailsList) {
					SubscriberIdDetailsDto subscriberIdDetailsDto = new SubscriberIdDetailsDto();
					BeanUtils.copyProperties(subscriberIdDetails, subscriberIdDetailsDto);
					subscriberIdDetailsDtoList.add(subscriberIdDetailsDto);
				}
		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberIdDetailsDtoList;
	}
	
	@SuppressWarnings("unchecked")
	public Set<SubscriberImageDetailDto> getSubscriberImageDetail(SubscriberDetailsDto subscriberDetailsDto,
			Integer isOldData) throws BusinessException {
		// TODO Auto-generated method stub
		Set<SubscriberImageDetailDto> subscriberImageDetailDtoList = new HashSet<SubscriberImageDetailDto>();
		List<SubscriberImageDetail> subscriberImageDetailList = null;
		try {
			subscriberImageDetailList = (List<SubscriberImageDetail>) this.kycDaoService
					.getSubscriberImageDetail(subscriberDetailsDto.getSubscriberDetailsId(), isOldData);

			if (subscriberImageDetailList != null)
				for (SubscriberImageDetail subscriberImageDetail : subscriberImageDetailList) {
					SubscriberImageDetailDto subscriberImageDetailDto = new SubscriberImageDetailDto();
					BeanUtils.copyProperties(subscriberImageDetail, subscriberImageDetailDto);
					subscriberImageDetailDtoList.add(subscriberImageDetailDto);
				}
		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberImageDetailDtoList;
	}
	@Override
	public boolean isSubscriberExist(String msisdn, String simSerialNo) {
		SubscriberDto dto = null;
		try {
			dto = getSubscriberByMsisdnAndSimNo(msisdn, simSerialNo, KycConstants.FALSE);
			if (dto != null && dto.getSubscriberId() != null) {
				return true;
			}
		} catch (BusinessException e) {

		}
		return false;
	}

	public SubscriberDto getSubscriberByMsisdnAndSimNo(String msisdn, String simSerialNo, Integer isOldData)
			throws BusinessException {
		// TODO Auto-generated method stub
		SubscriberDto subscriberDto = new SubscriberDto();

		try {
			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("msisdn", msisdn);
			//parameterMap.put("simSerialNumber", simSerialNo);
			/*if (isOldData != null)
				parameterMap.put("isOldSubscriber", isOldData);*/
			@SuppressWarnings("unchecked")
			Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByCriteria(Subscriber.class,
					parameterMap);
			if (subscriber == null) {
				return null;
			}
			BeanUtils.copyProperties(subscriber, subscriberDto);
			List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
					KycConstants.FALSE, true);
			subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
			subscriberDto.setOperactionOnSubscriber(null);
			subscriberDto.setActionOnSubscriber(null);
			//subscriberDto.setSubscriberWorkFlow(null);
			subscriberDto.setSubscriberId(subscriber.getSubscriberId());
		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// throw new
			// BusinessException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionCode(),SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionDescription());
		}
		return subscriberDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public SubscriberDto getSubscriberBySubscriberId(Long subscriberId, Integer isOldData) throws BusinessException {
		// TODO Auto-generated method stub
		SubscriberDto subscriberDto = new SubscriberDto();

		try {
			// Subscriber subscriber =
			// this.kycDaoService.getSubscriberBySubscriberId(subscriberId);

			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberId);
			

			Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByCriteria(Subscriber.class,
					parameterMap);
			BeanUtils.copyProperties(subscriber, subscriberDto);

			List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
					KycConstants.FALSE, true);

			subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
			subscriberDto.setOperactionOnSubscriber(null);
			subscriberDto.setActionOnSubscriber(null);
			//subscriberDto.setSubscriberWorkFlow(null);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subscriberDto;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public SubscriberWorkFlowDto actionOnSubscriber(SubscriberWorkFlowDto subscriberWorkFlowDto)
			throws BusinessException, IntegrationServiceException, IOException {

		SubscriberWorkFlow subscriberWorkFlow = new SubscriberWorkFlow();
		String roleName = subscriberWorkFlowDto.getRoleName();

		if (roleName != null) {
			new SubscriberException(SubscriberExceptionCodes.ROLE_NAME_NOT_PASS);
		}
		try {
			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberWorkFlowDto.getSubscriberId());

			Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByCriteriaQuery(Subscriber.class,
					parameterMap);
			
			if (subscriber == null) {
				new SubscriberException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS);

			}
			subscriberWorkFlowDto.setMsisdn(subscriber.getMsisdn());

			List<SubscriberDetails> subscriberDetailsList = subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnObjectForUpdate(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
			
			if (subscriberDetailsList!=null &&  subscriberDetailsList.get(0).getSubscriberWorkFlow() == null) {
				subscriberWorkFlow.setSubscriberDetails(subscriberDetailsList.get(0));
			} else
				subscriberWorkFlow = subscriberDetailsList.get(0).getSubscriberWorkFlow();

			subscriberWorkFlow.setAction(subscriberWorkFlowDto.getAction());
			subscriberWorkFlow.setRoleName(roleName);
			subscriberWorkFlow.setUserId(subscriberWorkFlowDto.getUserId());
			subscriberWorkFlow.setStatusReason(subscriberWorkFlowDto.getStatusReason());
			subscriberWorkFlow.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			
			
			SubscriberDetails subscriberDetails = subscriberDetailsList.get(0);
			subscriberDetails.setSubscriberWorkFlow(subscriberWorkFlow);
			List<SubscriberDetails> list = new ArrayList<SubscriberDetails>();
			list.add(subscriberDetails);
			subscriber.setSubscriberDetails(list);
			
			SubscriberWorkFlowHistory subscriberWorkFlowHistory = new SubscriberWorkFlowHistory();
			subscriberWorkFlowHistory.setRoleName(roleName);
			subscriberWorkFlowHistory.setUserId(subscriberWorkFlowDto.getUserId());

			subscriberWorkFlowHistory
			.setSubscriberWorkFlowId(subscriberDetailsList.get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
			subscriberWorkFlowHistory.setAction(subscriberWorkFlowDto.getAction());

			if (ActionCodes.APPROVED.toString().equalsIgnoreCase(subscriberWorkFlowDto.getAction())) {
				/*this.taskManagementService.addTask(TaskEnum.SINGLE_VIEW_VALIDATION.getTaskCode(),
							subscriber.getSubscriberId(), subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
				*/
				if(subscriberDetailsList.get(0).getBulkSubscriberFlag()==1)
				{
					this.taskManagementService.addTask(TaskEnum.EMA_UNBARING_FOR_BULK.getTaskCode(), subscriber.getSubscriberId(),
							subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
				}
				else
				{
					//if (subscriber.getIsByPassed() == KycConstants.TRUE) {
					this.taskManagementService.addTask(TaskEnum.EMA_UNBARING.getTaskCode(), subscriber.getSubscriberId(),
								subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
					//}
				}
				//subscriber.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				System.out.println("updating worf flow");
				
				/*subscriberDetailsList.get(0).setWorkFlowStatus("PENDING");
				subscriberDetailsList.get(0).setAction("APPROVED");
				subscriberDetailsList.get(0).setWorkFlowReason(subscriberWorkFlowDto.getStatusReason());*/
				
				/*subscriberWorkFlow.setSubscriberDetails(subscriberDetails);
				subscriberDetails.setAction("APPROVED");
				list.add(subscriberDetails);
				subscriber.setSubscriberDetails(list);*/
				
				/*SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subscriberDetailsList.get(0).getSubscriberDetailsId());
				workflow.setAction("APPROVED");
				SubscriberDetails subDetails=subscriberDetailsList.get(0);
				subDetails.setAction("APPROVED");
				workflow.setSubscriberDetails(subDetails);
				subscriberWorkFlowDao.saveOrUpdateEntity(workflow);*/
				
				/*SubscriberDetails details=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnObj(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
				SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(details.getSubscriberDetailsId());
				workflow.setAction("APPROVED");
				details.setAction("APPROVED");
				details.setSubscriberWorkFlow(workflow);
				workflow.setSubscriberDetails(details);
				subscriberDetailsDao.saveOrUpdate(details);*/
				
				SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subscriberDetailsList.get(0).getSubscriberDetailsId());
				SubscriberDetails details=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnObjForUpdate(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
				details.setAction("APPROVED");
				details.setFinalStatus("IN_PROCESS");
				if(workflow==null){
					workflow=new SubscriberWorkFlow();
					workflow.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
					workflow.setRoleName("CM");
					workflow.setStatusReason("PENDING FOR CM APPROVAL");					
					workflow.setUserId(subscriberWorkFlowDto.getUserId());					
					workflow.setAction("APPROVED");
					workflow.setSubscriberDetails(details);
					details.setSubscriberWorkFlow(workflow);
					subscriberWorkFlowDao.saveOrUpdate(workflow);
				}
				else{
					workflow.setAction("APPROVED");
					workflow.setSubscriberDetails(details);
					details.setSubscriberWorkFlow(workflow);
					subscriberWorkFlowDao.saveOrUpdate(workflow);
				}
				
				
				//subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
				subscriberWorkFlowHistory = (SubscriberWorkFlowHistory) kycDaoService
						.saveOrUpdateEntity(subscriberWorkFlowHistory);
				
				Assignment assignment = cMDao.getAssignmentDetail(subscriberWorkFlowDto.getUserId().toString());
				if(assignment!=null){
					assignment.setCount(0);
					cMDao.saveObject(assignment);
				}
				
			} else if (ActionCodes.BARRED.toString().equalsIgnoreCase(subscriberWorkFlowDto.getAction())) {
				
				//sms on barred cases				
				NotificationDto notificationDto1 = new NotificationDto();
				Templates templates11 =templatesDao.getTemplateObj(SMS_BARRED_BY_CM, "en", "sms");
				
				String templateContent1=templates11.getTemplateContent();
				String smsOutput1 = MessageFormat.format(templateContent1, subscriber.getMsisdn());
				notificationDto1.setTemplateContent(smsOutput1);
				
				
				GKYCStatusRequestDto pGKYCStatusRequestDto1 = new GKYCStatusRequestDto();
				pGKYCStatusRequestDto1.settMSISDN(subscriber.getMsisdn());
				GKYCStatusResponseDto gKYCStatusResponseDto1 = gKYCStatusDao.getGKYCStatus(pGKYCStatusRequestDto1);					
				if(gKYCStatusResponseDto1!=null && gKYCStatusResponseDto1.getRetailerMSISDN()!= null){									
					notificationDto1.setMsisdn(gKYCStatusResponseDto1.getRetailerMSISDN());
					this.integrationService.notify(notificationDto1);
				}
				
				SubscriberWorkFlow workflow=subscriberWorkFlowDao.getSubscriberWorkFlowData(subscriberDetailsList.get(0).getSubscriberDetailsId());
				SubscriberDetails details=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnObjForUpdate(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
				details.setAction("BARRED");
				details.setFinalStatus(ActionCodes.BARRED.toString());
				details.setFinalStatusReason(subscriberWorkFlowDto.getStatusReason());
				details.setBarringSource("BARRED_BY_CM");
				if(workflow!=null){
					workflow.setAction("BARRED");
					workflow.setSubscriberDetails(details);
					details.setSubscriberWorkFlow(workflow);
					subscriberWorkFlowDao.saveOrUpdate(workflow);
				}
				else{
					workflow=new SubscriberWorkFlow();
					workflow.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
					workflow.setRoleName("CM");
					workflow.setStatusReason("BARRED BY CM");
					workflow.setUserId(subscriberWorkFlowDto.getUserId());
					workflow.setAction("BARRED");
					workflow.setSubscriberDetails(details);
					details.setSubscriberWorkFlow(workflow);
					subscriberWorkFlowDao.saveOrUpdate(workflow);
				}				
				
				subscriberWorkFlowHistory = (SubscriberWorkFlowHistory) kycDaoService
						.saveOrUpdateEntity(subscriberWorkFlowHistory);
				
				Assignment assignment = cMDao.getAssignmentDetail(subscriberWorkFlowDto.getUserId().toString());
				if(assignment!=null){
					assignment.setCount(0);
					cMDao.saveObject(assignment);
				}
				
				System.out.println("BARRED");
			} else {
				System.out.println("Status : " + subscriberWorkFlowDto.getAction());
				subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
				subscriberWorkFlowHistory.setSubscriberWorkFlowId(subscriberDetailsList.get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
				subscriberWorkFlowHistory = (SubscriberWorkFlowHistory) kycDaoService
						.saveOrUpdateEntity(subscriberWorkFlowHistory);
			}

		} catch (KycDaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

		return subscriberWorkFlowDto;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public SubscriberWorkFlowDto autoAssignSubscriber(SubscriberWorkFlowDto subscriberWorkFlowDto,List<SubscriberDetails> subscriberDetailsList)
			throws BusinessException {

		SubscriberWorkFlow subscriberWorkFlow = new SubscriberWorkFlow();
		String roleName = subscriberWorkFlowDto.getRoleName();

		if (roleName != null) {
			new SubscriberException(SubscriberExceptionCodes.ROLE_NAME_NOT_PASS);
		}
		try {
			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberWorkFlowDto.getSubscriberId());

			Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByCriteriaQuery(Subscriber.class,
					parameterMap);
			
			subscriberWorkFlowDto.setMsisdn(subscriber.getMsisdn());

			if (subscriberDetailsList.get(0).getSubscriberWorkFlow() == null) {
				subscriberWorkFlow.setSubscriberDetails(subscriberDetailsList.get(0));
			} else
				subscriberWorkFlow = subscriberDetailsList.get(0).getSubscriberWorkFlow();

			subscriberWorkFlow.setAction(subscriberWorkFlowDto.getAction());
			subscriberWorkFlow.setRoleName(roleName);
			subscriberWorkFlow.setUserId(subscriberWorkFlowDto.getUserId());
			subscriberWorkFlow.setStatusReason(subscriberWorkFlowDto.getStatusReason());
			subscriberWorkFlow.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			SubscriberDetails subscriberDetails = subscriberDetailsList.get(0);
			subscriberDetails.setSubscriberWorkFlow(subscriberWorkFlow);
			List<SubscriberDetails> list = new ArrayList<SubscriberDetails>();
			list.add(subscriberDetails);
			subscriber.setSubscriberDetails(list);
			
			SubscriberWorkFlowHistory subscriberWorkFlowHistory = new SubscriberWorkFlowHistory();
			subscriberWorkFlowHistory.setRoleName(roleName);
			subscriberWorkFlowHistory.setUserId(subscriberWorkFlowDto.getUserId());
			subscriberWorkFlowHistory
			.setSubscriberWorkFlowId(subscriberDetailsList.get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
			subscriberWorkFlowHistory.setAction(subscriberWorkFlowDto.getAction());

			System.out.println("Status : " + subscriberWorkFlowDto.getAction());
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			subscriberWorkFlowHistory.setSubscriberWorkFlowId(subscriberDetailsList.get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
			subscriberWorkFlowHistory = (SubscriberWorkFlowHistory) kycDaoService
					.saveOrUpdateEntity(subscriberWorkFlowHistory);

		} catch (KycDaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

		return subscriberWorkFlowDto;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void updateWorkFlow(Subscriber subscriber, String action) {
		Map<String, Object> parameterMap =  new HashMap<>();
		parameterMap.put("subscriber", subscriber);
		SubscriberWorkFlow workFlow;
		try {
			//workFlow = (SubscriberWorkFlow) this.kycDaoService.getEntityByCriteria(SubscriberWorkFlow.class, parameterMap);
			workFlow=subscriberWorkFlowDao.getSubscriberWorkFlowObj(subscriber.getSubscriberDetails().get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
			workFlow.setAction(action);
			subscriberWorkFlowDao.saveOrUpdate(workFlow);
			//this.kycDaoService.saveOrUpdateEntity(workFlow);
			
			//update workflow
			SubscriberWorkFlowHistory subscriberWorkFlowHistory = new SubscriberWorkFlowHistory();
			SubscriberWorkFlow workFlowObj=subscriberWorkFlowDao.getSubscriberWorkFlowObj(subscriber.getSubscriberDetails().get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
			subscriberWorkFlowHistory.setRoleName(workFlowObj.getRoleName());
			subscriberWorkFlowHistory.setUserId(workFlowObj.getUserId());
			//subscriberWorkFlowHistory.setSubscriberId(subscriber.getSubscriberId());
			//subscriberWorkFlowHistory.setSubscriberDetailsId(new Long(subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId()));
			/*subscriberWorkFlowHistory
					.setSubscriberWorkFlowId(subscriber.getSubscriberWorkFlow().getSubscriberWorkFlowId());*/
			subscriberWorkFlowHistory
				.setSubscriberWorkFlowId(subscriber.getSubscriberDetails().get(0).getSubscriberWorkFlow().getSubscriberWorkFlowId());
			subscriberWorkFlowHistory.setAction(action);
			//track workflow hostory
			try {
				//this.kycDaoService.saveOrUpdateEntity(subscriberWorkFlowHistory);
				subscriberWorkFlowHistoryDao.saveOrUpdate(subscriberWorkFlowHistory);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void subscriberToUser(SubscriberDto subscriber) throws BusinessException, IOException {
		// create user after approved
		String roles = subscriber.getSubscriberRoles();
		if (roles != null) {
			UsersDto bean = new UsersDto();
			bean.setUserName(subscriber.getMsisdn());
			SubscriberDetailsDto details = subscriber.getSubscriberDetails().get(0);
			UserDetailsDto detailsDto = new UserDetailsDto();
			detailsDto.setMsisdn(subscriber.getMsisdn());
			detailsDto.setFirstName(details.getFirstName());
			detailsDto.setLastName(details.getLastName());
			detailsDto.setEmail(details.getEmail());

			String[] rolesArray = roles.split(",");

			List<RoleDto> dtos = new ArrayList<>();
			for (String string : rolesArray) {
				RoleDto dto = null;
				try {
					dto = this.helperDataManagementService.getRoleByName(string);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dtos.add(dto);
			}
			LocationDto dto = this.helperDataManagementService.getLocation(subscriber.getSubscriberId());
			bean.setLocation(dto);
			bean.setUserDetail(detailsDto);
			bean.setRoles(dtos);
			bean.setSubscriberId(subscriber.getSubscriberId());
			log.debug("Bean to create user from subscriber : ");
			try {
				this.userManagementService.provisionUser(bean);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public SubscriberDto getSubscriberBySubscriberId(int subscriberId, Integer isOldData) throws BusinessException {
		// TODO Auto-generated method stub
		SubscriberDto subscriberDto = new SubscriberDto();

		try {
			// Subscriber subscriber =
			// this.kyctnzDaoService.getSubscriberBySubscriberId(subscriberId);

			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberId);
			/*if (isOldData != null)
				parameterMap.put("isOldSubscriber", isOldData);*/

			Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByCriteria(Subscriber.class,
					parameterMap);
			BeanUtils.copyProperties(subscriber, subscriberDto);

			List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
					KycConstants.FALSE, true);

			subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
			subscriberDto.setOperactionOnSubscriber(null);
			subscriberDto.setActionOnSubscriber(null);
			//subscriberDto.setSubscriberWorkFlow(null);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subscriberDto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BaseDto updateSubscriber(SubscriberDto subscriberDto,String imageEdited,String ipAddress,String handsetImei,String placeOfBirth) throws BusinessException {
		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		try {
			Subscriber subscriber = null;
			// get subscriber data by id
			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberDto.getSubscriberId());			
			subscriber=subscriberDao.getSubscriberBySubscriberId(subscriberDto.getSubscriberId());
			
			// get subscriber from data with subscriber id
			List<SubscriberDetails> subscriberDetailsList = subscriberDetailsDao.getSubscriberDetails(subscriber.getSubscriberId(), KycConstants.FALSE);
			
			if(subscriberDetailsList.get(0).getFinalStatus().equals(KycConstants.FINAL_STATUS)){
				SubscriberDto baseDto=(SubscriberDto) updateSubscriberObj(subscriberDto,imageEdited,ipAddress,handsetImei,placeOfBirth);
				return baseDto;
			}

			else{
			Boolean idFrontImageSaveStatus = false;	
			Boolean idBackImageSaveStatus = false;
			//Boolean subscriberImageIdSaveStatus = false;
			Boolean physicalFormIdSaveStatus = false;
			
			Boolean saveresult = false;
			String trnsId=subscriberDto.getKycTansactionId();
			String timestr =null;
			String destfilepath =null;
			if(trnsId==null){
				timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		       destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ subscriber.getKycTansactionId() + "/";
			}
			else{
				timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		        destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ subscriberDto.getKycTansactionId() + "/";
			}
			
			
			
			
			SubscriberDetails editSubscriberDetails = new SubscriberDetails();
			SubscriberDetails oldSubscriberDetails = new SubscriberDetails();
			List<SubscriberIdDetails> subscriberIdDetails = new ArrayList<SubscriberIdDetails>();
			List<SubscriberImageDetail> subscriberImageDetail = new ArrayList<SubscriberImageDetail>();
			Address address  = new Address();
			//Region region = new Region();

			
			
			
			if (subscriber.getSubscriberId() != null) {
				subscriber.setUpdatedOn(timestamp);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null)
					subscriber.setUpdatedBy(auth.getName());
			} else {
				subscriber.setDeleteFlag(KycConstants.FALSE);
				subscriber.setStatusFlag(KycConstants.TRUE);
				subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null)
					subscriber.setCreatedBy(auth.getName());
			}
			//subscriber.setIsOldSubscriber(subscriberDto.getIsOldSubscriber());
				

			
			// get subscriber 1st elemnet from subcriber DTO
			SubscriberDetailsDto subscriberDetailsDto = subscriberDto.getSubscriberDetails().get(0);

			if (subscriberDetailsList != null && subscriberDetailsList.size() > 0) {
				oldSubscriberDetails = subscriberDetailsList.get(0);				
				BeanUtils.copyProperties(oldSubscriberDetails, editSubscriberDetails);
				editSubscriberDetails.setSubscriberDetailsId(null);
				//editSubscriberDetails.setSubscriberImageDetail(null);				
				subscriberImageDetail = this.kycDaoService
						.getSubscriberImageDetail(editSubscriberDetails.getSubscriberDetailsId(), KycConstants.FALSE);
				
				subscriberIdDetails = this.kycDaoService
							.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				
				editSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
				editSubscriberDetails.setIsLatest(KycConstants.TRUE);

				oldSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);
				oldSubscriberDetails.setStatusFlag(KycConstants.FALSE);
				
			}
			
			
			
			Set<SubscriberIdDetails> editsubscriberIdDetailsObj = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberDetailsDto.getSubscriberIdDetails()) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageFrontDate = subscriberIdDetailsDto.getIdImageFrontData();
					String idImageBackDate = subscriberIdDetailsDto.getIdImageBackData();
					String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					//String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					//String subscriberImage ="subscriberImage";
					String physicalFormId=subscriberIdDetailsDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageFrontData(),
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}
					
					if (idImageBackDate != null && idImageBackDate.length() > 0) {
						idBackImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageBackData(),
								destfilepath, idImageBack + ".jpg");
						subscriberIdDetail.setIdImageBack(idImageBack);
					}
					
					/*if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getSubscriberImageId(),
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}*/
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getPhysicalFormId(),
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !idBackImageSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(editSubscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					subscriberIdDetail.setIsOldIdDetails(KycConstants.FALSE);
					subscriberIdDetail.setIdNo(null);

					// add in subscriberIdDetails arraylist
					editsubscriberIdDetailsObj.add(subscriberIdDetail);
				}
			
			
			
			
			
			
			
			
			
			List list = this.kycDaoService.getSubscriberAddress(subscriberDetailsDto.getSubscriberDetailsId());
			
		   
		    Object AddressId = ((HashMap) list.get(0)).get("ADDRESS_ID");		    
			
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			Integer districtId = addressDto.getDistrict().getDistrictId();
			Integer addressId = ((BigDecimal) AddressId).intValue();
			BeanUtils.copyProperties(addressDto, address);			
			
			address.setDistrictId(districtId);
			address.setAddressId(addressId);	
			if (addressDto.getVillage() != null)
			{
				address.setVillageId(addressDto.getVillage().getVillageId());
			}
			address.setCountryId(addressDto.getCountryId());
				
			
			
			editSubscriberDetails.setAddress(address);			
			editSubscriberDetails.setPostalAddress(addressDto.getPostalCode());
			editSubscriberDetails.setFirstName(subscriberDetailsDto.getFirstName());
			editSubscriberDetails.setMiddleName(subscriberDetailsDto.getMiddleName());
			editSubscriberDetails.setLastName(subscriberDetailsDto.getLastName());			
			editSubscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(),"dd-MM-yyyy"));			
			editSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
			editSubscriberDetails.setStatusFlag(KycConstants.FALSE);
			editSubscriberDetails.setSubscriberDetailsId(null);
			editSubscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			editSubscriberDetails.setSubscriberIdDetails(editsubscriberIdDetailsObj);
			editSubscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());
			editSubscriberDetails.setUpdatedOn(timestamp);
			editSubscriberDetails.setFinalStatusReason(null);
			editSubscriberDetails.setActivationTime(null);
			//editSubscriberDetails.setAmAccount(null);
			editSubscriberDetails.setEmaActivationTime(null);
			editSubscriberDetails.setPartialActivationTime(null);
			editSubscriberDetails.setSvActivationTime(null);
			editSubscriberDetails.setWorkFlowReason(null);
			editSubscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			editSubscriberDetails.setBulkSubscriberFlag(0);
			editSubscriberDetails.setIpAddress(ipAddress);
			editSubscriberDetails.setIpAddress(handsetImei);
			editSubscriberDetails.setPlaceOfBirth(placeOfBirth);
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				editSubscriberDetails.setUpdatedBy(auth.getName());
			}
			
			
			if(imageEdited!=null && imageEdited.equals("Yes") || imageEdited.equals("YES")){
				editSubscriberDetails.setIsImageEdited("YES");
			}
			
			else if(imageEdited==null || imageEdited.equals("No") || imageEdited.equals("NO")){
				editSubscriberDetails.setIsImageEdited("NO");
			}
			
			if(subscriberDetailsDto.getAmAccount().equals("Yes") ||subscriberDetailsDto.getAmAccount()=="1"){
				editSubscriberDetails.setAmAccount(1);
			}
			else if(subscriberDetailsDto.getAmAccount().equals("No") ||subscriberDetailsDto.getAmAccount()=="0"){
				editSubscriberDetails.setAmAccount(0);
			}
			if(subscriberDetailsDto.getIsMinor().equals("Yes")||subscriberDetailsDto.getIsMinor()=="1"){
				editSubscriberDetails.setIsMinor(1);
			}
			if(subscriberDetailsDto.getIsMinor().equals("No")||subscriberDetailsDto.getIsMinor()=="0"){
				editSubscriberDetails.setIsMinor(0);
			}
			
			if(subscriberDetailsDto.getOccupation()!=null){
				editSubscriberDetails.setOccupation(subscriberDetailsDto.getOccupation());
			}
			
			editSubscriberDetails.setNationality(subscriberDetailsDto.getNationality());
			if(subscriberDetailsDto.getCaseType()!=null){
				editSubscriberDetails.setCaseType(subscriberDetailsDto.getCaseType());	
			}
			
			if(subscriberDetailsDto.getSubscriberType()!=null){
				editSubscriberDetails.setSubscriberType(subscriberDetailsDto.getSubscriberType());	
			}
			
			if(subscriberDetailsDto.getProxyForRegistration().equals("Yes")||subscriberDetailsDto.getProxyForRegistration()=="1"){
				editSubscriberDetails.setProxyForRegistration(1);
			}
			else{
				editSubscriberDetails.setProxyForRegistration(0);
			}

			Set<SubscriberImageDetail> subscriberImageDetailTemp = new HashSet<SubscriberImageDetail>();

			for (SubscriberImageDetail subscriberImageDetail2 : subscriberImageDetail) {
				subscriberImageDetail2.setSubscriberDetails(editSubscriberDetails);
				subscriberImageDetailTemp.add(subscriberImageDetail2);
			}

			//editSubscriberDetails.setSubscriberImageDetail(subscriberImageDetailTemp);
			subscriberDetailsList.add(editSubscriberDetails);
			subscriber.setSubscriberDetails(subscriberDetailsList);
			subscriber.setUpdatedOn(timestamp);
			
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			
			
			this.taskManagementService.addTask(TaskEnum.SIM_VALIDATION.getTaskCode(),
					subscriber.getSubscriberId(), subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId().toString());
			
			
		
			
			/*List<SubscriberDetails> detailsObj=subscriber.getSubscriberDetails();
			for (SubscriberDetails subscriberDetails : detailsObj) {
				SubscriberIdDetails subscriberIdDetailsObj=subscriberIdDetailsDao.getSubscriberIdDetailsObj(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				if(subscriberDetails.getSubscriberDetailsId()!=subscriberIdDetailsObj.getSubscriberDetails().getSubscriberDetailsId())
				{
					subscriberIdDetailsObj.getSubscriberDetails().setSubscriberDetailsId(subscriberDetails.getSubscriberDetailsId());
					subscriberIdDetailsObj.setSubscriberDetails(subscriberDetails);
					subscriberIdDetailsDao.saveOrUpdate(subscriberIdDetailsObj);
				}
			}*/
			
				List<SubscriberIdDetails> subscriberIdDetailsObj=subscriberIdDetailsDao.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				for (SubscriberIdDetails subscriberIdDetailsList : subscriberIdDetailsObj) {
					subscriberIdDetailsList.setIsOldIdDetails(KycConstants.TRUE);
					subscriberIdDetailsDao.saveOrUpdate(subscriberIdDetailsList);
				}
				
			
			}

		} catch (KycDaoException e) {
			
			e.printStackTrace();
		}
		return subscriberDto;
	
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDto updateSubscriberObj(SubscriberDto subscriberDto,String imageEdited, String ipAddress, String handsetImei,String placeOfBirth) throws BusinessException {
		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		try {
			Subscriber subscriber = null;
			// get subscriber data by id
			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberDto.getSubscriberId());			
			//subscriber=subscriberDao.getSubscriberBySubscriberId(subscriberDto.getSubscriberId());
			subscriber=subscriberDao.getSubscriberBySubscriberId(subscriberDto.getSubscriberId());
			
			// get subscriber from data with subscriber id
			//List<SubscriberDetails> subscriberDetailsList = this.kycDaoService.getSubscriberDetails(subscriber.getSubscriberId(), KycConstants.FALSE);
			List<SubscriberDetails> subscriberDetailsList =subscriberDetailsDao.getSubscriberDetails(subscriber.getSubscriberId(), KycConstants.FALSE);
			
			Boolean idFrontImageSaveStatus = false;
			Boolean idBackImageSaveStatus = false;
			//Boolean subscriberImageIdSaveStatus = false;
			Boolean physicalFormIdSaveStatus = false;
			
			Boolean saveresult = false;
			String trnsId=subscriberDto.getKycTansactionId();
			String timestr =null;
			String destfilepath =null;
			if(trnsId==null){
				timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		       destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ subscriber.getKycTansactionId() + "/";
			}
			else{
				timestr = environment.getProperty(ConfigConstants.HOST_NAME, null, Locale.getDefault()) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		        destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ subscriberDto.getKycTansactionId() + "/";
			}
			
			
			
			
			SubscriberDetails editSubscriberDetails = new SubscriberDetails();
			SubscriberDetails oldSubscriberDetails = new SubscriberDetails();
			List<SubscriberIdDetails> subscriberIdDetails = new ArrayList<SubscriberIdDetails>();
			//List<SubscriberImageDetail> subscriberImageDetail = new ArrayList<SubscriberImageDetail>();
			Address address  = new Address();
			//Region region = new Region();

			
			
			
			if (subscriber.getSubscriberId() != null) {
				subscriber.setUpdatedOn(timestamp);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null)
					subscriber.setUpdatedBy(auth.getName());
			} else {
				subscriber.setDeleteFlag(KycConstants.FALSE);
				subscriber.setStatusFlag(KycConstants.TRUE);
				subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null)
					subscriber.setCreatedBy(auth.getName());
			}
			//subscriber.setIsOldSubscriber(subscriberDto.getIsOldSubscriber());
				

			
			// get subscriber 1st elemnet from subcriber DTO
			SubscriberDetailsDto subscriberDetailsDto = subscriberDto.getSubscriberDetails().get(0);

			if (subscriberDetailsList != null && subscriberDetailsList.size() > 0) {
				oldSubscriberDetails = subscriberDetailsList.get(0);				
				BeanUtils.copyProperties(oldSubscriberDetails, editSubscriberDetails);
				editSubscriberDetails.setSubscriberDetailsId(null);
				editSubscriberDetails.setSubscriberIdDetails(null);
				
				
				/*subscriberImageDetail = this.kycDaoService
						.getSubscriberImageDetail(editSubscriberDetails.getSubscriberDetailsId(), KycConstants.FALSE); */
				
				/*subscriberIdDetails = this.kycDaoService
							.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);*/
				
				subscriberIdDetails = subscriberIdDetailsDao.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				
				//editSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);
				if(imageEdited!=null && imageEdited.equals("Yes") || imageEdited.equals("YES")){
					oldSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
				}
				else if(imageEdited==null || imageEdited.equals("No") || imageEdited.equals("NO")){
					oldSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);
				}

				//oldSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
				editSubscriberDetails.setIsLatest(KycConstants.TRUE);
				oldSubscriberDetails.setStatusFlag(KycConstants.FALSE);
				
			}
			
			
			
			Set<SubscriberIdDetails> editsubscriberIdDetailsObj = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberDetailsDto.getSubscriberIdDetails()) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageFrontDate = subscriberIdDetailsDto.getIdImageFrontData();
					String idImageBackDate = subscriberIdDetailsDto.getIdImageBackData();
					String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					//String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					//String subscriberImage ="subscriberImage";
					String physicalFormId=subscriberIdDetailsDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageFrontData(),
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}
					
					if (idImageBackDate != null && idImageBackDate.length() > 0) {
						idBackImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageBackData(),
								destfilepath, idImageBack + ".jpg");
						subscriberIdDetail.setIdImageBack(idImageBack);
					}
					
					/*if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getSubscriberImageId(),
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}*/
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getPhysicalFormId(),
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !idBackImageSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(editSubscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					if(imageEdited!=null && imageEdited.equals("Yes") || imageEdited.equals("YES")){
						subscriberIdDetail.setIsOldIdDetails(KycConstants.TRUE);
					}
					else if(imageEdited==null || imageEdited.equals("No") || imageEdited.equals("NO")){
						subscriberIdDetail.setIsOldIdDetails(KycConstants.FALSE);
					}
					//subscriberIdDetail.setIsOldIdDetails(KycConstants.TRUE);
					subscriberIdDetail.setIdNo(null);

					// add in subscriberIdDetails arraylist
					editsubscriberIdDetailsObj.add(subscriberIdDetail);
				}
			
			
			
			List list = this.kycDaoService.getSubscriberAddress(subscriberDetailsDto.getSubscriberDetailsId());	
			Object addressObj=null;
			if(list!=null && list.size()>0){
				addressObj = ((HashMap) list.get(0)).get("ADDRESS_ID");	
			}
		   
			else{
				List listObj = this.kycDaoService.getSubscriberAddressNew(subscriberDetailsDto.getSubscriberDetailsId());	
				addressObj = ((HashMap) listObj.get(0)).get("ADDRESS_ID");
			}
		   		    
			
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			Integer districtId = addressDto.getDistrict().getDistrictId();
			Integer addressId = ((BigDecimal) addressObj).intValue();
			BeanUtils.copyProperties(addressDto, address);	
			if (addressDto.getVillage() != null)
			{
				address.setVillageId(addressDto.getVillage().getVillageId());
			}
			address.setCountryId(addressDto.getCountryId());
			address.setDistrictId(districtId);
			address.setAddressId(addressId);	
			
			
			editSubscriberDetails.setAddress(address);			
			editSubscriberDetails.setPostalAddress(addressDto.getPostalCode());
			editSubscriberDetails.setFirstName(subscriberDetailsDto.getFirstName());
			editSubscriberDetails.setMiddleName(subscriberDetailsDto.getMiddleName());
			editSubscriberDetails.setLastName(subscriberDetailsDto.getLastName());			
			editSubscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(),"dd-MM-yyyy"));	
			
			if(imageEdited!=null && imageEdited.equals("Yes") || imageEdited.equals("YES")){
				editSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);
				editSubscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			}
			else if(imageEdited==null || imageEdited.equals("No")|| imageEdited.equals("NO") ){
				editSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
				editSubscriberDetails.setFinalStatus(KycConstants.FINAL_STATUS);
				editSubscriberDetails.setFinalStatusReason(KycConstants.FINAL_STATUS_REASON);
			}
			editSubscriberDetails.setStatusFlag(KycConstants.FALSE);
			//editSubscriberDetails.setSubscriberDetailsId(null);
			
			editSubscriberDetails.setSubscriberIdDetails(editsubscriberIdDetailsObj);
			editSubscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());
			//editSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);
			editSubscriberDetails.setUpdatedOn(timestamp);
			editSubscriberDetails.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			editSubscriberDetails.setNationality(subscriberDetailsDto.getNationality());
			//editSubscriberDetails.setFinalStatusReason(null);
			editSubscriberDetails.setActivationTime(null);
			//editSubscriberDetails.setAmAccount(null);
			editSubscriberDetails.setEmaActivationTime(null);
			editSubscriberDetails.setPartialActivationTime(null);
			editSubscriberDetails.setSvActivationTime(null);
			editSubscriberDetails.setWorkFlowReason(null);
			editSubscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			editSubscriberDetails.setBulkSubscriberFlag(0);
			editSubscriberDetails.setIpAddress(ipAddress);
			editSubscriberDetails.setIpAddress(handsetImei);
			editSubscriberDetails.setPlaceOfBirth(placeOfBirth);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				editSubscriberDetails.setUpdatedBy(auth.getName());
			}
			
			
			if(subscriberDetailsDto.getIsImageEdited()!=null && subscriberDetailsDto.getIsImageEdited().equals("Yes") || subscriberDetailsDto.getIsImageEdited().equals("YES")){
				editSubscriberDetails.setIsImageEdited("YES");
			}
			
			else if(subscriberDetailsDto.getIsImageEdited()==null || subscriberDetailsDto.getIsImageEdited().equals("No") || subscriberDetailsDto.getIsImageEdited().equals("NO")){
				editSubscriberDetails.setIsImageEdited("NO");
			}
			
			if(subscriberDetailsDto.getAmAccount().equals("Yes") ||subscriberDetailsDto.getAmAccount()=="1"){
				editSubscriberDetails.setAmAccount(1);
			}
			else if(subscriberDetailsDto.getAmAccount().equals("No") ||subscriberDetailsDto.getAmAccount()=="0"){
				editSubscriberDetails.setAmAccount(0);
			}
			if(subscriberDetailsDto.getIsMinor().equals("Yes")||subscriberDetailsDto.getIsMinor()=="1"){
				editSubscriberDetails.setIsMinor(1);
			}
			if(subscriberDetailsDto.getIsMinor().equals("No")||subscriberDetailsDto.getIsMinor()=="0"){
				editSubscriberDetails.setIsMinor(0);
			}
			
			
			if(subscriberDetailsDto.getOccupation()!=null){
				editSubscriberDetails.setOccupation(subscriberDetailsDto.getOccupation());
			}
			if(subscriberDetailsDto.getCaseType()!=null){
				editSubscriberDetails.setCaseType(subscriberDetailsDto.getCaseType());	
			}	
			
			if(subscriberDetailsDto.getProxyForRegistration().equals("Yes")||subscriberDetailsDto.getProxyForRegistration()=="1"){
				editSubscriberDetails.setProxyForRegistration(1);
			}
			else{
				editSubscriberDetails.setProxyForRegistration(0);
			}

			if(subscriberDetailsDto.getSubscriberType()!=null){
				editSubscriberDetails.setSubscriberType(subscriberDetailsDto.getSubscriberType());	
			}
			/*Set<SubscriberImageDetail> subscriberImageDetailTemp = new HashSet<SubscriberImageDetail>();

			for (SubscriberImageDetail subscriberImageDetail2 : subscriberImageDetail) {
				subscriberImageDetail2.setSubscriberDetails(editSubscriberDetails);
				subscriberImageDetailTemp.add(subscriberImageDetail2);
			}*/
			
			//editSubscriberDetails.setSubscriberImageDetail(subscriberImageDetailTemp);
			//editSubscriberDetails.setSubscriber(subscriber);
			subscriberDetailsList.add(editSubscriberDetails);
			subscriber.setSubscriberDetails(subscriberDetailsList);
			
			subscriber.setUpdatedOn(timestamp);
			
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			//subscriberDao.saveOrUpdate(subscriber);
			
			this.taskManagementService.addTask(TaskEnum.SIM_VALIDATION.getTaskCode(),
					subscriber.getSubscriberId(), subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId().toString());
			
			
			
			
				List<SubscriberIdDetails> subscriberIdDetailsObj=subscriberIdDetailsDao.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				for (SubscriberIdDetails subscriberIdDetailsList : subscriberIdDetailsObj) {
					
					if(imageEdited!=null && imageEdited.equals("Yes") || imageEdited.equals("YES")){
						subscriberIdDetailsList.setIsOldIdDetails(KycConstants.FALSE);
					}
					else if(imageEdited==null || imageEdited.equals("No") || imageEdited.equals("NO")){
						subscriberIdDetailsList.setIsOldIdDetails(KycConstants.TRUE);
					}
					//subscriberIdDetailsList.setIsOldIdDetails(KycConstants.FALSE);
					subscriberIdDetailsDao.saveOrUpdate(subscriberIdDetailsList);
				}
				
				if(imageEdited==null || imageEdited.equals("No") || imageEdited.equals("NO")){
					taskManagementService.addTask(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(), subscriber.getSubscriberId(),
							subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId().toString());
				}
				
		

		} catch (KycDaoException e) {
			
			e.printStackTrace();
		}
		return subscriberDto;
	
		
	}
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDto updateSubscriberObj(SubscriberDto subscriberDto) throws BusinessException {
		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		try {
			Subscriber subscriber = null;
			// get subscriber data by id
			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("subscriberId", subscriberDto.getSubscriberId());			
			subscriber=subscriberDao.getSubscriberBySubscriberId(subscriberDto.getSubscriberId());
			
			// get subscriber from data with subscriber id
			List<SubscriberDetails> subscriberDetailsList = this.kycDaoService.getSubscriberDetails(subscriber.getSubscriberId(), KycConstants.FALSE);
			
			Boolean idFrontImageSaveStatus = false;			
			Boolean subscriberImageIdSaveStatus = false;
			Boolean physicalFormIdSaveStatus = false;
			
			Boolean saveresult = false;
			String trnsId=subscriberDto.getKycTansactionId();
			String timestr =null;
			String destfilepath =null;
			if(trnsId==null){
				timestr = messageSource.getMessage(ConfigConstants.HOST_NAME, null, Locale.getDefault()) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		       destfilepath = messageSource.getMessage(ConfigConstants.UPLOAD_FOLDER, null, Locale.getDefault()) + messageSource.getMessage(ConfigConstants.SUBSCRIBER_DIRECTORY, null, Locale.getDefault()) + timestr + "/"
				+ subscriber.getKycTansactionId() + "/";
			}
			else{
				timestr = messageSource.getMessage(ConfigConstants.HOST_NAME, null, Locale.getDefault()) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		        destfilepath = messageSource.getMessage(ConfigConstants.UPLOAD_FOLDER, null, Locale.getDefault()) + messageSource.getMessage(ConfigConstants.SUBSCRIBER_DIRECTORY, null, Locale.getDefault()) + timestr + "/"
				+ subscriberDto.getKycTansactionId() + "/";
			}
			
			
			
			
			SubscriberDetails editSubscriberDetails = new SubscriberDetails();
			SubscriberDetails oldSubscriberDetails = new SubscriberDetails();
			List<SubscriberIdDetails> subscriberIdDetails = new ArrayList<SubscriberIdDetails>();
			List<SubscriberImageDetail> subscriberImageDetail = new ArrayList<SubscriberImageDetail>();
			Address address  = new Address();
			Region region = new Region();

			
			
			
			if (subscriber.getSubscriberId() != null) {
				subscriber.setUpdatedOn(timestamp);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null)
					subscriber.setUpdatedBy(auth.getName());
			} else {
				subscriber.setDeleteFlag(KycConstants.FALSE);
				subscriber.setStatusFlag(KycConstants.TRUE);
				subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null)
					subscriber.setCreatedBy(auth.getName());
			}
			//subscriber.setIsOldSubscriber(subscriberDto.getIsOldSubscriber());
				

			
			// get subscriber 1st elemnet from subcriber DTO
			SubscriberDetailsDto subscriberDetailsDto = subscriberDto.getSubscriberDetails().get(0);

			if (subscriberDetailsList != null && subscriberDetailsList.size() > 0) {
				oldSubscriberDetails = subscriberDetailsList.get(0);				
				BeanUtils.copyProperties(oldSubscriberDetails, editSubscriberDetails);
				editSubscriberDetails.setSubscriberDetailsId(null);
				editSubscriberDetails.setSubscriberImageDetail(null);				
				subscriberImageDetail = this.kycDaoService
						.getSubscriberImageDetail(editSubscriberDetails.getSubscriberDetailsId(), KycConstants.FALSE);
				
				subscriberIdDetails = this.kycDaoService
							.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				
				//editSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);

				oldSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
				editSubscriberDetails.setIsLatest(KycConstants.TRUE);
				oldSubscriberDetails.setStatusFlag(KycConstants.FALSE);
				
			}
			
			
			
			Set<SubscriberIdDetails> editsubscriberIdDetailsObj = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberDetailsDto.getSubscriberIdDetails()) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageFrontDate = subscriberIdDetailsDto.getIdImageFrontData();
					//String idImageBackDate = subscriberIdDetailsDto.getIdImageBackData();
					//String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					String subscriberImage ="subscriberImage";
					String physicalFormId=subscriberIdDetailsDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageFrontData(),
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}
					
					if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getSubscriberImageId(),
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getPhysicalFormId(),
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !subscriberImageIdSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(editSubscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					subscriberIdDetail.setIsOldIdDetails(KycConstants.TRUE);
					subscriberIdDetail.setIdNo(null);

					// add in subscriberIdDetails arraylist
					editsubscriberIdDetailsObj.add(subscriberIdDetail);
				}
			
			
			
			List list = this.kycDaoService.getSubscriberAddress(subscriberDetailsDto.getSubscriberDetailsId());
			
		   
		    Object AddressId = ((HashMap) list.get(0)).get("ADDRESS_ID");		    
			
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			Integer districtId = addressDto.getDistrict().getDistrictId();
			Integer addressId = ((BigDecimal) AddressId).intValue();
			BeanUtils.copyProperties(addressDto, address);	
			if (addressDto.getParish() != null)
			{
				address.setParishId(addressDto.getParish().getParishId());
			}
			
			address.setDistrictId(districtId);
			address.setAddressId(addressId);	
			
			editSubscriberDetails.setAddress(address);			
			editSubscriberDetails.setPostalAddress(addressDto.getPostalCode());
			editSubscriberDetails.setFirstName(subscriberDetailsDto.getFirstName());
			editSubscriberDetails.setMiddleName(subscriberDetailsDto.getMiddleName());
			editSubscriberDetails.setLastName(subscriberDetailsDto.getLastName());			
			editSubscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(),"dd-MM-yy"));			
			editSubscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
			editSubscriberDetails.setStatusFlag(KycConstants.FALSE);
			editSubscriberDetails.setSubscriberDetailsId(null);
			editSubscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			editSubscriberDetails.setSubscriberIdDetails(editsubscriberIdDetailsObj);
			editSubscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());
			editSubscriberDetails.setIsOldUserDetails(KycConstants.TRUE);
			editSubscriberDetails.setUpdatedOn(timestamp);
			editSubscriberDetails.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			editSubscriberDetails.setNationality(subscriberDetailsDto.getNationality());
			editSubscriberDetails.setFinalStatusReason(null);
			editSubscriberDetails.setActivationTime(null);
			//editSubscriberDetails.setAmAccount(null);
			editSubscriberDetails.setHpActivationTime(null);
			editSubscriberDetails.setPartialActivationTime(null);
			editSubscriberDetails.setSvActivationTime(null);
			editSubscriberDetails.setWorkFlowReason(null);
			editSubscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			
			if(subscriberDetailsDto.getIsImageEdited()!=null && subscriberDetailsDto.getIsImageEdited().equals("Yes")){
				editSubscriberDetails.setIsImageEdited("YES");
			}
			
			else if(subscriberDetailsDto.getIsImageEdited()==null || subscriberDetailsDto.getIsImageEdited().equals("No")){
				editSubscriberDetails.setIsImageEdited("NO");
			}
			
			if(subscriberDetailsDto.getAmAccount().equals("Yes") ||subscriberDetailsDto.getAmAccount()=="1"){
				editSubscriberDetails.setAmAccount(1);
			}
			else if(subscriberDetailsDto.getAmAccount().equals("No") ||subscriberDetailsDto.getAmAccount()=="0"){
				editSubscriberDetails.setAmAccount(0);
			}
			if(subscriberDetailsDto.getIsMinor().equals("Yes")||subscriberDetailsDto.getIsMinor()=="1"){
				editSubscriberDetails.setIsMinor(1);
			}
			if(subscriberDetailsDto.getIsMinor().equals("No")||subscriberDetailsDto.getIsMinor()=="0"){
				editSubscriberDetails.setIsMinor(0);
			}
			
			
			if(subscriberDetailsDto.getOccupation()!=null){
				editSubscriberDetails.setOccupation(subscriberDetailsDto.getOccupation());
			}
			if(subscriberDetailsDto.getCaseType()!=null){
				editSubscriberDetails.setCaseType(subscriberDetailsDto.getCaseType());	
			}	

			Set<SubscriberImageDetail> subscriberImageDetailTemp = new HashSet<SubscriberImageDetail>();

			for (SubscriberImageDetail subscriberImageDetail2 : subscriberImageDetail) {
				subscriberImageDetail2.setSubscriberDetails(editSubscriberDetails);
				subscriberImageDetailTemp.add(subscriberImageDetail2);
			}

			editSubscriberDetails.setSubscriberImageDetail(subscriberImageDetailTemp);
			subscriberDetailsList.add(editSubscriberDetails);
			subscriber.setSubscriberDetails(subscriberDetailsList);
			subscriber.setUpdatedOn(timestamp);
			
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			
			
			
				List<SubscriberIdDetails> subscriberIdDetailsObj=subscriberIdDetailsDao.getSubscriberIdDetails(subscriberDto.getSubscriberDetails().get(0).getSubscriberDetailsId(), KycConstants.FALSE);
				for (SubscriberIdDetails subscriberIdDetailsList : subscriberIdDetailsObj) {
					subscriberIdDetailsList.setIsOldIdDetails(KycConstants.FALSE);
					subscriberIdDetailsDao.saveOrUpdate(subscriberIdDetailsList);
				}
				
			
		

		} catch (KycDaoException e) {
			
			e.printStackTrace();
		}
		return subscriberDto;
	
		
	}*/


	@Override
	public BaseDto addSubscriber(SubscriberDto subscriberDto, UsersDto usersDto,SubscriberUserDto subscriberUserDto,String ipAddress,String handsetImei,String placeOfBirth) throws BusinessException, UserException {
		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());

		Subscriber subscriber = new Subscriber();
		BeanUtils.copyProperties(subscriberDto, subscriber);
		Boolean idFrontImageSaveStatus = false;
		Boolean idBackImageSaveStatus = false;
		//Boolean subscriberImageIdSaveStatus = false;
		Boolean physicalFormIdSaveStatus = false;
		List<SubscriberDetails> subscriberDetailsList = new ArrayList<SubscriberDetails>();
		List<SubscriberDetailsDto> SubscriberDetailsDtoList = subscriberDto.getSubscriberDetails();
		List<SubscriberDocumentsDto> documentsDtos = null;

		Boolean saveresult = false;
		String timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		String destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ subscriberDto.getKycTansactionId() + "/";

		for (SubscriberDetailsDto subscriberDetailsDto : SubscriberDetailsDtoList) {
			SubscriberDetails subscriberDetails = new SubscriberDetails();
			// BeanUtils.copyProperties(subscriberDto.getSubscriberDetails(),
			// subscriberDetails);
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			BeanUtils.copyProperties(subscriberDetailsDto, subscriberDetails);
			subscriberDetails.setPermissableRadius(environment.getProperty(PERMISSABLE_RADIUS));
			subscriberDetails.setPostalAddress(addressDto.getPostalCode());
			subscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(), "dd-MM-yyyy"));
			if(subscriberDetailsDto.getAmAccount().equals("Yes") ||subscriberDetailsDto.getAmAccount()=="1"){
				subscriberDetails.setAmAccount(1);
			}
			else if(subscriberDetailsDto.getAmAccount().equals("No") ||subscriberDetailsDto.getAmAccount()=="0"){
				subscriberDetails.setAmAccount(0);
			}
			if(subscriberDetailsDto.getIsMinor().equals("Yes")||subscriberDetailsDto.getIsMinor()=="1"){
				subscriberDetails.setIsMinor(1);
			}
			if(subscriberDetailsDto.getIsMinor().equals("No")||subscriberDetailsDto.getIsMinor()=="0"){
				subscriberDetails.setIsMinor(0);
			}
			
			if(subscriberDetailsDto.getOccupation()!=null){
				subscriberDetails.setOccupation(subscriberDetailsDto.getOccupation());
			}
			
			if(subscriberDetailsDto.getMinorName()!=null){
				subscriberDetails.setMinorName(subscriberDetailsDto.getMinorName());
			}
			
			if(subscriberDetailsDto.getSubscriberType()!=null){				
				subscriberDetails.setSubscriberType(subscriberDetailsDto.getSubscriberType());
			}
			
			if (addressDto != null) {
				Address address = new Address();
				BeanUtils.copyProperties(subscriberDetailsDto.getAddress(), address);
				/*if (addressDto.getCountry() != null)
					address.setCountryid(addressDto.getCountry().getCountryId());*/
				if (addressDto.getRegion() != null)
					address.setRegionId(addressDto.getRegion().getRegionId());
				if (addressDto.getDistrict() != null)
					address.setDistrictId(addressDto.getDistrict().getDistrictId());
				if (addressDto.getVillage() != null)
					address.setVillageId(addressDto.getVillage().getVillageId());
				address.setCountryId(addressDto.getCountryId());
				
				subscriberDetails.setAddress(address);
			}

			Set<SubscriberIdDetails> subscriberIdDetails = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberDetailsDto.getSubscriberIdDetails()) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageFrontDate = subscriberIdDetailsDto.getIdImageFrontData();
					String idImageBackDate = subscriberIdDetailsDto.getIdImageBackData();
					String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					//String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					//String subscriberImage ="subscriberImage";
					String physicalFormId=subscriberIdDetailsDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageFrontData(),
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}
					
					if (idImageBackDate != null && idImageBackDate.length() > 0) {
						idBackImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageBackData(),
								destfilepath, idImageBack + ".jpg");
						subscriberIdDetail.setIdImageBack(idImageBack);
					}
					
					/*if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getSubscriberImageId(),
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}*/
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getPhysicalFormId(),
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !idBackImageSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(subscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					subscriberIdDetail.setIsOldIdDetails(KycConstants.FALSE);

					// add in subscriberIdDetails arraylist
					subscriberIdDetails.add(subscriberIdDetail);
				}

			// Set Douments
			documentsDtos = subscriberDetailsDto.getSubscriberDocuments();
			if (documentsDtos != null) {
				List<SubscriberDocuments> documents = new ArrayList<>();
				String documentFilePath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
						+ environment.getProperty(ConfigConstants.SUBSCRIBER_DOCUMENTS) + subscriberDto.getKycTansactionId() + "/";
				for (SubscriberDocumentsDto documentsDto : documentsDtos) {
					SubscriberDocuments document = new SubscriberDocuments();
					BeanUtils.copyProperties(documentsDto, document);

					// Save Image
					if (documentsDto.getDocumentImageData() != null
							&& documentsDto.getDocumentImageData().length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(documentsDto.getDocumentImageData(),
								destfilepath, documentsDto.getDocumentName() + ".jpg");

					}
					document.setDocumentImage(documentFilePath);
					document.setSubscriberDetails(subscriberDetails);
					documents.add(document);
				}
				subscriberDetails.setSubscriberDocuments(documents);
			}
			subscriberDetails.setSubscriberIdDetails(subscriberIdDetails);

			/*Set<SubscriberImageDetail> subscriberImageDetails = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberImageDetail() != null)
				for (SubscriberImageDetailDto subscriberImageDetailDto : subscriberDetailsDto
						.getSubscriberImageDetail()) {
					SubscriberImageDetail subscriberImageDetail = new SubscriberImageDetail();
					BeanUtils.copyProperties(subscriberImageDetailDto, subscriberImageDetail);
					subscriberImageDetail.setSubscriberDetails(subscriberDetails);

					subscriberImageDetail.setImagePath(timestr);

					saveresult = Utility.saveBase64ToFile(subscriberImageDetailDto.getImageStr(), destfilepath,
							subscriberImageDetailDto.getImageType() + "." + subscriberImageDetailDto.getImageFormat());

					if (!saveresult) {
						// responseBean.setStatus(getStatusBean("ERROR_IMG",
						// "exception.message", "Something Went wrong. Please
						// try again later."));
						break;
					}

					subscriberImageDetail.setIsOldImageDetails(KycConstants.FALSE);
					subscriberImageDetails.add(subscriberImageDetail);
				}*/
			//subscriberDetails.setSubscriberImageDetail(subscriberImageDetails);
			subscriberDetails.setSubscriber(subscriber);
			subscriberDetails.setStatusFlag(KycConstants.TRUE);
			subscriberDetails.setDeleteFlag(KycConstants.FALSE);
			subscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
			
			//newly added
			subscriberDetails.setIsImageEdited("NO");
			subscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setNationality(subscriberDetailsDto.getNationality());
			subscriberDetails.setCaseType(subscriberDetailsDto.getCaseType());			
			subscriberDetails.setPhysicalFormStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setIsPhysicalFormReceived(0);
			subscriberDetails.setMsisdn(subscriberDto.getMsisdn());
			subscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());
			subscriberDetails.setUpdatedOn(timestamp);
			subscriberDetails.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			subscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			subscriberDetails.setSimSerialNumber(subscriberDto.getSimSerialNumber());
			subscriberDetails.setBulkSubscriberFlag(0);
			subscriberDetails.setIpAddress(ipAddress);
			subscriberDetails.setImei(handsetImei);
			subscriberDetails.setPlaceOfBirth(placeOfBirth);
			
			//subscriberDetails.setCountryId(subscriberDto.getCountryId());
			if(subscriberDetailsDto.getProxyForRegistration().equals("Yes")||subscriberDetailsDto.getProxyForRegistration()=="1"){
				subscriberDetails.setProxyForRegistration(1);
			}
			if(subscriberDetailsDto.getProxyForRegistration().equals("No")||subscriberDetailsDto.getProxyForRegistration()=="0"){
				subscriberDetails.setProxyForRegistration(0);
			}
			subscriberDetails.setSubmitedOn(new Timestamp(Long.valueOf(subscriberDetailsDto.getSubmitedOn())));
			if(subscriberDetailsDto.getSyncedOn()!=null){
				subscriberDetails.setSyncedOn(new Timestamp(Long.valueOf(subscriberDetailsDto.getSyncedOn())));
			}
			if(subscriberDetailsDto.getRegisteredOn()!=null){
				subscriberDetails.setRegisteredOn(new Timestamp(Long.valueOf(subscriberDetailsDto.getRegisteredOn())));
			}
			
			
			//subscriberDetails.setSyncedOn(new Timestamp(DateUtils.getDate(subscriberDetailsDto.getSyncedOn(),"dd-MM-yy HH:mm:ss").getTime()));
			subscriberDetails.setOnlineOfflineFlag(subscriberDetailsDto.getOnlineOfflineFlag());
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriberDetails.setCreatedBy(auth.getName());
			}
			
			/*byte [] fingerPrint=Base64.decodeBase64(subscriberDetailsDto.getFingerPrint());
			try {
				subscriberDetails.setFingerPrint(new javax.sql.rowset.serial.SerialBlob(fingerPrint));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			subscriberDetailsList.add(subscriberDetails);

		}

		// OperactionOnSubscriber operactionOnSubscriber = new
		// OperactionOnSubscriber();
		// operactionOnSubscriber.setSubscriber(subscriber);
		// subscriber.setOperactionOnSubscriber(operactionOnSubscriber);
		subscriber.setSubscriberDetails(subscriberDetailsList);
		subscriber.setStatusFlag(KycConstants.TRUE);
		subscriber.setDeleteFlag(KycConstants.FALSE);

		/*subscriber.setFinalStatus(KycConstants.PENDING_STATUS);
		subscriber.setNationality(subscriberDto.getNationality());
		subscriber.setCaseType(subscriberDto.getCaseType());*/

		
		//Added for user creation
		if(subscriberUserDto.getUserFlag()==0 || subscriberUserDto.getUserFlag()==null ){
			subscriber.setUserFlag(KycConstants.FALSE);
		}

		if (subscriber.getSubscriberId() != null && subscriber.getSubscriberId()!=0) {
			subscriber.setUpdatedOn(timestamp);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setUpdatedBy(auth.getName());
			}
		} else {
			subscriber.setDeleteFlag(KycConstants.FALSE);
			subscriber.setStatusFlag(KycConstants.TRUE);
			subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));		
			subscriber.setUpdatedOn(timestamp);
			
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setCreatedBy(auth.getName());
			}
		}
		
		
		
		try {			
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			
			this.taskManagementService.addTask(TaskEnum.SIM_VALIDATION.getTaskCode(),
					subscriber.getSubscriberId(), subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId().toString());

			/*if (subscriber == null) {
				throw new BusinessException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionCode(),
						SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionDescription());
			}*/
			subscriberDto.setSubscriberId(subscriber.getSubscriberId());
			if(isDebugEnabled) {
				log.debug("Subscriber created with subscriber id : "+subscriber.getSubscriberId());
			}
			if(subscriberUserDto.getUserFlag()==1 && usersDto.getUserDetail().getMsisdn()!=null ){
				provisionUser(usersDto);
			}
			
			if(subscriberDto.getLocation() != null) {
				boolean locationUpated = this.helperDataManagementService.updateLocation(subscriberDto.getLocation() , null, subscriber.getSubscriberId());
				if(isDebugEnabled) {
					log.debug("Subscriber Location Updated : "+locationUpated);
				}
			}
		} catch (KycDaoException cause) {
			if(isDebugEnabled) {
				log.debug("Exception occured while adding subscriber ");
			}
			throw new BusinessException(cause);
		}
		return subscriberDto;

	
	}


	@Override
	@SuppressWarnings("unchecked")
	public SubscriberSearchResponseDto getSubscriber(SearchSubscriberDto searchSubscriberDto, Integer isOldData)
			throws BusinessException {

		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		
		List<Subscriber> subscriberList = null;
		try {
			List<SubscriberDto> subscriberDtoObjList = new ArrayList<SubscriberDto>();
			//searchSubscriberDto.setIsOldSubscriber(isOldData);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String createdBy=auth.getName();
			searchSubscriberDto.setCreatedBy(createdBy);
			subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto);

			List<SubscriberDetails> subscriberDetailsObjList = new ArrayList<>();
			for (SubscriberDetails subscriberDetails : subscriberList.get(0).getSubscriberDetails()) {
				if(subscriberDetails.getIsOldUserDetails()==0){
					SubscriberDetails subscriberDetailsObj=new SubscriberDetails();
					BeanUtils.copyProperties(subscriberDetails, subscriberDetailsObj);	
					subscriberDetailsObjList.add(subscriberDetailsObj);
				}
				
			}
			List<Subscriber> subscriberObjList = new ArrayList<Subscriber>();
			for (Subscriber subscriberLists : subscriberList) {
				Subscriber subscriber=new Subscriber();
				BeanUtils.copyProperties(subscriberLists, subscriber);
				subscriber.setSubscriberDetails(subscriberDetailsObjList);
				subscriberObjList.add(subscriber);
				
			}
			//subscriberList.get(0).setSubscriberDetails(subscriberDetailsObjList);		
			
			for (Subscriber subscriber : subscriberObjList) {
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);

				
				
					List<SubscriberDetailsDto> subscriberDetails = getSubscriberDetails(subscriberDto,searchSubscriberDto.getIsOldUserDetails(), false);
					subscriberDto.setSubscriberDetails(subscriberDetails);
					subscriberDto.setOperactionOnSubscriber(null);
					subscriberDto.setActionOnSubscriber(null);
					//subscriberDto.setSubscriberWorkFlow(null);
	
					subscriberDtoList.add(subscriberDto);
				
			}
			long totalCount = this.kycDaoService.getTotalSubscriber(searchSubscriberDto);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	
	}

	public UsersDto provisionUser(UsersDto usersDto) throws UserException {
		// UserDto dto = new UserDto();
		Users users = new Users();
		BeanUtils.copyProperties(usersDto, users);
		try {
			List<UserDetails> detailsList = new ArrayList<UserDetails>();
			UserDetails userDetail=this.userRepositry.getUserDetailByMsisdn(usersDto.getUserDetail().getMsisdn());
			//userDetail.setFirstName(usersDto.getUserDetail().getFirstName());
			if(userDetail!=null){
				userDetail.setFirstName(usersDto.getUserDetail().getFirstName());
				userDetail.setLastName(usersDto.getUserDetail().getLastName());
				userDetail.setEmail(usersDto.getUserDetail().getEmail());
				userDetail.setAuuid(usersDto.getUserDetail().getAuuid());
				userDetail.setDob(DateUtils.getDate(usersDto.getUserDetail().getDob(),"dd-MM-yyyy"));
				userDetail.setIdCardNo(usersDto.getUserDetail().getIdCardNo());
				userDetail.setDistributorName(usersDto.getUserDetail().getDistributorName());
				userDetail.setTsmAuuid(usersDto.getUserDetail().getTsmAuuid());
				userDetail.setDeviceId(usersDto.getUserDetail().getDeviceId());
				if(usersDto.getUserDetail().getEditInfoAccess().equals("active"))
				{
				   userDetail.setEditInfoAccess(1);
				}
				else if(usersDto.getUserDetail().getEditInfoAccess().equals("inactive"))
				{
				   userDetail.setEditInfoAccess(0);
				}
				if(usersDto.getUserDetail().getSimSwapAccess().equals("active"))
				{
				   userDetail.setSimSwapAccess(1);
				}
				else if(usersDto.getUserDetail().getSimSwapAccess().equals("inactive"))
				{
				   userDetail.setSimSwapAccess(0);
				}
				userDetail.setChannelPartnerCellId(usersDto.getUserDetail().getChannelPartnerCellId());
				userDetail.setLatitude(usersDto.getUserDetail().getLatitude());
				userDetail.setLongitude(usersDto.getUserDetail().getLongitude());
				userDetail.setPermissableRadius(usersDto.getUserDetail().getPermissableRadius());
				
				if (usersDto.getUserDetail().getUserDepartment() != null) {
					List<Department> userDepartment = new ArrayList<>();
					for (DepartmentDto userD : usersDto.getUserDetail().getUserDepartment()) {
						Department department = new Department();
						BeanUtils.copyProperties(userD, department);
						userDepartment.add(department);
					}
					userDetail.setUserDepartment(userDepartment);
				}

				//userDetail.setUsers(users);
				userDetail.setIsOldUserDetails(KycConstants.FALSE);
				//this.userRepositry.saveOrUpdateUser(userDetail);
				
				/*subscriberDetailsList.add(editSubscriberDetails);
				subscriber.setSubscriberDetails(subscriberDetailsList);*/
				
				userDetail.setUsers(users);				
				detailsList.add(userDetail);
				users.setUserDetails(detailsList);
			}
			//if (usersDto.getUserDetail() != null)
			else if (userDetail == null)
			{
				UserDetails userDetails = new UserDetails();
				
				BeanUtils.copyProperties(usersDto.getUserDetail(), userDetails);
				userDetails.setDob(DateUtils.getDate(usersDto.getUserDetail().getDob(),"dd-MM-yyyy"));

				if (usersDto.getUserDetail().getUserDepartment() != null) {
					List<Department> userDepartment = new ArrayList<>();
					for (DepartmentDto userD : usersDto.getUserDetail().getUserDepartment()) {
						Department department = new Department();
						BeanUtils.copyProperties(userD, department);
						userDepartment.add(department);
					}
					userDetails.setUserDepartment(userDepartment);
				}

				userDetails.setUsers(users);
				userDetails.setIsOldUserDetails(KycConstants.FALSE);
				detailsList.add(userDetails);
			}
			

			if (usersDto.getSubRole() != null) {
				List<Role> roles = new ArrayList<>();
				for (RoleDto dto : usersDto.getSubRole()) {
					Role tempRole = new Role();
					BeanUtils.copyProperties(dto, tempRole);
					for (RoleDto roleDto : usersDto.getRoles()) {						
						tempRole.setParentRoleId(roleDto.getRoleId());	
						if(roleDto.getRoleId()==22 || roleDto.getRoleId()==37 || roleDto.getRoleId()==38 || roleDto.getRoleId()==39
						|| roleDto.getRoleId()==40  || roleDto.getRoleId()==41 || roleDto.getRoleId()==42 || roleDto.getRoleId()== 43)
						{
							tempRole.setMobileAccessFlag(1);
						}
						else{
							tempRole.setMobileAccessFlag(0);
						}
						tempRole.setWebAccessFlag(1);
					}
					
					roles.add(tempRole);
				}
				users.setRoles(roles);
			}
			/*if(usersDto.getSubRoles().getRoleId()!=null)
			{
				List<Role> roles = new ArrayList<>();
				Role role=new Role();
				RoleDto roleDto=usersDto.getSubRoles();
				//role.setRoleId(usersDto.getSubRoles().getParentRoleId());
				//role.setRoleName(usersDto.getSubRoles().getRoleName());
				BeanUtils.copyProperties(roleDto, role);
				roles.add(role);
				users.setRoles(roles);
			}*/
			users.setUserDetails(detailsList);
			
			 //generate user password
			 
			String password = KycConstants.DEFULT_PASSWORD;
			BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(KycConstants.PASSWORD_LENGTH);
			password = cryptPasswordEncoder.encode(password);
			users.setPassword(password);
			users.setBulkEnabled("1");
			users.setIsPasswordModified(KycConstants.FALSE);
			usersDto.setPassword(password);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if(users.getUserId()!=null)
			{
				users.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				if (auth != null) {
					users.setUpdatedBy(auth.getName());
				}
			}
			else
			{
				users.setDeleteFlag(UserConstants.FALSE);
				users.setStatusFlag(UserConstants.TRUE);
				users.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				if (auth != null) {
					users.setCreatedBy(auth.getName());
				}
			}
			
			 //Save user in database.
			 
			users = this.userRepositry.saveOrUpdateUser(users);
			if (users == null) {
				throw new UserException(UserExceptionCodes.USER_UNABLE_TO_REGISTERED);
			}
			
			
			// Update user location.
			 
			if (users.getUserId() != null)
				updateUserLocation(users, usersDto);
			
			
			 //Updating mapping between subscriber and user
			 
			Long subscriberId = usersDto.getSubscriberId();
			if(subscriberId != null && subscriberId> 0) {
				Subscriber subscriber = new Subscriber();
				subscriber.setSubscriberId(subscriberId);
				SubscriberUser subscriberUser = new SubscriberUser();
				subscriberUser.setUsers(users);
				subscriberUser.setSubscriber(subscriber);
				this.kycDaoService.saveOrUpdateEntity(subscriberUser);
			}
		} catch (UserDaoException  e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		} catch (KycDaoException e) {
			//e.printStackTrace();
		}
		return usersDto;
	}
	
	@SuppressWarnings("unchecked")
	private void updateUserLocation(Users users, UsersDto usersDto) {
		if (users.getUserId() != null && usersDto.getLocation() != null) {
			Map<String, Object> parameterMap = new HashMap<>();
			List<Location> list = null;
			try {
				parameterMap.put("userId", users.getUserId());
				list = this.kycDaoService.findByCriteria(Location.class, parameterMap);
			} catch (KycDaoException e1) {
				e1.printStackTrace();
			}
			if (list != null && list.size() > 0) {
				for (Location location : list) {
					location.setIsOldRecord(KycConstants.TRUE);
					try {
						this.kycDaoService.saveOrUpdateEntity(location);
					} catch (KycDaoException e) {
						e.printStackTrace();
					}
				}
			}
			//LocationDto dto = usersDto.getLocation();
			
			//UserLocation location = new UserLocation();
			/*location.setUserId(users.getUserId());
			location.setRegionId(dto.getRegionId);
			location.setTerritoryId(dto.getTerritoryId());
			location.setDistrictId(dto.getDistrictId());*/
			/*location.setUserId(users.getUserId());
			location.setBranchId(dto.getBranchId());
			location.setZoneId(dto.getZoneId());
			location.setClusterId(dto.getClusterId());
			location.setSiteId(dto.getSiteId());
			location.setIsOldRecord(KycConstants.FALSE);
			*/
			
			
		/*	//nested loop for user to user_region mapping
			for (RegionIds regionIds : usersDto.getLocation().getRegionIds()) {
				UserRegion userRegion=new UserRegion();
				userRegion.setUserId(users.getUserId());
				userRegion.setIsOldRecord(KycConstants.FALSE);
				userRegion.setRegionId(regionIds.getRegionId());
				try {
					this.kycDaoService.saveOrUpdateEntity(userRegion);
				} catch (KycDaoException e) {
					e.printStackTrace();
				}
				*/
				//nested loop for user_region to user_territory mapping
				for (ProvinceIds provinceIds : usersDto.getLocation().getProvinceIds()) {
					UserProvince userProvince=new UserProvince();
					userProvince.setProvinceId(provinceIds.getProvinceId());
					
					try {
						this.kycDaoService.saveOrUpdateEntity(userProvince);
					} catch (KycDaoException e) {
						e.printStackTrace();
					}
					//nested loop for user_territory to user_district mapping
					for (DistrictIds districtIds : usersDto.getLocation().getDistrictIds()) {
						UserDistrict userDistrict=new UserDistrict();
						userDistrict.setUserProvinceId(provinceIds.getProvinceId());
						userDistrict.setDistrictId(districtIds.getDistrictId());
						try {
							this.kycDaoService.saveOrUpdateEntity(userDistrict);
						} catch (KycDaoException e) {
							e.printStackTrace();
						}
					}
					
				}
			//}
			
			
			
			

		}
	}

	@Override
	public TotalCountDto getSubscriberCount(int userId, String roleName) throws BusinessException {
		TotalCountDto totalCountDto = new TotalCountDto();
		SearchSubscriberDto searchSubscriberDto = new SearchSubscriberDto();

		//searchSubscriberDto.setIsOldSubscriber(KycConstants.FALSE);

		searchSubscriberDto.setUserId(userId);
		searchSubscriberDto.setRoleName(roleName);
		long subscriberCount = 0L;
		long cmSubscriberCount = 0L;
		long cmSelfAssignedSubscriberCount = 0L;
		long cmApprovedSubscriberCount = 0L;
		long cmRejectedSubscriberCount = 0L;
		
		long kycOpenCasesSubscriberCount = 0L;
		long kycClosedCasesSubscriberCount = 0L;

		try {

			HashMap map = new HashMap<>();
			/*map.put("isOldSubscriber", KycConstants.FALSE);*/

			subscriberCount = kycDaoService.getEntitySize(Subscriber.class, map);

			cmSubscriberCount = kycDaoService.getTotalSubscriber(searchSubscriberDto, null);
			// searchSubscriberDto.setStatus(ActionCodes.PENDING.toString());
			cmSelfAssignedSubscriberCount = kycDaoService.getTotalSubscriber(searchSubscriberDto,
					ActionCodes.PENDING.toString());
			// searchSubscriberDto.setStatus(ActionCodes.APPROVED.toString());
			cmApprovedSubscriberCount = kycDaoService.getTotalSubscriber(searchSubscriberDto,
					ActionCodes.APPROVED.toString());
			// searchSubscriberDto.setStatus(ActionCodes.REJECTED.toString());
			cmRejectedSubscriberCount = kycDaoService.getTotalSubscriber(searchSubscriberDto,
					ActionCodes.REJECTED.toString());

			searchSubscriberDto.setRoleName("KYC-SUPERVISOR");
			kycOpenCasesSubscriberCount = kycDaoService.getTotalSubscriber(searchSubscriberDto,
					ActionCodes.PENDING.toString());
			
			kycClosedCasesSubscriberCount = kycDaoService.getTotalSubscriber(searchSubscriberDto,
					null);
					
			totalCountDto.setSubscriberCount(subscriberCount);
			totalCountDto.setCmSubscriberCount(cmSubscriberCount);
			totalCountDto.setCmSelfAssignedSubscriberCount(cmSelfAssignedSubscriberCount);
			totalCountDto.setCmApprovedSubscriberCount(cmApprovedSubscriberCount);
			totalCountDto.setCmRejectedSubscriberCount(cmRejectedSubscriberCount);
			
			totalCountDto.setKycOpenCasesSubscriberCount(kycOpenCasesSubscriberCount);
			totalCountDto.setKycClosedCasesSubscriberCount(kycClosedCasesSubscriberCount);
		} catch (KycDaoException cause) {
			// TODO Auto-generated catch block
		}
		return totalCountDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SubscriberSearchResponseDto getBarredSubscribers(SearchSubscriberDto searchSubscriberDto, Integer isOldData)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		try {
			//searchSubscriberDto.setIsOldSubscriber(isOldData);
			SubscriberDetails subscriberDetails=subscriberDetailsDao.getSubscriberDetailsByMsisdn(searchSubscriberDto.getMsisdn(), KycConstants.FALSE,"BARRED");
			if(subscriberDetails.getHotlineFlag()!=null && subscriberDetails.getHotlineFlag()==1){
				subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto);
				for (Subscriber subscriber : subscriberList) {
					SubscriberDto subscriberDto = new SubscriberDto();
					BeanUtils.copyProperties(subscriber, subscriberDto);

					List<SubscriberDetailsDto> subscriberDetailsDtoList = getBarredSubscriberDetails(subscriberDto,
							KycConstants.FALSE, false);
					subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
					subscriberDto.setOperactionOnSubscriber(null);
					subscriberDto.setActionOnSubscriber(null);
					//subscriberDto.setSubscriberWorkFlow(null);

					subscriberDtoList.add(subscriberDto);
				}
			}
			else{
				subscriberList = (List<Subscriber>) this.kycDaoService.getBarredSubscriber(searchSubscriberDto);
				for (Subscriber subscriber : subscriberList) {
					SubscriberDto subscriberDto = new SubscriberDto();
					BeanUtils.copyProperties(subscriber, subscriberDto);

					List<SubscriberDetailsDto> subscriberDetailsDtoList = getBarredSubscriberDetails(subscriberDto,
							KycConstants.FALSE, false);
					subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
					subscriberDto.setOperactionOnSubscriber(null);
					subscriberDto.setActionOnSubscriber(null);
					//subscriberDto.setSubscriberWorkFlow(null);

					subscriberDtoList.add(subscriberDto);
				}	
			}
			
			long totalCount = this.kycDaoService.getTotalSubscriber(searchSubscriberDto);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);
			

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	}

	@Override
	public List<SubscriberIdDetailsDto> getUserByName(String idNumber) throws BusinessException {
		List<SubscriberIdDetailsDto> subscriberIdDetailsDtoList = new ArrayList<>();		
		List<SubscriberIdDetails> subscriberIdDetailsList = subscriberIdDetailsDao.getSubscriberIdDetails(idNumber);
		if(subscriberIdDetailsList.size()>0)
		{
			for (SubscriberIdDetails subscriberIdDetails : subscriberIdDetailsList) {
				SubscriberIdDetailsDto subscriberIdDetailsDto = new SubscriberIdDetailsDto();
				BeanUtils.copyProperties(subscriberIdDetails, subscriberIdDetailsDto);
				subscriberIdDetailsDtoList.add(subscriberIdDetailsDto);
			}
		}

		return subscriberIdDetailsDtoList;

	}

	@Override
	public SubscriberDto generatePassword(SubscriberDto subscriberData) throws BusinessException,  SubscriberException, IOException {
		SubscriberDto subscriberDto = new SubscriberDto();			  
		
		SubscriberDetails details=subscriberDetailsDao.getSubscriberDetailsByMsisdn(subscriberData.getMsisdn(), KycConstants.FALSE,KycConstants.FINAL_STATUS);
		if(details==null){
			
			SubscriberDetails detailsSimvalidationflag=subscriberDetailsDao.getSubscriberDetailsBySImValidationFlag(subscriberData.getMsisdn(), KycConstants.FALSE,KycConstants.TRUE);
			//If SIm Validation Is Failed
			if(detailsSimvalidationflag==null){

				GKYCStatusRequestDto pGKYCStatusRequestDto = new GKYCStatusRequestDto();
				pGKYCStatusRequestDto.settMSISDN(subscriberData.getMsisdn());
				GKYCStatusResponseDto gKYCStatusResponseDto = gKYCStatusDao.getSimValidationFailedSubscriber(pGKYCStatusRequestDto);
				pGKYCStatusRequestDto.settMSISDN(gKYCStatusResponseDto.getRetailerMSISDN());
				Subscriber subscriber = subscriberDao.getSubscriberBySubscriberId(Long.valueOf(gKYCStatusResponseDto.getSubscriberId()));
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date security_code_timeStamp = new Date(timestamp.getTime());
				LOGGER.info("security_code_timeStamp" +security_code_timeStamp);				
				Date otpTimeStamp=null;
				if(subscriber.getOtpCreationTime()==null){
					Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
					otpTimeStamp= new Date(timestamp1.getTime());
				}
				else{
					otpTimeStamp= new Date(subscriber.getOtpCreationTime().getTime());
				}
				long timeDiff = security_code_timeStamp.getTime() - otpTimeStamp.getTime();				
				long diffMinutes = timeDiff / (60 * 1000) % 60;
				LOGGER.info("diffMinute:"+diffMinutes);
				System.out.println("diffMinute:"+diffMinutes);
				int blockingTimePeriod = Integer.parseInt(environment.getProperty(SECURITY_CODE_EXPIRY_TIME));
				
				//if (diffMinutes >= securityCodeTimeout && subscriberData.getIpAddress().equals(details.getIpAddress()) && subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
				if (diffMinutes <= blockingTimePeriod &&  subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_FAILED_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(subscriber.getOtpCount()+1);
					}
					else{
						subscriber.setOtpCount(subscriber.getOtpCount()+1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}
				else if(diffMinutes >= blockingTimePeriod &&  subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_FAILED_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(1);
					}
					else{
						subscriber.setOtpCount(1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}			
				else if(diffMinutes >= blockingTimePeriod &&  subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_FAILED_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(1);
					}
					else{
						subscriber.setOtpCount(1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}
				else if(diffMinutes < blockingTimePeriod &&  subscriber.getOtpCount()==null ) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_FAILED_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(1);
					}
					else{
						subscriber.setOtpCount(1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}
				else{
					throw new SubscriberException(SubscriberExceptionCodes.MAXIMUM_LIMIS_REACHED);
				}
				
						
			
			
			}
			else{
				
				//If sim validation success
				throw new SubscriberException(SubscriberExceptionCodes.NOT_APPROVED_SUBSCRIBER);
			}
		}
		
		//If FInal Status Is Approved
		else if(details.getFinalStatus().equals("APPROVED")){
			Subscriber subscriber = subscriberDao.getSubscriberBySubscriberId(details.getSubscriber().getSubscriberId());
			if (subscriber == null) {
				throw new SubscriberException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS);
			}
			else if(!details.getFinalStatus().equals("APPROVED")){
				throw new SubscriberException(SubscriberExceptionCodes.NOT_APPROVED_SUBSCRIBER);
			}
			else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date security_code_timeStamp = new Date(timestamp.getTime());
				LOGGER.info("security_code_timeStamp" +security_code_timeStamp);				
				Date otpTimeStamp=null;
				if(subscriber.getOtpCreationTime()==null){
					Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
					otpTimeStamp= new Date(timestamp1.getTime());
				}
				else{
					otpTimeStamp= new Date(subscriber.getOtpCreationTime().getTime());
				}
				long timeDiff = security_code_timeStamp.getTime() - otpTimeStamp.getTime();				
				long diffMinutes = timeDiff / (60 * 1000) % 60;
				LOGGER.info("diffMinute:"+diffMinutes);
				System.out.println("diffMinute:"+diffMinutes);
				int blockingTimePeriod = Integer.parseInt(environment.getProperty(SECURITY_CODE_EXPIRY_TIME));
				
				//if (diffMinutes >= securityCodeTimeout && subscriberData.getIpAddress().equals(details.getIpAddress()) && subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
				if (diffMinutes <= blockingTimePeriod &&  subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(subscriber.getMsisdn());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(subscriber.getOtpCount()+1);
					}
					else{
						subscriber.setOtpCount(subscriber.getOtpCount()+1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}
				else if(diffMinutes >= blockingTimePeriod &&  subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(subscriber.getMsisdn());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(1);
					}
					else{
						subscriber.setOtpCount(1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}			
				else if(diffMinutes >= blockingTimePeriod &&  subscriber.getOtpCount()!=null &&  subscriber.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(subscriber.getMsisdn());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(1);
					}
					else{
						subscriber.setOtpCount(1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}
				else if(diffMinutes < blockingTimePeriod &&  subscriber.getOtpCount()==null ) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(subscriber.getMsisdn());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_SUBSCRIBER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", subscriber.getMsisdn()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					System.out.println("subscriber otp:"+otp);
					LOGGER.info("subscriber otp:"+otp);
					subscriber.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					subscriber.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(subscriber.getOtpCount()==null || subscriber.getOtpCount()==0){
						subscriber.setOtpCount(1);
					}
					else{
						subscriber.setOtpCount(1);
					}
					subscriberDao.saveOrUpdate(subscriber);
				}
				else{
					throw new SubscriberException(SubscriberExceptionCodes.MAXIMUM_LIMIS_REACHED);
				}
				
				
			}

			//return subscriberDto;
		}
		else {
			
			throw new SubscriberException(SubscriberExceptionCodes.NOT_APPROVED_SUBSCRIBER);
		}
		return subscriberDto;
		
	}

	@Override
	public SubscriberDto validateOtp(SubscriberDto subscriberData) throws BusinessException, SubscriberException {
		SubscriberDto subscriberDto = new SubscriberDto();		 
		 Subscriber subscriber = subscriberDao.getSubscriberObj(subscriberData.getMsisdn());
		if (subscriber == null) {
			throw new SubscriberException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS);
		}
		else {
			if (subscriber.getOtp().equalsIgnoreCase(DigestUtils.md5DigestAsHex(subscriberData.getOtp().getBytes())))
				
			{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Date security_code_timeStamp = new Date(timestamp.getTime());
			LOGGER.info("security_code_timeStamp" +security_code_timeStamp);				
			Date otpTimeStamp= new Date(subscriber.getOtpCreationTime().getTime());
			long timeDiff = security_code_timeStamp.getTime() - otpTimeStamp.getTime();				
			long diffMinutes = timeDiff / (60 * 1000) % 60;
			LOGGER.info("diffMinute:"+diffMinutes);
			int securityCodeTimeout = Integer.parseInt(environment.getProperty(SECURITY_CODE_EXPIRY_TIME));
			if (diffMinutes <= securityCodeTimeout) {
				subscriberDto.setMessage(environment.getProperty(SECURITY_CODE_VALIDATED));
				StringBuilder encString=new StringBuilder();
				encString.append(subscriberData.getMsisdn());
				encString.append(subscriberData.getOtp());
				String finalString=encString.toString();
				subscriberDto.setMsisdn(aesUtil.encrypt(finalString));
				
				//invalidating otp for 2nd time
				subscriber.setOtp(null);
				subscriber.setOtpCreationTime(null);
				subscriber.setOtpCount(0);
				subscriberDao.update(subscriber);
			} else {
				subscriberDto.setMessage(environment.getProperty(SECURITY_CODE_EXPIRED));
			}
			
			}
			else{
				subscriberDto.setMessage(environment.getProperty(SECURITY_CODE_INVALID));
			}
		
		}

		return subscriberDto;
	}

	@Override
	public SubscriberSearchResponseDto getApprovedSubscribers(SearchSubscriberDto searchSubscriberDto,Integer isOldData, String finalStatus) {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = new ArrayList<>();
		//searchSubscriberDto.setIsOldSubscriber(isOldData);
		
		SubscriberDetails details=subscriberDetailsDao.getSubscriberDetailsByMsisdn(searchSubscriberDto.getMsisdn(), isOldData,KycConstants.FINAL_STATUS);
		if(details!=null && details.getFinalStatus().equals(KycConstants.FINAL_STATUS)){
		//Subscriber subscriberObj = subscriberDao.getSubscriberObj(searchSubscriberDto.getMsisdn());
	    Subscriber subscriberObj = subscriberDao.getSubscriberBySubscriberId(details.getSubscriber().getSubscriberId());
		
		
		List<SubscriberDetails> subscriberDetailsObjList = new ArrayList<>();
		for (SubscriberDetails subscriberDetails : subscriberObj.getSubscriberDetails()) {
			if(subscriberDetails.getIsOldUserDetails()==0){
				SubscriberDetails subscriberDetailsObj=new SubscriberDetails();
				BeanUtils.copyProperties(subscriberDetails, subscriberDetailsObj);	
				subscriberDetailsObjList.add(subscriberDetailsObj);
			}
			
		}
		subscriberObj.setSubscriberDetails(subscriberDetailsObjList);
		
		subscriberList.add(subscriberObj);
		for (Subscriber subscriber : subscriberList) {
			SubscriberDto subscriberDto = new SubscriberDto();
			BeanUtils.copyProperties(subscriber, subscriberDto);
			List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<>();
			try {
				subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,KycConstants.FALSE, false);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
			subscriberDtoList.add(subscriberDto);
		}
		
		
		long totalCount = 0;
		try {
			totalCount = this.kycDaoService.getTotalSubscriber(searchSubscriberDto);
		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
		subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
		subscriberSearchResponseDto.setTotalRecord(totalCount);
		subscriberSearchResponseDto.setRows(subscriberDtoList);
		}
		return subscriberSearchResponseDto;
	}

	@Override
	public SubscriberDto getSubscriberData(String msisdn) throws BusinessException {
		SubscriberDto subscriberDto=new SubscriberDto();
		Subscriber subscriberObj = subscriberDao.getSubscriberObject(msisdn, KycConstants.FALSE);
		BeanUtils.copyProperties(subscriberObj, subscriberDto);
		return subscriberDto;
	}
	
	@Override
	public BaseDto addExistingSubscriber(SubscriberDto subscriberDto, UsersDto usersDto,SubscriberUserDto subscriberUserDto, String amAccount, String isMinor, String latitude, String longitude, String cellId,String occupation,String minorName,String caseType,String proxyForRegistration,String submitedOn,String syncedOn,String onlineOfflineFlag,String registeredOn,String idImageBackData,String simSerialNo,String ipAddress,String handsetImei,String placeOfBirth) throws BusinessException, UserException {
		
		//Subscriber subscriber=subscriberDao.getSubscriberObj(subscriberDto.getMsisdn());
		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		Subscriber subscriber = new Subscriber();
		String[] ignoreProperties = {subscriberDto.getSubscriberId().toString()};
		BeanUtils.copyProperties(subscriberDto, subscriber,ignoreProperties );
		
		double randomNo=Math.floor((Math.random()*100000000)+1);
		String kycTansactionId="kyc" + randomNo + "" + new Date().getTime();
		
		Boolean idFrontImageSaveStatus = false;		
		//Boolean subscriberImageIdSaveStatus = false;
		Boolean idBackImageSaveStatus = false;
		Boolean physicalFormIdSaveStatus = false;
		List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
		List<SubscriberDetails> subscriberDetailsList = new ArrayList<SubscriberDetails>();
		List<SubscriberDetails> subscriberDetailsObjList = subscriberDetailsDao.getSubscriberDetails(subscriberUserDto.getSubscriberData().getSubscriberId(), KycConstants.FALSE);
		//subscriberDetailsList = new ArrayList<SubscriberDetails>(subscriberDetailsObjList);
		for (SubscriberDetails subscriberDetails : subscriberDetailsObjList) {
			SubscriberDetailsDto subscriberDetailsDto=new SubscriberDetailsDto();
			
			Address address = subscriberDetails.getAddress();
			VillageDto villageDto=new VillageDto();
			villageDto.setVillageId(subscriberDetails.getAddress().getVillageId());
			
			DistrictDto districtDto=new DistrictDto();
			districtDto.setDistrictId(subscriberDetails.getAddress().getDistrictId());
			AddressDto addressDto=new AddressDto();
			String[] addressIgnoreProperties = {address.getAddressId().toString()};
			BeanUtils.copyProperties(address, addressDto,addressIgnoreProperties);
			addressDto.setDistrict(districtDto);
			addressDto.setVillage(villageDto);
			
			addressDto.setCountryId(subscriberDetails.getAddress().getCountryId());
			
			
			Set<SubscriberIdDetails> subscriberIdDetails=subscriberDetails.getSubscriberIdDetails();
			Set<SubscriberIdDetailsDto> subscriberIdDetailsDto=new HashSet<>();
			String idNo=null;
			String[] subscriberIdDetailsIgnoreProperties = {idNo};
			BeanUtils.copyProperties(subscriberIdDetails, subscriberIdDetailsDto,subscriberIdDetailsIgnoreProperties);
			
			//String[] subscriberDetailsIgnoreProperties = {subscriberDetailsDto.getSubscriberDetailsId().toString()};
			BeanUtils.copyProperties(subscriberDetails, subscriberDetailsDto);
			subscriberDetailsDto.setAlternateNo(subscriberDetails.getAlternateNo());
			subscriberDetailsDto.setIsMinor(String.valueOf(subscriberDetails.getIsMinor()));
			subscriberDetailsDto.setAmAccount(String.valueOf(subscriberDetails.getAmAccount()));
			subscriberDetailsDto.setDateOfBirth(subscriberDetails.getDateOfBirth().toString());
			/*byte [] fingerPrint=Base64.decodeBase64(subscriberDetailsDto.getFingerPrint());
			try {
				subscriberDetails.setFingerPrint(new javax.sql.rowset.serial.SerialBlob(fingerPrint));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			subscriberDetailsDto.setAddress(addressDto);
			subscriberDetailsDto.setSubscriberIdDetails(subscriberIdDetailsDto);
			subscriberDetailsDtoList.add(subscriberDetailsDto);
			
		}
		List<SubscriberDocumentsDto> documentsDtos = null;

		Boolean saveresult = false;
		String timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		String destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ kycTansactionId + "/";

		for (SubscriberDetailsDto subscriberDetailsDto : subscriberDetailsDtoList) {
			SubscriberDetails subscriberDetails = new SubscriberDetails();
					
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			BeanUtils.copyProperties(subscriberDetailsDto, subscriberDetails);
			subscriberDetails.setPermissableRadius(environment.getProperty(PERMISSABLE_RADIUS));
			subscriberDetails.setPostalAddress(addressDto.getPostalCode());
			subscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(), "dd-MM-yyyy"));
					
				Address address = new Address();
				BeanUtils.copyProperties(subscriberDetailsDto.getAddress(), address);
				
				if (addressDto.getDistrict() != null)
					address.setDistrictId(addressDto.getDistrict().getDistrictId());
				    address.setAddressId(null);
				if (addressDto.getVillage() != null)
				    address.setVillageId(addressDto.getVillage().getVillageId());
				address.setCountryId(addressDto.getCountryId());
				subscriberDetails.setAddress(address);
			

			Set<SubscriberIdDetails> subscriberIdDetails = new HashSet<>();
			Set<SubscriberIdDetailsDto> subscriberIdDetailsDtoSet=new HashSet<>();
			SubscriberIdDetails subscriberIdDetailsObj=subscriberIdDetailsDao.getSubscriberIdDetailsObj(subscriberDetailsDto.getSubscriberDetailsId(), KycConstants.FALSE);
			subscriberIdDetails.add(subscriberIdDetailsObj);
			for (SubscriberIdDetails subscriberIdDetailsSet : subscriberIdDetails) {
				SubscriberIdDetailsDto subscriberIdDetailsDto=new SubscriberIdDetailsDto();
				BeanUtils.copyProperties(subscriberIdDetailsSet, subscriberIdDetailsDto);
				subscriberIdDetailsDtoSet.add(subscriberIdDetailsDto);
			}
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)				 
				
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberIdDetailsDtoSet) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageFrontDate = subscriberDto.getIdImageFrontData();			
					String idImageBackDate = idImageBackData;
					String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					//String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					//String subscriberImage ="subscriberImage";
					String physicalFormId=subscriberDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					
					 /*String subImageBase64 = null;
					if(subscriberIdDetailsDto.getIdPath()!=null && subscriberIdDetailsDto.getSubscriberImageId()!=null){
						RestTemplate restTemplate = new RestTemplate();	
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
						String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
					    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdPath())
					    		.path("/")
					    		.path(subscriberDto.getKycTansactionId())
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdImageFront())
					    		.path(".jpg")
					    	    .build()
					    	    .toUri();
					    System.out.println("targetUrl:"+targetUrl);		
					    HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
					    //System.out.println("body:"+response.getBody());
					    byte[] body = response.getBody();
					    
					    System.out.println("body:"+body);					 
					   
						try {
							subImageBase64 = new String(Base64.encodeBase64(body), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
					
					}
					String subscriberImageId = subImageBase64;  
					*/
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(subscriberDto.getIdImageFrontData(),
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}

					
					/*if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberImageId,
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}*/

					if (idImageBackDate != null && idImageBackDate.length() > 0) {
						idBackImageSaveStatus = Utility.saveBase64ToFile(idImageBackDate,
								destfilepath, idImageBack + ".jpg");
						subscriberIdDetail.setIdImageBack(idImageBack);
					}
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(subscriberDto.getPhysicalFormId(),
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !idBackImageSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(subscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					subscriberIdDetail.setIsOldIdDetails(KycConstants.FALSE);
					subscriberIdDetail.setIdNo(null);

					// add in subscriberIdDetails arraylist
					subscriberIdDetails.add(subscriberIdDetail);
				}

			// Set Douments
			documentsDtos = subscriberDetailsDto.getSubscriberDocuments();
			if (documentsDtos != null) {
				List<SubscriberDocuments> documents = new ArrayList<>();
				String documentFilePath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
						+ environment.getProperty(ConfigConstants.SUBSCRIBER_DOCUMENTS) + subscriberDto.getKycTansactionId() + "/";
				for (SubscriberDocumentsDto documentsDto : documentsDtos) {
					SubscriberDocuments document = new SubscriberDocuments();
					BeanUtils.copyProperties(documentsDto, document);

					// Save Image
					if (documentsDto.getDocumentImageData() != null
							&& documentsDto.getDocumentImageData().length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(documentsDto.getDocumentImageData(),
								destfilepath, documentsDto.getDocumentName() + ".jpg");

					}
					document.setDocumentImage(documentFilePath);
					document.setSubscriberDetails(subscriberDetails);
					documents.add(document);
				}
				subscriberDetails.setSubscriberDocuments(documents);
			}
			subscriberDetails.setSubscriberIdDetails(subscriberIdDetails);

			/*Set<SubscriberImageDetail> subscriberImageDetails = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberImageDetail() != null)
				for (SubscriberImageDetailDto subscriberImageDetailDto : subscriberDetailsDto
						.getSubscriberImageDetail()) {
					SubscriberImageDetail subscriberImageDetail = new SubscriberImageDetail();
					BeanUtils.copyProperties(subscriberImageDetailDto, subscriberImageDetail);
					subscriberImageDetail.setSubscriberDetails(subscriberDetails);

					subscriberImageDetail.setImagePath(timestr);

					saveresult = Utility.saveBase64ToFile(subscriberImageDetailDto.getImageStr(), destfilepath,
							subscriberImageDetailDto.getImageType() + "." + subscriberImageDetailDto.getImageFormat());

					if (!saveresult) {
						
						break;
					}

					subscriberImageDetail.setIsOldImageDetails(KycConstants.FALSE);
					subscriberImageDetails.add(subscriberImageDetail);
				}*/
			//subscriberDetails.setSubscriberImageDetail(subscriberImageDetails);
			subscriberDetails.setSubscriber(subscriber);
			subscriberDetails.setStatusFlag(KycConstants.TRUE);
			subscriberDetails.setDeleteFlag(KycConstants.FALSE);
			subscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
			subscriberDetails.setSubscriberDetailsId(null);
			
			subscriberDetails.setFinalStatusReason(null);
			subscriberDetails.setActivationTime(null);
			subscriberDetails.setAmAccount(null);
			subscriberDetails.setEmaActivationTime(null);
			subscriberDetails.setPartialActivationTime(null);
			subscriberDetails.setSvActivationTime(null);
			subscriberDetails.setWorkFlowReason(null);
			
			subscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			
			
			subscriberDetails.setPhysicalFormStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setIsPhysicalFormReceived(0);
			subscriberDetails.setMsisdn(subscriberDto.getNewMsisdn());
			subscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());			
			subscriberDetails.setIsMinor(Integer.parseInt(subscriberDetailsDto.getIsMinor()));
			subscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			subscriberDetails.setPlaceOfBirth(placeOfBirth);
			
			if(amAccount!=null && amAccount.equals("Yes")){
				subscriberDetails.setAmAccount(1);
			}
			else{
				subscriberDetails.setAmAccount(0);
			}
			if(isMinor!=null && isMinor.equals("Yes")){
				subscriberDetails.setIsMinor(1);
			}
			else{
				subscriberDetails.setIsMinor(0);
			}
			
			if(latitude!=null){
				subscriberDetails.setLatitude(latitude);
			}
			
			if(longitude!=null){
				subscriberDetails.setLongitude(longitude);
			}
			if(cellId!=null){
				subscriberDetails.setChannelPartnerCellId(cellId);
			}
			
			if(occupation!=null){
				subscriberDetails.setOccupation(occupation);
			}
			if(minorName!=null){
				subscriberDetails.setMinorName(minorName);
			}
			if(caseType!=null){
				subscriberDetails.setCaseType(caseType);	
			}
			
			if(simSerialNo!=null){
				subscriberDetails.setSimSerialNumber(simSerialNo);	
			}
			subscriberDetails.setIsImageEdited("NO");
			subscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(), "dd-MM-yyyy"));
			subscriberDetails.setUpdatedOn(timestamp);
			subscriberDetails.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			
			
			subscriberDetails.setBulkSubscriberFlag(0);
			subscriberDetails.setIpAddress(ipAddress);
			subscriberDetails.setImei(handsetImei);
			
			//subscriberDetails.setCountryId(subscriberDto.getCountryId());
			if(proxyForRegistration.equals("Yes")||proxyForRegistration=="1"){
				subscriberDetails.setProxyForRegistration(1);
			}
			if(proxyForRegistration.equals("No")||proxyForRegistration=="0"){
				subscriberDetails.setProxyForRegistration(0);
			}		
			
			
			if(submitedOn!=null){
				subscriberDetails.setSubmitedOn(new Timestamp(Long.valueOf(submitedOn)));
			}
			if(syncedOn!=null){
				subscriberDetails.setSyncedOn(new Timestamp(Long.valueOf(syncedOn)));
			}
			
			if(registeredOn!=null){
				subscriberDetails.setRegisteredOn(new Timestamp(Long.valueOf(registeredOn)));
			}
			if(onlineOfflineFlag!=null)
			{
				subscriberDetails.setOnlineOfflineFlag(onlineOfflineFlag);
			}			
			
			
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriberDetails.setCreatedBy(auth.getName());
			}
			
			subscriberDetailsList.add(subscriberDetails);

		}

		
		subscriber.setSubscriberDetails(subscriberDetailsList);
		subscriber.setStatusFlag(KycConstants.TRUE);
		subscriber.setDeleteFlag(KycConstants.FALSE);
		subscriber.setMsisdn(subscriberDto.getNewMsisdn());
		subscriber.setSubscriberId(null);		
	
		
		subscriber.setKycTansactionId(kycTansactionId);
				
		//Added for user creation
		if(subscriberUserDto.getUserFlag()==0 || subscriberUserDto.getUserFlag()==null ){
			subscriber.setUserFlag(KycConstants.FALSE);
		}

		if (subscriber.getSubscriberId() != null) {
			subscriber.setUpdatedOn(timestamp);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setUpdatedBy(auth.getName());
			}
		} else {
			subscriber.setDeleteFlag(KycConstants.FALSE);
			subscriber.setStatusFlag(KycConstants.TRUE);
			subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));	
			subscriber.setUpdatedOn(timestamp);
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setCreatedBy(auth.getName());
			}
		}
		try {
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			if (subscriber == null) {
				throw new BusinessException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionCode(),
						SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionDescription());
			}
			subscriberDto.setSubscriberId(subscriber.getSubscriberId());
			if(isDebugEnabled) {
				log.debug("Subscriber created with subscriber id : "+subscriber.getSubscriberId());
			}
			/*if(subscriberUserDto.getUserFlag()==1 && usersDto.getUserDetail().getMsisdn()!=null ){
				provisionUser(usersDto);
			}*/
			
			if(subscriberDto.getLocation() != null) {
				boolean locationUpated = this.helperDataManagementService.updateLocation(subscriberDto.getLocation() , null, subscriber.getSubscriberId());
				if(isDebugEnabled) {
					log.debug("Subscriber Location Updated : "+locationUpated);
				}
			}
		} catch (KycDaoException cause) {
			if(isDebugEnabled) {
				log.debug("Exception occured while adding subscriber ");
			}
			throw new BusinessException(cause);
		}
		return subscriberDto;

	
	}

	@Override
	public SubscriberDto addExistingSubscriberWithNewdata(SubscriberDto subscriberDto, UsersDto usersDto,SubscriberUserDto subscriberUserDto ,String caseType, String newMsisdn,String proxyForRegistration,String submitedOn,String syncedOn,String onlineOfflineFlag,String registeredOn,String physicalFormData,String ipAddress,String handsetImei,String placeOfBirth)throws BusinessException, UserException { {

		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		//Subscriber subscriber=subscriberDao.getSubscriberObj(subscriberDto.getMsisdn());
		double randomNo=Math.floor((Math.random()*100000000)+1);
		String kycTansactionId="kyc" + randomNo + "" + new Date().getTime();
		Subscriber subscriber = new Subscriber();
		String[] ignoreProperties = {subscriberDto.getSubscriberId().toString()};
		BeanUtils.copyProperties(subscriberDto, subscriber,ignoreProperties );
		Boolean idFrontImageSaveStatus = false;		
		//Boolean subscriberImageIdSaveStatus = false;
		Boolean idBackImageSaveStatus = false;
		Boolean physicalFormIdSaveStatus = false;
		List<SubscriberDetailsDto> subscriberDetailsDtoList = new ArrayList<SubscriberDetailsDto>();
		List<SubscriberDetails> subscriberDetailsList = new ArrayList<SubscriberDetails>();
		List<SubscriberDetails> subscriberDetailsObjList = subscriberDetailsDao.getSubscriberDetails(subscriberUserDto.getSubscriberData().getSubscriberId(), KycConstants.FALSE);
		//subscriberDetailsList = new ArrayList<SubscriberDetails>(subscriberDetailsObjList);
		for (SubscriberDetails subscriberDetails : subscriberDetailsObjList) {
			SubscriberDetailsDto subscriberDetailsDto=new SubscriberDetailsDto();
			
			Address address = subscriberDetails.getAddress();
			VillageDto villageDto=new VillageDto();
			villageDto.setVillageId(subscriberDetails.getAddress().getVillageId());
			DistrictDto districtDto=new DistrictDto();
			districtDto.setDistrictId(subscriberDetails.getAddress().getDistrictId());
			AddressDto addressDto=new AddressDto();
			String[] addressIgnoreProperties = {address.getAddressId().toString()};
			BeanUtils.copyProperties(address, addressDto,addressIgnoreProperties);
			addressDto.setDistrict(districtDto);
			addressDto.setVillage(villageDto);
			
			addressDto.setCountryId(subscriberDetails.getAddress().getCountryId());
			
			Set<SubscriberIdDetails> subscriberIdDetails=subscriberDetails.getSubscriberIdDetails();
			Set<SubscriberIdDetailsDto> subscriberIdDetailsDto=new HashSet<>();
			String idNo=null;
			String[] subscriberIdDetailsIgnoreProperties = {idNo};
			BeanUtils.copyProperties(subscriberIdDetails, subscriberIdDetailsDto,subscriberIdDetailsIgnoreProperties);
			
			//String[] subscriberDetailsIgnoreProperties = {subscriberDetailsDto.getIsLatest().toString()};
			BeanUtils.copyProperties(subscriberDetails, subscriberDetailsDto);
			subscriberDetailsDto.setAlternateNo(subscriberDetails.getAlternateNo());
			subscriberDetailsDto.setIsMinor(String.valueOf(subscriberDetails.getIsMinor()));
			subscriberDetailsDto.setAmAccount(String.valueOf(subscriberDetails.getAmAccount()));
			subscriberDetailsDto.setDateOfBirth(subscriberDetails.getDateOfBirth().toString());
			/*byte [] fingerPrint=Base64.decodeBase64(subscriberDetailsDto.getFingerPrint());
			try {
				subscriberDetails.setFingerPrint(new javax.sql.rowset.serial.SerialBlob(fingerPrint));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
			subscriberDetailsDto.setAddress(addressDto);
			subscriberDetailsDto.setSubscriberIdDetails(subscriberIdDetailsDto);
			subscriberDetailsDtoList.add(subscriberDetailsDto);
			
		}
		List<SubscriberDocumentsDto> documentsDtos = null;

		Boolean saveresult = false;
		String timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		String destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ kycTansactionId + "/";

		for (SubscriberDetailsDto subscriberDetailsDto : subscriberDetailsDtoList) {
			SubscriberDetails subscriberDetails = new SubscriberDetails();
					
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			BeanUtils.copyProperties(subscriberDetailsDto, subscriberDetails);
			subscriberDetails.setPermissableRadius(environment.getProperty(PERMISSABLE_RADIUS));
			subscriberDetails.setPostalAddress(addressDto.getPostalCode());
			subscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(), "dd-MM-yyyy"));
			
			if(subscriberDetailsDto.getOccupation()!=null){
				subscriberDetails.setOccupation(subscriberDetailsDto.getOccupation());
			}
			
				Address address = new Address();
				BeanUtils.copyProperties(subscriberDetailsDto.getAddress(), address);
				
				if (addressDto.getDistrict() != null)
					address.setDistrictId(addressDto.getDistrict().getDistrictId());
				    address.setAddressId(null);
				    
				if (addressDto.getVillage() != null)
					address.setVillageId(addressDto.getVillage().getVillageId());
				address.setCountryId(addressDto.getCountryId());
				subscriberDetails.setAddress(address);
			

			Set<SubscriberIdDetails> subscriberIdDetails = new HashSet<>();
			Set<SubscriberIdDetailsDto> subscriberIdDetailsDtoSet=new HashSet<>();
			SubscriberIdDetails subscriberIdDetailsObj=subscriberIdDetailsDao.getSubscriberIdDetailsObj(subscriberDetailsDto.getSubscriberDetailsId(), KycConstants.FALSE);
			subscriberIdDetails.add(subscriberIdDetailsObj);
			for (SubscriberIdDetails subscriberIdDetailsSet : subscriberIdDetails) {
				SubscriberIdDetailsDto subscriberIdDetailsDto=new SubscriberIdDetailsDto();
				String[] ignorePropertiesId = {subscriberIdDetailsSet.getIdNo().toString()};				
				BeanUtils.copyProperties(subscriberIdDetailsSet, subscriberIdDetailsDto,ignorePropertiesId);
				subscriberIdDetailsDtoSet.add(subscriberIdDetailsDto);
			}
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)	{			 
				
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberIdDetailsDtoSet) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageBackDate = subscriberIdDetailsDto.getIdImageBackData();
					String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					subscriberIdDetail.setIdNo(null);
					
					//String idImageFrontDate = subscriberDto.getIdImageFrontData();				
					//String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					//String subscriberImage ="subscriberImage";
					//String physicalFormId=subscriberDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					String physicalFormId=physicalFormData;
					
					
					
					String idImageBase64 = null;
					if(subscriberIdDetailsDto.getIdPath()!=null && subscriberIdDetailsDto.getIdImageFront()!=null){
						RestTemplate restTemplate = new RestTemplate();	
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
						String url=environment.getProperty(ConfigConstants.BASEIMAGE_URL);
					    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdPath())
					    		.path("/")
					    		.path(subscriberDto.getKycTansactionId())
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdImageFront())
					    		.path(".jpg")
					    	    .build()
					    	    .toUri();
					    System.out.println("targetUrl:"+targetUrl);		
					    HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
					    //System.out.println("body:"+response.getBody());
					    byte[] body = response.getBody();
					    
					    System.out.println("body:"+body);					 
					   
						try {
							idImageBase64 = new String(Base64.encodeBase64(body), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
					
					}
					String idImageFrontDate = idImageBase64;

					
					String idBackImageBase64 = null;
					if(subscriberIdDetailsDto.getIdPath()!=null && subscriberIdDetailsDto.getIdImageBack()!=null){
						RestTemplate restTemplate = new RestTemplate();	
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
						String url=environment.getProperty(ConfigConstants.BASEIMAGE_URL);
					    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdPath())
					    		.path("/")
					    		.path(subscriberDto.getKycTansactionId())
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdImageBack())
					    		.path(".jpg")
					    	    .build()
					    	    .toUri();
					    System.out.println("targetUrl:"+targetUrl);		
					    HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
					    //System.out.println("body:"+response.getBody());
					    byte[] body = response.getBody();
					    
					    System.out.println("body:"+body);					 
					   
						try {
							idBackImageBase64 = new String(Base64.encodeBase64(body), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
					
					}
					String idImageBackData = idBackImageBase64;
					
					
					
					/*String physicalFormBase64 = null;
					if(subscriberIdDetailsDto.getIdPath()!=null && subscriberIdDetailsDto.getPhysicalFormId()!=null){
						RestTemplate restTemplate = new RestTemplate();	
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
						String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
					    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdPath())
					    		.path("/")
					    		.path(subscriberDto.getKycTansactionId())
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getPhysicalFormId())
					    		.path(".jpg")
					    	    .build()
					    	    .toUri();
					    System.out.println("targetUrl:"+targetUrl);		
					    HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
					    //System.out.println("body:"+response.getBody());
					    byte[] body = response.getBody();
					    
					    System.out.println("body:"+body);					 
					   
						try {
							physicalFormBase64 = new String(Base64.encodeBase64(body), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
					
					}
					
					
					String physicalFormId=physicalFormBase64;*/
					
					
					 /*String subImageBase64 = null;
					if(subscriberIdDetailsDto.getIdPath()!=null && subscriberIdDetailsDto.getSubscriberImageId()!=null){
						RestTemplate restTemplate = new RestTemplate();	
						//http://localhost:8081/kycug/api/subscriber/getSubscriberImage/KYC1/2017/5/30/1496141082566/kyc163030811496141081244/subscriberImage.jpg
						String url=messageSource.getMessage(ConfigConstants.BASEIMAGE_URL, null, Locale.getDefault());
					    URI  targetUrl= UriComponentsBuilder.fromUriString(url)
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdPath())
					    		.path("/")
					    		.path(subscriberDto.getKycTansactionId())
					    		.path("/")
					    		.path(subscriberIdDetailsDto.getIdImageFront())
					    		.path(".jpg")
					    	    .build()
					    	    .toUri();
					    System.out.println("targetUrl:"+targetUrl);		
					    HttpEntity<byte[]> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, byte[].class);
					    //System.out.println("body:"+response.getBody());
					    byte[] body = response.getBody();
					    
					    System.out.println("body:"+body);					 
					   
						try {
							subImageBase64 = new String(Base64.encodeBase64(body), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
					
					}
					String subscriberImageId = subImageBase64;  
*/					
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(idImageFrontDate,
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}

					
					/*if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberImageId,
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}*/
					
					if (idImageBackData != null && idImageBackData.length() > 0) {
						idBackImageSaveStatus = Utility.saveBase64ToFile(idImageBackData,
								destfilepath, idImageBack + ".jpg");
						subscriberIdDetail.setIdImageBack(idImageBack);
					}
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(physicalFormId,
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !idBackImageSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(subscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					subscriberIdDetail.setIsOldIdDetails(KycConstants.FALSE);
					//subscriberIdDetail.setIdNo(null);

					// add in subscriberIdDetails arraylist
					System.out.println(subscriberIdDetail);
					
					subscriberIdDetails.add(subscriberIdDetail);
				}
			}
			// Set Douments
			documentsDtos = subscriberDetailsDto.getSubscriberDocuments();
			if (documentsDtos != null) {
				List<SubscriberDocuments> documents = new ArrayList<>();
				String documentFilePath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
						+ environment.getProperty(ConfigConstants.SUBSCRIBER_DOCUMENTS) + subscriberDto.getKycTansactionId() + "/";
				for (SubscriberDocumentsDto documentsDto : documentsDtos) {
					SubscriberDocuments document = new SubscriberDocuments();
					BeanUtils.copyProperties(documentsDto, document);

					// Save Image
					if (documentsDto.getDocumentImageData() != null
							&& documentsDto.getDocumentImageData().length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(documentsDto.getDocumentImageData(),
								destfilepath, documentsDto.getDocumentName() + ".jpg");

					}
					document.setDocumentImage(documentFilePath);
					document.setSubscriberDetails(subscriberDetails);
					documents.add(document);
				}
				subscriberDetails.setSubscriberDocuments(documents);
			}
			subscriberDetails.setSubscriberIdDetails(subscriberIdDetails);

			/*Set<SubscriberImageDetail> subscriberImageDetails = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberImageDetail() != null)
				for (SubscriberImageDetailDto subscriberImageDetailDto : subscriberDetailsDto
						.getSubscriberImageDetail()) {
					SubscriberImageDetail subscriberImageDetail = new SubscriberImageDetail();
					BeanUtils.copyProperties(subscriberImageDetailDto, subscriberImageDetail);
					subscriberImageDetail.setSubscriberDetails(subscriberDetails);

					subscriberImageDetail.setImagePath(timestr);

					saveresult = Utility.saveBase64ToFile(subscriberImageDetailDto.getImageStr(), destfilepath,
							subscriberImageDetailDto.getImageType() + "." + subscriberImageDetailDto.getImageFormat());

					if (!saveresult) {
						
						break;
					}

					subscriberImageDetail.setIsOldImageDetails(KycConstants.FALSE);
					subscriberImageDetails.add(subscriberImageDetail);
				}*/
			//subscriberDetails.setSubscriberImageDetail(subscriberImageDetails);
			subscriberDetails.setSubscriber(subscriber);
			subscriberDetails.setStatusFlag(KycConstants.TRUE);
			subscriberDetails.setDeleteFlag(KycConstants.FALSE);
			subscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
			subscriberDetails.setSubscriberDetailsId(null);
			
			
			if(caseType!=null){
				subscriberDetails.setCaseType(caseType);	
			}
			subscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setPhysicalFormStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setIsPhysicalFormReceived(0);
			subscriberDetails.setMsisdn(newMsisdn);
			subscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());
			
			if(subscriberDetailsDto.getMinorName()!=null){
				subscriberDetails.setMinorName(subscriberDetailsDto.getMinorName());
			}
			
			subscriberDetails.setIsImageEdited("NO");
			subscriberDetails.setFinalStatusReason(null);
			subscriberDetails.setActivationTime(null);
			subscriberDetails.setAmAccount(null);
			subscriberDetails.setEmaActivationTime(null);
			subscriberDetails.setPartialActivationTime(null);
			subscriberDetails.setSvActivationTime(null);
			subscriberDetails.setWorkFlowReason(null);
			subscriberDetails.setPlaceOfBirth(placeOfBirth);
			/*String am=subscriberUserDto.getSubscriberData().getSubscriberDetails().get(0).getAmAccount();
					
			System.out.println("am"+am);
			*/
			if(subscriberDetailsObjList.get(0).getAmAccount()!=0){
				subscriberDetails.setAmAccount(1);
			}
			else{
				subscriberDetails.setAmAccount(0);
			}
			if(subscriberDetailsObjList.get(0).getIsMinor()!=0){
				subscriberDetails.setIsMinor(1);
			}
			else{
				subscriberDetails.setIsMinor(0);
			}
			if(subscriberDetailsObjList.get(0).getOccupation()!=null){
				subscriberDetails.setOccupation(subscriberDetailsObjList.get(0).getOccupation());
			}
			
			subscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			subscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(), "dd-MM-yyyy"));
			subscriberDetails.setUpdatedOn(timestamp);
			subscriberDetails.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			
			subscriberDetails.setSimSerialNumber(subscriberDto.getSimSerialNumber());
			subscriberDetails.setBulkSubscriberFlag(0);
			subscriberDetails.setIpAddress(ipAddress);
			subscriberDetails.setImei(handsetImei);
			
			//subscriberDetails.setCountryId(subscriberDto.getCountryId());
			if(proxyForRegistration.equals("Yes")||proxyForRegistration=="1"){
				subscriberDetails.setProxyForRegistration(1);
			}
			if(proxyForRegistration.equals("No")||proxyForRegistration=="0"){
				subscriberDetails.setProxyForRegistration(0);
			}			
			if(submitedOn!=null){
				subscriberDetails.setSubmitedOn(new Timestamp(Long.valueOf(submitedOn)));
			}
			if(syncedOn!=null){
				subscriberDetails.setSyncedOn(new Timestamp(Long.valueOf(syncedOn)));
			}
			if(registeredOn!=null){
				subscriberDetails.setRegisteredOn(new Timestamp(Long.valueOf(subscriberDetailsDto.getRegisteredOn())));
			}
			
			
			if(onlineOfflineFlag!=null)
			{
				subscriberDetails.setOnlineOfflineFlag(onlineOfflineFlag);
			}			
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriberDetails.setCreatedBy(auth.getName());
			}
			
			subscriberDetailsList.add(subscriberDetails);

		}

		
		subscriber.setSubscriberDetails(subscriberDetailsList);
		subscriber.setStatusFlag(KycConstants.TRUE);
		subscriber.setDeleteFlag(KycConstants.FALSE);
		subscriber.setMsisdn(newMsisdn);
		subscriber.setSubscriberId(null);
		
		
		subscriber.setKycTansactionId(kycTansactionId);
		
		//Added for user creation
		if(subscriberUserDto.getUserFlag()==0 || subscriberUserDto.getUserFlag()==null ){
			subscriber.setUserFlag(KycConstants.FALSE);
		}

		if (subscriber.getSubscriberId() != null) {
			subscriber.setUpdatedOn(timestamp);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setUpdatedBy(auth.getName());
			}
		} else {
			subscriber.setDeleteFlag(KycConstants.FALSE);
			subscriber.setStatusFlag(KycConstants.TRUE);
			subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));	
			subscriber.setUpdatedOn(timestamp);
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setCreatedBy(auth.getName());
			}
		}
				
		try {
			
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			if (subscriber == null) {
				throw new BusinessException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionCode(),
						SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionDescription());
			}
			this.taskManagementService.addTask(TaskEnum.SIM_VALIDATION.getTaskCode(),
					subscriber.getSubscriberId(), subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId().toString());
			subscriberDto.setSubscriberId(subscriber.getSubscriberId());
			if(isDebugEnabled) {
				log.debug("Subscriber created with subscriber id : "+subscriber.getSubscriberId());
			}
			
			if(subscriberDto.getLocation() != null) {
				boolean locationUpated = this.helperDataManagementService.updateLocation(subscriberDto.getLocation() , null, subscriber.getSubscriberId());
				if(isDebugEnabled) {
					log.debug("Subscriber Location Updated : "+locationUpated);
				}
			}
		} catch (KycDaoException cause) {
			if(isDebugEnabled) {
				log.debug("Exception occured while adding subscriber ");
			}
			throw new BusinessException(cause);
		}
		return subscriberDto;

	
	
		
	}

	}

	@Override
	public void updateSubscriberForm(SubscriberDto subscriberDto) throws BusinessException {
		List<Data> subData=subscriberDto.getData();
		for (Data data : subData) {	
			SubscriberDetails subscriber=subscriberDetailsDao.getSubscriberDetails(data.getMsisdn(), KycConstants.FALSE,"REJECTED");
			//Subscriber subscriber=subscriberDao.getSubscriberObj(data.getMsisdn());
			if(data.getLocationCheck().equals("true")){
				subscriber.setIsPhysicalFormReceived(1);
			}
			else{
				subscriber.setIsPhysicalFormReceived(0);					
			}
			subscriber.setLocation(subscriberDto.getLocationText());
			subscriber.setPhysicalFormStatus(KycConstants.FINAL_STATUS);
			subscriber.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			//subscriberDao.saveOrUpdate(subscriber);
			subscriberDetailsDao.saveOrUpdate(subscriber);
			
		}		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public SubscriberSearchResponseDto getOldApprovedSubscriber(SearchSubscriberDto searchSubscriberDto)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		searchSubscriberDto.setIsByPassflag(getBypassStatus());
		try {
			subscriberList = (List<Subscriber>) this.kycDaoService.getNewApprovedSubscribers(searchSubscriberDto);

			for (Subscriber subscriber : subscriberList) {
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);

				List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
						KycConstants.FALSE, false);

				subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
				subscriberDto.setOperactionOnSubscriber(null);
				subscriberDto.setActionOnSubscriber(null);
				//subscriberDto.setSubscriberWorkFlow(null);

				subscriberDtoList.add(subscriberDto);
			}
			//long totalCount;

			//totalCount = this.kycDaoService.getTotalSubscriber(searchSubscriberDto, actionType);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			//subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;
	}

	@Override
	public SubscriberSearchResponseDto getNewApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDto> subscriberDtoList = new ArrayList<SubscriberDto>();
		List<Subscriber> subscriberList = null;
		searchSubscriberDto.setIsByPassflag(getBypassStatus());
		try {
			subscriberList = (List<Subscriber>) this.kycDaoService.getOldApprovedSubscriber(searchSubscriberDto);

			for (Subscriber subscriber : subscriberList) {
				SubscriberDto subscriberDto = new SubscriberDto();
				BeanUtils.copyProperties(subscriber, subscriberDto);

				List<SubscriberDetailsDto> subscriberDetailsDtoList = getSubscriberDetails(subscriberDto,
						KycConstants.FALSE, false);

				subscriberDto.setSubscriberDetails(subscriberDetailsDtoList);
				subscriberDto.setOperactionOnSubscriber(null);
				subscriberDto.setActionOnSubscriber(null);
				//subscriberDto.setSubscriberWorkFlow(null);
				subscriberDtoList.add(subscriberDto);
			}
			//long totalCount;

			//totalCount = this.kycDaoService.getTotalSubscriber(searchSubscriberDto, actionType);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());
			//subscriberSearchResponseDto.setTotalRecord(totalCount);
			subscriberSearchResponseDto.setRows(subscriberDtoList);

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return subscriberSearchResponseDto;

	}
	
	@Override
	public BaseDto addBulkSubscriber(SubscriberDto subscriberDto, UsersDto usersDto,SubscriberUserDto subscriberUserDto) throws BusinessException, UserException {
		
		final Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		Subscriber subscriber = new Subscriber();
		BeanUtils.copyProperties(subscriberDto, subscriber);
		Boolean idFrontImageSaveStatus = false;
		Boolean idBackImageSaveStatus = false;
		//Boolean subscriberImageIdSaveStatus = false;
		Boolean physicalFormIdSaveStatus = false;
		List<SubscriberDetails> subscriberDetailsList = new ArrayList<SubscriberDetails>();
		List<SubscriberDetailsDto> SubscriberDetailsDtoList = subscriberDto.getSubscriberDetails();
		List<SubscriberDocumentsDto> documentsDtos = null;

		Boolean saveresult = false;
		String timestr = environment.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		String destfilepath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
				+ subscriberDto.getKycTansactionId() + "/";

		for (SubscriberDetailsDto subscriberDetailsDto : SubscriberDetailsDtoList) {
			SubscriberDetails subscriberDetails = new SubscriberDetails();
			// BeanUtils.copyProperties(subscriberDto.getSubscriberDetails(),
			// subscriberDetails);
			AddressDto addressDto = subscriberDetailsDto.getAddress();
			BeanUtils.copyProperties(subscriberDetailsDto, subscriberDetails);
			subscriberDetails.setPermissableRadius(environment.getProperty(PERMISSABLE_RADIUS));
			subscriberDetails.setPostalAddress(addressDto.getPostalCode());
			subscriberDetails.setDateOfBirth(DateUtils.getDate(subscriberDetailsDto.getDateOfBirth(), "dd-MM-yyyy"));
			if(subscriberDetailsDto.getAmAccount().equals("Yes") ||subscriberDetailsDto.getAmAccount()=="1"){
				subscriberDetails.setAmAccount(1);
			}
			else if(subscriberDetailsDto.getAmAccount().equals("No") ||subscriberDetailsDto.getAmAccount()=="0"){
				subscriberDetails.setAmAccount(0);
			}
			if(subscriberDetailsDto.getIsMinor().equals("Yes")||subscriberDetailsDto.getIsMinor()=="1"){
				subscriberDetails.setIsMinor(1);
			}
			if(subscriberDetailsDto.getIsMinor().equals("No")||subscriberDetailsDto.getIsMinor()=="0"){
				subscriberDetails.setIsMinor(0);
			}
			
			if(subscriberDetailsDto.getOccupation()!=null){
				subscriberDetails.setOccupation(subscriberDetailsDto.getOccupation());
			}
			
			if(subscriberDetailsDto.getMinorName()!=null){
				subscriberDetails.setMinorName(subscriberDetailsDto.getMinorName());
			}
			if(subscriberDetailsDto.getSubscriberType()!=null){				
				subscriberDetails.setSubscriberType(subscriberDetailsDto.getSubscriberType());
			}
			
			if (addressDto != null) {
				Address address = new Address();
				BeanUtils.copyProperties(subscriberDetailsDto.getAddress(), address);
				if (addressDto.getCountry() != null)
					address.setCountryId(addressDto.getCountry().getCountryId());
				if (addressDto.getRegion() != null)
					address.setRegionId(addressDto.getRegion().getRegionId());
				if (addressDto.getDistrict() != null)
					address.setDistrictId(addressDto.getDistrict().getDistrictId());
				if (addressDto.getVillage() != null)
					address.setVillageId(addressDto.getVillage().getVillageId());
				
				subscriberDetails.setAddress(address);
			}

			Set<SubscriberIdDetails> subscriberIdDetails = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberIdDetails() != null)
				for (SubscriberIdDetailsDto subscriberIdDetailsDto : subscriberDetailsDto.getSubscriberIdDetails()) {

					SubscriberIdDetails subscriberIdDetail = new SubscriberIdDetails();
					BeanUtils.copyProperties(subscriberIdDetailsDto, subscriberIdDetail);
					String idImageFront = subscriberIdDetailsDto.getIdImageFront();
					String idImageFrontDate = subscriberIdDetailsDto.getIdImageFrontData();
					String idImageBackDate = subscriberIdDetailsDto.getIdImageBackData();
					String idImageBack = subscriberIdDetailsDto.getIdImageBack();
					//String subscriberImageId = subscriberIdDetailsDto.getSubscriberImageId();
					//String subscriberImage ="subscriberImage";
					String physicalFormId=subscriberIdDetailsDto.getPhysicalFormId();
					String physicalForm="physicalForm";
					
					// Save Image
					if (idImageFrontDate != null && idImageFrontDate.length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageFrontData(),
								destfilepath, idImageFront + ".jpg");
						subscriberIdDetail.setIdImageFront(idImageFront);
					}
					
					if (idImageBackDate != null && idImageBackDate.length() > 0) {
						idBackImageSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getIdImageBackData(),
								destfilepath, idImageBack + ".jpg");
						subscriberIdDetail.setIdImageBack(idImageBack);
					}
					/*if (subscriberImageId != null && subscriberImageId.length() > 0) {
						subscriberImageIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getSubscriberImageId(),
								destfilepath, subscriberImage + ".jpg");
						subscriberIdDetail.setSubscriberImageId(subscriberImage);
					}*/
					
					if (physicalFormId != null && physicalFormId.length() > 0) {
						physicalFormIdSaveStatus = Utility.saveBase64ToFile(subscriberIdDetailsDto.getPhysicalFormId(),
								destfilepath, physicalForm + ".jpg");
						subscriberIdDetail.setPhysicalFormId(physicalForm);
					}

					if (!idFrontImageSaveStatus || !idBackImageSaveStatus || !physicalFormIdSaveStatus) {
						break;
					}
					subscriberIdDetail.setSubscriberDetails(subscriberDetails);
					subscriberIdDetail.setIdPath(timestr);
					subscriberIdDetail.setIsOldIdDetails(KycConstants.FALSE);

					// add in subscriberIdDetails arraylist
					subscriberIdDetails.add(subscriberIdDetail);
				}

			// Set Douments
			documentsDtos = subscriberDetailsDto.getSubscriberDocuments();
			if (documentsDtos != null) {
				List<SubscriberDocuments> documents = new ArrayList<>();
				String documentFilePath = environment.getProperty(ConfigConstants.UPLOAD_FOLDER) + environment.getProperty(ConfigConstants.SUBSCRIBER_DIRECTORY) + timestr + "/"
						+ environment.getProperty(ConfigConstants.SUBSCRIBER_DOCUMENTS) + subscriberDto.getKycTansactionId() + "/";
				for (SubscriberDocumentsDto documentsDto : documentsDtos) {
					SubscriberDocuments document = new SubscriberDocuments();
					BeanUtils.copyProperties(documentsDto, document);

					// Save Image
					if (documentsDto.getDocumentImageData() != null
							&& documentsDto.getDocumentImageData().length() > 0) {
						idFrontImageSaveStatus = Utility.saveBase64ToFile(documentsDto.getDocumentImageData(),
								destfilepath, documentsDto.getDocumentName() + ".jpg");

					}
					document.setDocumentImage(documentFilePath);
					document.setSubscriberDetails(subscriberDetails);
					documents.add(document);
				}
				subscriberDetails.setSubscriberDocuments(documents);
			}
			subscriberDetails.setSubscriberIdDetails(subscriberIdDetails);

			/*Set<SubscriberImageDetail> subscriberImageDetails = new HashSet<>();
			if (subscriberDetailsDto.getSubscriberImageDetail() != null)
				for (SubscriberImageDetailDto subscriberImageDetailDto : subscriberDetailsDto
						.getSubscriberImageDetail()) {
					SubscriberImageDetail subscriberImageDetail = new SubscriberImageDetail();
					BeanUtils.copyProperties(subscriberImageDetailDto, subscriberImageDetail);
					subscriberImageDetail.setSubscriberDetails(subscriberDetails);

					subscriberImageDetail.setImagePath(timestr);

					saveresult = Utility.saveBase64ToFile(subscriberImageDetailDto.getImageStr(), destfilepath,
							subscriberImageDetailDto.getImageType() + "." + subscriberImageDetailDto.getImageFormat());

					if (!saveresult) {
						// responseBean.setStatus(getStatusBean("ERROR_IMG",
						// "exception.message", "Something Went wrong. Please
						// try again later."));
						break;
					}

					subscriberImageDetail.setIsOldImageDetails(KycConstants.FALSE);
					subscriberImageDetails.add(subscriberImageDetail);
				}*/
			//subscriberDetails.setSubscriberImageDetail(subscriberImageDetails);
			subscriberDetails.setSubscriber(subscriber);
			subscriberDetails.setStatusFlag(KycConstants.TRUE);
			subscriberDetails.setDeleteFlag(KycConstants.FALSE);
			subscriberDetails.setIsOldUserDetails(KycConstants.FALSE);
			
			//newly added
			subscriberDetails.setIsImageEdited("NO");
			subscriberDetails.setFinalStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setNationality(subscriberDetailsDto.getNationality());
						
			subscriberDetails.setPhysicalFormStatus(KycConstants.PENDING_STATUS);
			subscriberDetails.setIsPhysicalFormReceived(0);
			subscriberDetails.setMsisdn(subscriberDto.getMsisdn());
			subscriberDetails.setAlternateNo(subscriberDetailsDto.getAlternateNo());
			subscriberDetails.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			subscriberDetails.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			subscriberDetails.setGender(subscriberDetailsDto.getGender().toLowerCase());
			subscriberDetails.setBulkSubscriberFlag(subscriberDetailsDto.getBulkSubscriberFlag());
			subscriberDetails.setIpAddress("WEB");
			subscriberDetails.setIpAddress("WEB");
			subscriberDetails.setPlaceOfBirth(subscriberDetailsDto.getPlaceOfBirth());
			if(subscriberDetailsDto.getBulkSubscriberFlag()!=null && subscriberDetailsDto.getBulkSubscriberFlag()==1){
				subscriberDetails.setCaseType("NEW");
			}
			else{
				subscriberDetails.setCaseType("OLD");	
			}
			subscriberDetails.setProxyForRegistration(0);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriberDetails.setCreatedBy(auth.getName());
			}
			subscriberDetailsList.add(subscriberDetails);

		}

		// OperactionOnSubscriber operactionOnSubscriber = new
		// OperactionOnSubscriber();
		// operactionOnSubscriber.setSubscriber(subscriber);
		// subscriber.setOperactionOnSubscriber(operactionOnSubscriber);
		subscriber.setSubscriberDetails(subscriberDetailsList);
		subscriber.setStatusFlag(KycConstants.TRUE);
		subscriber.setDeleteFlag(KycConstants.FALSE);

		/*subscriber.setFinalStatus(KycConstants.PENDING_STATUS);
		subscriber.setNationality(subscriberDto.getNationality());
		subscriber.setCaseType(subscriberDto.getCaseType());*/

		
		//Added for user creation
		
		subscriber.setUserFlag(KycConstants.FALSE);		

		if (subscriber.getSubscriberId() != null && subscriber.getSubscriberId()!=0) {
			subscriber.setUpdatedOn(timestamp);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setUpdatedBy(auth.getName());
			}
		} else {
			subscriber.setDeleteFlag(KycConstants.FALSE);
			subscriber.setStatusFlag(KycConstants.TRUE);
			subscriber.setCreatedOn(new Timestamp(System.currentTimeMillis()));			
			subscriber.setUpdatedOn(timestamp);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				subscriber.setCreatedBy(auth.getName());
			}
		}
		
		
		
		try {			
			subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
			if (subscriber == null) {
				throw new BusinessException(SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionCode(),
						SubscriberExceptionCodes.SUBSCRIBER_NOT_EXISTS.getExceptionDescription());
			}
			
			this.taskManagementService.addTask(TaskEnum.SIM_VALIDATION.getTaskCode(),
					subscriber.getSubscriberId(), subscriber.getSubscriberDetails().get(0).getSubscriberDetailsId().toString());
			
			subscriberDto.setSubscriberId(subscriber.getSubscriberId());
			if(isDebugEnabled) {
				log.debug("Subscriber created with subscriber id : "+subscriber.getSubscriberId());
			}
			/*if(subscriberUserDto.getUserFlag()==1 && usersDto.getUserDetail().getMsisdn()!=null ){
				provisionUser(usersDto);
			}*/
			
			if(subscriberDto.getLocation() != null) {
				boolean locationUpated = this.helperDataManagementService.updateLocation(subscriberDto.getLocation() , null, subscriber.getSubscriberId());
				if(isDebugEnabled) {
					log.debug("Subscriber Location Updated : "+locationUpated);
				}
			}
		} catch (KycDaoException cause) {
			if(isDebugEnabled) {
				log.debug("Exception occured while adding subscriber ");
			}
			throw new BusinessException(cause);
		}
		return subscriberDto;

	
	}

	

	@Override
	public void activateAm(SubscriberDto subscriberDto) throws BusinessException {
		SubscriberDetails subscriber=subscriberDetailsDao.getSubscriberDetails(subscriberDto.getMsisdn(), KycConstants.FALSE,"PENDING");
			taskManagementService.addTask(TaskEnum.AM_ACTIVATION.getTaskCode(), subscriber.getSubscriber().getSubscriberId(),
					subscriber.getSubscriberDetailsId().toString());		
		
	}

	@Override
	public SubscriberSearchResponseDto getSearchSubscriber(SearchSubscriberDto searchSubscriberDto, Integer isOldData)
			throws BusinessException {
		SubscriberSearchResponseDto subscriberSearchResponseDto = new SubscriberSearchResponseDto();
		List<SubscriberDetails> subscriberList =new ArrayList<>();
		SubscriberDetails subscriberDetails=subscriberDetailsDao.getSubscriberDetailsStatus(searchSubscriberDto.getMsisdn(), KycConstants.FALSE);
		if(subscriberDetails!=null){
			subscriberList.add(subscriberDetails);
			subscriberSearchResponseDto.setSlot(searchSubscriberDto.getSlot());
			subscriberSearchResponseDto.setStartIndex(searchSubscriberDto.getStartIndex());			
			subscriberSearchResponseDto.setRows(subscriberList);
		}		

		return subscriberSearchResponseDto;
	}
	
	
}