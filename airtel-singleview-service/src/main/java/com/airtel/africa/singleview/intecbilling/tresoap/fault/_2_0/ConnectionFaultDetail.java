/**
 * ConnectionFaultDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0;

public class ConnectionFaultDetail  implements java.io.Serializable {
    private java.lang.String message;

    private java.math.BigInteger errorId;

    public ConnectionFaultDetail() {
    }

    public ConnectionFaultDetail(
           java.lang.String message,
           java.math.BigInteger errorId) {
           this.message = message;
           this.errorId = errorId;
    }


    /**
     * Gets the message value for this ConnectionFaultDetail.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ConnectionFaultDetail.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the errorId value for this ConnectionFaultDetail.
     * 
     * @return errorId
     */
    public java.math.BigInteger getErrorId() {
        return errorId;
    }


    /**
     * Sets the errorId value for this ConnectionFaultDetail.
     * 
     * @param errorId
     */
    public void setErrorId(java.math.BigInteger errorId) {
        this.errorId = errorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConnectionFaultDetail)) return false;
        ConnectionFaultDetail other = (ConnectionFaultDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.errorId==null && other.getErrorId()==null) || 
             (this.errorId!=null &&
              this.errorId.equals(other.getErrorId())));
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
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getErrorId() != null) {
            _hashCode += getErrorId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
