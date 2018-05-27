package com.airtel.user.persistence.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="role")
public class Role extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	@SequenceGenerator(allocationSize = 1,name = "role_seq", sequenceName = "role_seq")
	private Integer roleId;
	
	@Column(name = "role_name",  unique = true, nullable = false ,length = 45)
	private String roleName;
	
	@Column(name = "user_role_name")
	private String userRoleName;
 
	//@Basic(optional = false)
	@Column(name = "description", length = 120)
	private String description;
	
	@Column(name = "parent_role_name")
	private String parentRoleName;

	@Column(name = "web_access_flag", columnDefinition = "char default 0")
	private Integer webAccessFlag;
	
	@Column(name = "mobile_access_flag", columnDefinition = "char default 0")
	private Integer mobileAccessFlag;
	
	@JsonBackReference("roles")
	@Fetch(FetchMode.SELECT)
	@ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
	private List<Users> users;
	
	@Column(name = "PARENT_ROLE_ID")
	private Integer parentRoleId;
	
	/*@JsonBackReference("roles")
	@Fetch(FetchMode.SELECT)
	@ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
	//@Cascade({CascadeType.ALL})
	private List<UserDetails> userDetails;*/

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

	/*public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}*/

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getParentRoleName() {
		return parentRoleName;
	}

	public void setParentRoleName(String parentRoleName) {
		this.parentRoleName = parentRoleName;
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

	public Integer getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(Integer parentRoleId) {
		this.parentRoleId = parentRoleId;
	}
	

}