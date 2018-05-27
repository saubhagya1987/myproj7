package com.airtel.kyc.business.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.airtel.kyc.business.GKYCStatusService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;
import com.airtel.kyc.repository.dao.GKYCStatusDao;

@Service
public class GKYCStatusServiceImpl implements GKYCStatusService{
	
	private static Logger logger = Logger.getLogger(GKYCStatusServiceImpl.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();

	@Autowired
	GKYCStatusDao gKYCStatusDao;
	
	@Autowired
	AppConstants appConstants;
	
	@Override
	public GKYCStatusResponseDto getGKYCStatus(GKYCStatusRequestDto pGKYCStatusRequestDto) {
		// TODO Auto-generated method stub
		GKYCStatusResponseDto gKYCStatusResponseDto =  new GKYCStatusResponseDto();
		if(appConstants.gkycApiUser.equals(pGKYCStatusRequestDto.gettUserName()) && appConstants.gkycApiPassword.equals(pGKYCStatusRequestDto.gettPassword())){
			gKYCStatusResponseDto = gKYCStatusDao.getGKYCStatus(pGKYCStatusRequestDto);
			if(gKYCStatusResponseDto==null){
				gKYCStatusResponseDto =  new GKYCStatusResponseDto();
				gKYCStatusResponseDto.setReturnCode("514");
				gKYCStatusResponseDto.setErrorDesc("Record not found!");
				gKYCStatusResponseDto.setStatus("FAILURE");
			}
			else{
				if((gKYCStatusResponseDto.getSource()!=null) && ("WEB".equalsIgnoreCase(gKYCStatusResponseDto.getSource())|| gKYCStatusResponseDto.getSource().contains(".") )){
					gKYCStatusResponseDto.setSource("WEB");
				}
				else{
					gKYCStatusResponseDto.setSource("APP");
				}
				gKYCStatusResponseDto.setReturnCode(HttpStatus.OK.toString());
				gKYCStatusResponseDto.setStatus("SUCCESS");
			}
		}
		else{
			gKYCStatusResponseDto.setReturnCode("513");
			gKYCStatusResponseDto.setErrorDesc("Unauthorised access!");
			gKYCStatusResponseDto.setStatus("FAILURE");
		}
		
		return gKYCStatusResponseDto;
	}
}