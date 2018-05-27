package com.airtel.kyc.business.service;

import java.io.IOException;
import java.util.List;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SubscriberException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.helpers.dto.BaseDto;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberIdDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberSearchResponseDto;
import com.airtel.kyc.helpers.dto.SubscriberUserDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.helpers.dto.TotalCountDto;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.SubscriberIdDetails;
import com.airtel.kyc.repository.exception.KycDaoException;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;

public interface SubscriberManagementService {


	public SubscriberSearchResponseDto getSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType) throws BusinessException;
	public boolean isSubscriberExist(String msisdn,String simSerialNo) ;
	
	public SubscriberDto getSubscriberBySubscriberId(Long subscriberId, Integer isOldData) throws BusinessException;
	
	public SubscriberWorkFlowDto actionOnSubscriber(SubscriberWorkFlowDto subscriberWorkFlowDto)
			throws BusinessException, IntegrationServiceException, IOException;
	
	public SubscriberDto getSubscriberByMsisdnAndSimNo(String msisdn, String simSerialNo, Integer isOldData)
			throws BusinessException;
	
	public void updateWorkFlow(Subscriber subscriber, String action);
	
	public void subscriberToUser(SubscriberDto subscriberDto) throws BusinessException, IOException;	
	
	
	public SubscriberDto getSubscriberBySubscriberId(int subscriberId, Integer isOldData) throws BusinessException;
	
	public BaseDto updateSubscriber(SubscriberDto subscriberDto,String imageEdited, String ipAddress, String handsetImei, String placeOfBirth) throws BusinessException;
	
	public BaseDto addSubscriber(SubscriberDto subscriberDto, UsersDto usersDto, SubscriberUserDto subscriberUserDto, String ipAddress, String handsetImei, String placeOfBirth) throws BusinessException, UserException;
	
	public BaseDto addBulkSubscriber(SubscriberDto subscriberDto, UsersDto usersDto, SubscriberUserDto subscriberUserDto) throws BusinessException, UserException;
	
	public BaseDto addExistingSubscriber(SubscriberDto subscriberDto, UsersDto usersDto, SubscriberUserDto subscriberUserDto, String amAccount, String isMinor, String latitude, String longitude, String channelPartnerCellId,String occupation,String minorName, String caseType,String proxyForRegistration,String submitedOn,String syncedOn,String onlineOfflineFlag,String registeredOn, String idImageBackData, String simSerialNo, String ipAddress, String handsetImei, String placeOfBirth) throws BusinessException, UserException;
	
	public SubscriberSearchResponseDto getSubscriber(SearchSubscriberDto searchSubscriberDto, Integer isOldData) throws BusinessException;
	public SubscriberSearchResponseDto getSearchSubscriber(SearchSubscriberDto searchSubscriberDto, Integer isOldData) throws BusinessException;
	public TotalCountDto getSubscriberCount(int userId, String roleName)throws BusinessException;
	public SubscriberSearchResponseDto getBarredSubscribers(SearchSubscriberDto searchSubscriberDto, Integer isOldData)throws BusinessException, KycDaoException;
	public List<SubscriberIdDetailsDto> getUserByName(String idNumber) throws BusinessException;
	public SubscriberDto generatePassword(SubscriberDto subscriberData)throws BusinessException,  SubscriberException, IOException;
	public SubscriberDto validateOtp(SubscriberDto subscriberData)throws BusinessException,  SubscriberException;
	public SubscriberSearchResponseDto getApprovedSubscribers(SearchSubscriberDto searchSubscriberDto, Integer isOldData, String finalStatus);
	public SubscriberDto getSubscriberData(String msisdn)throws BusinessException;
	public SubscriberDto addExistingSubscriberWithNewdata(SubscriberDto subscriberDto, UsersDto usersDto,SubscriberUserDto subscriberUserDto, String caseType, String newMsisdn,String proxyForRegistration,String submitedOn,String syncedOn,String onlineOfflineFlag,String registeredOn, String physicalFormId, String ipAddress, String handsetImei, String placeOfBirth) throws BusinessException, UserException;
	public void updateSubscriberForm(SubscriberDto subscriberDto)throws BusinessException;
	public SubscriberSearchResponseDto getOldApprovedSubscriber(SearchSubscriberDto searchSubscriberDto) throws BusinessException;
	public SubscriberSearchResponseDto getNewApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)throws BusinessException;
	public SubscriberSearchResponseDto getSubscriber(SearchSubscriberDto searchSubscriberDto) throws BusinessException;
	public SubscriberSearchResponseDto getCMSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException, UserDaoException;
	
	public SubscriberWorkFlowDto autoAssignSubscriber(SubscriberWorkFlowDto subscriberWorkFlowDto,List<SubscriberDetails> subscriberDetailsList)
			throws BusinessException;
	public void activateAm(SubscriberDto subscriberDto)throws BusinessException;
	public SubscriberSearchResponseDto assignToMe(SearchSubscriberDto searchSubscriberDto, String actionType)
			throws BusinessException;
}
