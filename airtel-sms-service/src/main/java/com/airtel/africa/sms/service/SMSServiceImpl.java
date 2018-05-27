package com.airtel.africa.sms.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.airtel.africa.sms.constant.HTTPParam;
import com.airtel.africa.sms.constant.SMSConstants;
import com.airtel.africa.sms.exceptions.SMSException;
import com.sun.mail.util.BASE64EncoderStream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Hem Chand
 * 
 */
@Service
public class SMSServiceImpl implements SMSService, SMSConstants {

	private static Log log = LogFactory.getLog(SMSServiceImpl.class);
	private static boolean isInfoEnabled = log.isInfoEnabled();
	private static boolean isDebugEnabled = log.isDebugEnabled();

	public void sendSMS(HTTPParam httpParam, Map<String, Object> modelMap,
			String requestTemplate) throws SMSException {
		long st = System.currentTimeMillis();
		if (isInfoEnabled) {
			log.info("Method : sendSMS started");
		}
		if (isDebugEnabled) {
			log.debug(" HTTPParam : " + httpParam.toString());
			log.debug(" keys to process for ftl : " + modelMap.keySet());
		}

		// Process rquest Template in .ftl extension should be kept in resource
		String xmlReqest = "";
		try {
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(this.getClass(), "/");
			Template randomViewTemplate = cfg.getTemplate(requestTemplate);
			xmlReqest = FreeMarkerTemplateUtils.processTemplateIntoString(
					randomViewTemplate, modelMap);
			if (isDebugEnabled) {
				log.debug(" Requet created : " + xmlReqest);
			}
		} catch (IOException | TemplateException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new SMSException("Exception in processing Template : "
					+ e.getMessage());
		}
		final org.apache.http.params.HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		HttpClient client = new DefaultHttpClient(httpParams);

		// Authentication
		// String token = "2314641ap:2314641ap";
		String token = httpParam.getUsername() + ":" + httpParam.getPassword();
		byte[] encoding = BASE64EncoderStream.encode(token.getBytes());

		// URL to Hit
		// HttpPost post = new HttpPost("http://172.23.12.99:55000");
		HttpPost post = new HttpPost(httpParam.getUrl());
		post.setHeader("Authorization", "Basic " + new String(encoding));

		// Request Body
		StringEntity entity;
		try {
			entity = new StringEntity(xmlReqest);
			// entity.setContentType("text/xml");
		} catch (UnsupportedEncodingException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new SMSException("Exception in Setting Request body : "
					+ e.getMessage());

		}
		entity.setContentType(httpParam.getContentType());
		post.setEntity(entity);

		HttpResponse response;
		try {
			response = client.execute(post);

			HttpEntity httpEntity = response.getEntity();
			String responseMsg = EntityUtils.toString(httpEntity);
			if (isDebugEnabled) {
				log.debug("Response Message from sms service   ("
						+ response.getStatusLine().getStatusCode() + " ): "
						+ responseMsg);
			}
		} catch (IOException e) {
			if (isDebugEnabled) {
				e.printStackTrace();
			}
			throw new SMSException(
					"Exception in communicating with sms service : "
							+ e.getMessage());
		}
		if (isInfoEnabled) {
			log.info("sms send sccessfully");
			log.info("Method : sendSMS Ended  "
					+ (System.currentTimeMillis() - st) + " ms.");
		}

	}

}
