/**
 * KYCPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public interface KYCPortType extends java.rmi.Remote {

    /**
     * Guided to
     * 				KYC:WSAreconnectBarringRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse reconnectBarringRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest parameters) throws java.rmi.RemoteException;

    /**
     * Guided to
     * 				KYC:WSAgetMSISDNStatusRequest.Fetch& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse getMSISDNStatusRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest parameters) throws java.rmi.RemoteException;

    /**
     * Guided to
     * 				KYC:WSAgetServiceDetailsRequest.Fetch& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse getServiceDetailsRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetServiceDetailsRequest parameters) throws java.rmi.RemoteException;

    /**
     * Guided to KYC:WSAgetSIMNumberRequest.Fetch&
     * 				[1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse getSIMNumberRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetSIMNumberRequest parameters) throws java.rmi.RemoteException;

    /**
     * Guided to KYC:WSAbarSubscriberRequest.Update&
     * 				[1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse barSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.BarSubscriberRequest parameters) throws java.rmi.RemoteException;

    /**
     * Guided to
     * 				KYC:WSAupdateCustomerDemographicsReq.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse updateCustomerDemographicsReq_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq parameters) throws java.rmi.RemoteException;
}
