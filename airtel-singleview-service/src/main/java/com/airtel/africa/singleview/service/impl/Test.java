package com.airtel.africa.singleview.service.impl;

import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.xml.rpc.Service;

import com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest;
import com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse;
import com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCLocator;
import com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub;



public class Test {

    public static void main(String[] args) throws Exception {

    	// configure the SSLContext with a TrustManager
        /*SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {}, new SecureRandom());
        SSLContext.setDefault(ctx);*/
        
        URL wsdlUrl = new URL("https://172.23.3.45:8186/services/KYC.KYCHttpSoap11Endpoint");
        KYCLocator locator= new KYCLocator();
        Service service = (Service)locator.getCall();
        
        KYCSoap11BindingStub stub = new KYCSoap11BindingStub(wsdlUrl,service);
        
        GetMSISDNStatusRequest parameters=new GetMSISDNStatusRequest();
		parameters.setSUBSCRIBER_NO("704008823");
		GetMSISDNStatusResponse getMSISDNStatusResponse = stub.getMSISDNStatusRequest_Fetch(parameters);	
        
        System.out.println("-----------"+getMSISDNStatusResponse.getRESULT().getMESSAGE());
	}

}
