package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.airtel.kyc.repository.entity.BaseEntity;

@Entity
@Table(name = "config_data")
@NamedQueries({
	@NamedQuery(name = "ConfigData.findAll", query = " Select c from ConfigData c"),
	@NamedQuery(name = "ConfigData.findByDataId", query = " Select c from ConfigData c where configDataId=:configDataId"),
})
public class ConfigData extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "config_data_id")
	private Integer configDataId;
	
	@Column(name = "config_data_name", nullable = false)
	private String configDataName;
	
	@Column(name = "config_data_value")
	private String configDataValue;

	public Integer getConfigDataId() {
		return configDataId;
	}

	public void setConfigDataId(Integer configDataId) {
		this.configDataId = configDataId;
	}

	public String getConfigDataName() {
		return configDataName;
	}

	public void setConfigDataName(String configDataName) {
		this.configDataName = configDataName;
	}

	public String getConfigDataValue() {
		return configDataValue;
	}

	public void setConfigDataValue(String configDataValue) {
		this.configDataValue = configDataValue;
	}
}