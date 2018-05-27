package com.airtel.kyc.enumData;

public enum ConfigDataEnum {

	EMAIL_AM_BARRING(100001,"EMAIL_AM_BARRING"),
	EMAIL_CP_SUPPORT(100002,"EMAIL_CP_SUPPORT"),
	DAYS_TO_HOTLINE_NREG(100003,"DAYS_TO_HOTLINE_NREG"),
	DAYS_TO_BARRING_NREG(100004,"DAYS_TO_BARRING_NREG"),
	AM_LIMIT_SIMSWAP(100005,"AM_LIMIT_SIMSWAP"),
	NO_OF_CONNECTION(100006,"NO_OF_CONNECTION"),
	DAYS_TO_REJECT_NREG(100007,"DAYS_TO_REJECT_NREG"),
	NO_OF_DIGITS_MATCH_ID_SIMSWAP(100008,"NO_OF_DIGITS_MATCH_ID_SIMSWAP"),
	NO_OF_TIMES_GKYC_STATUS_CHANGED(100009,"NO_OF_TIMES_GKYC_STATUS_CHANGED")	
	;
	
	private Integer configDataId;
	private String configDataName;
	
	ConfigDataEnum(Integer configDataId,String configDataName) {
		this.configDataId=configDataId;
		this.configDataName=configDataName;
	}

	public Integer getConfigDataId() {
		return configDataId;
	}

	public void setConfigDataId(Integer configDataId) {
		this.configDataId = configDataId;
	}

	public String getConfigDataName() {
		return configDataName;
	}

	public void setConfigDataName(String configDataName) {
		this.configDataName = configDataName;
	}
}