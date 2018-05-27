package com.airtel.kyc.repository.dao;

import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;

public interface GKYCStatusDao {

	GKYCStatusResponseDto getGKYCStatus(GKYCStatusRequestDto pGKYCStatusRequestDto);
	GKYCStatusResponseDto getSimValidationFailedSubscriber(GKYCStatusRequestDto pGKYCStatusRequestDto);
	GKYCStatusResponseDto getAuuId(Integer userId);
}
