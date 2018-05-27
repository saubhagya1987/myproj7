package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Data implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String locationCheck;

    private String msisdn;

    public String getLocationCheck ()
    {
        return locationCheck;
    }

    public void setLocationCheck (String locationCheck)
    {
        this.locationCheck = locationCheck;
    }

    public String getMsisdn ()
    {
        return msisdn;
    }

    public void setMsisdn (String msisdn)
    {
        this.msisdn = msisdn;
    }

    
}
