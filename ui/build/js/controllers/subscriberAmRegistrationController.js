define(['angular'],function(angular) {
	angular.module('zambiaApp')
	.controller('subscriberAmRegistrationController',['$scope','httpService','API','genericFactory','genericService','$filter',function($scope,httpService,API,genericFactory,genericService,$filter){
		$scope.search = {
				"msisdn" : null		
		}
		$scope.initImages = function(){
			$scope.idFrontDocumentId = document.getElementById('idFrontImg');
			$scope.idBackDocumentId = document.getElementById('idFrontImg');
			$scope.physicalFormDocumentId = document.getElementById('physicalFormIdImg');
		}
		$scope.init = function(){
			$scope.maxDate = new Date().setFullYear(new Date().getFullYear() - 18);
			$scope.SubscriberObj = {"userDetail" : {"userDepartment":[] } , "roles" : [],"subRole":[],"subRoles":[], "location" : {}};
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
							"gender": "male",
							"address": {
								"district": null,
								"permanentAddress":null,
								"countryId": 1,
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
								"physicalFormId": null,
								"idType": null,
								"idNumber": null
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
			$scope.initImages();
		}();
		$scope.generateOtp = function(){
			genericFactory.getIp(function(ip){
				$scope.showOtp = false;
				$scope.search.ipAddress = ip;
	//			$scope.showRegistrationBlock = false;
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
			});
		}
		
		$scope.validateOtp = function(otp){
			$scope.search.otp = otp;
			httpService.post(API.validateOtp_API,genericFactory.getEncryption($scope.search)).then(function(res){
				if(res.data.responseDto.responseCode=="200"){
					if(API.aesUtil.decrypt(API.salt,API.IV,API.passphrase,res.data.responseDto.body).substring(9,15)==$scope.search.otp){
					$scope.showOtp = false;
					httpService.post(API.getSubscriberData_API,genericFactory.getEncryption({msisdn:$scope.search.msisdn})).then(function(res){
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
											"placeOfBirth":data.subscriberDetails[0].placeOfBirth,
											"nationality":  data.subscriberDetails[0].nationality,
											"subscriberDetailsId": data.subscriberDetails[0].subscriberDetailsId,
											"firstName": data.subscriberDetails[0].firstName,
											"lastName": data.subscriberDetails[0].lastName,
											"email": data.subscriberDetails[0].email,
											"dateOfBirth": data.subscriberDetails[0].dateOfBirth,
											"isMinor": data.subscriberDetails[0].isMinor,
											"gender": data.subscriberDetails[0].gender,
											"amAccount": "Yes",
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
							$scope.disable = {kycNotApproved:true};
							$scope.heading = "Edit Customer Information";
							$scope.editSubscriberModal = 'templates/subscriber/subscriberAmModal.html';
							angular.element('#editSubscriberModal').modal();
							setTimeout(function(){
								$scope.resetImages();
								$scope.initImages();
							},1000)	
//					$scope.showRegistrationBlock = true;
				}else{
//					$scope.showRegistrationBlock = false;
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				}
				},function(err){
					genericFactory.showNotification(API.errorMsg)
				})
				}
				else
						genericFactory.showNotification("Invalid Code");
			}
			else{
				genericFactory.showNotification(res.data.responseDto.responseDescription);
			}
			},function(err){
				genericFactory.showNotification(API.errorMsg)
			})
		}
		$scope.editSubscriber = function(){
			$scope.finalSubscriberData = angular.copy($scope.addSubscriberObj);
			if($scope.idFrontDocumentId && $scope.idFrontDocumentId.value!=null && $scope.imagesObject.idFront && $scope.imagesObject.idFront.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFrontData = $scope.imagesObject.idFront.base64;
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].isImageEdited = "Yes";
			}else{
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFrontData = genericFactory.getBase64Image($scope.idFrontDocumentId)
			}
			if($scope.idBackDocumentId && $scope.idBackDocumentId.value!=null && $scope.imagesObject.idBack && $scope.imagesObject.idBack.base64){
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBackData = $scope.imagesObject.idBack.base64;
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].isImageEdited = "Yes";
			}else{
				$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFrontData = genericFactory.getBase64Image($scope.idBackDocumentId)
			}
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].physicalFormId = genericFactory.getBase64Image($scope.physicalFormDocumentId);
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].caseType = "AM";
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.countryId = $scope.countries[0].countryId;
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].nationality = $scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality.isoCode;
			delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.district.districtName;
			genericFactory.getIp(function(ip){
				$scope.finalSubscriberData.subscriberData.ipAddress = ip;
				httpService.post(API.editSubscriber_API,genericFactory.getEncryption($scope.finalSubscriberData)).then(function(res){
					console.log(res);
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
			//console.log(JSON.stringify($scope.addSubscriberObj));
			if($scope.addSubscriberObj && $scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberType && id)
				return ((id.id_type==0 || id.id_type==($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberType=='Zambian'?1:2)) && ($scope.addSubscriberObj.subscriberData.subscriberDetails[0].amAccount=="Yes"?(id.is_am==1?true:false):true))
		}
//		$scope.amRegistration = function(){
//			$scope.customerAmData = {
//				msisdn:$scope.search.msisdn
//			}
//			httpService.post(API.amRegistration_API,$scope.customerAmData).then(function(res){
//				if(res.data.status.statusCode=="200"){
//					genericFactory.showNotification("Number Registered for Airtel Money");
//				}else{
//					genericFactory.showNotification(res.data.responseDto.responseDescription);
//				}
////				$scope.showRegistrationBlock = false;
//				$scope.showOtp = false;
//			},function(error){
//				genericFactory.showNotification(API.errorMsg)
//			})
//		}
	}
	])
})