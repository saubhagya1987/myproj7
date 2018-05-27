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

@Entity
@Table(name = "zone")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Zone.findAll", query = " Select z from Zone z ")	
	
})
public class Zone extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "zone_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_seq")
	@SequenceGenerator(allocationSize = 1, name = "zone_seq", sequenceName = "zone_seq")
	private Integer zoneId;

	@Column(name = "zone_name")
	private String zoneName;

	@LazyCollection(LazyCollectionOption.TRUE)
	@Fetch(FetchMode.SELECT)
	@OneToMany( mappedBy="zone", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private List<Branch> branches;


	public List<Branch> getBranchs() {
		return branches;
	}

	public void setBranchs(List<Branch> branchs) {
		this.branches = branchs;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

}