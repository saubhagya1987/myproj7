/**
 * ROWSET.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class ROWSET  implements java.io.Serializable {
    private com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROW ROW;

    public ROWSET() {
    }

    public ROWSET(
           com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROW ROW) {
           this.ROW = ROW;
    }


    /**
     * Gets the ROW value for this ROWSET.
     * 
     * @return ROW
     */
    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROW getROW() {
        return ROW;
    }


    /**
     * Sets the ROW value for this ROWSET.
     * 
     * @param ROW
     */
    public void setROW(com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROW ROW) {
        this.ROW = ROW;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ROWSET)) return false;
        ROWSET other = (ROWSET) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ROW==null && other.getROW()==null) || 
             (this.ROW!=null &&
              this.ROW.equals(other.getROW())));
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
        if (getROW() != null) {
            _hashCode += getROW().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROWSET.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROWSET"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROW");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROW"));
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
