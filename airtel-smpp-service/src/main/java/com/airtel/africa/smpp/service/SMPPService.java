package com.airtel.africa.smpp.service;

import java.io.IOException;

import com.airtel.africa.smpp.exceptions.SMPPException;

public interface SMPPService {

	public void sendSMS(String dest, String message) throws SMPPException, IOException;

	public void receiveMessage() throws SMPPException;

}
