package com.airtel.africa.utils;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ema="Enter command: RESP:0:HLRProfile,114:IMSI,645017740649977:SIM,8926001031106499777;";
		
		String [] emaArray=ema.split("IMSI,");
		String [] splitArray=(emaArray)[1].split(":");
		System.out.println("subscriberDetailsList.get(0) 329 "+(splitArray)[0]);
		

	}

}
