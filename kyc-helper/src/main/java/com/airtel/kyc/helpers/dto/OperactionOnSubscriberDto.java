package com.airtel.kyc.helpers.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class OperactionOnSubscriberDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer actionSubscriberId;

	private Timestamp amActivationTime;

	private Timestamp svActivationTime;

	private String biActivationTime;

	private String emaActivationTime;

	public Integer getActionSubscriberId() {
		return actionSubscriberId;
	}

	public void setActionSubscriberId(Integer actionSubscriberId) {
		this.actionSubscriberId = actionSubscriberId;
	}

	public Timestamp getAmActivationTime() {
		return amActivationTime;
	}

	public void setAmActivationTime(Timestamp amActivationTime) {
		this.amActivationTime = amActivationTime;
	}

	public Timestamp getSvActivationTime() {
		return svActivationTime;
	}

	public void setSvActivationTime(Timestamp svActivationTime) {
		this.svActivationTime = svActivationTime;
	}

	public String getBiActivationTime() {
		return biActivationTime;
	}

	public void setBiActivationTime(String biActivationTime) {
		this.biActivationTime = biActivationTime;
	}

	public String getEmaActivationTime() {
		return emaActivationTime;
	}

	public void setEmaActivationTime(String emaActivationTime) {
		this.emaActivationTime = emaActivationTime;
	}

}