/**
 * CHANGESERVICE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class CHANGESERVICE  implements java.io.Serializable {
    private java.lang.String pArea;

    private java.lang.String pSubscrType;

    private java.lang.String pSubNo;

    private java.lang.String pUsername;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PCSServices pServices;

    private java.lang.String pSubsidyFlag;

    private java.lang.String pHlrProfile;

    private java.lang.String pSndcmd;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParamList[] pAdditionalparams;

    public CHANGESERVICE() {
    }

    public CHANGESERVICE(
           java.lang.String pArea,
           java.lang.String pSubscrType,
           java.lang.String pSubNo,
           java.lang.String pUsername,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PCSServices pServices,
           java.lang.String pSubsidyFlag,
           java.lang.String pHlrProfile,
           java.lang.String pSndcmd,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParamList[] pAdditionalparams) {
           this.pArea = pArea;
           this.pSubscrType = pSubscrType;
           this.pSubNo = pSubNo;
           this.pUsername = pUsername;
           this.pServices = pServices;
           this.pSubsidyFlag = pSubsidyFlag;
           this.pHlrProfile = pHlrProfile;
           this.pSndcmd = pSndcmd;
           this.pAdditionalparams = pAdditionalparams;
    }


    /**
     * Gets the pArea value for this CHANGESERVICE.
     * 
     * @return pArea
     */
    public java.lang.String getPArea() {
        return pArea;
    }


    /**
     * Sets the pArea value for this CHANGESERVICE.
     * 
     * @param pArea
     */
    public void setPArea(java.lang.String pArea) {
        this.pArea = pArea;
    }


    /**
     * Gets the pSubscrType value for this CHANGESERVICE.
     * 
     * @return pSubscrType
     */
    public java.lang.String getPSubscrType() {
        return pSubscrType;
    }


    /**
     * Sets the pSubscrType value for this CHANGESERVICE.
     * 
     * @param pSubscrType
     */
    public void setPSubscrType(java.lang.String pSubscrType) {
        this.pSubscrType = pSubscrType;
    }


    /**
     * Gets the pSubNo value for this CHANGESERVICE.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this CHANGESERVICE.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pUsername value for this CHANGESERVICE.
     * 
     * @return pUsername
     */
    public java.lang.String getPUsername() {
        return pUsername;
    }


    /**
     * Sets the pUsername value for this CHANGESERVICE.
     * 
     * @param pUsername
     */
    public void setPUsername(java.lang.String pUsername) {
        this.pUsername = pUsername;
    }


    /**
     * Gets the pServices value for this CHANGESERVICE.
     * 
     * @return pServices
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PCSServices getPServices() {
        return pServices;
    }


    /**
     * Sets the pServices value for this CHANGESERVICE.
     * 
     * @param pServices
     */
    public void setPServices(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PCSServices pServices) {
        this.pServices = pServices;
    }


    /**
     * Gets the pSubsidyFlag value for this CHANGESERVICE.
     * 
     * @return pSubsidyFlag
     */
    public java.lang.String getPSubsidyFlag() {
        return pSubsidyFlag;
    }


    /**
     * Sets the pSubsidyFlag value for this CHANGESERVICE.
     * 
     * @param pSubsidyFlag
     */
    public void setPSubsidyFlag(java.lang.String pSubsidyFlag) {
        this.pSubsidyFlag = pSubsidyFlag;
    }


    /**
     * Gets the pHlrProfile value for this CHANGESERVICE.
     * 
     * @return pHlrProfile
     */
    public java.lang.String getPHlrProfile() {
        return pHlrProfile;
    }


    /**
     * Sets the pHlrProfile value for this CHANGESERVICE.
     * 
     * @param pHlrProfile
     */
    public void setPHlrProfile(java.lang.String pHlrProfile) {
        this.pHlrProfile = pHlrProfile;
    }


    /**
     * Gets the pSndcmd value for this CHANGESERVICE.
     * 
     * @return pSndcmd
     */
    public java.lang.String getPSndcmd() {
        return pSndcmd;
    }


    /**
     * Sets the pSndcmd value for this CHANGESERVICE.
     * 
     * @param pSndcmd
     */
    public void setPSndcmd(java.lang.String pSndcmd) {
        this.pSndcmd = pSndcmd;
    }


    /**
     * Gets the pAdditionalparams value for this CHANGESERVICE.
     * 
     * @return pAdditionalparams
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParamList[] getPAdditionalparams() {
        return pAdditionalparams;
    }


    /**
     * Sets the pAdditionalparams value for this CHANGESERVICE.
     * 
     * @param pAdditionalparams
     */
    public void setPAdditionalparams(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSParamList[] pAdditionalparams) {
        this.pAdditionalparams = pAdditionalparams;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CHANGESERVICE)) return false;
        CHANGESERVICE other = (CHANGESERVICE) obj;
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
            ((this.pSubscrType==null && other.getPSubscrType()==null) || 
             (this.pSubscrType!=null &&
              this.pSubscrType.equals(other.getPSubscrType()))) &&
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
            ((this.pAdditionalparams==null && other.getPAdditionalparams()==null) || 
             (this.pAdditionalparams!=null &&
              java.util.Arrays.equals(this.pAdditionalparams, other.getPAdditionalparams())));
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
        if (getPSubscrType() != null) {
            _hashCode += getPSubscrType().hashCode();
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
        if (getPAdditionalparams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPAdditionalparams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPAdditionalparams(), i);
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
        new org.apache.axis.description.TypeDesc(CHANGESERVICE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGESERVICE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubscrType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSubscrType"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PServices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pCSServices"));
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
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSndcmd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSndcmd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAdditionalparams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pAdditionalparams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSParamList"));
        elemField.setNillable(true);
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
