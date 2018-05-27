package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String msisdn;
	private String notification_name;
	private String notification_body;
	private String receiver;
	private String templateContent;
	private String templateName;
	private String templatePropertyKey;
	private Map<String, Object> map;
	private String sender;
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNotification_name() {
		return notification_name;
	}

	public void setNotification_name(String notification_name) {
		this.notification_name = notification_name;
	}

	public String getNotification_body() {
		return notification_body;
	}

	public void setNotification_body(String notification_body) {
		this.notification_body = notification_body;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getTemplatePropertyKey() {
		return templatePropertyKey;
	}

	public void setTemplatePropertyKey(String templatePropertyKey) {
		this.templatePropertyKey = templatePropertyKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
