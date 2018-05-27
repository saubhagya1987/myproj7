package com.airtel.user.helper.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProvinceIds implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer provinceId;
	private String provinceName;
	private DistrictIds districts;
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public DistrictIds getDistricts() {
		return districts;
	}
	public void setDistricts(DistrictIds districts) {
		this.districts = districts;
	}
	
}
