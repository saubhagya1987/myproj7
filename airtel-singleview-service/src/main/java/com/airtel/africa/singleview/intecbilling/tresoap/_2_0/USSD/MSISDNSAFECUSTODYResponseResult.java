/**
 * MSISDNSAFECUSTODYResponseResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class MSISDNSAFECUSTODYResponseResult  implements java.io.Serializable {
    private long RETURN_CODE;

    private java.lang.String MESSAGE;

    private java.lang.String serviceRequestNumber;

    public MSISDNSAFECUSTODYResponseResult() {
    }

    public MSISDNSAFECUSTODYResponseResult(
           long RETURN_CODE,
           java.lang.String MESSAGE,
           java.lang.String serviceRequestNumber) {
           this.RETURN_CODE = RETURN_CODE;
           this.MESSAGE = MESSAGE;
           this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the RETURN_CODE value for this MSISDNSAFECUSTODYResponseResult.
     * 
     * @return RETURN_CODE
     */
    public long getRETURN_CODE() {
        return RETURN_CODE;
    }


    /**
     * Sets the RETURN_CODE value for this MSISDNSAFECUSTODYResponseResult.
     * 
     * @param RETURN_CODE
     */
    public void setRETURN_CODE(long RETURN_CODE) {
        this.RETURN_CODE = RETURN_CODE;
    }


    /**
     * Gets the MESSAGE value for this MSISDNSAFECUSTODYResponseResult.
     * 
     * @return MESSAGE
     */
    public java.lang.String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this MSISDNSAFECUSTODYResponseResult.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(java.lang.String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the serviceRequestNumber value for this MSISDNSAFECUSTODYResponseResult.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this MSISDNSAFECUSTODYResponseResult.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MSISDNSAFECUSTODYResponseResult)) return false;
        MSISDNSAFECUSTODYResponseResult other = (MSISDNSAFECUSTODYResponseResult) obj;
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
            ((this.serviceRequestNumber==null && other.getServiceRequestNumber()==null) || 
             (this.serviceRequestNumber!=null &&
              this.serviceRequestNumber.equals(other.getServiceRequestNumber())));
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
        if (getServiceRequestNumber() != null) {
            _hashCode += getServiceRequestNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MSISDNSAFECUSTODYResponseResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDNSAFECUSTODYResponseResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETURN_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "RETURN_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ServiceRequestNumber"));
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
