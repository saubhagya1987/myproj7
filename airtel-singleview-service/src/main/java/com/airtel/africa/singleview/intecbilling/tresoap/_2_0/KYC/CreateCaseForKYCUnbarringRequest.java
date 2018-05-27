/**
 * CreateCaseForKYCUnbarringRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC;

public class CreateCaseForKYCUnbarringRequest  implements java.io.Serializable {
    private java.lang.String PRIORITY;

    private java.lang.String SOURCE;

    private java.lang.String SERVICENAME;

    private java.math.BigInteger CUST_ACCT;

    private java.math.BigInteger TYPE;

    private java.math.BigInteger WORK_GROUP;

    private java.math.BigInteger ESCALATION;

    private org.apache.axis.types.Duration DURATION;

    private java.lang.String COMMENT;

    public CreateCaseForKYCUnbarringRequest() {
    }

    public CreateCaseForKYCUnbarringRequest(
           java.lang.String PRIORITY,
           java.lang.String SOURCE,
           java.lang.String SERVICENAME,
           java.math.BigInteger CUST_ACCT,
           java.math.BigInteger TYPE,
           java.math.BigInteger WORK_GROUP,
           java.math.BigInteger ESCALATION,
           org.apache.axis.types.Duration DURATION,
           java.lang.String COMMENT) {
           this.PRIORITY = PRIORITY;
           this.SOURCE = SOURCE;
           this.SERVICENAME = SERVICENAME;
           this.CUST_ACCT = CUST_ACCT;
           this.TYPE = TYPE;
           this.WORK_GROUP = WORK_GROUP;
           this.ESCALATION = ESCALATION;
           this.DURATION = DURATION;
           this.COMMENT = COMMENT;
    }


    /**
     * Gets the PRIORITY value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return PRIORITY
     */
    public java.lang.String getPRIORITY() {
        return PRIORITY;
    }


    /**
     * Sets the PRIORITY value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param PRIORITY
     */
    public void setPRIORITY(java.lang.String PRIORITY) {
        this.PRIORITY = PRIORITY;
    }


    /**
     * Gets the SOURCE value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return SOURCE
     */
    public java.lang.String getSOURCE() {
        return SOURCE;
    }


    /**
     * Sets the SOURCE value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param SOURCE
     */
    public void setSOURCE(java.lang.String SOURCE) {
        this.SOURCE = SOURCE;
    }


    /**
     * Gets the SERVICENAME value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return SERVICENAME
     */
    public java.lang.String getSERVICENAME() {
        return SERVICENAME;
    }


    /**
     * Sets the SERVICENAME value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param SERVICENAME
     */
    public void setSERVICENAME(java.lang.String SERVICENAME) {
        this.SERVICENAME = SERVICENAME;
    }


    /**
     * Gets the CUST_ACCT value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return CUST_ACCT
     */
    public java.math.BigInteger getCUST_ACCT() {
        return CUST_ACCT;
    }


    /**
     * Sets the CUST_ACCT value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param CUST_ACCT
     */
    public void setCUST_ACCT(java.math.BigInteger CUST_ACCT) {
        this.CUST_ACCT = CUST_ACCT;
    }


    /**
     * Gets the TYPE value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return TYPE
     */
    public java.math.BigInteger getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param TYPE
     */
    public void setTYPE(java.math.BigInteger TYPE) {
        this.TYPE = TYPE;
    }


    /**
     * Gets the WORK_GROUP value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return WORK_GROUP
     */
    public java.math.BigInteger getWORK_GROUP() {
        return WORK_GROUP;
    }


    /**
     * Sets the WORK_GROUP value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param WORK_GROUP
     */
    public void setWORK_GROUP(java.math.BigInteger WORK_GROUP) {
        this.WORK_GROUP = WORK_GROUP;
    }


    /**
     * Gets the ESCALATION value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return ESCALATION
     */
    public java.math.BigInteger getESCALATION() {
        return ESCALATION;
    }


    /**
     * Sets the ESCALATION value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param ESCALATION
     */
    public void setESCALATION(java.math.BigInteger ESCALATION) {
        this.ESCALATION = ESCALATION;
    }


    /**
     * Gets the DURATION value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return DURATION
     */
    public org.apache.axis.types.Duration getDURATION() {
        return DURATION;
    }


    /**
     * Sets the DURATION value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param DURATION
     */
    public void setDURATION(org.apache.axis.types.Duration DURATION) {
        this.DURATION = DURATION;
    }


    /**
     * Gets the COMMENT value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @return COMMENT
     */
    public java.lang.String getCOMMENT() {
        return COMMENT;
    }


    /**
     * Sets the COMMENT value for this CreateCaseForKYCUnbarringRequest.
     * 
     * @param COMMENT
     */
    public void setCOMMENT(java.lang.String COMMENT) {
        this.COMMENT = COMMENT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateCaseForKYCUnbarringRequest)) return false;
        CreateCaseForKYCUnbarringRequest other = (CreateCaseForKYCUnbarringRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PRIORITY==null && other.getPRIORITY()==null) || 
             (this.PRIORITY!=null &&
              this.PRIORITY.equals(other.getPRIORITY()))) &&
            ((this.SOURCE==null && other.getSOURCE()==null) || 
             (this.SOURCE!=null &&
              this.SOURCE.equals(other.getSOURCE()))) &&
            ((this.SERVICENAME==null && other.getSERVICENAME()==null) || 
             (this.SERVICENAME!=null &&
              this.SERVICENAME.equals(other.getSERVICENAME()))) &&
            ((this.CUST_ACCT==null && other.getCUST_ACCT()==null) || 
             (this.CUST_ACCT!=null &&
              this.CUST_ACCT.equals(other.getCUST_ACCT()))) &&
            ((this.TYPE==null && other.getTYPE()==null) || 
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE()))) &&
            ((this.WORK_GROUP==null && other.getWORK_GROUP()==null) || 
             (this.WORK_GROUP!=null &&
              this.WORK_GROUP.equals(other.getWORK_GROUP()))) &&
            ((this.ESCALATION==null && other.getESCALATION()==null) || 
             (this.ESCALATION!=null &&
              this.ESCALATION.equals(other.getESCALATION()))) &&
            ((this.DURATION==null && other.getDURATION()==null) || 
             (this.DURATION!=null &&
              this.DURATION.equals(other.getDURATION()))) &&
            ((this.COMMENT==null && other.getCOMMENT()==null) || 
             (this.COMMENT!=null &&
              this.COMMENT.equals(other.getCOMMENT())));
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
        if (getPRIORITY() != null) {
            _hashCode += getPRIORITY().hashCode();
        }
        if (getSOURCE() != null) {
            _hashCode += getSOURCE().hashCode();
        }
        if (getSERVICENAME() != null) {
            _hashCode += getSERVICENAME().hashCode();
        }
        if (getCUST_ACCT() != null) {
            _hashCode += getCUST_ACCT().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        if (getWORK_GROUP() != null) {
            _hashCode += getWORK_GROUP().hashCode();
        }
        if (getESCALATION() != null) {
            _hashCode += getESCALATION().hashCode();
        }
        if (getDURATION() != null) {
            _hashCode += getDURATION().hashCode();
        }
        if (getCOMMENT() != null) {
            _hashCode += getCOMMENT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
