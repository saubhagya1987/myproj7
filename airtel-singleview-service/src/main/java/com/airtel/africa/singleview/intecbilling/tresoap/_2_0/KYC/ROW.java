/**
 * ROW.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class ROW  implements java.io.Serializable {
    private java.math.BigInteger NUMBER_OF_SERVICES;

    private java.math.BigInteger EQUIPID;

    private java.lang.String EQUIPNAME;

    public ROW() {
    }

    public ROW(
           java.math.BigInteger NUMBER_OF_SERVICES,
           java.math.BigInteger EQUIPID,
           java.lang.String EQUIPNAME) {
           this.NUMBER_OF_SERVICES = NUMBER_OF_SERVICES;
           this.EQUIPID = EQUIPID;
           this.EQUIPNAME = EQUIPNAME;
    }


    /**
     * Gets the NUMBER_OF_SERVICES value for this ROW.
     * 
     * @return NUMBER_OF_SERVICES
     */
    public java.math.BigInteger getNUMBER_OF_SERVICES() {
        return NUMBER_OF_SERVICES;
    }


    /**
     * Sets the NUMBER_OF_SERVICES value for this ROW.
     * 
     * @param NUMBER_OF_SERVICES
     */
    public void setNUMBER_OF_SERVICES(java.math.BigInteger NUMBER_OF_SERVICES) {
        this.NUMBER_OF_SERVICES = NUMBER_OF_SERVICES;
    }


    /**
     * Gets the EQUIPID value for this ROW.
     * 
     * @return EQUIPID
     */
    public java.math.BigInteger getEQUIPID() {
        return EQUIPID;
    }


    /**
     * Sets the EQUIPID value for this ROW.
     * 
     * @param EQUIPID
     */
    public void setEQUIPID(java.math.BigInteger EQUIPID) {
        this.EQUIPID = EQUIPID;
    }


    /**
     * Gets the EQUIPNAME value for this ROW.
     * 
     * @return EQUIPNAME
     */
    public java.lang.String getEQUIPNAME() {
        return EQUIPNAME;
    }


    /**
     * Sets the EQUIPNAME value for this ROW.
     * 
     * @param EQUIPNAME
     */
    public void setEQUIPNAME(java.lang.String EQUIPNAME) {
        this.EQUIPNAME = EQUIPNAME;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ROW)) return false;
        ROW other = (ROW) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NUMBER_OF_SERVICES==null && other.getNUMBER_OF_SERVICES()==null) || 
             (this.NUMBER_OF_SERVICES!=null &&
              this.NUMBER_OF_SERVICES.equals(other.getNUMBER_OF_SERVICES()))) &&
            ((this.EQUIPID==null && other.getEQUIPID()==null) || 
             (this.EQUIPID!=null &&
              this.EQUIPID.equals(other.getEQUIPID()))) &&
            ((this.EQUIPNAME==null && other.getEQUIPNAME()==null) || 
             (this.EQUIPNAME!=null &&
              this.EQUIPNAME.equals(other.getEQUIPNAME())));
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
        if (getNUMBER_OF_SERVICES() != null) {
            _hashCode += getNUMBER_OF_SERVICES().hashCode();
        }
        if (getEQUIPID() != null) {
            _hashCode += getEQUIPID().hashCode();
        }
        if (getEQUIPNAME() != null) {
            _hashCode += getEQUIPNAME().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
