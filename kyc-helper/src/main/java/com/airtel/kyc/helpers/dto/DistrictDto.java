package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistrictDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer districtId;


	private String districtName;

	private List<VillageDto> villages;

	public Integer getDistrictId() {
		return districtId;
	}


	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public List<VillageDto> getVillages() {
		return villages;
	}


	public void setVillages(List<VillageDto> villages) {
		this.villages = villages;
	}


	

}