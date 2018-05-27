/**
 * FetchMNPVerificationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class FetchMNPVerificationResponse  implements java.io.Serializable {
    private java.lang.String age_On_Network;

    private java.lang.String identity_Type;

    private java.lang.String identity_Number;

    private java.lang.String status;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult RESULT;

    public FetchMNPVerificationResponse() {
    }

    public FetchMNPVerificationResponse(
           java.lang.String age_On_Network,
           java.lang.String identity_Type,
           java.lang.String identity_Number,
           java.lang.String status,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult RESULT) {
           this.age_On_Network = age_On_Network;
           this.identity_Type = identity_Type;
           this.identity_Number = identity_Number;
           this.status = status;
           this.RESULT = RESULT;
    }


    /**
     * Gets the age_On_Network value for this FetchMNPVerificationResponse.
     * 
     * @return age_On_Network
     */
    public java.lang.String getAge_On_Network() {
        return age_On_Network;
    }


    /**
     * Sets the age_On_Network value for this FetchMNPVerificationResponse.
     * 
     * @param age_On_Network
     */
    public void setAge_On_Network(java.lang.String age_On_Network) {
        this.age_On_Network = age_On_Network;
    }


    /**
     * Gets the identity_Type value for this FetchMNPVerificationResponse.
     * 
     * @return identity_Type
     */
    public java.lang.String getIdentity_Type() {
        return identity_Type;
    }


    /**
     * Sets the identity_Type value for this FetchMNPVerificationResponse.
     * 
     * @param identity_Type
     */
    public void setIdentity_Type(java.lang.String identity_Type) {
        this.identity_Type = identity_Type;
    }


    /**
     * Gets the identity_Number value for this FetchMNPVerificationResponse.
     * 
     * @return identity_Number
     */
    public java.lang.String getIdentity_Number() {
        return identity_Number;
    }


    /**
     * Sets the identity_Number value for this FetchMNPVerificationResponse.
     * 
     * @param identity_Number
     */
    public void setIdentity_Number(java.lang.String identity_Number) {
        this.identity_Number = identity_Number;
    }


    /**
     * Gets the status value for this FetchMNPVerificationResponse.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this FetchMNPVerificationResponse.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the RESULT value for this FetchMNPVerificationResponse.
     * 
     * @return RESULT
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult getRESULT() {
        return RESULT;
    }


    /**
     * Sets the RESULT value for this FetchMNPVerificationResponse.
     * 
     * @param RESULT
     */
    public void setRESULT(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult RESULT) {
        this.RESULT = RESULT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetchMNPVerificationResponse)) return false;
        FetchMNPVerificationResponse other = (FetchMNPVerificationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.age_On_Network==null && other.getAge_On_Network()==null) || 
             (this.age_On_Network!=null &&
              this.age_On_Network.equals(other.getAge_On_Network()))) &&
            ((this.identity_Type==null && other.getIdentity_Type()==null) || 
             (this.identity_Type!=null &&
              this.identity_Type.equals(other.getIdentity_Type()))) &&
            ((this.identity_Number==null && other.getIdentity_Number()==null) || 
             (this.identity_Number!=null &&
              this.identity_Number.equals(other.getIdentity_Number()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.RESULT==null && other.getRESULT()==null) || 
             (this.RESULT!=null &&
              this.RESULT.equals(other.getRESULT())));
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
        if (getAge_On_Network() != null) {
            _hashCode += getAge_On_Network().hashCode();
        }
        if (getIdentity_Type() != null) {
            _hashCode += getIdentity_Type().hashCode();
        }
        if (getIdentity_Number() != null) {
            _hashCode += getIdentity_Number().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getRESULT() != null) {
            _hashCode += getRESULT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FetchMNPVerificationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchMNPVerificationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("age_On_Network");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Age_On_Network"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identity_Type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Identity_Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identity_Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Identity_Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESULT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "RESULT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNResult"));
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
