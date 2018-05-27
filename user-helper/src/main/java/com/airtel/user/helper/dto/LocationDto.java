package com.airtel.user.helper.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(value = Include.NON_NULL)
public class LocationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer zoneId;
	private String zoneName;
	private Integer branchId;
	private String branchName;
	private Integer clusterId;
	private String clusterName;
	private Integer siteId;
	private String siteName;
	
	private String provinceName;
	private String villageName;
	private String districtName;
	
	private Integer provinceId;
	private Integer villageId;
	private Integer districtId;
	
	//newly added
	//private Integer regionId;
	//private String regionName;

	//private Integer territoryId;
	//private String territoryName;
	
	//private Integer districtId;
	//private String districtName;
	
	private List<RegionIds> regionIds;
	
	//private List<TerritoryIds> territoryIds;
	private List<ProvinceIds> provinceIds;
	
	private List<DistrictIds> districtIds;
	
	private List<VillageIds> villageIds;
	
	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	/*public Integer getRegionId() {
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

	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}*/

	/*public Integer getDistrictId() {
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
	}*/

	/*public List<Integer> getRegionIds() {
		return regionIds;
	}

	public void setRegionIds(List<Integer> regionIds) {
		this.regionIds = regionIds;
	}*/

	/*public List<Integer> getTerritoryIds() {
		return territoryIds;
	}

	public void setTerritoryIds(List<Integer> territoryIds) {
		this.territoryIds = territoryIds;
	}*/

	public List<RegionIds> getRegionIds() {
		return regionIds;
	}

	public void setRegionIds(List<RegionIds> regionIds) {
		this.regionIds = regionIds;
	}

	

	public List<DistrictIds> getDistrictIds() {
		return districtIds;
	}

	public void setDistrictIds(List<DistrictIds> districtIds) {
		this.districtIds = districtIds;
	}

	public List<ProvinceIds> getProvinceIds() {
		return provinceIds;
	}

	public void setProvinceIds(List<ProvinceIds> provinceIds) {
		this.provinceIds = provinceIds;
	}

	public List<VillageIds> getVillageIds() {
		return villageIds;
	}

	public void setVillageIds(List<VillageIds> villageIds) {
		this.villageIds = villageIds;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	

}