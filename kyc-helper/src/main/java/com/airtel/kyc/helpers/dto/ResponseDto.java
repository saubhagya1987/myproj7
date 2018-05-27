package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The response code. */
	private int responseCode;

	/** The response description. */
	private String responseDescription;

	/** The exception code. */
	private int exceptionCode;

	/** The exception description. */
	private String exceptionDescription;
	
	private String key;

	/**
	 * Instantiates a new response dto.
	 *
	 * @param responseCode
	 *            the response code
	 * @param responseDescription
	 *            the response description
	 */
	private T body;
	
	private ReportResponseDto reportResponseDto;
	private List<ReportResponseDto> reportResponseDtoList;
	
	public ResponseDto() {
	}
	
	public ResponseDto(int responseCode, String responseDescription) {
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionDescription() {
		return exceptionDescription;
	}

	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public ReportResponseDto getReportResponseDto() {
		return reportResponseDto;
	}

	public void setReportResponseDto(ReportResponseDto reportResponseDto) {
		this.reportResponseDto = reportResponseDto;
	}

	public List<ReportResponseDto> getReportResponseDtoList() {
		return reportResponseDtoList;
	}

	public void setReportResponseDtoList(List<ReportResponseDto> reportResponseDtoList) {
		this.reportResponseDtoList = reportResponseDtoList;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}