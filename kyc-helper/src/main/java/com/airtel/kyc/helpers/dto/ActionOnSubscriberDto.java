package com.airtel.kyc.helpers.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(value = Include.NON_NULL)
public class ActionOnSubscriberDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer actionSubscriberId;

	private Timestamp actionType;

	private Timestamp actionTime;

	private String actionBy;

	private String actionStatus;

	public Integer getActionSubscriberId() {
		return actionSubscriberId;
	}

	public void setActionSubscriberId(Integer actionSubscriberId) {
		this.actionSubscriberId = actionSubscriberId;
	}

	public Timestamp getActionType() {
		return actionType;
	}

	public void setActionType(Timestamp actionType) {
		this.actionType = actionType;
	}

	public Timestamp getActionTime() {
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

}
