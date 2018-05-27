/*package com.airtel.africa.tt.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.airtel.africa.tt.service.TimingTaskService;

//@Service("taskblacklisting")
@Component
//@DisallowConcurrentExecution
public class BlackListingTask extends QuartzJobBean {

	public static final String COUNT = "count";
	
	@Autowired
	private TimingTaskService timingTaskService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		String csvFile = "D:/kyc-ug-dir/_blacklist/_new/test.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] msisdns = line.split(cvsSplitBy);

				System.out.println("BlackListingTask " + msisdns[0]);
				
				timingTaskService.blacklist(msisdns[0]);

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
}*/