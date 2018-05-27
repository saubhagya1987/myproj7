package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessTokenDto implements Serializable {

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

}
