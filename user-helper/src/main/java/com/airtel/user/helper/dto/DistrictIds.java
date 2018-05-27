package com.airtel.user.helper.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class DistrictIds implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer districtId;
	private String districtName;
	private VillageIds villageIds;
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
	public VillageIds getVillageIds() {
		return villageIds;
	}
	public void setVillageIds(VillageIds villageIds) {
		this.villageIds = villageIds;
	}

}
