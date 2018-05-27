/**
 * CHANGEMSISDN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class CHANGEMSISDN  implements java.io.Serializable {
    private java.lang.String pArea;

    private java.lang.String pSubscrType;

    private java.lang.String pSubNo;

    private java.lang.String pUsername;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices;

    private java.lang.String pNewsubno;

    private java.lang.String pHlrProfile;

    private java.lang.String PSndcmd;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditionalparams;

    public CHANGEMSISDN() {
    }

    public CHANGEMSISDN(
           java.lang.String pArea,
           java.lang.String pSubscrType,
           java.lang.String pSubNo,
           java.lang.String pUsername,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices,
           java.lang.String pNewsubno,
           java.lang.String pHlrProfile,
           java.lang.String PSndcmd,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditionalparams) {
           this.pArea = pArea;
           this.pSubscrType = pSubscrType;
           this.pSubNo = pSubNo;
           this.pUsername = pUsername;
           this.pServices = pServices;
           this.pNewsubno = pNewsubno;
           this.pHlrProfile = pHlrProfile;
           this.PSndcmd = PSndcmd;
           this.pAdditionalparams = pAdditionalparams;
    }


    /**
     * Gets the pArea value for this CHANGEMSISDN.
     * 
     * @return pArea
     */
    public java.lang.String getPArea() {
        return pArea;
    }


    /**
     * Sets the pArea value for this CHANGEMSISDN.
     * 
     * @param pArea
     */
    public void setPArea(java.lang.String pArea) {
        this.pArea = pArea;
    }


    /**
     * Gets the pSubscrType value for this CHANGEMSISDN.
     * 
     * @return pSubscrType
     */
    public java.lang.String getPSubscrType() {
        return pSubscrType;
    }


    /**
     * Sets the pSubscrType value for this CHANGEMSISDN.
     * 
     * @param pSubscrType
     */
    public void setPSubscrType(java.lang.String pSubscrType) {
        this.pSubscrType = pSubscrType;
    }


    /**
     * Gets the pSubNo value for this CHANGEMSISDN.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this CHANGEMSISDN.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pUsername value for this CHANGEMSISDN.
     * 
     * @return pUsername
     */
    public java.lang.String getPUsername() {
        return pUsername;
    }


    /**
     * Sets the pUsername value for this CHANGEMSISDN.
     * 
     * @param pUsername
     */
    public void setPUsername(java.lang.String pUsername) {
        this.pUsername = pUsername;
    }


    /**
     * Gets the pServices value for this CHANGEMSISDN.
     * 
     * @return pServices
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices getPServices() {
        return pServices;
    }


    /**
     * Sets the pServices value for this CHANGEMSISDN.
     * 
     * @param pServices
     */
    public void setPServices(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices) {
        this.pServices = pServices;
    }


    /**
     * Gets the pNewsubno value for this CHANGEMSISDN.
     * 
     * @return pNewsubno
     */
    public java.lang.String getPNewsubno() {
        return pNewsubno;
    }


    /**
     * Sets the pNewsubno value for this CHANGEMSISDN.
     * 
     * @param pNewsubno
     */
    public void setPNewsubno(java.lang.String pNewsubno) {
        this.pNewsubno = pNewsubno;
    }


    /**
     * Gets the pHlrProfile value for this CHANGEMSISDN.
     * 
     * @return pHlrProfile
     */
    public java.lang.String getPHlrProfile() {
        return pHlrProfile;
    }


    /**
     * Sets the pHlrProfile value for this CHANGEMSISDN.
     * 
     * @param pHlrProfile
     */
    public void setPHlrProfile(java.lang.String pHlrProfile) {
        this.pHlrProfile = pHlrProfile;
    }


    /**
     * Gets the PSndcmd value for this CHANGEMSISDN.
     * 
     * @return PSndcmd
     */
    public java.lang.String getPSndcmd() {
        return PSndcmd;
    }


    /**
     * Sets the PSndcmd value for this CHANGEMSISDN.
     * 
     * @param PSndcmd
     */
    public void setPSndcmd(java.lang.String PSndcmd) {
        this.PSndcmd = PSndcmd;
    }


    /**
     * Gets the pAdditionalparams value for this CHANGEMSISDN.
     * 
     * @return pAdditionalparams
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] getPAdditionalparams() {
        return pAdditionalparams;
    }


    /**
     * Sets the pAdditionalparams value for this CHANGEMSISDN.
     * 
     * @param pAdditionalparams
     */
    public void setPAdditionalparams(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditionalparams) {
        this.pAdditionalparams = pAdditionalparams;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CHANGEMSISDN)) return false;
        CHANGEMSISDN other = (CHANGEMSISDN) obj;
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
            ((this.pNewsubno==null && other.getPNewsubno()==null) || 
             (this.pNewsubno!=null &&
              this.pNewsubno.equals(other.getPNewsubno()))) &&
            ((this.pHlrProfile==null && other.getPHlrProfile()==null) || 
             (this.pHlrProfile!=null &&
              this.pHlrProfile.equals(other.getPHlrProfile()))) &&
            ((this.PSndcmd==null && other.getPSndcmd()==null) || 
             (this.PSndcmd!=null &&
              this.PSndcmd.equals(other.getPSndcmd()))) &&
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
        if (getPNewsubno() != null) {
            _hashCode += getPNewsubno().hashCode();
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
        new org.apache.axis.description.TypeDesc(CHANGEMSISDN.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CHANGEMSISDN"));
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
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PServices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PNewsubno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pNewsubno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PSndcmd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAdditionalparams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pAdditionalparams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList"));
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
