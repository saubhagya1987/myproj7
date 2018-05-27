package com.airtel.kyc.integration.serviceimpl;

import java.io.IOException;
import java.util.Calendar;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.africa.airtel.ema.exceptions.EmaException;
import com.airtel.africa.airtel.ema.helpers.EMAResponse;
import com.airtel.africa.airtel.ema.service.EmaService;
import com.airtel.africa.am.helpers.AMResponse;
import com.airtel.africa.am.helpers.AMUserDTO;
import com.airtel.africa.am.services.AirtelMoneyService;
//import com.airtel.africa.airtel.money.exceptions.AitelMoneyException;
//import com.airtel.africa.airtel.money.helpers.AMBarUnbarRequest;
//import com.airtel.africa.airtel.money.helpers.AMResponse;
//import com.airtel.africa.airtel.money.service.AirtelMoneyService;
import com.airtel.africa.email.service.EmailService;
//import com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq;
//import com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS;
//import com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON;
import com.airtel.africa.singleview.exceptions.SingleviewException;
import com.airtel.africa.singleview.helpers.SVResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.ReconnectBarringRequest;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReq;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqADDRESS;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsReqPERSON;
import com.airtel.africa.singleview.service.SingleviewService;
import com.airtel.africa.smpp.exceptions.SMPPException;
import com.airtel.africa.smpp.service.SMPPService;
import com.airtel.africa.ssf.service.SSFService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.SubscriberDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberIdDetailsDto;
import com.airtel.kyc.helpers.response.NotificationResponse;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.integration.exception.IntegrationServiceExceptionCodes;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.dao.AddressDao;
import com.airtel.kyc.repository.dao.CountryDao;
import com.airtel.kyc.repository.dao.DistrictDao;
import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.SubscriberIdDetails;
import com.airtel.kyc.utils.DateUtils;

@Service
public class IntegrationServiceImpl implements IntegrationService {

