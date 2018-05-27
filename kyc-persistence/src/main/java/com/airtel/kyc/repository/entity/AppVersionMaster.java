package com.airtel.kyc.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "APP_VERSION_MASTER")
@NamedQueries({	
	@NamedQuery(name = "AppVersionMaster.findByAppId", query = " Select a from AppVersionMaster a where appId=:appId")
		
})
public class AppVersionMaster {	
	
	@Id	
	@Column(name = "app_id")	
	private String appId;
	
	@Column(name = "app_url")
	private String appUrl;
	
	@Column(name = "app_version")
	private String appVersion;
	
	@Column(name = "location_data_url")
	private String locationDataUrl;
	@Column(name = "location_data_version")
	private String locationDataVersion;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getLocationDataUrl() {
		return locationDataUrl;
	}
	public void setLocationDataUrl(String locationDataUrl) {
		this.locationDataUrl = locationDataUrl;
	}
	public String getLocationDataVersion() {
		return locationDataVersion;
	}
	public void setLocationDataVersion(String locationDataVersion) {
		this.locationDataVersion = locationDataVersion;
	}
	

	

}
