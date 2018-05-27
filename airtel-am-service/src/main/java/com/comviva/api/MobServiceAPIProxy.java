package com.comviva.api;

public class MobServiceAPIProxy implements com.comviva.api.MobServiceAPI {
  private String _endpoint = null;
  private com.comviva.api.MobServiceAPI mobServiceAPI = null;
  
  public MobServiceAPIProxy() {
    _initMobServiceAPIProxy();
  }
  
  public MobServiceAPIProxy(String endpoint) {
    _endpoint = endpoint;
    _initMobServiceAPIProxy();
  }
  
  private void _initMobServiceAPIProxy() {
    try {
      mobServiceAPI = (new com.comviva.api.MobServiceAPIServiceLocator()).getMobServiceAPIPort();
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
  
  public com.comviva.api.MobServiceAPI getMobServiceAPI() {
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI;
  }
  
  public com.comviva.services.ModificationReturn modification(com.comviva.services.ModificationRequest modificationRequest) throws java.rmi.RemoteException{
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI.modification(modificationRequest);
  }
  
  public com.comviva.services.RegistrationReturn registration(com.comviva.services.RegistrationRequest registrationRequest) throws java.rmi.RemoteException{
    if (mobServiceAPI == null)
      _initMobServiceAPIProxy();
    return mobServiceAPI.registration(registrationRequest);
  }
  
  
}