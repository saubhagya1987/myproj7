/**
 * ModificationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.comviva.services;

public class ModificationRequest  implements java.io.Serializable {
    private java.lang.String ACCOUNTNUMBER;

    private java.lang.String ACCOUNTTYPE;

    private java.lang.String ADDRESS;

    private java.lang.String ADDRESSPROOF;

    private java.lang.String APIPASSWORD;

    private java.lang.String APIUSERNAME;

    private java.lang.String BIRTHPLACE;

    private java.lang.String CITY;

    private java.lang.String CONTACT_NUMBER;

    private java.lang.String COUNTRY;

    private java.lang.String CUSTOMERID;

    private java.lang.String DISTRICT;

    private java.lang.String DOB;

    private java.lang.String EMPLOYERNAME;

    private java.lang.String EXPIREDDATE;

    private java.lang.String FNAME;

    private java.lang.String FORMNUMBER;

    private java.lang.String GENDER;

    private java.lang.String ICCID;

    private java.lang.String IDENTIFICATION_NUMBER;

    private java.lang.String IDENTITYPROOF;

    private java.lang.String IDEXPIRED;

    private java.lang.String IDNUMBER;

    private java.lang.String IDTYPE;

    private java.lang.String ISSUEDDATE;

    private java.lang.String ISSUED_COUNTRYCODE;

    private java.lang.String IS_MSISDN_ACTIVATED;

    private java.lang.String LANGUAGE;

    private java.lang.String LNAME;

    private java.lang.String MIDDLENAME;

    private java.lang.String MSISDN;

    private java.lang.String NATIONALITY;

    private java.lang.String NICKNAME;

    private java.lang.String OFFNET_MSISDN;

    private java.lang.String PHOTOPROOF;

    private java.lang.String PLACEOF_ID_ISSUED;

    private java.lang.String POSTALCODE;

    private java.lang.String POSTPAIDINTEGRATION;

    private java.lang.String PRIMARYACCOUNT;

    private java.lang.String REGION;

    private java.lang.String REGTYPEID;

    private java.lang.String RESIDENCYCOUNTRYCODE;

    private java.lang.String STATE;

    private java.lang.String STATUS;

    private java.lang.String TYPE;

    private java.lang.String WALLETTYPE;

    private java.lang.String namePrefix;

    private java.lang.String CARD_ID;

    public ModificationRequest() {
    }

    public ModificationRequest(
           java.lang.String ACCOUNTNUMBER,
           java.lang.String ACCOUNTTYPE,
           java.lang.String ADDRESS,
           java.lang.String ADDRESSPROOF,
           java.lang.String APIPASSWORD,
           java.lang.String APIUSERNAME,
           java.lang.String BIRTHPLACE,
           java.lang.String CITY,
           java.lang.String CONTACT_NUMBER,
           java.lang.String COUNTRY,
           java.lang.String CUSTOMERID,
           java.lang.String DISTRICT,
           java.lang.String DOB,
           java.lang.String EMPLOYERNAME,
           java.lang.String EXPIREDDATE,
           java.lang.String FNAME,
           java.lang.String FORMNUMBER,
           java.lang.String GENDER,
           java.lang.String ICCID,
           java.lang.String IDENTIFICATION_NUMBER,
           java.lang.String IDENTITYPROOF,
           java.lang.String IDEXPIRED,
           java.lang.String IDNUMBER,
           java.lang.String IDTYPE,
           java.lang.String ISSUEDDATE,
           java.lang.String ISSUED_COUNTRYCODE,
           java.lang.String IS_MSISDN_ACTIVATED,
           java.lang.String LANGUAGE,
           java.lang.String LNAME,
           java.lang.String MIDDLENAME,
           java.lang.String MSISDN,
           java.lang.String NATIONALITY,
           java.lang.String NICKNAME,
           java.lang.String OFFNET_MSISDN,
           java.lang.String PHOTOPROOF,
           java.lang.String PLACEOF_ID_ISSUED,
           java.lang.String POSTALCODE,
           java.lang.String POSTPAIDINTEGRATION,
           java.lang.String PRIMARYACCOUNT,
           java.lang.String REGION,
           java.lang.String REGTYPEID,
           java.lang.String RESIDENCYCOUNTRYCODE,
           java.lang.String STATE,
           java.lang.String STATUS,
           java.lang.String TYPE,
           java.lang.String WALLETTYPE,
           java.lang.String namePrefix,
           java.lang.String CARD_ID) {
           this.ACCOUNTNUMBER = ACCOUNTNUMBER;
           this.ACCOUNTTYPE = ACCOUNTTYPE;
           this.ADDRESS = ADDRESS;
           this.ADDRESSPROOF = ADDRESSPROOF;
           this.APIPASSWORD = APIPASSWORD;
           this.APIUSERNAME = APIUSERNAME;
           this.BIRTHPLACE = BIRTHPLACE;
           this.CITY = CITY;
           this.CONTACT_NUMBER = CONTACT_NUMBER;
           this.COUNTRY = COUNTRY;
           this.CUSTOMERID = CUSTOMERID;
           this.DISTRICT = DISTRICT;
           this.DOB = DOB;
           this.EMPLOYERNAME = EMPLOYERNAME;
           this.EXPIREDDATE = EXPIREDDATE;
           this.FNAME = FNAME;
           this.FORMNUMBER = FORMNUMBER;
           this.GENDER = GENDER;
           this.ICCID = ICCID;
           this.IDENTIFICATION_NUMBER = IDENTIFICATION_NUMBER;
           this.IDENTITYPROOF = IDENTITYPROOF;
           this.IDEXPIRED = IDEXPIRED;
           this.IDNUMBER = IDNUMBER;
           this.IDTYPE = IDTYPE;
           this.ISSUEDDATE = ISSUEDDATE;
           this.ISSUED_COUNTRYCODE = ISSUED_COUNTRYCODE;
           this.IS_MSISDN_ACTIVATED = IS_MSISDN_ACTIVATED;
           this.LANGUAGE = LANGUAGE;
           this.LNAME = LNAME;
           this.MIDDLENAME = MIDDLENAME;
           this.MSISDN = MSISDN;
           this.NATIONALITY = NATIONALITY;
           this.NICKNAME = NICKNAME;
           this.OFFNET_MSISDN = OFFNET_MSISDN;
           this.PHOTOPROOF = PHOTOPROOF;
           this.PLACEOF_ID_ISSUED = PLACEOF_ID_ISSUED;
           this.POSTALCODE = POSTALCODE;
           this.POSTPAIDINTEGRATION = POSTPAIDINTEGRATION;
           this.PRIMARYACCOUNT = PRIMARYACCOUNT;
           this.REGION = REGION;
           this.REGTYPEID = REGTYPEID;
           this.RESIDENCYCOUNTRYCODE = RESIDENCYCOUNTRYCODE;
           this.STATE = STATE;
           this.STATUS = STATUS;
           this.TYPE = TYPE;
           this.WALLETTYPE = WALLETTYPE;
           this.namePrefix = namePrefix;
           this.CARD_ID = CARD_ID;
    }


    /**
     * Gets the ACCOUNTNUMBER value for this ModificationRequest.
     * 
     * @return ACCOUNTNUMBER
     */
    public java.lang.String getACCOUNTNUMBER() {
        return ACCOUNTNUMBER;
    }


    /**
     * Sets the ACCOUNTNUMBER value for this ModificationRequest.
     * 
     * @param ACCOUNTNUMBER
     */
    public void setACCOUNTNUMBER(java.lang.String ACCOUNTNUMBER) {
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
    }


    /**
     * Gets the ACCOUNTTYPE value for this ModificationRequest.
     * 
     * @return ACCOUNTTYPE
     */
    public java.lang.String getACCOUNTTYPE() {
        return ACCOUNTTYPE;
    }


    /**
     * Sets the ACCOUNTTYPE value for this ModificationRequest.
     * 
     * @param ACCOUNTTYPE
     */
    public void setACCOUNTTYPE(java.lang.String ACCOUNTTYPE) {
        this.ACCOUNTTYPE = ACCOUNTTYPE;
    }


    /**
     * Gets the ADDRESS value for this ModificationRequest.
     * 
     * @return ADDRESS
     */
    public java.lang.String getADDRESS() {
        return ADDRESS;
    }


    /**
     * Sets the ADDRESS value for this ModificationRequest.
     * 
     * @param ADDRESS
     */
    public void setADDRESS(java.lang.String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }


    /**
     * Gets the ADDRESSPROOF value for this ModificationRequest.
     * 
     * @return ADDRESSPROOF
     */
    public java.lang.String getADDRESSPROOF() {
        return ADDRESSPROOF;
    }


    /**
     * Sets the ADDRESSPROOF value for this ModificationRequest.
     * 
     * @param ADDRESSPROOF
     */
    public void setADDRESSPROOF(java.lang.String ADDRESSPROOF) {
        this.ADDRESSPROOF = ADDRESSPROOF;
    }


    /**
     * Gets the APIPASSWORD value for this ModificationRequest.
     * 
     * @return APIPASSWORD
     */
    public java.lang.String getAPIPASSWORD() {
        return APIPASSWORD;
    }


    /**
     * Sets the APIPASSWORD value for this ModificationRequest.
     * 
     * @param APIPASSWORD
     */
    public void setAPIPASSWORD(java.lang.String APIPASSWORD) {
        this.APIPASSWORD = APIPASSWORD;
    }


    /**
     * Gets the APIUSERNAME value for this ModificationRequest.
     * 
     * @return APIUSERNAME
     */
    public java.lang.String getAPIUSERNAME() {
        return APIUSERNAME;
    }


    /**
     * Sets the APIUSERNAME value for this ModificationRequest.
     * 
     * @param APIUSERNAME
     */
    public void setAPIUSERNAME(java.lang.String APIUSERNAME) {
        this.APIUSERNAME = APIUSERNAME;
    }


    /**
     * Gets the BIRTHPLACE value for this ModificationRequest.
     * 
     * @return BIRTHPLACE
     */
    public java.lang.String getBIRTHPLACE() {
        return BIRTHPLACE;
    }


    /**
     * Sets the BIRTHPLACE value for this ModificationRequest.
     * 
     * @param BIRTHPLACE
     */
    public void setBIRTHPLACE(java.lang.String BIRTHPLACE) {
        this.BIRTHPLACE = BIRTHPLACE;
    }


    /**
     * Gets the CITY value for this ModificationRequest.
     * 
     * @return CITY
     */
    public java.lang.String getCITY() {
        return CITY;
    }


    /**
     * Sets the CITY value for this ModificationRequest.
     * 
     * @param CITY
     */
    public void setCITY(java.lang.String CITY) {
        this.CITY = CITY;
    }


    /**
     * Gets the CONTACT_NUMBER value for this ModificationRequest.
     * 
     * @return CONTACT_NUMBER
     */
    public java.lang.String getCONTACT_NUMBER() {
        return CONTACT_NUMBER;
    }


    /**
     * Sets the CONTACT_NUMBER value for this ModificationRequest.
     * 
     * @param CONTACT_NUMBER
     */
    public void setCONTACT_NUMBER(java.lang.String CONTACT_NUMBER) {
        this.CONTACT_NUMBER = CONTACT_NUMBER;
    }


    /**
     * Gets the COUNTRY value for this ModificationRequest.
     * 
     * @return COUNTRY
     */
    public java.lang.String getCOUNTRY() {
        return COUNTRY;
    }


    /**
     * Sets the COUNTRY value for this ModificationRequest.
     * 
     * @param COUNTRY
     */
    public void setCOUNTRY(java.lang.String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }


    /**
     * Gets the CUSTOMERID value for this ModificationRequest.
     * 
     * @return CUSTOMERID
     */
    public java.lang.String getCUSTOMERID() {
        return CUSTOMERID;
    }


    /**
     * Sets the CUSTOMERID value for this ModificationRequest.
     * 
     * @param CUSTOMERID
     */
    public void setCUSTOMERID(java.lang.String CUSTOMERID) {
        this.CUSTOMERID = CUSTOMERID;
    }


    /**
     * Gets the DISTRICT value for this ModificationRequest.
     * 
     * @return DISTRICT
     */
    public java.lang.String getDISTRICT() {
        return DISTRICT;
    }


    /**
     * Sets the DISTRICT value for this ModificationRequest.
     * 
     * @param DISTRICT
     */
    public void setDISTRICT(java.lang.String DISTRICT) {
        this.DISTRICT = DISTRICT;
    }


    /**
     * Gets the DOB value for this ModificationRequest.
     * 
     * @return DOB
     */
    public java.lang.String getDOB() {
        return DOB;
    }


    /**
     * Sets the DOB value for this ModificationRequest.
     * 
     * @param DOB
     */
    public void setDOB(java.lang.String DOB) {
        this.DOB = DOB;
    }


    /**
     * Gets the EMPLOYERNAME value for this ModificationRequest.
     * 
     * @return EMPLOYERNAME
     */
    public java.lang.String getEMPLOYERNAME() {
        return EMPLOYERNAME;
    }


    /**
     * Sets the EMPLOYERNAME value for this ModificationRequest.
     * 
     * @param EMPLOYERNAME
     */
    public void setEMPLOYERNAME(java.lang.String EMPLOYERNAME) {
        this.EMPLOYERNAME = EMPLOYERNAME;
    }


    /**
     * Gets the EXPIREDDATE value for this ModificationRequest.
     * 
     * @return EXPIREDDATE
     */
    public java.lang.String getEXPIREDDATE() {
        return EXPIREDDATE;
    }


    /**
     * Sets the EXPIREDDATE value for this ModificationRequest.
     * 
     * @param EXPIREDDATE
     */
    public void setEXPIREDDATE(java.lang.String EXPIREDDATE) {
        this.EXPIREDDATE = EXPIREDDATE;
    }


    /**
     * Gets the FNAME value for this ModificationRequest.
     * 
     * @return FNAME
     */
    public java.lang.String getFNAME() {
        return FNAME;
    }


    /**
     * Sets the FNAME value for this ModificationRequest.
     * 
     * @param FNAME
     */
    public void setFNAME(java.lang.String FNAME) {
        this.FNAME = FNAME;
    }


    /**
     * Gets the FORMNUMBER value for this ModificationRequest.
     * 
     * @return FORMNUMBER
     */
    public java.lang.String getFORMNUMBER() {
        return FORMNUMBER;
    }


    /**
     * Sets the FORMNUMBER value for this ModificationRequest.
     * 
     * @param FORMNUMBER
     */
    public void setFORMNUMBER(java.lang.String FORMNUMBER) {
        this.FORMNUMBER = FORMNUMBER;
    }


    /**
     * Gets the GENDER value for this ModificationRequest.
     * 
     * @return GENDER
     */
    public java.lang.String getGENDER() {
        return GENDER;
    }


    /**
     * Sets the GENDER value for this ModificationRequest.
     * 
     * @param GENDER
     */
    public void setGENDER(java.lang.String GENDER) {
        this.GENDER = GENDER;
    }


    /**
     * Gets the ICCID value for this ModificationRequest.
     * 
     * @return ICCID
     */
    public java.lang.String getICCID() {
        return ICCID;
    }


    /**
     * Sets the ICCID value for this ModificationRequest.
     * 
     * @param ICCID
     */
    public void setICCID(java.lang.String ICCID) {
        this.ICCID = ICCID;
    }


    /**
     * Gets the IDENTIFICATION_NUMBER value for this ModificationRequest.
     * 
     * @return IDENTIFICATION_NUMBER
     */
    public java.lang.String getIDENTIFICATION_NUMBER() {
        return IDENTIFICATION_NUMBER;
    }


    /**
     * Sets the IDENTIFICATION_NUMBER value for this ModificationRequest.
     * 
     * @param IDENTIFICATION_NUMBER
     */
    public void setIDENTIFICATION_NUMBER(java.lang.String IDENTIFICATION_NUMBER) {
        this.IDENTIFICATION_NUMBER = IDENTIFICATION_NUMBER;
    }


    /**
     * Gets the IDENTITYPROOF value for this ModificationRequest.
     * 
     * @return IDENTITYPROOF
     */
    public java.lang.String getIDENTITYPROOF() {
        return IDENTITYPROOF;
    }


    /**
     * Sets the IDENTITYPROOF value for this ModificationRequest.
     * 
     * @param IDENTITYPROOF
     */
    public void setIDENTITYPROOF(java.lang.String IDENTITYPROOF) {
        this.IDENTITYPROOF = IDENTITYPROOF;
    }


    /**
     * Gets the IDEXPIRED value for this ModificationRequest.
     * 
     * @return IDEXPIRED
     */
    public java.lang.String getIDEXPIRED() {
        return IDEXPIRED;
    }


    /**
     * Sets the IDEXPIRED value for this ModificationRequest.
     * 
     * @param IDEXPIRED
     */
    public void setIDEXPIRED(java.lang.String IDEXPIRED) {
        this.IDEXPIRED = IDEXPIRED;
    }


    /**
     * Gets the IDNUMBER value for this ModificationRequest.
     * 
     * @return IDNUMBER
     */
    public java.lang.String getIDNUMBER() {
        return IDNUMBER;
    }


    /**
     * Sets the IDNUMBER value for this ModificationRequest.
     * 
     * @param IDNUMBER
     */
    public void setIDNUMBER(java.lang.String IDNUMBER) {
        this.IDNUMBER = IDNUMBER;
    }


    /**
     * Gets the IDTYPE value for this ModificationRequest.
     * 
     * @return IDTYPE
     */
    public java.lang.String getIDTYPE() {
        return IDTYPE;
    }


    /**
     * Sets the IDTYPE value for this ModificationRequest.
     * 
     * @param IDTYPE
     */
    public void setIDTYPE(java.lang.String IDTYPE) {
        this.IDTYPE = IDTYPE;
    }


    /**
     * Gets the ISSUEDDATE value for this ModificationRequest.
     * 
     * @return ISSUEDDATE
     */
    public java.lang.String getISSUEDDATE() {
        return ISSUEDDATE;
    }


    /**
     * Sets the ISSUEDDATE value for this ModificationRequest.
     * 
     * @param ISSUEDDATE
     */
    public void setISSUEDDATE(java.lang.String ISSUEDDATE) {
        this.ISSUEDDATE = ISSUEDDATE;
    }


    /**
     * Gets the ISSUED_COUNTRYCODE value for this ModificationRequest.
     * 
     * @return ISSUED_COUNTRYCODE
     */
    public java.lang.String getISSUED_COUNTRYCODE() {
        return ISSUED_COUNTRYCODE;
    }


    /**
     * Sets the ISSUED_COUNTRYCODE value for this ModificationRequest.
     * 
     * @param ISSUED_COUNTRYCODE
     */
    public void setISSUED_COUNTRYCODE(java.lang.String ISSUED_COUNTRYCODE) {
        this.ISSUED_COUNTRYCODE = ISSUED_COUNTRYCODE;
    }


    /**
     * Gets the IS_MSISDN_ACTIVATED value for this ModificationRequest.
     * 
     * @return IS_MSISDN_ACTIVATED
     */
    public java.lang.String getIS_MSISDN_ACTIVATED() {
        return IS_MSISDN_ACTIVATED;
    }


    /**
     * Sets the IS_MSISDN_ACTIVATED value for this ModificationRequest.
     * 
     * @param IS_MSISDN_ACTIVATED
     */
    public void setIS_MSISDN_ACTIVATED(java.lang.String IS_MSISDN_ACTIVATED) {
        this.IS_MSISDN_ACTIVATED = IS_MSISDN_ACTIVATED;
    }


    /**
     * Gets the LANGUAGE value for this ModificationRequest.
     * 
     * @return LANGUAGE
     */
    public java.lang.String getLANGUAGE() {
        return LANGUAGE;
    }


    /**
     * Sets the LANGUAGE value for this ModificationRequest.
     * 
     * @param LANGUAGE
     */
    public void setLANGUAGE(java.lang.String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }


    /**
     * Gets the LNAME value for this ModificationRequest.
     * 
     * @return LNAME
     */
    public java.lang.String getLNAME() {
        return LNAME;
    }


    /**
     * Sets the LNAME value for this ModificationRequest.
     * 
     * @param LNAME
     */
    public void setLNAME(java.lang.String LNAME) {
        this.LNAME = LNAME;
    }


    /**
     * Gets the MIDDLENAME value for this ModificationRequest.
     * 
     * @return MIDDLENAME
     */
    public java.lang.String getMIDDLENAME() {
        return MIDDLENAME;
    }


    /**
     * Sets the MIDDLENAME value for this ModificationRequest.
     * 
     * @param MIDDLENAME
     */
    public void setMIDDLENAME(java.lang.String MIDDLENAME) {
        this.MIDDLENAME = MIDDLENAME;
    }


    /**
     * Gets the MSISDN value for this ModificationRequest.
     * 
     * @return MSISDN
     */
    public java.lang.String getMSISDN() {
        return MSISDN;
    }


    /**
     * Sets the MSISDN value for this ModificationRequest.
     * 
     * @param MSISDN
     */
    public void setMSISDN(java.lang.String MSISDN) {
        this.MSISDN = MSISDN;
    }


    /**
     * Gets the NATIONALITY value for this ModificationRequest.
     * 
     * @return NATIONALITY
     */
    public java.lang.String getNATIONALITY() {
        return NATIONALITY;
    }


    /**
     * Sets the NATIONALITY value for this ModificationRequest.
     * 
     * @param NATIONALITY
     */
    public void setNATIONALITY(java.lang.String NATIONALITY) {
        this.NATIONALITY = NATIONALITY;
    }


    /**
     * Gets the NICKNAME value for this ModificationRequest.
     * 
     * @return NICKNAME
     */
    public java.lang.String getNICKNAME() {
        return NICKNAME;
    }


    /**
     * Sets the NICKNAME value for this ModificationRequest.
     * 
     * @param NICKNAME
     */
    public void setNICKNAME(java.lang.String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }


    /**
     * Gets the OFFNET_MSISDN value for this ModificationRequest.
     * 
     * @return OFFNET_MSISDN
     */
    public java.lang.String getOFFNET_MSISDN() {
        return OFFNET_MSISDN;
    }


    /**
     * Sets the OFFNET_MSISDN value for this ModificationRequest.
     * 
     * @param OFFNET_MSISDN
     */
    public void setOFFNET_MSISDN(java.lang.String OFFNET_MSISDN) {
        this.OFFNET_MSISDN = OFFNET_MSISDN;
    }


    /**
     * Gets the PHOTOPROOF value for this ModificationRequest.
     * 
     * @return PHOTOPROOF
     */
    public java.lang.String getPHOTOPROOF() {
        return PHOTOPROOF;
    }


    /**
     * Sets the PHOTOPROOF value for this ModificationRequest.
     * 
     * @param PHOTOPROOF
     */
    public void setPHOTOPROOF(java.lang.String PHOTOPROOF) {
        this.PHOTOPROOF = PHOTOPROOF;
    }


    /**
     * Gets the PLACEOF_ID_ISSUED value for this ModificationRequest.
     * 
     * @return PLACEOF_ID_ISSUED
     */
    public java.lang.String getPLACEOF_ID_ISSUED() {
        return PLACEOF_ID_ISSUED;
    }


    /**
     * Sets the PLACEOF_ID_ISSUED value for this ModificationRequest.
     * 
     * @param PLACEOF_ID_ISSUED
     */
    public void setPLACEOF_ID_ISSUED(java.lang.String PLACEOF_ID_ISSUED) {
        this.PLACEOF_ID_ISSUED = PLACEOF_ID_ISSUED;
    }


    /**
     * Gets the POSTALCODE value for this ModificationRequest.
     * 
     * @return POSTALCODE
     */
    public java.lang.String getPOSTALCODE() {
        return POSTALCODE;
    }


    /**
     * Sets the POSTALCODE value for this ModificationRequest.
     * 
     * @param POSTALCODE
     */
    public void setPOSTALCODE(java.lang.String POSTALCODE) {
        this.POSTALCODE = POSTALCODE;
    }


    /**
     * Gets the POSTPAIDINTEGRATION value for this ModificationRequest.
     * 
     * @return POSTPAIDINTEGRATION
     */
    public java.lang.String getPOSTPAIDINTEGRATION() {
        return POSTPAIDINTEGRATION;
    }


    /**
     * Sets the POSTPAIDINTEGRATION value for this ModificationRequest.
     * 
     * @param POSTPAIDINTEGRATION
     */
    public void setPOSTPAIDINTEGRATION(java.lang.String POSTPAIDINTEGRATION) {
        this.POSTPAIDINTEGRATION = POSTPAIDINTEGRATION;
    }


    /**
     * Gets the PRIMARYACCOUNT value for this ModificationRequest.
     * 
     * @return PRIMARYACCOUNT
     */
    public java.lang.String getPRIMARYACCOUNT() {
        return PRIMARYACCOUNT;
    }


    /**
     * Sets the PRIMARYACCOUNT value for this ModificationRequest.
     * 
     * @param PRIMARYACCOUNT
     */
    public void setPRIMARYACCOUNT(java.lang.String PRIMARYACCOUNT) {
        this.PRIMARYACCOUNT = PRIMARYACCOUNT;
    }


    /**
     * Gets the REGION value for this ModificationRequest.
     * 
     * @return REGION
     */
    public java.lang.String getREGION() {
        return REGION;
    }


    /**
     * Sets the REGION value for this ModificationRequest.
     * 
     * @param REGION
     */
    public void setREGION(java.lang.String REGION) {
        this.REGION = REGION;
    }


    /**
     * Gets the REGTYPEID value for this ModificationRequest.
     * 
     * @return REGTYPEID
     */
    public java.lang.String getREGTYPEID() {
        return REGTYPEID;
    }


    /**
     * Sets the REGTYPEID value for this ModificationRequest.
     * 
     * @param REGTYPEID
     */
    public void setREGTYPEID(java.lang.String REGTYPEID) {
        this.REGTYPEID = REGTYPEID;
    }


    /**
     * Gets the RESIDENCYCOUNTRYCODE value for this ModificationRequest.
     * 
     * @return RESIDENCYCOUNTRYCODE
     */
    public java.lang.String getRESIDENCYCOUNTRYCODE() {
        return RESIDENCYCOUNTRYCODE;
    }


    /**
     * Sets the RESIDENCYCOUNTRYCODE value for this ModificationRequest.
     * 
     * @param RESIDENCYCOUNTRYCODE
     */
    public void setRESIDENCYCOUNTRYCODE(java.lang.String RESIDENCYCOUNTRYCODE) {
        this.RESIDENCYCOUNTRYCODE = RESIDENCYCOUNTRYCODE;
    }


    /**
     * Gets the STATE value for this ModificationRequest.
     * 
     * @return STATE
     */
    public java.lang.String getSTATE() {
        return STATE;
    }


    /**
     * Sets the STATE value for this ModificationRequest.
     * 
     * @param STATE
     */
    public void setSTATE(java.lang.String STATE) {
        this.STATE = STATE;
    }


    /**
     * Gets the STATUS value for this ModificationRequest.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this ModificationRequest.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     * Gets the TYPE value for this ModificationRequest.
     * 
     * @return TYPE
     */
    public java.lang.String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this ModificationRequest.
     * 
     * @param TYPE
     */
    public void setTYPE(java.lang.String TYPE) {
        this.TYPE = TYPE;
    }


    /**
     * Gets the WALLETTYPE value for this ModificationRequest.
     * 
     * @return WALLETTYPE
     */
    public java.lang.String getWALLETTYPE() {
        return WALLETTYPE;
    }


    /**
     * Sets the WALLETTYPE value for this ModificationRequest.
     * 
     * @param WALLETTYPE
     */
    public void setWALLETTYPE(java.lang.String WALLETTYPE) {
        this.WALLETTYPE = WALLETTYPE;
    }


    /**
     * Gets the namePrefix value for this ModificationRequest.
     * 
     * @return namePrefix
     */
    public java.lang.String getNamePrefix() {
        return namePrefix;
    }


    /**
     * Sets the namePrefix value for this ModificationRequest.
     * 
     * @param namePrefix
     */
    public void setNamePrefix(java.lang.String namePrefix) {
        this.namePrefix = namePrefix;
    }


    /**
     * Gets the CARD_ID value for this ModificationRequest.
     * 
     * @return CARD_ID
     */
    public java.lang.String getCARD_ID() {
        return CARD_ID;
    }


    /**
     * Sets the CARD_ID value for this ModificationRequest.
     * 
     * @param CARD_ID
     */
    public void setCARD_ID(java.lang.String CARD_ID) {
        this.CARD_ID = CARD_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ModificationRequest)) return false;
        ModificationRequest other = (ModificationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ACCOUNTNUMBER==null && other.getACCOUNTNUMBER()==null) || 
             (this.ACCOUNTNUMBER!=null &&
              this.ACCOUNTNUMBER.equals(other.getACCOUNTNUMBER()))) &&
            ((this.ACCOUNTTYPE==null && other.getACCOUNTTYPE()==null) || 
             (this.ACCOUNTTYPE!=null &&
              this.ACCOUNTTYPE.equals(other.getACCOUNTTYPE()))) &&
            ((this.ADDRESS==null && other.getADDRESS()==null) || 
             (this.ADDRESS!=null &&
              this.ADDRESS.equals(other.getADDRESS()))) &&
            ((this.ADDRESSPROOF==null && other.getADDRESSPROOF()==null) || 
             (this.ADDRESSPROOF!=null &&
              this.ADDRESSPROOF.equals(other.getADDRESSPROOF()))) &&
            ((this.APIPASSWORD==null && other.getAPIPASSWORD()==null) || 
             (this.APIPASSWORD!=null &&
              this.APIPASSWORD.equals(other.getAPIPASSWORD()))) &&
            ((this.APIUSERNAME==null && other.getAPIUSERNAME()==null) || 
             (this.APIUSERNAME!=null &&
              this.APIUSERNAME.equals(other.getAPIUSERNAME()))) &&
            ((this.BIRTHPLACE==null && other.getBIRTHPLACE()==null) || 
             (this.BIRTHPLACE!=null &&
              this.BIRTHPLACE.equals(other.getBIRTHPLACE()))) &&
            ((this.CITY==null && other.getCITY()==null) || 
             (this.CITY!=null &&
              this.CITY.equals(other.getCITY()))) &&
            ((this.CONTACT_NUMBER==null && other.getCONTACT_NUMBER()==null) || 
             (this.CONTACT_NUMBER!=null &&
              this.CONTACT_NUMBER.equals(other.getCONTACT_NUMBER()))) &&
            ((this.COUNTRY==null && other.getCOUNTRY()==null) || 
             (this.COUNTRY!=null &&
              this.COUNTRY.equals(other.getCOUNTRY()))) &&
            ((this.CUSTOMERID==null && other.getCUSTOMERID()==null) || 
             (this.CUSTOMERID!=null &&
              this.CUSTOMERID.equals(other.getCUSTOMERID()))) &&
            ((this.DISTRICT==null && other.getDISTRICT()==null) || 
             (this.DISTRICT!=null &&
              this.DISTRICT.equals(other.getDISTRICT()))) &&
            ((this.DOB==null && other.getDOB()==null) || 
             (this.DOB!=null &&
              this.DOB.equals(other.getDOB()))) &&
            ((this.EMPLOYERNAME==null && other.getEMPLOYERNAME()==null) || 
             (this.EMPLOYERNAME!=null &&
              this.EMPLOYERNAME.equals(other.getEMPLOYERNAME()))) &&
            ((this.EXPIREDDATE==null && other.getEXPIREDDATE()==null) || 
             (this.EXPIREDDATE!=null &&
              this.EXPIREDDATE.equals(other.getEXPIREDDATE()))) &&
            ((this.FNAME==null && other.getFNAME()==null) || 
             (this.FNAME!=null &&
              this.FNAME.equals(other.getFNAME()))) &&
            ((this.FORMNUMBER==null && other.getFORMNUMBER()==null) || 
             (this.FORMNUMBER!=null &&
              this.FORMNUMBER.equals(other.getFORMNUMBER()))) &&
            ((this.GENDER==null && other.getGENDER()==null) || 
             (this.GENDER!=null &&
              this.GENDER.equals(other.getGENDER()))) &&
            ((this.ICCID==null && other.getICCID()==null) || 
             (this.ICCID!=null &&
              this.ICCID.equals(other.getICCID()))) &&
            ((this.IDENTIFICATION_NUMBER==null && other.getIDENTIFICATION_NUMBER()==null) || 
             (this.IDENTIFICATION_NUMBER!=null &&
              this.IDENTIFICATION_NUMBER.equals(other.getIDENTIFICATION_NUMBER()))) &&
            ((this.IDENTITYPROOF==null && other.getIDENTITYPROOF()==null) || 
             (this.IDENTITYPROOF!=null &&
              this.IDENTITYPROOF.equals(other.getIDENTITYPROOF()))) &&
            ((this.IDEXPIRED==null && other.getIDEXPIRED()==null) || 
             (this.IDEXPIRED!=null &&
              this.IDEXPIRED.equals(other.getIDEXPIRED()))) &&
            ((this.IDNUMBER==null && other.getIDNUMBER()==null) || 
             (this.IDNUMBER!=null &&
              this.IDNUMBER.equals(other.getIDNUMBER()))) &&
            ((this.IDTYPE==null && other.getIDTYPE()==null) || 
             (this.IDTYPE!=null &&
              this.IDTYPE.equals(other.getIDTYPE()))) &&
            ((this.ISSUEDDATE==null && other.getISSUEDDATE()==null) || 
             (this.ISSUEDDATE!=null &&
              this.ISSUEDDATE.equals(other.getISSUEDDATE()))) &&
            ((this.ISSUED_COUNTRYCODE==null && other.getISSUED_COUNTRYCODE()==null) || 
             (this.ISSUED_COUNTRYCODE!=null &&
              this.ISSUED_COUNTRYCODE.equals(other.getISSUED_COUNTRYCODE()))) &&
            ((this.IS_MSISDN_ACTIVATED==null && other.getIS_MSISDN_ACTIVATED()==null) || 
             (this.IS_MSISDN_ACTIVATED!=null &&
              this.IS_MSISDN_ACTIVATED.equals(other.getIS_MSISDN_ACTIVATED()))) &&
            ((this.LANGUAGE==null && other.getLANGUAGE()==null) || 
             (this.LANGUAGE!=null &&
              this.LANGUAGE.equals(other.getLANGUAGE()))) &&
            ((this.LNAME==null && other.getLNAME()==null) || 
             (this.LNAME!=null &&
              this.LNAME.equals(other.getLNAME()))) &&
            ((this.MIDDLENAME==null && other.getMIDDLENAME()==null) || 
             (this.MIDDLENAME!=null &&
              this.MIDDLENAME.equals(other.getMIDDLENAME()))) &&
            ((this.MSISDN==null && other.getMSISDN()==null) || 
             (this.MSISDN!=null &&
              this.MSISDN.equals(other.getMSISDN()))) &&
            ((this.NATIONALITY==null && other.getNATIONALITY()==null) || 
             (this.NATIONALITY!=null &&
              this.NATIONALITY.equals(other.getNATIONALITY()))) &&
            ((this.NICKNAME==null && other.getNICKNAME()==null) || 
             (this.NICKNAME!=null &&
              this.NICKNAME.equals(other.getNICKNAME()))) &&
            ((this.OFFNET_MSISDN==null && other.getOFFNET_MSISDN()==null) || 
             (this.OFFNET_MSISDN!=null &&
              this.OFFNET_MSISDN.equals(other.getOFFNET_MSISDN()))) &&
            ((this.PHOTOPROOF==null && other.getPHOTOPROOF()==null) || 
             (this.PHOTOPROOF!=null &&
              this.PHOTOPROOF.equals(other.getPHOTOPROOF()))) &&
            ((this.PLACEOF_ID_ISSUED==null && other.getPLACEOF_ID_ISSUED()==null) || 
             (this.PLACEOF_ID_ISSUED!=null &&
              this.PLACEOF_ID_ISSUED.equals(other.getPLACEOF_ID_ISSUED()))) &&
            ((this.POSTALCODE==null && other.getPOSTALCODE()==null) || 
             (this.POSTALCODE!=null &&
              this.POSTALCODE.equals(other.getPOSTALCODE()))) &&
            ((this.POSTPAIDINTEGRATION==null && other.getPOSTPAIDINTEGRATION()==null) || 
             (this.POSTPAIDINTEGRATION!=null &&
              this.POSTPAIDINTEGRATION.equals(other.getPOSTPAIDINTEGRATION()))) &&
            ((this.PRIMARYACCOUNT==null && other.getPRIMARYACCOUNT()==null) || 
             (this.PRIMARYACCOUNT!=null &&
              this.PRIMARYACCOUNT.equals(other.getPRIMARYACCOUNT()))) &&
            ((this.REGION==null && other.getREGION()==null) || 
             (this.REGION!=null &&
              this.REGION.equals(other.getREGION()))) &&
            ((this.REGTYPEID==null && other.getREGTYPEID()==null) || 
             (this.REGTYPEID!=null &&
              this.REGTYPEID.equals(other.getREGTYPEID()))) &&
            ((this.RESIDENCYCOUNTRYCODE==null && other.getRESIDENCYCOUNTRYCODE()==null) || 
             (this.RESIDENCYCOUNTRYCODE!=null &&
              this.RESIDENCYCOUNTRYCODE.equals(other.getRESIDENCYCOUNTRYCODE()))) &&
            ((this.STATE==null && other.getSTATE()==null) || 
             (this.STATE!=null &&
              this.STATE.equals(other.getSTATE()))) &&
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS()))) &&
            ((this.TYPE==null && other.getTYPE()==null) || 
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE()))) &&
            ((this.WALLETTYPE==null && other.getWALLETTYPE()==null) || 
             (this.WALLETTYPE!=null &&
              this.WALLETTYPE.equals(other.getWALLETTYPE()))) &&
            ((this.namePrefix==null && other.getNamePrefix()==null) || 
             (this.namePrefix!=null &&
              this.namePrefix.equals(other.getNamePrefix()))) &&
            ((this.CARD_ID==null && other.getCARD_ID()==null) || 
             (this.CARD_ID!=null &&
              this.CARD_ID.equals(other.getCARD_ID())));
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
        if (getACCOUNTNUMBER() != null) {
            _hashCode += getACCOUNTNUMBER().hashCode();
        }
        if (getACCOUNTTYPE() != null) {
            _hashCode += getACCOUNTTYPE().hashCode();
        }
        if (getADDRESS() != null) {
            _hashCode += getADDRESS().hashCode();
        }
        if (getADDRESSPROOF() != null) {
            _hashCode += getADDRESSPROOF().hashCode();
        }
        if (getAPIPASSWORD() != null) {
            _hashCode += getAPIPASSWORD().hashCode();
        }
        if (getAPIUSERNAME() != null) {
            _hashCode += getAPIUSERNAME().hashCode();
        }
        if (getBIRTHPLACE() != null) {
            _hashCode += getBIRTHPLACE().hashCode();
        }
        if (getCITY() != null) {
            _hashCode += getCITY().hashCode();
        }
        if (getCONTACT_NUMBER() != null) {
            _hashCode += getCONTACT_NUMBER().hashCode();
        }
        if (getCOUNTRY() != null) {
            _hashCode += getCOUNTRY().hashCode();
        }
        if (getCUSTOMERID() != null) {
            _hashCode += getCUSTOMERID().hashCode();
        }
        if (getDISTRICT() != null) {
            _hashCode += getDISTRICT().hashCode();
        }
        if (getDOB() != null) {
            _hashCode += getDOB().hashCode();
        }
        if (getEMPLOYERNAME() != null) {
            _hashCode += getEMPLOYERNAME().hashCode();
        }
        if (getEXPIREDDATE() != null) {
            _hashCode += getEXPIREDDATE().hashCode();
        }
        if (getFNAME() != null) {
            _hashCode += getFNAME().hashCode();
        }
        if (getFORMNUMBER() != null) {
            _hashCode += getFORMNUMBER().hashCode();
        }
        if (getGENDER() != null) {
            _hashCode += getGENDER().hashCode();
        }
        if (getICCID() != null) {
            _hashCode += getICCID().hashCode();
        }
        if (getIDENTIFICATION_NUMBER() != null) {
            _hashCode += getIDENTIFICATION_NUMBER().hashCode();
        }
        if (getIDENTITYPROOF() != null) {
            _hashCode += getIDENTITYPROOF().hashCode();
        }
        if (getIDEXPIRED() != null) {
            _hashCode += getIDEXPIRED().hashCode();
        }
        if (getIDNUMBER() != null) {
            _hashCode += getIDNUMBER().hashCode();
        }
        if (getIDTYPE() != null) {
            _hashCode += getIDTYPE().hashCode();
        }
        if (getISSUEDDATE() != null) {
            _hashCode += getISSUEDDATE().hashCode();
        }
        if (getISSUED_COUNTRYCODE() != null) {
            _hashCode += getISSUED_COUNTRYCODE().hashCode();
        }
        if (getIS_MSISDN_ACTIVATED() != null) {
            _hashCode += getIS_MSISDN_ACTIVATED().hashCode();
        }
        if (getLANGUAGE() != null) {
            _hashCode += getLANGUAGE().hashCode();
        }
        if (getLNAME() != null) {
            _hashCode += getLNAME().hashCode();
        }
        if (getMIDDLENAME() != null) {
            _hashCode += getMIDDLENAME().hashCode();
        }
        if (getMSISDN() != null) {
            _hashCode += getMSISDN().hashCode();
        }
        if (getNATIONALITY() != null) {
            _hashCode += getNATIONALITY().hashCode();
        }
        if (getNICKNAME() != null) {
            _hashCode += getNICKNAME().hashCode();
        }
        if (getOFFNET_MSISDN() != null) {
            _hashCode += getOFFNET_MSISDN().hashCode();
        }
        if (getPHOTOPROOF() != null) {
            _hashCode += getPHOTOPROOF().hashCode();
        }
        if (getPLACEOF_ID_ISSUED() != null) {
            _hashCode += getPLACEOF_ID_ISSUED().hashCode();
        }
        if (getPOSTALCODE() != null) {
            _hashCode += getPOSTALCODE().hashCode();
        }
        if (getPOSTPAIDINTEGRATION() != null) {
            _hashCode += getPOSTPAIDINTEGRATION().hashCode();
        }
        if (getPRIMARYACCOUNT() != null) {
            _hashCode += getPRIMARYACCOUNT().hashCode();
        }
        if (getREGION() != null) {
            _hashCode += getREGION().hashCode();
        }
        if (getREGTYPEID() != null) {
            _hashCode += getREGTYPEID().hashCode();
        }
        if (getRESIDENCYCOUNTRYCODE() != null) {
            _hashCode += getRESIDENCYCOUNTRYCODE().hashCode();
        }
        if (getSTATE() != null) {
            _hashCode += getSTATE().hashCode();
        }
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        if (getWALLETTYPE() != null) {
            _hashCode += getWALLETTYPE().hashCode();
        }
        if (getNamePrefix() != null) {
            _hashCode += getNamePrefix().hashCode();
        }
        if (getCARD_ID() != null) {
            _hashCode += getCARD_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
