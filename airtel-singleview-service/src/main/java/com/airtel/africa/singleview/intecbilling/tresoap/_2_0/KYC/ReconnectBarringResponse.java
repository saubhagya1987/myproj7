/**
 * ReconnectBarringResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class ReconnectBarringResponse  implements java.io.Serializable {
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponseRESULT RESULT;

    private org.apache.axis.client.Call _call;

    
    
    
    public org.apache.axis.client.Call get_call() {
		return _call;
	}

	public void set_call(org.apache.axis.client.Call _call) {
		this._call = _call;
	}

	public ReconnectBarringResponse() {
    }

    public ReconnectBarringResponse(
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponseRESULT RESULT) {
           this.RESULT = RESULT;
    }


    /**
     * Gets the RESULT value for this ReconnectBarringResponse.
     * 
     * @return RESULT
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponseRESULT getRESULT() {
        return RESULT;
    }


    /**
     * Sets the RESULT value for this ReconnectBarringResponse.
     * 
     * @param RESULT
     */
    public void setRESULT(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringResponseRESULT RESULT) {
        this.RESULT = RESULT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReconnectBarringResponse)) return false;
        ReconnectBarringResponse other = (ReconnectBarringResponse) obj;
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
