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
@Table(name = "cluster_")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Cluster_.findByBranchId", query = " Select c from Cluster_ c where branch.branchId=:branchId")	
	
})
public class Cluster_ extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cluster_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq")
	@SequenceGenerator(allocationSize = 1, name = "region_seq", sequenceName = "region_seq")
	private Integer clusterId;

	@Column(name = "cluster_name")
	private String clusterName;

	@LazyCollection(LazyCollectionOption.TRUE)
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "cluster_", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private List<Site> sites;

	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	
	
}