/**
 * UpdateCustomerDemographicsReqADDRESS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class UpdateCustomerDemographicsReqADDRESS  implements java.io.Serializable {
    private java.lang.String BUILDING;

    private java.lang.String APARTMENT;

    private java.lang.String STREET;

    private java.lang.String POST_AREA;

    private java.lang.String ADDRS_BLOCK;

    private java.lang.String PO_BOX;

    private java.lang.String ADDRESS_AREA;

    private java.lang.String ZIP_CODE;

    private java.lang.String COUNTRY;

    public UpdateCustomerDemographicsReqADDRESS() {
    }

    public UpdateCustomerDemographicsReqADDRESS(
           java.lang.String BUILDING,
           java.lang.String APARTMENT,
           java.lang.String STREET,
           java.lang.String POST_AREA,
           java.lang.String ADDRS_BLOCK,
           java.lang.String PO_BOX,
           java.lang.String ADDRESS_AREA,
           java.lang.String ZIP_CODE,
           java.lang.String COUNTRY) {
           this.BUILDING = BUILDING;
           this.APARTMENT = APARTMENT;
           this.STREET = STREET;
           this.POST_AREA = POST_AREA;
           this.ADDRS_BLOCK = ADDRS_BLOCK;
           this.PO_BOX = PO_BOX;
           this.ADDRESS_AREA = ADDRESS_AREA;
           this.ZIP_CODE = ZIP_CODE;
           this.COUNTRY = COUNTRY;
    }


    /**
     * Gets the BUILDING value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return BUILDING
     */
    public java.lang.String getBUILDING() {
        return BUILDING;
    }


    /**
     * Sets the BUILDING value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param BUILDING
     */
    public void setBUILDING(java.lang.String BUILDING) {
        this.BUILDING = BUILDING;
    }


    /**
     * Gets the APARTMENT value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return APARTMENT
     */
    public java.lang.String getAPARTMENT() {
        return APARTMENT;
    }


    /**
     * Sets the APARTMENT value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param APARTMENT
     */
    public void setAPARTMENT(java.lang.String APARTMENT) {
        this.APARTMENT = APARTMENT;
    }


    /**
     * Gets the STREET value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return STREET
     */
    public java.lang.String getSTREET() {
        return STREET;
    }


    /**
     * Sets the STREET value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param STREET
     */
    public void setSTREET(java.lang.String STREET) {
        this.STREET = STREET;
    }


    /**
     * Gets the POST_AREA value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return POST_AREA
     */
    public java.lang.String getPOST_AREA() {
        return POST_AREA;
    }


    /**
     * Sets the POST_AREA value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param POST_AREA
     */
    public void setPOST_AREA(java.lang.String POST_AREA) {
        this.POST_AREA = POST_AREA;
    }


    /**
     * Gets the ADDRS_BLOCK value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return ADDRS_BLOCK
     */
    public java.lang.String getADDRS_BLOCK() {
        return ADDRS_BLOCK;
    }


    /**
     * Sets the ADDRS_BLOCK value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param ADDRS_BLOCK
     */
    public void setADDRS_BLOCK(java.lang.String ADDRS_BLOCK) {
        this.ADDRS_BLOCK = ADDRS_BLOCK;
    }


    /**
     * Gets the PO_BOX value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return PO_BOX
     */
    public java.lang.String getPO_BOX() {
        return PO_BOX;
    }


    /**
     * Sets the PO_BOX value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param PO_BOX
     */
    public void setPO_BOX(java.lang.String PO_BOX) {
        this.PO_BOX = PO_BOX;
    }


    /**
     * Gets the ADDRESS_AREA value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return ADDRESS_AREA
     */
    public java.lang.String getADDRESS_AREA() {
        return ADDRESS_AREA;
    }


    /**
     * Sets the ADDRESS_AREA value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param ADDRESS_AREA
     */
    public void setADDRESS_AREA(java.lang.String ADDRESS_AREA) {
        this.ADDRESS_AREA = ADDRESS_AREA;
    }


    /**
     * Gets the ZIP_CODE value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return ZIP_CODE
     */
    public java.lang.String getZIP_CODE() {
        return ZIP_CODE;
    }


    /**
     * Sets the ZIP_CODE value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param ZIP_CODE
     */
    public void setZIP_CODE(java.lang.String ZIP_CODE) {
        this.ZIP_CODE = ZIP_CODE;
    }


    /**
     * Gets the COUNTRY value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @return COUNTRY
     */
    public java.lang.String getCOUNTRY() {
        return COUNTRY;
    }


    /**
     * Sets the COUNTRY value for this UpdateCustomerDemographicsReqADDRESS.
     * 
     * @param COUNTRY
     */
    public void setCOUNTRY(java.lang.String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomerDemographicsReqADDRESS)) return false;
        UpdateCustomerDemographicsReqADDRESS other = (UpdateCustomerDemographicsReqADDRESS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BUILDING==null && other.getBUILDING()==null) || 
             (this.BUILDING!=null &&
              this.BUILDING.equals(other.getBUILDING()))) &&
            ((this.APARTMENT==null && other.getAPARTMENT()==null) || 
             (this.APARTMENT!=null &&
              this.APARTMENT.equals(other.getAPARTMENT()))) &&
            ((this.STREET==null && other.getSTREET()==null) || 
             (this.STREET!=null &&
              this.STREET.equals(other.getSTREET()))) &&
            ((this.POST_AREA==null && other.getPOST_AREA()==null) || 
             (this.POST_AREA!=null &&
              this.POST_AREA.equals(other.getPOST_AREA()))) &&
            ((this.ADDRS_BLOCK==null && other.getADDRS_BLOCK()==null) || 
             (this.ADDRS_BLOCK!=null &&
              this.ADDRS_BLOCK.equals(other.getADDRS_BLOCK()))) &&
            ((this.PO_BOX==null && other.getPO_BOX()==null) || 
             (this.PO_BOX!=null &&
              this.PO_BOX.equals(other.getPO_BOX()))) &&
            ((this.ADDRESS_AREA==null && other.getADDRESS_AREA()==null) || 
             (this.ADDRESS_AREA!=null &&
              this.ADDRESS_AREA.equals(other.getADDRESS_AREA()))) &&
            ((this.ZIP_CODE==null && other.getZIP_CODE()==null) || 
             (this.ZIP_CODE!=null &&
              this.ZIP_CODE.equals(other.getZIP_CODE()))) &&
            ((this.COUNTRY==null && other.getCOUNTRY()==null) || 
             (this.COUNTRY!=null &&
              this.COUNTRY.equals(other.getCOUNTRY())));
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
        if (getBUILDING() != null) {
            _hashCode += getBUILDING().hashCode();
        }
        if (getAPARTMENT() != null) {
            _hashCode += getAPARTMENT().hashCode();
        }
        if (getSTREET() != null) {
            _hashCode += getSTREET().hashCode();
        }
        if (getPOST_AREA() != null) {
            _hashCode += getPOST_AREA().hashCode();
        }
        if (getADDRS_BLOCK() != null) {
            _hashCode += getADDRS_BLOCK().hashCode();
        }
        if (getPO_BOX() != null) {
            _hashCode += getPO_BOX().hashCode();
        }
        if (getADDRESS_AREA() != null) {
            _hashCode += getADDRESS_AREA().hashCode();
        }
        if (getZIP_CODE() != null) {
            _hashCode += getZIP_CODE().hashCode();
        }
        if (getCOUNTRY() != null) {
            _hashCode += getCOUNTRY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
