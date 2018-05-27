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

@Entity
@Table(name = "branch")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Branch.findByZoneId", query = " Select b from Branch b where zone.zoneId=:zoneId")	
	
})
public class Branch extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "branch_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq")
	@SequenceGenerator(allocationSize = 1, name = "region_seq", sequenceName = "region_seq")
	private Integer branchId;

	@Column(name = "branch_name")
	private String branchName;

	@LazyCollection(LazyCollectionOption.TRUE)
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private List<Cluster_> clusters;

	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "zone_id", nullable = false)
	private Zone zone;

	public List<Cluster_> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster_> clusters) {
		this.clusters = clusters;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
}