package com.airtel.user.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.cache.annotation.Cacheable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@JsonInclude(value = Include.NON_NULL)
@Entity
@Table(name = "users")
//@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="users")
public class Users extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(allocationSize = 1, name = "user_seq", sequenceName = "user_seq")
	private Integer userId;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;
	
	@Column(name = "auuid", unique = true)	
	private String auuid;

	@Column(name = "password", nullable = true, length = 60)
	private String password;

	@Column(name = "agent_id")
	private Long agentId;

	@Column(name = "device_id", length = 60, unique = true)
	private String deviceId;

	@Column(name = "is_bulk_enabled")
	private String bulkEnabled;

	@Column(name = "enabled")
	private String enabled;

	@Column(name = "pwd_created_on")
	private Timestamp pwdCreatedOn;

	@Column(name = "is_password_modified", columnDefinition = "char default 1")
	private Integer isPasswordModified;

	@Column(name = "pwd_expire_in")
	private Timestamp pwdExpireIn;

	@Column(name = "failure_reason")
	private String failureReason;

	@Column(name = "last_login")
	private Timestamp lastLogin;

	@Column(name = "login_device_id", length = 60)
	private String loginDeviceId;

	@Column(name = "device_type", length = 60)
	private String deviceType;
	
	@Column(name = "is_deleted", columnDefinition = "char default 0")
	private Integer isDeleted;

	@Column(name = "is_blocked", columnDefinition = "char default 1")
	private Integer isBlocked;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "otp_creation_time")
	private Timestamp otpCreationTime;
	
	@Column(name = "user_transaction_id")
	private String userTransactionId;
	
	@Column(name = "retry_attempt", columnDefinition = "char default 0")
	private Integer retryAttempt;
	

	@Column(name = "otp_count", columnDefinition = "char default 0")
	private Integer otpCount;
	
	@Column(name = "notification_token")
	private String notificationToken;
	
	/*@Column(name = "TEMP_PASS", nullable = true, length = 60)
	private String tempPass;*/
	/*
	 * @JsonManagedReference(value="userDetails")
	 * 
	 * @OneToOne(mappedBy = "users",fetch = FetchType.LAZY)
	 * 
	 * @Cascade({CascadeType.ALL}) private UserDetails userDetails;
	 */

	@JsonManagedReference("userDetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<UserDetails> userDetails;

	@JsonManagedReference(value = "roles")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false) })
	private List<Role> roles;

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

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLoginDeviceId() {
		return loginDeviceId;
	}

	public void setLoginDeviceId(String loginDeviceId) {
		this.loginDeviceId = loginDeviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getIsPasswordModified() {
		return isPasswordModified;
	}

	public void setIsPasswordModified(Integer isPasswordModified) {
		this.isPasswordModified = isPasswordModified;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Integer isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAuuid() {
		return auuid;
	}

	public void setAuuid(String auuid) {
		this.auuid = auuid;
	}

	public Timestamp getOtpCreationTime() {
		return otpCreationTime;
	}

	public void setOtpCreationTime(Timestamp otpCreationTime) {
		this.otpCreationTime = otpCreationTime;
	}

	public String getUserTransactionId() {
		return userTransactionId;
	}

	public void setUserTransactionId(String userTransactionId) {
		this.userTransactionId = userTransactionId;
	}

	public Integer getRetryAttempt() {
		return retryAttempt;
	}

	public void setRetryAttempt(Integer retryAttempt) {
		this.retryAttempt = retryAttempt;
	}

	public Integer getOtpCount() {
		return otpCount;
	}

	public void setOtpCount(Integer otpCount) {
		this.otpCount = otpCount;
	}

	public String getNotificationToken() {
		return notificationToken;
	}

	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	/*public String getTempPass() {
		return tempPass;
	}

	public void setTempPass(String tempPass) {
		this.tempPass = tempPass;
	}*/

}