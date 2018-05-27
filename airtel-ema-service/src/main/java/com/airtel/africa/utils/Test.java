package com.airtel.africa.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtel.africa.airtel.ema.exceptions.EmaException;

public class Test {

	public static void main(String[] args) throws EmaException {
		
		//Test test=new Test();
		
		//Map<String, String> map=test.getHlrSub("683234671");
		
	}
	
	

	public Map<String, String> getHlrSub(String msisdn) throws EmaException{
		

		/*String BASE_URL="10.246.14.182";
		String SADM_PORT="3300";
		String SADM_LOGIN_COMMAND="LOGIN:agile_kyc:@Gil!e_@17#1;";
		String SADM_HLRSUB_COMMAND="GET:HLRSUB:MSISDN,";*/
		
		String BASE_URL="172.24.35.121";
		String SADM_PORT="10084";
		String SADM_LOGIN_COMMAND="LOGIN:ATNG:ATNG123;";
		String SADM_HLRSUB_COMMAND="GET:HLRSUB:MSISDN,";
		
		//GET:HLRSUB:MSISDN,255683234671;

		
		Map<String, String> responsemap=new HashMap<String, String>();
			Socket sadmSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String resp=null;
	        try 
	        {
	            //sadmSocket = new Socket(BASE_URL, Integer.parseInt(SADM_PORT));
	            sadmSocket = new Socket(InetAddress.getByName(BASE_URL), Integer.parseInt(SADM_PORT));
	            out = new PrintWriter(sadmSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(sadmSocket.getInputStream()));
	           // System.out.println("TRYING Connect to SADM"+SADM_LOGIN_COMMAND);
		        out.println(SADM_LOGIN_COMMAND);
		        resp=in.readLine();
		        System.out.println(resp);
		        if(resp.equals("RESP:0")){
		        	//System.out.println("SADM Connected");
		        	String COMMAND = SADM_HLRSUB_COMMAND+"260"+msisdn+":IMSI;";
		        	//System.out.println(COMMAND);
		        	out.println(COMMAND);
		        	resp=in.readLine();
		        	//System.out.println(resp);
		        }else{
		        	//System.out.println("SADM Login failed:");
		        	//System.out.print(in.readLine());
		        }
		        //System.out.println(in.readLine());
		        
		        /*
		         * 
	GET:HLRSUB:MSISDN,[MSISDN_NUMBER]:CFU,0;      replace CFU with parameters require

	e.g  GET:HLRSUB:MSISDN,[MSISDN_NUMBER]:NAM,0:TS22,1:TS21,1:OBO,0:OBI,0:OSB1,0:OSB3,0:OSB4,0:OBSSM,0:TICK,0;

	S/N	Barring	VOICE-Outgoing	Voice-Outgoing	SMS-Outgoing	Data	Voice-Incoming	SMS-Incoming	Call Forwarding	TICK
	1	Pre-activation	 	OSB1	TS22	NAM	OBI	TS21	 	 
	2	Payment	 	OSB2	TS22	NAM	OBI	TS21	 	 
	3	KYC Non-Compliant	 	OSB3	TS22	NAM	OBI	TS21	CFU	TICK
	4	Pre-reg/48 hrs Barring	 	OSB4	TS22	NAM	OBI	TS21	 	 
	5	VOICE	OBO	 	 	 	 	 	 	 
	6	SMS	 	 	TS22	 	 	 	 	 
	7	DATA	 	 	 	NAM	 	 	 	 

	Parameter	Status
	OSBs	1
	TSS22	0
	NAM	1
	OBO	1
	OBI	1
	TS21	0
	CFU	0
	TICK	101


		         * 
		         * 
		         * 
		         * LOGIN RESPONSE----RESP:0;
					Enter command: RESP:0:
					MSISDN,2348023133115:
					IMSI,621203830536930:
					AUTHD,AVAILABLE:
					PDPCP,32:
					CSP,157:
					NAM,1:
					BAIC,1,0,TS10,0,TS20,0,BS20,0,BS30:
					BAOC,1,0,TS10,0,TS20,0,BS20,0,BS30:
					BOIC,1,0,TS10,0,TS20,0,BS20,0,BS30:
					BICRO,1,0,TS10,0,TS20,0,BS20,0,BS30:
					BOIEXH,1,0,TS10,0,TS20,0,BS20,0,BS30:
					CFU,1,0,TS10,0,BS20,0,BS30:
					CFB,1,0,TS10,0,BS20,0,BS30:
					CFNRC,1,0,TS10,0,BS20,0,BS30:
					CFNRY,1,0,TS10,0,BS20,0,BS30:
					CAW,1,1,TS10,1,BS20,0,BS30:
					BS26,1:
					BS3G,1:
					CAT,10:
					CLIP,1:
					CLIR,1:
					DBSG,1:
					HOLD,1:
					MPTY,1:
					OICK,320:
					OSB2,1:
					OFA,5:
					PWD,0000:
					SOCB,1:
					SOCFB,0:
					SOCFRC,0:
					SOCFRY,0:
					SOCFU,0:
					SOCLIP,0:
					SOCLIR,2:
					STYPE,21:
					TS11,1:
					TS21,1:
					LOC,4-2348020004048,,2348020004048,,,"MS PURGED IN VLR":
					VLRID,4-2348020004048:
					RSA,1:
					SCHAR,4-0:
					DUALSTAT,0:
					REDMCH,1:
					IMEISV,3536220615371978;
		         * 
		         */
	        
	        if(resp!=null && !resp.equals(""))
			{
	        	List<String> hlrAttributes=StringUtils.StrTOStrList(resp, ":");
				/*String resp1=null;
				int resp_stindx=resp.indexOf(":");
				resp=resp.substring(resp_stindx+1,resp.length());
				resp1=resp.substring(0,resp.indexOf(":"));
				if(resp1.equals("0")){
					List<String> hlrAttributes=StringUtils.StrTOStrList(resp1, ":");*/
					for (String string : hlrAttributes) {
						if(string!=null){
							if(string.equalsIgnoreCase("RESP,0"))
							continue;
							if((string.contains("OSB1,") || string.contains("OSB2,") || string.contains("OSB3,") || string.contains("OSB4,")) && !responsemap.containsKey("Outgoing_Voice")){
								if(string.equalsIgnoreCase("OSB1,1")){
									responsemap.put("Outgoing_Voice","N");
									responsemap.put("Outgoing_Voice_Reason",string+":Pre-activation");
									continue;
								}else{ 
									responsemap.put("Outgoing_Voice","Y");
								}
								if(string.equalsIgnoreCase("OSB2,1")){
									responsemap.put("Outgoing_Voice","N");
									responsemap.put("Outgoing_Voice_Reason",string+":Payment");
									continue;
								}
								else{ 
									responsemap.put("Outgoing_Voice","Y");
								}
								if(string.equalsIgnoreCase("OSB3,1")){
									responsemap.put("Outgoing_Voice","N");
									responsemap.put("Outgoing_Voice_Reason",string+":KYC Non-Compliant");
									continue;
								}else{
									responsemap.put("Outgoing_Voice","Y");
								}
								if(string.equalsIgnoreCase("OSB4,1")){
									responsemap.put("Outgoing_Voice","N");
									responsemap.put("Outgoing_Voice_Reason",string+":Pre-reg/48 hrs Barring");
									continue;
								}
								else{
									responsemap.put("Outgoing_Voice","Y");
								}
							}else if(string.contains("OBI,")){
								if((string.equalsIgnoreCase("OBI,1")))
								responsemap.put("Incoming_Voice","N");
								else
								responsemap.put("Incoming_Voice","Y");
							}
							else if(string.contains("TS11,")){
								if((string.equalsIgnoreCase("TS11,0")))
								responsemap.put("Incoming_SMS","N");
								else
								responsemap.put("Incoming_SMS","Y");
							}
							else if(string.contains("TS22,")){
								if((string.equalsIgnoreCase("TS22,0")))
								responsemap.put("Outgoing_SMS","N");
								else
								responsemap.put("Outgoing_SMS","Y");
							}
							else if(string.contains("NAM,")){
								if((string.equalsIgnoreCase("NAM,1")))
								responsemap.put("Data","N");
								else
								responsemap.put("Data","Y");
							}
							else if((string.contains("IMSI,"))){
								int sep_indx=string.indexOf(",");
								String value=string.substring(sep_indx+1,string.length());
								responsemap.put("IMSI",value);
							}
							else{
								continue;
							}
						}
					}
					if(!responsemap.containsKey("Outgoing_SMS")){
						responsemap.put("Outgoing_SMS","Y");
					}
					if(!responsemap.containsKey("Incoming_SMS")){
						responsemap.put("Incoming_SMS","Y");
					}
					if(!responsemap.containsKey("Data")){
						responsemap.put("Data","Y");
					}
					if(!responsemap.containsKey("Outgoing_Voice")){
						responsemap.put("Outgoing_Voice","Y");
					}
					if(!responsemap.containsKey("Incoming_Voice")){
						responsemap.put("Incoming_Voice","Y");
					}
				}else{
					in.close();
					sadmSocket.close();
					throw new EmaException(-3040,"IAT Exception: Error in fetching HLRSUB "+"Resp="+resp,null);
				}
	        in.close();
			sadmSocket.close();
	        } catch (Exception e) {
				e.printStackTrace();
				throw new EmaException(-3040,"IAT Exception: Error in fetching HLRSUB "+e.getMessage(),e);
			}finally{
				 out.close();
			}
	       return  responsemap;
		
	}
}
