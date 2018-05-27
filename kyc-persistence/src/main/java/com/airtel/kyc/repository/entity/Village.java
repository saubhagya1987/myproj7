package com.airtel.kyc.repository.entity;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.airtel.kyc.repository.listener.AuditedEntityListener;

@Entity
@Table(name = "village")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({	
	@NamedQuery(name = "Village.findByDistrictId", query = " Select p from Village p where district.districtId=:districtId"),
	@NamedQuery(name = "Village.findByVillageId", query = " Select p from Village p where villageId=:villageId"),
	@NamedQuery(name = "Village.findByVillageName", query = " Select p from Village p where villageName=:villageName")
})
public class Village implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "village_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "village_seq")
	@SequenceGenerator(allocationSize = 1, name = "village_seq", sequenceName = "village_seq")
	private Integer villageId;

	@Column(name = "village_name")
	private String villageName;
	
	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

	

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}


	

}
