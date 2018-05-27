package com.airtel.user.helper.util;

import org.springframework.beans.factory.annotation.Value;

public class AppConstants {
	public final String MESSAGE_SEPARATOR = "\\#\\$";
	public final String COLUMN_SEPARATOR = "\\^";
	public final String KPI_SEPARATOR = "\\|";

	@Value("${success.code}")
	public String success_code;

	@Value("${failure.code}")
	public String failure_code;

	@Value("${success.status}")
	public String success_status;

	@Value("${failure.status}")
	public String failure_status;

	@Value("${msisdn.not.found}")
	public String msisdn_not_found;

	@Value("${data.not.found}")
	public String data_not_found;

	@Value("${retailer.not.found}")
	public String retailer_not_found;

	@Value("${retailer.found}")
	public String retailer_found;

	@Value("${registration.successful}")
	public String registration_successful;

	@Value("${registration.fail}")
	public String registration_fail;

	@Value("${survey.successful}")
	public String survey_successful;

	@Value("${survey.fail}")
	public String survey_fail;

	@Value("${dao.exception}")
	public String dao_exception;

	@Value("${upload.baseDir}")
	public String uploadFolder;

	@Value("${upload.hostname}")
	public String hostname;
	
	@Value("${mail_subject}")
	public String mail_subject;

	@Value("${mail_template}")
	public String mail_template;

	@Value("${mailreportTO}")
	public String mailreportto;

	@Value("${mailreportFrom}")
	public String mailreportfrom;

	@Value("${mailreportTocc1}")
	public String mailreporttocc1;

	@Value("${mailreportTocc2}")
	public String mailreporttocc2;

	@Value("${mailFromName}")
	public String mailsign;
	
	@Value("${date_format}")
	public String dateFormat;
	
	@Value("${date_time_format}")
	public String dateTimeFormat;
	
	@Value("${filesystem.dump_file_path}")
	public String dumpFilePath;
	
	@Value("${custFiletemp}")
	public String custFiletemp;

	@Value("${upload.customers_dir}")
	public String customers_dir;
	
	@Value("${upload.reatailers_dir}")
	public String reatailers_dir;
	
	@Value("${security.keypath}")
	public String seckey_path;
	
	
	@Value("${application.client.defaultpwd}")
	public String defaultpwd;
	
	@Value("${application.resourceid}")
	public String resourceid;
	
	@Value("${application.client.tokenvalidity}")
	public String tokenvalidity;
	
	@Value("${application.client.refreshtokenvalidity}")
	public String refreshtokenvalidity;
	
	@Value("${application.client.authority}")
	public String authority;
	
	@Value("${application.client.grants}")
	public String grants;
	
	@Value("${application.client.autoapprove}")
	public String autoapprove;
	
	@Value("${application.client.scope}")
	public String scope;
	

	
	@Value("${application.is_simswap_demo_enabled}")
	public String simswap_demo_enabled;
	

	@Value("${application.isdebug}")
	public String isdebug;

	@Value("${simswap_service_url}")
	public String simswap_url;
	
	@Value("${sv.simswap.demographic.request.collingtime}")
	public String demographic_colling;
	
	@Value("${sv.simswap.demographic.request.retry_count}")
	public String demographic_retry_count;
	
	@Value("${sv.simswap.demographic.request.retry_frequency}")
	public String demographic_retry_frequency;
	
	@Value("${sv.simswap.statuscheck.request.collingtime}")
	public String statuscheck_colling;
	
	@Value("${sv.simswap.statuscheck.request.retry_count}")
	public String statuscheck_retry_count;
	
	@Value("${sv.simswap.statuscheck.request.retry_frequency}")
	public String statuscheck_retry_frequency;
	
	@Value("${sv.simswap.unbarr.request.collingtime}")
	public String unbarr_colling;
	
	@Value("${sv.simswap.unbarr.request.retry_count}")
	public String unbarr_retry_count;
	
	@Value("${sv.simswap.unbarr.request.retry_frequency}")
	public String unbarr_retry_frequency;
	
	@Value("${sv.simswap.activationcheck.request.collingtime}")
	public String activationcheck_colling;
	
	@Value("${sv.simswap.activationcheck.request.retry_count}")
	public String activationcheck_retry_count;
	
	@Value("${sv.simswap.activationcheck.request.retry_frequency}")
	public String activationcheck_retry_frequency;
	
	@Value("${validation_1}")
	public String v_1;
	

	@Value("${validation_2}")
	public String v_2;
	

	@Value("${validation_3}")
	public String v_3;
	

	@Value("${validation_4}")
	public String v_4;
	

