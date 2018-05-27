/**
 * MobServiceAPIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.comviva.api;

public class MobServiceAPIServiceLocator extends org.apache.axis.client.Service implements com.comviva.api.MobServiceAPIService {

    public MobServiceAPIServiceLocator() {
    }


    public MobServiceAPIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MobServiceAPIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MobServiceAPIPort
    private java.lang.String MobServiceAPIPort_address = "http://172.27.34.27:7852/Kyc/services/MobServiceAPI";

    public java.lang.String getMobServiceAPIPortAddress() {
        return MobServiceAPIPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MobServiceAPIPortWSDDServiceName = "MobServiceAPIPort";

    public java.lang.String getMobServiceAPIPortWSDDServiceName() {
        return MobServiceAPIPortWSDDServiceName;
    }

    public void setMobServiceAPIPortWSDDServiceName(java.lang.String name) {
        MobServiceAPIPortWSDDServiceName = name;
    }

    public com.comviva.api.MobServiceAPI getMobServiceAPIPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MobServiceAPIPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMobServiceAPIPort(endpoint);
    }

    public com.comviva.api.MobServiceAPI getMobServiceAPIPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.comviva.api.MobServiceAPIServiceSoapBindingStub _stub = new com.comviva.api.MobServiceAPIServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMobServiceAPIPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMobServiceAPIPortEndpointAddress(java.lang.String address) {
        MobServiceAPIPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.comviva.api.MobServiceAPI.class.isAssignableFrom(serviceEndpointInterface)) {
                com.comviva.api.MobServiceAPIServiceSoapBindingStub _stub = new com.comviva.api.MobServiceAPIServiceSoapBindingStub(new java.net.URL(MobServiceAPIPort_address), this);
                _stub.setPortName(getMobServiceAPIPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MobServiceAPIPort".equals(inputPortName)) {
            return getMobServiceAPIPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://api.comviva.com", "MobServiceAPIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://api.comviva.com", "MobServiceAPIPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MobServiceAPIPort".equals(portName)) {
            setMobServiceAPIPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
