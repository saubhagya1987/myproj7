package com.airtel.kyc.business;

import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;

public interface GKYCStatusService {

	GKYCStatusResponseDto getGKYCStatus(GKYCStatusRequestDto pGKYCStatusRequestDto);
}
