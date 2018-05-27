/**
 * UpdateMSISDNStatusRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class UpdateMSISDNStatusRequest  implements java.io.Serializable {
    private java.lang.String MSISDN;

    private java.lang.String status;

    private java.lang.String gaining_Operator;

    private java.lang.String donor_Operator;

    public UpdateMSISDNStatusRequest() {
    }

    public UpdateMSISDNStatusRequest(
           java.lang.String MSISDN,
           java.lang.String status,
           java.lang.String gaining_Operator,
           java.lang.String donor_Operator) {
           this.MSISDN = MSISDN;
           this.status = status;
           this.gaining_Operator = gaining_Operator;
           this.donor_Operator = donor_Operator;
    }


    /**
     * Gets the MSISDN value for this UpdateMSISDNStatusRequest.
     * 
     * @return MSISDN
     */
    public java.lang.String getMSISDN() {
        return MSISDN;
    }


    /**
     * Sets the MSISDN value for this UpdateMSISDNStatusRequest.
     * 
     * @param MSISDN
     */
    public void setMSISDN(java.lang.String MSISDN) {
        this.MSISDN = MSISDN;
    }


    /**
     * Gets the status value for this UpdateMSISDNStatusRequest.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this UpdateMSISDNStatusRequest.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the gaining_Operator value for this UpdateMSISDNStatusRequest.
     * 
     * @return gaining_Operator
     */
    public java.lang.String getGaining_Operator() {
        return gaining_Operator;
    }


    /**
     * Sets the gaining_Operator value for this UpdateMSISDNStatusRequest.
     * 
     * @param gaining_Operator
     */
    public void setGaining_Operator(java.lang.String gaining_Operator) {
        this.gaining_Operator = gaining_Operator;
    }


    /**
     * Gets the donor_Operator value for this UpdateMSISDNStatusRequest.
     * 
     * @return donor_Operator
     */
    public java.lang.String getDonor_Operator() {
        return donor_Operator;
    }


    /**
     * Sets the donor_Operator value for this UpdateMSISDNStatusRequest.
     * 
     * @param donor_Operator
     */
    public void setDonor_Operator(java.lang.String donor_Operator) {
        this.donor_Operator = donor_Operator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateMSISDNStatusRequest)) return false;
        UpdateMSISDNStatusRequest other = (UpdateMSISDNStatusRequest) obj;
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
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.gaining_Operator==null && other.getGaining_Operator()==null) || 
             (this.gaining_Operator!=null &&
              this.gaining_Operator.equals(other.getGaining_Operator()))) &&
            ((this.donor_Operator==null && other.getDonor_Operator()==null) || 
             (this.donor_Operator!=null &&
              this.donor_Operator.equals(other.getDonor_Operator())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getGaining_Operator() != null) {
            _hashCode += getGaining_Operator().hashCode();
        }
        if (getDonor_Operator() != null) {
            _hashCode += getDonor_Operator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateMSISDNStatusRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "updateMSISDNStatusRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSISDN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "MSISDN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gaining_Operator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Gaining_Operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("donor_Operator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Donor_Operator"));
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
