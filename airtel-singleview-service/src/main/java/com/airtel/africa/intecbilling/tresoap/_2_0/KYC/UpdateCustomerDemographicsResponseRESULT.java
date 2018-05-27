/**
 * UpdateCustomerDemographicsResponseRESULT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class UpdateCustomerDemographicsResponseRESULT  implements java.io.Serializable {
    private java.math.BigInteger RETURN_CODE;

    private java.lang.String MESSAGE;

    public UpdateCustomerDemographicsResponseRESULT() {
    }

    public UpdateCustomerDemographicsResponseRESULT(
           java.math.BigInteger RETURN_CODE,
           java.lang.String MESSAGE) {
           this.RETURN_CODE = RETURN_CODE;
           this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the RETURN_CODE value for this UpdateCustomerDemographicsResponseRESULT.
     * 
     * @return RETURN_CODE
     */
    public java.math.BigInteger getRETURN_CODE() {
        return RETURN_CODE;
    }


    /**
     * Sets the RETURN_CODE value for this UpdateCustomerDemographicsResponseRESULT.
     * 
     * @param RETURN_CODE
     */
    public void setRETURN_CODE(java.math.BigInteger RETURN_CODE) {
        this.RETURN_CODE = RETURN_CODE;
    }


    /**
     * Gets the MESSAGE value for this UpdateCustomerDemographicsResponseRESULT.
     * 
     * @return MESSAGE
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this UpdateCustomerDemographicsResponseRESULT.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomerDemographicsResponseRESULT)) return false;
        UpdateCustomerDemographicsResponseRESULT other = (UpdateCustomerDemographicsResponseRESULT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
        if (getRETURN_CODE() != null) {
            _hashCode += getRETURN_CODE().hashCode();
        }
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateCustomerDemographicsResponseRESULT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "updateCustomerDemographicsResponse.RESULT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETURN_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "RETURN_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "MESSAGE"));
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
