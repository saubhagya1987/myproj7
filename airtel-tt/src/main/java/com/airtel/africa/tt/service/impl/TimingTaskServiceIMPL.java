package com.airtel.africa.tt.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.africa.tt.service.TimingTaskService;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.service.HelperDataManagementService;
import com.airtel.kyc.business.service.SubscriberManagementService;
import com.airtel.kyc.business.service.TaskManagementService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ConfigDataEnum;
import com.airtel.kyc.enumData.TaskEnum;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.SearchSubscriberDto;
import com.airtel.kyc.helpers.dto.SubscriberDetailsDto;
import com.airtel.kyc.helpers.dto.SubscriberWorkFlowDto;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.dao.CMDao;
import com.airtel.kyc.repository.dao.KycDaoService;
import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.entity.Assignment;
import com.airtel.kyc.repository.entity.AssignmentTracker;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.SubscriberWorkFlow;
import com.airtel.kyc.repository.entity.SubscriberWorkFlowHistory;
import com.airtel.kyc.repository.entity.Templates;
import com.airtel.kyc.repository.exception.KycDaoException;

@Service
public class TimingTaskServiceIMPL implements TimingTaskService,KycConstants {

	@Autowired
	private CMDao cMDao;
	
	@Autowired
	private TaskManagementService taskManagementService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private KycDaoService kycDaoService;
	
	@Autowired
	private IntegrationService integrationService; 

	@Autowired
	private SubscriberManagementService subscriberManagementService;

	@Autowired
	private AppConstants appConstants;
	
	@Autowired
	private SubscriberDetailsDao subscriberDetailsDao;
	
	@Autowired
	private HelperDataManagementService dataManagementService;

