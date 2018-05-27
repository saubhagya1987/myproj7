package com.airtel.africa.ssf.config;

import org.springframework.beans.factory.annotation.Value;

public class SSFConstants {

	@Value("${com.airtel.africa.ldap.server}")
	public String LDAP_HOST;
	
	@Value("${com.airtel.africa.ldap.port}")
	public String LDAP_PORT;
	
	@Value("${com.airtel.africa.ldap.basedn}")
	public String BASE_DN;
	
	@Value("${com.airtel.africa.ldap.binddn}")
	public String BIND_DN;
	
	@Value("${com.airtel.africa.ldap.bindpw}")
	public String BIND_PASSWORD;
	
	@Value("${com.airtel.africa.ldap.authenticate.flag}")
	public String AUTH_FLAG;
	
}
