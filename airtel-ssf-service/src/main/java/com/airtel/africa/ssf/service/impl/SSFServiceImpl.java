package com.airtel.africa.ssf.service.impl;

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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.africa.ssf.config.SSFConf;
import com.airtel.africa.ssf.config.SSFConstants;
import com.airtel.africa.ssf.service.SSFService;

@Service
public class SSFServiceImpl extends SSFConstants implements SSFService  {

	private static final Logger log = Logger.getLogger(SSFServiceImpl.class);

	@Autowired
	private SSFConf ssfConf;
	
/*	public Boolean authenticateUser(String userName, String userPassword) {
		log.info("Authenticating -- ");
		
		NamingEnumeration<SearchResult> results = null;
		Boolean flag=Boolean.FALSE;
		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setCountLimit(1);
			controls.setTimeLimit(5000);

			//String searchString = "(uid=" + userName + ")";
			//results = ssfConf.getContext().search("", searchString, controls);

			if (results.hasMore()) {
				SearchResult result = (SearchResult) results.next();
				Attributes attrs = result.getAttributes();

				//User Exists, Validate the Password
				//if(ssfConf.isPwAuthenticationActive()){
					//Attribute dnAttr = attrs.get("distinguishedName");
					//String dn = (String) dnAttr.get();
					String dn = "uid="+userName+","+ssfConf.getBaseDN();
					ssfConf.getEnv().put(Context.SECURITY_PRINCIPAL, dn);
					ssfConf.getEnv().put(Context.SECURITY_CREDENTIALS, userPassword);
					// Exception will be thrown on Invalid case
					new InitialDirContext(ssfConf.getEnv());
				//}
					flag = Boolean.TRUE;
			} else
				flag = Boolean.FALSE;

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
					 Do Nothing 
				}
			}
			ssfConf.closeContext();
		}
		log.info("Authenticated -- "+flag);
		return flag;
	}
*/
	public Boolean authenticateUser(String userName, String userPassword) {
		log.info("Authenticating -- ");
		
		if (LDAP_HOST == null || LDAP_PORT == null
				|| BIND_DN == null) {
			
				log.debug("SSF Details \nhost : " + LDAP_HOST);
				log.debug("SSF Details \nport : " + LDAP_PORT);
				log.debug("SSF Details \bind DN : " + BIND_DN);
		}
	    Hashtable<String, String> env = new Hashtable<String, String>();

	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + LDAP_HOST + ":" + LDAP_PORT + "/" + BASE_DN);
		env.put(Context.REFERRAL, "follow");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, BIND_DN);
		env.put(Context.SECURITY_CREDENTIALS, BIND_PASSWORD);

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

	       if(userPassword == null || "".equals(userPassword)){
	    	   String searchString = "(uid=" + userName + ")";
	    	   results = ctx.search("", searchString, controls);
	    	   return results.hasMore();
	       }
	       else{

	           env.put(Context.SECURITY_PRINCIPAL, "uid="+userName+","+BASE_DN);
	           env.put(Context.SECURITY_CREDENTIALS, userPassword);

	           new InitialDirContext(env); // Exception will be thrown on Invalid case
	           log.info("Authentication successful!");
	           log.debug("Authentication successful!");
	           return true;
	       }
	    } catch (AuthenticationException e) { // Invalid Login
	    	log.info("Authentication failure! AuthenticationException");
	        log.debug("Authentication failure! AuthenticationException");
	        return false;
	    } catch (NameNotFoundException e) { // The base context was not found.
	    	log.info("Authentication failure! NameNotFoundException");
	        log.debug("Authentication failure! NameNotFoundException");

	        return false;
	    } catch (SizeLimitExceededException e) {
	    	log.info("Authentication failure! SizeLimitExceededException");
	        log.debug("Authentication failure! SizeLimitExceededException");
	        return false;
	        //throw new RuntimeException("LDAP Query Limit Exceeded, adjust the query to bring back less records", e);
	    } catch (NamingException e) {
	    	log.info("Authentication failure! NamingException");
	        log.debug("Authentication failure! NamingException");

	        return false;
	       //throw new RuntimeException(e);
	    }catch (Exception e) {
	    	log.info("Authentication failure! Exception");
	        log.debug("Authentication failure! Exception");
	        return false;
	       //throw new RuntimeException(e);
	    } 
 
	    finally {

	       if (results != null) {
	          try { results.close(); } catch (Exception e) { /* Do Nothing */ }
	       }

	       if (ctx != null) {
	          try { ctx.close(); } catch (Exception e) { /* Do Nothing */ }
	       }
	    }
	}
}
