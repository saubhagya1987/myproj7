package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.helpers.dto.ReportRequestDto;
import com.airtel.kyc.helpers.dto.ReportResponseDto;

public interface ReportDao {

	ReportResponseDto getTotalCountOfSubscriber(ReportRequestDto reportRequestDto);

	List<ReportResponseDto> channelPartnerWiseSubReport(ReportRequestDto reportRequestDto);

	ReportResponseDto getTotalCountOfDataExecutive(ReportRequestDto reportRequestDto);

	List<ReportResponseDto> dataExecutiveWiseSubReport(ReportRequestDto reportRequestDto);

	ReportResponseDto sndDailyReport(ReportRequestDto reportRequestDto);

	ReportResponseDto tbmDailyReport(ReportRequestDto reportRequestDto);

	List<ReportResponseDto> sndUserPerformance(ReportRequestDto reportRequestDto);

	List<ReportResponseDto> sndUserQuality(ReportRequestDto reportRequestDto);

	List<ReportResponseDto> sndChannelActivity(ReportRequestDto reportRequestDto);

	List<ReportResponseDto> sndChannelPerformance(ReportRequestDto reportRequestDto);
	

}
