package com.airtel.kyc.business.service.impl;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.airtel.africa.airtel.ema.helpers.EMAResponse;
import com.airtel.africa.am.helpers.AMResponse;
//import com.airtel.africa.intecbilling.tresoap._2_0.KYC.GetSIMNumberResponse;
//import com.airtel.africa.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse;
//import com.airtel.africa.intecbilling.tresoap._2_0.KYC.FetchSubscriberSIMSummaryRes;
import com.airtel.africa.singleview.helpers.SVResponse;
import com.airtel.africa.singleview.intecbilling.tresoap._2_0.KYC.UpdateCustomerDemographicsResponse;
import com.airtel.africa.singleview.service.SingleviewService;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.service.HelperDataManagementService;
import com.airtel.kyc.business.service.SubscriberManagementService;
import com.airtel.kyc.business.service.TaskManagementService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ActionCodes;
import com.airtel.kyc.enumData.ExceptionCodes;
import com.airtel.kyc.enumData.TaskEnum;
import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.dao.GKYCStatusDao;
import com.airtel.kyc.repository.dao.KycDaoService;
import com.airtel.kyc.repository.dao.RequestLogDao;
import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.dao.TemplatesDao;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.Templates;
import com.airtel.kyc.repository.entity.task.AgentLog;
import com.airtel.kyc.repository.entity.task.RequestLog;
import com.airtel.kyc.repository.exception.KycDaoException;

@Service
public class TaskManagementServiceImpl implements TaskManagementService, KycConstants {

