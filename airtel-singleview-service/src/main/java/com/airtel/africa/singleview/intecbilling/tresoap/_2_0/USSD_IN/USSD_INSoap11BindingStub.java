/**
 * USSD_INSoap11BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN;

public class USSD_INSoap11BindingStub extends org.apache.axis.client.Stub
		implements com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[23];
		_initOperationDesc1();
		_initOperationDesc2();
		_initOperationDesc3();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("transferOwnershipRequest_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"transferOwnershipRequest_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "transferOwnershipRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipRequest.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "transferOwnershipResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"transferOwnershipRequest_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Changeprepostpaid_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"Changeprepostpaid_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Changeprepostpaid"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Changeprepostpaid.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"ChangeprepostpaidResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"Changeprepostpaid_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CreateNewSubscriberRequestType_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"CreateNewSubscriberRequestType_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
						"CreateNewSubscriberRequestType"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberRequestType.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"CreateNewSubscriberResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"CreateNewSubscriberRequestType_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("fetchMSISDNStatusRequest_Fetch");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"fetchMSISDNStatusRequest_FetchRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchMSISDNStatusRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusRequest.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchMSISDNStatusResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"fetchMSISDNStatusRequest_FetchResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("createMSISDNRequest_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"createMSISDNRequest_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "createMSISDNRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateMSISDNRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNResponse"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"createMSISDNRequest_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("barOnSubscriberRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"barOnSubscriberRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "barOnSubscriberRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "barOnSubscriberResponse"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"barOnSubscriberRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("MSISDNSAFECUSTODY_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"MSISDNSAFECUSTODY_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNSAFECUSTODY"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODY.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"MSISDNSAFECUSTODYResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"MSISDNSAFECUSTODY_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[6] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("disconnectOnSubscriberRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"disconnectOnSubscriberRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
						"disconnectOnSubscriberRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberRequest.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"disconnectOnSubscriberResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"disconnectOnSubscriberRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[7] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("addressChangeRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"addressChangeRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "addressChangeRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "addressChangeResponse"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"addressChangeRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[8] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ReConnectbarRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"ReConnectbarRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ReConnectbarRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ReConnectbarResponse"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"ReConnectbarRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[9] = oper;

	}

	private static void _initOperationDesc2() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("terminateOnSubscriberRequestType_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"terminateOnSubscriberRequestType_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
						"terminateOnSubscriberRequestType"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestType.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"terminateOnSubscriberRequestResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"terminateOnSubscriberRequestType_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[10] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CHANGEMSISDN_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"CHANGEMSISDN_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGEMSISDN"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDN.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGEMSISDNResponseType"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"CHANGEMSISDN_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[11] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("fetchSubscriberSIMSummary_Fetch");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"fetchSubscriberSIMSummary_FetchRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchSubscriberSIMSummary"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummary.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchSubscriberSIMSummaryRes"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"fetchSubscriberSIMSummary_FetchResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[12] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("paySubscriber_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"paySubscriber_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "paySubscriber"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriber.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "paySubscriberResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"paySubscriber_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[13] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("nameChangeRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"nameChangeRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "nameChangeRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeRequest.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "nameChangeResponse"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"nameChangeRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[14] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("fetchMNPVerificationRequest_Fetch");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"fetchMNPVerificationRequest_FetchRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
						"fetchMNPVerificationRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationRequest.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchMNPVerificationResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"fetchMNPVerificationRequest_FetchResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[15] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CHANGESERVICE_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"CHANGESERVICE_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESERVICE"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICE.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESERVICEResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"CHANGESERVICE_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[16] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("createNewInteraction_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"createNewInteraction_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "createNewInteraction"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteraction.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"createNewInteractionResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"createNewInteraction_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[17] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CHANGESIMCARD_Insert");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"CHANGESIMCARD_InsertRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESIMCARD"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARD.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESIMCARDResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"CHANGESIMCARD_InsertResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[18] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("updateMSISDNStatusRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"updateMSISDNStatusRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "updateMSISDNStatusRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.UpdateMSISDNStatusRequest.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNResponse"));
		oper.setReturnClass(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"updateMSISDNStatusRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[19] = oper;

	}

	private static void _initOperationDesc3() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("REINSTALLSUBSCRIBERRequestType_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"REINSTALLSUBSCRIBERRequestType_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
						"REINSTALLSUBSCRIBERRequestType"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERRequestType.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"REINSTALLSUBSCRIBERResponseType"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"REINSTALLSUBSCRIBERRequestType_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[20] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("fetchAvailableUsageLimit_Fetch");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"fetchAvailableUsageLimit_FetchRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchAvailableUsageLimit"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimit.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchAvailableUsageLimitResp"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"fetchAvailableUsageLimit_FetchResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[21] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("reconnectOnSubscriberRequest_Update");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
						"reconnectOnSubscriberRequest_UpdateRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
						"reconnectOnSubscriberRequest"),
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberRequest.class, false,
				false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"reconnectOnSubscriberResponse"));
		oper.setReturnClass(
				com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD_IN",
				"reconnectOnSubscriberRequest_UpdateResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFault"),
				"com.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail",
				new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail"),
				true));
		_operations[22] = oper;

	}

	public USSD_INSoap11BindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public USSD_INSoap11BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public USSD_INSoap11BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
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
		addBindings0();
		addBindings1();
	}

	private void addBindings0() {
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
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">CHANGESERVICE>pArea");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">CHANGESERVICE>pSubNo");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">CHANGESERVICE>pSubscrType");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">CHANGESERVICE>pSubsidyFlag");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">CHANGESERVICE>pUsername");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createMSISDNRequest>Gaining_Operator");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createMSISDNRequest>MSISDN");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createMSISDNRequest>MSISDN_Pool");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createMSISDNRequest>Vanity_Category");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createNewInteraction>pAccountNo");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createNewInteraction>pDescription");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createNewInteraction>pInteractionMode");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createNewInteraction>pInteractionType");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createNewInteraction>pSubNo");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">createNewInteraction>pUsername");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">CSOutputParams>Code");
		cachedSerQNames.add(qName);
		cls = long.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">CSOutputParams>Description");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMNPVerificationRequest>MSISDN");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMNPVerificationResponse>Identity_Number");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMNPVerificationResponse>Identity_Type");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMNPVerificationResponse>Status");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMSISDNStatusRequest>MSISDN");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMSISDNStatusResponse>Donor_Operator");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">fetchMSISDNStatusResponse>Status");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">MSISDNResult>MESSAGE");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">MSISDNResult>RETURN_CODE");
		cachedSerQNames.add(qName);
		cls = long.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", ">RESULT>MESSAGE");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">updateMSISDNStatusRequest>Donor_Operator");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">updateMSISDNStatusRequest>Gaining_Operator");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">updateMSISDNStatusRequest>MSISDN");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				">updateMSISDNStatusRequest>Status");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
		cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
				.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "addressChangeRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "addressChangeResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "barOnSubscriberRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "barOnSubscriberResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGEMSISDN");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDN.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGEMSISDNResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Changeprepostpaid");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Changeprepostpaid.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"ChangeprepostpaidResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESERVICE");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICE.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESERVICEResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESIMCARD");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARD.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESIMCARDResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChgServiceList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChgServiceList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChgSrvService");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChgSrvService.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChgSrvXMLServices");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChgServiceList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChgServiceList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SServiceList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "createMSISDNRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateMSISDNRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "createNewInteraction");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteraction.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"createNewInteractionResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"CreateNewSubscriberRequestType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberRequestType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"CreateNewSubscriberResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSOperation");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSOperation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSOutputParams");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSOutputParams.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSService");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSService.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSParam");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParam.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSParamList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParamList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSService");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSService.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSServiceList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSServiceList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSXMLServices");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSServiceList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSServiceList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SServiceList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"disconnectOnSubscriberRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"disconnectOnSubscriberResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchAvailableUsageLimit");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimit.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchAvailableUsageLimitResp");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchMNPVerificationRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchMNPVerificationResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchMSISDNStatusRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchMSISDNStatusResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchSubscriberSIMSummary");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummary.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"fetchSubscriberSIMSummaryRes");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "InputParamsInvoice");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Invoice[][].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "InvoiceList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "InvoiceList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "InputParamsPayment");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaymentList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PaymentList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PaymentList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Invoice");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Invoice.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "InvoiceList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Invoice[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Invoice");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Invoice");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNResult");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNSAFECUSTODY");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODY.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"MSISDNSAFECUSTODYResponseResult");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseResult.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"MSISDNSAFECUSTODYResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "nameChangeRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "nameChangeResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Operation");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Operation.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "OperationInvoice");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.OperationInvoice.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "OperationPayment");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.OperationPayment.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "OutputParams");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.OutputParams.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pAdditionalparams");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pAdditonalparams");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Payment");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Payment.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PaymentList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaymentList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "paySubscriber");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriber.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "paySubscriberResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pChgServices");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PChgServices.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pCSAdditionalparams");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParamList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSParamList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pCSServices");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PCSServices.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pParamlistXml");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ReConnectbarRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ReConnectbarResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"reconnectOnSubscriberRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"reconnectOnSubscriberResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"REINSTALLSUBSCRIBERRequestType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERRequestType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	private void addBindings1() {
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
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"REINSTALLSUBSCRIBERResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "RESULT");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.RESULT.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Service");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Service.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ServiceInvoice");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ServiceInvoice.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ServicePayment");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ServicePayment.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParam");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParam.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SService");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SService.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SServiceList");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SServiceList.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"terminateOnSubscriberRequestResponseType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD",
				"terminateOnSubscriberRequestType");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestType.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "transferOwnershipRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "transferOwnershipResponse");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "updateMSISDNStatusRequest");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.UpdateMSISDNStatusRequest.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "xmlInvoicesInfo");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlInvoicesInfo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "xmlPaymentInfo");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlPaymentInfo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "XMLServices");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SServiceList[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SServiceList");
		qName2 = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SServiceList");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "ConnectionFaultDetail");
		cachedSerQNames.add(qName);
		cls = com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail.class;
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
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class) cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class) cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse transferOwnershipRequest_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:transferOwnershipRequest_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "transferOwnershipRequest_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType changeprepostpaid_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Changeprepostpaid parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:Changeprepostpaid_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "Changeprepostpaid_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse createNewSubscriberRequestType_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberRequestType parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:CreateNewSubscriberRequestType_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "CreateNewSubscriberRequestType_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse fetchMSISDNStatusRequest_Fetch(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:fetchMSISDNStatusRequest_Fetch");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "fetchMSISDNStatusRequest_Fetch"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse createMSISDNRequest_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateMSISDNRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:createMSISDNRequest_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "createMSISDNRequest_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse barOnSubscriberRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:barOnSubscriberRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "barOnSubscriberRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType MSISDNSAFECUSTODY_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODY parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:MSISDNSAFECUSTODY_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "MSISDNSAFECUSTODY_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse disconnectOnSubscriberRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:disconnectOnSubscriberRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "disconnectOnSubscriberRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse addressChangeRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:addressChangeRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "addressChangeRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse reConnectbarRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:ReConnectbarRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ReConnectbarRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType terminateOnSubscriberRequestType_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestType parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:terminateOnSubscriberRequestType_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "terminateOnSubscriberRequestType_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType CHANGEMSISDN_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDN parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:CHANGEMSISDN_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "CHANGEMSISDN_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes fetchSubscriberSIMSummary_Fetch(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummary parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:fetchSubscriberSIMSummary_Fetch");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "fetchSubscriberSIMSummary_Fetch"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes resp = (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes) _resp;
					resp.set_call(_call);
					return resp;
				} catch (java.lang.Exception _exception) {
					com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes resp = (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes) _resp;
					resp.set_call(_call);
					return resp;
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType paySubscriber_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriber parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[13]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:paySubscriber_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "paySubscriber_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse nameChangeRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[14]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:nameChangeRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "nameChangeRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse fetchMNPVerificationRequest_Fetch(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[15]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:fetchMNPVerificationRequest_Fetch");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "fetchMNPVerificationRequest_Fetch"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType CHANGESERVICE_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICE parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[16]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:CHANGESERVICE_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "CHANGESERVICE_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse createNewInteraction_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteraction parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[17]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:createNewInteraction_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "createNewInteraction_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType CHANGESIMCARD_Insert(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARD parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[18]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:CHANGESIMCARD_Insert");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "CHANGESIMCARD_Insert"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse updateMSISDNStatusRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.UpdateMSISDNStatusRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[19]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:updateMSISDNStatusRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "updateMSISDNStatusRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType REINSTALLSUBSCRIBERRequestType_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERRequestType parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[20]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:REINSTALLSUBSCRIBERRequestType_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "REINSTALLSUBSCRIBERRequestType_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp fetchAvailableUsageLimit_Fetch(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimit parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[21]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:fetchAvailableUsageLimit_Fetch");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "fetchAvailableUsageLimit_Fetch"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse reconnectOnSubscriberRequest_Update(
			com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberRequest parameters)
			throws java.rmi.RemoteException,
			com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[22]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:reconnectOnSubscriberRequest_Update");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "reconnectOnSubscriberRequest_Update"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse) org.apache.axis.utils.JavaUtils
							.convert(_resp,
									com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) {
					throw (com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

}
