package com.airtel.kyc.security.web;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
@Component
public class WebEncryption {
	
	
	
	private static final String ALGO = "AES";
	  private static final byte[] keyValue = 
	      new byte[] { 'A', 'b', 'c', 'd', 'e', 'f', 'g',
	      'h', 'i', 'j', 'k','l', 'm', 'n', 'o', 'p'};

	  public static String encrypt(String Data) throws Exception {
	    Key key = generateKey();
	    Cipher c = Cipher.getInstance(ALGO);
	    c.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encVal = c.doFinal(Data.getBytes());
	    String encryptedValue = Base64.getEncoder().encodeToString(encVal);
	    return encryptedValue;
	  }


	  private static Key generateKey() throws Exception {
	    Key key = new SecretKeySpec(keyValue, ALGO);
	    return key;
	  }

	
	 private static SecretKeySpec secretKey;
	    private static byte[] key;
	 
	    public static void setKey(String myKey)
	    {
	        MessageDigest sha = null;
	        try {
	            key = myKey.getBytes("UTF-8");
	            sha = MessageDigest.getInstance("SHA-1");
	            key = sha.digest(key);
	            key = Arrays.copyOf(key, 16);
	            secretKey = new SecretKeySpec(key, "AES");
	        }
	        catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /*public static String encrypt(String strToEncrypt, String secret)
	    {
	        try
	        {
	            setKey(secret);
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	        }
	        catch (Exception e)
	        {
	            System.out.println("Error while encrypting: " + e.toString());
	        }
	        return null;
	    }  */ 
	 
	    public static String decrypt(String strToDecrypt, String secret)
	    {
	        try
	        {
	            setKey(secret);
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	        }
	        catch (Exception e)
	        {
	            System.out.println("Error while decrypting: " + e.toString());
	        }
	        return null;
	    }
	    
	    public static void main(String[] args) throws Exception
	    {
	        //final String secretKey = "ssshhhhhhhhhhh!!!!";
	        
	        //final String secretKey = "abc";
	    	final String secretKey = "QWJjZGVmZ2hpamtsbW5vcA==";
	        String originalString = "xyz";
	        String encryptedString = WebEncryption.encrypt(originalString) ;
	        //String encryptedString = WebEncryption.encrypt(originalString, secretKey) ;
	        String decryptedString = WebEncryption.decrypt(encryptedString, secretKey) ;
	         
	        System.out.println(originalString);
	        System.out.println(encryptedString);
	        System.out.println(decryptedString);
	    }
	}
