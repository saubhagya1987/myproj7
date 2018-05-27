package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Roles  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;

    public String getAuthority ()
    {
        return authority;
    }

    public void setAuthority (String authority)
    {
        this.authority = authority;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [authority = "+authority+"]";
    }

}
