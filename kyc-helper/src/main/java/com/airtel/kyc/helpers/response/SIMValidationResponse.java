package com.airtel.kyc.helpers.response;

import com.airtel.kyc.helpers.dto.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("rawtypes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SIMValidationResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private boolean isSIMValid;

	public boolean isSIMValid() {
		return isSIMValid;
	}

	public void setSIMValid(boolean isSIMValid) {
		this.isSIMValid = isSIMValid;
	}
}