	private static Logger logger = Logger.getLogger(TaskManagementServiceImpl.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();


	@SuppressWarnings("rawtypes")
	@Qualifier("kycDaoSession")
	@Autowired
	private KycDaoService kycDaoService;
		
	@Autowired
	private SubscriberManagementService subscriberManagementService;

	@Autowired
	private IntegrationService integrationService;
	
	@Autowired
	private HelperDataManagementService dataManagementService;

	@Autowired
	private SubscriberDetailsDao subscriberDetailsDao;
	
	@Autowired
	private SingleviewService singleviewService;
	

	@Autowired
	private RequestLogDao requestLogDao;
	
	@Autowired
	private TemplatesDao templatesDao;

	@Autowired
	private AppConstants appConstants;
	
	@Autowired
	GKYCStatusDao gKYCStatusDao;
	
	@Override
	public Long addTask(int taskId, Long number_id, String kyc_id) throws BusinessException {
		RequestLog request = new RequestLog();
		Long kycId = new Long(kyc_id);
		switch (taskId) {
		
			
		case 0:// @SV_validation : Agent to validate msisdn & sim serial on
			List<RequestLog> lstRLog = getRequestsByKYCnType1(0, kycId);
			if (lstRLog != null && lstRLog.size() > 0) {
				return null;
			} else {
				request.setAgent_id(TaskEnum.SIM_VALIDATION.getTaskCode());
				request.setAgentName(TaskEnum.SIM_VALIDATION.toString());
				request.setReq_due_time(
						new Timestamp(System.currentTimeMillis() + appConstants.sv_validation_colling));
			}
			break;

		case 1:// @EMA_UNBAR : Agent to unbar msisdn on EMA system
			request.setAgentName(TaskEnum.EMA_UNBARING.toString());
			request.setAgent_id(TaskEnum.EMA_UNBARING.getTaskCode());
			request.setReq_due_time(
					new Timestamp(System.currentTimeMillis() + Long.parseLong(appConstants.ema_unbarring_colling)));
			break;
		//@SV Update
		case 2:
			request.setAgentName(TaskEnum.SV_DEMGRAPHIC_UPDATION.toString());
			request.setAgent_id(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode());
			request.setReq_due_time(
					new Timestamp(System.currentTimeMillis() + Long.parseLong(appConstants.sv_update_collingtime)));
			break;
		//@AM create/update
		case 3:
			request.setAgentName(TaskEnum.AM_ACTIVATION.toString());
			request.setAgent_id(TaskEnum.AM_ACTIVATION.getTaskCode());
			request.setReq_due_time(
					new Timestamp(System.currentTimeMillis() + Long.parseLong(appConstants.airtel_money_collingtime)));
			break;
			
		
		case 4:// @EMA_BAR : Agent to BAR MSISDN on EMA system
			request.setAgent_id(TaskEnum.EMA_BARING.getTaskCode());
			request.setAgentName(TaskEnum.EMA_BARING.toString());
			request.setReq_due_time(
					new Timestamp(System.currentTimeMillis() + Long.parseLong(appConstants.ema_barring_colling)));
			break;
			
		case 5:// @EMA_BAR : Agent to BAR MSISDN on EMA system
			request.setAgent_id(TaskEnum.SV_STATUS_UPDATION.getTaskCode());
			request.setAgentName(TaskEnum.SV_STATUS_UPDATION.toString());
			request.setReq_due_time(
					new Timestamp(System.currentTimeMillis() + Long.parseLong(appConstants.sv_update_collingtime)));
			break;
			
		case 6:
			request.setAgent_id(TaskEnum.EMA_UNBARING_FOR_BULK.getTaskCode());
			request.setAgentName(TaskEnum.EMA_UNBARING_FOR_BULK.toString());
			request.setReq_due_time(
					new Timestamp(System.currentTimeMillis() + Long.parseLong(appConstants.ema_unbarring_colling)));
			break;
			
		

		}
		request.setRetry_count(0);
		request.setRequest_by(appConstants.hostname);
		request.setKyc_id(kyc_id);
		request.setNumber_id(number_id);
		request.setRequest_status(THREAD_STATUS_NEW);
		request.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		RequestLog log = saveupdateRequest(request);
		logger.debug("-----RequestLog----"+log);
		return number_id;
	}

	@SuppressWarnings("unchecked")
	public List<RequestLog> getRequestsByKYCnType1(Integer requestType, Long number_id) throws BusinessException {
		List<RequestLog> reqests = null;
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("agent_id", requestType);
		parameterMap.put("number_id", number_id);
		try {
			reqests = this.kycDaoService.findByCriteria(RequestLog.class, parameterMap);
		} catch (KycDaoException e) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		return reqests;
	}

	@Override
	public RequestLog saveupdateRequest(RequestLog request) {
		try {
			logger.info("Executing request..."+request);
			return (RequestLog) this.kycDaoService.saveOrUpdateEntity(request);
		} catch (KycDaoException e) {
			logger.info("KycDaoException..."+e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AgentLog saveupdateAgentRun(AgentLog log) throws BusinessException {
		try {
			return (AgentLog) this.kycDaoService.saveOrUpdateEntity(log);
		} catch (KycDaoException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AgentLog processRequests(AgentLog agent) throws BusinessException {
		logger.debug("Executing processRequests..."+agent.getAgent_id());
		try {
			int jobcount = 0;
			switch (agent.getAgent_id()) {
			
			
			case 0:
				jobcount = 0;
				jobcount = SM_VALIDATION(agent);
				break;

			case 1:
				jobcount = 0;
				jobcount = unBarOnEMA(agent);
				break;

			case 2:
				jobcount = 0;
				jobcount = updateSingleView(agent.getRun_id());
				break;

			case 3:
				jobcount = 0;
				jobcount = activateAirtelMoney(agent.getRun_id());
				break;		

			case 4:
				jobcount = 0;
				jobcount = barOnEMA(agent);
				break;
			
			case 5:
				jobcount = 0;
				jobcount = activateOnSV(agent.getRun_id());
				break;	
				
			case 6:
				jobcount = 0;
				jobcount = unBarOnEMAForBulk(agent);
				break;		

			default:
				break;
			}
			agent.setRun_status("Completed!");
			agent.setRun_info(jobcount + "");
		} catch (Exception e) {
			agent.setRun_status("Failed!");
			agent.setRun_info(e.getMessage());
		}
		return agent;
	}

	

	@SuppressWarnings("unchecked")
	private int SM_VALIDATION(AgentLog agentLog) {
		Long run_id = agentLog.getRun_id();
		//List<RequestLog> requets = agentLog.getRequestLogs();
		String MethodName = "Run id :"+run_id+" SM_VALIDATION:";
		logger.debug(MethodName + " : Started");
		List<RequestLog> requets = null;
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.SIM_VALIDATION.getTaskCode(),
					appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		int jobcount = (requets != null) ? requets.size() : 0;
		if (isDebugEnabled) {
			logger.debug(MethodName + " : Number of jobs to run : " + jobcount);
		}
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				logger.debug(MethodName + number_id + " Processing");
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.ema_validation_retry_count)) {
					
					requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
					
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					/*Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByNamedQuery("Subscriber.findSubscriberById",parameterMap);*/
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					
					if(isDebugEnabled){
						logger.debug(MethodName +" : Subscriber : "+subscriber);
						logger.debug(MethodName+" : ema unbar processing ... ");
					}
					EMAResponse responseEMA = this.integrationService.simValidation(subscriber.getMsisdn());
					
					//Added for testing
					//EMAResponse responseEMA = new EMAResponse();					
					//responseEMA.setUnbar(true);
					//Added for testing
					
					if(isDebugEnabled){
						logger.debug(MethodName +" : EMAResponse : "+responseEMA);
					}
					if (responseEMA != null) {
						if (responseEMA.isExceptionOccured()) {
							logger.debug("Exception occured flag: "+responseEMA.isExceptionOccured());
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.ema_validation_retry_count)));
							requestLog.setError_data(responseEMA.getException().getMessage());
						} else {
							logger.debug("in else block");
							requestLog.setRequest_data(responseEMA.getRequest());
							requestLog.setResponse_data(responseEMA.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							//System.out.println("responseEMA.isUnbar()"+responseEMA.isUnbar());
							//responseEMA.setUnbar(true);
							if (responseEMA.getResponse()!=null) {
								logger.debug("subscriberDetailsList.get(0) 315 responseEMA.getResponse()"+responseEMA.getResponse());
								String  simSerailNo=responseEMA.getResponse();
								String simSerailNoObj[]= simSerailNo.split("SIM,");
								logger.debug("subscriberDetailsList.get(0)318  simSerailNoObj[]"+simSerailNoObj);
								String simNo=simSerailNoObj[1];
								logger.debug("subscriberDetailsList.get(0) 320 simNo"+simNo);
								String finalSimNo=simNo.replaceAll(";", "");
								logger.debug("subscriberDetailsList.get(0) 322 finalSimNo"+finalSimNo);
								System.out.println("finalSimNo:"+finalSimNo);
								String emaLastSimSerialNo = finalSimNo.substring(Math.max(finalSimNo.length() - 5, 0));
								
								logger.debug("subscriberDetailsList.get(0) 370 getSimSerialNumber()"+emaLastSimSerialNo);
								
								String kycLastSimSerialNo = subscriberDetailsList.get(0).getSimSerialNumber().substring(Math.max(subscriberDetailsList.get(0).getSimSerialNumber().length() - 5, 0));
								
								logger.debug("subscriberDetailsList.get(0) 371 getSimSerialNumber()"+kycLastSimSerialNo);
								
								//Added For IMSI FETCH
								String ema=responseEMA.getResponse();
								
								String [] emaArray=ema.split("IMSI,");
								String [] splitArray=(emaArray)[1].split(":");
								logger.debug("subscriberDetailsList.get(0) 329 "+(splitArray)[0]);
								/*logger.debug("subscriberDetailsList.get(0) 372 "+ema);
								String [] emaArray=ema.split(":");
								logger.debug("subscriberDetailsList.get(0) 329 "+(emaArray[3].split(","))[1]);
								String[] imsiArray = emaArray[3].split(",");
								logger.debug("subscriberDetailsList.get(0) 331 "+imsiArray[1]);*/
								
								/*if(imsiArray[1]!=null){		
									logger.debug("subscriberDetailsList.get(0) 334 imsi Fetch started");
									SubscriberDetails details=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnObj(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
									details.setImsiNumber(imsiArray[1]);
									subscriberDetailsDao.update(details);
									logger.debug("subscriberDetailsList.get(0) 338 imsi Fetch ended");
								}*/
								
								if(kycLastSimSerialNo.equals(emaLastSimSerialNo)){
									logger.debug("subscriberDetailsList.get(0) 342 after imsi Fetch");
									logger.debug("subscriberDetailsList.get(0) 343 in if block sim serial no"+ subscriberDetailsList.get(0).getSimSerialNumber());
								/*subscriberDetailsList.get(0).setHpActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setPartialActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PENDING.getActionDescription());
								subscriberDetailsList.get(0).setFinalStatusReason("EMA_UNBAR_TRUE : unbared on ema.");
								subscriberDetailsList.get(0).setWorkFlowStatus("EMA_UNBAR_TRUE");
								subscriberDetailsList.get(0).setWorkFlowReason("unbared on ema successful.");
								this.kycDaoService.saveOrUpdateEntity(subscriber);		*/	
									
									if(splitArray[0]!=null){		
										logger.debug("subscriberDetailsList.get(0) 334 imsi Fetch started");
										
										subscriberDetailsList.get(0).setImsiNumber(splitArray[0]);
										
										logger.debug("subscriberDetailsList.get(0) 338 imsi Fetch ended");
									}
									logger.debug("subscriberDetailsList.get(0) 352 in else block");
									Timestamp time = new Timestamp(System.currentTimeMillis());
									subscriberDetailsList.get(0).setWorkFlowStatus("SIM_VALIDATION_TRUE");
									subscriberDetailsList.get(0).setWorkFlowReason("Sim validation is successful.");
									subscriberDetailsList.get(0).setSvValidationTime(time);
									subscriberDetailsList.get(0).setSimValidationFlag(1);
									subscriberDetailsList.get(0).setSimValidationTime(time);
									subscriber.setSubscriberDetails(subscriberDetailsList);
									logger.debug("subscriberDetailsList.get(0) 358="+MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
									this.kycDaoService.saveOrUpdateEntity(subscriber);
									
							
										
								
								
								}
								else{
									logger.debug("subscriberDetailsList.get(0) 381 "+MethodName + subscriber.getMsisdn() + " SM-Mismatch in SV : Null SIMNumberResponse");
									requestLog.setError_data("Null SIMNumberResponse");
									
									subscriberDetailsList.get(0).setFinalStatusReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, Null SIMNumberResponse");
									
									subscriberDetailsList.get(0).setWorkFlowReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, Null SIMNumberResponse");
									
									
									NotificationDto notificationDto = new NotificationDto();
									Templates templates1 =templatesDao.getTemplateObj(SIM_VALIDATION_FAILED, "en", "sms");
									logger.info("saubhagya : templates1 : "+templates1);
									String templateContent=templates1.getTemplateContent();
									String smsOutput = MessageFormat.format(templateContent, subscriber.getMsisdn());
									notificationDto.setTemplateContent(smsOutput);
									logger.info("saubhagya : templates1  after: "+templates1);
									//Map<String , Object> map = new HashMap<>();
									
									GKYCStatusRequestDto pGKYCStatusRequestDto = new GKYCStatusRequestDto();
									pGKYCStatusRequestDto.settMSISDN(subscriber.getMsisdn());
									GKYCStatusResponseDto gKYCStatusResponseDto = gKYCStatusDao.getGKYCStatus(pGKYCStatusRequestDto);
									
									if(gKYCStatusResponseDto!=null && gKYCStatusResponseDto.getRetailerMSISDN()!= null){
										//map.put("msisdn", gKYCStatusResponseDto.getRetailerMSISDN());
										logger.info("saubhagya :1 8 : map "+gKYCStatusResponseDto.getRetailerMSISDN());
										//notificationDto.setMap(map);
										logger.info("saubhagya :1 8 : ");
										notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());
										//notificationDto.setMsisdn("977240042");
										this.integrationService.notify(notificationDto);
									}
								
								//update final status
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.VALIDATION_FAILED.toString());
								subscriberDetailsList.get(0).setWorkFlowStatus("SIM_VALIDATION_FALSE");
								
								logger.debug("subscriberDetailsList.get(0) 398"+MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
								
								
								
								}
								
								
							} else {
								logger.debug("subscriberDetailsList.get(0) 405"+MethodName + subscriber.getMsisdn() + " SM-Mismatch in SV : Null SIMNumberResponse");
								requestLog.setError_data("Null SIMNumberResponse");
								
								subscriberDetailsList.get(0).setFinalStatusReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
										+ subscriberDetailsList.get(0).getSimSerialNumber()
										+ " are not same in SV, Null SIMNumberResponse");
								
								subscriberDetailsList.get(0).setWorkFlowReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
										+ subscriberDetailsList.get(0).getSimSerialNumber()
										+ " are not same in SV, Null SIMNumberResponse");
						
							
								
							
							//update final status
							subscriberDetailsList.get(0).setFinalStatus(ActionCodes.VALIDATION_FAILED.toString());
							subscriberDetailsList.get(0).setWorkFlowStatus("SIM_VALIDATION_FALSE");
							
							logger.debug("subscriberDetailsList.get(0) 422"+MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
							
							subscriber.setSubscriberDetails(subscriberDetailsList);
							this.kycDaoService.saveOrUpdateEntity(subscriber);
							
							NotificationDto notificationDto = new NotificationDto();
							Templates templates1 =templatesDao.getTemplateObj(SIM_VALIDATION_FAILED, "en", "sms");
							logger.info("saubhagya : templates1 : "+templates1);
							String templateContent=templates1.getTemplateContent();
							String smsOutput = MessageFormat.format(templateContent, subscriber.getMsisdn());
							notificationDto.setTemplateContent(smsOutput);
							logger.info("saubhagya : templates1  after: "+templates1);
							//Map<String , Object> map = new HashMap<>();
							
							GKYCStatusRequestDto pGKYCStatusRequestDto = new GKYCStatusRequestDto();
							pGKYCStatusRequestDto.settMSISDN(subscriber.getMsisdn());
							GKYCStatusResponseDto gKYCStatusResponseDto = gKYCStatusDao.getGKYCStatus(pGKYCStatusRequestDto);
							
							if(gKYCStatusResponseDto!=null && gKYCStatusResponseDto.getRetailerMSISDN()!= null){
								//map.put("msisdn", gKYCStatusResponseDto.getRetailerMSISDN());
								//map.put("msisdn", "975031118");
								logger.info("saubhagya :2 8 : map "+gKYCStatusResponseDto.getRetailerMSISDN());
								//notificationDto.setMap(map);
								logger.info("saubhagya :2 8 : ");
								notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());
								this.integrationService.notify(notificationDto);
							}
							
							
							
							}
							logger.debug("subscriberDetailsList.get(0) 426"+MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
							
							
						}
					}
				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
					
					//sms on sim validation failed cases
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,parameterMap);
					NotificationDto notificationDto1 = new NotificationDto();
					Templates templates11 =templatesDao.getTemplateObj(SIM_VALIDATION_FAILED, "en", "sms");
					logger.info("saubhagya : templates11 : "+templates11);
					String templateContent1=templates11.getTemplateContent();
					String smsOutput1 = MessageFormat.format(templateContent1, subscriber.getMsisdn());
					notificationDto1.setTemplateContent(smsOutput1);
					logger.info("saubhagya : templates11  after: "+templates11);
					
					GKYCStatusRequestDto pGKYCStatusRequestDto1 = new GKYCStatusRequestDto();
					pGKYCStatusRequestDto1.settMSISDN(subscriber.getMsisdn());
					GKYCStatusResponseDto gKYCStatusResponseDto1 = gKYCStatusDao.getGKYCStatus(pGKYCStatusRequestDto1);					
					if(gKYCStatusResponseDto1!=null && gKYCStatusResponseDto1.getRetailerMSISDN()!= null){									
						notificationDto1.setMsisdn(gKYCStatusResponseDto1.getRetailerMSISDN());
						this.integrationService.notify(notificationDto1);
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
				
				logger.error("error occured in sim validation"+ExceptionUtils.getStackTrace(e));
				requestLog.setError_data(e.getMessage());
				requestLog.setReq_due_time(new Timestamp(
						System.currentTimeMillis() + Long.parseLong(appConstants.sv_validation_retry_frequency)));
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}
		
		
	
		
	/*@SuppressWarnings("unchecked")
	private int SM_VALIDATION(AgentLog agentLog) {
		int jobcount = 0;
		Long run_id = agentLog.getRun_id();
		List<RequestLog> requets = null;//agentLog.getRequestLogs();
		
		String MethodName = "Run id :"+run_id+" SM_VALIDATION:";
		logger.debug(MethodName + "Started");
		
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.SINGLE_VIEW_VALIDATION.getTaskCode(),
					appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		
		if (requets != null)
			jobcount = requets.size();
		if(isDebugEnabled){
			logger.debug("Rin Id :"+run_id+" : Number of job to do : "+jobcount);
		}
		for (RequestLog requestLog : requets) {
			if(isDebugEnabled){
				logger.debug("Rin Id :"+run_id+" : Request log : "+requestLog+" Processing... ");
			}
			try {
				Long number_id = requestLog.getNumber_id();
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				logger.debug("narottam    "+ (requestLog.getRetry_count() < Integer.parseInt(appConstants.sv_validation_retry_count)));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.sv_validation_retry_count)) {
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByNamedQuery("Subscriber.findSubscriberById",parameterMap);
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					if(isDebugEnabled){
						logger.debug("Rin Id :"+run_id+" : Subscriber : "+subscriber);
						logger.debug("Rin Id :"+run_id+" : isSimserialMSISDNCorrect processing ... ");
					}
					
					logger.debug("narottam  msisdn fetched"+ subscriber.getMsisdn());
					logger.debug("narottam ss no fetched"+ subscriberDetailsList.get(0).getSimSerialNumber());
					SVResponse responseSV = this.singleviewService.isSimserialMSISDNCorrect(subscriber.getMsisdn(),	subscriberDetailsList.get(0).getSimSerialNumber());
					logger.debug("narottam responseSV" +responseSV);
					if(isDebugEnabled){
						logger.debug("Rin Id :"+run_id+" : sim & msisdn check response : "+responseSV);
					}
					if (responseSV != null) {
						//Added for testing
						responseSV.setExceptionOccured(false);
						responseSV.setResult(true);
						//Added For testing
						
						if (responseSV.isExceptionOccured()) {
							logger.error(MethodName + number_id + " Exception in sv subscriber sim summary API "
									+ responseSV.getException().getMessage() + "");
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.sv_validation_retry_frequency)));
							requestLog.setError_data(responseSV.getException().getMessage());
						} else {
							requestLog.setRequest_data(responseSV.getRequest());
							requestLog.setResponse_data(responseSV.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							FetchSubscriberSIMSummaryRes responseObj = (FetchSubscriberSIMSummaryRes) responseSV
									.getResponseObject();
							logger.debug(MethodName + number_id + " Sim validation status : "+responseSV.getResult());
							if (false == (boolean) responseSV.getResult()) {
								//responseSV.getResponseObject();
								logger.debug("in if block");
								System.out.println("in if block");
								
								if(responseObj != null)
								{
									logger.debug(MethodName + subscriber.getMsisdn() + " SM-Mismatch in SV "
											+ responseObj.getICCID() + "!=" + subscriberDetailsList.get(0).getSimSerialNumber());
									requestLog.setError_data("MSISDN_SIM_MISMATCH : "+"SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, sim serial in singleview is " + responseObj.getICCID());
									
									subscriberDetailsList.get(0).setFinalStatusReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, sim serial in singleview is " + responseObj.getICCID());
									
									subscriberDetailsList.get(0).setWorkFlowReason(subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, sim serial in singleview is " + responseObj.getICCID());
									
									
								}
								else
								{
									logger.debug(MethodName + subscriber.getMsisdn() + " SM-Mismatch in SV : Null SIMNumberResponse");
									requestLog.setError_data("Null SIMNumberResponse");
									
									subscriberDetailsList.get(0).setFinalStatusReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, Null SIMNumberResponse");
									
									subscriberDetailsList.get(0).setWorkFlowReason("SIM_VALIDATION_FALSE : "+ subscriber.getMsisdn() + " & "
											+ subscriberDetailsList.get(0).getSimSerialNumber()
											+ " are not same in SV, Null SIMNumberResponse");
								}
								
								
								//update final status
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.VALIDATION_FAILED.toString());
								subscriberDetailsList.get(0).setWorkFlowStatus("SIM_VALIDATION_FALSE");
								
								logger.debug(MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
								
								this.kycDaoService.saveOrUpdateEntity(subscriber);
								
								//update work flow
								try{
									if(isDebugEnabled) {
										logger.debug(MethodName + number_id + " Updating work flow with status "+ActionCodes.VALIDATION_FAILED.toString());
									}
									subscriber.setSubscriberId(number_id);
									this.subscriberManagementService.updateWorkFlow(subscriber, ActionCodes.VALIDATION_FAILED.toString());
								} catch (Exception e) {
									if(isDebugEnabled) {
										logger.error(MethodName + number_id + " Exception tracked while updateing work flow :");
										e.printStackTrace();
									}
								}
							} else {
								logger.debug("in else block");
								System.out.println("in else block");
								subscriberDetailsList.get(0).setWorkFlowStatus("SIM_VALIDATION_TRUE");
								subscriberDetailsList.get(0).setWorkFlowReason("Sim validation is successful.");
								subscriberDetailsList.get(0).setSvValidationTime(new Timestamp(System.currentTimeMillis()));
								logger.debug(MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
								this.kycDaoService.saveOrUpdateEntity(subscriber);
								// if bypass : unbar ema task added
								
								if(subscriberDetailsList.get(0).getBulkSubscriberFlag()==1)
								{
									addTask(TaskEnum.EMA_UNBARING_FOR_BULK.getTaskCode(), number_id,
											subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
								}
								else
								{
									//if (subscriber.getIsByPassed() == KycConstants.TRUE) {
										addTask(TaskEnum.EMA_UNBARING.getTaskCode(), number_id,
												subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
									//}
								}
									
								
							}
						}
					} else {
						requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
								+ Long.parseLong(appConstants.sv_validation_retry_frequency)));
						requestLog.setError_data("unable to get Response.");
					}
				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(MethodName + e.getMessage());
				requestLog.setError_data(e.getMessage());
				requestLog.setReq_due_time(new Timestamp(
						System.currentTimeMillis() + Long.parseLong(appConstants.sv_validation_retry_frequency)));
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}*/
	
	
	@SuppressWarnings("unchecked")
	private int unBarOnEMA(AgentLog agentLog) {
		Long run_id = agentLog.getRun_id();
		//List<RequestLog> requets = agentLog.getRequestLogs();
		String MethodName = "Run id :"+run_id+" unBarOnEMA:";
		logger.debug(MethodName + " : Started");
		List<RequestLog> requets = null;
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.EMA_UNBARING.getTaskCode(),
					appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		int jobcount = (requets != null) ? requets.size() : 0;
		if (isDebugEnabled) {
			logger.debug(MethodName + " : Number of jobs to run : " + jobcount);
		}
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				logger.debug(MethodName + number_id + " Processing");
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.ema_validation_retry_count)) {
					
					requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
					
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					/*Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByNamedQuery("Subscriber.findSubscriberById",parameterMap);*/
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					
					logger.debug(MethodName +" :665 EMAResponse : "+subscriberDetailsList.size());
					if(isDebugEnabled){
						logger.debug(MethodName +" : Subscriber : "+subscriber);
						logger.debug(MethodName+" : ema unbar processing ... ");
					}
					EMAResponse responseEMA = this.integrationService.unbar(subscriber.getMsisdn());
					
					//Added for testing
					//EMAResponse responseEMA = new EMAResponse();					
					//responseEMA.setUnbar(true);
					//Added for testing
					
					if(isDebugEnabled){
						logger.debug(MethodName +" : EMAResponse : "+responseEMA);
					}
					if (responseEMA != null) {
						if (responseEMA.isExceptionOccured()) {
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.ema_validation_retry_count)));
							requestLog.setError_data(responseEMA.getException().getMessage());
						} else {
							requestLog.setRequest_data(responseEMA.getRequest());
							requestLog.setResponse_data(responseEMA.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							System.out.println("responseEMA.isUnbar()"+responseEMA.isUnbar());
							//responseEMA.setUnbar(true);
							if (responseEMA.isUnbar()) {
								subscriberDetailsList.get(0).setEmaActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setPartialActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PENDING.getActionDescription());
								subscriberDetailsList.get(0).setFinalStatusReason("EMA_UNBAR_TRUE : unbared on ema.");
								subscriberDetailsList.get(0).setWorkFlowStatus("EMA_UNBAR_TRUE");
								subscriberDetailsList.get(0).setWorkFlowReason("unbared on ema successful.");
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);										
								
								//addTask(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(), number_id,subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
								addTask(TaskEnum.SV_STATUS_UPDATION.getTaskCode(), number_id,subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
								
								//for sending sms to CP
								NotificationDto notificationDto = new NotificationDto();								
								Templates templatesRetailer =templatesDao.getTemplateObj(SIM_UNBARRING_RETAILER, "en", "sms");
								logger.info("saubhagya : templates1 : "+templatesRetailer);
								String templateContent=templatesRetailer.getTemplateContent();
								String smsOutput = MessageFormat.format(templateContent, subscriber.getMsisdn());
								notificationDto.setTemplateContent(smsOutput);
								logger.info("saubhagya : templates1  after: "+templatesRetailer);
								
								GKYCStatusRequestDto pGKYCStatusRequestDto = new GKYCStatusRequestDto();
								pGKYCStatusRequestDto.settMSISDN(subscriber.getMsisdn());
								GKYCStatusResponseDto gKYCStatusResponseDto = gKYCStatusDao.getGKYCStatus(pGKYCStatusRequestDto);
								
								if(gKYCStatusResponseDto!=null && gKYCStatusResponseDto.getRetailerMSISDN()!= null){									
									logger.info("saubhagya :1 8 : map "+gKYCStatusResponseDto.getRetailerMSISDN());									
									logger.info("saubhagya :1 8 : ");
									notificationDto.setMsisdn(gKYCStatusResponseDto.getRetailerMSISDN());									
									this.integrationService.notify(notificationDto);
								}
								
								//for sending sms to Subscriber
								Templates templatesSubscriber =templatesDao.getTemplateObj(SIM_UNBARRING_SMS, "en", "sms");								
								String templateContentForSub=templatesSubscriber.getTemplateContent();								
								notificationDto.setTemplateContent(templateContentForSub);
								logger.info("saubhagya : templates1  after: "+templateContentForSub);								
								
								if(gKYCStatusResponseDto!=null && gKYCStatusResponseDto.getMSISDN()!= null){									
									logger.info("saubhagya :1 9 : map "+gKYCStatusResponseDto.getMSISDN());									
									logger.info("saubhagya :1 9 : ");
									notificationDto.setMsisdn(gKYCStatusResponseDto.getMSISDN());									
									this.integrationService.notify(notificationDto);
								}
								
								
							} else {
								subscriberDetailsList.get(0).setWorkFlowStatus("EMA_UNBAR_FALSE");
								subscriberDetailsList.get(0).setWorkFlowReason("Unable to unbar on ema.");
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
							}
							logger.debug(MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
							
							
						}
					}
				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				logger.error(MethodName + e.getMessage());
				requestLog.setError_data(e.getMessage());
				requestLog.setReq_due_time(new Timestamp(
						System.currentTimeMillis() + Long.parseLong(appConstants.sv_validation_retry_frequency)));
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}

	@SuppressWarnings("unchecked")
	private int updateSingleView(Long run_id) {
		String MethodName = "updateSingleView:";
		logger.debug(MethodName + "Started");
		int jobcount = 0;
		List<RequestLog> requets = null;
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(),
					appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		if (requets != null)
			jobcount = requets.size();
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				logger.debug(MethodName + number_id + " Processing");
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.sv_update_retry_count)) {
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					/*Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);*/
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);

					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					logger.debug("prev list size"+subscriberDetailsList.size());
					if(subscriberDetailsList!=null && subscriberDetailsList.size()<1 ){
						subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOnApproved(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					}	
					logger.debug("list size"+subscriberDetailsList.size());
					logger.debug(MethodName + number_id + " Processing getApprovedSubscriber start");
					Map<String, Object> parameterMap1 = new HashMap<>();
					parameterMap1.put("subscriberId", number_id);
					parameterMap1.put("isOldUserDetails",0);
					parameterMap1.put("finalStatus",ActionCodes.APPROVED.toString());
					
					SubscriberDetails subscriberDetail = this.kycDaoService.getApprovedSubscriber(parameterMap1);
					logger.debug(MethodName + number_id + " Processing getApprovedSubscriber end:"+subscriberDetail);

					/*SubscriberDto dto = this.subscriberManagementService
							.getSubscriberByMsisdnAndSimNo(subscriber.getMsisdn(), null, 0);*/
					
					logger.debug("am account flag :"+subscriberDetailsList.get(0).getAmAccount());
					SVResponse responseSV = this.integrationService.updateSingleViewSubsDetails(subscriberDetailsList.get(0));
					/*logger.info("saubhagya : responseSV request"+responseSV.getRequest());
					logger.debug("saubhagya : responseSV request"+responseSV.getRequest());
					
					logger.info("saubhagya : responseSV request"+responseSV.getResponse());
					logger.debug("saubhagya : responseSV request"+responseSV.getResponse());*/
					//SVResponse responseSV = new SVResponse();
					//Added for testing
					//responseSV.setExceptionOccured(false);
					//responseSV.setResult(true);
					//Added For testing
					if (responseSV != null) {
						
						
						
						if (responseSV.isExceptionOccured()) {
							requestLog.setRequest_data(responseSV.getRequest());
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.sv_update_retry_frequency)));
							requestLog.setError_data(responseSV.getException().getMessage());
						} else {
							requestLog.setRequest_data(responseSV.getRequest());
							requestLog.setResponse_data(responseSV.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							
							UpdateCustomerDemographicsResponse responseObj = (UpdateCustomerDemographicsResponse) responseSV.getResponseObject();
							
							if (false == (boolean)responseSV.getResult()) {
								
								logger.debug(MethodName + number_id + " Processing result :"+responseSV.getResult());
								
								requestLog.setError_data("SIM_UPDATION_FALSE");
								subscriberDetailsList.get(0).setWorkFlowStatus("SV_UPDATION_FALSE");
								
								String str ="";
								if(responseObj.getRESULT() != null)
									str =responseObj.getRESULT().getMESSAGE();
								
								subscriberDetailsList.get(0).setWorkFlowReason("Unable to update singleview. : "+str);
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
							} else {
								logger.debug(MethodName + number_id + " Processing result :"+responseSV.getResult());
								requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
								
								//Add New Task for Updating airtel Money
								logger.debug(MethodName + number_id + " Checking AM activation "+subscriberDetailsList.get(0).getAmAccount());
								logger.debug(MethodName + number_id + " Checking AM activation done....");
								if(subscriberDetailsList.get(0).getAmAccount() == null || subscriberDetailsList.get(0).getAmAccount() == 0){
									// status update on subscriber
									logger.debug(MethodName + number_id + " Checking AM activation is false");
									// status update on subscriber
									subscriberDetailsList.get(0).setFinalStatus(ActionCodes.APPROVED.toString());
									subscriberDetailsList.get(0).setFinalStatusReason(subscriber.getMsisdn() +" : singleview updated successfuly");

									subscriberDetailsList.get(0).setSvActivationTime(new Timestamp(System.currentTimeMillis()));
									
									subscriberDetailsList.get(0).setWorkFlowStatus("SV_UPDATION_TRUE");
									subscriberDetailsList.get(0).setWorkFlowReason("Singleview update successful.");
									
									subscriberDetailsList.get(0).setIsOldUserDetails(0);
									
									logger.debug(MethodName+" : updating getApprovedSubscriber: before updatin subscriber ");
									
									subscriber.setSubscriberDetails(subscriberDetailsList);
									this.kycDaoService.saveOrUpdateEntity(subscriber);
									
									logger.debug(MethodName+" : updating getApprovedSubscriber: after updating subscriber ");
									
									subscriber.setSubscriberId(number_id);
									this.subscriberManagementService.updateWorkFlow(subscriber, ActionCodes.APPROVED.toString());

									if(subscriberDetail!=null){
										logger.debug(MethodName+" : updating getApprovedSubscriber: "+subscriberDetail.getSubscriberDetailsId());
										subscriberDetail.setIsOldUserDetails(1);
										this.kycDaoService.updateQuery(subscriberDetail);
									}
								}
								else{

									subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PENDING.toString());
									subscriberDetailsList.get(0).setFinalStatusReason(subscriber.getMsisdn() +"singleview updated successfuly");

									subscriberDetailsList.get(0).setSvActivationTime(new Timestamp(System.currentTimeMillis()));
									
									subscriberDetailsList.get(0).setWorkFlowStatus("SV_UPDATION_TRUE");
									subscriberDetailsList.get(0).setWorkFlowReason("Singleview update successful.");
									
									subscriber.setSubscriberDetails(subscriberDetailsList);
									this.kycDaoService.saveOrUpdateEntity(subscriber);
									
									subscriber.setSubscriberId(number_id);
									this.subscriberManagementService.updateWorkFlow(subscriber, ActionCodes.APPROVED.toString());

									addTask(TaskEnum.AM_ACTIVATION.getTaskCode(), number_id,
											subscriberDetailsList.get(0).getSubscriberDetailsId().toString());

								}
							}
						}
					} else {
						requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
						logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
					}

				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				logger.error("error in sv"+ExceptionUtils.getStackTrace(e));
				e.printStackTrace();
				requestLog.setError_data(MethodName + e.getMessage());
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}

	@SuppressWarnings("unchecked")
	private int activateAirtelMoney(Long run_id) {
		String MethodName = "updateAirtelMoney:";
		logger.debug(MethodName + "Started");
		int jobcount = 0;
		List<RequestLog> requets = null;
		RequestLog reqLog=null;
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.AM_ACTIVATION.getTaskCode(),appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		if (requets != null)
			jobcount = requets.size();
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				Long reqId=requestLog.getRequest_id();
				 reqLog=requestLogDao.getReqObj(reqId);
				logger.debug(MethodName + number_id + " Processing");
				reqLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < appConstants.airtel_money_retry_count) {
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);
					
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					if(subscriberDetailsList.get(0).getAmAccount()==1){
						Map<String, Object> parameterMap1 = new HashMap<>();
						parameterMap1.put("subscriberId", number_id);
						parameterMap1.put("isOldUserDetails",0);
						parameterMap1.put("finalStatus",ActionCodes.APPROVED.toString());
						
						logger.debug(MethodName + number_id + " Processing getApprovedSubscriber start");
						
						SubscriberDetails subscriberDetail = this.kycDaoService.getApprovedSubscriber(parameterMap1);
						logger.debug(MethodName + number_id + " Processing getApprovedSubscriber end :"+subscriberDetail);

						SubscriberDto dto = this.subscriberManagementService
								.getSubscriberByMsisdnAndSimNo(subscriber.getMsisdn(), null, 0);
						logger.debug("saubhagya dto :"+dto);
						AMResponse responseAM = this.integrationService.updateOnAM(dto);
						logger.debug(MethodName + "AIRTELMONEY_UPDATION : updated AM status : "+responseAM);
						
						logger.info("saubhagya : updated AM status : "+responseAM);
						
						if (responseAM != null) {
							if (responseAM.isExceptionOccured()) {
								logger.info("saubhagya : updated AM if : "+responseAM.getException().getMessage());
								logger.error(MethodName + number_id + " Exception in Update in AM "
										+ responseAM.getException().getMessage() + "");
								reqLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
										+ Long.parseLong(appConstants.am_update_retry_frequency)));
								reqLog.setError_data(responseAM.getException().getMessage());
							} else {
								logger.info("saubhagya : 1111 : ");
								
								reqLog.setRequest_data(responseAM.getRequest());
								reqLog.setResponse_data(responseAM.getResponse());
								
								reqLog.setRequest_status(THREAD_STATUS_SUCCESS);
								
								logger.info("saubhagya : 2222 : ");
								
								if (true == (boolean)responseAM.getResult()) {
									logger.info("saubhagya : 3 : ");
									//logger.info("saubhagya : AM_UPDATION_TRUE : "+responseAM.getException().getMessage());
									//logger.debug(MethodName + "AM_UPDATION_TRUE : "+subscriber.getMsisdn());
									subscriberDetailsList.get(0).setAmActivationTime(new Timestamp(System.currentTimeMillis()));
									subscriberDetailsList.get(0).setActivationTime(new Timestamp(System.currentTimeMillis()));
									
									subscriberDetailsList.get(0).setFinalStatus(ActionCodes.APPROVED.toString());
									subscriberDetailsList.get(0).setFinalStatusReason("subscriber approved.");
									
									subscriberDetailsList.get(0).setWorkFlowStatus("AM_UPDATION_TRUE");
									subscriberDetailsList.get(0).setWorkFlowReason("AM updated successful.");
									
									subscriberDetailsList.get(0).setIsOldUserDetails(0);
									logger.info("saubhagya : 4 : ");
									subscriber.setSubscriberDetails(subscriberDetailsList);
									this.kycDaoService.saveOrUpdateEntity(subscriber);
									
									subscriber.setSubscriberId(number_id);
									this.subscriberManagementService.updateWorkFlow(subscriber, ActionCodes.APPROVED.toString());
									//Event for sms Triggering for subscriber & retailer
									logger.info("saubhagya : 5 : ");
									if(subscriberDetail!=null){
										logger.info("saubhagya : getApprovedSubscriber : "+subscriberDetail.getSubscriberDetailsId());
										logger.debug(MethodName+" : updating getApprovedSubscriber : "+subscriberDetail.getSubscriberDetailsId());
										subscriberDetail.setIsOldUserDetails(1);
										this.kycDaoService.updateQuery(subscriberDetail);
									}
									logger.info("saubhagya : 6 : ");
									//TO DO
									NotificationDto notificationDto = new NotificationDto();
									notificationDto.setMsisdn(subscriber.getMsisdn());
									String locale = appConstants.LOCALE;
									if(subscriberDetailsList.get(0).getLanguage() != null) {
										locale = subscriberDetailsList.get(0).getLanguage();
									}
									
									
									
									logger.info("saubhagya : 7 : ");
									//trigger sms to subscriber for AM
									//Templates templates1 =dataManagementService.getTemplates(locale, SMS_REGISTRATION_CUSTOMER_WELCOME_AM, "sms");
									Templates templates1 =templatesDao.getTemplateObj(SMS_REGISTRATION_CUSTOMER_WELCOME_AM, "en", "sms");
									logger.info("saubhagya : templates1 : "+templates1);
									String templateContent=templates1.getTemplateContent();
									String smsOutput = MessageFormat.format(templateContent, subscriber.getMsisdn());
									notificationDto.setTemplateContent(smsOutput);
									logger.info("saubhagya : templates1  after: "+templates1);
									Map<String , Object> map = new HashMap<>();
									map.put("msisdn", subscriber.getMsisdn()+"");
									notificationDto.setMap(map);
									logger.info("saubhagya : 8 : ");
									this.integrationService.notify(notificationDto);
									
									
									
								} else {
									logger.info("saubhagya : 9 : ");
									logger.info("saubhagya : AIRTELMONEY_UPDATION_FALSE : ");
									subscriberDetailsList.get(0).setWorkFlowStatus("AIRTELMONEY_UPDATION_FALSE");
									subscriberDetailsList.get(0).setWorkFlowReason("Unable to update airtel money");
									subscriber.setSubscriberDetails(subscriberDetailsList);
									this.kycDaoService.saveOrUpdateEntity(subscriber);							
									}
							}
						
					}
					
					
               }
					
					else{
						//suspend airtel if amaccount is set no
						
						subscriberDetailsList.get(0).setAmActivationTime(new Timestamp(System.currentTimeMillis()));
						subscriberDetailsList.get(0).setActivationTime(new Timestamp(System.currentTimeMillis()));
						
						subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PARTIALLY_ACTIVATED.toString());
						subscriberDetailsList.get(0).setFinalStatusReason("subscriber approved.");
						
						subscriberDetailsList.get(0).setWorkFlowStatus("AM_UPDATION_PENDING");
						subscriberDetailsList.get(0).setWorkFlowReason("AM updation Pending.");
						
						subscriberDetailsList.get(0).setIsOldUserDetails(0);
						logger.info("saubhagya : 23 : ");
						subscriber.setSubscriberDetails(subscriberDetailsList);
						this.kycDaoService.saveOrUpdateEntity(subscriber);
						
						subscriber.setSubscriberId(number_id);
						this.subscriberManagementService.updateWorkFlow(subscriber, ActionCodes.APPROVED.toString());

						
					}
				} else {
					logger.info("saubhagya : 10 : ");
					logger.info("saubhagya : THREAD_STATUS_EXPIRED : ");
					reqLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("saubhagya : 11 : ");
				e.printStackTrace();
				logger.info("saubhagya : exception block : "+e.getStackTrace().toString());
				logger.info("saubhagya : exception block : "+e);
				reqLog.setError_data(MethodName + e.getMessage());
				logger.error(MethodName + e.getMessage());
			}
			reqLog.setRetry_count(requestLog.getRetry_count() + 1);
			reqLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			reqLog.setRun_id(run_id);
			logger.info("saubhagya : before request log save method ");
			try{
				//saveupdateRequest(requestLog);
				requestLogDao.saveOrUpdate(reqLog);
			}
			catch(Exception e){
				e.printStackTrace();
				logger.info("saubhagya : saveupdateRequest "+e);
				logger.info("saubhagya : Ended ");
			}
			
			logger.debug(MethodName + "Ended");
			logger.info("saubhagya : Ended ");
			logger.info("saubhagya : 12 : ");
		}
		return jobcount;
	}

	@SuppressWarnings("unchecked")
	private int barOnEMA(AgentLog agentLog) {
		String MethodName = "barOnEMA";
		logger.debug(MethodName + "Started");
		int jobcount = 0;
		Long run_id = agentLog.getRun_id();
		List<RequestLog> requets = null;//agentLog.getRequestLogs();
		
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.EMA_BARING.getTaskCode(), appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		if (requets != null)
			jobcount = requets.size();
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				logger.debug(MethodName + number_id + " Processing...");
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.ema_barring_retry_count)) {
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					/*Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByNamedQuery("Subscriber.findSubscriberById",parameterMap);*/
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
					EMAResponse responseEMA = this.integrationService.bar(subscriber.getMsisdn());
					requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
					if (responseEMA != null) {
						if (responseEMA.isExceptionOccured()) {
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.ema_validation_retry_count)));
							requestLog.setError_data(responseEMA.getException().getMessage());
						} else {
							requestLog.setRequest_data(responseEMA.getRequest());
							requestLog.setResponse_data(responseEMA.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							if (!responseEMA.isUnbar()){
								//Add new Task
								//subscriberDetailsList.get(0).setFinalStatus(ActionCodes.BARRED.toString());
								subscriberDetailsList.get(0).setFinalStatusReason("Barring Failed On Ema ");
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
							}
							else{
								subscriberDetailsList.get(0).setEmaActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setPartialActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.BARRED.getActionDescription());
								subscriberDetailsList.get(0).setFinalStatusReason("EMA_BAR_TRUE : bared on ema.");
								
								subscriberDetailsList.get(0).setWorkFlowStatus("EMA_BAR_TRUE");
								subscriberDetailsList.get(0).setWorkFlowReason("bared on ema successful.");
								
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
								
								SubscriberWorkFlowDto subscriberWorkFlowDto = new SubscriberWorkFlowDto();
								subscriberWorkFlowDto.setAction("BARRED");
								subscriberWorkFlowDto.setRoleName("CM");
								subscriberWorkFlowDto.setStatusReason("BARRED SUCCESSFUL");
								subscriberWorkFlowDto.setSubscriberId(subscriber.getSubscriberId());
								subscriberWorkFlowDto.setUserId(subscriberDetailsList.get(0).getSubscriberWorkFlow().getUserId());
								
								subscriberWorkFlowDto = (SubscriberWorkFlowDto) subscriberManagementService
										.actionOnSubscriber(subscriberWorkFlowDto);

							}
							
							/*if (!responseEMA.isUnbar()) {
								// add new Task
								subscriber.setFinalStatus(ActionCodes.REJECTED.toString());
								subscriber.setFinalStatusReason("Ema barred ");
								
								subscriber.setWorkFlowStatus("EMA_BAR_TRUE");
								subscriber.setWorkFlowReason("bared on ema successful.");
								
							} else {
								//subscriber.setFinalStatus(ActionCodes.FAILED.toString());
								//subscriber.setFinalStatusReason("Ema unbarred ");
								
								subscriber.setWorkFlowStatus("EMA_BAR_FALSE");
								subscriber.setWorkFlowReason("unable to bared on ema");
								
							}*/
							
							this.kycDaoService.saveOrUpdateEntity(subscriber);
						}
					}
				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				logger.error(MethodName + e.getMessage());
				requestLog.setError_data(e.getMessage());
				requestLog.setReq_due_time(new Timestamp(
						System.currentTimeMillis() + Long.parseLong(appConstants.ema_barring_retry_frequency)));
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}
	
	@SuppressWarnings("unchecked")
	private int activateOnSV(Long run_id) {
		String MethodName = "Run id :"+run_id+" activateOnSV:";
		logger.debug(MethodName + " : Started");
		List<RequestLog> requets = null;
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.SV_STATUS_UPDATION.getTaskCode(),
					appConstants.hostname);
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		int jobcount = (requets != null) ? requets.size() : 0;
		if (isDebugEnabled) {
			logger.debug(MethodName + " : Number of jobs to run : " + jobcount);
		}
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				logger.debug(MethodName +" Subscriber Id : "+ number_id + " retry count - "+requestLog.getRetry_count()+" Processing... ");
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.sv_status_update_count)) {
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);
					
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					
					if(isDebugEnabled){
						logger.debug(MethodName+" : Subscriber : "+subscriber);
						logger.debug(MethodName+" : activateOnSingleView processing ... ");
					}
					SVResponse responseSV = this.integrationService.activateOnSingleView(subscriber.getMsisdn());
					if(isDebugEnabled){
						logger.debug(MethodName+" : SVResponse : "+responseSV.getResult());
					}
					if (responseSV != null) {
						if (responseSV.isExceptionOccured()) {
							logger.error(MethodName + number_id + " Exception in SV Status update api"
									+ responseSV.getException().getMessage() + "");
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.sv_staus_update_retry_frequency)));
							requestLog.setRequest_data(responseSV.getRequest());
							requestLog.setError_data(responseSV.getException().getMessage());
						} else {
							requestLog.setRequest_data(responseSV.getRequest());
							requestLog.setResponse_data(responseSV.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							logger.debug(MethodName + number_id + " Sim activation status : "+responseSV.getResult());
							if (false == (boolean)responseSV.getResult()) {
								if(responseSV != null && responseSV.getResult() != null)
									logger.debug(MethodName + subscriber.getMsisdn() + " unable to activate in SV "
										+ responseSV.getResponse());
								requestLog.setError_data("SV_STATUS_UPDATE_FALSE : "+responseSV.getResponse());
								
								//update final status
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PENDING.getActionDescription());
								subscriberDetailsList.get(0).setFinalStatusReason("SV_STATUS_UPDATE_FALSE : "+ responseSV.getResponse());
								logger.debug(MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
							} else {
								
								logger.debug(MethodName + number_id + " Updating status on  SV");
								//subscriberDetailsList.get(0).setEmaActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setSvActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setPartialActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PENDING.getActionDescription());
								subscriberDetailsList.get(0).setFinalStatusReason("SV_STATUS_UPDATE_TRUE : activate on single view successful.");
								
								subscriberDetailsList.get(0).setWorkFlowStatus("SV_STATUS_UPDATE_TRUE");
								subscriberDetailsList.get(0).setWorkFlowReason("SV_STATUS_UPDATE_TRUE : activate on single view successful.");

								logger.debug(MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
								
								addTask(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(), number_id,subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
								
								//addTask(TaskEnum.SV_DEMGRAPHIC_UPDATION.getTaskCode(), number_id,subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
							}
							
						}
					}
				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				logger.error(MethodName + e.getMessage());
				requestLog.setError_data(MethodName + e.getMessage());
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}
	
	@SuppressWarnings("unchecked")
	private int unBarOnEMAForBulk(AgentLog agentLog) {
		Long run_id = agentLog.getRun_id();
		//List<RequestLog> requets = agentLog.getRequestLogs();
		String MethodName = "Run id :"+run_id+" unBarOnEMAForBulk:";
		logger.debug(MethodName + " : Started");
		List<RequestLog> requets = null;
		try {
			requets = this.kycDaoService.getRequestsByType(TaskEnum.EMA_UNBARING_FOR_BULK.getTaskCode(),
					appConstants.hostname);
			
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		int jobcount = (requets != null) ? requets.size() : 0;
		if (isDebugEnabled) {
			logger.debug(MethodName + " : Number of jobs to run : " + jobcount);
		}
		for (RequestLog requestLog : requets) {
			try {
				Long number_id = requestLog.getNumber_id();
				logger.debug(MethodName + number_id + " Processing");
				requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
				if (requestLog.getRetry_count() < Integer.parseInt(appConstants.ema_validation_retry_count)) {
					
					requestLog.setReq_raised_at(new Timestamp(System.currentTimeMillis()));
					
					Map<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("subscriberId", number_id);
					/*Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityByNamedQuery("Subscriber.findSubscriberById",parameterMap);*/
					Subscriber subscriber = (Subscriber) this.kycDaoService.getEntityBySQL(Subscriber.class,
							parameterMap);
					List<SubscriberDetails> subscriberDetailsList=subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
					if(isDebugEnabled){
						logger.debug(MethodName +" : Subscriber : "+subscriber);
						logger.debug(MethodName+" : ema unbar for bulk processing ... ");
					}
					EMAResponse responseEMA = this.integrationService.unbarforBulk(subscriber.getMsisdn());
					
					if(isDebugEnabled){
						logger.debug(MethodName +" : EMAResponse : "+responseEMA);
					}
					if (responseEMA != null) {
						if (responseEMA.isExceptionOccured()) {
							requestLog.setReq_due_time(new Timestamp(System.currentTimeMillis()
									+ Long.parseLong(appConstants.ema_validation_retry_count)));
							requestLog.setError_data(responseEMA.getException().getMessage());
						} else {
							requestLog.setRequest_data(responseEMA.getRequest());
							requestLog.setResponse_data(responseEMA.getResponse());
							requestLog.setRequest_status(THREAD_STATUS_SUCCESS);
							
							if (responseEMA.isUnbar()) {
								subscriberDetailsList.get(0).setEmaActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setPartialActivationTime(new Timestamp(System.currentTimeMillis()));
								subscriberDetailsList.get(0).setFinalStatus(ActionCodes.PENDING.getActionDescription());
								subscriberDetailsList.get(0).setFinalStatusReason("EMA_UNBAR_TRUE : unbared on ema.");
								subscriberDetailsList.get(0).setWorkFlowStatus("EMA_UNBAR_TRUE");
								subscriberDetailsList.get(0).setWorkFlowReason("unbared on ema successful.");
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
								
								addTask(TaskEnum.SV_STATUS_UPDATION.getTaskCode(), number_id,
										subscriber.getKycTansactionId());
								
								
							} else {
								subscriberDetailsList.get(0).setWorkFlowStatus("EMA_UNBAR_FALSE");
								subscriberDetailsList.get(0).setWorkFlowReason("Unable to unbar on ema.");
								subscriber.setSubscriberDetails(subscriberDetailsList);
								this.kycDaoService.saveOrUpdateEntity(subscriber);
							}
							logger.debug(MethodName + number_id + " status : "+subscriberDetailsList.get(0).getFinalStatus());
							
						}
					}
				} else {
					requestLog.setRequest_status(THREAD_STATUS_EXPIRED);
					logger.warn(MethodName + number_id + " Retry Limits Exceeds.");
				}
			} catch (Exception e) {
				logger.error(MethodName + e.getMessage());
				requestLog.setError_data(e.getMessage());
				requestLog.setReq_due_time(new Timestamp(
						System.currentTimeMillis() + Long.parseLong(appConstants.sv_validation_retry_frequency)));
			}
			requestLog.setRetry_count(requestLog.getRetry_count() + 1);
			requestLog.setReq_completd_at(new Timestamp(System.currentTimeMillis()));
			requestLog.setRun_id(run_id);
			saveupdateRequest(requestLog);
			logger.debug(MethodName + "Ended");
		}
		return jobcount;
	}
	


	
		

	@Override
	public String sendSMS(String msisdn, String sms_text) {
		return null;
	}
}
