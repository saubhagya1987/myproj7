package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CountryDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer countryId;	
	
	private String countryName;
	
	private String isoCode;
	
	private List<CountryDto> countryList;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public List<CountryDto> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDto> countryList) {
		this.countryList = countryList;
	}


}