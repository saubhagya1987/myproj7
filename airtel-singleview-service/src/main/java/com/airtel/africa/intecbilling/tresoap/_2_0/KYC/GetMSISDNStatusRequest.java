/**
 * GetMSISDNStatusRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class GetMSISDNStatusRequest  implements java.io.Serializable {
    private java.lang.String SUBSCRIBER_NO;

    public GetMSISDNStatusRequest() {
    }

    public GetMSISDNStatusRequest(
           java.lang.String SUBSCRIBER_NO) {
           this.SUBSCRIBER_NO = SUBSCRIBER_NO;
    }


    /**
     * Gets the SUBSCRIBER_NO value for this GetMSISDNStatusRequest.
     * 
     * @return SUBSCRIBER_NO
     */
    public java.lang.String getSUBSCRIBER_NO() {
        return SUBSCRIBER_NO;
    }


    /**
     * Sets the SUBSCRIBER_NO value for this GetMSISDNStatusRequest.
     * 
     * @param SUBSCRIBER_NO
     */
    public void setSUBSCRIBER_NO(java.lang.String SUBSCRIBER_NO) {
        this.SUBSCRIBER_NO = SUBSCRIBER_NO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMSISDNStatusRequest)) return false;
        GetMSISDNStatusRequest other = (GetMSISDNStatusRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SUBSCRIBER_NO==null && other.getSUBSCRIBER_NO()==null) || 
             (this.SUBSCRIBER_NO!=null &&
              this.SUBSCRIBER_NO.equals(other.getSUBSCRIBER_NO())));
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
        if (getSUBSCRIBER_NO() != null) {
            _hashCode += getSUBSCRIBER_NO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMSISDNStatusRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getMSISDNStatusRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUBSCRIBER_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "SUBSCRIBER_NO"));
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
