package com.airtel.kyc.helpers.response;

import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.TotalCountDto;
import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TotalCountResponse extends BaseResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	private TotalCountDto totalCountDto;
	
	private UsersDto usersDto;

	public TotalCountDto getTotalCountDto() {
		return totalCountDto;
	}

	public void setTotalCountDto(TotalCountDto totalCountDto) {
		this.totalCountDto = totalCountDto;
	}

	public UsersDto getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}
	
	
	
}
