package com.airtel.africa.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.airtel.africa.airtel.ema.exceptions.EmaException;

public class EMA {

	public static void main(String[] args) throws EmaException {
		String SADM_HLRSUB_COMMAND="GET:HLRSUB:MSISDN,260683234671:IMSI;";		
		fireCommand(SADM_HLRSUB_COMMAND);		

	}
	
	
	
	public static String fireCommandWithResponse(String base_url, String port, String login_command, String command)
			throws EmaException {
		String BASE_URL = base_url;
		String SADM_PORT = port;
		String SADM_LOGIN_COMMAND = login_command;

		Socket sadmSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String resp = null;
		try {
			sadmSocket = new Socket(InetAddress.getByName(BASE_URL), Integer.parseInt(SADM_PORT));
			out = new PrintWriter(sadmSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sadmSocket.getInputStream()));
			//System.out.println("TRYING Connect to SADM--- " + SADM_LOGIN_COMMAND);
			out.println(SADM_LOGIN_COMMAND);
			resp = in.readLine();
			//System.out.println(resp);

			resp = in.readLine();
			//System.out.println(resp);

			resp = in.readLine();
			//System.out.println(resp);

			resp = in.readLine();
			//System.out.println(resp);

			resp = in.readLine();
			//System.out.println(resp);

			if (resp.contains("RESP:0")) {
				//System.out.println("SADM Connected");
				//System.out.println(command);
				out.println(command);
				resp = in.readLine();
				//System.out.println(resp);

			} else {
				//System.out.println("SADM Login failed:");
				//System.out.print(in.readLine());
			}
			in.close();
			sadmSocket.close();
		} catch (Exception e) {
			// e.printStackTrace();
			throw new EmaException(-3040, "EMA Conntion issue " + e.getMessage(), e);
		} finally {
			out.close();
		}
		return resp;

	}

	@SuppressWarnings("resource")
	public static boolean fireCommand(String base_url,String port,String login_command ,String command) throws EmaException{
		String BASE_URL=base_url;
		String SADM_PORT=port;
		String SADM_LOGIN_COMMAND=login_command;
		Socket sadmSocket = null;
		
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String resp=null;
	        try 
	        {
				// sadmSocket = new Socket(BASE_URL, Integer.parseInt(SADM_PORT));
				sadmSocket = new Socket(InetAddress.getByName(BASE_URL), Integer.parseInt(SADM_PORT));
				out = new PrintWriter(sadmSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(sadmSocket.getInputStream()));
				//System.out.println("TRYING Connect to SADM--- " + SADM_LOGIN_COMMAND);
				out.println(SADM_LOGIN_COMMAND);
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);

		        if(resp.contains("RESP:0")){
		        	//System.out.println("SADM Connected");
		        	//String COMMAND = SADM_HLRSUB_COMMAND+"255"+msisdn+":IMSI;";
		        	//System.out.println(command);
		        	out.println(command);
		        	resp=in.readLine();
		        	//System.out.println(resp);
		        	
		        }else{
		        	//System.out.println("SADM Login failed:");
		        	//System.out.print(in.readLine());
		        }
		        
		        if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
						return true;
		        
		   
	        in.close();
			sadmSocket.close();
			
	        } catch (Exception e) {
				//e.printStackTrace();
				throw new EmaException(-3040,"EMA Conntion issue "+e.getMessage(),e);
			}finally{
				 out.close();
			}
	       return  false;
		
	}
	
	
	@SuppressWarnings("resource")
	public static boolean fireCommand(String command) throws EmaException{
		String BASE_URL="10.246.14.182";
		String SADM_PORT="3300";
		String SADM_LOGIN_COMMAND="LOGIN:agile_kyc:@Gil!e_@17#1;";
		
			Socket sadmSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String resp=null;
	        try 
	        {
				// sadmSocket = new Socket(BASE_URL, Integer.parseInt(SADM_PORT));
				sadmSocket = new Socket(InetAddress.getByName(BASE_URL), Integer.parseInt(SADM_PORT));
				out = new PrintWriter(sadmSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(sadmSocket.getInputStream()));
				//System.out.println("TRYING Connect to SADM--- " + SADM_LOGIN_COMMAND);
				out.println(SADM_LOGIN_COMMAND);
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);
	
				resp = in.readLine();
				//System.out.println(resp);

		        if(resp.contains("RESP:0")){
		        	//System.out.println("SADM Connected");
		        	//String COMMAND = SADM_HLRSUB_COMMAND+"255"+msisdn+":IMSI;";
		        	//System.out.println(command);
		        	out.println(command);
		        	resp=in.readLine();
		        	//System.out.println(resp);
		        	
		        }else{
		        	//System.out.println("SADM Login failed:");
		        	//System.out.print(in.readLine());
		        }
		        
		        if(resp!=null && !resp.equals("") && resp.contains("RESP:0"))
						return true;
		        
		   
	        in.close();
			sadmSocket.close();
			
	        } catch (Exception e) {
				e.printStackTrace();
				throw new EmaException(-3040,"EMA Conntion issue "+e.getMessage(),e);
			}finally{
				 out.close();
			}
	       return  false;
		
	}
}
