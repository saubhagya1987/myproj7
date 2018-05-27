package com.airtel.kyc.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomFilterWrapper extends HttpServletRequestWrapper {
	String header=null;
	public CustomFilterWrapper(HttpServletRequest request) {
		
        super(request);
       // String header=request.getHeader("Authorization");
        header=request.getHeader("Authorization");
        if(header!=null){
        	System.out.println("header----------"+header);
        }
    }

    @Override
    public String getParameter(String encyptedValue) {


        //String[] vals = getParameterMap().get(encyptedValue);
        String decryptedValue= null;

        if (encyptedValue!=null) {
        	decryptedValue= encyptedValue;

        }
		return decryptedValue; 


    }
    
 /*   @Override
    public String getHeader(String name){
        //if(name.equalsIgnoreCase("Authorization")){
    	String header1 = super.getHeader("Authorization");
    	if(header1!=null){
    		System.out.println("header1 name"+header1);
        	System.out.println("bearer name"+header);
            String headerName=header1;
            String[] parts = headerName.split(" ");
           
            String encodedPart = parts[1];
        	String base64String=encodedPart;
        	byte[] valueDecoded= Base64.decodeBase64(base64String );
        	String sdecode= new String(valueDecoded);
        	System.out.println("decoded name"+sdecode);
        	String decodeheaderobj=super.getHeader(sdecode);
        	System.out.println("decodeheaderobj"+decodeheaderobj);
        	return decodeheaderobj;
    	}
    	else{
    		return null;
    	}
    	
        	
        	//return "Bearer " + jwt;
        	
        //}
          //return "Bearer " + encodedPart;
                 
    }*/

}
