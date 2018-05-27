package com.airtel.user.helper.response;

import com.airtel.user.helper.dto.SearchResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("rawtypes")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SearchResponseDto searchResponseDto;

	public SearchResponseDto getSearchResponseDto() {
		return searchResponseDto;
	}

	public void setSearchResponseDto(SearchResponseDto searchResponseDto) {
		this.searchResponseDto = searchResponseDto;
	}
	
}