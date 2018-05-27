/**
 * FetchMSISDNStatusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class FetchMSISDNStatusResponse  implements java.io.Serializable {
    private java.lang.String donor_Operator;

    private java.lang.String status;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult RESULT;

    public FetchMSISDNStatusResponse() {
    }

    public FetchMSISDNStatusResponse(
           java.lang.String donor_Operator,
           java.lang.String status,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult RESULT) {
           this.donor_Operator = donor_Operator;
           this.status = status;
           this.RESULT = RESULT;
    }


    /**
     * Gets the donor_Operator value for this FetchMSISDNStatusResponse.
     * 
     * @return donor_Operator
     */
    public java.lang.String getDonor_Operator() {
        return donor_Operator;
    }


    /**
     * Sets the donor_Operator value for this FetchMSISDNStatusResponse.
     * 
     * @param donor_Operator
     */
    public void setDonor_Operator(java.lang.String donor_Operator) {
        this.donor_Operator = donor_Operator;
    }


    /**
     * Gets the status value for this FetchMSISDNStatusResponse.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this FetchMSISDNStatusResponse.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the RESULT value for this FetchMSISDNStatusResponse.
     * 
     * @return RESULT
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult getRESULT() {
        return RESULT;
    }


    /**
     * Sets the RESULT value for this FetchMSISDNStatusResponse.
     * 
     * @param RESULT
     */
    public void setRESULT(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.MSISDNResult RESULT) {
        this.RESULT = RESULT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetchMSISDNStatusResponse)) return false;
        FetchMSISDNStatusResponse other = (FetchMSISDNStatusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.donor_Operator==null && other.getDonor_Operator()==null) || 
             (this.donor_Operator!=null &&
              this.donor_Operator.equals(other.getDonor_Operator()))) &&
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
        if (getDonor_Operator() != null) {
            _hashCode += getDonor_Operator().hashCode();
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
        new org.apache.axis.description.TypeDesc(FetchMSISDNStatusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchMSISDNStatusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("donor_Operator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Donor_Operator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
