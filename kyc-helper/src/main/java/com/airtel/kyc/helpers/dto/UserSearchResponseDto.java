package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.List;

import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserSearchResponseDto extends SearchResponseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<UsersDto> result;

	public List<UsersDto> getResult() {
		return result;
	}

	public void setResult(List<UsersDto> result) {
		this.result = result;
	}

}
