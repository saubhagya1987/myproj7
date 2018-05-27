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
@Table(name = "district")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "District.findAll", query = " Select d from District d"),
	@NamedQuery(name = "District.findByDistrictId", query = " Select d from District d where districtId=:districtId"),
	@NamedQuery(name = "District.findByDistrictName", query = " Select d from District d where districtName=:districtName"),
	@NamedQuery(name = "District.findByProvinceId", query = " Select d from District d where province.provinceId=:provinceId")
	
})
public class District extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "district_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_seq")
	@SequenceGenerator(allocationSize = 1, name = "district_seq", sequenceName = "district_seq")
	private Integer districtId;

	@Column(name = "district_name")
	private String districtName;
	
	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "province_id", nullable = false)
	private Province province;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "district", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private List<Village> village;

	
	
	/*public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
*/

	

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Village> getVillage() {
		return village;
	}

	public void setVillage(List<Village> village) {
		this.village = village;
	}

}