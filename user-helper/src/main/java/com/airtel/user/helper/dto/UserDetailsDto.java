package com.airtel.user.helper.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDetailsDto extends BaseDto implements Serializable {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;

	private Integer userDetailsId;

	private String firstName;

	private String lastName;

	private String email;

	private String contact;

	private String country;

	private String state;

	private String district;

	private String region;
	
	private String territory;

	private String siteId;

	private String tinNo;

	@NotNull(message = "Mobile Number is mandatory")
	@Size(min=9, max=9, message = "{mobile.number.validation}")
	private String msisdn;
	
	private String auuid;	
	
	private String airtelHandsetName;	
	
	private String airtelHandsetImei;	
	
	private String cpContract;
	
	private Set<UserIdDetailsDto> userIdDetail;

	private Set<UserImageDetailDto> userImageDetail;

	// private List<RoleDto> roles;

	private List<DepartmentDto> userDepartment;

	private List<PermissionDto> userPermission;	
	
	private String dob;	
	

	@NotNull(message = "id card number is mandatory")
	@Size(min=5, max=5, message = "{id.card.validation.msg}")
	private String idCardNo;	

	private String distributorName;	

	private String tsmAuuid;	
	
	private String editInfoAccess;	
	
	private String simSwapAccess;	
	
	private String channelPartnerCellId;	
	
	private String latitude;	
	
	private String longitude;	
	
	private String permissableRadius;
	
	private String deviceId;
	
	private String message;
	
	//newly added for v27 ppt
	private String commissionMobileNo;	
	
	private String distributorId;	
	
	private String distributorMsisdn;
	
	private String deviceName;
	
	private String dealerCode;
	
	public String getAuuid() {
		return auuid;
	}

	public void setAuuid(String auuid) {
		this.auuid = auuid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public Set<UserIdDetailsDto> getUserIdDetail() {
		return userIdDetail;
	}

	public void setUserIdDetail(Set<UserIdDetailsDto> userIdDetail) {
		this.userIdDetail = userIdDetail;
	}

	public Set<UserImageDetailDto> getUserImageDetail() {
		return userImageDetail;
	}

	public void setUserImageDetail(Set<UserImageDetailDto> userImageDetail) {
		this.userImageDetail = userImageDetail;
	}

	/*
	 * public List<RoleDto> getRoles() { return roles; }
	 * 
	 * public void setRoles(List<RoleDto> roles) { this.roles = roles; }
	 */

	public List<DepartmentDto> getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(List<DepartmentDto> userDepartment) {
		this.userDepartment = userDepartment;
	}

	public List<PermissionDto> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(List<PermissionDto> userPermission) {
		this.userPermission = userPermission;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	
	public String getEditInfoAccess() {
		return editInfoAccess;
	}

	public void setEditInfoAccess(String editInfoAccess) {
		this.editInfoAccess = editInfoAccess;
	}

	public String getSimSwapAccess() {
		return simSwapAccess;
	}

	public void setSimSwapAccess(String simSwapAccess) {
		this.simSwapAccess = simSwapAccess;
	}

	public String getChannelPartnerCellId() {
		return channelPartnerCellId;
	}

	public void setChannelPartnerCellId(String channelPartnerCellId) {
		this.channelPartnerCellId = channelPartnerCellId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPermissableRadius() {
		return permissableRadius;
	}

	public void setPermissableRadius(String permissableRadius) {
		this.permissableRadius = permissableRadius;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCommissionMobileNo() {
		return commissionMobileNo;
	}

	public void setCommissionMobileNo(String commissionMobileNo) {
		this.commissionMobileNo = commissionMobileNo;
	}

	public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributorMsisdn() {
		return distributorMsisdn;
	}

	public void setDistributorMsisdn(String distributorMsisdn) {
		this.distributorMsisdn = distributorMsisdn;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getAirtelHandsetName() {
		return airtelHandsetName;
	}

	public void setAirtelHandsetName(String airtelHandsetName) {
		this.airtelHandsetName = airtelHandsetName;
	}

	public String getAirtelHandsetImei() {
		return airtelHandsetImei;
	}

	public void setAirtelHandsetImei(String airtelHandsetImei) {
		this.airtelHandsetImei = airtelHandsetImei;
	}

	public String getCpContract() {
		return cpContract;
	}

	public void setCpContract(String cpContract) {
		this.cpContract = cpContract;
	}

	public String getTsmAuuid() {
		return tsmAuuid;
	}

	public void setTsmAuuid(String tsmAuuid) {
		this.tsmAuuid = tsmAuuid;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

}