/**
 * GetSIMNumberResponseRESULT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class GetSIMNumberResponseRESULT  implements java.io.Serializable {
    private java.lang.String SIM_NO;

    private java.math.BigInteger RETURN_CODE;

    private java.lang.String MESSAGE;

    public GetSIMNumberResponseRESULT() {
    }

    public GetSIMNumberResponseRESULT(
           java.lang.String SIM_NO,
           java.math.BigInteger RETURN_CODE,
           java.lang.String MESSAGE) {
           this.SIM_NO = SIM_NO;
           this.RETURN_CODE = RETURN_CODE;
           this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the SIM_NO value for this GetSIMNumberResponseRESULT.
     * 
     * @return SIM_NO
     */
    public java.lang.String getSIM_NO() {
        return SIM_NO;
    }


    /**
     * Sets the SIM_NO value for this GetSIMNumberResponseRESULT.
     * 
     * @param SIM_NO
     */
    public void setSIM_NO(java.lang.String SIM_NO) {
        this.SIM_NO = SIM_NO;
    }


    /**
     * Gets the RETURN_CODE value for this GetSIMNumberResponseRESULT.
     * 
     * @return RETURN_CODE
     */
    public java.math.BigInteger getRETURN_CODE() {
        return RETURN_CODE;
    }


    /**
     * Sets the RETURN_CODE value for this GetSIMNumberResponseRESULT.
     * 
     * @param RETURN_CODE
     */
    public void setRETURN_CODE(java.math.BigInteger RETURN_CODE) {
        this.RETURN_CODE = RETURN_CODE;
    }


    /**
     * Gets the MESSAGE value for this GetSIMNumberResponseRESULT.
     * 
     * @return MESSAGE
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this GetSIMNumberResponseRESULT.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetSIMNumberResponseRESULT)) return false;
        GetSIMNumberResponseRESULT other = (GetSIMNumberResponseRESULT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SIM_NO==null && other.getSIM_NO()==null) || 
             (this.SIM_NO!=null &&
              this.SIM_NO.equals(other.getSIM_NO()))) &&
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
        if (getSIM_NO() != null) {
            _hashCode += getSIM_NO().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(GetSIMNumberResponseRESULT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getSIMNumberResponse.RESULT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SIM_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "SIM_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
