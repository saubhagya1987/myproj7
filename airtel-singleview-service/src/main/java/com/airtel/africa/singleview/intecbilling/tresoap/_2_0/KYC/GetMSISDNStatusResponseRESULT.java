/**
 * GetMSISDNStatusResponseRESULT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class GetMSISDNStatusResponseRESULT  implements java.io.Serializable {
    private java.lang.String STATUS;

    private java.lang.Double CURRENT_BALANCE;

    private java.math.BigInteger RETURN_CODE;

    private java.lang.String MESSAGE;

    public GetMSISDNStatusResponseRESULT() {
    }

    public GetMSISDNStatusResponseRESULT(
           java.lang.String STATUS,
           java.lang.Double CURRENT_BALANCE,
           java.math.BigInteger RETURN_CODE,
           java.lang.String MESSAGE) {
           this.STATUS = STATUS;
           this.CURRENT_BALANCE = CURRENT_BALANCE;
           this.RETURN_CODE = RETURN_CODE;
           this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the STATUS value for this GetMSISDNStatusResponseRESULT.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this GetMSISDNStatusResponseRESULT.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     * Gets the CURRENT_BALANCE value for this GetMSISDNStatusResponseRESULT.
     * 
     * @return CURRENT_BALANCE
     */
    public java.lang.Double getCURRENT_BALANCE() {
        return CURRENT_BALANCE;
    }


    /**
     * Sets the CURRENT_BALANCE value for this GetMSISDNStatusResponseRESULT.
     * 
     * @param CURRENT_BALANCE
     */
    public void setCURRENT_BALANCE(java.lang.Double CURRENT_BALANCE) {
        this.CURRENT_BALANCE = CURRENT_BALANCE;
    }


    /**
     * Gets the RETURN_CODE value for this GetMSISDNStatusResponseRESULT.
     * 
     * @return RETURN_CODE
     */
    public java.math.BigInteger getRETURN_CODE() {
        return RETURN_CODE;
    }


    /**
     * Sets the RETURN_CODE value for this GetMSISDNStatusResponseRESULT.
     * 
     * @param RETURN_CODE
     */
    public void setRETURN_CODE(java.math.BigInteger RETURN_CODE) {
        this.RETURN_CODE = RETURN_CODE;
    }


    /**
     * Gets the MESSAGE value for this GetMSISDNStatusResponseRESULT.
     * 
     * @return MESSAGE
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this GetMSISDNStatusResponseRESULT.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMSISDNStatusResponseRESULT)) return false;
        GetMSISDNStatusResponseRESULT other = (GetMSISDNStatusResponseRESULT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS()))) &&
            ((this.CURRENT_BALANCE==null && other.getCURRENT_BALANCE()==null) || 
             (this.CURRENT_BALANCE!=null &&
              this.CURRENT_BALANCE.equals(other.getCURRENT_BALANCE()))) &&
            ((this.RETURN_CODE==null && other.getRETURN_CODE()==null) || 
             (this.RETURN_CODE!=null &&
              this.RETURN_CODE.equals(other.getRETURN_CODE()))) &&
            ((this.MESSAGE==null && other.getMESSAGE()==null) || 
             (this.MESSAGE!=null &&
              this.MESSAGE.equals(other.getMESSAGE())));
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
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        if (getCURRENT_BALANCE() != null) {
            _hashCode += getCURRENT_BALANCE().hashCode();
        }
        if (getRETURN_CODE() != null) {
            _hashCode += getRETURN_CODE().hashCode();
        }
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
