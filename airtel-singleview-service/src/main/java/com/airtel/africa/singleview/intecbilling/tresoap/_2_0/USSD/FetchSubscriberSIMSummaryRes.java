/**
 * FetchSubscriberSIMSummaryRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class FetchSubscriberSIMSummaryRes  implements java.io.Serializable {
    private java.lang.String ICCID;

    private java.lang.String CUSTOMER_NAME;

    private java.lang.String IMSI;

    private java.lang.String CONTRACT_NUMBER;

    private java.lang.String CONTACT_NAME;

    private java.lang.String PRE_POST;

    private java.lang.String CUSTOMER_NUMBER;
    
    private org.apache.axis.client.Call _call;

    
    
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.RESULT RESULT;

    public FetchSubscriberSIMSummaryRes() {
    }

    
    
    public org.apache.axis.client.Call get_call() {
		return _call;
	}



	public void set_call(org.apache.axis.client.Call _call) {
		this._call = _call;
	}



	public FetchSubscriberSIMSummaryRes(
           java.lang.String ICCID,
           java.lang.String CUSTOMER_NAME,
           java.lang.String IMSI,
           java.lang.String CONTRACT_NUMBER,
           java.lang.String CONTACT_NAME,
           java.lang.String PRE_POST,
           java.lang.String CUSTOMER_NUMBER,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.RESULT RESULT) {
           this.ICCID = ICCID;
           this.CUSTOMER_NAME = CUSTOMER_NAME;
           this.IMSI = IMSI;
           this.CONTRACT_NUMBER = CONTRACT_NUMBER;
           this.CONTACT_NAME = CONTACT_NAME;
           this.PRE_POST = PRE_POST;
           this.CUSTOMER_NUMBER = CUSTOMER_NUMBER;
           this.RESULT = RESULT;
    }


    /**
     * Gets the ICCID value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return ICCID
     */
    public java.lang.String getICCID() {
        return ICCID;
    }


    /**
     * Sets the ICCID value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param ICCID
     */
    public void setICCID(java.lang.String ICCID) {
        this.ICCID = ICCID;
    }


    /**
     * Gets the CUSTOMER_NAME value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return CUSTOMER_NAME
     */
    public java.lang.String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }


    /**
     * Sets the CUSTOMER_NAME value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param CUSTOMER_NAME
     */
    public void setCUSTOMER_NAME(java.lang.String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }


    /**
     * Gets the IMSI value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return IMSI
     */
    public java.lang.String getIMSI() {
        return IMSI;
    }


    /**
     * Sets the IMSI value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param IMSI
     */
    public void setIMSI(java.lang.String IMSI) {
        this.IMSI = IMSI;
    }


    /**
     * Gets the CONTRACT_NUMBER value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return CONTRACT_NUMBER
     */
    public java.lang.String getCONTRACT_NUMBER() {
        return CONTRACT_NUMBER;
    }


    /**
     * Sets the CONTRACT_NUMBER value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param CONTRACT_NUMBER
     */
    public void setCONTRACT_NUMBER(java.lang.String CONTRACT_NUMBER) {
        this.CONTRACT_NUMBER = CONTRACT_NUMBER;
    }


    /**
     * Gets the CONTACT_NAME value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return CONTACT_NAME
     */
    public java.lang.String getCONTACT_NAME() {
        return CONTACT_NAME;
    }


    /**
     * Sets the CONTACT_NAME value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param CONTACT_NAME
     */
    public void setCONTACT_NAME(java.lang.String CONTACT_NAME) {
        this.CONTACT_NAME = CONTACT_NAME;
    }


    /**
     * Gets the PRE_POST value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return PRE_POST
     */
    public java.lang.String getPRE_POST() {
        return PRE_POST;
    }


    /**
     * Sets the PRE_POST value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param PRE_POST
     */
    public void setPRE_POST(java.lang.String PRE_POST) {
        this.PRE_POST = PRE_POST;
    }


    /**
     * Gets the CUSTOMER_NUMBER value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return CUSTOMER_NUMBER
     */
    public java.lang.String getCUSTOMER_NUMBER() {
        return CUSTOMER_NUMBER;
    }


    /**
     * Sets the CUSTOMER_NUMBER value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param CUSTOMER_NUMBER
     */
    public void setCUSTOMER_NUMBER(java.lang.String CUSTOMER_NUMBER) {
        this.CUSTOMER_NUMBER = CUSTOMER_NUMBER;
    }


    /**
     * Gets the RESULT value for this FetchSubscriberSIMSummaryRes.
     * 
     * @return RESULT
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.RESULT getRESULT() {
        return RESULT;
    }


    /**
     * Sets the RESULT value for this FetchSubscriberSIMSummaryRes.
     * 
     * @param RESULT
     */
    public void setRESULT(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.RESULT RESULT) {
        this.RESULT = RESULT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetchSubscriberSIMSummaryRes)) return false;
        FetchSubscriberSIMSummaryRes other = (FetchSubscriberSIMSummaryRes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ICCID==null && other.getICCID()==null) || 
             (this.ICCID!=null &&
              this.ICCID.equals(other.getICCID()))) &&
            ((this.CUSTOMER_NAME==null && other.getCUSTOMER_NAME()==null) || 
             (this.CUSTOMER_NAME!=null &&
              this.CUSTOMER_NAME.equals(other.getCUSTOMER_NAME()))) &&
            ((this.IMSI==null && other.getIMSI()==null) || 
             (this.IMSI!=null &&
              this.IMSI.equals(other.getIMSI()))) &&
            ((this.CONTRACT_NUMBER==null && other.getCONTRACT_NUMBER()==null) || 
             (this.CONTRACT_NUMBER!=null &&
              this.CONTRACT_NUMBER.equals(other.getCONTRACT_NUMBER()))) &&
            ((this.CONTACT_NAME==null && other.getCONTACT_NAME()==null) || 
             (this.CONTACT_NAME!=null &&
              this.CONTACT_NAME.equals(other.getCONTACT_NAME()))) &&
            ((this.PRE_POST==null && other.getPRE_POST()==null) || 
             (this.PRE_POST!=null &&
              this.PRE_POST.equals(other.getPRE_POST()))) &&
            ((this.CUSTOMER_NUMBER==null && other.getCUSTOMER_NUMBER()==null) || 
             (this.CUSTOMER_NUMBER!=null &&
              this.CUSTOMER_NUMBER.equals(other.getCUSTOMER_NUMBER()))) &&
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
        if (getICCID() != null) {
            _hashCode += getICCID().hashCode();
        }
        if (getCUSTOMER_NAME() != null) {
            _hashCode += getCUSTOMER_NAME().hashCode();
        }
        if (getIMSI() != null) {
            _hashCode += getIMSI().hashCode();
        }
        if (getCONTRACT_NUMBER() != null) {
            _hashCode += getCONTRACT_NUMBER().hashCode();
        }
        if (getCONTACT_NAME() != null) {
            _hashCode += getCONTACT_NAME().hashCode();
        }
        if (getPRE_POST() != null) {
            _hashCode += getPRE_POST().hashCode();
        }
        if (getCUSTOMER_NUMBER() != null) {
            _hashCode += getCUSTOMER_NUMBER().hashCode();
        }
        if (getRESULT() != null) {
            _hashCode += getRESULT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FetchSubscriberSIMSummaryRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "fetchSubscriberSIMSummaryRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ICCID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "ICCID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUSTOMER_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CUSTOMER_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMSI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "IMSI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTRACT_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CONTRACT_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CONTACT_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRE_POST");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "PRE_POST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUSTOMER_NUMBER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CUSTOMER_NUMBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESULT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "RESULT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "RESULT"));
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
