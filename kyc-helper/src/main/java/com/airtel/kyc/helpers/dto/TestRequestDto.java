package com.airtel.kyc.helpers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestRequestDto {
	
	private String userName;
	private String msisdn;
	private String isOldData;
	private String simSerialNo;
	private Integer id;

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getIsOldData() {
		return isOldData;
	}

	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}

	public String getSimSerialNo() {
		return simSerialNo;
	}

	public void setSimSerialNo(String simSerialNo) {
		this.simSerialNo = simSerialNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
