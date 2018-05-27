package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReportResponse<T>  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResponseDto responseDto;
	private ReportResponseDto reportResponseDto;
	
	List<ReportResponseDto> reportData;
	
	private Object body;
	public ResponseDto getResponseDto() {
		return responseDto;
	}

	public void setResponseDto(ResponseDto responseDto) {
		this.responseDto = responseDto;
	}

	public ReportResponseDto getReportResponseDto() {
		return reportResponseDto;
	}

	public void setReportResponseDto(ReportResponseDto reportResponseDto) {
		this.reportResponseDto = reportResponseDto;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public List<ReportResponseDto> getReportData() {
		return reportData;
	}

	public void setReportData(List<ReportResponseDto> reportData) {
		this.reportData = reportData;
	}

	

	
}
