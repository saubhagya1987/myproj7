package com.airtel.kyc.helpers.response;

import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberSearchResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("rawtypes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SubscriberDto subscriberDto;

	private SubscriberSearchResponseDto subscriberSearchResponseDto; 
	
	//private NidaUserDto nidaUserDto;
	
	public SubscriberDto getSubscriberDto() {
		return subscriberDto;
	}

	public void setSubscriberDto(SubscriberDto subscriberDto) {
		this.subscriberDto = subscriberDto;
	}

	public SubscriberSearchResponseDto getSubscriberSearchResponseDto() {
		return subscriberSearchResponseDto;
	}

	public void setSubscriberSearchResponseDto(SubscriberSearchResponseDto subscriberSearchResponseDto) {
		this.subscriberSearchResponseDto = subscriberSearchResponseDto;
	}

	/*public NidaUserDto getNidaUserDto() {
		return nidaUserDto;
	}

	public void setNidaUserDto(NidaUserDto nidaUserDto) {
		this.nidaUserDto = nidaUserDto;
	}*/
	
}
