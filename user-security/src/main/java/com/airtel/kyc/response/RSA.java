package com.airtel.kyc.response;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Component;

@Component
public class RSA   
{  
       private Charset PLAIN_TEXT_ENCODING = Charset.forName("UTF-8");  
       private int KEY_SIZE_BITS = 1024;  

       private Key publicKey;  
       private Key privateKey;  
       private BigInteger modulus;  
       private BigInteger exponent;  
       Cipher cipher;  
       KeyFactory fact;  
         
       public RSA()   
               throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,   
               UnsupportedEncodingException, InvalidKeyException   
       {  
            cipher = Cipher.getInstance("RSA");  
            fact = KeyFactory.getInstance("RSA");  
            setNewKey(KEY_SIZE_BITS);  
       }  
         
       public boolean setNewKey(int KeySize)   
               throws NoSuchAlgorithmException, InvalidKeySpecException  
       {  
           if (KeySize <= 0) return false;  
           KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");  
           kpg.initialize(KeySize);  
           KeyPair kp = kpg.genKeyPair();  
           publicKey = kp.getPublic();  
           privateKey = kp.getPrivate();  
             
           RSAPublicKeySpec pub = (RSAPublicKeySpec) fact.getKeySpec(publicKey, RSAPublicKeySpec.class);  
           modulus = pub.getModulus();  
           exponent = pub.getPublicExponent();  
             
          return true;  
       }  
         
       public BigInteger getModulus()  
       {  
           return modulus;  
       }  
         
       public BigInteger getExponent()  
       {  
           return exponent;  
       }  
         
       public Key getPublicKey()  
       {  
           return publicKey;  
       }  
         
       public Key getPrivateKey()  
       {  
           return privateKey;  
       }  
         
       public String encrypt(String plaintext)   
               throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException  
       {  
             if (plaintext.length() == 0) return null;  
             cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
             byte[] encrypted = cipher.doFinal(plaintext.getBytes());  
             return Utils.byteArrayToBase64String(encrypted);  
       }  
         
       public String decrypt(String ciphertext)   
               throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException   
       {  
             if (ciphertext.length() == 0) return null;  
             byte[] dec = Utils.base64StringToByteArray(ciphertext);  
             cipher.init(Cipher.DECRYPT_MODE, privateKey);  
             byte[] decrypted = cipher.doFinal(dec);  
             return new String(decrypted, PLAIN_TEXT_ENCODING);  
       }         
}
