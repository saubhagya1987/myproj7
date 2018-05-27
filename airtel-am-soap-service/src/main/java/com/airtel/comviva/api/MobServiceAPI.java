/**
 * MobServiceAPI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.comviva.api;

public interface MobServiceAPI extends java.rmi.Remote {
    public com.airtel.comviva.services.BlockServiceResponse blockService(com.airtel.comviva.services.BlockServiceRequest blockServiceRequest) throws java.rmi.RemoteException;
    public com.airtel.comviva.services.ModificationResponse modification(com.airtel.comviva.services.ModificationRequest modificationRequest) throws java.rmi.RemoteException;
    public com.airtel.comviva.services.RegistrationResponse registration(com.airtel.comviva.services.RegistrationRequest registrationRequest) throws java.rmi.RemoteException;
}
