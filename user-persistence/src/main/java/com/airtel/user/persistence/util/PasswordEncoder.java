/**
 * 
 */
package com.airtel.user.persistence.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Programmers
 *
 */
public class PasswordEncoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder(11);
		System.out.println(encoder.encode("ani"));
		System.out.println(encoder.encode("password"));
		System.out.println(encoder.encode("password"));
		System.out.println(encoder.encode("Airtel@123"));
		System.out.println(isMatched("ani","$2a$11$v262moWznQhjHM3ApR69QujXu773ZwkApEF4Mjy3mSV/X4OlH6kqm"));
	}
	
	public static String encode(String pwd){
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder(11);
		return encoder.encode(pwd);
	}
	
	public static Boolean isMatched(String rawPassword,String encodedPassword){
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder(11);
		return encoder.matches(rawPassword, encodedPassword);
	}

}
