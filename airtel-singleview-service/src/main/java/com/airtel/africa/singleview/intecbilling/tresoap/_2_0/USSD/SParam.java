/**
 * SParam.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class SParam  implements java.io.Serializable {
    private java.lang.String paramName;

    private java.lang.String paramValue;

    public SParam() {
    }

    public SParam(
           java.lang.String paramName,
           java.lang.String paramValue) {
           this.paramName = paramName;
           this.paramValue = paramValue;
    }


    /**
     * Gets the paramName value for this SParam.
     * 
     * @return paramName
     */
    public java.lang.String getParamName() {
        return paramName;
    }


    /**
     * Sets the paramName value for this SParam.
     * 
     * @param paramName
     */
    public void setParamName(java.lang.String paramName) {
        this.paramName = paramName;
    }


    /**
     * Gets the paramValue value for this SParam.
     * 
     * @return paramValue
     */
    public java.lang.String getParamValue() {
        return paramValue;
    }


    /**
     * Sets the paramValue value for this SParam.
     * 
     * @param paramValue
     */
    public void setParamValue(java.lang.String paramValue) {
        this.paramValue = paramValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SParam)) return false;
        SParam other = (SParam) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paramName==null && other.getParamName()==null) || 
             (this.paramName!=null &&
              this.paramName.equals(other.getParamName()))) &&
            ((this.paramValue==null && other.getParamValue()==null) || 
             (this.paramValue!=null &&
              this.paramValue.equals(other.getParamValue())));
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
        if (getParamName() != null) {
            _hashCode += getParamName().hashCode();
        }
        if (getParamValue() != null) {
            _hashCode += getParamValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SParam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ParamName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ParamValue"));
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
