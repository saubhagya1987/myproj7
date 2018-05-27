package com.airtel.kyc.security.web;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

@Component
public class AesUtil {
	

    private final int keySize;
    private final int iterationCount;
    private final Cipher cipher;
    
    
    String salt="3b1c7b4c914daeacc4853ee0ade912f4";
    String iv="b4d0febfe0df20d742b12650dd932101";
    String passphrase="Secret Passphrase";
    
    public AesUtil(/*int keySize, int iterationCount*/) {
        this.keySize = 128;
        this.iterationCount = 1000;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw fail(e);
        }
    }
    
    public String encrypt(String plaintext) {
        try {
            SecretKey key = generateKey(salt, passphrase);
            byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
            return base64(encrypted);
        }
        catch (UnsupportedEncodingException e) {
            throw fail(e);
        }
    }
    
    public String decrypt(String ciphertext) {
        try {
            SecretKey key = generateKey(salt, passphrase);
            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
            return new String(decrypted, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw fail(e);
        }
    }
    
    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
        try {
            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(bytes);
        }
        catch (InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            throw fail(e);
        }
    }
    
    private SecretKey generateKey(String salt, String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return key;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw fail(e);
        }
    }
    
    public static String random(int length) {
        byte[] salt = new byte[length];
        new SecureRandom().nextBytes(salt);
        return hex(salt);
    }
    
    public static String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    
    public static byte[] base64(String str) {
        return Base64.decodeBase64(str);
    }
    
    public static String hex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }
    
    public static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private IllegalStateException fail(Exception e) {
        return new IllegalStateException(e);
    }
    
    
    
    
    /*public static void main(String[] args) throws Exception    
    {
    	int iterationCount = 1000;
        int keySize = 128;
        
        String jsonObject ="{\n\n\n {\n \"id\": 22,\n\"menu_id\": 1,\n \"field_type_id\": 1,\n\"c4w_code\": \"1234\",\n\"field_label\": \"Customer No\",\n\"field_values\": \"\",\n\"date_Created\": \"2012-09-16 05:11:23\",\n\"date_modified\": \"2013-11-20 10:33:23\",\n\"is_required\":0,\n\"is_static\": 1,\n\"field_order\": 1\n},\n{\n\"id\":23,\n\"menu_id\": 1,\n\"field_type_id\": 1,\n\"c4w_code\": \"1234\",\n\"field_label\": \"Company Name\",\n\"field_values\": \"\",\n\"date_Created\": \"2012-09-16 05:11:56\",\n\"date_modified\": \"2013-11-20 10:33:23\",\n\"is_required\": 1,\n\"is_static\": 1,\n\"field_order\": 3\n}\n\n}";    
        String salt="3b1c7b4c914daeacc4853ee0ade912f4";
        String iv="b4d0febfe0df20d742b12650dd932101";
        String passphrase="Secret Passphrase"; 
        
        AesUtil aesUtil=new AesUtil(keySize, iterationCount);
        String encVal=aesUtil.encrypt(salt, iv, passphrase, jsonObject);
       // String decVal=aesUtil.decrypt("3b1c7b4c914daeacc4853ee0ade912f4", "b4d0febfe0df20d742b12650dd932101", "Secret Passphrase", "GEXWEqPwLtrsYVtfUMiOuQ==");
       String decVal=aesUtil.decrypt(salt, iv, passphrase, encVal);
        System.out.println(encVal);
        System.out.println(decVal);  	
        
    }*/

}
