/**
 * CreateNewInteraction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class CreateNewInteraction  implements java.io.Serializable {
    private java.lang.String pUsername;

    private java.lang.String pSubNo;

    private java.lang.String pAccountNo;

    private java.util.Calendar pTroubleDate;

    private java.lang.String pInteractionType;

    private java.lang.String pInteractionMode;

    private java.lang.String pReasonCode;

    private java.lang.String pDescription;

    private java.lang.String pPhoneNumberType;

    private java.math.BigInteger pPhoneNumber;

    private java.lang.String pConvenientTime;

    public CreateNewInteraction() {
    }

    public CreateNewInteraction(
           java.lang.String pUsername,
           java.lang.String pSubNo,
           java.lang.String pAccountNo,
           java.util.Calendar pTroubleDate,
           java.lang.String pInteractionType,
           java.lang.String pInteractionMode,
           java.lang.String pReasonCode,
           java.lang.String pDescription,
           java.lang.String pPhoneNumberType,
           java.math.BigInteger pPhoneNumber,
           java.lang.String pConvenientTime) {
           this.pUsername = pUsername;
           this.pSubNo = pSubNo;
           this.pAccountNo = pAccountNo;
           this.pTroubleDate = pTroubleDate;
           this.pInteractionType = pInteractionType;
           this.pInteractionMode = pInteractionMode;
           this.pReasonCode = pReasonCode;
           this.pDescription = pDescription;
           this.pPhoneNumberType = pPhoneNumberType;
           this.pPhoneNumber = pPhoneNumber;
           this.pConvenientTime = pConvenientTime;
    }


    /**
     * Gets the pUsername value for this CreateNewInteraction.
     * 
     * @return pUsername
     */
    public java.lang.String getPUsername() {
        return pUsername;
    }


    /**
     * Sets the pUsername value for this CreateNewInteraction.
     * 
     * @param pUsername
     */
    public void setPUsername(java.lang.String pUsername) {
        this.pUsername = pUsername;
    }


    /**
     * Gets the pSubNo value for this CreateNewInteraction.
     * 
     * @return pSubNo
     */
    public java.lang.String getPSubNo() {
        return pSubNo;
    }


    /**
     * Sets the pSubNo value for this CreateNewInteraction.
     * 
     * @param pSubNo
     */
    public void setPSubNo(java.lang.String pSubNo) {
        this.pSubNo = pSubNo;
    }


    /**
     * Gets the pAccountNo value for this CreateNewInteraction.
     * 
     * @return pAccountNo
     */
    public java.lang.String getPAccountNo() {
        return pAccountNo;
    }


    /**
     * Sets the pAccountNo value for this CreateNewInteraction.
     * 
     * @param pAccountNo
     */
    public void setPAccountNo(java.lang.String pAccountNo) {
        this.pAccountNo = pAccountNo;
    }


    /**
     * Gets the pTroubleDate value for this CreateNewInteraction.
     * 
     * @return pTroubleDate
     */
    public java.util.Calendar getPTroubleDate() {
        return pTroubleDate;
    }


    /**
     * Sets the pTroubleDate value for this CreateNewInteraction.
     * 
     * @param pTroubleDate
     */
    public void setPTroubleDate(java.util.Calendar pTroubleDate) {
        this.pTroubleDate = pTroubleDate;
    }


    /**
     * Gets the pInteractionType value for this CreateNewInteraction.
     * 
     * @return pInteractionType
     */
    public java.lang.String getPInteractionType() {
        return pInteractionType;
    }


    /**
     * Sets the pInteractionType value for this CreateNewInteraction.
     * 
     * @param pInteractionType
     */
    public void setPInteractionType(java.lang.String pInteractionType) {
        this.pInteractionType = pInteractionType;
    }


    /**
     * Gets the pInteractionMode value for this CreateNewInteraction.
     * 
     * @return pInteractionMode
     */
    public java.lang.String getPInteractionMode() {
        return pInteractionMode;
    }


    /**
     * Sets the pInteractionMode value for this CreateNewInteraction.
     * 
     * @param pInteractionMode
     */
    public void setPInteractionMode(java.lang.String pInteractionMode) {
        this.pInteractionMode = pInteractionMode;
    }


    /**
     * Gets the pReasonCode value for this CreateNewInteraction.
     * 
     * @return pReasonCode
     */
    public java.lang.String getPReasonCode() {
        return pReasonCode;
    }


    /**
     * Sets the pReasonCode value for this CreateNewInteraction.
     * 
     * @param pReasonCode
     */
    public void setPReasonCode(java.lang.String pReasonCode) {
        this.pReasonCode = pReasonCode;
    }


    /**
     * Gets the pDescription value for this CreateNewInteraction.
     * 
     * @return pDescription
     */
    public java.lang.String getPDescription() {
        return pDescription;
    }


    /**
     * Sets the pDescription value for this CreateNewInteraction.
     * 
     * @param pDescription
     */
    public void setPDescription(java.lang.String pDescription) {
        this.pDescription = pDescription;
    }


    /**
     * Gets the pPhoneNumberType value for this CreateNewInteraction.
     * 
     * @return pPhoneNumberType
     */
    public java.lang.String getPPhoneNumberType() {
        return pPhoneNumberType;
    }


    /**
     * Sets the pPhoneNumberType value for this CreateNewInteraction.
     * 
     * @param pPhoneNumberType
     */
    public void setPPhoneNumberType(java.lang.String pPhoneNumberType) {
        this.pPhoneNumberType = pPhoneNumberType;
    }


    /**
     * Gets the pPhoneNumber value for this CreateNewInteraction.
     * 
     * @return pPhoneNumber
     */
    public java.math.BigInteger getPPhoneNumber() {
        return pPhoneNumber;
    }


    /**
     * Sets the pPhoneNumber value for this CreateNewInteraction.
     * 
     * @param pPhoneNumber
     */
    public void setPPhoneNumber(java.math.BigInteger pPhoneNumber) {
        this.pPhoneNumber = pPhoneNumber;
    }


    /**
     * Gets the pConvenientTime value for this CreateNewInteraction.
     * 
     * @return pConvenientTime
     */
    public java.lang.String getPConvenientTime() {
        return pConvenientTime;
    }


    /**
     * Sets the pConvenientTime value for this CreateNewInteraction.
     * 
     * @param pConvenientTime
     */
    public void setPConvenientTime(java.lang.String pConvenientTime) {
        this.pConvenientTime = pConvenientTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateNewInteraction)) return false;
        CreateNewInteraction other = (CreateNewInteraction) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pUsername==null && other.getPUsername()==null) || 
             (this.pUsername!=null &&
              this.pUsername.equals(other.getPUsername()))) &&
            ((this.pSubNo==null && other.getPSubNo()==null) || 
             (this.pSubNo!=null &&
              this.pSubNo.equals(other.getPSubNo()))) &&
            ((this.pAccountNo==null && other.getPAccountNo()==null) || 
             (this.pAccountNo!=null &&
              this.pAccountNo.equals(other.getPAccountNo()))) &&
            ((this.pTroubleDate==null && other.getPTroubleDate()==null) || 
             (this.pTroubleDate!=null &&
              this.pTroubleDate.equals(other.getPTroubleDate()))) &&
            ((this.pInteractionType==null && other.getPInteractionType()==null) || 
             (this.pInteractionType!=null &&
              this.pInteractionType.equals(other.getPInteractionType()))) &&
            ((this.pInteractionMode==null && other.getPInteractionMode()==null) || 
             (this.pInteractionMode!=null &&
              this.pInteractionMode.equals(other.getPInteractionMode()))) &&
            ((this.pReasonCode==null && other.getPReasonCode()==null) || 
             (this.pReasonCode!=null &&
              this.pReasonCode.equals(other.getPReasonCode()))) &&
            ((this.pDescription==null && other.getPDescription()==null) || 
             (this.pDescription!=null &&
              this.pDescription.equals(other.getPDescription()))) &&
            ((this.pPhoneNumberType==null && other.getPPhoneNumberType()==null) || 
             (this.pPhoneNumberType!=null &&
              this.pPhoneNumberType.equals(other.getPPhoneNumberType()))) &&
            ((this.pPhoneNumber==null && other.getPPhoneNumber()==null) || 
             (this.pPhoneNumber!=null &&
              this.pPhoneNumber.equals(other.getPPhoneNumber()))) &&
            ((this.pConvenientTime==null && other.getPConvenientTime()==null) || 
             (this.pConvenientTime!=null &&
              this.pConvenientTime.equals(other.getPConvenientTime())));
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
        if (getPUsername() != null) {
            _hashCode += getPUsername().hashCode();
        }
        if (getPSubNo() != null) {
            _hashCode += getPSubNo().hashCode();
        }
        if (getPAccountNo() != null) {
            _hashCode += getPAccountNo().hashCode();
        }
        if (getPTroubleDate() != null) {
            _hashCode += getPTroubleDate().hashCode();
        }
        if (getPInteractionType() != null) {
            _hashCode += getPInteractionType().hashCode();
        }
        if (getPInteractionMode() != null) {
            _hashCode += getPInteractionMode().hashCode();
        }
        if (getPReasonCode() != null) {
            _hashCode += getPReasonCode().hashCode();
        }
        if (getPDescription() != null) {
            _hashCode += getPDescription().hashCode();
        }
        if (getPPhoneNumberType() != null) {
            _hashCode += getPPhoneNumberType().hashCode();
        }
        if (getPPhoneNumber() != null) {
            _hashCode += getPPhoneNumber().hashCode();
        }
        if (getPConvenientTime() != null) {
            _hashCode += getPConvenientTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateNewInteraction.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "createNewInteraction"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUsername");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pUsername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PSubNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pSubNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAccountNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pAccountNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PTroubleDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pTroubleDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PInteractionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pInteractionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PInteractionMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pInteractionMode"));
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
        elemField.setFieldName("PDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PPhoneNumberType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pPhoneNumberType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PPhoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pPhoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PConvenientTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pConvenientTime"));
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
