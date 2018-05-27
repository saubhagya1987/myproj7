/**
 * KYCLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class KYCLocator extends org.apache.axis.client.Service implements com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYC {

    public KYCLocator() {
    }


    public KYCLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public KYCLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for KYCHttpsSoap11Endpoint
    private java.lang.String KYCHttpsSoap11Endpoint_address = "https://172.23.3.45:8186/services/KYC.KYCHttpSoap11Endpoint";

    public java.lang.String getKYCHttpsSoap11EndpointAddress() {
        return KYCHttpsSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String KYCHttpsSoap11EndpointWSDDServiceName = "KYCHttpsSoap11Endpoint";

    public java.lang.String getKYCHttpsSoap11EndpointWSDDServiceName() {
        return KYCHttpsSoap11EndpointWSDDServiceName;
    }

    public void setKYCHttpsSoap11EndpointWSDDServiceName(java.lang.String name) {
        KYCHttpsSoap11EndpointWSDDServiceName = name;
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType getKYCHttpsSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(KYCHttpsSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getKYCHttpsSoap11Endpoint(endpoint);
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType getKYCHttpsSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub _stub = new com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub(portAddress, this);
            _stub.setPortName(getKYCHttpsSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setKYCHttpsSoap11EndpointEndpointAddress(java.lang.String address) {
        KYCHttpsSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub _stub = new com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCSoap11BindingStub(new java.net.URL(KYCHttpsSoap11Endpoint_address), this);
                _stub.setPortName(getKYCHttpsSoap11EndpointWSDDServiceName());
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
        if ("KYCHttpsSoap11Endpoint".equals(inputPortName)) {
            return getKYCHttpsSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "KYC");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "KYCHttpsSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("KYCHttpsSoap11Endpoint".equals(portName)) {
            setKYCHttpsSoap11EndpointEndpointAddress(address);
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
