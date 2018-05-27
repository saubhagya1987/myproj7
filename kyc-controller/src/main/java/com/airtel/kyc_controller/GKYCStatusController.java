package com.airtel.kyc_controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.kyc.business.GKYCStatusService;
import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;

@RestController
@RequestMapping(value = { "/api/gkyc" })
public class GKYCStatusController {
	private static Logger logger = Logger.getLogger(GKYCStatusController.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();
	
	@Autowired
	GKYCStatusService gKYCStatusService;

	@RequestMapping(value = "/getGkycStatus", method = RequestMethod.POST, produces = "application/json")
	public GKYCStatusResponseDto getConfigData(@RequestBody GKYCStatusRequestDto gKYCStatusDto)
			throws Exception {

		logger.info(gKYCStatusDto.gettMSISDN());
		GKYCStatusResponseDto response = gKYCStatusService.getGKYCStatus(gKYCStatusDto);
		return response; 		
	}
}