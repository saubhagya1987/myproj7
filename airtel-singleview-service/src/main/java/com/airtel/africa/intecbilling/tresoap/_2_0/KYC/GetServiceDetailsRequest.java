/**
 * GetServiceDetailsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class GetServiceDetailsRequest  implements java.io.Serializable {
    private java.lang.String pChannel;

    private java.lang.String pSubNo;

    private java.lang.String pArea;

    private java.lang.String pSubscrType;

    private java.lang.String pCallTime;

    public GetServiceDetailsRequest() {
    }

    public GetServiceDetailsRequest(
           java.lang.String pChannel,
           java.lang.String pSubNo,
           java.lang.String pArea,
           java.lang.String pSubscrType,
           java.lang.String pCallTime) {
           this.pChannel = pChannel;
           this.pSubNo = pSubNo;
           this.pArea = pArea;
           this.pSubscrType = pSubscrType;
           this.pCallTime = pCallTime;
    }


    /**
     * Gets the pChannel value for this GetServiceDetailsRequest.
     * 
     * @return pChannel
     */
    public java.lang.String getPChannel() {
        return pChannel;
    }


    /**
     * Sets the pChannel value for this GetServiceDetailsRequest.
     * 
     * @param pChannel
     */
    public void setPChannel(java.lang.String pChannel) {
        this.pChannel = pChannel;
    }


    /**
     * Gets the pSubNo value for this GetServiceDetailsRequest.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this GetServiceDetailsRequest.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pArea value for this GetServiceDetailsRequest.
     * 
     * @return pArea
     */
    public java.lang.String getPArea() {
        return pArea;
    }


    /**
     * Sets the pArea value for this GetServiceDetailsRequest.
     * 
     * @param pArea
     */
    public void setPArea(java.lang.String pArea) {
        this.pArea = pArea;
    }


    /**
     * Gets the pSubscrType value for this GetServiceDetailsRequest.
     * 
     * @return pSubscrType
     */
    public java.lang.String getPSubscrType() {
        return pSubscrType;
    }


    /**
     * Sets the pSubscrType value for this GetServiceDetailsRequest.
     * 
     * @param pSubscrType
     */
    public void setPSubscrType(java.lang.String pSubscrType) {
        this.pSubscrType = pSubscrType;
    }


    /**
     * Gets the pCallTime value for this GetServiceDetailsRequest.
     * 
     * @return pCallTime
     */
    public java.lang.String getPCallTime() {
        return pCallTime;
    }


    /**
     * Sets the pCallTime value for this GetServiceDetailsRequest.
     * 
     * @param pCallTime
     */
    public void setPCallTime(java.lang.String pCallTime) {
        this.pCallTime = pCallTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceDetailsRequest)) return false;
        GetServiceDetailsRequest other = (GetServiceDetailsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pChannel==null && other.getPChannel()==null) || 
             (this.pChannel!=null &&
              this.pChannel.equals(other.getPChannel()))) &&
            ((this.pSubNo==null && other.getPSubNo()==null) || 
             (this.pSubNo!=null &&
              this.pSubNo.equals(other.getPSubNo()))) &&
            ((this.pArea==null && other.getPArea()==null) || 
             (this.pArea!=null &&
              this.pArea.equals(other.getPArea()))) &&
            ((this.pSubscrType==null && other.getPSubscrType()==null) || 
             (this.pSubscrType!=null &&
              this.pSubscrType.equals(other.getPSubscrType()))) &&
            ((this.pCallTime==null && other.getPCallTime()==null) || 
             (this.pCallTime!=null &&
              this.pCallTime.equals(other.getPCallTime())));
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
        if (getPChannel() != null) {
            _hashCode += getPChannel().hashCode();
        }
        if (getPSubNo() != null) {
            _hashCode += getPSubNo().hashCode();
        }
        if (getPArea() != null) {
            _hashCode += getPArea().hashCode();
        }
        if (getPSubscrType() != null) {
            _hashCode += getPSubscrType().hashCode();
        }
        if (getPCallTime() != null) {
            _hashCode += getPCallTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceDetailsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PChannel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pChannel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pSubNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubscrType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pSubscrType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PCallTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "pCallTime"));
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
