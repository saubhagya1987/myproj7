/*package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.airtel.kyc.repository.listener.AuditedEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "region")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Region.findAll", query = " Select r from Region r")	
	
})
public class Region extends BaseEntity implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "region_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq")
	@SequenceGenerator(allocationSize = 1, name = "region_seq", sequenceName = "region_seq")
	private Integer regionId;

	@Column(name = "region_name")
	private String regionName;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "region", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private List<Territory> territory;



	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<Territory> getTerritory() {
		return territory;
	}

	public void setTerritory(List<Territory> territory) {
		this.territory = territory;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}*/