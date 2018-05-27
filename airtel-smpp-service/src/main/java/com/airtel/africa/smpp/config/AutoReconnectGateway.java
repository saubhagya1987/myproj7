package com.airtel.africa.smpp.config;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.BasicConfigurator;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.SessionState;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.session.Session;
import org.jsmpp.session.SessionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.airtel.africa.smpp.helpers.MessageReceiverListenerImpl;
import com.airtel.africa.smpp.helpers.SMPPConstants;

/**
 * This are implementation of {@link Gateway}. This gateway will reconnect for a
 * specified interval if the session are closed.
 * 
 * @author Narotam
 * 
 */
@Component
//@Qualifier("smsGateway")
//@PropertySource({ "classpath:smpp.properties" })
@PropertySource(value = {"file:/africa_agile/opt/kyc_zm/smpp.properties"})
//@PropertySource(value = {"file:/C:/Users/user1/Desktop/kyc_zm/encyption/smpp.properties"})
public class AutoReconnectGateway implements SMPPConstants {

	private static final Logger logger = LoggerFactory.getLogger(AutoReconnectGateway.class);

	@Autowired
	private Environment environment;

	private static SMPPSession session = null;
	private static BindParameter bindParam;
	private static long reconnectInterval = 5000L; // 5 seconds

	private static String remoteIpAddress;
	private static int remotePort;
	private static String SMSC_USER_NAME;
	private static String SMSC_PWD;
	private static String SYSTEM_TYPE;
	private static String SOURCE;
	private static String ISD_CODE;

	@PostConstruct
	public void init() {
		try {
			logger.info("***** Initilazing SMPP session ***** ");

			remoteIpAddress = environment.getProperty(SMSC_HOST);
			remotePort = Integer.parseInt(environment.getProperty(SMSC_PORT));
			SMSC_USER_NAME = environment.getProperty(SMSC_USER);
			SMSC_PWD = environment.getProperty(SMSC_PASSWORD);
			SYSTEM_TYPE = environment.getProperty(SMSC_SYSTEM_TYPE);
			SOURCE = environment.getProperty(SMSC_SOURCE); 
			ISD_CODE = environment.getProperty(COUNTRY_CODE);
			
			bindParam = new BindParameter(BindType.BIND_TX, SMSC_USER_NAME, SMSC_PWD, SYSTEM_TYPE,
					TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.UNKNOWN, null);

			logger.info("***** Initilazing SMPP session ***** ISD_CODE "+ISD_CODE);
			logger.info("***** Initilazing SMPP session ***** SOURCE "+SOURCE);
			
		} catch (Exception ex) {
			logger.error(" ***** Exception ***** " + ex.getMessage());
		}
	}

	@PreDestroy
	public void destroy() {
		logger.info("***** Destroying SMPP session  ***** ");
		if (session != null) {
			session.close();
		}
	}

	/**
	 * Create new {@link SMPPSession} complete with the
	 * {@link SessionStateListenerImpl}.
	 * 
	 * @return the {@link SMPPSession}.
	 * @throws IOException
	 *             if the creation of new session failed.
	 */
	private void newSession() throws IOException {
		BasicConfigurator.configure();
		session = new SMPPSession();
		session.connectAndBind(remoteIpAddress, remotePort, bindParam);
		session.addSessionStateListener(new SessionStateListenerImpl());
		session.setMessageReceiverListener(new MessageReceiverListenerImpl());
	}

	/**
	 * Get the session. If the session still null or not in bound state, then IO
	 * exception will be thrown.
	 * 
	 * @return the valid session.
	 * @throws IOException
	 *             if there is no valid session or session creation is invalid.
	 */
	public SMPPSession getSession() throws IOException {
		if (session == null) {
			logger.info("Initiate session for the first time to " + remoteIpAddress + ":" + remotePort);
			newSession();
		} else if (!session.getSessionState().isBound()) {
			throw new IOException("We have no valid session yet");
		}
		return session;
	}

	/**
	 * Reconnect session after specified interval.
	 * 
	 * @param timeInMillis
	 *            is the interval.
	 */
	private void reconnectAfter(final long timeInMillis) {
		new Thread() {
			@Override
			public void run() {
				logger.info("Schedule reconnect after " + timeInMillis + " millis");
				try {
					Thread.sleep(timeInMillis);
				} catch (InterruptedException e) {
				}

				int attempt = 0;
				while (session == null || session.getSessionState().equals(SessionState.CLOSED)) {
					try {
						logger.info("Reconnecting attempt #" + (++attempt) + "...");
						newSession();
					} catch (IOException e) {
						logger.error("Failed opening connection and bind to " + remoteIpAddress + ":" + remotePort, e);
						// wait for a second
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ee) {
						}
					}
				}
			}
		}.start();
	}

	/**
	 * This class will receive the notification from {@link SMPPSession} for the
	 * state changes. It will schedule to re-initialize session.
	 * 
	 * @author uudashr
	 * 
	 */
	private class SessionStateListenerImpl implements SessionStateListener {
		public void onStateChange(SessionState newState, SessionState oldState, Session source) {
			if (newState.equals(SessionState.CLOSED)) {
				logger.info("Session closed");
				reconnectAfter(reconnectInterval);
			}
		}
	}
	
	public String getSource(){
		return SOURCE;
	}
	
	public String getISD() throws IOException {
		return ISD_CODE;
	}
}