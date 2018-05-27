package com.airtel.kyc.business;

import com.airtel.kyc.helpers.dto.ReportRequestDto;
import com.airtel.kyc.helpers.dto.ReportResponse;

public interface ReportService {

	//ResponseDto channelPartnerDailyReport(ReportRequestDto reportRequestDto);

	//ResponseDto channelPartnerWiseSubReport(ReportRequestDto reportRequestDto);

	//ResponseDto dataExecutiveDailyReport(ReportRequestDto reportRequestDto);

	//ResponseDto dataExecutiveWiseSubReport(ReportRequestDto reportRequestDto);

	ReportResponse channelPartnerDailyReport(ReportRequestDto reportRequestDto);
	ReportResponse channelPartnerWiseSubReport(ReportRequestDto reportRequestDto);
	ReportResponse sndDailyReport(ReportRequestDto reportRequestDto);
	ReportResponse tbmDailyReport(ReportRequestDto reportRequestDto);
	ReportResponse sndUserPerformance(ReportRequestDto reportRequestDto);
	ReportResponse sndUserQuality(ReportRequestDto reportRequestDto);
	ReportResponse sndChannelActivity(ReportRequestDto reportRequestDto);
	ReportResponse sndChannelPerformance(ReportRequestDto reportRequestDto);

	/*BaseResponse<?> channelPartnerWiseSubReport(ReportRequestDto reportRequestDto);

	BaseResponse<?> sndDailyReport(ReportRequestDto reportRequestDto);

	BaseResponse<?> tbmDailyReport(ReportRequestDto reportRequestDto);*/

}
