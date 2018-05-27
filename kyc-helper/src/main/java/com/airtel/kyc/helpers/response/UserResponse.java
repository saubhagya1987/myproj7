package com.airtel.kyc.helpers.response;

import java.util.List;

import com.airtel.kyc.helpers.dto.AccessTokenDto;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.CommissionRuleDto;
import com.airtel.kyc.helpers.dto.TotalCountDto;
import com.airtel.kyc.helpers.dto.UserSearchResponseDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsersDto userDto;
	
	private UserDetailsDto userDetailsDto;
	
	private AccessTokenDto accessTokenDto;
	
	private List<UsersDto> userDtoList;
	
	private UserSearchResponseDto searchResponseDto;
	
	private Integer isByPassflag;
	
	private String body;
	
	private CommissionRuleDto commissionRuleDto;
	
	private TotalCountDto totalCountDto = new TotalCountDto();
	
	public UsersDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UsersDto userDto) {
		this.userDto = userDto;
	}

	public UserSearchResponseDto getSearchResponseDto() {
		return searchResponseDto;
	}

	public void setSearchResponseDto(UserSearchResponseDto searchResponseDto) {
		this.searchResponseDto = searchResponseDto;
	}

	public Integer getIsByPassflag() {
		return isByPassflag;
	}

	public void setIsByPassflag(Integer isByPassflag) {
		this.isByPassflag = isByPassflag;
	}

	public TotalCountDto getTotalCountDto() {
		return totalCountDto;
	}

	public void setTotalCountDto(TotalCountDto totalCountDto) {
		this.totalCountDto = totalCountDto;
	}

	public List<UsersDto> getUserDtoList() {
		return userDtoList;
	}

	public void setUserDtoList(List<UsersDto> userDtoList) {
		this.userDtoList = userDtoList;
	}

	public AccessTokenDto getAccessTokenDto() {
		return accessTokenDto;
	}

	public void setAccessTokenDto(AccessTokenDto accessTokenDto) {
		this.accessTokenDto = accessTokenDto;
	}

	public UserDetailsDto getUserDetailsDto() {
		return userDetailsDto;
	}

	public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
		this.userDetailsDto = userDetailsDto;
	}

	public CommissionRuleDto getCommissionRuleDto() {
		return commissionRuleDto;
	}

	public void setCommissionRuleDto(CommissionRuleDto commissionRuleDto) {
		this.commissionRuleDto = commissionRuleDto;
	}
	
	
	
}
