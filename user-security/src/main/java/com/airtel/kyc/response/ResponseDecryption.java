package com.airtel.kyc.response;

import org.springframework.stereotype.Component;
 
/**
 * 
 *
 */
@Component
public class ResponseDecryption {
    
    
    public String decryptData() throws Exception
    {
        
    	AES aes = new  AES();
    	final String secretKey = "Secret Passphrase";
        String plainText = "xyze";
        
        int keySizeInBits = 128;                                       
        int keySizeInBytes = keySizeInBits/8;    
          
        // Derive a cryptographic key: PBKDF2  
        String salt = Utils.byteArrayToHexString(Utils.getRandomBytes(8));  
        String key = Utils.pbkdf2(secretKey, salt, 1000, keySizeInBytes);  
          
        // generate IV  
        byte[] ivBytes = aes.generateIV();  
        aes.setIV(ivBytes);  
        
        String ciphertext = aes.encrypt(plainText, key);
        
        String plaintext2 = aes.decrypt(ciphertext, key);       
         
        System.out.println(plainText);
        System.out.println(ciphertext);
        System.out.println(plaintext2);
		return plaintext2;
    }
    public String encryptData(String plainText) throws Exception
    {
    	//{"scope":"trust read write","userId":"133","name":"admin","hasBulkRights":"true","roles":[{"authority":"ROLE_ADMIN"}],"userName":"2322227","expires_in":"515","token_type":"bearer","isPasswordModified":"1","refresh_token":"75b69002-2365-4a3e-a1ff-2d2387bba94b","access_token":"916449cf-7385-44cf-bd6a-47d4902d5be3","subRoleId":21,"roleId":0,"mobileAccessFlag":1,"userExist":true}
    	System.out.println("plainText string"+plainText);
    	//String modifiedText = plainText.replaceAll("\"", "\\\\\"");
    	//System.out.println("modifiedText"+modifiedText);
    	
    	AES aes = new  AES();
    	final String secretKey = "Secret Passphrase";
        //String plainText = "xyze";
        
        int keySizeInBits = 128;                                       
        int keySizeInBytes = keySizeInBits/8;    
          
        // Derive a cryptographic key: PBKDF2  
        String salt = "0x676776B3922CF017";//Utils.byteArrayToHexString(Utils.getRandomBytes(8));  
        String key = "Secret Passphrase" ; 
          
        // generate IV  
        byte[] ivBytes = {34, -14, 93, 92, -28, -36, 42, -109, -18, 6, 37, -1, -113, 24, -31};//aes.generateIV();  
        System.out.println(ivBytes);
        aes.setIV(ivBytes);  
        
        String ciphertext = aes.encrypt(plainText, key);        
        String modifiedText = aes.decrypt(ciphertext, key);  
        System.out.println(plainText);
        System.out.println(ciphertext);
        System.out.println(modifiedText);
        
		return ciphertext;
    }

}  