package com.airtel.africa.smpp.service;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.bean.AlertNotification;
import org.jsmpp.bean.DataSm;
import org.jsmpp.bean.DeliverSm;
import org.jsmpp.bean.DeliveryReceipt;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageType;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.OptionalParameter;
import org.jsmpp.bean.OptionalParameters;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.DataSmResult;
import org.jsmpp.session.MessageReceiverListener;
import org.jsmpp.util.InvalidDeliveryReceiptException;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.africa.smpp.config.AutoReconnectGateway;
import com.airtel.africa.smpp.exceptions.SMPPException;

@Service
public class SMPPServiceImpl implements SMPPService{

	private static final Logger logger = Logger.getLogger(SMPPServiceImpl.class);
	
	//private static TimeFormatter timeFormatter = new AbsoluteTimeFormatter();

	@Autowired
	private AutoReconnectGateway autoReconnectGateway;
	
	/*@Override
	public void sendSMS(String dest, String message) throws SMPPException {
		logger.info("***** sending sendSMS ***** "+dest);
		final RegisteredDelivery registeredDelivery = new RegisteredDelivery();
		registeredDelivery.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.SUCCESS_FAILURE);

		String source = autoReconnectGateway.getSource();
		
		try {
			String MSISDN = autoReconnectGateway.getISD()+dest;
			
			logger.info("***** MSISDN with country code sendSMS ***** "+MSISDN);
			
			String messageId = autoReconnectGateway.getSession().submitShortMessage("CMT",
					TypeOfNumber.ALPHANUMERIC, NumberingPlanIndicator.UNKNOWN, source, TypeOfNumber.INTERNATIONAL,
					NumberingPlanIndicator.UNKNOWN, MSISDN, new ESMClass(), (byte) 0, (byte) 1,
					null, null, registeredDelivery, (byte) 0,
					new GeneralDataCoding(), (byte) 0,
					message.getBytes());
			
			logger.info("***** messageId sendSMS ***** "+messageId);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (PDUException e) {
			e.printStackTrace();
		} catch (ResponseTimeoutException e) {
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			e.printStackTrace();
		} catch (NegativeResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public void sendSMS(String recipientPhoneNumber, String message)
			throws SMPPException, IOException {
		
		logger.info("sendsms recipientPhoneNumber :"+recipientPhoneNumber +"  message:"+ message);

		try {

			String[] msgId = null;
			int splitSize = 153;
			int totalSize = 153;
			int totalSegments = 0;
			String finalNo=autoReconnectGateway.getISD()+recipientPhoneNumber;

			if (message != null && message.length() > totalSize) {
				totalSegments = getTotalSegmentsForTextMessage(message);
			}
			else if(message != null && message.length() <= totalSize){
				totalSegments = 1;
			}

			Random random = new Random();
			OptionalParameter sarMsgRefNum = OptionalParameters
					.newSarMsgRefNum((short) random.nextInt());
			OptionalParameter sarTotalSegments = OptionalParameters
					.newSarTotalSegments(totalSegments);

			String[] segmentData = splitIntoStringArray(message, splitSize,
					totalSegments);

			final RegisteredDelivery registeredDelivery = new RegisteredDelivery();
			registeredDelivery
					.setSMSCDeliveryReceipt(SMSCDeliveryReceipt.SUCCESS_FAILURE);

			msgId = new String[totalSegments];
			logger.info("finalNo = "+finalNo+" ===sendsms totalSegments----------------->>>>> :"+totalSegments);
			for (int i = 0, seqNum = 0; i < totalSegments; i++) {
				
				logger.info("segmentData["+i+"] :"+segmentData[i].getBytes());
				seqNum = i + 1;
				OptionalParameter sarSegmentSeqnum = OptionalParameters
						.newSarSegmentSeqnum(seqNum);

				msgId[i] = autoReconnectGateway
						.getSession()
						.submitShortMessage(
								"CMT",
								TypeOfNumber.ALPHANUMERIC,
								NumberingPlanIndicator.UNKNOWN,
								"AirtelWeb",
								TypeOfNumber.INTERNATIONAL,
								NumberingPlanIndicator.UNKNOWN,
								finalNo,
								new ESMClass(),
								(byte) 0,
								(byte) 1,
								null,
								null,
								registeredDelivery,
								(byte) 0,
								new GeneralDataCoding(), (byte) 0,
								segmentData[i].getBytes(), sarMsgRefNum,
								sarSegmentSeqnum, sarTotalSegments);
				logger.info(" sendsms ********************messageId  ==>   " + segmentData[i].getBytes());
				
				//QuerySmResult qsmRes = AutoReconnectGateway.getInstance().getSession().queryShortMessage(msgId[i], TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, "AirtelWeb");
				//System.out.println("********************sms state  ==>   " + qsmRes.getMessageState());
				
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.jsmpp.PDUException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResponseTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NegativeResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Set listener to receive deliver_sm
		autoReconnectGateway.getSession()
				.setMessageReceiverListener(new MessageReceiverListener() {

					public void onAcceptDeliverSm(DeliverSm deliverSm)
							throws ProcessRequestException {
						if (MessageType.SMSC_DEL_RECEIPT.containedIn(deliverSm
								.getEsmClass())) {
							// delivery receipt
							try {
								DeliveryReceipt delReceipt = deliverSm
										.getShortMessageAsDeliveryReceipt();
								long id = Long.parseLong(delReceipt.getId()) & 0xffffffff;
								String messageId = Long.toString(id, 16)
										.toUpperCase();
								logger.info("Received '" + messageId + "' : "
										+ delReceipt);
							} catch (InvalidDeliveryReceiptException e) {
								logger.info("Receiving faild.");
								e.printStackTrace();
							}
						} else {
							// regular short message
							logger.info("Receiving message : "
									+ new String(deliverSm.getShortMessage()));
						}
					}

					public void onAcceptAlertNotification(
							AlertNotification alertNotification) {
						logger.info("onAcceptAlertNotification");
					}

					public DataSmResult onAcceptDataSm(DataSm dataSm,
							org.jsmpp.session.Session arg1)
							throws ProcessRequestException {
						logger.info("onAcceptDataSm");
						return null;
					}
				});
		//return smsFlag;
	}

	
	@Override
	public void receiveMessage() throws SMPPException {
		/*new Thread() {
			@Override
			public void run(){
				// TODO
			}
		}.start();*/
	}
	
	
	private int getTotalSegmentsForTextMessage(String message) {
		int splitPos = 135;
		int totalsegments = 1;
		if (message.length() > splitPos) {
			totalsegments = (message.length() / splitPos)
					+ ((message.length() % splitPos > 0) ? 1 : 0);
		}
		return totalsegments;
	}

	private String[] splitIntoStringArray(String msg, int pos, int totalSegments) {
		String[] segmentData = new String[totalSegments];
		if (totalSegments > 1) {
			int splitPos = pos;

			int startIndex = 0;

			segmentData[startIndex] = new String();
			segmentData[startIndex] = msg.substring(startIndex, splitPos);

			for (int i = 1; i < totalSegments; i++) {
				segmentData[i] = new String();
				startIndex = splitPos;
				if (msg.length() - startIndex <= pos) {
					segmentData[i] = msg.substring(startIndex, msg.length());
				} else {
					splitPos = startIndex + pos;
					segmentData[i] = msg.substring(startIndex, splitPos);
				}
			}
		}
		else{
			segmentData[0]=msg;
		}
		return segmentData;
	}

}
