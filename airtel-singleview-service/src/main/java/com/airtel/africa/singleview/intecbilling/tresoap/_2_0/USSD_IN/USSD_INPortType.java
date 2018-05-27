/**
 * USSD_INPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN;

public interface USSD_INPortType extends java.rmi.Remote {

    /**
     * Guided to UDG:WSAtransferOwnershipRequest.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse transferOwnershipRequest_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAChangeprepostpaid.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType changeprepostpaid_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Changeprepostpaid parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSACreateNewSubscriberRequestType.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse createNewSubscriberRequestType_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberRequestType parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAfetchMSISDNStatusRequest.Fetch& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse fetchMSISDNStatusRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAcreateMSISDNRequest.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse createMSISDNRequest_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateMSISDNRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAbarOnSubscriberRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse barOnSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAMSISDNSAFECUSTODY.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType MSISDNSAFECUSTODY_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODY parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAdisconnectOnSubscriberRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse disconnectOnSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAaddressChangeRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse addressChangeRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAReConnectbarRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse reConnectbarRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAterminateOnSubscriberRequestType.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType terminateOnSubscriberRequestType_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestType parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSACHANGEMSISDN.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType CHANGEMSISDN_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDN parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAfetchSubscriberSIMSummary.Fetch& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes fetchSubscriberSIMSummary_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummary parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSApaySubscriber.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType paySubscriber_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriber parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAnameChangeRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse nameChangeRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAfetchMNPVerificationRequest.Fetch& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse fetchMNPVerificationRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSACHANGESERVICE.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType CHANGESERVICE_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICE parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAcreateNewInteraction.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse createNewInteraction_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteraction parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSACHANGESIMCARD.Insert& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType CHANGESIMCARD_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARD parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAupdateMSISDNStatusRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse updateMSISDNStatusRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.UpdateMSISDNStatusRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAREINSTALLSUBSCRIBERRequestType.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType REINSTALLSUBSCRIBERRequestType_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERRequestType parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAfetchAvailableUsageLimit.Fetch& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp fetchAvailableUsageLimit_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimit parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;

    /**
     * Guided to UDG:WSAreconnectOnSubscriberRequest.Update& [1]
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse reconnectOnSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail;
}
