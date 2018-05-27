package com.airtel.user.helper.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown=true)
public class UsersDto extends BaseDto  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private Long subscriberId;

	private String userName;
	
	private String auuId;

	private Long agentId;

	private String password;
	
	private String ipAddress;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	private String deviceId;

	private String bulkEnabled;

	private String enabled;

	private Timestamp pwdCreatedOn;

	private Timestamp pwdExpireIn;

	private String failureReason;

	private List<RoleDto> roles;
	
	private List<RoleDto> subRole;
	
	private List<RoleDto> subRoles;
	
	//private RoleDto subRoles;

	// private UserDetailsDto userDetails;

	private List<UserDetailsDto> userDetails;

	private UserDetailsDto userDetail;

	private Integer bypassStatus;

	private LocationDto location;

	private Integer isPasswordModified ;
	
	private Integer isBlocked;
	
	private Integer isDeleted;
	
	private Integer userFlag;
	
	private Boolean auuIdFlag;
	
	private String message;
	
	private String otp;
	
	private String modelName;
	
	private Boolean notAllowed;
	
	private String userTransactionId;
	
	private String idCardNo;
	
	private String msisdn;
	
	private Integer uid;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	private String appVersion;
	
	private String notificationToken;

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getIsPasswordModified() {
		return isPasswordModified;
	}

	public void setIsPasswordModified(Integer isPasswordModified) {
		this.isPasswordModified = isPasswordModified;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public UserDetailsDto getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetailsDto userDetail) {
		this.userDetail = userDetail;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getBulkEnabled() {
		return bulkEnabled;
	}

	public void setBulkEnabled(String bulkEnabled) {
		this.bulkEnabled = bulkEnabled;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Timestamp getPwdCreatedOn() {
		return pwdCreatedOn;
	}

	public void setPwdCreatedOn(Timestamp pwdCreatedOn) {
		this.pwdCreatedOn = pwdCreatedOn;
	}

	public Timestamp getPwdExpireIn() {
		return pwdExpireIn;
	}

	public void setPwdExpireIn(Timestamp pwdExpireIn) {
		this.pwdExpireIn = pwdExpireIn;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public List<UserDetailsDto> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetailsDto> userDetails) {
		this.userDetails = userDetails;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public Integer getBypassStatus() {
		return bypassStatus;
	}

	public void setBypassStatus(Integer bypassStatus) {
		this.bypassStatus = bypassStatus;
	}

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	/*public RoleDto getSubRoles() {
		return subRoles;
	}

	public void setSubRoles(RoleDto subRoles) {
		this.subRoles = subRoles;
	}*/

	public List<RoleDto> getSubRole() {
		return subRole;
	}

	public void setSubRole(List<RoleDto> subRole) {
		this.subRole = subRole;
	}

	public List<RoleDto> getSubRoles() {
		return subRoles;
	}

	public void setSubRoles(List<RoleDto> subRoles) {
		this.subRoles = subRoles;
	}

	public Integer getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Integer isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}

	public Boolean getAuuIdFlag() {
		return auuIdFlag;
	}

	public void setAuuIdFlag(Boolean auuIdFlag) {
		this.auuIdFlag = auuIdFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAuuId() {
		return auuId;
	}

	public void setAuuId(String auuId) {
		this.auuId = auuId;
	}	

	public Boolean getNotAllowed() {
		return notAllowed;
	}

	public void setNotAllowed(Boolean notAllowed) {
		this.notAllowed = notAllowed;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getUserTransactionId() {
		return userTransactionId;
	}

	public void setUserTransactionId(String userTransactionId) {
		this.userTransactionId = userTransactionId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getNotificationToken() {
		return notificationToken;
	}

	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	
	

}