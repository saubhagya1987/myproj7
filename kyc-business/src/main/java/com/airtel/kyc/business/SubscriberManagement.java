package com.airtel.kyc.business;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberUserDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.helpers.response.SubscriberResponse;
import com.airtel.kyc.helpers.response.TotalCountResponse;

public interface SubscriberManagement {
	public BaseResponse<?> registerSubscriber(SubscriberUserDto subscriberUserDto , boolean isReRegistation) throws BusinessException, SystemException, UserException;
	public BaseResponse<?> getSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType) throws BusinessException;
	public BaseResponse<?> getSubscriberBySubscriberId(Long subscriberId) throws BusinessException;
	public BaseResponse<?> actionOnSubscriber(SubscriberWorkFlowDto subscriberWorkFlowDto) throws BusinessException;

	public BaseResponse<?> getSubscriber(SearchSubscriberDto searchSubscriberDto) throws BusinessException;

	public BaseResponse<TotalCountResponse> getSubscriberCount(int userId, String roleName)throws BusinessException;
	public BaseResponse<SubscriberResponse> getBarredSubscribers(SearchSubscriberDto searchSubscriberDto)throws BusinessException;
	public BaseResponse<SubscriberResponse> updateSubscriber(SubscriberUserDto subscriberUserDto,boolean isReRegistation)throws BusinessException, SystemException, UserException;
	public BaseResponse<?> checkEntriesForIdNo(String idNumber)throws BusinessException, SystemException;
	public BaseResponse<?> generatePassword(SubscriberDto subscriberDto)throws BusinessException,SystemException;
	public BaseResponse<?> validateOtp(SubscriberDto subscriberDto)throws BusinessException,SystemException;
	public BaseResponse<SubscriberResponse> getApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)throws BusinessException,SystemException;
	public BaseResponse<SubscriberResponse> editRegisterSubscriber(SubscriberUserDto subscriberUserDto,boolean isReRegistation) throws BusinessException, SystemException, UserException;
	public BaseResponse<?> registerExistingSubscriber(SubscriberUserDto subscriberUserDto,	boolean isReRegistation)throws BusinessException, SystemException, UserException;
	public BaseResponse<?> registerExistingSubscriberWithNewData(SubscriberUserDto subscriberUserDto,boolean isReRegistation)throws BusinessException, SystemException, UserException;
	public BaseResponse<SubscriberResponse> updateSubscriberForm(SubscriberDto subscriberDto, boolean isReRegistation)throws BusinessException, SystemException, UserException;
	public BaseResponse<SubscriberResponse> getOldApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)throws BusinessException;
	public BaseResponse<SubscriberResponse> getNewApprovedSubscribers(SearchSubscriberDto searchSubscriberDto)throws BusinessException;
	public BaseResponse<?> getCMSubscriber(SearchSubscriberDto searchSubscriberDto, String actionType)throws BusinessException;
	public ResponseEntity<byte[]> registerbulkSubscriber(MultipartFile file)throws BusinessException, SystemException;
	public BaseResponse<SubscriberResponse> activateAm(SubscriberDto subscriberDto)throws BusinessException, SystemException;
	public BaseResponse<SubscriberResponse> getApprovedSubscriber(String finalTime);
	public BaseResponse<?> assignToMe(SearchSubscriberDto searchSubscriberDto, String actionType) throws BusinessException;
	public BaseResponse<SubscriberResponse> getSearchSubscriberList(SearchSubscriberDto searchSubscriberDto)throws BusinessException;
}
