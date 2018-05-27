/**
 * PaySubscriber.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class PaySubscriber  implements java.io.Serializable {
    private java.lang.String area;

    private java.lang.String subscriberType;

    private java.lang.String subNo;

    private double amount;

    private java.lang.String username;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlPaymentInfo xmlPaymentInfo;

    private java.lang.String currency;

    private java.lang.String paymentFor;

    private java.lang.String pPayFutureInvocies;

    private java.lang.String pIsdepositrefund;

    private java.lang.String pIsadvancepaycontrno;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pParamlistXml;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlInvoicesInfo xmlInvoicesInfo;

    public PaySubscriber() {
    }

    public PaySubscriber(
           java.lang.String area,
           java.lang.String subscriberType,
           java.lang.String subNo,
           double amount,
           java.lang.String username,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlPaymentInfo xmlPaymentInfo,
           java.lang.String currency,
           java.lang.String paymentFor,
           java.lang.String pPayFutureInvocies,
           java.lang.String pIsdepositrefund,
           java.lang.String pIsadvancepaycontrno,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pParamlistXml,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlInvoicesInfo xmlInvoicesInfo) {
           this.area = area;
           this.subscriberType = subscriberType;
           this.subNo = subNo;
           this.amount = amount;
           this.username = username;
           this.xmlPaymentInfo = xmlPaymentInfo;
           this.currency = currency;
           this.paymentFor = paymentFor;
           this.pPayFutureInvocies = pPayFutureInvocies;
           this.pIsdepositrefund = pIsdepositrefund;
           this.pIsadvancepaycontrno = pIsadvancepaycontrno;
           this.pParamlistXml = pParamlistXml;
           this.xmlInvoicesInfo = xmlInvoicesInfo;
    }


    /**
     * Gets the area value for this PaySubscriber.
     * 
     * @return area
     */
    public java.lang.String getArea() {
        return area;
    }


    /**
     * Sets the area value for this PaySubscriber.
     * 
     * @param area
     */
    public void setArea(java.lang.String area) {
        this.area = area;
    }


    /**
     * Gets the subscriberType value for this PaySubscriber.
     * 
     * @return subscriberType
     */
    public java.lang.String getSubscriberType() {
        return subscriberType;
    }


    /**
     * Sets the subscriberType value for this PaySubscriber.
     * 
     * @param subscriberType
     */
    public void setSubscriberType(java.lang.String subscriberType) {
        this.subscriberType = subscriberType;
    }


    /**
     * Gets the subNo value for this PaySubscriber.
     * 
     * @return subNo
     */
    public java.lang.String getSubNo() {
        return subNo;
    }


    /**
     * Sets the subNo value for this PaySubscriber.
     * 
     * @param subNo
     */
    public void setSubNo(java.lang.String subNo) {
        this.subNo = subNo;
    }


    /**
     * Gets the amount value for this PaySubscriber.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this PaySubscriber.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the username value for this PaySubscriber.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this PaySubscriber.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the xmlPaymentInfo value for this PaySubscriber.
     * 
     * @return xmlPaymentInfo
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlPaymentInfo getXmlPaymentInfo() {
        return xmlPaymentInfo;
    }


    /**
     * Sets the xmlPaymentInfo value for this PaySubscriber.
     * 
     * @param xmlPaymentInfo
     */
    public void setXmlPaymentInfo(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlPaymentInfo xmlPaymentInfo) {
        this.xmlPaymentInfo = xmlPaymentInfo;
    }


    /**
     * Gets the currency value for this PaySubscriber.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this PaySubscriber.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the paymentFor value for this PaySubscriber.
     * 
     * @return paymentFor
     */
    public java.lang.String getPaymentFor() {
        return paymentFor;
    }


    /**
     * Sets the paymentFor value for this PaySubscriber.
     * 
     * @param paymentFor
     */
    public void setPaymentFor(java.lang.String paymentFor) {
        this.paymentFor = paymentFor;
    }


    /**
     * Gets the pPayFutureInvocies value for this PaySubscriber.
     * 
     * @return pPayFutureInvocies
     */
    public java.lang.String getPPayFutureInvocies() {
        return pPayFutureInvocies;
    }


    /**
     * Sets the pPayFutureInvocies value for this PaySubscriber.
     * 
     * @param pPayFutureInvocies
     */
    public void setPPayFutureInvocies(java.lang.String pPayFutureInvocies) {
        this.pPayFutureInvocies = pPayFutureInvocies;
    }


    /**
     * Gets the pIsdepositrefund value for this PaySubscriber.
     * 
     * @return pIsdepositrefund
     */
    public java.lang.String getPIsdepositrefund() {
        return pIsdepositrefund;
    }


    /**
     * Sets the pIsdepositrefund value for this PaySubscriber.
     * 
     * @param pIsdepositrefund
     */
    public void setPIsdepositrefund(java.lang.String pIsdepositrefund) {
        this.pIsdepositrefund = pIsdepositrefund;
    }


    /**
     * Gets the pIsadvancepaycontrno value for this PaySubscriber.
     * 
     * @return pIsadvancepaycontrno
     */
    public java.lang.String getPIsadvancepaycontrno() {
        return pIsadvancepaycontrno;
    }


    /**
     * Sets the pIsadvancepaycontrno value for this PaySubscriber.
     * 
     * @param pIsadvancepaycontrno
     */
    public void setPIsadvancepaycontrno(java.lang.String pIsadvancepaycontrno) {
        this.pIsadvancepaycontrno = pIsadvancepaycontrno;
    }


    /**
     * Gets the pParamlistXml value for this PaySubscriber.
     * 
     * @return pParamlistXml
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] getPParamlistXml() {
        return pParamlistXml;
    }


    /**
     * Sets the pParamlistXml value for this PaySubscriber.
     * 
     * @param pParamlistXml
     */
    public void setPParamlistXml(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.SParamList[] pParamlistXml) {
        this.pParamlistXml = pParamlistXml;
    }


    /**
     * Gets the xmlInvoicesInfo value for this PaySubscriber.
     * 
     * @return xmlInvoicesInfo
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlInvoicesInfo getXmlInvoicesInfo() {
        return xmlInvoicesInfo;
    }


    /**
     * Sets the xmlInvoicesInfo value for this PaySubscriber.
     * 
     * @param xmlInvoicesInfo
     */
    public void setXmlInvoicesInfo(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.XmlInvoicesInfo xmlInvoicesInfo) {
        this.xmlInvoicesInfo = xmlInvoicesInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaySubscriber)) return false;
        PaySubscriber other = (PaySubscriber) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.area==null && other.getArea()==null) || 
             (this.area!=null &&
              this.area.equals(other.getArea()))) &&
            ((this.subscriberType==null && other.getSubscriberType()==null) || 
             (this.subscriberType!=null &&
              this.subscriberType.equals(other.getSubscriberType()))) &&
            ((this.subNo==null && other.getSubNo()==null) || 
             (this.subNo!=null &&
              this.subNo.equals(other.getSubNo()))) &&
            this.amount == other.getAmount() &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.xmlPaymentInfo==null && other.getXmlPaymentInfo()==null) || 
             (this.xmlPaymentInfo!=null &&
              this.xmlPaymentInfo.equals(other.getXmlPaymentInfo()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.paymentFor==null && other.getPaymentFor()==null) || 
             (this.paymentFor!=null &&
              this.paymentFor.equals(other.getPaymentFor()))) &&
            ((this.pPayFutureInvocies==null && other.getPPayFutureInvocies()==null) || 
             (this.pPayFutureInvocies!=null &&
              this.pPayFutureInvocies.equals(other.getPPayFutureInvocies()))) &&
            ((this.pIsdepositrefund==null && other.getPIsdepositrefund()==null) || 
             (this.pIsdepositrefund!=null &&
              this.pIsdepositrefund.equals(other.getPIsdepositrefund()))) &&
            ((this.pIsadvancepaycontrno==null && other.getPIsadvancepaycontrno()==null) || 
             (this.pIsadvancepaycontrno!=null &&
              this.pIsadvancepaycontrno.equals(other.getPIsadvancepaycontrno()))) &&
            ((this.pParamlistXml==null && other.getPParamlistXml()==null) || 
             (this.pParamlistXml!=null &&
              java.util.Arrays.equals(this.pParamlistXml, other.getPParamlistXml()))) &&
            ((this.xmlInvoicesInfo==null && other.getXmlInvoicesInfo()==null) || 
             (this.xmlInvoicesInfo!=null &&
              this.xmlInvoicesInfo.equals(other.getXmlInvoicesInfo())));
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
        if (getArea() != null) {
            _hashCode += getArea().hashCode();
        }
        if (getSubscriberType() != null) {
            _hashCode += getSubscriberType().hashCode();
        }
        if (getSubNo() != null) {
            _hashCode += getSubNo().hashCode();
        }
        _hashCode += new Double(getAmount()).hashCode();
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getXmlPaymentInfo() != null) {
            _hashCode += getXmlPaymentInfo().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getPaymentFor() != null) {
            _hashCode += getPaymentFor().hashCode();
        }
        if (getPPayFutureInvocies() != null) {
            _hashCode += getPPayFutureInvocies().hashCode();
        }
        if (getPIsdepositrefund() != null) {
            _hashCode += getPIsdepositrefund().hashCode();
        }
        if (getPIsadvancepaycontrno() != null) {
            _hashCode += getPIsadvancepaycontrno().hashCode();
        }
        if (getPParamlistXml() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPParamlistXml());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPParamlistXml(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getXmlInvoicesInfo() != null) {
            _hashCode += getXmlInvoicesInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaySubscriber.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "paySubscriber"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("area");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Area"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "subscriberType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "subNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlPaymentInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "xmlPaymentInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "xmlPaymentInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentFor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PaymentFor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PPayFutureInvocies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pPayFutureInvocies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PIsdepositrefund");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pIsdepositrefund"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PIsadvancepaycontrno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pIsadvancepaycontrno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PParamlistXml");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pParamlistXml"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SParamList"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlInvoicesInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "xmlInvoicesInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "xmlInvoicesInfo"));
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
