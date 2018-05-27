/**
 * TransferOwnershipRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class TransferOwnershipRequest  implements java.io.Serializable {
    private java.lang.String pArea;

    private java.lang.String pSubscrtype;

    private java.lang.String pSubNo;

    private java.lang.String pUsername;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices;

    private java.lang.String pNewcontract;

    private java.math.BigInteger pSpecifiedamount;

    private java.lang.String pSubsidyFlag;

    private java.lang.String pHlrProfile;

    private java.lang.String pSndcmd;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditionalparams;

    public TransferOwnershipRequest() {
    }

    public TransferOwnershipRequest(
           java.lang.String pArea,
           java.lang.String pSubscrtype,
           java.lang.String pSubNo,
           java.lang.String pUsername,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices,
           java.lang.String pNewcontract,
           java.math.BigInteger pSpecifiedamount,
           java.lang.String pSubsidyFlag,
           java.lang.String pHlrProfile,
           java.lang.String pSndcmd,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditionalparams) {
           this.pArea = pArea;
           this.pSubscrtype = pSubscrtype;
           this.pSubNo = pSubNo;
           this.pUsername = pUsername;
           this.pServices = pServices;
           this.pNewcontract = pNewcontract;
           this.pSpecifiedamount = pSpecifiedamount;
           this.pSubsidyFlag = pSubsidyFlag;
           this.pHlrProfile = pHlrProfile;
           this.pSndcmd = pSndcmd;
           this.pAdditionalparams = pAdditionalparams;
    }


    /**
     * Gets the pArea value for this TransferOwnershipRequest.
     * 
     * @return pArea
     */
    public java.lang.String getPArea() {
        return pArea;
    }


    /**
     * Sets the pArea value for this TransferOwnershipRequest.
     * 
     * @param pArea
     */
    public void setPArea(java.lang.String pArea) {
        this.pArea = pArea;
    }


    /**
     * Gets the pSubscrtype value for this TransferOwnershipRequest.
     * 
     * @return pSubscrtype
     */
    public java.lang.String getPSubscrtype() {
        return pSubscrtype;
    }


    /**
     * Sets the pSubscrtype value for this TransferOwnershipRequest.
     * 
     * @param pSubscrtype
     */
    public void setPSubscrtype(java.lang.String pSubscrtype) {
        this.pSubscrtype = pSubscrtype;
    }


    /**
     * Gets the pSubNo value for this TransferOwnershipRequest.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this TransferOwnershipRequest.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pUsername value for this TransferOwnershipRequest.
     * 
     * @return pUsername
     */
    public java.lang.String getPUsername() {
        return pUsername;
    }


    /**
     * Sets the pUsername value for this TransferOwnershipRequest.
     * 
     * @param pUsername
     */
    public void setPUsername(java.lang.String pUsername) {
        this.pUsername = pUsername;
    }


    /**
     * Gets the pServices value for this TransferOwnershipRequest.
     * 
     * @return pServices
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices getPServices() {
        return pServices;
    }


    /**
     * Sets the pServices value for this TransferOwnershipRequest.
     * 
     * @param pServices
     */
    public void setPServices(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.PServices pServices) {
        this.pServices = pServices;
    }


    /**
     * Gets the pNewcontract value for this TransferOwnershipRequest.
     * 
     * @return pNewcontract
     */
    public java.lang.String getPNewcontract() {
        return pNewcontract;
    }


    /**
     * Sets the pNewcontract value for this TransferOwnershipRequest.
     * 
     * @param pNewcontract
     */
    public void setPNewcontract(java.lang.String pNewcontract) {
        this.pNewcontract = pNewcontract;
    }


    /**
     * Gets the pSpecifiedamount value for this TransferOwnershipRequest.
     * 
     * @return pSpecifiedamount
     */
    public java.math.BigInteger getPSpecifiedamount() {
        return pSpecifiedamount;
    }


    /**
     * Sets the pSpecifiedamount value for this TransferOwnershipRequest.
     * 
     * @param pSpecifiedamount
     */
    public void setPSpecifiedamount(java.math.BigInteger pSpecifiedamount) {
        this.pSpecifiedamount = pSpecifiedamount;
    }


    /**
     * Gets the pSubsidyFlag value for this TransferOwnershipRequest.
     * 
     * @return pSubsidyFlag
     */
    public java.lang.String getPSubsidyFlag() {
        return pSubsidyFlag;
    }


    /**
     * Sets the pSubsidyFlag value for this TransferOwnershipRequest.
     * 
     * @param pSubsidyFlag
     */
    public void setPSubsidyFlag(java.lang.String pSubsidyFlag) {
        this.pSubsidyFlag = pSubsidyFlag;
    }


    /**
     * Gets the pHlrProfile value for this TransferOwnershipRequest.
     * 
     * @return pHlrProfile
     */
    public java.lang.String getPHlrProfile() {
        return pHlrProfile;
    }


    /**
     * Sets the pHlrProfile value for this TransferOwnershipRequest.
     * 
     * @param pHlrProfile
     */
    public void setPHlrProfile(java.lang.String pHlrProfile) {
        this.pHlrProfile = pHlrProfile;
    }


    /**
     * Gets the pSndcmd value for this TransferOwnershipRequest.
     * 
     * @return pSndcmd
     */
    public java.lang.String getPSndcmd() {
        return pSndcmd;
    }


    /**
     * Sets the pSndcmd value for this TransferOwnershipRequest.
     * 
     * @param pSndcmd
     */
    public void setPSndcmd(java.lang.String pSndcmd) {
        this.pSndcmd = pSndcmd;
    }


    /**
     * Gets the pAdditionalparams value for this TransferOwnershipRequest.
     * 
     * @return pAdditionalparams
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] getPAdditionalparams() {
        return pAdditionalparams;
    }


    /**
     * Sets the pAdditionalparams value for this TransferOwnershipRequest.
     * 
     * @param pAdditionalparams
     */
    public void setPAdditionalparams(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pAdditionalparams) {
        this.pAdditionalparams = pAdditionalparams;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransferOwnershipRequest)) return false;
        TransferOwnershipRequest other = (TransferOwnershipRequest) obj;
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
            ((this.pNewcontract==null && other.getPNewcontract()==null) || 
             (this.pNewcontract!=null &&
              this.pNewcontract.equals(other.getPNewcontract()))) &&
            ((this.pSpecifiedamount==null && other.getPSpecifiedamount()==null) || 
             (this.pSpecifiedamount!=null &&
              this.pSpecifiedamount.equals(other.getPSpecifiedamount()))) &&
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
        if (getPNewcontract() != null) {
            _hashCode += getPNewcontract().hashCode();
        }
        if (getPSpecifiedamount() != null) {
            _hashCode += getPSpecifiedamount().hashCode();
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
        new org.apache.axis.description.TypeDesc(TransferOwnershipRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "transferOwnershipRequest"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PServices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pServices"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PNewcontract");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pNewcontract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSpecifiedamount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSpecifiedamount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList"));
        elemField.setMinOccurs(0);
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
