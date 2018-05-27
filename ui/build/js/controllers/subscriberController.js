'use strict';
define(['angular'], function(angular) {
	angular.module('zambiaApp')
	.controller('subscriberController', ['$scope','httpService','API','genericService','$filter','$state','genericFactory', function ($scope,httpService,API,genericService,$filter,$state,genericFactory) {
		$scope.initImages = function(){
			$scope.idFrontDocumentId = document.getElementById('idFrontImg');
			$scope.idBackDocumentId = document.getElementById('idBackImg');
			$scope.physicalFormDocumentId = document.getElementById('physicalFormIdImg');
		}
		$scope.page = {
				slot : 10,
				startIndex : 0
		}
		$scope.init = function(){
			$scope.simSerialPrefix = "";
			if($state.current.name=='dashboard.editSubscriber'){
				$scope.heading = "Edit Customer";
				$scope.sendOTP = true;
			}else if($state.current.name=='dashboard.editNotApprovedSubscriber'){
				$scope.heading = "Edit Not Approved Customer";
				$scope.sendOTP = false;
			}else if($state.current.name=='dashboard.editBarredSubscriber'){
				$scope.heading = "Edit Barred Customer";
				$scope.sendOTP = false;
			}else if($state.current.name=='dashboard.addSubscriberNewSim'){
				$scope.heading = "New Sim from Existing Customer";
				$scope.sendOTP = true;				
			}
			else if($state.current.name=='dashboard.searchCustomer'){
				$scope.heading = "Search Customer";
			}
			$scope.maxDate = new Date().setFullYear(new Date().getFullYear() - 18);
			$scope.minDate = new Date().setFullYear(new Date().getFullYear() - 100);
			$scope.SubscriberObj = {"userDetail" : {"userDepartment":[] } , "roles" : [],"subRole":[],"subRoles":[], "location" : {}};
			$scope.templates = {editSubscriberModal:null};
			$scope.addSubscriberObj={
					"subscriberData": {
						"msisdn": null,//222222222,//
						"simSerialNumber": null,//22222,//
						"kycTansactionId": null,
						"registrationType": "normal",
						"createdBy": null,
						"registrationMode": "Online",
						"subscriberDetails": [{
							"firstName": null,//"gabva",//
							"middleName": null,//"vafkafj",//
							"lastName": null,//"nbariun",//
							"email": null,//"adf@sfg.com",//
							"dateOfBirth":null,//"05-02-2018",
							"submitedOn": null,
							"syncedOn": null,
							"imei" : "GUI",
							"onlineOfflineFlag": "Yes",
							"gender": "male",
							"address": {
								"district": null,
								"permanentAddress":null,//"201 asdflkj",//
								"countryId": 1,
							},
							"placeOfBirth": null,
							"nationality": "ZM",
							"subscriberType":"Zambian",
							"caseType": "NEW",
							"isMinor": "No",
							"proxyForRegistration": "No",
							"amAccount": "Yes",
							"alternateNo": null,//"666666666",//
							"subscriberIdDetails": [{
								"idImageFront": null,
								"idImageFrontData": null,
								"idImageBack": null,
								"idImageBackData": null,
								"physicalFormId": null,
								"idType": null,
								"idNumber": null//"123412341234",//
							}]
						}]
					},
					"userDto": {},
					"userFlag": 0
				}
//			var allinfo = genericService.getAllData();
//			if((typeof allinfo === 'object' || typeof allinfo === 'function') && typeof allinfo.then === 'function'){
//				allinfo.then(function(data){
//					$scope.countries = data['countries'];
//					$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId = $filter('filter')($scope.countries,{countryName:'ZAMBIA'})[0];
//					$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality = $filter('filter')($scope.countries,{countryName:'ZAMBIA'})[0];
//					$scope.districts = data['districts'];
//					$scope.ids = data['ids'];
//				});	
//			}else{
				$scope.countries = allinfo['countries'];
				$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId = $filter('filter')($scope.countries,{countryName:'ZAMBIA'})[0];
				$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality = $filter('filter')($scope.countries,{countryName:'ZAMBIA'})[0];
				$scope.districts = allinfo['districts'];
				$scope.ids = allinfo['ids'];
//			}
			$scope.imagesObject = {physicalFormId:null,idFront:null,idBack:null};
			//$scope.imagesObject.loadImage = true;
			$scope.initImages();
		}();
		
		$scope.checkIdAvailable = function(id){
			if(id && id.is_mandatory==1)
				$scope.imagesObject.idNoRequired = true;
			else
				$scope.imagesObject.idNoRequired = false;
			$scope.imagesObject.change = true;
			$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idNumber = null;
			$scope.imagesObject.idFront = null;
			$scope.idFrontDocumentId.value = null;
		}
		
		$scope.shouldShow = function (id) {
			if($scope.addSubscriberObj && $scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberType && id)
				return ((id.id_type==0 || id.id_type==($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberType=='Zambian'?1:2)) && ($scope.addSubscriberObj.subscriberData.subscriberDetails[0].amAccount=="Yes"?(id.is_am==1?true:false):true))
		}
		
		$scope.addSubscriber = function(){
			if($scope.subscriberForm.$invalid){
				return;
			}
			var url = null;
			$scope.finalSubscriberData = angular.copy($scope.addSubscriberObj);
			if($state.current.name=='dashboard.addSubscriber'){
				url = API.addSubscriber_API;
//				if($scope.finalSubscriberData.simSerialNumber.indexOf($scope.simSerialPrefix) != 0)
				$scope.finalSubscriberData.subscriberData.simSerialNumber = $scope.simSerialPrefix + $scope.finalSubscriberData.subscriberData.simSerialNumber;
				$scope.finalSubscriberData.subscriberData.kycTansactionId = "kyc" + genericFactory.getRandomSpan() + "" + new Date().getTime();
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.district.districtName;
			}else if($state.current.name=='dashboard.addSubscriberNewSim'){
				url = API.addExistingSubscriber_API;
				$scope.finalSubscriberData.subscriberData.simSerialNumber = $scope.simSerialPrefix + $scope.finalSubscriberData.subscriberData.simSerialNumber;
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].caseType = "EXISTING";
				$scope.finalSubscriberData.subscriberData.kycTansactionId = "kyc" + genericFactory.getRandomSpan() + "" + new Date().getTime();
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.district.districtName;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].version;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idNo;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.addressId;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idType;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idNumber;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFront;
				delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBack;
			}		
			if($scope.imagesObject.idFront && $scope.imagesObject.idFront.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFrontData = $scope.imagesObject.idFront.base64;
			}
			if($scope.imagesObject.idBack && $scope.imagesObject.idBack.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBackData = $scope.imagesObject.idBack.base64;
			}
			if($scope.imagesObject.physicalFormId && $scope.imagesObject.physicalFormId.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].physicalFormId = $scope.imagesObject.physicalFormId.base64;
			}
			if ($scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idType) {
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFront = $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idType + "_FRONT";
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBack = $scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idType + "_BACK";
			}
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].submitedOn = new Date().getTime();
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].syncedOn = new Date().getTime();
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].onlineOfflineFlag = "Yes";
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.countryId = $scope.countries[0].countryId;
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].nationality = $scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality.isoCode;
			genericFactory.getIp(function(ip){
				$scope.finalSubscriberData.subscriberData.ipAddress = ip;
				httpService.post(url,genericFactory.getEncryption($scope.finalSubscriberData)).then(function(res){
					if(res.data.responseDto.responseCode=="200"){
						if($state.current.name=='dashboard.addSubscriberNewSim' || $state.current.name=='dashboard.addSubscriber'){
							angular.element('#editSubscriberModal').modal('hide');
							genericFactory.showNotification("Number Successfully Registered");
						}else if($state.current.name=='dashboard.editSubscriber'){
							angular.element('#editSubscriberModal').modal('hide');
							genericFactory.showNotification("Number Successfully Edited");
						}
						$scope.showOtp = false;
						$scope.resetSubcriberForm();
					}
					else{
						if(res.error)
							genericFactory.showNotification(res.error_description);
						else
							genericFactory.showNotification(res.data.responseDto.responseDescription);
					}
					
				},function(err){
					genericFactory.showNotification(API.errMsg);
				})
			});
		}
		
		//Reset SubscriberForm
		$scope.resetSubcriberForm = function(){
			$scope.subscriberForm.$setPristine();
			$scope.subscriberForm.$setUntouched();
			$scope.addSubscriberObj={
					"subscriberData": {
						"msisdn": null,
						"simSerialNumber": null,
						"kycTansactionId": null,
						"registrationType": "normal",
						"createdBy": null,
						"registrationMode": "Online",
						"subscriberDetails": [{
							"firstName": null,
							"middleName": null,
							"lastName": null,
							"email": null,
							"dateOfBirth": null,
							"submitedOn": null,
							"syncedOn": null,
							"imei" : "GUI",
							"onlineOfflineFlag": "Yes",
							"gender": null,
							"address": {
								"district": null,
								"permanentAddress":null,
								"countryId": null,
							},
							"placeOfBirth":null,
							"nationality": "ZM",
							"subscriberType":"Zambian",
							"caseType": "NEW",
							"isMinor": "No",
							"proxyForRegistration": "No",
							"amAccount": "Yes",
							"alternateNo": null,
							"subscriberIdDetails": [{
								"idImageFront": null,
								"idImageFrontData": null,
								"idImageBack": null,
								"idImageBackData": null,
								"idType": null,
								"idNumber": null
							}]
						}]
					},
					"userDto": {},
					"userFlag": 0
				}
			$scope.addSubscriberObj.subscriberData.subscriberDetails[0].registeredOn = new Date().getTime();
			$scope.resetImages();
		}
		$scope.resetImages = function(){
			if($scope.idFrontDocumentId)
				$scope.idFrontDocumentId.value = null;
			if($scope.idBackDocumentId)
				$scope.idBackDocumentId.value = null;
			if($scope.physicalFormDocumentId)
				$scope.physicalFormDocumentId.value = null;
			if($scope.imagesObject.idFront)
				$scope.imagesObject.idFront = null;
			if($scope.imagesObject.idBack)
				$scope.imagesObject.idBack = null;
			if($scope.imagesObject.physicalFormId)
				$scope.imagesObject.physicalFormId = null;
		}
		//Edit subscriber Controller 
		$scope.search = {
				"msisdn" : null		
		}
		$scope.generateOtp = function(){
			genericFactory.getIp(function(ip){
				$scope.search.ipAddress = ip;
				$scope.showRegistrationBlock = false;
				httpService.post(API.generateOtp_API,genericFactory.getEncryption($scope.search)).then(function(res){
					if(res.data.responseDto.responseCode=="200"){
						$scope.showOtp = true;
						genericFactory.showNotification("OTP sent to Mobile");
					}else{
						genericFactory.showNotification(res.data.responseDto.responseDescription);
					}
				},function(err){
					genericFactory.showNotification(API.errorMsg);;
				})
			})
		}
		
		$scope.validateOtp = function(otp){
			if($scope.subscriberForm)
				$scope.subscriberForm.$setPristine();
			if($state.current.name=='dashboard.editSubscriber' || $state.current.name=='dashboard.addSubscriberNewSim'){
				$scope.search.otp = otp;
			}
			httpService.post(API.validateOtp_API,genericFactory.getEncryption($scope.search)).then(function(res){
				if(res.data.responseDto.responseCode=="200"){
					if(API.aesUtil.decrypt(API.salt,API.IV,API.passphrase,res.data.responseDto.body).substring(9,15)==$scope.search.otp){
						$scope.showOtp = false;
						$scope.getAllDetails();
					}
					else
						genericFactory.showNotification("Invalid Code");
						
				}else
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				
				}),function(err){
					genericFactory.showNotification(API.errorMsg)
				
					}
		}
		$scope.getAllDetails = function(){
			if($state.current.name=='dashboard.editBarredSubscriber'){
				$scope.getBarredSubscriber();
				return;
			}
			httpService.post(API.getSubscriberData_API,genericFactory.getEncryption({msisdn:$scope.search.msisdn})).then(function(res){
						if(res.data.responseDto.responseCode == 200){
							var data = res.data.subscriberSearchResponseDto.rows[0];
							$scope.addSubscriberObj={
									"subscriberData": {
										"registrationType": "normal",
										"createdBy": data.createdBy,
										"registrationMode": "Online",
										"subscriberDetails": [{
											"imei" : "GUI",
											"subscriberIdDetails": data.subscriberDetails[0].subscriberIdDetails,
											"address": data.subscriberDetails[0].address,
											"placeOfBirth":data.subscriberDetails[0].placeOfBirth,
											"caseType": "EDIT",
											"simSerialNumber": data.subscriberDetails[0].simSerialNumber,
											"nationality":  data.subscriberDetails[0].nationality,
											"subscriberDetailsId": data.subscriberDetails[0].subscriberDetailsId,
											"firstName": data.subscriberDetails[0].firstName,
											"lastName": data.subscriberDetails[0].lastName,
											"email": data.subscriberDetails[0].email,
											"dateOfBirth": data.subscriberDetails[0].dateOfBirth,
											"isMinor": data.subscriberDetails[0].isMinor,
											"gender": data.subscriberDetails[0].gender,
											"amAccount": data.subscriberDetails[0].amAccount,
											"alternateNo": data.subscriberDetails[0].alternateNo,
											"isImageEdited": "NO",
											"proxyForRegistration": data.subscriberDetails[0].proxyForRegistration,
											"subscriberType": data.subscriberDetails[0].subscriberType
										}],
										"subscriberId": data.subscriberId,
										"msisdn": data.msisdn
									},
									"userDto": {},
									"userFlag": 0
								}

							$scope.imagesObject.idFrontPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFront);
							$scope.imagesObject.idBackPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBack);
							$scope.imagesObject.idPhysicalFormPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,"physicalForm");	
							if($scope.addSubscriberObj.subscriberData.subscriberDetails[0].address)
								$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId =  $filter('filter')($scope.countries,{countryId:$scope.countries[0].countryId})[0];
							$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality =  $filter('filter')($scope.countries,{isoCode:$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality})[0];
//							$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idType = $filter('filter')($scope.ids,{id_desc:$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idType})[0];
							if($state.current.name=='dashboard.editSubscriber'){
								$scope.imagesObject.change = false;
								$scope.disable = {kycNotApproved:true};
								$scope.heading = "Edit Customer Information";
								$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberEditModal.html';
							}else if($state.current.name=='dashboard.editNotApprovedSubscriber'){
								//$scope.addSubscriberObj = res.data.subscriberSearchResponseDto.rows[0];
								$scope.imagesObject.change = false;
								$scope.disable = {kycNotApproved:false};
								$scope.heading = "Edit Not Approved Customer Information";
								$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberEditModal.html';
							}else if($state.current.name=='dashboard.addSubscriberNewSim'){
								$scope.heading = "Add New Customer From Existing Customer";	
								$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberNewSimModal.html';
							}
							else if($state.current.name=='dashboard.subscriberAmReg'){
								$scope.imagesObject.change = false;
								$scope.disable = {kycNotApproved:true};
								$scope.heading = "Edit Customer Information";
								$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberAmModal.html';
							}
							//$scope.showAddSubscriberModal  =true;
							angular.element('#editSubscriberModal').modal();
							setTimeout(function(){
								$scope.resetImages();
								$scope.initImages();
							},1000)							
						}else{
							genericFactory.showNotification(res.data.responseDto.responseDescription);							
						}
					},function(err){
						genericFactory.showNotification(API.errorMsg)
					})
		}
