package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "device_id")
@NamedQueries({	
	@NamedQuery(name = "DeviceId.findByModelName", query = " Select d from DeviceId d where modelName=:modelName")
		
})
public class DeviceId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "device_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_id_seq")
	@SequenceGenerator(allocationSize = 1,name = "device_id_seq", sequenceName = "device_id_seq")
	private Integer deviceId;
	
	@Column(name = "model_name",   unique = true, nullable = false)
	private String modelName;
 
	@Column(name = "updated_by")
	private String updatedBy;	
	
	@Column(name = "updated_on")
	private Timestamp updatedOn;
	

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
