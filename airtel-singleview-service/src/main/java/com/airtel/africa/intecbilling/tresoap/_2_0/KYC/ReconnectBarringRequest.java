/**
 * ReconnectBarringRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

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

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReconnectBarringRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUsername");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pUsername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pSubno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PReasoncode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pReasoncode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSndcmd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pSndcmd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
