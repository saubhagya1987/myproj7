package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "province")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Province.findAll", query = " Select t from Province t"),
	@NamedQuery(name = "Province.findByProvinceName", query = " Select p from Province p where provinceName=:provinceName")
	//@NamedQuery(name = "Province.findByRegionId", query = " Select t from Territory t where region.regionId=:regionId")
	//@NamedQuery(name = "Territory.findByRegionList", query = " Select t from Territory t where region.regionId IN :regionIdList")	
	
})
public class Province implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "province_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_seq")
	@SequenceGenerator(allocationSize = 1, name = "province_seq", sequenceName = "province_seq")
	private Integer provinceId;

	@Column(name = "province_name")
	private String provinceName;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "province", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private List<District> districts;


	/*@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;*/


	

	public List<District> getDistricts() {
		return districts;
	}


	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}


	public Integer getProvinceId() {
		return provinceId;
	}


	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}


	public String getProvinceName() {
		return provinceName;
	}


	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


	/*public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}*/


}
