/**
 * GetMSISDNStatusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class GetMSISDNStatusResponse  implements java.io.Serializable {
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponseRESULT RESULT;

    public GetMSISDNStatusResponse() {
    }

    public GetMSISDNStatusResponse(
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponseRESULT RESULT) {
           this.RESULT = RESULT;
    }


    /**
     * Gets the RESULT value for this GetMSISDNStatusResponse.
     * 
     * @return RESULT
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponseRESULT getRESULT() {
        return RESULT;
    }


    /**
     * Sets the RESULT value for this GetMSISDNStatusResponse.
     * 
     * @param RESULT
     */
    public void setRESULT(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.GetMSISDNStatusResponseRESULT RESULT) {
        this.RESULT = RESULT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMSISDNStatusResponse)) return false;
        GetMSISDNStatusResponse other = (GetMSISDNStatusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RESULT==null && other.getRESULT()==null) || 
             (this.RESULT!=null &&
              this.RESULT.equals(other.getRESULT())));
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
        if (getRESULT() != null) {
            _hashCode += getRESULT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