//		$scope.overlays = [{x : 50, y:155, w:106, h:29, color:'#00FF00'}];
		$scope.addUser = function(){
			if($scope.addUserForm.$invalid){
				$scope.addUserForm.$submitted = true;
				return;
			}
			$scope.userForm.transaction_detail = {
					"client_transaction_id": new Date().getTime(),
					"transaction_by_msisdn": null,
					"transaction_by_deviceid": null,
					"transaction_mode": "online",
					"transaction_type": "USER_ADD",
					"transaction_start": new Date().getTime(),
					"transaction_end": new Date().getTime(),
					"transaction_lat": null,
					"transaction_long": null,
					"transaction_appVersion": null,
					"transaction_os": null
				}
			$scope.finalUserData = angular.copy($scope.userForm);
			delete $scope.finalUserData.topRole;
			delete $scope.finalUserData.users[0].department.department_name;
			$scope.finalUserData.users[0].userRole[0].role = {role_id:$scope.userForm.users[0].userRole[0].role.role_id}
			if($scope.userForm.users[0].userdetail.zone){
				$scope.finalUserData.users[0].userdetail.zone = $scope.userForm.users[0].userdetail.zone.zone;
			}
			if($scope.userForm.users[0].userdetail.territory){
				$scope.finalUserData.users[0].userdetail.territory = $scope.userForm.users[0].userdetail.territory.territory;
			}
			genericFactory.getIp(function(ip){
				$scope.finalUserData.ipAddress = ip;
				httpService.post(API.addUser_API,genericFactory.getEncryption($scope.finalUserData)).then(function(res){
					if(res.data.status.statusCode=="200"){
						angular.element('#createUserModal').modal('hide');
						genericFactory.showNotification("User Successfully Registered");
					}else{
						genericFactory.showNotification(res.data.status.statusMessage);
					}				
				},function(error){
					var errors = "Unable to connect to server...Please try again.";
					genericFactory.showNotification(errors);
				})
			});
		}

		$scope.getSubRoles = function(roleId,data){
			$scope.subRoles = null;
			$scope.subRoles = $filter('filter')($scope.roles,{role_group_id:roleId})[0].role;
			if(roleId == 201){
				$scope.mandatory = true;
			}else{
				$scope.mandatory = false;
			}
		}
		$scope.getTerritory = function(zone){
			$scope.territories = null;
			$scope.territories = $filter('filter')($scope.zones,{zone:zone});
		}
		$scope.editSubscriber = function(sData){
			if($state.current.name == 'dashboard.addSubscriberNewSim'){
				$scope.addSubscriber();
				return;
			}
			$scope.finalSubscriberData = angular.copy($scope.addSubscriberObj);
			if($scope.imagesObject.idFront && $scope.imagesObject.idFront.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFrontData = $scope.imagesObject.idFront.base64;
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].isImageEdited = "Yes";
			}else{
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFrontData = genericFactory.getBase64Image($scope.idFrontDocumentId)
			}
			if($scope.imagesObject.idBack && $scope.imagesObject.idBack.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBackData = $scope.imagesObject.idBack.base64;
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].isImageEdited = "Yes";
			}else{
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBackData = genericFactory.getBase64Image($scope.idBackDocumentId)
			}
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].physicalFormId = genericFactory.getBase64Image($scope.physicalFormDocumentId);
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.countryId = $scope.countries[0].countryId;
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].nationality = $scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality.isoCode;
			delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.district.districtName;
			genericFactory.getIp(function(ip){
				$scope.finalSubscriberData.subscriberData.ipAddress = ip;
				httpService.post(API.editSubscriber_API,genericFactory.getEncryption($scope.finalSubscriberData)).then(function(res){
					if(res.data.responseDto.responseCode=="200"){
						angular.element('#editSubscriberModal').modal('hide');
						genericFactory.showNotification("Subscriber Successfully Edited");
					}else{
						genericFactory.showNotification(res.data.status.statusMessage);
					}
				},function(err){
					genericFactory.showNotification(API.errorMsg);
				})
			});
			
		}
		$scope.getBarredSubscriber = function(){
			const payload = {
				'startIndex' : 0,
				'slot' : 1,
				'msisdn': $scope.search.msisdn
			}
			httpService.post(API.barredSubscriber_API,genericFactory.getEncryption(payload)).then(function(res){
				if(res.data.responseDto.responseCode == 200){
					var data = res.data.subscriberSearchResponseDto.rows[0];
					$scope.addSubscriberObj={
							"subscriberData": {
								"registrationType": "normal",
								"createdBy": data.createdBy,
								"registrationMode": "Online",
								"subscriberDetails": [{
									"subscriberIdDetails": data.subscriberDetails[0].subscriberIdDetails,
									"address": data.subscriberDetails[0].address,
									"caseType": "EDIT",
									"imei" : "GUI",
									"simSerialNumber": data.subscriberDetails[0].simSerialNumber,
									"placeOfBirth": data.subscriberDetails[0].placeOfBirth,
									"nationality": data.subscriberDetails[0].nationality,
									"subscriberDetailsId": data.subscriberDetails[0].subscriberDetailsId,
									"firstName": data.subscriberDetails[0].firstName,
									"lastName": data.subscriberDetails[0].lastName,
									"email": data.subscriberDetails[0].email,
									"dateOfBirth": data.subscriberDetails[0].dateOfBirth,
									"isMinor": data.subscriberDetails[0].isMinor,
									"gender": data.subscriberDetails[0].gender,
									"amAccount": data.subscriberDetails[0].amAccount,
									"alternateNo": data.subscriberDetails[0].alternateNo,
									"isImageEdited": "NO",
									"proxyForRegistration": data.subscriberDetails[0].proxyForRegistration,
									"subscriberType": data.subscriberDetails[0].subscriberType
								}],
								"subscriberId": data.subscriberId,
								"msisdn": data.msisdn
							},
							"userDto": {},
							"userFlag": 0
						}
					$scope.imagesObject.idFrontPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFront);
					$scope.imagesObject.idBackPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBack);							
					if($scope.addSubscriberObj.subscriberData.subscriberDetails[0].address)
						$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId =  $filter('filter')($scope.countries,{countryId:$scope.countries[0].countryId})[0];
					var isoCode = $scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality;
					$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality = {};
					$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality.countryName =  $filter('filter')($scope.countries,{isoCode:isoCode})[0].countryName;
					$scope.imagesObject.change = false;
					$scope.disable = {kycNotApproved:true};
					$scope.heading = "Edit Barred Customer Information";
					$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberEditModal.html';
//					$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberNewSimModal.html'
					angular.element('#editSubscriberModal').modal();
					setTimeout(function(){
						$scope.resetImages();
						$scope.initImages();
					},1000)
					
				}
				else{
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg)
			})
		}
		
		$scope.searchCustomer = function(){
			$scope.search.email = null;
			$scope.search.kycTansactionId = null;
			$scope.search.slot = 10;
			$scope.search.startIndex = 0;
			httpService.post(API.approvedSubscriber_API,genericFactory.getEncryption($scope.search)).then(function(res){
				if(res.data.responseDto.responseCode == 200){
					var data = res.data.subscriberSearchResponseDto.rows[0];
					$scope.addSubscriberObj={
							"subscriberData": {
								"registrationType": "normal",
								"createdBy": data.createdBy,
								"registrationMode": "Online",
								"kycTansactionId":data.kycTansactionId,
								"subscriberDetails": [{
									"subscriberIdDetails": data.subscriberDetails[0].subscriberIdDetails,
									"address": data.subscriberDetails[0].address,
									"caseType": "EDIT",
									"imei" : "GUI",
									"simSerialNumber": data.subscriberDetails[0].simSerialNumber,
									"placeOfBirth":data.subscriberDetails[0].placeOfBirth,
									"nationality": data.subscriberDetails[0].nationality,
									"subscriberDetailsId": data.subscriberDetails[0].subscriberDetailsId,
									"firstName": data.subscriberDetails[0].firstName,
									"lastName": data.subscriberDetails[0].lastName,
									"email": data.subscriberDetails[0].email,
									"dateOfBirth": data.subscriberDetails[0].dateOfBirth,
									"isMinor": data.subscriberDetails[0].isMinor,
									"gender": data.subscriberDetails[0].gender,
									"amAccount": data.subscriberDetails[0].amAccount,
									"alternateNo": data.subscriberDetails[0].alternateNo,
									"isImageEdited": "NO",
									"proxyForRegistration": data.subscriberDetails[0].proxyForRegistration
								}],
								"subscriberId": data.subscriberId,
								"msisdn": data.msisdn
							},
							"userDto": {},
							"userFlag": 0
						}
					$scope.imagesObject.idFrontPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFront);
					$scope.imagesObject.idBackPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBack);							
					if($scope.addSubscriberObj.subscriberData.subscriberDetails[0].address)
						$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId =  $filter('filter')($scope.countries,{countryId:$scope.countries[0].countryId})[0];
					$scope.imagesObject.change = false;
					$scope.disable = {kycNotApproved:true};
					$scope.heading = "Customer Information";
					$scope.templates.editSubscriberModal = 'templates/subscriber/subscriberViewModal.html';
					angular.element('#editSubscriberModal').modal();
					setTimeout(function(){
						$scope.resetImages();
						$scope.initImages();
					},1000)
					
				}else{
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg)
			})
		}
		$scope.addSubscriberObj.subscriberData.subscriberDetails[0].registeredOn = new Date().getTime();
	}]);
})