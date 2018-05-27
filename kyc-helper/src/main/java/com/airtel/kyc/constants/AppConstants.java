package com.airtel.kyc.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	@Value("${thread.locale}")
	public String LOCALE;

	@Value("${application.isSv_validation_enabled}")
	public String sv_validation_enabled;
	
	@Value("${sv.kyc.sim_msisdn.validation.collingtime}")
	public long sv_validation_colling;

	@Value("${application.sv_imsi_fetch_enabled}")
	public String sv_imsi_fetch_enabled;

	@Value("${application.sv_demographic_update_enabled}")
	public String sv_updation_enabled;
	
	@Value("${application.sv_status_activate_enabled}")
	public String sv_status_activate_enabled;

	@Value("${application.hp_unbar_enabled}")
	public String hp_unbar_enabled;

	@Value("${application.hp_bar_enabled}")
	public String hp_bar_enabled;

	@Value("${application.am_activate_enabled}")
	public String am_activate_enabled;

	@Value("${sv.kyc.sim_msisdn.validation.retry_count}")
	public String sv_validation_retry_count;
	
	@Value("${sv.kyc.sim_msisdn.status.retry_frequency}")
	public String sv_staus_update_retry_frequency;

	@Value("${sv.kyc.sim_msisdn.update.retry_count}")
	public String sv_update_retry_count;
	
	@Value("${sv.kyc.sim_msisdn.status.retry_count}")
	public String sv_status_update_count;

	@Value("${sv.kyc.sim_msisdn.update.collingtime}")
	public String sv_update_collingtime;

	@Value("${am.kyc.update.retry_count}")
	public int airtel_money_retry_count;

	@Value("${am.kyc.update.collingtime}")
	public String airtel_money_collingtime;

	@Value("${kyc.provision.retry_count}")
	public String provision_user_retry_count;

	@Value("${kyc.provision.cooling.time}")
	public String provision_user_cooling_time;

	@Value("${kyc.provision.user.retry_frequency}")
	public String provision_user_retry_frequency;

	@Value("${hp.kyc.unbar.collingtime}")
	public String hp_unbarring_colling;

	@Value("${hp.kyc.unbar.retry_count}")
	public String hp_validation_retry_count;

	@Value("${hp.kyc.unbar.retry_frequency}")
	public String hp_validation_retry_frequency;

	@Value("${hp.kyc.bar.collingtime}")
	public String hp_barring_colling;

	@Value("${hp.kyc.bar.retry_count}")
	public String hp_barring_retry_count;

	@Value("${hp.kyc.bar.retry_frequency}")
	public String hp_barring_retry_frequency;

	@Value("${application.unbarring.sleeptime}")
	public String unbarr_sleep;

	@Value("${upload.hostname}")
	public String hostname;

	@Value("${sv.kyc.imsi_fetch.retry_frequency}")
	public String sv_imsi_fetch_retry_frequency;

	@Value("${sv.kyc.sim_msisdn.update.retry_frequency}")
	public String sv_update_retry_frequency;

	@Value("${am.kyc.update.retry_frequency}")
	public String am_update_retry_frequency;

	@Value("${application.thread.sv.validation}")
	public Long APPLICATION_THREAD_SV_VALIDATION_SLEEP;
	
	@Value("${application.thread.sv.status.sleep}")
	public Long THREAD_NAME_SV_STATUS_UPDATION_SLEEP;

	@Value("${application.thread.sv.updation}")
	public Long APPLICATION_THREAD_SV_UPDATION_SLEEP;

	@Value("${application.thread.hp.unbar}")
	public Long APPLICATION_THREAD_HP_UNBAR_SLEEP;

	@Value("${application.thread.hp.bar}")
	public Long APPLICATION_THREAD_HP_BAR_SLEEP;

	@Value("${application.thread.am.updation}")
	public Long APPLICATION_THREAD_AM_UPDATION_SLEEP;

	@Value("${application.thread.provision.user}")
	public Long APPLICATION_THREAD_PROVISION_USER_SLEEP;
	
	@Value("${application.thread.ema.unbar}")
	public Long APPLICATION_THREAD_EMA_UNBAR_SLEEP;

	@Value("${application.thread.ema.bar}")
	public Long APPLICATION_THREAD_EMA_BAR_SLEEP;
	
	@Value("${application.thread.ema.unbar}")
	public Long APPLICATION_THREAD_EMA_UNBAR_BULK_SLEEP;
	
	@Value("${kyc.cm.logged.in.time}")
	public String loggedInTime;
	
	@Value("${kyc.cm.logged.in.users}")
	public String loggedInUser;	
	
	@Value("${kyc.cm.time.span.format}")
	public String timeSpanFormat;	
	
	@Value("${kyc.blasklist.file.path}")
	public String blackListFilepath;
	
	@Value("${kyc.hotline.file.path}")
	public String hotLineFilePath;
	
	@Value("${kyc.unhotline.file.path}")
	public String unHotlinrfilePath;
	
	@Value("${kyc.barring.days.nonrg}")
	public String daysForBarring;
	
	@Value("${kyc.barred.days.before.rejection}")
	public String daysForRejection;

	@Value("${date.format}")
	public String dateFormat;
	
	@Value("${kyc.retailer.no}")
	public String retailerNo;
	
	@Value("${ema.kyc.unbar.collingtime}")
	public String ema_unbarring_colling;

	@Value("${ema.kyc.unbar.retry_count}")
	public String ema_validation_retry_count;

	@Value("${ema.kyc.unbar.retry_frequency}")
	public String ema_validation_retry_frequency;

	@Value("${ema.kyc.bar.collingtime}")
	public String ema_barring_colling;
	
	@Value("${ema.kyc.bar.retry_count}")
	public String ema_barring_retry_count;

	@Value("${ema.kyc.bar.retry_frequency}")
	public String ema_barring_retry_frequency;
	
	@Value("${sv.kyc.sim_msisdn.validation.retry_frequency}")
	public String sv_validation_retry_frequency;
	
	@Value("${application.ema_unbar_enabled}")
	public String ema_unbar_enabled;

	@Value("${application.ema_bar_enabled}")
	public String ema_bar_enabled;
	
	@Value("${application.ema_unbar_enabled}")
	public String ema_unbar_bulk_enabled;
	
	@Value("${gkyc.status.api.userid}")
	public String gkycApiUser;
	
	@Value("${gkyc.status.api.password}")
	public String gkycApiPassword;
	
	@Value("${bi.dump.destination.ip}")
	public String biDumpDestIp;
	
	@Value("${bi.dump.destination.port}")
	public int biDumpDestport;
	
	@Value("${bi.dump.destination.user}")
	public String biDumpDestUser;
	
	@Value("${bi.dump.destination.password}")
	public String biDumpDestPassword;
	
	@Value("${bi.dump.destination.file.location}")
	public String biDumpDestFileLocation;
	
	@Value("${cron.controller.autoassign.flag}")
	public String cronAutoassignFlag;
	
	@Value("${cron.controller.blacklist.flag}")
	public String cronBlacklistFlag;
	
	@Value("${cron.controller.bar.flag}")
	public String cronBarFlag;
	
	@Value("${cron.controller.reject.flag}")
	public String cronRejectFlag;
	
	@Value("${cron.controller.bi.flag}")
	public String cronBIFlag;
}