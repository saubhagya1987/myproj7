/**
 * USSD_INLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN;

public class USSD_INLocator extends org.apache.axis.client.Service implements com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_IN {

    public USSD_INLocator() {
    }


    public USSD_INLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public USSD_INLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for USSD_INHttpSoap11Endpoint
    private java.lang.String USSD_INHttpSoap11Endpoint_address = "http://172.23.36.100:8137/services/USSD_IN.USSD_INHttpSoap11Endpoint";

    public java.lang.String getUSSD_INHttpSoap11EndpointAddress() {
        return USSD_INHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String USSD_INHttpSoap11EndpointWSDDServiceName = "USSD_INHttpSoap11Endpoint";

    public java.lang.String getUSSD_INHttpSoap11EndpointWSDDServiceName() {
        return USSD_INHttpSoap11EndpointWSDDServiceName;
    }

    public void setUSSD_INHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        USSD_INHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType getUSSD_INHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(USSD_INHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUSSD_INHttpSoap11Endpoint(endpoint);
    }

    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType getUSSD_INHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INSoap11BindingStub _stub = new com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INSoap11BindingStub(portAddress, this);
            _stub.setPortName(getUSSD_INHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUSSD_INHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        USSD_INHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INSoap11BindingStub _stub = new com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INSoap11BindingStub(new java.net.URL(USSD_INHttpSoap11Endpoint_address), this);
                _stub.setPortName(getUSSD_INHttpSoap11EndpointWSDDServiceName());
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
        if ("USSD_INHttpSoap11Endpoint".equals(inputPortName)) {
            return getUSSD_INHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN", "USSD_IN");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN", "USSD_INHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("USSD_INHttpSoap11Endpoint".equals(portName)) {
            setUSSD_INHttpSoap11EndpointEndpointAddress(address);
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
