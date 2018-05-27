/**
 * MSISDNSAFECUSTODY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class MSISDNSAFECUSTODY  implements java.io.Serializable {
    private java.lang.String pSubNo;

    private java.math.BigInteger pPeriod;

    public MSISDNSAFECUSTODY() {
    }

    public MSISDNSAFECUSTODY(
           java.lang.String pSubNo,
           java.math.BigInteger pPeriod) {
           this.pSubNo = pSubNo;
           this.pPeriod = pPeriod;
    }


    /**
     * Gets the pSubNo value for this MSISDNSAFECUSTODY.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this MSISDNSAFECUSTODY.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pPeriod value for this MSISDNSAFECUSTODY.
     * 
     * @return pPeriod
     */
    public java.math.BigInteger getPPeriod() {
        return pPeriod;
    }


    /**
     * Sets the pPeriod value for this MSISDNSAFECUSTODY.
     * 
     * @param pPeriod
     */
    public void setPPeriod(java.math.BigInteger pPeriod) {
        this.pPeriod = pPeriod;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MSISDNSAFECUSTODY)) return false;
        MSISDNSAFECUSTODY other = (MSISDNSAFECUSTODY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pSubNo==null && other.getPSubNo()==null) || 
             (this.pSubNo!=null &&
              this.pSubNo.equals(other.getPSubNo()))) &&
            ((this.pPeriod==null && other.getPPeriod()==null) || 
             (this.pPeriod!=null &&
              this.pPeriod.equals(other.getPPeriod())));
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
        if (getPSubNo() != null) {
            _hashCode += getPSubNo().hashCode();
        }
        if (getPPeriod() != null) {
            _hashCode += getPPeriod().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MSISDNSAFECUSTODY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNSAFECUSTODY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSubNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
