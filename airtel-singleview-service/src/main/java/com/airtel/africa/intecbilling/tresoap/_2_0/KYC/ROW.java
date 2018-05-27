/**
 * ROW.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

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

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROW.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROW"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMBER_OF_SERVICES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "NUMBER_OF_SERVICES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EQUIPID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "EQUIPID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EQUIPNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "EQUIPNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
