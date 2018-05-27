package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN;

public class USSD_INPortTypeProxy implements com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType {
  private String _endpoint = null;
  private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType uSSD_INPortType = null;
  
  public USSD_INPortTypeProxy() {
    _initUSSD_INPortTypeProxy();
  }
  
  public USSD_INPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initUSSD_INPortTypeProxy();
  }
  
  private void _initUSSD_INPortTypeProxy() {
    try {
      uSSD_INPortType = (new com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INLocator()).getUSSD_INHttpSoap11Endpoint();
      if (uSSD_INPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)uSSD_INPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)uSSD_INPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (uSSD_INPortType != null)
      ((javax.xml.rpc.Stub)uSSD_INPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD_IN.USSD_INPortType getUSSD_INPortType() {
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType;
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipResponse transferOwnershipRequest_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TransferOwnershipRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.transferOwnershipRequest_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ChangeprepostpaidResponseType changeprepostpaid_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.Changeprepostpaid parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.changeprepostpaid_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberResponse createNewSubscriberRequestType_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewSubscriberRequestType parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.createNewSubscriberRequestType_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusResponse fetchMSISDNStatusRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMSISDNStatusRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.fetchMSISDNStatusRequest_Fetch(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse createMSISDNRequest_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateMSISDNRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.createMSISDNRequest_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberResponse barOnSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.BarOnSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.barOnSubscriberRequest_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODYResponseType MSISDNSAFECUSTODY_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNSAFECUSTODY parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.MSISDNSAFECUSTODY_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberResponse disconnectOnSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.DisconnectOnSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.disconnectOnSubscriberRequest_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeResponse addressChangeRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.AddressChangeRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.addressChangeRequest_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarResponse reConnectbarRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReConnectbarRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.reConnectbarRequest_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestResponseType terminateOnSubscriberRequestType_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.TerminateOnSubscriberRequestType parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.terminateOnSubscriberRequestType_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDNResponseType CHANGEMSISDN_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGEMSISDN parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.CHANGEMSISDN_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummaryRes fetchSubscriberSIMSummary_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchSubscriberSIMSummary parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.fetchSubscriberSIMSummary_Fetch(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriberResponseType paySubscriber_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PaySubscriber parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.paySubscriber_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeResponse nameChangeRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.NameChangeRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.nameChangeRequest_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationResponse fetchMNPVerificationRequest_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchMNPVerificationRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.fetchMNPVerificationRequest_Fetch(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICEResponseType CHANGESERVICE_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESERVICE parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.CHANGESERVICE_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteractionResponse createNewInteraction_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CreateNewInteraction parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.createNewInteraction_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARDResponseType CHANGESIMCARD_Insert(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CHANGESIMCARD parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.CHANGESIMCARD_Insert(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResponse updateMSISDNStatusRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.UpdateMSISDNStatusRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.updateMSISDNStatusRequest_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERResponseType REINSTALLSUBSCRIBERRequestType_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.REINSTALLSUBSCRIBERRequestType parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.REINSTALLSUBSCRIBERRequestType_Update(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimitResp fetchAvailableUsageLimit_Fetch(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.FetchAvailableUsageLimit parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.fetchAvailableUsageLimit_Fetch(parameters);
  }
  
  public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberResponse reconnectOnSubscriberRequest_Update(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.ReconnectOnSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0.USSD.ConnectionFaultDetail{
    if (uSSD_INPortType == null)
      _initUSSD_INPortTypeProxy();
    return uSSD_INPortType.reconnectOnSubscriberRequest_Update(parameters);
  }
  
  
}