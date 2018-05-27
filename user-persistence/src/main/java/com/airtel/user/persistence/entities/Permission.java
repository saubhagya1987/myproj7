package com.airtel.user.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "permission")
public class Permission extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "permission_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
	@SequenceGenerator(allocationSize = 1,name = "permission_seq", sequenceName = "permission_seq")
	private Integer permissionId;
	
	@Column(name = "permission_name", unique = true, nullable = false, length = 45)
	private String permissionName;
 
	//@Basic(optional = false)
	@Column(name = "description", length = 120)
	private String description;
	
	
	/*@JsonBackReference("permission")
	@Fetch(FetchMode.SELECT)
	@ManyToMany(mappedBy = "permission",fetch=FetchType.EAGER)
	private List<UserDetails> userDetails;*/

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

		
	
}