	@Value("${validation_5}")
	public String v_5;
	

	@Value("${validation_6}")
	public String v_6;
	

	@Value("${validation_7}")
	public String v_7;
	

	@Value("${validation_8}")
	public String v_8;
	

	@Value("${validation_9}")
	public String v_9;
	

	@Value("${validation_10}")
	public String v_10;
	

	@Value("${validation_11}")
	public String v_11;
	

	@Value("${validation_12}")
	public String v_12;
	

	@Value("${validation_13}")
	public String v_13;
	

	@Value("${validation_14}")
	public String v_14;
	

	@Value("${validation_15}")
	public String v_15;
	

	@Value("${validation_16}")
	public String v_16;
	

	@Value("${validation_17}")
	public String v_17;
	

	@Value("${validation_18}")
	public String v_18;
	

	@Value("${validation_19}")
	public String v_19;
	
	@Value("${validation_20}")
	public String v_20;
	
	@Value("${validation_21}")
	public String v_21;
	
	@Value("${validation_22}")
	public String v_22;
	
	@Value("${validation_23}")
	public String v_23;
	
	@Value("${validation_24}")
	public String v_24;
	
	@Value("${validation_25}")
	public String v_25;
	
	@Value("${validation_26}")
	public String v_26;
	
	@Value("${validation_27}")
	public String v_27;
	
	@Value("${validation_28}")
	public String v_28;
	
	@Value("${validation_29}")
	public String v_29;
	
	@Value("${validation_30}")
	public String v_30;
	
	@Value("${validation_31}")
	public String v_31;
	
	@Value("${validation_subscriber_name}")
	public String v_32;
	
	@Value("${validation_subscriber_phone_no}")
	public String v_33;
	
	@Value("${validation_last_recharge}")
	public String v_34;
	
	@Value("${validation_freq_dial_no}")
	public String v_35;
	
	@Value("${validation_alt_phone_no}")
	public String v_36;
	
	@Value("${validation_subscriber_image}")
	public String v_37;
	
	@Value("${validation_new_sim_no}")
	public String v_38;	
	
	public String geterrorcode(Integer err_code){
		String msg="";
		switch (err_code) {
		case 1:
			msg=v_1;
			break;
		case 2:
			msg=v_2;
			break;
		case 3:
			msg=v_3;
			break;
		case 4:
			msg=v_4;
			break;
		case 5:
			msg=v_5;
			break;
		case 6:
			msg=v_6;
			break;
		case 7:
			msg=v_7;
			break;
		case 8:
			msg=v_8;
			break;
		case 9:
			msg=v_9;
			break;
		case 10:
			msg=v_10;
			break;
		case 11:
			msg=v_11;
			break;
		case 12:
			msg=v_12;
			break;
		case 13:
			msg=v_13;
			break;
		case 14:
			msg=v_14;
			break;
		case 15:
			msg=v_15;
			break;
		case 16:
			msg=v_16;
			break;
		case 17:
			msg=v_17;
			break;
		case 18:
			msg=v_18;
			break;
		case 19:
			msg=v_19;
			break;
		case 20:
			msg=v_20;
			break;
		case 21:
			msg=v_21;
			break;
		case 22:
			msg=v_22;
			break;
		case 23:
			msg=v_23;
			break;
		case 24:
			msg=v_24;
			break;
		case 25:
			msg=v_25;
			break;
		case 26:
			msg=v_26;
			break;
		case 27:
			msg=v_27;
			break;
		case 28:
			msg=v_28;
			break;
		case 29:
			msg=v_29;
			break;
		case 30:
			msg=v_30;
			break;
		case 31:
			msg=v_31;
			break;
		case 32:
			msg=v_32;
			break;
		case 33:
			msg=v_33;
			break;
		case 34:
			msg=v_34;
			break;
		case 35:
			msg=v_35;
			break;
		case 36:
			msg=v_36;
			break;
		case 37:
			msg=v_37;
			break;
		case 38:
			msg=v_38;
			break;
		default:
				break;
		}	
		return msg;
		}
	
	@Value("${initiated}")
	public String initiated;
	
	@Value("${rejected}")
	public String rejected;
	
	@Value("${swap.done}")
	public String swap_done;
	
	@Value("${subject}")
	public String subject;
	
	@Value("${smtp.from}")
	public String smtp_from;
	
	@Value("${SMTP_HOST}")
	public String SMTP_HOST;

	@Value("${SMTP_PORT}")
	public String SMTP_PORT;

	@Value("${SMTP_USER_NAME}")
	public String SMTP_USER_NAME;

	@Value("${SMTP_PWD}")
	public String SMTP_PWD;
}


