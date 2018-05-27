package com.airtel.africa.ssf.config;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Narotam
 *
 */
//@PropertySource({ "classpath:ssf.properties" })
@PropertySource(value = {"file:/africa_agile/opt/kyc_zm/ssf.properties"})
//@PropertySource(value = {"file:/C:/Users/user1/Desktop/kyc_zm/encyption/ssf.properties"})
@Component
public class SSFConf extends SSFConstants {

	private static Log log = LogFactory.getLog(SSFConf.class);
	private static boolean isDebugEnabled = log.isDebugEnabled();

	private DirContext ctx;

	public DirContext getContext() {
		try {
			ctx = new InitialDirContext(getEnv());
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}

		return ctx;
	}

	public Hashtable<String, String> getEnv() {

		if (LDAP_HOST == null || LDAP_PORT == null
				|| BIND_DN == null) {
			if (isDebugEnabled) {
				log.debug("SSF Details \nhost : " + LDAP_HOST);
				log.debug("SSF Details \nport : " + LDAP_PORT);
				log.debug("SSF Details \bind DN : " + BIND_DN);
			}
		}
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + LDAP_HOST + ":" + LDAP_PORT + "/" + BASE_DN);
		env.put(Context.REFERRAL, "follow");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, BIND_DN);
		env.put(Context.SECURITY_CREDENTIALS, BIND_PASSWORD);

		return env;
	}

	public void closeContext() {
		if (ctx != null) {
			try {
				ctx.close();
			} catch (Exception e) {
				/* Do Nothing */ }
		}
	}
	
	public boolean isPwAuthenticationActive(){
		if("YES".equals(AUTH_FLAG)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getBaseDN(){
		return BASE_DN;
	}
}
