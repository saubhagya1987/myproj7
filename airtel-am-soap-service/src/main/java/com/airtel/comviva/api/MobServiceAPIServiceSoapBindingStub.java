/**
 * MobServiceAPIServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.comviva.api;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MobServiceAPIServiceSoapBindingStub extends org.apache.axis.client.Stub implements com.airtel.comviva.api.MobServiceAPI {
	private static Log log = LogFactory.getLog(MobServiceAPIServiceSoapBindingStub.class);
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("blockService");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://api.comviva.com", "blockServiceRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/AirtelMoney", "BlockServiceRequest"), com.airtel.comviva.services.BlockServiceRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://services.comviva.com", "BlockServiceResponse"));
        oper.setReturnClass(com.airtel.comviva.services.BlockServiceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://api.comviva.com", "blockServiceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modification");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://api.comviva.com", "modificationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/AirtelMoney", "ModificationRequest"), com.airtel.comviva.services.ModificationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://services.comviva.com", "ModificationResponse"));
        oper.setReturnClass(com.airtel.comviva.services.ModificationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://api.comviva.com", "modificationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("registration");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://api.comviva.com", "registrationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/AirtelMoney", "RegistrationRequest"), com.airtel.comviva.services.RegistrationRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://services.comviva.com", "RegistrationResponse"));
        oper.setReturnClass(com.airtel.comviva.services.RegistrationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://api.comviva.com", "registrationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public MobServiceAPIServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public MobServiceAPIServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public MobServiceAPIServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://services.comviva.com", "BlockServiceRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.comviva.services.BlockServiceRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://services.comviva.com", "BlockServiceResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.comviva.services.BlockServiceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://services.comviva.com", "ModificationRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.comviva.services.ModificationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://services.comviva.com", "ModificationResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.comviva.services.ModificationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://services.comviva.com", "RegistrationRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.comviva.services.RegistrationRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://services.comviva.com", "RegistrationResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.comviva.services.RegistrationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.airtel.comviva.services.BlockServiceResponse blockService(com.airtel.comviva.services.BlockServiceRequest blockServiceRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://api.comviva.com", "blockService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {blockServiceRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.airtel.comviva.services.BlockServiceResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.airtel.comviva.services.BlockServiceResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.airtel.comviva.services.BlockServiceResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.airtel.comviva.services.ModificationResponse modification(com.airtel.comviva.services.ModificationRequest modificationRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://api.comviva.com", "modification"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modificationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	
               com.airtel.comviva.services.ModificationResponse res = (com.airtel.comviva.services.ModificationResponse) _resp;
               res.set_call(_call);
               return res;
            } catch (java.lang.Exception _exception) {
            	
            	com.airtel.comviva.services.ModificationResponse res=(com.airtel.comviva.services.ModificationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.airtel.comviva.services.ModificationResponse.class);
            	res.set_call(_call);
            	return res;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.airtel.comviva.services.RegistrationResponse registration(com.airtel.comviva.services.RegistrationRequest registrationRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://api.comviva.com", "registration"));

        setRequestHeaders(_call);
        setAttachments(_call);
        log.info("saubhagya call obj"+_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {registrationRequest});

        if (_resp instanceof java.rmi.RemoteException) {
        	log.info("saubhagya _resp obj"+_resp);
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	log.info("saubhagya _call obj"+_call);
            	com.airtel.comviva.services.RegistrationResponse resp = (com.airtel.comviva.services.RegistrationResponse) _resp;
            	resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	log.info("saubhagya _exception obj"+_exception);
            	com.airtel.comviva.services.RegistrationResponse resp = (com.airtel.comviva.services.RegistrationResponse) _resp;
            	resp.set_call(_call);
                return resp;
                //return (com.airtel.comviva.services.RegistrationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.airtel.comviva.services.RegistrationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
	  log.info("saubhagya axisFaultException obj"+axisFaultException);
	  axisFaultException.printStackTrace();
  throw axisFaultException;
}
    }

}
