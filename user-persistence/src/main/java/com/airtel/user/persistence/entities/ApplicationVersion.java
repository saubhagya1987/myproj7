/**
 * 
 */
package com.airtel.user.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import com.airtel.user.persistence.listener.AuditedEntityListener;

/**
 * @author B0079855
 *
 */
@Entity
@Table(name = "app_version_master")
@EntityListeners(AuditedEntityListener.class)
public class ApplicationVersion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OS_VERSION", length=20)
	private String osVersion;
	
	@Column(name = "APP_VERSION", length=20, nullable=true)
	private String appVersion;
	
	@Column(name = "APP_URL", length=150, nullable=false)
	private String appUrl;

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	
	
}
