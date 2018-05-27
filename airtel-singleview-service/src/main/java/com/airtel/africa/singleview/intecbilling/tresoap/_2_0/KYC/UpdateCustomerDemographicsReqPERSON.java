/**
 * UpdateCustomerDemographicsReqPERSON.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class UpdateCustomerDemographicsReqPERSON  implements java.io.Serializable {
    private java.lang.String FIRST_NAME;

    private java.lang.String MIDDLE_NAME;

    private java.lang.String LAST_NAME;

    private java.lang.String OFFICIAL_NAME;

    private java.lang.String ID_TYPE;

    private java.lang.String ID_NUMBER;

    private java.lang.String SECONDARY_NUMBER;

    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS ADDRESS;

    public UpdateCustomerDemographicsReqPERSON() {
    }

    public UpdateCustomerDemographicsReqPERSON(
           java.lang.String FIRST_NAME,
           java.lang.String MIDDLE_NAME,
           java.lang.String LAST_NAME,
           java.lang.String OFFICIAL_NAME,
           java.lang.String ID_TYPE,
           java.lang.String ID_NUMBER,
           java.lang.String SECONDARY_NUMBER,
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS ADDRESS) {
           this.FIRST_NAME = FIRST_NAME;
           this.MIDDLE_NAME = MIDDLE_NAME;
           this.LAST_NAME = LAST_NAME;
           this.OFFICIAL_NAME = OFFICIAL_NAME;
           this.ID_TYPE = ID_TYPE;
           this.ID_NUMBER = ID_NUMBER;
           this.SECONDARY_NUMBER = SECONDARY_NUMBER;
           this.ADDRESS = ADDRESS;
    }


    /**
     * Gets the FIRST_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return FIRST_NAME
     */
    public java.lang.String getFIRST_NAME() {
        return FIRST_NAME;
    }


    /**
     * Sets the FIRST_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param FIRST_NAME
     */
    public void setFIRST_NAME(java.lang.String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }


    /**
     * Gets the MIDDLE_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return MIDDLE_NAME
     */
    public java.lang.String getMIDDLE_NAME() {
        return MIDDLE_NAME;
    }


    /**
     * Sets the MIDDLE_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param MIDDLE_NAME
     */
    public void setMIDDLE_NAME(java.lang.String MIDDLE_NAME) {
        this.MIDDLE_NAME = MIDDLE_NAME;
    }


    /**
     * Gets the LAST_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return LAST_NAME
     */
    public java.lang.String getLAST_NAME() {
        return LAST_NAME;
    }


    /**
     * Sets the LAST_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param LAST_NAME
     */
    public void setLAST_NAME(java.lang.String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }


    /**
     * Gets the OFFICIAL_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return OFFICIAL_NAME
     */
    public java.lang.String getOFFICIAL_NAME() {
        return OFFICIAL_NAME;
    }


    /**
     * Sets the OFFICIAL_NAME value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param OFFICIAL_NAME
     */
    public void setOFFICIAL_NAME(java.lang.String OFFICIAL_NAME) {
        this.OFFICIAL_NAME = OFFICIAL_NAME;
    }


    /**
     * Gets the ID_TYPE value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return ID_TYPE
     */
    public java.lang.String getID_TYPE() {
        return ID_TYPE;
    }


    /**
     * Sets the ID_TYPE value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param ID_TYPE
     */
    public void setID_TYPE(java.lang.String ID_TYPE) {
        this.ID_TYPE = ID_TYPE;
    }


    /**
     * Gets the ID_NUMBER value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return ID_NUMBER
     */
    public java.lang.String getID_NUMBER() {
        return ID_NUMBER;
    }


    /**
     * Sets the ID_NUMBER value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param ID_NUMBER
     */
    public void setID_NUMBER(java.lang.String ID_NUMBER) {
        this.ID_NUMBER = ID_NUMBER;
    }


    /**
     * Gets the SECONDARY_NUMBER value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return SECONDARY_NUMBER
     */
    public java.lang.String getSECONDARY_NUMBER() {
        return SECONDARY_NUMBER;
    }


    /**
     * Sets the SECONDARY_NUMBER value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param SECONDARY_NUMBER
     */
    public void setSECONDARY_NUMBER(java.lang.String SECONDARY_NUMBER) {
        this.SECONDARY_NUMBER = SECONDARY_NUMBER;
    }


    /**
     * Gets the ADDRESS value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @return ADDRESS
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS getADDRESS() {
        return ADDRESS;
    }


    /**
     * Sets the ADDRESS value for this UpdateCustomerDemographicsReqPERSON.
     * 
     * @param ADDRESS
     */
    public void setADDRESS(com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomerDemographicsReqPERSON)) return false;
        UpdateCustomerDemographicsReqPERSON other = (UpdateCustomerDemographicsReqPERSON) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.FIRST_NAME==null && other.getFIRST_NAME()==null) || 
             (this.FIRST_NAME!=null &&
              this.FIRST_NAME.equals(other.getFIRST_NAME()))) &&
            ((this.MIDDLE_NAME==null && other.getMIDDLE_NAME()==null) || 
             (this.MIDDLE_NAME!=null &&
              this.MIDDLE_NAME.equals(other.getMIDDLE_NAME()))) &&
            ((this.LAST_NAME==null && other.getLAST_NAME()==null) || 
             (this.LAST_NAME!=null &&
              this.LAST_NAME.equals(other.getLAST_NAME()))) &&
            ((this.OFFICIAL_NAME==null && other.getOFFICIAL_NAME()==null) || 
             (this.OFFICIAL_NAME!=null &&
              this.OFFICIAL_NAME.equals(other.getOFFICIAL_NAME()))) &&
            ((this.ID_TYPE==null && other.getID_TYPE()==null) || 
             (this.ID_TYPE!=null &&
              this.ID_TYPE.equals(other.getID_TYPE()))) &&
            ((this.ID_NUMBER==null && other.getID_NUMBER()==null) || 
             (this.ID_NUMBER!=null &&
              this.ID_NUMBER.equals(other.getID_NUMBER()))) &&
            ((this.SECONDARY_NUMBER==null && other.getSECONDARY_NUMBER()==null) || 
             (this.SECONDARY_NUMBER!=null &&
              this.SECONDARY_NUMBER.equals(other.getSECONDARY_NUMBER()))) &&
            ((this.ADDRESS==null && other.getADDRESS()==null) || 
             (this.ADDRESS!=null &&
              this.ADDRESS.equals(other.getADDRESS())));
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
        if (getFIRST_NAME() != null) {
            _hashCode += getFIRST_NAME().hashCode();
        }
        if (getMIDDLE_NAME() != null) {
            _hashCode += getMIDDLE_NAME().hashCode();
        }
        if (getLAST_NAME() != null) {
            _hashCode += getLAST_NAME().hashCode();
        }
        if (getOFFICIAL_NAME() != null) {
            _hashCode += getOFFICIAL_NAME().hashCode();
        }
        if (getID_TYPE() != null) {
            _hashCode += getID_TYPE().hashCode();
        }
        if (getID_NUMBER() != null) {
            _hashCode += getID_NUMBER().hashCode();
        }
        if (getSECONDARY_NUMBER() != null) {
            _hashCode += getSECONDARY_NUMBER().hashCode();
        }
        if (getADDRESS() != null) {
            _hashCode += getADDRESS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
