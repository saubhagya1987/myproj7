package com.airtel.kyc.controller.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.WebDecryption;
import com.airtel.kyc.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class EncrytptionUtils {

	@Autowired
	EncryptionRSA encryptionRSA;
	
	@Autowired
	WebDecryption webDecryption;
	
	/*@Autowired
	private MessageSource messageSource;*/
	
	@Autowired
	private Environment env;
	
	public EncryptedJsonDto decryption(EncryptedJsonDto encryptedJsonDto) throws JsonProcessingException {
		EncryptedJsonDto dto=new EncryptedJsonDto();
		String encKey=encryptedJsonDto.getEncyptredKey();
		byte[] valueDecoded= Base64.decodeBase64(encKey );
    	System.out.println("base64String"+valueDecoded);
    	String sdecode= new String(valueDecoded);
    	System.out.println("sdecode"+sdecode);
    	Long time=null;
    	Boolean exceptionFlag=false;
    	try{
    		time=Long.valueOf(sdecode);
    	}
    	catch(Exception e){
    		exceptionFlag=true;
    	}
    	
    	if(!exceptionFlag){
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
		Date serverDate=new Date();
		String formattedDate=DateUtils.getFormatDate(serverDate, "yyyy-MM-dd HH:mm");
		
		
		final long ONE_MINUTE_IN_MILLIS=60000;

		Calendar date1 = Calendar.getInstance();
		long t= date1.getTimeInMillis();
		Date beforeThreeMins=new Date(t - (10 * ONE_MINUTE_IN_MILLIS));
		String beforeThreeMinsObj=DateUtils.getFormatDate(beforeThreeMins, "yyyy-MM-dd HH:mm");
		long  dateDiff=serverDate.getTime()-beforeThreeMins.getTime();
		
		//Date finalDate=DateUtils.getDate(formattedDate, "yyyy-MM-dd HH:mm");
		if(date!=null){
			//if(date.equals(formattedDate) || dateDiff<=10){
				String secret=encryptedJsonDto.getEncyptredKey();
				String cipherText=encryptedJsonDto.getEncyptedValue();
				String decryptedData=webDecryption.decrypt(secret, cipherText);
				dto.setEncyptedValue(decryptedData);
				
				String privateKeyPath=env.getProperty("encryption.key.path");
				String tokenKey=encryptedJsonDto.getTokenKey();
				String tokenValue=encryptedJsonDto.getTokenValue();
				String decryptedTokenKey=null;
				try {
					decryptedTokenKey = encryptionRSA.decrypt(String.valueOf(tokenKey), privateKeyPath, "", "");
					dto.setTokenKey(decryptedTokenKey);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String decryptedTokenData=encryptionRSA.dataDecrypt(Base64.decodeBase64(tokenValue), decryptedTokenKey);
					dto.setTokenValue(decryptedTokenData);
					System.out.println("decryptedTokenData: "+decryptedTokenData);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		}
    	}
		
		else{
			
		String encryptedKey=encryptedJsonDto.getEncyptredKey();
		String encryptedData=encryptedJsonDto.getEncyptedValue();
		String privateKeyPath=env.getProperty("encryption.key.path");
		System.out.println("privateKeyPath: "+privateKeyPath);
		String decryptedKey=null;
		try {
			decryptedKey = encryptionRSA.decrypt(String.valueOf(encryptedKey), privateKeyPath, "", "");
			dto.setEncyptredKey(decryptedKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String decryptedData=encryptionRSA.dataDecrypt(Base64.decodeBase64(encryptedData), decryptedKey);
			dto.setEncyptedValue(decryptedData);
			System.out.println("decryptedData: "+decryptedData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		String tokenKey=encryptedJsonDto.getTokenKey();
		String tokenValue=encryptedJsonDto.getTokenValue();
		String decryptedTokenKey=null;
		try {
			decryptedTokenKey = encryptionRSA.decrypt(String.valueOf(tokenKey), privateKeyPath, "", "");
			dto.setTokenKey(decryptedTokenKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String decryptedTokenData=encryptionRSA.dataDecrypt(Base64.decodeBase64(tokenValue), decryptedTokenKey);
			dto.setTokenValue(decryptedTokenData);
			System.out.println("decryptedTokenData: "+decryptedTokenData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return dto;
	}
}
