/**
 * MobServiceAPI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.comviva.api;

public interface MobServiceAPI extends java.rmi.Remote {
	public com.comviva.services.ModificationReturn modification(
			com.comviva.services.ModificationRequest modificationRequest) throws java.rmi.RemoteException;

	public com.comviva.services.RegistrationReturn registration(
			com.comviva.services.RegistrationRequest registrationRequest) throws java.rmi.RemoteException;
}
