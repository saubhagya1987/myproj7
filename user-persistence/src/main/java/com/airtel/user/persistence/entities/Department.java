package com.airtel.user.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="department")
public class Department extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
	@SequenceGenerator(allocationSize = 1,name = "department_seq", sequenceName = "department_seq")
	private Integer departmentId;
	
	@Column(name = "department_name",   unique = true, nullable = false, length = 45)
	private String departmentName;
 
	//@Basic(optional = false)
	@Column(name = "description", length = 120)
	private String description;
	
	/*@JsonBackReference("department")
	@Fetch(FetchMode.SELECT)
	@ManyToMany(mappedBy = "department",fetch=FetchType.EAGER)
	private List<UserDetails> userDetails;
	
	
	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}*/

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}