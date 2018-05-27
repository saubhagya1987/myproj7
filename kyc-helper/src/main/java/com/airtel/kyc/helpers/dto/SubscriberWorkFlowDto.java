package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberWorkFlowDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer subscriberWorkFlowId;

	//private SubscriberDto subscriber;
	
	private SubscriberDetailsDto subscriberDetailsDto;

	
	private UsersDto usersDto;
	
	private Long subscriberId;
	private Integer userId;
	
	private String action;
	
	private String roleName;
	
	private String msisdn;
	
	private String statusReason;

	public Integer getSubscriberWorkFlowId() {
		return subscriberWorkFlowId;
	}

	public void setSubscriberWorkFlowId(Integer subscriberWorkFlowId) {
		this.subscriberWorkFlowId = subscriberWorkFlowId;
	}

/*	public SubscriberDto getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(SubscriberDto subscriber) {
		this.subscriber = subscriber;
	}*/
	
	public UsersDto getUsersDto() {
		return usersDto;
	}

	public SubscriberDetailsDto getSubscriberDetailsDto() {
		return subscriberDetailsDto;
	}

	public void setSubscriberDetailsDto(SubscriberDetailsDto subscriberDetailsDto) {
		this.subscriberDetailsDto = subscriberDetailsDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}


	
}