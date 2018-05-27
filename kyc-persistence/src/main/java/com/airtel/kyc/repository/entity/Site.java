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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.airtel.kyc.repository.listener.AuditedEntityListener;

@Entity
@Table(name = "site")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Site.findByClusterId", query = " Select s from Site s where cluster_.clusterId=:clusterId")	
	
})
public class Site extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_seq")
	@SequenceGenerator(allocationSize = 1, name = "site_seq", sequenceName = "site_seq")
	private Integer id;

	@Column(name = "site_id")
	private Integer siteId;

	@Column(name = "site_name")
	private String siteName;

	
	@Fetch(FetchMode.SELECT)
	@LazyCollection(LazyCollectionOption.TRUE)
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "cluster_id", nullable = false)
	private Cluster_ cluster_;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}