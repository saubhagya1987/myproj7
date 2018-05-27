/**
 * Payment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class Payment  implements java.io.Serializable {
    private long paymentMode;

    private java.lang.Long creditCardCoID;

    private java.lang.Long creditCardNum;

    private java.lang.String creditCardExpire;

    private java.lang.Long creditCardVerifNum;

    private java.lang.Long chequeBankCode;

    private java.lang.Long chequeBankBranchID;

    private java.lang.String chequeNum;

    private java.util.Calendar chequeIssueDate;

    private java.util.Calendar chequeExpiryDate;

    private java.lang.Long chequeBankAccId;

    public Payment() {
    }

    public Payment(
           long paymentMode,
           java.lang.Long creditCardCoID,
           java.lang.Long creditCardNum,
           java.lang.String creditCardExpire,
           java.lang.Long creditCardVerifNum,
           java.lang.Long chequeBankCode,
           java.lang.Long chequeBankBranchID,
           java.lang.String chequeNum,
           java.util.Calendar chequeIssueDate,
           java.util.Calendar chequeExpiryDate,
           java.lang.Long chequeBankAccId) {
           this.paymentMode = paymentMode;
           this.creditCardCoID = creditCardCoID;
           this.creditCardNum = creditCardNum;
           this.creditCardExpire = creditCardExpire;
           this.creditCardVerifNum = creditCardVerifNum;
           this.chequeBankCode = chequeBankCode;
           this.chequeBankBranchID = chequeBankBranchID;
           this.chequeNum = chequeNum;
           this.chequeIssueDate = chequeIssueDate;
           this.chequeExpiryDate = chequeExpiryDate;
           this.chequeBankAccId = chequeBankAccId;
    }


    /**
     * Gets the paymentMode value for this Payment.
     * 
     * @return paymentMode
     */
    public long getPaymentMode() {
        return paymentMode;
    }


    /**
     * Sets the paymentMode value for this Payment.
     * 
     * @param paymentMode
     */
    public void setPaymentMode(long paymentMode) {
        this.paymentMode = paymentMode;
    }


    /**
     * Gets the creditCardCoID value for this Payment.
     * 
     * @return creditCardCoID
     */
    public java.lang.Long getCreditCardCoID() {
        return creditCardCoID;
    }


    /**
     * Sets the creditCardCoID value for this Payment.
     * 
     * @param creditCardCoID
     */
    public void setCreditCardCoID(java.lang.Long creditCardCoID) {
        this.creditCardCoID = creditCardCoID;
    }


    /**
     * Gets the creditCardNum value for this Payment.
     * 
     * @return creditCardNum
     */
    public java.lang.Long getCreditCardNum() {
        return creditCardNum;
    }


    /**
     * Sets the creditCardNum value for this Payment.
     * 
     * @param creditCardNum
     */
    public void setCreditCardNum(java.lang.Long creditCardNum) {
        this.creditCardNum = creditCardNum;
    }


    /**
     * Gets the creditCardExpire value for this Payment.
     * 
     * @return creditCardExpire
     */
    public java.lang.String getCreditCardExpire() {
        return creditCardExpire;
    }


    /**
     * Sets the creditCardExpire value for this Payment.
     * 
     * @param creditCardExpire
     */
    public void setCreditCardExpire(java.lang.String creditCardExpire) {
        this.creditCardExpire = creditCardExpire;
    }


    /**
     * Gets the creditCardVerifNum value for this Payment.
     * 
     * @return creditCardVerifNum
     */
    public java.lang.Long getCreditCardVerifNum() {
        return creditCardVerifNum;
    }


    /**
     * Sets the creditCardVerifNum value for this Payment.
     * 
     * @param creditCardVerifNum
     */
    public void setCreditCardVerifNum(java.lang.Long creditCardVerifNum) {
        this.creditCardVerifNum = creditCardVerifNum;
    }


    /**
     * Gets the chequeBankCode value for this Payment.
     * 
     * @return chequeBankCode
     */
    public java.lang.Long getChequeBankCode() {
        return chequeBankCode;
    }


    /**
     * Sets the chequeBankCode value for this Payment.
     * 
     * @param chequeBankCode
     */
    public void setChequeBankCode(java.lang.Long chequeBankCode) {
        this.chequeBankCode = chequeBankCode;
    }


    /**
     * Gets the chequeBankBranchID value for this Payment.
     * 
     * @return chequeBankBranchID
     */
    public java.lang.Long getChequeBankBranchID() {
        return chequeBankBranchID;
    }


    /**
     * Sets the chequeBankBranchID value for this Payment.
     * 
     * @param chequeBankBranchID
     */
    public void setChequeBankBranchID(java.lang.Long chequeBankBranchID) {
        this.chequeBankBranchID = chequeBankBranchID;
    }


    /**
     * Gets the chequeNum value for this Payment.
     * 
     * @return chequeNum
     */
    public java.lang.String getChequeNum() {
        return chequeNum;
    }


    /**
     * Sets the chequeNum value for this Payment.
     * 
     * @param chequeNum
     */
    public void setChequeNum(java.lang.String chequeNum) {
        this.chequeNum = chequeNum;
    }


    /**
     * Gets the chequeIssueDate value for this Payment.
     * 
     * @return chequeIssueDate
     */
    public java.util.Calendar getChequeIssueDate() {
        return chequeIssueDate;
    }


    /**
     * Sets the chequeIssueDate value for this Payment.
     * 
     * @param chequeIssueDate
     */
    public void setChequeIssueDate(java.util.Calendar chequeIssueDate) {
        this.chequeIssueDate = chequeIssueDate;
    }


    /**
     * Gets the chequeExpiryDate value for this Payment.
     * 
     * @return chequeExpiryDate
     */
    public java.util.Calendar getChequeExpiryDate() {
        return chequeExpiryDate;
    }


    /**
     * Sets the chequeExpiryDate value for this Payment.
     * 
     * @param chequeExpiryDate
     */
    public void setChequeExpiryDate(java.util.Calendar chequeExpiryDate) {
        this.chequeExpiryDate = chequeExpiryDate;
    }


    /**
     * Gets the chequeBankAccId value for this Payment.
     * 
     * @return chequeBankAccId
     */
    public java.lang.Long getChequeBankAccId() {
        return chequeBankAccId;
    }


    /**
     * Sets the chequeBankAccId value for this Payment.
     * 
     * @param chequeBankAccId
     */
    public void setChequeBankAccId(java.lang.Long chequeBankAccId) {
        this.chequeBankAccId = chequeBankAccId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Payment)) return false;
        Payment other = (Payment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.paymentMode == other.getPaymentMode() &&
            ((this.creditCardCoID==null && other.getCreditCardCoID()==null) || 
             (this.creditCardCoID!=null &&
              this.creditCardCoID.equals(other.getCreditCardCoID()))) &&
            ((this.creditCardNum==null && other.getCreditCardNum()==null) || 
             (this.creditCardNum!=null &&
              this.creditCardNum.equals(other.getCreditCardNum()))) &&
            ((this.creditCardExpire==null && other.getCreditCardExpire()==null) || 
             (this.creditCardExpire!=null &&
              this.creditCardExpire.equals(other.getCreditCardExpire()))) &&
            ((this.creditCardVerifNum==null && other.getCreditCardVerifNum()==null) || 
             (this.creditCardVerifNum!=null &&
              this.creditCardVerifNum.equals(other.getCreditCardVerifNum()))) &&
            ((this.chequeBankCode==null && other.getChequeBankCode()==null) || 
             (this.chequeBankCode!=null &&
              this.chequeBankCode.equals(other.getChequeBankCode()))) &&
            ((this.chequeBankBranchID==null && other.getChequeBankBranchID()==null) || 
             (this.chequeBankBranchID!=null &&
              this.chequeBankBranchID.equals(other.getChequeBankBranchID()))) &&
            ((this.chequeNum==null && other.getChequeNum()==null) || 
             (this.chequeNum!=null &&
              this.chequeNum.equals(other.getChequeNum()))) &&
            ((this.chequeIssueDate==null && other.getChequeIssueDate()==null) || 
             (this.chequeIssueDate!=null &&
              this.chequeIssueDate.equals(other.getChequeIssueDate()))) &&
            ((this.chequeExpiryDate==null && other.getChequeExpiryDate()==null) || 
             (this.chequeExpiryDate!=null &&
              this.chequeExpiryDate.equals(other.getChequeExpiryDate()))) &&
            ((this.chequeBankAccId==null && other.getChequeBankAccId()==null) || 
             (this.chequeBankAccId!=null &&
              this.chequeBankAccId.equals(other.getChequeBankAccId())));
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
        _hashCode += new Long(getPaymentMode()).hashCode();
        if (getCreditCardCoID() != null) {
            _hashCode += getCreditCardCoID().hashCode();
        }
        if (getCreditCardNum() != null) {
            _hashCode += getCreditCardNum().hashCode();
        }
        if (getCreditCardExpire() != null) {
            _hashCode += getCreditCardExpire().hashCode();
        }
        if (getCreditCardVerifNum() != null) {
            _hashCode += getCreditCardVerifNum().hashCode();
        }
        if (getChequeBankCode() != null) {
            _hashCode += getChequeBankCode().hashCode();
        }
        if (getChequeBankBranchID() != null) {
            _hashCode += getChequeBankBranchID().hashCode();
        }
        if (getChequeNum() != null) {
            _hashCode += getChequeNum().hashCode();
        }
        if (getChequeIssueDate() != null) {
            _hashCode += getChequeIssueDate().hashCode();
        }
        if (getChequeExpiryDate() != null) {
            _hashCode += getChequeExpiryDate().hashCode();
        }
        if (getChequeBankAccId() != null) {
            _hashCode += getChequeBankAccId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Payment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Payment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PaymentMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardCoID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CreditCardCoID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CreditCardNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardExpire");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CreditCardExpire"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardVerifNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CreditCardVerifNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chequeBankCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChequeBankCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chequeBankBranchID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChequeBankBranchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chequeNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChequeNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chequeIssueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChequeIssueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chequeExpiryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChequeExpiryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chequeBankAccId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ChequeBankAccId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
