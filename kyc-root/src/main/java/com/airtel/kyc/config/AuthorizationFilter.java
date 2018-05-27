/*package com.airtel.kyc.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.util.NestedServletException;

public class AuthorizationFilter extends OAuth2ClientContextFilter {
	
	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
				throws IOException, ServletException {
		
		
		
			HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
	 		request.setAttribute(CURRENT_URI, calculateCurrentUri(request));
	 
	 		try {
	 			
	 			
	 			HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
	 	        String remote_addr = request.getRemoteAddr();        
	 	        System.out.println(requestWrapper.getHeader("Authorization"));
	 	        if(requestWrapper.getHeader("Authorization")!=null){
	 	        	String headerName=requestWrapper.getHeader("Authorization");
	 	            String[] parts = headerName.split(" ");
	 	           
	 	            String encodedPart = parts[1];
	 	        	String base64String=encodedPart;
	 	        	byte[] valueDecoded= Base64.decodeBase64(base64String );
	 	        	String sdecode= new String(valueDecoded);
	 	            requestWrapper.addHeader("Authorization", sdecode);
	 	            System.out.println(requestWrapper.getHeader("Authorization"));
	 	        }
	 	        
	 	        chain.doFilter((ServletRequest) requestWrapper, response);
	 			
	 			
	 			
	 			
	 			chain.doFilter(servletRequest, servletResponse);
	 		}
	 		catch (IOException ex) {
	 			throw ex;
	 		}
	 		catch (Exception ex) {
	 			// Try to extract a SpringSecurityException from the stacktrace
	 			Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
	 			UserRedirectRequiredException redirect = (UserRedirectRequiredException) throwableAnalyzer
	 					.getFirstThrowableOfType(UserRedirectRequiredException.class, causeChain);
	 			if (redirect != null) {
	 				redirectUser(redirect, request, response);
	 			}
	 			else {
	 				if (ex instanceof ServletException) {
	 					throw (ServletException) ex;
	 				}
	 				if (ex instanceof RuntimeException) {
	 					throw (RuntimeException) ex;
	 				}
	 				throw new NestedServletException("Unhandled exception", ex);
	 			}
	 		}
	 	}

	
	  
	    public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
	        *//**
	         * construct a wrapper for this request
	         * 
	         * @param request
	         *//*
	        public HeaderMapRequestWrapper(HttpServletRequest request) {
	            super(request);
	        }

	        private Map<String, String> headerMap = new HashMap<String, String>();

	        *//**
	         * add a header with given name and value
	         * 
	         * @param name
	         * @param value
	         *//*
	        public void addHeader(String name, String value) {
	            headerMap.put(name, value);
	        }

	        @Override
	        public String getHeader(String name) {
	            String headerValue = super.getHeader(name);
	            if (headerMap.containsKey(name)) {
	                headerValue = headerMap.get(name);
	            }
	            return headerValue;
	        }

	        *//**
	         * get the Header names
	         *//*
	        @Override
	        public Enumeration<String> getHeaderNames() {
	            List<String> names = Collections.list(super.getHeaderNames());
	            for (String name : headerMap.keySet()) {
	                names.add(name);
	            }
	            return Collections.enumeration(names);
	        }

	        @Override
	        public Enumeration<String> getHeaders(String name) {
	            List<String> values = Collections.list(super.getHeaders(name));
	            if (headerMap.containsKey(name)) {
	                values.add(headerMap.get(name));
	            }
	            return Collections.enumeration(values);
	        }

	    }


}
*/