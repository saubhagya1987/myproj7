package com.airtel.africa.email.service;

import java.util.Map;

import com.airtel.africa.email.exceptions.EmailException;
import com.airtel.africa.email.exceptions.EmailTemplateNotFoundException;
import com.airtel.africa.email.exceptions.InvalidTemplateException;

/**
 * @author Hem Chand
 * 
 */
public interface EmailService {

	/*
	 * @Description - method for processing template and send it into the mail
	 * body.
	 * 
	 * @method sendTemplateInEmail
	 * 
	 * @parameters templateName: name of freemarker template should be kept in
	 * resource map : map object that need to process with ftl template
	 */
	public void sendTemplateInEmail(String receiver, String sender,
			String subject, String templateName, Map<String, Object> map,
			String cc) throws EmailException, EmailTemplateNotFoundException,
			InvalidTemplateException;

	/*
	 * @Description - method for processing template and send it into the mail
	 * body with attachment.
	 * 
	 * @method sendTemplateInEmail
	 * 
	 * @parameters templateName: name of freemarker template exsist in resource
	 * map : map object that need to process with ftl template filenameFile :
	 * filenameFile is the map of file name and its byte array that is to send
	 * as attachments with the mail
	 */
	public void sendTemplateInEmail(String receiver, String sender,
			String subject, String templateName, Map<String, Object> map,
			String cc, final Map<String, byte[]> filenameFile)
			throws EmailException, EmailTemplateNotFoundException,
			InvalidTemplateException;

	/*
	 * @Description - method for sending mail with subjet and email body
	 * 
	 * @method sendEmail
	 * 
	 * @parameters self-elaborated
	 */
	public void sendEmail(String receiver, String sender, String subject,
			String emailBody) throws EmailException;

	/*
	 * @Description - method for sending mail with subjet and email body wit
	 * cc(ie.cc=example1@example.com,example2@example.com)
	 * 
	 * @method sendEmail
	 * 
	 * @parameters self-elaborated cc : string of emails with comma seprated on
	 * which mail need to be triggerd as cc
	 */
	public void sendEmail(String receiver, String sender, String subject,
			String emailBody, String cc) throws EmailException;

	/*
	 * @Description - method for sending mail with subjet and email body
	 * 
	 * @method sendEmail
	 * 
	 * @parameters self-elaborated
	 */
	public void sendEmail(String receiver, String sender, String subject,
			String emailBody, final Map<String, byte[]> filenameFile)
			throws EmailException;

	public void sendEmail(String receiver, String sender, String subject,
			String emailBody, final Map<String, byte[]> filenameFile, String cc)
			throws EmailException;

}
