package com.airtel.user.helper.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoleDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2800789809085548239L;

	private Integer roleId;
	
	private String roleName;
 
	private String userRoleName;
	
	private String description;
	
	private String subRoleName;
	
	private Integer webAccessFlag;
	
	private Integer mobileAccessFlag;
	
	private Integer subRoleId;
	
	private Integer parentRoleId;
	
    private Integer deleteFlag;
	
	private Integer statusFlag;
	
	private List<RoleDto> subRoleList;
	
	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public Integer getWebAccessFlag() {
		return webAccessFlag;
	}

	public void setWebAccessFlag(Integer webAccessFlag) {
		this.webAccessFlag = webAccessFlag;
	}

	public Integer getMobileAccessFlag() {
		return mobileAccessFlag;
	}

	public void setMobileAccessFlag(Integer mobileAccessFlag) {
		this.mobileAccessFlag = mobileAccessFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleDto other = (RoleDto) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	

	public Integer getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(Integer subRoleId) {
		this.subRoleId = subRoleId;
	}

	public String getSubRoleName() {
		return subRoleName;
	}

	public void setSubRoleName(String subRoleName) {
		this.subRoleName = subRoleName;
	}

	public List<RoleDto> getSubRoleList() {
		return subRoleList;
	}

	public void setSubRoleList(List<RoleDto> subRoleList) {
		this.subRoleList = subRoleList;
	}

	public Integer getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(Integer parentRoleId) {
		this.parentRoleId = parentRoleId;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}
}