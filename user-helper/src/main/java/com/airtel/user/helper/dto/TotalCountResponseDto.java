package com.airtel.user.helper.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TotalCountResponseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userCount;
	private long roleCount;
	private long departmentCount;
	
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	public long getRoleCount() {
		return roleCount;
	}
	public void setRoleCount(long roleCount) {
		this.roleCount = roleCount;
	}
	public long getDepartmentCount() {
		return departmentCount;
	}
	public void setDepartmentCount(long departmentCount) {
		this.departmentCount = departmentCount;
	}
	
	
	
}
