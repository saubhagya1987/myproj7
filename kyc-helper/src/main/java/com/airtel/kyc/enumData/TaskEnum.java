package com.airtel.kyc.enumData;

public enum TaskEnum {
	SIM_VALIDATION(0, "SIM_VALIDATION"),	
	EMA_UNBARING(1, "EMA_UNBARING"),
	SV_DEMGRAPHIC_UPDATION(2, "SV_DEMGRAPHIC_UPDATION"),
	AM_ACTIVATION(3, "AM_ACTIVATION"),	
	EMA_BARING(4, "EMA_BARING"),
	SV_STATUS_UPDATION(5,"SV_STATUS_UPDATION"),
	EMA_UNBARING_FOR_BULK(6, "EMA_UNBARING_FOR_BULK");	

	
	private Integer taskCode;

	private String taskDescription;
	
	TaskEnum(Integer actionCode, String taskDescription) {
		this.taskCode=actionCode;
		this.taskDescription=taskDescription;
	}

	public Integer getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(Integer taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
}