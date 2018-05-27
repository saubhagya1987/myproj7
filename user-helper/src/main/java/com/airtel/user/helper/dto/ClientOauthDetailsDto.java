package com.airtel.user.helper.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ClientOauthDetailsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String client_id;

	private String client_secret;
	
	private Integer access_token_validity;
	
	private Integer refresh_token_validity;
	
	private String resource_ids;

	private String scope;

	private String authorized_grant_types;

	private String authorities;

	private String web_server_redirect_uri;

	private String additional_information;

	private String autoapprove;

	private String deviceId;
	
	private UsersDto user;

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public Integer getAccess_token_validity() {
		return access_token_validity;
	}

	public void setAccess_token_validity(Integer access_token_validity) {
		this.access_token_validity = access_token_validity;
	}

	public Integer getRefresh_token_validity() {
		return refresh_token_validity;
	}

	public void setRefresh_token_validity(Integer refresh_token_validity) {
		this.refresh_token_validity = refresh_token_validity;
	}

	public String getResource_ids() {
		return resource_ids;
	}

	public void setResource_ids(String resource_ids) {
		this.resource_ids = resource_ids;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorized_grant_types() {
		return authorized_grant_types;
	}

	public void setAuthorized_grant_types(String authorized_grant_types) {
		this.authorized_grant_types = authorized_grant_types;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getWeb_server_redirect_uri() {
		return web_server_redirect_uri;
	}

	public void setWeb_server_redirect_uri(String web_server_redirect_uri) {
		this.web_server_redirect_uri = web_server_redirect_uri;
	}

	public String getAdditional_information() {
		return additional_information;
	}

	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	public UsersDto getUser() {
		return user;
	}

	public void setUser(UsersDto user) {
		this.user = user;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	
}
