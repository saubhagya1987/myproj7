/**
 * BarOnSubscriberRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class BarOnSubscriberRequest  implements java.io.Serializable {
    private java.lang.String pArea;

    private java.lang.String pSubscrtype;

    private java.lang.String pSubNo;

    private java.lang.String pUsername;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices;

    private java.lang.String pSubsidyFlag;

    private java.lang.String pHlrProfile;

    private java.lang.String pSndcmd;

    private java.lang.String pReasonCode;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditonalparams;

    public BarOnSubscriberRequest() {
    }

    public BarOnSubscriberRequest(
           java.lang.String pArea,
           java.lang.String pSubscrtype,
           java.lang.String pSubNo,
           java.lang.String pUsername,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices,
           java.lang.String pSubsidyFlag,
           java.lang.String pHlrProfile,
           java.lang.String pSndcmd,
           java.lang.String pReasonCode,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditonalparams) {
           this.pArea = pArea;
           this.pSubscrtype = pSubscrtype;
           this.pSubNo = pSubNo;
           this.pUsername = pUsername;
           this.pServices = pServices;
           this.pSubsidyFlag = pSubsidyFlag;
           this.pHlrProfile = pHlrProfile;
           this.pSndcmd = pSndcmd;
           this.pReasonCode = pReasonCode;
           this.pAdditonalparams = pAdditonalparams;
    }


    /**
     * Gets the pArea value for this BarOnSubscriberRequest.
     * 
     * @return pArea
     */
    public java.lang.String getPArea() {
        return pArea;
    }


    /**
     * Sets the pArea value for this BarOnSubscriberRequest.
     * 
     * @param pArea
     */
    public void setPArea(java.lang.String pArea) {
        this.pArea = pArea;
    }


    /**
     * Gets the pSubscrtype value for this BarOnSubscriberRequest.
     * 
     * @return pSubscrtype
     */
    public java.lang.String getPSubscrtype() {
        return pSubscrtype;
    }


    /**
     * Sets the pSubscrtype value for this BarOnSubscriberRequest.
     * 
     * @param pSubscrtype
     */
    public void setPSubscrtype(java.lang.String pSubscrtype) {
        this.pSubscrtype = pSubscrtype;
    }


    /**
     * Gets the pSubNo value for this BarOnSubscriberRequest.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this BarOnSubscriberRequest.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pUsername value for this BarOnSubscriberRequest.
     * 
     * @return pUsername
     */
    public java.lang.String getPUsername() {
        return pUsername;
    }


    /**
     * Sets the pUsername value for this BarOnSubscriberRequest.
     * 
     * @param pUsername
     */
    public void setPUsername(java.lang.String pUsername) {
        this.pUsername = pUsername;
    }


    /**
     * Gets the pServices value for this BarOnSubscriberRequest.
     * 
     * @return pServices
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices getPServices() {
        return pServices;
    }


    /**
     * Sets the pServices value for this BarOnSubscriberRequest.
     * 
     * @param pServices
     */
    public void setPServices(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices) {
        this.pServices = pServices;
    }


    /**
     * Gets the pSubsidyFlag value for this BarOnSubscriberRequest.
     * 
     * @return pSubsidyFlag
     */
    public java.lang.String getPSubsidyFlag() {
        return pSubsidyFlag;
    }


    /**
     * Sets the pSubsidyFlag value for this BarOnSubscriberRequest.
     * 
     * @param pSubsidyFlag
     */
    public void setPSubsidyFlag(java.lang.String pSubsidyFlag) {
        this.pSubsidyFlag = pSubsidyFlag;
    }


    /**
     * Gets the pHlrProfile value for this BarOnSubscriberRequest.
     * 
     * @return pHlrProfile
     */
    public java.lang.String getPHlrProfile() {
        return pHlrProfile;
    }


    /**
     * Sets the pHlrProfile value for this BarOnSubscriberRequest.
     * 
     * @param pHlrProfile
     */
    public void setPHlrProfile(java.lang.String pHlrProfile) {
        this.pHlrProfile = pHlrProfile;
    }


    /**
     * Gets the pSndcmd value for this BarOnSubscriberRequest.
     * 
     * @return pSndcmd
     */
    public java.lang.String getPSndcmd() {
        return pSndcmd;
    }


    /**
     * Sets the pSndcmd value for this BarOnSubscriberRequest.
     * 
     * @param pSndcmd
     */
    public void setPSndcmd(java.lang.String pSndcmd) {
        this.pSndcmd = pSndcmd;
    }


    /**
     * Gets the pReasonCode value for this BarOnSubscriberRequest.
     * 
     * @return pReasonCode
     */
    public java.lang.String getPReasonCode() {
        return pReasonCode;
    }


    /**
     * Sets the pReasonCode value for this BarOnSubscriberRequest.
     * 
     * @param pReasonCode
     */
    public void setPReasonCode(java.lang.String pReasonCode) {
        this.pReasonCode = pReasonCode;
    }


    /**
     * Gets the pAdditonalparams value for this BarOnSubscriberRequest.
     * 
     * @return pAdditonalparams
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] getPAdditonalparams() {
        return pAdditonalparams;
    }


    /**
     * Sets the pAdditonalparams value for this BarOnSubscriberRequest.
     * 
     * @param pAdditonalparams
     */
    public void setPAdditonalparams(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditonalparams) {
        this.pAdditonalparams = pAdditonalparams;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BarOnSubscriberRequest)) return false;
        BarOnSubscriberRequest other = (BarOnSubscriberRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pArea==null && other.getPArea()==null) || 
             (this.pArea!=null &&
              this.pArea.equals(other.getPArea()))) &&
            ((this.pSubscrtype==null && other.getPSubscrtype()==null) || 
             (this.pSubscrtype!=null &&
              this.pSubscrtype.equals(other.getPSubscrtype()))) &&
            ((this.pSubNo==null && other.getPSubNo()==null) || 
             (this.pSubNo!=null &&
              this.pSubNo.equals(other.getPSubNo()))) &&
            ((this.pUsername==null && other.getPUsername()==null) || 
             (this.pUsername!=null &&
              this.pUsername.equals(other.getPUsername()))) &&
            ((this.pServices==null && other.getPServices()==null) || 
             (this.pServices!=null &&
              this.pServices.equals(other.getPServices()))) &&
            ((this.pSubsidyFlag==null && other.getPSubsidyFlag()==null) || 
             (this.pSubsidyFlag!=null &&
              this.pSubsidyFlag.equals(other.getPSubsidyFlag()))) &&
            ((this.pHlrProfile==null && other.getPHlrProfile()==null) || 
             (this.pHlrProfile!=null &&
              this.pHlrProfile.equals(other.getPHlrProfile()))) &&
            ((this.pSndcmd==null && other.getPSndcmd()==null) || 
             (this.pSndcmd!=null &&
              this.pSndcmd.equals(other.getPSndcmd()))) &&
            ((this.pReasonCode==null && other.getPReasonCode()==null) || 
             (this.pReasonCode!=null &&
              this.pReasonCode.equals(other.getPReasonCode()))) &&
            ((this.pAdditonalparams==null && other.getPAdditonalparams()==null) || 
             (this.pAdditonalparams!=null &&
              java.util.Arrays.equals(this.pAdditonalparams, other.getPAdditonalparams())));
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
        if (getPArea() != null) {
            _hashCode += getPArea().hashCode();
        }
        if (getPSubscrtype() != null) {
            _hashCode += getPSubscrtype().hashCode();
        }
        if (getPSubNo() != null) {
            _hashCode += getPSubNo().hashCode();
        }
        if (getPUsername() != null) {
            _hashCode += getPUsername().hashCode();
        }
        if (getPServices() != null) {
            _hashCode += getPServices().hashCode();
        }
        if (getPSubsidyFlag() != null) {
            _hashCode += getPSubsidyFlag().hashCode();
        }
        if (getPHlrProfile() != null) {
            _hashCode += getPHlrProfile().hashCode();
        }
        if (getPSndcmd() != null) {
            _hashCode += getPSndcmd().hashCode();
        }
        if (getPReasonCode() != null) {
            _hashCode += getPReasonCode().hashCode();
        }
        if (getPAdditonalparams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPAdditonalparams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPAdditonalparams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BarOnSubscriberRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "barOnSubscriberRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubscrtype");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSubscrtype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSubNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUsername");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pUsername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PServices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubsidyFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSubsidyFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PHlrProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pHlrProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSndcmd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSndcmd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PReasonCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pReasonCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAdditonalparams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pAdditonalparams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList"));
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
