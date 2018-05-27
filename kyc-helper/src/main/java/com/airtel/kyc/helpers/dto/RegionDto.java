package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RegionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer regionId;


	private String regionName;


	private List<ProvinceDto> territory;


	public Integer getRegionId() {
		return regionId;
	}


	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public List<ProvinceDto> getTerritory() {
		return territory;
	}


	public void setTerritory(List<ProvinceDto> territory) {
		this.territory = territory;
	}


	
	
	
}