package com.airtel.africa.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	// HTTP POST request
	public static HttpURLConnection fetchResponse(String url, String MethodType, Map<String, String> parameterMap,
			String access_token, String body) throws Exception {
		int inc = 0;
		URL obj = new URL(url);
		StringBuilder builder = new StringBuilder();
		if (parameterMap != null) {
			for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
				if (inc == 0) {
					builder.append(entry.getKey() + "=" + entry.getValue());
				} else {
					builder.append("&" + entry.getKey() + "=" + entry.getValue());
				}
				inc++;
			}
		}
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(MethodType);
		
		
		
		if ("POST".equalsIgnoreCase(MethodType)) {
			con.setDoOutput(true);
		}
		
		if (access_token != null) {
			con.setRequestProperty("Authorization", "Bearer " + access_token);
		}
		if (body != null) {
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(body.getBytes());
				wr.writeBytes(builder.toString());
			}
		}
		return con;
	}

	// HTTP POST request
	public static String fetchResponseString(String url, String MethodType, Map<String, String> parameterMap,
			String access_token, String body) throws Exception {
		HttpURLConnection connection = fetchResponse(url, MethodType, parameterMap, access_token, body);
		StringBuffer responseString = null;
		int responseCode = connection.getResponseCode();
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (Exception exception) {
			// exception.printStackTrace();
		}
		String inputLine;
		if (in != null) {
			responseString = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				responseString.append(inputLine);
			}
			in.close();
			return responseString.toString();
		}
		return null;
	}

	
	public static String fireHttpRequest(String url, String methodType, String contentType, String requestBody,
			Map<String, String> parameterMap) {
		int inc = 0;
		StringBuilder builder = new StringBuilder();
		if (parameterMap != null) {
			for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
				if (inc == 0) {
					builder.append(entry.getKey() + "=" + entry.getValue());
				} else {
					builder.append("&" + entry.getKey() + "=" + entry.getValue());
				}
				inc++;
			}
		}
		DefaultHttpClient client = new DefaultHttpClient();
		if ("POST".equalsIgnoreCase(methodType)) {
			String finalUrl = url + "?" + builder.toString();
			HttpPost post = new HttpPost(finalUrl);
			try {
				StringEntity entity = new StringEntity(requestBody);
				entity.setContentType(contentType);
				post.setEntity(entity);
				HttpResponse response = client.execute(post);
				HttpEntity httpEntity = response.getEntity();
				String responseMsg = EntityUtils.toString(httpEntity);
				return responseMsg;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("GET".equalsIgnoreCase(methodType)) {

		}
		return null;
	}
	
	// HTTP POST request
	public static String sendRequest(String url, String MethodType, Map<String, String> parameterMap,
			String access_token) throws Exception {
		URL obj = new URL(url);
		StringBuffer response = new StringBuffer();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(MethodType);
		if (access_token != null) {
			con.setRequestProperty("Authorization", "Bearer " + access_token);
		}
		int inc = 0;
		StringBuilder builder = new StringBuilder();
		if (parameterMap != null) {
			for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
				if (inc == 0) {
					builder.append(entry.getKey() + "=" + entry.getValue());
				} else {
					builder.append("&" + entry.getKey() + "=" + entry.getValue());
				}
				inc++;
			}
		}
		String urlParameters = builder.toString();
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending '" + MethodType + "' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		String inputLine;
		if (in != null) {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		// print result
		return response.toString();
	}

	public static Object unmarshal(String xmlContent, Class<?> pojo) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(pojo);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(xmlContent.getBytes()));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String [] strings) {
		String url = "http://www.google.com/";
		try {
			String string =sendRequest(url, "GET", null, null);
			System.out.println(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
