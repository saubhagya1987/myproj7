/**
 * ROWSET.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class ROWSET  implements java.io.Serializable {
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROW ROW;

    public ROWSET() {
    }

    public ROWSET(
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROW ROW) {
           this.ROW = ROW;
    }


    /**
     * Gets the ROW value for this ROWSET.
     * 
     * @return ROW
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROW getROW() {
        return ROW;
    }


    /**
     * Sets the ROW value for this ROWSET.
     * 
     * @param ROW
     */
    public void setROW(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROW ROW) {
        this.ROW = ROW;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ROWSET)) return false;
        ROWSET other = (ROWSET) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ROW==null && other.getROW()==null) || 
             (this.ROW!=null &&
              this.ROW.equals(other.getROW())));
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
        if (getROW() != null) {
            _hashCode += getROW().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
