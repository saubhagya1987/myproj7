package com.airtel.kyc.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.airtel.kyc.business.ReportService;
import com.airtel.kyc.enumData.ExceptionCodes;
import com.airtel.kyc.enumData.ResponseCodes;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.ReportRequestDto;
import com.airtel.kyc.helpers.dto.ReportResponse;
import com.airtel.kyc.helpers.dto.ReportResponseDto;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.repository.dao.ReportDao;
import com.airtel.kyc.utils.DateUtils;
import com.airtel.kyc.utils.KycResponseUtils;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportDao reportDao;

	/*@SuppressWarnings("rawtypes")
	@Override
	public ResponseDto channelPartnerDailyReport(ReportRequestDto reportRequestDto) {
		ResponseDto responseDto=new ResponseDto();
		ReportResponseDto reportResponseDto=reportDao.getTotalCountOfSubscriber(reportRequestDto);
		if(reportResponseDto!=null && reportResponseDto.getTotalCount()>0){
			responseDto.setReportResponseDto(reportResponseDto);
			responseDto.setResponseCode(KycResponseUtils.getKycResponse(HttpStatus.OK).getResponseCode());
			responseDto.setResponseDescription("Success");
		}
		else{
			responseDto.setResponseCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setExceptionCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setResponseDescription(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionDescription());
		}
		return responseDto;
	}*/
	@Override
	public ReportResponse channelPartnerDailyReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			ReportResponseDto reportData=reportDao.getTotalCountOfSubscriber(reportRequestDto);	
			if(reportData!=null && reportData.getTotalCount()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
				reportResponseDto.setBody(reportData);
			}
			
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Data Not Exist");
				reportResponseDto.setBody(reportData);
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}
	
	@Override
	public ReportResponse channelPartnerWiseSubReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			List<ReportResponseDto> reportData=reportDao.channelPartnerWiseSubReport(reportRequestDto);	
			if(reportData.size()>0){
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
			ReportResponse reportDto = new ReportResponse();
			reportDto.setReportData(reportData);			
			reportResponseDto.setBody(reportDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Data Not Exist");
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
			responseDto.setBody(null);
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}
	

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseDto channelPartnerWiseSubReport(ReportRequestDto reportRequestDto) {
		ResponseDto responseDto=new ResponseDto();
		List<ReportResponseDto> reportResponseDto=reportDao.channelPartnerWiseSubReport(reportRequestDto);
		if(reportResponseDto.size()>0){
			responseDto.setReportResponseDtoList(reportResponseDto);
			responseDto.setResponseCode(KycResponseUtils.getKycResponse(HttpStatus.OK).getResponseCode());
			responseDto.setResponseDescription("Success");
		}
		else{
			responseDto.setResponseCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setExceptionCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setResponseDescription(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionDescription());
		}
		return responseDto;
	}*/
	
	/*@Override
	public BaseResponse<?> channelPartnerWiseSubReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			List<ReportResponseDto> reportData=reportDao.channelPartnerWiseSubReport(reportRequestDto);	
			if(reportData.size()>0){
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
			reportResponseDto.setBody(reportData);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Data Not Exist");
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
			responseDto.setBody(null);
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}

	@Override
	public ResponseDto dataExecutiveDailyReport(ReportRequestDto reportRequestDto) {
		ResponseDto responseDto=new ResponseDto();
		ReportResponseDto reportResponseDto=reportDao.getTotalCountOfDataExecutive(reportRequestDto);
		if(reportResponseDto!=null && reportResponseDto.getTotalProcessed()>0){
			responseDto.setReportResponseDto(reportResponseDto);
			responseDto.setResponseCode(KycResponseUtils.getKycResponse(HttpStatus.OK).getResponseCode());
			responseDto.setResponseDescription("Success");
		}
		else{
			responseDto.setResponseCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setExceptionCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setResponseDescription(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionDescription());
		}
		return responseDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseDto dataExecutiveWiseSubReport(ReportRequestDto reportRequestDto) {
		ResponseDto responseDto=new ResponseDto();
		List<ReportResponseDto> reportResponseDto=reportDao.dataExecutiveWiseSubReport(reportRequestDto);
		if(reportResponseDto.size()>0){
			List<ReportResponseDto> reportResponseList=new ArrayList<>();
			for (ReportResponseDto reportResponseDtoList : reportResponseDto) {
				ReportResponseDto reportResponseDtoObj=new ReportResponseDto();
				BeanUtils.copyProperties(reportResponseDtoList, reportResponseDtoObj);
				reportResponseDtoObj.setUpdatedTime(DateUtils.getFormatDate(reportResponseDtoList.getUpdatedOn(), "dd-MM-yy"));
				reportResponseDtoObj.setUpdatedOn(null);
				reportResponseList.add(reportResponseDtoObj);
			}
			responseDto.setReportResponseDtoList(reportResponseList);
			responseDto.setResponseCode(KycResponseUtils.getKycResponse(HttpStatus.OK).getResponseCode());
			responseDto.setResponseDescription("Success");
		}
		else{
			responseDto.setResponseCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setExceptionCode(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionCode());
			responseDto.setResponseDescription(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION.getExceptionDescription());
		}
		return responseDto;
	}



	@Override
	public BaseResponse<?> sndDailyReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			ReportResponseDto reportData=reportDao.sndDailyReport(reportRequestDto);	
			if(reportData!=null && reportData.getTotalCount()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
				reportResponseDto.setBody(reportData);
			}
			
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Data Not Exist");
				reportResponseDto.setBody(reportData);
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}



	@Override
	public BaseResponse<?> tbmDailyReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			ReportResponseDto reportData=reportDao.tbmDailyReport(reportRequestDto);	
			if(reportData!=null && reportData.getTotalCount()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
				reportResponseDto.setBody(reportData);
			}
			
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Data Not Exist");
				reportResponseDto.setBody(reportData);
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}


*/
	
	@Override
	public ReportResponse sndDailyReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			ReportResponseDto reportData=reportDao.sndDailyReport(reportRequestDto);	
			if(reportData!=null && reportData.getTotalCount()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
				reportResponseDto.setBody(reportData);
			}
			
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Data Not Exist");
				reportResponseDto.setBody(reportData);
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}



	@Override
	public ReportResponse tbmDailyReport(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			ReportResponseDto reportData=reportDao.tbmDailyReport(reportRequestDto);	
			if(reportData!=null && reportData.getTotalCount()>0){
				responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
				reportResponseDto.setBody(reportData);
			}
			
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(), "Data Not Exist");
				reportResponseDto.setBody(reportData);
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}

	@Override
	public ReportResponse sndUserPerformance(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			List<ReportResponseDto> reportData=reportDao.sndUserPerformance(reportRequestDto);
			if(reportData.size()>0){
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
			ReportResponse reportDto = new ReportResponse();
			reportDto.setReportData(reportData);			
			reportResponseDto.setBody(reportDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Data Not Exist");
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
			responseDto.setBody(null);
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}

	@Override
	public ReportResponse sndUserQuality(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			List<ReportResponseDto> reportData=reportDao.sndUserQuality(reportRequestDto);
			if(reportData.size()>0){
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
			ReportResponse reportDto = new ReportResponse();
			reportDto.setReportData(reportData);			
			reportResponseDto.setBody(reportDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Data Not Exist");
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
			responseDto.setBody(null);
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}

	@Override
	public ReportResponse sndChannelActivity(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			List<ReportResponseDto> reportData=reportDao.sndChannelActivity(reportRequestDto);
			if(reportData.size()>0){
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
			ReportResponse reportDto = new ReportResponse();
			reportDto.setReportData(reportData);			
			reportResponseDto.setBody(reportDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Data Not Exist");
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
			responseDto.setBody(null);
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}

	@Override
	public ReportResponse sndChannelPerformance(ReportRequestDto reportRequestDto) {
		ReportResponse reportResponseDto = new ReportResponse();
		ResponseDto responseDto = null;
		try {				
			List<ReportResponseDto> reportData=reportDao.sndChannelPerformance(reportRequestDto);
			if(reportData.size()>0){
			responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Success");
			ReportResponse reportDto = new ReportResponse();
			reportDto.setReportData(reportData);			
			reportResponseDto.setBody(reportDto);
			}
			else{
				responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),"Data Not Exist");
			}
		}
		catch (Exception cause) 
		{			
			responseDto = KycResponseUtils.getKycResponse(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode(),cause.getMessage());
			responseDto.setBody(null);
		}
		reportResponseDto.setResponseDto(responseDto);
		return reportResponseDto;
	}
	
	
	

}
