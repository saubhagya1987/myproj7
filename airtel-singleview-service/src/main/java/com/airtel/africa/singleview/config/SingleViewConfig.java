package com.airtel.africa.singleview.config;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCLocator;
import com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub;
import com.airtel.africa.singleview.helpers.SVConstants;

//@PropertySource({ "classpath:sv.properties" })
@PropertySource(value = {"file:/africa_agile/opt/kyc_zm/sv.properties"})
//@PropertySource(value = {"file:/C:/Users/user1/Desktop/kyc_zm/encyption/sv.properties"})
@Component
public class SingleViewConfig implements SVConstants{
	
	@Autowired
	private Environment environment;
	
	@SuppressWarnings("deprecation")
	public KYCSoap11BindingStub getStub() throws MalformedURLException, AxisFault, ServiceException{
		URL wsdlUrl = new URL(environment.getProperty(SV_KYC_ENDPOINT));
        KYCLocator locator= new KYCLocator();
        Service service = (Service)locator.getCall();
        
        return new KYCSoap11BindingStub(wsdlUrl,service);
	}
	
	public String getRetailerNo(){
		return environment.getProperty("com.airtel.africa.sv.retailer.no");
	}
}
