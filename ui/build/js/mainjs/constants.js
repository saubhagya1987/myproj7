var debug = false;
define(['angular'],
		function(angular) {
		    'use strict';
		    var server = '';
		    var pub = "https://125.16.240.133:8463";//8089
		    var test = "https://10.56.188.37:8463";
		    var sau = "http://10.12.26.175:8081";
		    var prodpub = "http://41.77.1.58";
		    var prodpriv = "http://172.27.132.15:8081";
		    var reportServer = "";
		    var prodUrl = true;
		    if(prodUrl){
		    	reportServer = "http://172.27.132.15:8082";
//			    server = prodpub;
//			    server = prodpriv;
		    }else{
		    	reportServer = location.origin.replace(location.port,"8441");
				server = test;
//				server = pub;
			    //server = sau;
//			    server = prodpub;
//			    server = prodpriv;
		    }
		    return {
		    	secret_key : window.btoa('ani:Airtel@123'),
		    	blank_msg : "No response from server!!",
		    	errorMsg : 'Unable to connect to server!! Please Login Again..',
		    	login_API : server + '/kyczm/api/user/validateUser',
		    	logout_API : server + '/kyczm/client/invalidateToken/',
		    	forgot_API : server + '/kyczm/api/user/forgotPassword',
		    	resetPassword_API : server + '/kyczm/api/user/password/',
		    	resetCpPassword_API : server + '/kyczm/api/user/resetPassword',
		    	getAllData: 'assets/json/kyc_master.json',
		    	addSubscriber_API: server + '/kyczm/api/subscriber/registerNormalSubscriber',
		    	generateOtp_API : server + '/kyczm/api/subscriber/generateOtp',
		    	validateOtp_API : server + '/kyczm/api/subscriber/validateOtp',
		    	amRegistration_API : server + '/kyczm/api/subscriber/activateAm',
		    	approvedSubscriber_API : server + '/kyczm/api/subscriber/getApprovedSubscriber',
		    	getSubscriberData_API : server + '/kyczm/api/subscriber/getAllSubscriberList/msisdn',
		    	editSubscriber_API : server +'/kyczm/api/subscriber/updateSubscriber',
		    	checkUser_API : server + '/kyczm/api/user/isMsisdnAvailable/',
		    	checkAuuidAvailable_API : server + '/kyczm/api/user/isUserAvailbleByAuuId/',
		    	addExistingSubscriber_API : server + '/kyczm/api/subscriber/registerExistingSubscriberWithNewData',
		    	addUser_API : server + '/kyczm/api/provision/user',
		    	searchUser_API : server +'/kyczm/api/users/fetchUser',
		    	deleteUser_API : server + '/kyczm/api/user/',
		    	barredSubscriber_API : server + '/kyczm/api/subscriber/getBarredSubscribers',
		    	openCases_API : server +'/kyczm/api/subscriber/action/getCMSubscribers',
		    	actionSubscriber_API : server + '/kyczm/api/subscriber/action/OnSubscriber',
		    	getManageInput_API : server + '/kyczm/api/helper/getConfigData',
		    	updateManageInput_API : server + '/kyczm/api/helper/updateConfigData',
		    	getCommissionRule_API : server + '/kyczm/api/getCommissionRule',
		    	saveCommissionRule_API : server + '/kyczm/api/saveCommissionRule',
		    	getAllSubRoles_API : server + '/kyczm/api/helper/getAllRoles',
		    	customerUpload_API : server + '/kyczm/api/subscriber/bulk/register',
		    	userUpload_API : server + '/kyczm/api/user/bulk/register',
				reports_API : reportServer + '/jasperserver/flow.html?_flowId=viewReportFlow&standAlone=true&ParentFolderUri=/KYC_REPORTS/ZM&j_username=jasperadmin&j_password=jasperadmin&reportUnit=%2FKYC_REPORTS%2FZM%2F',
		    	imageURL: server + '/kyczm/api/subscriber/getSubscriberImage/',
		    	userImageURL: server + '/kyczm/api/user/getUserImage/',
		    	cp_access: [27,32,33,45,46,47,48,49,50,51,52,53],
		    	de_access: [29,30,31],
		    	sp_access: [34,35,36],
		    	admin_access: [21],
		    	snd_access: [43,44,38,39],
		    	bulk_access: [29,30,31,34,35,36,21],
		    	RSA_Publickey: "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCz07sHJeQkdOozjDX4MQJQJvKt9A898ySfwK++XLC66/uOByoF8L38SaFnIsNxUewkYt5LCq4AhNcD+0B0JAfi97ih6V3fIyBSMzS0RGEuz/exDhxsJvPztKg9Pp3UT7tRTWdiI4M724kpJD9Y53+Ks7xd3Va+uSEtNg7mvd4EEQIDAQAB",
		    	passphrase: "Secret Passphrase",
		    	IV: "b4d0febfe0df20d742b12650dd932101",
		    	salt: "3b1c7b4c914daeacc4853ee0ade912f4",
		    	aesUtil : new AesUtil(128,1000),//key_size,iteration_count as params
		    	token_key :"j0e2mABWvYaVVw98PrNnP6HablTXPklANWZ/Z+RGc0yVm5AWoA58pDexTnEvLkkYvPLbVgm854LR\n/Hkb7s+4KQFESju448KDFpx661z7uVAcQbqxwY5+40Tas6yTUw5Bi0uRWXbhCXaOMoIjh2jFIZsA\n77RPAcTK+GrcZGR4Cv7O7UOSvcx9HnAnFKSbfHoppe7W/8LAkf9p9kOVcLttacXYTLHnrtd3Yaka\nw24XcAxYRfaZSEmuD2F9b0R8NFzxX4IGOqqRk0SjkZNScSTstE4TY9k8fkYK777VTy7jYGyrdbRb\nPO+D2wMHO2+v84St1LCSXMK612xfNJYoU+XR+Q==\n",
		    	token_val : "loXdjRETCqMLksMQjYOW3Q==\n",
		    	APK_API : server + "/kyczm/api/download/apk",
		    	APK_LINK : "http://41.77.1.58/knowyourcustomerzambia.apk"
		    }
		}
)