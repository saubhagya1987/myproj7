/**
 * _return.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class _return  implements java.io.Serializable {
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROWSET[] ROWSET;

    public _return() {
    }

    public _return(
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROWSET[] ROWSET) {
           this.ROWSET = ROWSET;
    }


    /**
     * Gets the ROWSET value for this _return.
     * 
     * @return ROWSET
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROWSET[] getROWSET() {
        return ROWSET;
    }


    /**
     * Sets the ROWSET value for this _return.
     * 
     * @param ROWSET
     */
    public void setROWSET(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROWSET[] ROWSET) {
        this.ROWSET = ROWSET;
    }

    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROWSET getROWSET(int i) {
        return this.ROWSET[i];
    }

    public void setROWSET(int i, com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ROWSET _value) {
        this.ROWSET[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _return)) return false;
        _return other = (_return) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ROWSET==null && other.getROWSET()==null) || 
             (this.ROWSET!=null &&
              java.util.Arrays.equals(this.ROWSET, other.getROWSET())));
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
        if (getROWSET() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getROWSET());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getROWSET(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
