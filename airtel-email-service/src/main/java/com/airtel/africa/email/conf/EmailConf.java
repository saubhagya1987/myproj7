package com.airtel.africa.email.conf;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.airtel.africa.email.exceptions.InvalidSMTPDetailException;

/**
 * @author Hem Chand
 *
 */
//@PropertySource({"classpath:email.properties","classpath:email-ext.properties"})
@PropertySource(value = {"file:/africa_agile/opt/kyc_zm/email.properties","file:/africa_agile/opt/kyc_zm/email-ext.properties"})
//@PropertySource(value = {"file:/C:/Users/user1/Desktop/kyc_zm/encyption/email.properties","file:/C:/Users/user1/Desktop/kyc_zm/encyption/email-ext.properties"})

@Component
public class EmailConf implements EmailConstants {

	private static Log log = LogFactory.getLog(EmailConf.class);
	private static boolean isDebugEnabled = log.isDebugEnabled();
	
	@Autowired
	private Environment environment;

	@Bean
	public JavaMailSenderImpl mailSender() {
		if (this.environment.getProperty(SMTP_HOST) == null
				|| this.environment.getProperty(SMTP_PORT) == null
				|| this.environment.getProperty(SMTP_USERNAME) == null) {
			if(isDebugEnabled) {
				log.debug("SMTP Details \nhost : "+this.environment.getProperty(SMTP_HOST));
				log.debug("SMTP Details \nport : "+this.environment.getProperty(SMTP_PORT));
				log.debug("SMTP Details \nusername : "+this.environment.getProperty(SMTP_USERNAME));
			}
			throw new InvalidSMTPDetailException(INVALID_EXCEPTION_MESSAGE);
		}
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(this.environment.getProperty(SMTP_HOST));
		javaMailSender.setPort(Integer.parseInt(this.environment
				.getProperty(SMTP_PORT)));
		javaMailSender.setUsername(this.environment.getProperty(SMTP_USERNAME));
		javaMailSender.setPassword(this.environment.getProperty(SMTP_PASSWORD));
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth",
				this.environment.getProperty(SMTP_AUTH));
		properties.setProperty("mail.smtp.starttls.enable",
				this.environment.getProperty(SMTP_START_TTLS));
		return properties;
	}
}
