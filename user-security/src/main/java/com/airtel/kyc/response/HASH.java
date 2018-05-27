package com.airtel.kyc.response;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;
@Component
public class HASH   
{  
      public String MD2(String message) throws NoSuchAlgorithmException   
      {  
            MessageDigest md2 = MessageDigest.getInstance("MD2");  
            byte[] array = md2.digest(message.getBytes());  
            return arrayToString(array);  
      }  
      
      public String MD5(String message) throws NoSuchAlgorithmException   
      {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            byte[] array = md5.digest(message.getBytes());  
            return arrayToString(array);  
      }  
        
      public String SHA1(String message) throws NoSuchAlgorithmException   
      {               
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");          
            byte[] array = sha1.digest(message.getBytes());  
            return arrayToString(array);                  
      }  
        
      public String SHA256(String message) throws NoSuchAlgorithmException   
      {               
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");          
            byte[] array = sha256.digest(message.getBytes());  
            return arrayToString(array);                  
      }  
        
      public String SHA384(String message) throws NoSuchAlgorithmException   
      {               
            MessageDigest sha384 = MessageDigest.getInstance("SHA-384");          
            byte[] array = sha384.digest(message.getBytes());  
            return arrayToString(array);                  
      }  
        
      public String SHA512(String message) throws NoSuchAlgorithmException   
      {               
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");          
            byte[] array = sha512.digest(message.getBytes());  
            return arrayToString(array);                  
      }  
        
      private String arrayToString(byte[] array)   
      {               
            StringBuffer sb = new StringBuffer();  
            for (int i = 0; i < array.length; ++i) {  
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));  
            }  
            return sb.toString();                 
      }  
}
