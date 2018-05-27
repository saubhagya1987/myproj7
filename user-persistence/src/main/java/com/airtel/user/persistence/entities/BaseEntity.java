package com.airtel.user.persistence.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
//@Audited
//@EntityListeners(AuditedEntityListener.class)
public class BaseEntity {


	@Column(name = "created_by")
	 private String createdBy;
	
	@Column(name = "updated_by")
	 private String updatedBy;
	
	@Column(name = "created_on")
	 private Timestamp createdOn;
	
	@Column(name = "updated_on")
	 private Timestamp updatedOn;
	
	/** The status flag. */
	@Column(name = "status_flag", columnDefinition = "char default 0")
	private Integer statusFlag;

	/** The delete flag. */
	@Column(name = "delete_flag", columnDefinition = "char default 1")
	private Integer deleteFlag;

	/** The version. */
	/*@Version
	@Column(name = "version", nullable = false)
	private long version;*/

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/*public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}*/
	
	
 
}