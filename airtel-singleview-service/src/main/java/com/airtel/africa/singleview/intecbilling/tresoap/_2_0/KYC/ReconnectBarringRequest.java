/**
 * ReconnectBarringRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class ReconnectBarringRequest  implements java.io.Serializable {
	
	
    private java.lang.String pUsername;

    private java.lang.String pSubno;

    private java.lang.String pReasoncode;

    private java.lang.String pSndcmd;

    public ReconnectBarringRequest() {
    }

    public ReconnectBarringRequest(
           java.lang.String pUsername,
           java.lang.String pSubno,
           java.lang.String pReasoncode,
           java.lang.String pSndcmd) {
           this.pUsername = pUsername;
           this.pSubno = pSubno;
           this.pReasoncode = pReasoncode;
           this.pSndcmd = pSndcmd;
    }


    /**
     * Gets the pUsername value for this ReconnectBarringRequest.
     * 
     * @return pUsername
     */
    public java.lang.String getPUsername() {
        return pUsername;
    }


    /**
     * Sets the pUsername value for this ReconnectBarringRequest.
     * 
     * @param pUsername
     */
    public void setPUsername(java.lang.String pUsername) {
        this.pUsername = pUsername;
    }


    /**
     * Gets the pSubno value for this ReconnectBarringRequest.
     * 
     * @return pSubno
     */
    public java.lang.String getPSubno() {
        return pSubno;
    }


    /**
     * Sets the pSubno value for this ReconnectBarringRequest.
     * 
     * @param pSubno
     */
    public void setPSubno(java.lang.String pSubno) {
        this.pSubno = pSubno;
    }


    /**
     * Gets the pReasoncode value for this ReconnectBarringRequest.
     * 
     * @return pReasoncode
     */
    public java.lang.String getPReasoncode() {
        return pReasoncode;
    }


    /**
     * Sets the pReasoncode value for this ReconnectBarringRequest.
     * 
     * @param pReasoncode
     */
    public void setPReasoncode(java.lang.String pReasoncode) {
        this.pReasoncode = pReasoncode;
    }


    /**
     * Gets the pSndcmd value for this ReconnectBarringRequest.
     * 
     * @return pSndcmd
     */
    public java.lang.String getPSndcmd() {
        return pSndcmd;
    }


    /**
     * Sets the pSndcmd value for this ReconnectBarringRequest.
     * 
     * @param pSndcmd
     */
    public void setPSndcmd(java.lang.String pSndcmd) {
        this.pSndcmd = pSndcmd;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReconnectBarringRequest)) return false;
        ReconnectBarringRequest other = (ReconnectBarringRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pUsername==null && other.getPUsername()==null) || 
             (this.pUsername!=null &&
              this.pUsername.equals(other.getPUsername()))) &&
            ((this.pSubno==null && other.getPSubno()==null) || 
             (this.pSubno!=null &&
              this.pSubno.equals(other.getPSubno()))) &&
            ((this.pReasoncode==null && other.getPReasoncode()==null) || 
             (this.pReasoncode!=null &&
              this.pReasoncode.equals(other.getPReasoncode()))) &&
            ((this.pSndcmd==null && other.getPSndcmd()==null) || 
             (this.pSndcmd!=null &&
              this.pSndcmd.equals(other.getPSndcmd())));
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
        if (getPUsername() != null) {
            _hashCode += getPUsername().hashCode();
        }
        if (getPSubno() != null) {
            _hashCode += getPSubno().hashCode();
        }
        if (getPReasoncode() != null) {
            _hashCode += getPReasoncode().hashCode();
        }
        if (getPSndcmd() != null) {
            _hashCode += getPSndcmd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
