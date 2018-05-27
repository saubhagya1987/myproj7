package com.airtel.comviva.api;

public class MobServiceAPIProxy implements com.airtel.comviva.api.MobServiceAPI {
  private String _endpoint = null;
  private com.airtel.comviva.api.MobServiceAPI mobServiceAPI = null;
  
  public MobServiceAPIProxy() {
    _initMobServiceAPIProxy();
  }
  
  public MobServiceAPIProxy(String endpoint) {
    _endpoint = endpoint;
    _initMobServiceAPIProxy();
  }
  
  private void _initMobServiceAPIProxy() {
    try {
      mobServiceAPI = (new com.airtel.comviva.api.MobServiceAPIServiceLocator()).getMobServiceAPIPort();
      if (mobServiceAPI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mobServiceAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mobServiceAPI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mobServiceAPI != null)
      ((javax.xml.rpc.Stub)mobServiceAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.airtel.comviva.api.MobServiceAPI getMobServiceAPI() {
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI;
  }
  
  public com.airtel.comviva.services.BlockServiceResponse blockService(com.airtel.comviva.services.BlockServiceRequest blockServiceRequest) throws java.rmi.RemoteException{
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI.blockService(blockServiceRequest);
  }
  
  public com.airtel.comviva.services.ModificationResponse modification(com.airtel.comviva.services.ModificationRequest modificationRequest) throws java.rmi.RemoteException{
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI.modification(modificationRequest);
  }
  
  public com.airtel.comviva.services.RegistrationResponse registration(com.airtel.comviva.services.RegistrationRequest registrationRequest) throws java.rmi.RemoteException{
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI.registration(registrationRequest);
  }
  
  
}