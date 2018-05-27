package com.airtel.kyc.enumData;

public enum ActionCodes {

	PENDING(1001, "PENDING"),
	APPROVED(1002, "APPROVED"),
	REASSIGNED(1003, "REASSIGNED"),
	VALIDATION_FAILED(1004, "VALIDATION_FAILED"),
	//IMSI_NOT_EXISTS(1004, "IMSI_NOT_EXISTS"),
	REJECTED(1005, "REJECTED"),
	IN_PROCESS(1006, "IN_PROCESS"),
	PARTIALLY_ACTIVATED(1007, "PARTIALLY_ACTIVATED"),
	FAILED(1008, "FAILED"),
	BARRED(1009, "BARRED"),
	HOTLINE(1010, "HOTLINE")
	;
	
	private Integer actionCode;

	private String actionDescription;
	
	ActionCodes(Integer actionCode, String actionDescription) {
		this.actionCode=actionCode;
		this.actionDescription=actionDescription;

	}

	public Integer getActionCode() {
		return actionCode;
	}


	public void setActionCode(Integer actionCode) {
		this.actionCode = actionCode;
	}


	public String getActionDescription() {
		return actionDescription;
	}


	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}


}
