package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberUserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Valid
	private SubscriberDto subscriberData;
	
	private UsersDto usersDto;
	
	private Integer userFlag;
	

	public UsersDto getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}

	public Integer getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}

	public SubscriberDto getSubscriberData() {
		return subscriberData;
	}

	public void setSubscriberData(SubscriberDto subscriberData) {
		this.subscriberData = subscriberData;
	}

}
