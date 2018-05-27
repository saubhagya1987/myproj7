/**
 * KYCSoap11BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class KYCSoap11BindingStub extends org.apache.axis.client.Stub implements com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("barSubscriberRequest_Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberRequest_UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberRequest"), com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberResponse"));
        oper.setReturnClass(com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberRequest_UpdateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
                      "com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reconnectBarringRequest_Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringRequest_UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringRequest"), com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringResponse"));
        oper.setReturnClass(com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringRequest_UpdateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
                      "com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateCustomerDemographicsReq_Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsReq_UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsReq"), com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsResponse"));
        oper.setReturnClass(com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsReq_UpdateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
                      "com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSIMNumberRequest_Fetch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberRequest_FetchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberRequest"), com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberResponse"));
        oper.setReturnClass(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberRequest_FetchResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
                      "com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceDetailsRequest_Fetch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsRequest_FetchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsRequest"), com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsResponse"));
        oper.setReturnClass(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsRequest_FetchResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
                      "com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMSISDNStatusRequest_Fetch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusRequest_FetchRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusRequest"), com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusResponse"));
        oper.setReturnClass(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusRequest_FetchResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
                      "com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
                      new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"), 
                      true
                     ));
        _operations[5] = oper;

    }

    public KYCSoap11BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public KYCSoap11BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public KYCSoap11BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">barSubscriberRequest>pReasoncode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">barSubscriberRequest>pSubno");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">barSubscriberRequest>pUsername");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">barSubscriberResponse.RESULT>RETURN_CODE");
            cachedSerQNames.add(qName);
            cls = long.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">barSubscriberResponse.RESULT>SERVICE_REQUEST_NUMBER");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">getMSISDNStatusRequest>SUBSCRIBER_NO");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">getMSISDNStatusResponse.RESULT>STATUS");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">getSIMNumberRequest>RETAILER_MOBILE_NO");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">getSIMNumberRequest>SUBSCRIBER_NO");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">getSIMNumberRequest>USER_NAME");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">getSIMNumberResponse.RESULT>SIM_NO");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">reconnectBarringRequest>pReasoncode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">reconnectBarringRequest>pSubno");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">reconnectBarringRequest>pUsername");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">reconnectBarringResponse.RESULT>RETURN_CODE");
            cachedSerQNames.add(qName);
            cls = long.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">reconnectBarringResponse.RESULT>SERVICE_REQUEST_NUMBER");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">RETAILER_MOBILE_NO");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">SUBSCRIBER_NO");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>ADDRESS_AREA");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>ADDRS_BLOCK");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>APARTMENT");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>BUILDING");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>COUNTRY");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>PO_BOX");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>POST_AREA");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>STREET");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.ADDRESS>ZIP_CODE");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.PERSON>FIRST_NAME");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.PERSON>ID_NUMBER");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.PERSON>ID_TYPE");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.PERSON>LAST_NAME");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.PERSON>MIDDLE_NAME");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", ">updateCustomerDemographicsReq.PERSON>SECONDARY_NUMBER");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberResponse.RESULT");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponseRESULT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusResponse.RESULT");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponseRESULT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberResponse.RESULT");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponseRESULT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringRequest");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringResponse.RESULT");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponseRESULT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "return");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROWSET[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROWSET");
            qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROWSET");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROW");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROW.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROWSET");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROWSET.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsReq");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsReq.ADDRESS");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsReq.PERSON");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsResponse");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsResponse.RESULT");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponseRESULT.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail");
            cachedSerQNames.add(qName);
            cls = com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail.class;
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

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse barSubscriberRequest_Update(com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:barSubscriberRequest_Update");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "barSubscriberRequest_Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse resp =(com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse) _resp;
                resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse resp =(com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse) _resp;
                resp.set_call(_call);
                return resp;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) {
              throw (com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse reconnectBarringRequest_Update(com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:reconnectBarringRequest_Update");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "reconnectBarringRequest_Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse) _resp;
                resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse) _resp;
                resp.set_call(_call);
                return resp;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) {
              throw (com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse updateCustomerDemographicsReq_Update(com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:updateCustomerDemographicsReq_Update");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateCustomerDemographicsReq_Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse) _resp;
                resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse) _resp;
                resp.set_call(_call);
                return resp;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) {
              throw (com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse getSIMNumberRequest_Fetch(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getSIMNumberRequest_Fetch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getSIMNumberRequest_Fetch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse) _resp;
                resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse) _resp;
                resp.set_call(_call);
                return resp;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) {
              throw (com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse getServiceDetailsRequest_Fetch(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getServiceDetailsRequest_Fetch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getServiceDetailsRequest_Fetch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse) _resp;
                resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse) _resp;
                resp.set_call(_call);
                return resp;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) {
              throw (com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse getMSISDNStatusRequest_Fetch(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getMSISDNStatusRequest_Fetch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMSISDNStatusRequest_Fetch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse) _resp;
                resp.set_call(_call);
                return resp;
            } catch (java.lang.Exception _exception) {
            	com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse resp = (com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse) _resp;
                resp.set_call(_call);
                return resp;
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) {
              throw (com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
