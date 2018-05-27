/**
 * GetServiceDetailsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class GetServiceDetailsResponse  implements java.io.Serializable {
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC._return _return;

    public GetServiceDetailsResponse() {
    }

    public GetServiceDetailsResponse(
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC._return _return) {
           this._return = _return;
    }


    /**
     * Gets the _return value for this GetServiceDetailsResponse.
     * 
     * @return _return
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC._return get_return() {
        return _return;
    }


    /**
     * Sets the _return value for this GetServiceDetailsResponse.
     * 
     * @param _return
     */
    public void set_return(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC._return _return) {
        this._return = _return;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceDetailsResponse)) return false;
        GetServiceDetailsResponse other = (GetServiceDetailsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._return==null && other.get_return()==null) || 
             (this._return!=null &&
              this._return.equals(other.get_return())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (get_return() != null) {
            _hashCode += get_return().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
