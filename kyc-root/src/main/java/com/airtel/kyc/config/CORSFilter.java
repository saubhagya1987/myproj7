package com.airtel.kyc.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {	
	

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//response.setHeader("Access-Control-Allow-Origin", "10.56.188.37:8447");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Content-Disposition","attachment");
		//response.setHeader("X-Frame-Options","SAMEORIGIN");
		//response.setHeader("Strict-Transport-Security", "max-age=300; includeSubDomains;preload");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
		//chain.doFilter(request, response);
        //chain.doFilter(new CustomFilterWrapper((HttpServletRequest)request),(HttpServletResponse)response);
        
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
        //String remote_addr = request.getRemoteAddr();        
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
        
        
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	// https://stackoverflow.com/questions/2811769/adding-an-http-header-to-the-request-in-a-servlet-filter
    // http://sandeepmore.com/blog/2010/06/12/modifying-http-headers-using-java/
    // http://bijubnair.blogspot.de/2008/12/adding-header-information-to-existing.html
    /**
     * allow adding additional header entries to a request
     * 
     * @author wf
     * 
     */
    public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
        /**
         * construct a wrapper for this request
         * 
         * @param request
         */
        public HeaderMapRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private Map<String, String> headerMap = new HashMap<String, String>();

        /**
         * add a header with given name and value
         * 
         * @param name
         * @param value
         */
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

        /**
         * get the Header names
         */
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