	@SuppressWarnings("unchecked")
	@Override
	public void autoAssign() {

		try {
			List<Integer> list = (List<Integer>) cMDao.getCMUsersByLastLogin(appConstants.timeSpanFormat,
					appConstants.loggedInTime, appConstants.loggedInUser);

			if (list.size() > 0) {

				SearchSubscriberDto searchSubscriberDto = new SearchSubscriberDto();
				searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
				searchSubscriberDto.setAction("PENDING");
				List<Subscriber> subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto, "AUTO_ASSIGN");

				if(subscriberList!=null && subscriberList.size() > 0){
					Object[] objArray = list.toArray();
	
					String userIds = StringUtils.join(objArray, ',');
	
					List<Assignment> listAssignmentLoggedIn = cMDao.getAssignmentDetails(userIds,"in");
					
					/*StringBuilder userIdLoggedOut = new StringBuilder("");
					for(Assignment assign:listAssignmentLoggedIn){
						if(userIdLoggedOut == null || userIdLoggedOut.length()<=1){
							userIdLoggedOut.append(assign.getUserId());
						}
						else{
							userIdLoggedOut.append(",").append(assign.getUserId());
						}
					}
					List<Assignment> listAssignmentLoggedOut = cMDao.getAssignmentDetails(userIdLoggedOut.toString(),"not in");
					*/
					List<Integer> loggedInUserEmptyBin = listAssignmentLoggedIn.stream().map(e->e.getUserId()).collect(Collectors.toList());
					Object[] objArray1 = loggedInUserEmptyBin.toArray();
					String userIdLoggedIn = StringUtils.join(objArray1, ',');
					List<Assignment> listAssignmentLoggedOut = cMDao.getAssignmentDetails(userIdLoggedIn,"not in");
					
					Set<Integer> hashSet = new HashSet<Integer>();
					
					for (Subscriber subscriber : subscriberList) {
						AssignmentTracker assignmentTracker = cMDao.getAssignmentTracker();
						Assignment assignment = getUserId(listAssignmentLoggedIn, listAssignmentLoggedOut, assignmentTracker);

						if(hashSet.add(assignment.getUserId())){
							List<SubscriberDetails> subscriberDetailsList = subscriberDetailsDao.getSubscriberDetailsAndUpdatedOn(subscriber.getSubscriberId(),subscriber.getUpdatedOn());
							assignment.setCount(1);
							cMDao.saveObject(assignment);
		
							SubscriberWorkFlowDto subscriberWorkFlowDto = new SubscriberWorkFlowDto();
							SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
							
							subscriberDetailsList.get(0).setWorkFlowStatus("PENDING");
							subscriberDetailsList.get(0).setAssignmentFlag(1);
							subscriberDetailsList.get(0).setFinalStatusReason("PENDING FOR CM APPROVAL");
							subscriberDetailsDto.setSubscriberDetailsId(subscriberDetailsList.get(0).getSubscriberDetailsId());
		
							subscriberWorkFlowDto.setSubscriberDetailsDto(subscriberDetailsDto);
							
							//kycDaoService.saveOrUpdateEntity(subscriber);
							
							subscriberWorkFlowDto.setAction("PENDING");
							subscriberWorkFlowDto.setRoleName("CM");
							subscriberWorkFlowDto.setStatusReason("PENDING FOR CM APPROVAL");
							subscriberWorkFlowDto.setSubscriberId(subscriber.getSubscriberId());
							subscriberWorkFlowDto.setUserId(assignment.getUserId());
							
							subscriberWorkFlowDto = (SubscriberWorkFlowDto) subscriberManagementService
									.autoAssignSubscriber(subscriberWorkFlowDto,subscriberDetailsList);
						}
					}
	
				}
			}
		} catch (KycDaoException | BusinessException e1) {
			e1.printStackTrace();
		}
	}
	
	private Assignment getUserId(List<Assignment> listAssignmentLoggedIn,List<Assignment> listAssignmentLoggedOut, 
			AssignmentTracker assignmentTracker) {
		Integer userId = null;
		Assignment assignmentRet = null;
		for(Assignment assignment:listAssignmentLoggedIn){
			if(assignment.getUserId().intValue() == assignmentTracker.getNextAssign().intValue()){
				userId = assignment.getUserId();
				assignmentRet = assignment;
				assignmentTracker.setCurrentAssign(userId);
				assignmentTracker.setNextAssign(assignment.getNextAssignUserId());
				try {
					cMDao.saveObject(assignmentTracker);
				} catch (KycDaoException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		if(userId == null){
			for(Assignment assignment:listAssignmentLoggedOut){
				if(assignment.getUserId().intValue() == assignmentTracker.getNextAssign().intValue()){
					assignmentTracker.setCurrentAssign(assignment.getUserId());
					assignmentTracker.setNextAssign(assignment.getNextAssignUserId());
				}
			}
		}
		if(userId == null){
			return getUserId(listAssignmentLoggedIn,listAssignmentLoggedOut,assignmentTracker);
		}
		else{
			return assignmentRet;
		}
	}
	
/**************************************/
/**************************************/
	
	@Override
	public void blacklistSubscriber() {
		String csvFile = appConstants.blackListFilepath+File.separator+"_new";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			File fileSource = new File(csvFile);
			File file[] = fileSource.listFiles();
			if(fileSource!=null){
				br = new BufferedReader(new FileReader(file[0].getAbsolutePath()));
			
				while ((line = br.readLine()) != null) {
	
					// use comma as separator
					String[] msisdns = line.split(cvsSplitBy);
	
					System.out.println("BlackListingTask " + msisdns[0]);
					
					try {
						Subscriber subscriber = cMDao.getSubscriberByMSISDN(msisdns[0]);
						if(subscriber!=null){
							int subDetailCount = 0;
							for(SubscriberDetails subDetails: subscriber.getSubscriberDetails()){
								if(subDetails.getIsOldUserDetails().intValue() == 0){
									subscriber.getSubscriberDetails().get(subDetailCount).setBlacklistFlag(1);
									cMDao.saveObject(subscriber);
								}
								subDetailCount = subDetailCount + 1;
							}
						}
					} catch (KycDaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}
				InputStream inStream = new FileInputStream(file[0]);
				OutputStream outStream  = new FileOutputStream(new File(appConstants.blackListFilepath+File.separator+"_done"+File.separator+file[0].getName()));
				byte[] bytesIn = new byte[1024];
				int length;
				while((length = inStream.read(bytesIn))>0){
					outStream.write(bytesIn, 0, length);
				}
				inStream.close();
				outStream.close();
				br.close();
			}
			if(file[0].exists()){
				file[0].delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/*@Override
	public void hotlineSubscriber() {
		String csvFile = appConstants.hotLineFilePath;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] msisdns = line.split(cvsSplitBy);

				System.out.println("BlackListingTask " + msisdns[0]);
				
				try {
					Subscriber subscriber = cMDao.getSubscriberByMSISDN(msisdns[0]);
					HPResponse hpResponse = integrationService.putOnHotLine(msisdns[0], msisdns[0]);
					if((boolean)hpResponse.getResult()){
						subscriber.getSubscriberDetails().get(0).setHotlineFlag(1);
						
						SubscriberWorkFlowDto subscriberWorkFlowDto = new SubscriberWorkFlowDto();

						subscriber.getSubscriberDetails().get(0).setWorkFlowStatus("HOTLINE");
						subscriber.getSubscriberDetails().get(0).setFinalStatusReason("HOTLINE DUE TO NONREGA");
						
						kycDaoService.saveOrUpdateEntity(subscriber);
						
						subscriberWorkFlowDto.setAction("HOTLINE");
						subscriberWorkFlowDto.setRoleName("CM");
						subscriberWorkFlowDto.setStatusReason("HOTLINE DUE TO NONREGA");
						subscriberWorkFlowDto.setSubscriberId(subscriber.getSubscriberId());
						subscriberWorkFlowDto.setUserId(0);

						subscriberWorkFlowDto = (SubscriberWorkFlowDto) subscriberManagementService
								.actionOnSubscriber(subscriberWorkFlowDto);
					}
				} catch (KycDaoException | HPException | BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
*/
	@SuppressWarnings({"unchecked" })
	@Override
	public void autoReject(){
		try {
			SearchSubscriberDto searchSubscriberDto = new SearchSubscriberDto();
			searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
			
			searchSubscriberDto.setStatus("BARRED");
			searchSubscriberDto.setRoleName("CM");
			
			//String noOfDays = appConstants.daysForRejection;
			ConfigData configData = cMDao.getConfigDataByDataId(new Integer(ConfigDataEnum.DAYS_TO_REJECT_NREG.getConfigDataId()));
			String noOfDays = configData.getConfigDataValue();
			searchSubscriberDto.setUpdatedOn(getDate(noOfDays));
			
			searchSubscriberDto.setCreatedOn(getDate(noOfDays));
			
			List<Subscriber> subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto, "AUTO_REJECT");
			System.out.println(subscriberList);
			for (Subscriber subscriber : subscriberList) {
				
				List<SubscriberDetails> subscriberDetailsList = subscriberDetailsDao.getSubscriberDetailsBarred(subscriber.getSubscriberId());
				
				SubscriberWorkFlowDto subscriberWorkFlowDto = new SubscriberWorkFlowDto();
				SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
				
			
				subscriberDetailsList.get(0).setFinalStatus("REJECTED");
				subscriberDetailsList.get(0).setWorkFlowStatus("REJECTED");
				subscriberDetailsList.get(0).setFinalStatusReason("REJECTED AFTER "+noOfDays+" DAYS OF BARRING");
				subscriberDetailsDto.setSubscriberDetailsId(subscriberDetailsList.get(0).getSubscriberDetailsId());
				
				subscriberWorkFlowDto.setSubscriberDetailsDto(subscriberDetailsDto);
				
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				subscriber.setUpdatedOn(currentTime);
				subscriberDetailsList.get(0).setUpdatedOn(currentTime);
				
				SubscriberWorkFlow subscriberWorkFlow = subscriberDetailsList.get(0).getSubscriberWorkFlow();
				
				if(subscriberWorkFlow == null){
					subscriberWorkFlow = new SubscriberWorkFlow();
				}

				subscriberWorkFlow.setAction("REJECTED");
				subscriberWorkFlow.setRoleName("AUTO_REJECT");
				subscriberWorkFlow.setStatusReason("REJECTED DUE TO BARRED STATUS FOR "+noOfDays+" DAYS");
				subscriberWorkFlow.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				subscriberWorkFlow.setSubscriberDetails(subscriberDetailsList.get(0));
				
				subscriberDetailsList.get(0).setSubscriberWorkFlow(subscriberWorkFlow);
				
				subscriber.setSubscriberDetails(subscriberDetailsList);
				
				subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
				
				SubscriberWorkFlowHistory subscriberWorkFlowHistory = new SubscriberWorkFlowHistory();
				subscriberWorkFlowHistory.setRoleName("AUTO_REJECT");
				for(SubscriberDetails subsDetails : subscriber.getSubscriberDetails()){
					if(subsDetails.getSubscriberDetailsId().intValue() == subscriberDetailsList.get(0).getSubscriberDetailsId().intValue()){
						subscriberWorkFlowHistory
						.setSubscriberWorkFlowId(subsDetails.getSubscriberWorkFlow().getSubscriberWorkFlowId());
					}
				}
				subscriberWorkFlowHistory.setAction("REJECTED");
				
				subscriberWorkFlowHistory = (SubscriberWorkFlowHistory) kycDaoService
						.saveOrUpdateEntity(subscriberWorkFlowHistory);
			}
			
		} catch (KycDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private Timestamp getDate(String noOfDays){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR,-Integer.parseInt(noOfDays));
		cal.set(Calendar.MILLISECOND,0);
		cal.add(Calendar.SECOND,0);
		cal.add(Calendar.MINUTE,0);
		cal.add(Calendar.HOUR,0);
		return new java.sql.Timestamp(cal.getTimeInMillis());
	}
	
	@Override
	@SuppressWarnings({"unchecked" })
	public void barSubscriber() throws IOException{
		try {
			SearchSubscriberDto searchSubscriberDto = new SearchSubscriberDto();
			searchSubscriberDto.setIsOldUserDetails(KycConstants.FALSE);
			
			searchSubscriberDto.setStatus("HOTLINE");
			//String noOfDays = appConstants.daysForBarring;
			ConfigData configData = cMDao.getConfigDataByDataId(new Integer(ConfigDataEnum.DAYS_TO_BARRING_NREG.getConfigDataId()));
			String noOfDays = configData.getConfigDataValue();
			searchSubscriberDto.setUpdatedOn(getDate(noOfDays));
			
			List<Subscriber> subscriberList = (List<Subscriber>) this.kycDaoService.getSubscriber(searchSubscriberDto, "AUTO_BAR");
			
			for (Subscriber subscriber : subscriberList) {
				
				List<SubscriberDetails> subscriberDetailsList = subscriberDetailsDao.getSubscriberDetailsHotline(subscriber.getSubscriberId());
				
				SubscriberDetailsDto subscriberDetailsDto = new SubscriberDetailsDto();
				
			
				subscriberDetailsList.get(0).setFinalStatus("BARRED");
				subscriberDetailsList.get(0).setWorkFlowStatus("BARRED");
				subscriberDetailsList.get(0).setFinalStatusReason("BARRED DUE TO HOTLINE STATUS FOR "+noOfDays+" DAYS");
				subscriberDetailsList.get(0).setHotlineFlag(0);
				subscriberDetailsList.get(0).setBarringSource("BULK_BARRING");
				
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				subscriber.setUpdatedOn(currentTime);
				subscriberDetailsList.get(0).setUpdatedOn(currentTime);
				
				subscriberDetailsDto.setSubscriberDetailsId(subscriberDetailsList.get(0).getSubscriberDetailsId());
				
				this.taskManagementService.addTask(TaskEnum.EMA_BARING.getTaskCode(),
						subscriber.getSubscriberId(), subscriberDetailsList.get(0).getSubscriberDetailsId().toString());
				
				SubscriberWorkFlow subscriberWorkFlow = subscriberDetailsList.get(0).getSubscriberWorkFlow();
				
				if(subscriberWorkFlow == null){
					subscriberWorkFlow = new SubscriberWorkFlow();
				}

				subscriberWorkFlow.setAction("BARRED");
				subscriberWorkFlow.setRoleName("AUTO_BARRING");
				subscriberWorkFlow.setStatusReason("BARRED DUE TO HOTLINE STATUS FOR "+noOfDays+" DAYS");
				subscriberWorkFlow.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				subscriberWorkFlow.setSubscriberDetails(subscriberDetailsList.get(0));
				
				subscriberDetailsList.get(0).setSubscriberWorkFlow(subscriberWorkFlow);
				
				subscriber.setSubscriberDetails(subscriberDetailsList);
				
				subscriber = (Subscriber) kycDaoService.saveOrUpdateEntity(subscriber);
				
				SubscriberWorkFlowHistory subscriberWorkFlowHistory = new SubscriberWorkFlowHistory();
				subscriberWorkFlowHistory.setRoleName("AUTO_BARRING");
				for(SubscriberDetails subsDetails : subscriber.getSubscriberDetails()){
					if(subsDetails.getSubscriberDetailsId().intValue() == subscriberDetailsList.get(0).getSubscriberDetailsId().intValue()){
						subscriberWorkFlowHistory
						.setSubscriberWorkFlowId(subsDetails.getSubscriberWorkFlow().getSubscriberWorkFlowId());
					}
				}
				subscriberWorkFlowHistory.setAction("BARRED");
				
				subscriberWorkFlowHistory = (SubscriberWorkFlowHistory) kycDaoService
						.saveOrUpdateEntity(subscriberWorkFlowHistory);

				
				//sms trigger for barring
				NotificationDto notificationDto = new NotificationDto();
				notificationDto.setMsisdn(subscriber.getMsisdn());
				String locale = appConstants.LOCALE;
				if(subscriberDetailsList.get(0).getLanguage() != null) {
					locale = subscriberDetailsList.get(0).getLanguage();
				}

				Templates templates = this.dataManagementService.getTemplates("en", SMS_CUSTOMER_NUMBER_BARRING, "sms");
				String templateContent  = MessageFormat.format(templates.getTemplateContent(),noOfDays);
				notificationDto.setTemplateContent(templateContent);
				notificationDto.setMsisdn(subscriber.getMsisdn());
				
				this.integrationService.notify(notificationDto);
			}
			
		} catch (KycDaoException | BusinessException | IntegrationServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/*@Override
	public void unHotlineSubscriber(){
		String csvFile = appConstants.unHotlinrfilePath;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] msisdns = line.split(cvsSplitBy);
				System.out.println("BlackListingTask " + msisdns[0]);
				try {
					Subscriber subscriber = cMDao.getSubscriberByMSISDN(msisdns[0]);
					HPResponse hpResponse = integrationService.unbarMSISDN(msisdns[0], msisdns[0]);
					if((boolean)hpResponse.getResult()){
						//subscriber.getSubscriberDetails().get(0).setHotlineFlag(0);
						cMDao.saveObject(subscriber);
					}
				} catch (KycDaoException | HPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}