package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class KYCPortTypeProxy implements com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType {
  private String _endpoint = null;
  private com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType kYCPortType = null;
  
  public KYCPortTypeProxy() {
    _initKYCPortTypeProxy();
  }
  
  public KYCPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initKYCPortTypeProxy();
  }
  
  private void _initKYCPortTypeProxy() {
    try {
      kYCPortType = (new com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCLocator()).getKYCHttpsSoap11Endpoint();
      if (kYCPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)kYCPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)kYCPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (kYCPortType != null)
      ((javax.xml.rpc.Stub)kYCPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.KYCPortType getKYCPortType() {
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType;
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponse barSubscriberRequest_Update(com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail{
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType.barSubscriberRequest_Update(parameters);
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponse reconnectBarringRequest_Update(com.airtel.africa.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail{
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType.reconnectBarringRequest_Update(parameters);
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse updateCustomerDemographicsReq_Update(com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail{
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType.updateCustomerDemographicsReq_Update(parameters);
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse getSIMNumberRequest_Fetch(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail{
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType.getSIMNumberRequest_Fetch(parameters);
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsResponse getServiceDetailsRequest_Fetch(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetServiceDetailsRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail{
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType.getServiceDetailsRequest_Fetch(parameters);
  }
  
  public com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponse getMSISDNStatusRequest_Fetch(com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusRequest parameters) throws java.rmi.RemoteException, com.airtel.africa.intecbilling.tresoap.fault._2_0.ConnectionFaultDetail{
    if (kYCPortType == null)
      _initKYCPortTypeProxy();
    return kYCPortType.getMSISDNStatusRequest_Fetch(parameters);
  }
  
  
}