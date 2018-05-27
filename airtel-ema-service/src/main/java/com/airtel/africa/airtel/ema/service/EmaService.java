package com.airtel.africa.airtel.ema.service;

import com.airtel.africa.airtel.ema.exceptions.EmaException;
import com.airtel.africa.airtel.ema.helpers.EMAResponse;

public interface EmaService {

	public boolean unbarMsisdn(String msisdn) throws EmaException;

	public EMAResponse unbar(String msisdn);

	public EMAResponse bar(String msisdn);

	public boolean barMsisdnOutgoing(String msisdn) throws EmaException;

	public boolean barMsisdnAll(String msisdn) throws EmaException;

	public EMAResponse unbarForBulk(String msisdn);
	
	public EMAResponse barForBulk(String msisdn);

	public EMAResponse simValidation(String msisdn);
	
}
