/**
 * RegistrationReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.comviva.services;

public class RegistrationReturn  implements java.io.Serializable {
    private java.lang.String ERRCODE;

    private java.lang.String MSISDN;

    private java.lang.String TYPE;

    private org.apache.axis.client.Call _call;

	public org.apache.axis.client.Call get_call() {
		return _call;
	}

	public void set_call(org.apache.axis.client.Call _call) {
		this._call = _call;
	}

    
    public RegistrationReturn() {
    }

    public RegistrationReturn(
           java.lang.String ERRCODE,
           java.lang.String MSISDN,
           java.lang.String TYPE) {
           this.ERRCODE = ERRCODE;
           this.MSISDN = MSISDN;
           this.TYPE = TYPE;
    }


    /**
     * Gets the ERRCODE value for this RegistrationReturn.
     * 
     * @return ERRCODE
     */
    public java.lang.String getERRCODE() {
        return ERRCODE;
    }


    /**
     * Sets the ERRCODE value for this RegistrationReturn.
     * 
     * @param ERRCODE
     */
    public void setERRCODE(java.lang.String ERRCODE) {
        this.ERRCODE = ERRCODE;
    }


    /**
     * Gets the MSISDN value for this RegistrationReturn.
     * 
     * @return MSISDN
     */
    public java.lang.String getMSISDN() {
        return MSISDN;
    }


    /**
     * Sets the MSISDN value for this RegistrationReturn.
     * 
     * @param MSISDN
     */
    public void setMSISDN(java.lang.String MSISDN) {
        this.MSISDN = MSISDN;
    }


    /**
     * Gets the TYPE value for this RegistrationReturn.
     * 
     * @return TYPE
     */
    public java.lang.String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this RegistrationReturn.
     * 
     * @param TYPE
     */
    public void setTYPE(java.lang.String TYPE) {
        this.TYPE = TYPE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistrationReturn)) return false;
        RegistrationReturn other = (RegistrationReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ERRCODE==null && other.getERRCODE()==null) || 
             (this.ERRCODE!=null &&
              this.ERRCODE.equals(other.getERRCODE()))) &&
            ((this.MSISDN==null && other.getMSISDN()==null) || 
             (this.MSISDN!=null &&
              this.MSISDN.equals(other.getMSISDN()))) &&
            ((this.TYPE==null && other.getTYPE()==null) || 
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE())));
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
        if (getERRCODE() != null) {
            _hashCode += getERRCODE().hashCode();
        }
        if (getMSISDN() != null) {
            _hashCode += getMSISDN().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