	private static Logger logger = Logger.getLogger(IntegrationServiceImpl.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();
	
	@Autowired
	private SingleviewService singleviewService;

	/*@Autowired
	private AirtelMoneyService airtelMoneyService;*/
	
	@Autowired
	private AirtelMoneyService airtelMoneyService;

	/*@Autowired
	private HPService hpService;*/
	
	@Autowired
	private EmaService emaService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private SSFService ssfService;

	@Autowired
	private SMPPService smppService;
	
	@Autowired
	private AppConstants appConstants;
	
	@Autowired
	SubscriberDetailsDao  subscriberDetailsDao;
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	CountryDao countryDao;
	
	@Autowired
	DistrictDao districtDao;

	//private static Log log = LogFactory.getLog(IntegrationServiceImpl.class);

	@Override
	public void notify(String msisdn, String message) throws IntegrationServiceException, IOException {
		try {
			smppService.sendSMS(msisdn, message);
		} catch (SMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public NotificationResponse notify(NotificationDto notificationDto) throws IntegrationServiceException, IOException {
		if (notificationDto == null && (notificationDto.getEmail() == null || notificationDto.getMsisdn() == null)) {
			throw new IntegrationServiceException(IntegrationServiceExceptionCodes.NOTIFY_MSISDN_EMAIL_NOT_PROVIDED);
		}
		NotificationResponse notificationResponse = new NotificationResponse();
		// trigger sms
		if (notificationDto.getMsisdn() != null) {
			String sms = "";
			if (notificationDto.getTemplateContent() != null) {
				sms = notificationDto.getTemplateContent();
				
				logger.debug("SMS triggering for msisdn : "+notificationDto.getMsisdn()+"  sms : "+sms);
				if(sms != null) {
					try {
						this.smppService.sendSMS(notificationDto.getMsisdn(), sms);
						logger.debug("SMS triggered status : success");
					} catch (SMPPException e) {
						logger.error("SMS triggered status : failure");
						notificationResponse.setExceptionOccured(true);
						e.printStackTrace();
					}
					catch (Exception e) {
						logger.error("SMS triggered status : failure");
						notificationResponse.setExceptionOccured(true);
						e.printStackTrace();
					}
				}
			}
		}
		// trigger mail
		if (notificationDto.getEmail() != null) {
			this.emailService.sendTemplateInEmail(notificationDto.getEmail(), notificationDto.getSender(),
					notificationDto.getSubject(), notificationDto.getTemplateName(), notificationDto.getMap(), null);
		}
		return notificationResponse;
	}

	@Override
	public SVResponse updateSingleView(SubscriberDto subscriberDto) throws SingleviewException{
		SVResponse response = new SVResponse();
		SubscriberDetailsDto detailsDto = (subscriberDto.getSubscriberDetails() != null
				&& !subscriberDto.getSubscriberDetails().isEmpty()) ? subscriberDto.getSubscriberDetails().get(0)
						: null;

		UpdateCustomerDemographicsReq customerDemographicsReq = new UpdateCustomerDemographicsReq();
		
		customerDemographicsReq.setSUBSCRIBER_NO(subscriberDto.getMsisdn());
		customerDemographicsReq.setRETAILER_MOBILE_NO(appConstants.retailerNo);
		
		UpdateCustomerDemographicsReqPERSON demographicsReqPERSON = new UpdateCustomerDemographicsReqPERSON();
		if (detailsDto != null) {
			demographicsReqPERSON.setFIRST_NAME(detailsDto.getFirstName());
			demographicsReqPERSON.setLAST_NAME(detailsDto.getLastName());
			demographicsReqPERSON.setMIDDLE_NAME(detailsDto.getMiddleName());
			SubscriberIdDetailsDto idDetailsDto = (detailsDto.getSubscriberIdDetails() != null
					&& !detailsDto.getSubscriberIdDetails().isEmpty())
							? detailsDto.getSubscriberIdDetails().iterator().next() : null;
			if (idDetailsDto != null) {
				//demographicsReqPERSON.setID_TYPE(idDetailsDto.getIdType());
				demographicsReqPERSON.setID_TYPE("NA");
				demographicsReqPERSON.setID_NUMBER(idDetailsDto.getIdNumber());
			}
			UpdateCustomerDemographicsReqADDRESS address = new UpdateCustomerDemographicsReqADDRESS();
			logger.debug("updateSingleView --detailsDto.getAddress() "+detailsDto.getAddress());
			logger.debug("updateSingleView --detailsDto.getAddress().getCountry() "+detailsDto.getAddress().getCountry());
			logger.debug("updateSingleView --detailsDto.getPostalAddress() "+detailsDto.getPostalAddress());
			if (detailsDto.getAddress() != null && detailsDto.getAddress().getCountry() != null
					&& detailsDto.getAddress().getCountry().getCountryName() != null){
				address.setCOUNTRY(detailsDto.getAddress().getCountry().getCountryName());
			}
			address.setPOST_AREA(detailsDto.getPostalAddress());
			logger.debug("updateSingleView --detailsDto.getAddress().getAddress() "+detailsDto.getAddress().getAddress());
			if (detailsDto.getAddress() != null && detailsDto.getAddress().getAddress() != null){
				address.setADDRS_BLOCK(detailsDto.getAddress().getAddress());
			}
			demographicsReqPERSON.setADDRESS(address);
		}
		customerDemographicsReq.setENTRY_DATE(Calendar.getInstance());
		customerDemographicsReq.setCONTACT(demographicsReqPERSON);
			return this.singleviewService.updateDemographicsDetails(customerDemographicsReq);
	}

	@Override
	public SVResponse updateSingleViewSubsDetails(SubscriberDetails subscriberDetail) throws SingleviewException{
		SVResponse response = new SVResponse();
		/*SubscriberDetailsDto detailsDto = (subscriberDto.getSubscriberDetails() != null
				&& !subscriberDto.getSubscriberDetails().isEmpty()) ? subscriberDto.getSubscriberDetails().get(0)
						: null;*/

		UpdateCustomerDemographicsReq customerDemographicsReq = new UpdateCustomerDemographicsReq();
		
		customerDemographicsReq.setSUBSCRIBER_NO(subscriberDetail.getMsisdn());
		customerDemographicsReq.setRETAILER_MOBILE_NO(appConstants.retailerNo);
		
		UpdateCustomerDemographicsReqPERSON demographicsReqPERSON = new UpdateCustomerDemographicsReqPERSON();
		if (subscriberDetail != null) {
			demographicsReqPERSON.setFIRST_NAME(subscriberDetail.getFirstName());
			demographicsReqPERSON.setLAST_NAME(subscriberDetail.getLastName());
			demographicsReqPERSON.setMIDDLE_NAME(subscriberDetail.getMiddleName());
			SubscriberIdDetails idDetails = (subscriberDetail.getSubscriberIdDetails() != null
					&& !subscriberDetail.getSubscriberIdDetails().isEmpty())
							? subscriberDetail.getSubscriberIdDetails().iterator().next() : null;
			if (idDetails != null) {
				//Commented due to ID type mismatch in singleview and airtel money
				//demographicsReqPERSON.setID_TYPE(idDetails.getIdType());
				demographicsReqPERSON.setID_TYPE("NA");
				demographicsReqPERSON.setID_NUMBER(idDetails.getIdNumber());
			}
			UpdateCustomerDemographicsReqADDRESS address = new UpdateCustomerDemographicsReqADDRESS();
			logger.debug("updateSingleView --detailsDto.getAddress() "+subscriberDetail.getAddress());
			logger.debug("updateSingleView --detailsDto.getAddress().getCountry() "+subscriberDetail.getAddress().getCountryId());
			logger.debug("updateSingleView --detailsDto.getPostalAddress() "+subscriberDetail.getPostalAddress());
			if (subscriberDetail.getAddress() != null && subscriberDetail.getAddress() != null
					&& subscriberDetail.getAddress().getCountryId() != null){
				if("ZM".equals(subscriberDetail.getNationality()) || "ZAMBIA".equals(subscriberDetail.getNationality())){
					address.setCOUNTRY("ZAMBIA");
				}
				else{
					address.setCOUNTRY("OTHER");
				}
				address.setADDRESS_AREA(subscriberDetail.getAddress().getPermanentAddress());
			}
			address.setPOST_AREA(subscriberDetail.getPostalAddress());
			logger.debug("updateSingleView --detailsDto.getAddress().getAddress() "+subscriberDetail.getAddress().getAddress());
			if (subscriberDetail.getAddress() != null && subscriberDetail.getAddress().getAddress() != null){
				address.setADDRS_BLOCK(subscriberDetail.getAddress().getAddress());
			}
			demographicsReqPERSON.setADDRESS(address);
		}
		customerDemographicsReq.setENTRY_DATE(Calendar.getInstance());
		customerDemographicsReq.setCONTACT(demographicsReqPERSON);
			return this.singleviewService.updateDemographicsDetails(customerDemographicsReq);
	}

	@Override
	public AMResponse updateOnAM(SubscriberDto subscriberDto){
		
		
		SubscriberDetailsDto detailsDto = (subscriberDto.getSubscriberDetails() != null
				&& !subscriberDto.getSubscriberDetails().isEmpty()) ? subscriberDto.getSubscriberDetails().get(0)
						: null;
		// update demographic airtel money
		AMUserDTO userDTO = new AMUserDTO();
		if (detailsDto != null) {
			SubscriberDetails subscriberDetails=subscriberDetailsDao.getSubscriberDetails(subscriberDto.getMsisdn(), KycConstants.FALSE, KycConstants.PENDING_STATUS);
			logger.debug(" subscriberDetails"+subscriberDetails);
			//Address address= addressDao.getAddress(subscriberDetails.getAddress().getAddressId());
			//Country country=countryDao.getCountryByCountryId(address.getCountryId());
			//District district=districtDao.findByDistrictId(address.getDistrictId());
			
			userDTO.setFNAME(detailsDto.getFirstName());
			logger.debug(" detailsDto"+detailsDto.getFirstName());
			userDTO.setLNAME(detailsDto.getLastName());
			logger.debug(" detailsDto.getLastName()"+detailsDto.getLastName());
			logger.debug(" detailsDto.getGender()"+detailsDto.getGender());
			String gender = detailsDto.getGender();
			if("male".equalsIgnoreCase(detailsDto.getGender())){
				gender = "Male";
			} else if("female".equalsIgnoreCase(detailsDto.getGender())){
				gender = "Female";
			}
			userDTO.setNATIONALITY(detailsDto.getNationality());
			logger.debug(" detailsDto.getNationality()"+detailsDto.getNationality());
			userDTO.setGENDER(gender);
			userDTO.setMSISDN(subscriberDto.getMsisdn());
			logger.debug(" detailsDto.getDateOfBirth()"+detailsDto.getDateOfBirth());
			if(detailsDto != null && detailsDto.getDateOfBirth() != null) 
				//userDTO.setDOB(new SimpleDateFormat("ddMMYYYY").format(detailsDto.getDateOfBirth()));
				userDTO.setDOB(DateUtils.getDateConvertedFormat(detailsDto.getDateOfBirth(), "dd-MM-yy", "ddMMYYYY"));
			userDTO.setREGTYPEID("FULL_KYC");
			userDTO.setTYPE("REGST");
			SubscriberIdDetailsDto idDetailsDto =detailsDto.getSubscriberIdDetails().iterator().next();
			logger.debug(" idDetailsDto"+idDetailsDto);
			userDTO.setADDRESS(detailsDto.getAddress().getPermanentAddress());
			logger.debug(" detailsDto.getAddress().getPermanentAddress()"+detailsDto.getAddress().getPermanentAddress());
			//userDTO.setCOUNTRY(country.getCountryName());
			//userDTO.setDISTRICT(district.getDistrictName());
			if("DRIVING_LICENCE".equals(idDetailsDto.getIdType())){
				userDTO.setIDTYPE("DRIVER_CARD");
			}
			else{
				userDTO.setIDTYPE(idDetailsDto.getIdType());
			}
			
			logger.debug(" idDetailsDto.getIdType()"+idDetailsDto.getIdType());
			userDTO.setIDNUMBER(idDetailsDto.getIdNumber());
			logger.debug(" idDetailsDto.getIdType()"+idDetailsDto.getIdNumber());
			
			//userDTO.setLANGUAGE("6");
		}
		return this.airtelMoneyService.registerAM(userDTO);
	}

	public Boolean authenticateUser(String userName, String userPassword) {
		return ssfService.authenticateUser(userName, userPassword);
	}

	/*public HPResponse unbarMSISDN(String msisdn, String serial) throws HPException {
		return hpService.unbarMSISDN(msisdn, serial);
	}

	public HPResponse barMSISDN(String msisdn, String serial) throws HPException {
		return hpService.barMSISDN(msisdn, serial);
	}

	public HPResponse putOnHotLine(String msisdn, String serial) throws HPException {
		return hpService.putOnHotLine(msisdn, serial);
	}*/

	/*public SVResponse getMSISDNStatus(String msisdn) throws SingleviewException {
		return singleviewService.getMSISDNStatus(msisdn);
	}*/
	
	/*public SVResponse getIMSI(String msisdn) throws SingleviewException{
		return singleviewService.getIMSI(msisdn);
	}*/
	
	@Override
	public SVResponse activateOnSingleView(String msisdn) throws SingleviewException{
		ReconnectBarringRequest barringRequest = new ReconnectBarringRequest();
		barringRequest.setPSubno(msisdn);
		barringRequest.setPUsername("KYC");
		barringRequest.setPReasoncode("1");
		barringRequest.setPSndcmd("N");
		return this.singleviewService.reconnectBarringRequest(barringRequest);
	}
	@Override
	public boolean barOnEMA(String msisdn) throws IntegrationServiceException {
		if (msisdn == null) {
			throw new IntegrationServiceException(IntegrationServiceExceptionCodes.MSISDN_NOT_PROVIDED);
		}
		try {
			return emaService.barMsisdnOutgoing(msisdn);
		} catch (EmaException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean barOnEMAALL(String msisdn) throws IntegrationServiceException {
		if (msisdn == null) {
			throw new IntegrationServiceException(IntegrationServiceExceptionCodes.MSISDN_NOT_PROVIDED);
		}
		try {
			return emaService.barMsisdnAll(msisdn);
		} catch (EmaException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean unbarOnEMA(String msisdn) throws IntegrationServiceException {
		if (msisdn == null) {
			throw new IntegrationServiceException(IntegrationServiceExceptionCodes.MSISDN_NOT_PROVIDED);
		}
		try {
			return emaService.unbarMsisdn(msisdn);
		} catch (EmaException e) {
			// e.printStackTrace();
			throw new IntegrationServiceException(IntegrationServiceExceptionCodes.MSISDN_NOT_PROVIDED);
		}
		// return false;
	}
	

	@Override
	public EMAResponse unbar(String msisdn) {
		return this.emaService.unbar(msisdn);
	}
		
	@Override
	public EMAResponse unbarforBulk(String msisdn) {
		return this.emaService.unbarForBulk(msisdn);
	}

	@Override
	public EMAResponse bar(String msisdn) {
		return this.emaService.bar(msisdn);
	}

	@Override
	public EMAResponse simValidation(String msisdn) {
		return this.emaService.simValidation(msisdn);
	}
	

}
