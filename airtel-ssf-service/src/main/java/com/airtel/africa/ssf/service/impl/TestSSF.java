package com.airtel.africa.ssf.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.SizeLimitExceededException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airtel.africa.ssf.config.SSFConf;

public class TestSSF {

	
	
	

	public DirContext getContext() {
		DirContext ctx=null;
		try {
			ctx = new InitialDirContext(getEnv());
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}

		return ctx;
	}

	public Hashtable<String, String> getEnv() {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://172.23.37.88"+ ":1389"+ "/cn=tamusers,ou=tam,ou=applications,o=bhartiairtel");
		env.put(Context.REFERRAL, "follow");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=agileuser,cn=users,ou=authentication,O=BHARTIAIRTEL");
		env.put(Context.SECURITY_CREDENTIALS, "Airtel@123");

		return env;
	}

	public void closeContext() {
		if (getContext() != null) {
			try {
				getContext().close();
			} catch (Exception e) {
				/* Do Nothing */ }
		}
	}
	
	public String getBaseDN(){
		return "cn=tamusers,ou=tam,ou=applications,o=bhartiairtel";
	}
	
	public Boolean authenticateUser(String userName, String userPassword) {
		System.out.println("Authenticating -- ");
		
		NamingEnumeration<SearchResult> results = null;
		Boolean flag=Boolean.FALSE;
		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setCountLimit(1);
			controls.setTimeLimit(5000);

			//String searchString = "(uid=" + userName + ")";
			//results = ssfConf.getContext().search("", searchString, controls);

			/*if (results.hasMore()) {
				SearchResult result = (SearchResult) results.next();
				Attributes attrs = result.getAttributes();*/

				//User Exists, Validate the Password
				//if(ssfConf.isPwAuthenticationActive()){
					//Attribute dnAttr = attrs.get("distinguishedName");
					//String dn = (String) dnAttr.get();
			
			
					//String dn = "uid="+userName+","+getBaseDN();
					//System.out.println("flag==="+dn);
					getEnv().put(Context.SECURITY_PRINCIPAL, "uid="+userName+",cn=tamusers,ou=tam,ou=applications,o=bhartiairtel");
					getEnv().put(Context.SECURITY_CREDENTIALS, userPassword);
					// Exception will be thrown on Invalid case
					
					
					
					new InitialDirContext(getEnv());
				//}
					flag = Boolean.TRUE;
			/*} else
				flag = Boolean.FALSE;*/

		} catch (AuthenticationException e) { // Invalid Login
			flag = Boolean.FALSE;
		} catch (NameNotFoundException e) { // The base context was not found.
			flag = Boolean.FALSE;
		} catch (SizeLimitExceededException e) {
			flag = Boolean.FALSE;
			throw new RuntimeException("LDAP Query Limit Exceeded, adjust the query to bring back less records", e);
		} catch (NamingException e) {
			flag = Boolean.FALSE;
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					/* Do Nothing */
				}
			}
			closeContext();
		}
		return flag;
	}

	public static void main(String...a){
		TestSSF obj =new TestSSF();
		/*boolean flag = obj.authenticateUser("2322222n","airtel123");
		System.out.println("flag11111==="+flag);*/
		
		/*Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-2);
		cal.set(Calendar.MILLISECOND,0);
		cal.add(Calendar.SECOND,0);
		cal.add(Calendar.MINUTE,0);
		cal.add(Calendar.HOUR,0);
		System.out.println(new java.sql.Timestamp(cal.getTimeInMillis()));*/
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate=  null;
		try {
			parsedDate = dateFormat.parse("04-07-2017");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strDate = "2017-07-24 00:00:00.123455";
		System.out.println("hello == "+new Timestamp(parsedDate.getTime()));
		
		
		boolean flag1 = obj.validateLogin("2322223","airtel123");
		System.out.println("flag2222==="+flag1);
	}
	
	public Boolean validateLogin(String userName, String userPassword) {
	    Hashtable<String, String> env = new Hashtable<String, String>();


	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://172.23.37.88"+ ":1389"+ "/cn=tamusers,ou=tam,ou=applications,o=bhartiairtel");
		env.put(Context.REFERRAL, "follow");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=agileuser,cn=users,ou=authentication,O=BHARTIAIRTEL");
		env.put(Context.SECURITY_CREDENTIALS, "Airtel@123");

	    DirContext ctx;
	    try {
	       ctx = new InitialDirContext(env);
	    } catch (NamingException e) {
	       throw new RuntimeException(e);
	    }

	    NamingEnumeration<SearchResult> results = null;

	    try {
	       SearchControls controls = new SearchControls();
	       controls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Search Entire Subtree
	       controls.setCountLimit(1);   //Sets the maximum number of entries to be returned as a result of the search
	       controls.setTimeLimit(5000); // Sets the time limit of these SearchControls in milliseconds

	       System.out.println((new SearchControls()).getClass().getName());
	       
	       if(userPassword == null || "".equals(userPassword)){
	    	   String searchString = "(uid=" + userName + ")";
	    	   results = ctx.search("", searchString, controls);
	    	   return results.hasMore();
	       }
	       else{
	     /*  if (results.hasMore()) {

	           SearchResult result = (SearchResult) results.next();
	           Attributes attrs = result.getAttributes();*/
	          /* Attribute dnAttr = attrs.get("distinguishedName");
	           String dn = (String) dnAttr.get();*/

	           // User Exists, Validate the Password

	           env.put(Context.SECURITY_PRINCIPAL, "uid="+userName+",cn=tamusers,ou=tam,ou=applications,o=bhartiairtel");
	           env.put(Context.SECURITY_CREDENTIALS, userPassword);

	           new InitialDirContext(env); // Exception will be thrown on Invalid case
	           return true;
	       }
	      /* } 
	       else 
	           return false;*/

	    } catch (AuthenticationException e) { // Invalid Login
	    	//e.printStackTrace();
	        return false;
	    } catch (NameNotFoundException e) { // The base context was not found.

	        return false;
	    } catch (SizeLimitExceededException e) {
	        throw new RuntimeException("LDAP Query Limit Exceeded, adjust the query to bring back less records", e);
	    } catch (NamingException e) {
	       throw new RuntimeException(e);
	    } finally {

	       if (results != null) {
	          try { results.close(); } catch (Exception e) { /* Do Nothing */ }
	       }

	       if (ctx != null) {
	          try { ctx.close(); } catch (Exception e) { /* Do Nothing */ }
	       }
	    }
	}
}
