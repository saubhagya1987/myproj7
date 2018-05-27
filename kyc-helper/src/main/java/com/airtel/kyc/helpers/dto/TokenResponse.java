package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TokenResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] childRoleList;

    private String scope;

    private String userId;

    private String name;

    private String hasBulkRights;

    private Roles[] roles;

    private String userName;

    private String expires_in;

    private String token_type;

    private String isPasswordModified;

    private String refresh_token;

    private String deviceId;

    private String access_token;
    
    private Integer status;
    
    private Integer subRoleId;
    
    private Integer roleId;
    
    private Integer mobileAccessFlag;
    
    private Boolean macIdStatus;
    
    private Boolean userExist;
    
    private String responseMessage;
    
    private String unauthorized;
    
    private String encryptedValue;   
    
    private String appUrl;
    
    private String appVersion;
    
    private String locationDataUrl;
    
    private String locationDataVersion;
    
    public String getEncryptedValue() {
		return encryptedValue;
	}

	public void setEncryptedValue(String encryptedValue) {
		this.encryptedValue = encryptedValue;
	}

	private String failedAttempt;

    public String[] getChildRoleList ()
    {
        return childRoleList;
    }

    public void setChildRoleList (String[] childRoleList)
    {
        this.childRoleList = childRoleList;
    }

    public String getScope ()
    {
        return scope;
    }

    public void setScope (String scope)
    {
        this.scope = scope;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getHasBulkRights ()
    {
        return hasBulkRights;
    }

    public void setHasBulkRights (String hasBulkRights)
    {
        this.hasBulkRights = hasBulkRights;
    }

    public Roles[] getRoles ()
    {
        return roles;
    }

    public void setRoles (Roles[] roles)
    {
        this.roles = roles;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    public String getExpires_in ()
    {
        return expires_in;
    }

    public void setExpires_in (String expires_in)
    {
        this.expires_in = expires_in;
    }

    public String getToken_type ()
    {
        return token_type;
    }

    public void setToken_type (String token_type)
    {
        this.token_type = token_type;
    }

    public String getIsPasswordModified ()
    {
        return isPasswordModified;
    }

    public void setIsPasswordModified (String isPasswordModified)
    {
        this.isPasswordModified = isPasswordModified;
    }

    public String getRefresh_token ()
    {
        return refresh_token;
    }

    public void setRefresh_token (String refresh_token)
    {
        this.refresh_token = refresh_token;
    }

    public String getDeviceId ()
    {
        return deviceId;
    }

    public void setDeviceId (String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token (String access_token)
    {
        this.access_token = access_token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [childRoleList = "+childRoleList+", scope = "+scope+", userId = "+userId+", name = "+name+", hasBulkRights = "+hasBulkRights+", roles = "+roles+", userName = "+userName+", expires_in = "+expires_in+", token_type = "+token_type+", isPasswordModified = "+isPasswordModified+", refresh_token = "+refresh_token+", deviceId = "+deviceId+", access_token = "+access_token+"]";
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(Integer subRoleId) {
		this.subRoleId = subRoleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMobileAccessFlag() {
		return mobileAccessFlag;
	}

	public void setMobileAccessFlag(Integer mobileAccessFlag) {
		this.mobileAccessFlag = mobileAccessFlag;
	}

	public Boolean getMacIdStatus() {
		return macIdStatus;
	}

	public void setMacIdStatus(Boolean macIdStatus) {
		this.macIdStatus = macIdStatus;
	}

	public Boolean getUserExist() {
		return userExist;
	}

	public void setUserExist(Boolean userExist) {
		this.userExist = userExist;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getUnauthorized() {
		return unauthorized;
	}

	public void setUnauthorized(String unauthorized) {
		this.unauthorized = unauthorized;
	}

	public String getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(String failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getLocationDataUrl() {
		return locationDataUrl;
	}

	public void setLocationDataUrl(String locationDataUrl) {
		this.locationDataUrl = locationDataUrl;
	}

	public String getLocationDataVersion() {
		return locationDataVersion;
	}

	public void setLocationDataVersion(String locationDataVersion) {
		this.locationDataVersion = locationDataVersion;
	}

	

}
