define(['angular'],function(angular){
	angular.module('zambiaApp')
	.controller('closeCaseController', ['$scope','httpService','API','genericService','$filter','$state','genericFactory', function ($scope,httpService,API,genericService,$filter,$state,genericFactory) {
		var url = null;
		var zoomValue = 0.2;
//		alert(genericFactory.decryptionS('roleName'))
		$scope.initImages = function(){
			$scope.idFrontDocumentId = document.getElementById('idFrontImg');
			$scope.idBackDocumentId = document.getElementById('idBackImg');
			$scope.physicalFormId = document.getElementById('physicalFormIdImg')
		}
		$scope.init = function(){
			$scope.cases = new Array();
			$scope.subList = new Array();
			$scope.templates = {editSubscriberModal:'templates/subscriber/subscriberCaseMgmtEdit.html'};
			$scope.searchData = {
						slot: 10, 
						startIndex: 0, 
						finalStatus: null,
						actionType: "CLOSED_ALL", 
						roleName: "KYC-SUPERVISOR", 
						userId: genericFactory.decryptionS('userId'),
						key: null
				}
			$scope.searchData.key=$scope.searchData.finalStatus;
			$scope.actionObject = {
					"subscriberId":null,
					"userId": genericFactory.decryptionS('userId'),
					"roleName":"CM",
					"action":null,
					"statusReason":null
				}
			$scope.page = {startIndex:0,endIndex:10};
//			var allinfo = genericService.getAllData();
//			if((typeof allinfo === 'object' || typeof allinfo === 'function') && typeof allinfo.then === 'function'){
//				allinfo.then(function(data){
//					$scope.countries = data['countries'];
////					$scope.addSubscriberObj.customerdetail.nationality = $filter('filter')($scope.countries,{country_code:'MW'})[0];
//					$scope.districts = data['districts'];
//					$scope.incomeSource = data['income_source'];
//					$scope.ids = data['ids'];
//					$scope.roles = data['roles'];
//					$scope.departments = data['departments'];
//					$scope.zones = data['zt_list'];
//					$scope.actions = data['user_actions'];
//				});	
//			}else{
				$scope.countries = allinfo['countries'];
//				$scope.addSubscriberObj.customerdetail.nationality = $filter('filter')($scope.countries,{country_code:'MW'})[0];
				$scope.districts = allinfo['districts'];
				$scope.incomeSource = allinfo['income_source'];
				$scope.ids = allinfo['ids'];
				$scope.roles = allinfo['roles'];
				$scope.departments = allinfo['departments'];
				$scope.zones = allinfo['zt_list'];
				$scope.actions = allinfo['user_actions'];
//			}
			$scope.imagesObject = {physicalFormId:null,idFront:null,idBack:null};
			$scope.initImages();
		}()
		
		$scope.searchCases = function(){
			httpService.post(API.openCases_API,genericFactory.getEncryption($scope.searchData)).then(function(res){
				if(res.data.responseDto.responseCode=="200"){
					if(res.data.subscriberSearchResponseDto && res.data.subscriberSearchResponseDto.rows.length>0)
						$scope.cases = res.data.subscriberSearchResponseDto.rows;
					else{
						$scope.cases = new Array();						
					}
					$scope.totalCount = res.data.subscriberSearchResponseDto.totalRecord;
				}else{
					genericFactory.showNotification(res.data.status.statusMessage);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg);
			})
		} 
		$scope.searchCases();
		$scope.reasonStatus = function(id,status){
			$scope.actionObject.subscriberId = id;
			$scope.actionObject.action = status;
			$scope.templates.headingStatusModal = status.replace(/_/g,' ');
			if(status=="BARRED"){
				$scope.buildRejectionList();
				$scope.templates.headingStatusModal = "Partial";
			}
			$scope.actionObject.statusReason = null
			$scope.templates.statusModal = "templates/common/NUM_APPROVE.html"; // "templates/common/"+status+".html"
			angular.element('#statusModal').modal();
		}
		$scope.changeStatus = function(){
			if($scope.actionObject.action=='BARRED'){
				if($scope.actionObject.statusReason && $scope.actionObject.statusReason!=null){
					$scope.actionObject.statusReason = $scope.actionObject.statusReason.join();
				}
			}else{
				$scope.actionObject.statusReason = angular.copy($scope.actionObject.statusReason2);
			}
			delete $scope.actionObject.statusReason2;
			httpService.post(API.actionSubscriber_API,genericFactory.getEncryption($scope.actionObject)).then(function(res){
				if(res.data.responseDto.responseCode=="200"){
					angular.element('#statusModal').modal('hide');
					if($scope.actionObject.action=='BARRED')
						genericFactory.showNotification("Subscriber partial successfully");
					else
						genericFactory.showNotification("Subscriber approved successfully");
					$scope.resetPage();
					angular.element('#editSubscriberModal').modal('hide');
				}else{
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg);
			})
		}		
		$scope.pagination = function(pg,pg_size){
			$scope.searchData.startIndex = (pg-1)*pg_size;
			$scope.searchCases();
		}
		$scope.resetPage = function(){
			$scope.searchData.startIndex = 0;
			$scope.searchData.slot = 10;
			$scope.searchData.key=$scope.searchData.finalStatus;
			$scope.currentPage = 1;
			$scope.searchCases();
		}
		$scope.openModal = function(index){
			$scope.templates.showModal = true;
			var data = angular.copy($scope.cases[index]);
			if(data.subscriberDetail && data.subscriberDetail.length>0){
				$scope.addSubscriberObj={
						"subscriberData": {
							"registrationType": "normal",
							"createdBy": data.createdBy,
							"registrationMode": "Online",
							"subscriberDetails": [{
								"subscriberIdDetails": data.subscriberDetail[0].subscriberIdDetails,
								"address": data.subscriberDetail[0].address,
								"caseType": "EDIT",
								"imei" : "GUI",
								"simSerialNumber": data.subscriberDetail[0].simSerialNumber,
								"nationality": data.subscriberDetail[0].nationality,
								"placeOfBirth":data.subscriberDetails[0].placeOfBirth,
								"subscriberDetailsId": data.subscriberDetail[0].subscriberDetailsId,
								"firstName": data.subscriberDetail[0].firstName,
								"lastName": data.subscriberDetail[0].lastName,
								"email": data.subscriberDetail[0].email,
								"dateOfBirth": data.subscriberDetail[0].dateOfBirth,
								"isMinor": data.subscriberDetail[0].isMinor,
								"gender": data.subscriberDetail[0].gender,
								"amAccount": data.subscriberDetail[0].amAccount,
								"alternateNo": data.subscriberDetail[0].alternateNo,
								"isImageEdited": "NO",
								"proxyForRegistration": data.subscriberDetail[0].proxyForRegistration
							}],
							"subscriberId": data.subscriberId,
							"msisdn": data.msisdn
						},
						"userDto": {},
						"userFlag": 0
					}
				}else{
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
				}
				$scope.imagesObject.idFrontPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageFront);
				$scope.imagesObject.idBackPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idImageBack);
				$scope.imagesObject.idPhysicalFormPath = genericFactory.getImageURL($scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].idPath,data.kycTansactionId,$scope.addSubscriberObj.subscriberData.subscriberDetails[0].subscriberIdDetails[0].physicalFormId);		
				if($scope.addSubscriberObj.subscriberData.subscriberDetails[0].address)
					$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId =  $filter('filter')($scope.countries,{countryId:$scope.countries[0].countryId})[0];
				$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality =  $filter('filter')($scope.countries,{isoCode:$scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality})[0];
//			$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId =  $filter('filter')($scope.countries,{countryId:$scope.addSubscriberObj.subscriberData.subscriberDetails[0].address.countryId})[0];
			$scope.heading = "Edit Customer Information";
			angular.element('#editSubscriberModal').modal({
			    backdrop: 'static',
			    keyboard: false
			});
			$scope.options = {
					zoom : {
						value : zoomValue,
						step : 0.2
					},
					rotate : {
						value : 90
					}
				};
			$scope.options1 = {
					zoom : {
						value : zoomValue,
						step : 0.2
					},
					rotate : {
						value : 90
					}
				};
			$scope.options2 = {
					zoom : {
						value : zoomValue,
						step : 0.2
					},
					rotate : {
						value : 90
					}
				};
			setTimeout(function(){
				$scope.resetImages();
				$scope.initImages();					
			},1000)	
		}

		$scope.closeModal = function(){
			$scope.templates.showModal = false;
			angular.element('#editSubscriberModal').modal('hide');
		}
		$scope.addSubscriber = function(){
			if($scope.subscriberForm.$invalid){
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
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].subscriberIdDetails[0].physicalFormId = genericFactory.getBase64Image($scope.physicalFormId);
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.countryId = $scope.countries[0].countryId;
			$scope.finalSubscriberData.subscriberData.subscriberDetails[0].nationality = $scope.addSubscriberObj.subscriberData.subscriberDetails[0].nationality.isoCode;
			delete $scope.finalSubscriberData.subscriberData.subscriberDetails[0].address.district.districtName;
				genericFactory.getIp(function(ip){
					$scope.finalSubscriberData.subscriberData.ipAddress = ip;
					httpService.post(API.editSubscriber_API,genericFactory.getEncryption($scope.finalSubscriberData)).then(function(res){
						if(res.data.responseDto.responseCode=="200"){
							$scope.searchCases();
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

		$scope.buildRejectionList = function(){
			if(!$scope.subList.length){
				for(var i=0;i<$scope.actions.length;i++){
					var parent = $scope.actions[i].group;
					if($scope.actions[i].fields!=null){
						var list = $scope.actions[i].fields.split(',');
						for(var j=0;j<list.length;j++)
							$scope.subList[$scope.subList.length] =  list[j]+" - "+parent;
					}else{
						$scope.subList[$scope.subList.length] = parent;
					}
				}
			}
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
//
//		$scope.rotation = {frontCurrent : 0, backCurrent: 0, physicalCurrent: 0, frontAngle: null, backAngle: null, physicalAngle: null};
//    	$scope.rotate = function (imgAngle, imgCurrent, angle) {
//        	$scope.rotation[imgAngle] = $scope.rotation[imgCurrent] = $scope.rotation[imgCurrent] + Number(angle);
//    	};
	}])
})