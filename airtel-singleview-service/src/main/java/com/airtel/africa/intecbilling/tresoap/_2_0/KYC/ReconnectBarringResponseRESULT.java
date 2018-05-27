/**
 * ReconnectBarringResponseRESULT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class ReconnectBarringResponseRESULT  implements java.io.Serializable {
    private long RETURN_CODE;

    private java.lang.String MESSAGE;

    private java.lang.String SERVICE_REQUEST_NUMBER;

    public ReconnectBarringResponseRESULT() {
    }

    public ReconnectBarringResponseRESULT(
           long RETURN_CODE,
           java.lang.String MESSAGE,
           java.lang.String SERVICE_REQUEST_NUMBER) {
           this.RETURN_CODE = RETURN_CODE;
           this.MESSAGE = MESSAGE;
           this.SERVICE_REQUEST_NUMBER = SERVICE_REQUEST_NUMBER;
    }


    /**
     * Gets the RETURN_CODE value for this ReconnectBarringResponseRESULT.
     * 
     * @return RETURN_CODE
     */
    public long getRETURN_CODE() {
        return RETURN_CODE;
    }


    /**
     * Sets the RETURN_CODE value for this ReconnectBarringResponseRESULT.
     * 
     * @param RETURN_CODE
     */
    public void setRETURN_CODE(long RETURN_CODE) {
        this.RETURN_CODE = RETURN_CODE;
    }


    /**
     * Gets the MESSAGE value for this ReconnectBarringResponseRESULT.
     * 
     * @return MESSAGE
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this ReconnectBarringResponseRESULT.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the SERVICE_REQUEST_NUMBER value for this ReconnectBarringResponseRESULT.
     * 
     * @return SERVICE_REQUEST_NUMBER
     */
    public java.lang.String getSERVICE_REQUEST_NUMBER() {
        return SERVICE_REQUEST_NUMBER;
    }


    /**
     * Sets the SERVICE_REQUEST_NUMBER value for this ReconnectBarringResponseRESULT.
     * 
     * @param SERVICE_REQUEST_NUMBER
     */
    public void setSERVICE_REQUEST_NUMBER(java.lang.String SERVICE_REQUEST_NUMBER) {
        this.SERVICE_REQUEST_NUMBER = SERVICE_REQUEST_NUMBER;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReconnectBarringResponseRESULT)) return false;
        ReconnectBarringResponseRESULT other = (ReconnectBarringResponseRESULT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.RETURN_CODE == other.getRETURN_CODE() &&
            ((this.MESSAGE==null && other.getMESSAGE()==null) || 
             (this.MESSAGE!=null &&
              this.MESSAGE.equals(other.getMESSAGE()))) &&
            ((this.SERVICE_REQUEST_NUMBER==null && other.getSERVICE_REQUEST_NUMBER()==null) || 
             (this.SERVICE_REQUEST_NUMBER!=null &&
              this.SERVICE_REQUEST_NUMBER.equals(other.getSERVICE_REQUEST_NUMBER())));
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
        _hashCode += new Long(getRETURN_CODE()).hashCode();
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        if (getSERVICE_REQUEST_NUMBER() != null) {
            _hashCode += getSERVICE_REQUEST_NUMBER().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReconnectBarringResponseRESULT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "reconnectBarringResponse.RESULT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETURN_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "RETURN_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVICE_REQUEST_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "SERVICE_REQUEST_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
