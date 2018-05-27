/**
 * UpdateCustomerDemographicsReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class UpdateCustomerDemographicsReq  implements java.io.Serializable {
    private java.lang.String USER_NAME;

    private java.lang.String RETAILER_MOBILE_NO;

    private java.lang.String SUBSCRIBER_NO;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON CONTACT;

    private java.util.Calendar ENTRY_DATE;

    public UpdateCustomerDemographicsReq() {
    }

    public UpdateCustomerDemographicsReq(
           java.lang.String USER_NAME,
           java.lang.String RETAILER_MOBILE_NO,
           java.lang.String SUBSCRIBER_NO,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON CONTACT,
           java.util.Calendar ENTRY_DATE) {
           this.USER_NAME = USER_NAME;
           this.RETAILER_MOBILE_NO = RETAILER_MOBILE_NO;
           this.SUBSCRIBER_NO = SUBSCRIBER_NO;
           this.CONTACT = CONTACT;
           this.ENTRY_DATE = ENTRY_DATE;
    }


    /**
     * Gets the USER_NAME value for this UpdateCustomerDemographicsReq.
     * 
     * @return USER_NAME
     */
    public java.lang.String getUSER_NAME() {
        return USER_NAME;
    }


    /**
     * Sets the USER_NAME value for this UpdateCustomerDemographicsReq.
     * 
     * @param USER_NAME
     */
    public void setUSER_NAME(java.lang.String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }


    /**
     * Gets the RETAILER_MOBILE_NO value for this UpdateCustomerDemographicsReq.
     * 
     * @return RETAILER_MOBILE_NO
     */
    public java.lang.String getRETAILER_MOBILE_NO() {
        return RETAILER_MOBILE_NO;
    }


    /**
     * Sets the RETAILER_MOBILE_NO value for this UpdateCustomerDemographicsReq.
     * 
     * @param RETAILER_MOBILE_NO
     */
    public void setRETAILER_MOBILE_NO(java.lang.String RETAILER_MOBILE_NO) {
        this.RETAILER_MOBILE_NO = RETAILER_MOBILE_NO;
    }


    /**
     * Gets the SUBSCRIBER_NO value for this UpdateCustomerDemographicsReq.
     * 
     * @return SUBSCRIBER_NO
     */
    public java.lang.String getSUBSCRIBER_NO() {
        return SUBSCRIBER_NO;
    }


    /**
     * Sets the SUBSCRIBER_NO value for this UpdateCustomerDemographicsReq.
     * 
     * @param SUBSCRIBER_NO
     */
    public void setSUBSCRIBER_NO(java.lang.String SUBSCRIBER_NO) {
        this.SUBSCRIBER_NO = SUBSCRIBER_NO;
    }


    /**
     * Gets the CONTACT value for this UpdateCustomerDemographicsReq.
     * 
     * @return CONTACT
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON getCONTACT() {
        return CONTACT;
    }


    /**
     * Sets the CONTACT value for this UpdateCustomerDemographicsReq.
     * 
     * @param CONTACT
     */
    public void setCONTACT(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON CONTACT) {
        this.CONTACT = CONTACT;
    }


    /**
     * Gets the ENTRY_DATE value for this UpdateCustomerDemographicsReq.
     * 
     * @return ENTRY_DATE
     */
    public java.util.Calendar getENTRY_DATE() {
        return ENTRY_DATE;
    }


    /**
     * Sets the ENTRY_DATE value for this UpdateCustomerDemographicsReq.
     * 
     * @param ENTRY_DATE
     */
    public void setENTRY_DATE(java.util.Calendar ENTRY_DATE) {
        this.ENTRY_DATE = ENTRY_DATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomerDemographicsReq)) return false;
        UpdateCustomerDemographicsReq other = (UpdateCustomerDemographicsReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.USER_NAME==null && other.getUSER_NAME()==null) || 
             (this.USER_NAME!=null &&
              this.USER_NAME.equals(other.getUSER_NAME()))) &&
            ((this.RETAILER_MOBILE_NO==null && other.getRETAILER_MOBILE_NO()==null) || 
             (this.RETAILER_MOBILE_NO!=null &&
              this.RETAILER_MOBILE_NO.equals(other.getRETAILER_MOBILE_NO()))) &&
            ((this.SUBSCRIBER_NO==null && other.getSUBSCRIBER_NO()==null) || 
             (this.SUBSCRIBER_NO!=null &&
              this.SUBSCRIBER_NO.equals(other.getSUBSCRIBER_NO()))) &&
            ((this.CONTACT==null && other.getCONTACT()==null) || 
             (this.CONTACT!=null &&
              this.CONTACT.equals(other.getCONTACT()))) &&
            ((this.ENTRY_DATE==null && other.getENTRY_DATE()==null) || 
             (this.ENTRY_DATE!=null &&
              this.ENTRY_DATE.equals(other.getENTRY_DATE())));
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
        if (getUSER_NAME() != null) {
            _hashCode += getUSER_NAME().hashCode();
        }
        if (getRETAILER_MOBILE_NO() != null) {
            _hashCode += getRETAILER_MOBILE_NO().hashCode();
        }
        if (getSUBSCRIBER_NO() != null) {
            _hashCode += getSUBSCRIBER_NO().hashCode();
        }
        if (getCONTACT() != null) {
            _hashCode += getCONTACT().hashCode();
        }
        if (getENTRY_DATE() != null) {
            _hashCode += getENTRY_DATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
