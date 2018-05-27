/**
 * CreateMSISDNRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class CreateMSISDNRequest  implements java.io.Serializable {
    private java.lang.String MSISDN;

    private java.lang.String MSISDN_Pool;

    private java.lang.String gaining_Operator;

    private java.lang.String donar_Operator;

    private java.lang.String vanity_Category;

    public CreateMSISDNRequest() {
    }

    public CreateMSISDNRequest(
           java.lang.String MSISDN,
           java.lang.String MSISDN_Pool,
           java.lang.String gaining_Operator,
           java.lang.String donar_Operator,
           java.lang.String vanity_Category) {
           this.MSISDN = MSISDN;
           this.MSISDN_Pool = MSISDN_Pool;
           this.gaining_Operator = gaining_Operator;
           this.donar_Operator = donar_Operator;
           this.vanity_Category = vanity_Category;
    }


    /**
     * Gets the MSISDN value for this CreateMSISDNRequest.
     * 
     * @return MSISDN
     */
    public java.lang.String getMSISDN() {
        return MSISDN;
    }


    /**
     * Sets the MSISDN value for this CreateMSISDNRequest.
     * 
     * @param MSISDN
     */
    public void setMSISDN(java.lang.String MSISDN) {
        this.MSISDN = MSISDN;
    }


    /**
     * Gets the MSISDN_Pool value for this CreateMSISDNRequest.
     * 
     * @return MSISDN_Pool
     */
    public java.lang.String getMSISDN_Pool() {
        return MSISDN_Pool;
    }


    /**
     * Sets the MSISDN_Pool value for this CreateMSISDNRequest.
     * 
     * @param MSISDN_Pool
     */
    public void setMSISDN_Pool(java.lang.String MSISDN_Pool) {
        this.MSISDN_Pool = MSISDN_Pool;
    }


    /**
     * Gets the gaining_Operator value for this CreateMSISDNRequest.
     * 
     * @return gaining_Operator
     */
    public java.lang.String getGaining_Operator() {
        return gaining_Operator;
    }


    /**
     * Sets the gaining_Operator value for this CreateMSISDNRequest.
     * 
     * @param gaining_Operator
     */
    public void setGaining_Operator(java.lang.String gaining_Operator) {
        this.gaining_Operator = gaining_Operator;
    }


    /**
     * Gets the donar_Operator value for this CreateMSISDNRequest.
     * 
     * @return donar_Operator
     */
    public java.lang.String getDonar_Operator() {
        return donar_Operator;
    }


    /**
     * Sets the donar_Operator value for this CreateMSISDNRequest.
     * 
     * @param donar_Operator
     */
    public void setDonar_Operator(java.lang.String donar_Operator) {
        this.donar_Operator = donar_Operator;
    }


    /**
     * Gets the vanity_Category value for this CreateMSISDNRequest.
     * 
     * @return vanity_Category
     */
    public java.lang.String getVanity_Category() {
        return vanity_Category;
    }


    /**
     * Sets the vanity_Category value for this CreateMSISDNRequest.
     * 
     * @param vanity_Category
     */
    public void setVanity_Category(java.lang.String vanity_Category) {
        this.vanity_Category = vanity_Category;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateMSISDNRequest)) return false;
        CreateMSISDNRequest other = (CreateMSISDNRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MSISDN==null && other.getMSISDN()==null) || 
             (this.MSISDN!=null &&
              this.MSISDN.equals(other.getMSISDN()))) &&
            ((this.MSISDN_Pool==null && other.getMSISDN_Pool()==null) || 
             (this.MSISDN_Pool!=null &&
              this.MSISDN_Pool.equals(other.getMSISDN_Pool()))) &&
            ((this.gaining_Operator==null && other.getGaining_Operator()==null) || 
             (this.gaining_Operator!=null &&
              this.gaining_Operator.equals(other.getGaining_Operator()))) &&
            ((this.donar_Operator==null && other.getDonar_Operator()==null) || 
             (this.donar_Operator!=null &&
              this.donar_Operator.equals(other.getDonar_Operator()))) &&
            ((this.vanity_Category==null && other.getVanity_Category()==null) || 
             (this.vanity_Category!=null &&
              this.vanity_Category.equals(other.getVanity_Category())));
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
        if (getMSISDN() != null) {
            _hashCode += getMSISDN().hashCode();
        }
        if (getMSISDN_Pool() != null) {
            _hashCode += getMSISDN_Pool().hashCode();
        }
        if (getGaining_Operator() != null) {
            _hashCode += getGaining_Operator().hashCode();
        }
        if (getDonar_Operator() != null) {
            _hashCode += getDonar_Operator().hashCode();
        }
        if (getVanity_Category() != null) {
            _hashCode += getVanity_Category().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateMSISDNRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "createMSISDNRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSISDN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSISDN_Pool");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDN_Pool"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gaining_Operator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Gaining_Operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("donar_Operator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Donar_Operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vanity_Category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Vanity_Category"));
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
