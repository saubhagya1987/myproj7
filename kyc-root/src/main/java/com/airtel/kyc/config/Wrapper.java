package com.airtel.kyc.config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Wrapper extends HttpServletRequestWrapper {
	
	public Wrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String tokenValue) {


        String[] vals = getParameterMap().get(tokenValue);
        String decryptedTokenValue= null;

        if (tokenValue!=null && tokenValue.equals("abc")) {
        	decryptedTokenValue= tokenValue;

        }
       
		return decryptedTokenValue; 


    }

}


