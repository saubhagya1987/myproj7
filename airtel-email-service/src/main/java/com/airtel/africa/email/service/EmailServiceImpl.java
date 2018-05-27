package com.airtel.africa.email.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.airtel.africa.email.conf.EmailConf;
import com.airtel.africa.email.conf.EmailConstants;
import com.airtel.africa.email.exceptions.EmailException;
import com.airtel.africa.email.exceptions.EmailTemplateNotFoundException;
import com.airtel.africa.email.exceptions.InvalidTemplateException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Hem Chand
 * 
 */
@Service
public class EmailServiceImpl implements EmailService, EmailConstants {

	private static Log log = LogFactory.getLog(EmailServiceImpl.class);
	private static boolean isInfoEnabled = log.isInfoEnabled();
	private static boolean isDebugEnabled = log.isDebugEnabled();
	private EmailConf emailConf;

	@Autowired
	public void setEmailConf(EmailConf emailConf) {
		this.emailConf = emailConf;
	}

	/*
	 * @method getStringFromFTLResource
	 * 
	 * for fetching & processing templates
	 */
	private String processFTLResource(String ftl, Map<String, Object> mapProcess)
			throws IOException, TemplateException {
		String xmlReqest = "";
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/");
		Template randomViewTemplate = cfg.getTemplate(ftl);
		xmlReqest = FreeMarkerTemplateUtils.processTemplateIntoString(
				randomViewTemplate, mapProcess);
		return xmlReqest;

	}

	public void sendEmail(String receiver, String sender, String subject,
			String emailBody) throws EmailException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendEmail started");
		}
		sendEmail(receiver, sender, subject, emailBody, null, null);
		if (isInfoEnabled) {
			log.info("Method : sendEmail Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}
	}

	public void sendEmail(String receiver, String sender, String subject,
			String emailBody, String cc) throws EmailException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendEmail started");
		}
		sendEmail(receiver, sender, subject, emailBody, null, cc);
		if (isInfoEnabled) {
			log.info("mail send sccessfully");
			log.info("Method : sendEmail Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}
	}

	public void sendEmail(String receiver, String sender, String subject,
			String emailBody, Map<String, byte[]> filenameFile)
			throws EmailException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendEmail started");
		}
		sendEmail(receiver, sender, subject, emailBody, filenameFile, null);
		if (isInfoEnabled) {
			log.info("mail send sccessfully");
			log.info("Method : sendEmail Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}
	}

	public void sendEmail(String receiver, String sender, String subject,
			String emailBody, Map<String, byte[]> filenameFile, String cc)
			throws EmailException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendEmail started");
		}
		MimeMessage message = new MimeMessage(this.emailConf.mailSender()
				.getSession());
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(message, true);
		} catch (MessagingException e2) {
			if (isDebugEnabled) {
				log.debug("Exception logged : " + e2.getMessage());
				e2.printStackTrace();
			}
			throw new EmailException(EMAIL_EXCEPTION_MESSAGE);
		}
		if (isDebugEnabled) {
			log.debug("sender : " + sender + ", receiver : " + receiver
					+ ", subject : " + subject + ", content size : "
					+ emailBody.length());
		}
		try {
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receiver));
			message.setSubject(subject);
			message.setContent(emailBody, "text/html");
			if (cc != null && !cc.equals("")) {
				String ccArr[] = cc.split(",");
				mimeMessageHelper.setCc(ccArr);
			}
			if (filenameFile != null) {
				for (Map.Entry<String, byte[]> entry : filenameFile.entrySet()) {
					mimeMessageHelper.addAttachment(entry.getKey(),
							new ByteArrayResource(entry.getValue()));
				}
			}
		} catch (MessagingException e1) {
			if (isDebugEnabled) {
				log.debug("Exception logged : " + e1.getMessage());
				e1.printStackTrace();
			}
			throw new EmailException(EMAIL_EXCEPTION_MESSAGE);
		}
		try {
			System.out.println("message about to send on : " + receiver);
			this.emailConf.mailSender().send(message);
		} catch (Exception e) {
			if (isDebugEnabled) {
				log.debug("Exception logged : " + e.getMessage());
				e.printStackTrace();
			}
			throw new EmailException(EMAIL_EXCEPTION_MESSAGE);
		}
		if (isInfoEnabled) {
			log.info("mail send sccessfully");
			log.info("Method : sendEmail Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}
	}

	public void sendTemplateInEmail(String receiver, String sender,
			String subject, String templateName, Map<String, Object> map,
			String cc) throws EmailException, EmailTemplateNotFoundException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendTemplateInEmail started");
		}
		String text = null;
		try {
			text = processFTLResource(templateName, map);
		} catch (IOException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new EmailTemplateNotFoundException();
		} catch (TemplateException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new InvalidTemplateException();
		}
		sendEmail(receiver, sender, subject, text, null, cc);
		if (isInfoEnabled) {
			log.info("mail send sccessfully");
			log.info("Method : sendEmail Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}
	}

	public void sendTemplateInEmail(String receiver, String sender,
			String subject, String templateName, Map<String, Object> map,
			String cc, Map<String, byte[]> filenameFile) throws EmailException,
			EmailTemplateNotFoundException, InvalidTemplateException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendTemplateInEmail started");
		}
		String text = null;
		try {
			text = processFTLResource(templateName, map);
		} catch (IOException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new EmailTemplateNotFoundException();
		} catch (TemplateException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new InvalidTemplateException();
		}
		sendEmail(receiver, sender, subject, text, filenameFile, cc);
		if (isInfoEnabled) {
			log.info("mail send sccessfully");
			log.info("Method : sendEmail Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}
	}

}
