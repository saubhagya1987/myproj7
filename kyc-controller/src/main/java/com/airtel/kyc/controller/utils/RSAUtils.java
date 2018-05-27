/*package com.airtel.kyc.controller.utils;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    public static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    //public static final int KEY_SIZE_2048 = 2048;
    public static final int KEY_SIZE_1024 = 1024;

    private RSAUtils() {
    }

    private static final String ALGORITHM = "RSA";

    public static KeyPair generateKeyPair() {
        return generateKeyPair(KEY_SIZE_1024);
    }

    public static KeyPair generateKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Failed to generate key pair!", e);
        }
    }

    public static PublicKey getPublicKey(String base64PublicKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(base64PublicKey));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get public key!", e);
        }
    }

    public static PublicKey getPublicKey(BigInteger modulus, BigInteger exponent) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get public key!", e);
        }
    }

    public static String getBase64PublicKey(PublicKey publicKey) {
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64PrivateKey));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get private key!", e);
        }
    }

    public static PrivateKey getPrivateKey(BigInteger modulus, BigInteger exponent) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, exponent);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get private key!", e);
        }
    }

    public static String getBase64PrivateKey(PrivateKey privateKey) {
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    public static byte[] encryptAsByteArray(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new IllegalArgumentException("Encrypt failed!", e);
        }
    }

    public static byte[] encryptAsByteArray(String data, String base64PublicKey) {
        return encryptAsByteArray(data, getPublicKey(base64PublicKey));
    }

    public static String encryptAsString(String data, PublicKey publicKey) {
        return Base64.encodeBase64String(encryptAsByteArray(data, publicKey));
    }

    public static String encryptAsString(String data, String base64PublicKey) {
        return Base64.encodeBase64String(encryptAsByteArray(data, getPublicKey(base64PublicKey)));
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(data));
        } catch (Exception e) {
            throw new IllegalArgumentException("Decrypt failed!", e);
        }
    }

    public static String decrypt(byte[] data, String base64PrivateKey) {
        return decrypt(data, getPrivateKey(base64PrivateKey));
    }

    public static String decrypt(String data, PrivateKey privateKey) {
        return decrypt(Base64.decodeBase64(data), privateKey);
    }

    public static String decrypt(String data, String base64PrivateKey) {
        return decrypt(Base64.decodeBase64(data), getPrivateKey(base64PrivateKey));
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	
    	String pkey="MIICXAIBAAKBgQDbLtlGNfNcJNQZ7wmX47NYLGVvnWHHzyW19PcB/tck9S/ueGQ/VXNuNjrzUdMXISmKKRaZhF6qvqn22ONmNFHISrLDONpG35NTDceV1ZRUCdrqLR9rXULK9XdeH4fcUlk68Z/XFwjyQ1J+8RveyupzdIsHDe3v9BDn5woNnZCoEQIDAQABAoGBAMt5amsrmohoCB8rMZS35b5tOJ2tVSDQxQluKADKO6Arnhub2DSTd11RKtjHlVdbidyg2EIcCGYqKk7mefRfjarTBM6uRHATcmw+GP7CcJ2ZvL//5PvLBoySWqcnKu0chBjA/Ja34fPNQe4I2LScyraj08rh2OR1Yl4jMdHpqpoBAkEA8gmu4sHZ4ljd5foA5XJQ6jLF9bKl0hhfBIfuikB78TFHxOQ+6JT7LWGrpP78oZ7oDBDxx3aJleTHxnYG0CcGSQJBAOfTp6DpvlE0ZleMJ+0BQkOH2iyQkpRHPPHyy7ViOIamh99SO8KhbVgHZhX9UbG685ks9//LOb09LWZ+mJP/84kCQDqDpMglXWufWGYU/sp5zJ4SEFHKEtSsF6ycwEqkNFKnO1aaZ6sK+X6LMlf9XU4lgKwKHKnAOpsGaFXOiu8fwCECQGfQXGyuv89Vi88AsJBt0+Kx3329cCZ+kEg41ghv56hEwuu6w5AxAeo9xfVq7z6L3bJmdNhThZO85avUXTdJkyECQCIzc0lhRye2vTbShXEKrNlwNozyBAjVXGLhyuo6EgqrbyxKRR+jNTtf8WmkGMVIWeAHBopZTo5a5mdMLn8pfRU=";
    	byte[] arr=pkey.getBytes();
    	String base64=Base64.encodeBase64String(arr);
    	String data="UJeurNAc9KQs+FCXHyI+81HF1HOPztUqeIMyh2OfyDJiGBi+2d3vS511qKACuR3BHF/C5VJTYSX4QL05CfQBe4Qr7Q70fedt8t48g1b0hQSPr+B68lhFoe9QIFJomKSl+V+VPL3r/hkERWRdLtMXVQn/LQ9lq4K4tIl0rf4Vl88=";
    	 String decval=RSAUtils.decrypt(data, base64);
    	 System.out.println("decval"+decval);
    	 
    	    
    	    PrivateKey priv = getPrivateKey(base64);
    	    
    	 
    	 String decval=RSAUtils.decrypt(data, priv);
       	 System.out.println("decval"+decval);
    	
    	 
    	
	}
   
}
*/