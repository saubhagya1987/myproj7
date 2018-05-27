package com.airtel.africa.airtel.ema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.airtel.africa.airtel.ema.exceptions.EmaException;
import com.airtel.africa.airtel.ema.helpers.EMAResponse;
import com.airtel.africa.airtel.ema.service.EmaService;
import com.airtel.africa.utils.EMA;


//@PropertySource({"classpath:ema.properties"})

@org.springframework.stereotype.Service
public class EmaServiceImpl implements EmaService {

	@Autowired
	private Environment env;

	private String BASE_URL;
	private String PORT;
	private String LOGIN_COMMAND;

	private void intializeVariables() {
		if (BASE_URL == null)
			BASE_URL = env.getProperty("com.airtel.ema.url");
		if (PORT == null)
			PORT = env.getProperty("com.airtel.ema.port");
		if (LOGIN_COMMAND == null)
			LOGIN_COMMAND = env.getProperty("com.airtel.ema.command.login");
	}

	@Override
	public boolean unbarMsisdn(String msisdn) throws EmaException {
		// TODO Auto-generated method stub
		String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":OSB3,0:TS22,1:TS21,1:NAM,0:OBA,0;";
		intializeVariables();
		return EMA.fireCommand(BASE_URL, PORT, LOGIN_COMMAND, command);
	}

	@Override
	public boolean barMsisdnOutgoing(String msisdn) throws EmaException {
		// TODO Auto-generated method stub
		String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":OBO,1;";
		intializeVariables();
		return EMA.fireCommand(BASE_URL, PORT, LOGIN_COMMAND, command);
	}

	@Override
	public boolean barMsisdnAll(String msisdn) throws EmaException {
		// TODO Auto-generated method stub
		String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":OBO,1:OBI,1:OSB3,1:NAM,1:BICRO,1;";
		intializeVariables();
		return EMA.fireCommand(BASE_URL, PORT, LOGIN_COMMAND, command);
	}

	@Override
	public EMAResponse unbar(String msisdn) {
		EMAResponse emaResponse = new EMAResponse();
		// TODO Auto-generated method stub
		//String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":OSB3,0:TS22,1:TS21,1:NAM,0:OBA,0;";
		String command = "SET:TEMPBLOCK:SubscriberNumber," + "260" + msisdn + ":TempBlockingStatus,CLEAR;";
		intializeVariables();
		String resp = null;
		try {
			resp = EMA.fireCommandWithResponse(BASE_URL, PORT, LOGIN_COMMAND, command);
			if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
				emaResponse.setUnbar(true);
			else 
				emaResponse.setUnbar(false);
		} catch (EmaException e) {
			emaResponse.setExceptionOccured(true);
			emaResponse.setException(e);
		}
		catch (Exception e) {
			emaResponse.setExceptionOccured(true);			
		}
		emaResponse.setRequest("EMA : "+BASE_URL+"  "+PORT+"  "+LOGIN_COMMAND+"  "+command);
		emaResponse.setResponse(resp);
		return emaResponse;
	}
	
	@Override
	public EMAResponse simValidation(String msisdn) {
		EMAResponse emaResponse = new EMAResponse();
		// TODO Auto-generated method stub
		String command ="GET:SUBSCRIBERINFORMATION:SubscriberNumber,"+"260"+msisdn+";";
		//String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":OSB3,0:TS22,1:TS21,1:NAM,0:OBA,0;";
		intializeVariables();
		String resp = null;
		try {
			resp = EMA.fireCommandWithResponse(BASE_URL, PORT, LOGIN_COMMAND, command);
			System.out.println("reposnse :"+resp);
			if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
			{
			emaResponse.setUnbar(true);
			emaResponse.setResponse(resp);
			}
			else 
			{
			    emaResponse.setUnbar(false);
				emaResponse.setResponse(resp);
			}
		} catch (EmaException e) {
			emaResponse.setExceptionOccured(true);
			emaResponse.setException(e);
		}
		catch (Exception e) {
			emaResponse.setExceptionOccured(true);			
		}
		emaResponse.setRequest("EMA : "+BASE_URL+"  "+PORT+"  "+LOGIN_COMMAND+"  "+command);
		emaResponse.setResponse(resp);
		return emaResponse;
	}

	@Override
	public EMAResponse bar(String msisdn) {
		EMAResponse emaResponse = new EMAResponse();
		// TODO Auto-generated method stub
		//String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":OBO,1:OBI,1:OSB3,1:NAM,1:BICRO,1;";
		String command = "SET:TEMPBLOCK:SubscriberNumber," + "260" + msisdn + ":TempBlockingStatus,CLEAR;";
		intializeVariables();
		String resp = null;
		try {
			resp = EMA.fireCommandWithResponse(BASE_URL, PORT, LOGIN_COMMAND, command);
			if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
				emaResponse.setUnbar(false);
			else 
				emaResponse.setUnbar(true);
		} catch (EmaException e) {
			emaResponse.setExceptionOccured(true);
			emaResponse.setException(e);
		}
		emaResponse.setRequest("EMA : "+BASE_URL+"  "+PORT+"  "+LOGIN_COMMAND+"  "+command);
		emaResponse.setResponse(resp);
		return emaResponse;
	}
	
	
	@Override
	public EMAResponse unbarForBulk(String msisdn) {

		EMAResponse emaResponse = new EMAResponse();
		// TODO Auto-generated method stub
		String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":TS22,1:TS21,1:NAM,0;";
			
		intializeVariables();
		String resp = null;
		try {
			resp = EMA.fireCommandWithResponse(BASE_URL, PORT, LOGIN_COMMAND, command);
			if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
				emaResponse.setUnbar(true);
			else 
				emaResponse.setUnbar(false);
		} catch (EmaException e) {
			emaResponse.setExceptionOccured(true);
			emaResponse.setException(e);
		}
		emaResponse.setRequest("EMA : "+BASE_URL+"  "+PORT+"  "+LOGIN_COMMAND+"  "+command);
		emaResponse.setResponse(resp);
		return emaResponse;
		
	}
	
	@Override
	public EMAResponse barForBulk(String msisdn) {
		EMAResponse emaResponse = new EMAResponse();
		// TODO Auto-generated method stub
		String command = "SET:HLRSUB:MSISDN," + "260" + msisdn + ":TS22,0:TS21,0:NAM,1;";
				
		intializeVariables();
		String resp = null;
		try {
			resp = EMA.fireCommandWithResponse(BASE_URL, PORT, LOGIN_COMMAND, command);
			if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
				emaResponse.setUnbar(false);
			else 
				emaResponse.setUnbar(true);
		} catch (EmaException e) {
			emaResponse.setExceptionOccured(true);
			emaResponse.setException(e);
		}
		emaResponse.setRequest("EMA : "+BASE_URL+"  "+PORT+"  "+LOGIN_COMMAND+"  "+command);
		emaResponse.setResponse(resp);
		return emaResponse;
	}

}
