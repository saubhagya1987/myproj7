/**
 * 
 */
package com.airtel.africa.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author B0073698
 *
 */
public class StringUtils {

	public final static String NUMERIC= "0-9";
	public final static String ALPHA= "a-zA-Z";
	public final static String SPACE= " ";
	public final static String COMMA= ",";
	public final static String DOT= ".";
	public final static String DASH= "\\-";
	public final static String DASH_SLASH= "\\-/";
	public final static String REG_START= "^[";
	public final static String REG_END= "]*$";
	public final static String PAN_REG = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$";
	public final static String EMAIL_REG = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	public final static String DATE_DD_MM_YYYY = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	public final static String DATE_MM_DD_YYYY = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	
	public static boolean isEmpty(String s){
		return null == s || s.trim().equals("");
	}
	
	public static boolean isName(String s){
		return (!isEmpty(s)) && s.matches(REG_START+ALPHA+NUMERIC+SPACE+REG_END);
	}
	
	public static boolean isPhone(String s){
		return (!isEmpty(s)) && ((s.length() == 10 && s.matches(REG_START+NUMERIC+REG_END) && !s.startsWith("0")) ||  (s.length() == 11 && s.startsWith("0") && s.matches(REG_START+NUMERIC+REG_END)));
	}
	
	public static boolean isEmail(String s){
		return (!isEmpty(s)) && s.matches(EMAIL_REG);
	}
	
	public static boolean isAlphaNumeric(String s){
		return (!isEmpty(s)) && s.matches(REG_START+ALPHA+NUMERIC+REG_END);
	}
	
	public static void main(String[] args){
		System.out.println(gen());
	}
	public static int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	   // return (1 + r.nextInt(2)) * 100000000 + r.nextInt(10000);
	   // return  680900*1000 + r.nextInt(100000);
	    return  680900000+ r.nextInt(1000);
	}
	
	public static List<String> StrTOStrList(String str,String delimeter) {
		List<String> list = new ArrayList<String>(Arrays.asList(str.split(delimeter)));
		return list;
	}
	
	public static String[] StrTOStrArr(String str,String delimeter) {
		String[] arr =org.springframework.util.StringUtils.commaDelimitedListToStringArray(str);
		return arr;
	}
}
