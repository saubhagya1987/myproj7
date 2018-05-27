define(['angular'],function(angular){
	angular.module('zambiaApp')
	.controller('userManagementController', ['$scope','httpService','API','genericService','$filter','$state','genericFactory', function ($scope,httpService,API,genericService,$filter,$state,genericFactory) {
		$scope.initImages = function(){
			$scope.cpImageDocumentId = document.getElementById('cpImage');
			$scope.cpImagePathDocumentId = document.getElementById('cpImagePath');			
		}
		$scope.init = function(){
			$scope.editMode = false;
			$scope.templates = {'addUserModal':"templates/userAddModal.html",pageSize:10,startIndex:0,cpImagePath:null};
			$scope.deleteUserObj={"userId":null,"userName":null,"isDeleted":null};
//			allinfo = genericService.getAllData();
//			if((typeof allinfo === 'object' || typeof allinfo === 'function') && typeof allinfo.then === 'function'){
//				allinfo.then(function(data){
//					$scope.roles = data['roles'];
//					$scope.departments = data['departments'];
//					$scope.province = data['province'];
//					$scope.topRole = $scope.roles[0];
//					$scope.subRoles = $filter('filter')($scope.roles,{roleId:$scope.topRole.roleId})[0].subRoleList;
//					allinfo = genericService.getAllData();
//					if($state.current.name=="dashboard.createCp")
//						$scope.subRoles = $filter('filter')($scope.roles,{roleId:$scope.roles[2].roleId})[0].subRoleList;
////					console.log($filter('filter')($scope.roles,{role:{role_id:$scope.topRole.role[0].role_id}})[0])
//				});	
//			}else{
				$scope.roles = allinfo['roles'];
				$scope.departments = allinfo['departments'];
				$scope.province = allinfo['province'];
				$scope.topRole = $scope.roles[0];
				$scope.subRoles = $filter('filter')($scope.roles,{roleId:$scope.topRole.roleId})[0].subRoleList;
				if($state.current.name=="dashboard.createCp"){
					$scope.subRoles = $filter('filter')($scope.roles,{roleId:$scope.roles[2].roleId})[0].subRoleList;
					$scope.disableAll = true;
				}
//				console.log($filter('filter')($scope.roles,{role:{role_id:$scope.topRole.role[0].role_id}})[0])
//			}
			$scope.templates.maxDate = new Date().setFullYear(new Date().getFullYear() - 18);
			$scope.templates.minDate = new Date().setFullYear(new Date().getFullYear() - 100);
		}();
		$scope.is_MSISDNAVAILABE1 = function(mobile) {
			if(mobile)
			httpService.post(API.checkUser_API,genericFactory.getEncryption({msisdn:mobile})).then(function(response) {
				$scope.isMSISDNAVAILABE = response.data.body.isMSISDNAvailable;
				if($scope.isMSISDNAVAILABE){
					$scope.isMSISDNAVAILABEMsg = response.data.responseDto.responseDescription;
					$scope.userForm.userDetail.idCardNo = response.data.body.idCardNo;
					$scope.subscriberNotRegistered = false;
				}else{
					if(!response.data.body.idCardNo){
						$scope.subscriberNotRegistered = true;
					}
					else
						$scope.subscriberNotRegistered = false;
					$scope.isMSISDNAVAILABEMsg = response.data.responseDto.responseDescription;
					$scope.userForm.userDetail.idCardNo = null;
				}
			});
		}
		$scope.is_AUUIDAVAILABLE1 = function(auuid){
			if(auuid) 
			httpService.post(API.checkAuuidAvailable_API,genericFactory.getEncryption({auuId:auuid})).then(function(res) {
				if(res.data.responseDto && res.data.responseDto.responseCode=="200"){
					$scope.isAUUIDAVAILABE = res.data.body.isAuuIdAvailable;
				}else{
					$scope.isAUUIDAVAILABE = false;
					genericFactory.showNotification(res.data.responseDto.responseDescription);					
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg);
			});
		}
		$scope.userSearchData = {
				email:'',
				isOldDetail:0,
				msisdn:'',
				slot:10
		};
		
		$scope.userForm = 
		{
				"userTransactionId": null,
				"userDetails": [{
					"userIdDetail": [{
						"idImage": null,
						"idImageName": "cpImage"
					}]
				}],
				"userDetail": {
					"userDepartment": [],
					"msisdn": null,
					"firstName": null,
					"lastName": null,
					"email": null,
					"auuid": null,
					"dealerCode": null,
					"dob": null,
					"distributorName": null,
					"ddmAuuid": null,
					"commissionMobileNo":null,
					"distributorId": null,
					"distributorMsisdn": null,
					"idCardNo": null,
					"simSwapAccess": "active",
					"airtelHandsetName": "Yes",
					"airtelHandsetImei": null,
					"cpContract": null,
					"tsmAuuid": null,
					"imei" : "GUI"
				},
				"roles": [],
				"subRole": [],
				"location": {
					"provinceIds": [],
					"villageIds": [],
					"districtIds": []
				},
				"userFlag": 0,
				"userName": null
			}
		$scope.addUserModal = function(){
			$scope.resetAddUserForm();
			$scope.userForm.topRole = $scope.topRole;
//			$scope.userForm.users[0].userRole[0].role = null;
			$scope.userForm.userDetail.userDepartment[0] = null;
			$scope.mandatory = false;
			$scope.editMode = false;
//			$scope.userForm.users[0].department = $scope.departments[0];
			$scope.roles = allinfo['roles'];
			$scope.userForm.roles[0] = $filter('filter')(allinfo['roles'],{roleId:$scope.userForm.topRole.roleId})[0];
			$scope.subRoles = null;
			$scope.getSubRoles($scope.userForm.roles[0].roleId);
			$scope.templates.addUserModal = "templates/userAddModal.html";
			angular.element('#editUser').modal();
			$scope.addUserForm.$setPristine();
			$scope.addUserForm.$setUntouched();
			setTimeout(function(){
				$scope.initImages();
			},1000)
		}
		$scope.getSubRoles = function(roleId,data){
			$scope.subRoles = null;
			$scope.subRoles = $filter('filter')(allinfo['roles'],{roleId:roleId})[0].subRoleList;
			if(roleId == 22){
				$scope.mandatory = true;
			}else if(roleId == 37 || roleId == 38  || roleId == 43){
				$scope.showSND = true;
				$scope.mandatory = false;
			}else{
				$scope.showSND = false;
				$scope.mandatory = false;
			}
		}
//		$scope.getDistricts = function(province){
//			$scope.districts = null;
//			$scope.districts = $filter('filter')($scope.province,{provinceName:province})[0].districts;
//		}
//		$scope.getVillages = function(district){
//			$scope.villages = null;
//			$scope.villages = $filter('filter')($scope.districts[0].villages,{villageName:district})[0];
//		}
		$scope.addUser = function(){
			if($scope.addUserForm.$invalid){
				$scope.addUserForm.$submitted = true;
				return;
			}
//			if($scope.isMSISDNAVAILABE || $scope.subscriberNotRegistered)
//				return;
			//$scope.templates.cpImagePath = null;
			$scope.userForm.userName = $scope.userForm.userDetail.msisdn;
			$scope.userForm.roles[0] = $scope.userForm.topRole;
			if($scope.editMode){
				
			}else{
				$scope.userForm.userTransactionId = "kyc" + genericFactory.getRandomSpan() + "" + new Date().getTime();
			}
			if($scope.isAirtel())
				$scope.userForm.userDetail.dealerCode = null;
			else
				$scope.userForm.userDetail.auuid = null;
			$scope.finalUserData = angular.copy($scope.userForm);
			delete $scope.finalUserData.topRole;
			delete $scope.finalUserData.roles[0].subRoleList;
			if($scope.finalUserData.roles[0].roleId == 22){
				$scope.finalUserData.location.provinceIds = [$scope.finalUserData.location.provinceIds[0]];
				$scope.finalUserData.location.districtIds = [$scope.finalUserData.location.districtIds[0]];
				$scope.finalUserData.location.villageIds = [$scope.finalUserData.location.villageIds[0]];
				delete $scope.finalUserData.location.provinceIds[0].districts;
				delete $scope.finalUserData.location.districtIds[0].villages;
				if($scope.cpImageDocumentId && $scope.cpImageDocumentId.value!=null && $scope.templates.cpImagePath && $scope.templates.cpImagePath.base64){
					$scope.finalUserData.userDetails[0].userIdDetail[0].idImage = $scope.templates.cpImagePath.base64;
				}else if($scope.editMode){
					$scope.finalUserData.userDetails[0].userIdDetail[0].idImage =  genericFactory.getBase64Image($scope.cpImagePathDocumentId);
					$scope.finalUserData.userDetails[0].userIdDetail[0].idImageName = "cpImage";
				}
			}else if($scope.finalUserData.roles[0].roleId == 37 || $scope.finalUserData.roles[0].roleId == 38  || $scope.finalUserData.roles[0].roleId == 43){
				$scope.finalUserData.location.provinceIds = [$scope.finalUserData.location.provinceIds[0]];
				$scope.finalUserData.location.districtIds = [$scope.finalUserData.location.districtIds[0]];
				$scope.finalUserData.location.villageIds = [$scope.finalUserData.location.villageIds[0]];
				delete $scope.finalUserData.userDetail.userIdDetail;
				delete $scope.finalUserData.userDetail.dob;
				delete $scope.finalUserData.userDetail.distributorName;
				delete $scope.finalUserData.userDetail.ddmAuuid;
				delete $scope.finalUserData.userDetail.commissionMobileNo;
				delete $scope.finalUserData.userDetail.distributorId;
				delete $scope.finalUserData.userDetail.distributorMsisdn;
				delete $scope.finalUserData.userDetail.idCardNo;
				$scope.finalUserData.userDetail.simSwapAccess = null;
				delete $scope.finalUserData.userDetail.airtelHandsetName;
				delete $scope.finalUserData.userDetail.airtelHandsetImei;
				delete $scope.finalUserData.userDetail.cpContract;
				delete $scope.finalUserData.userDetail.tsmAuuid;
				delete $scope.finalUserData.location.provinceIds[0].districts;
				delete $scope.finalUserData.location.districtIds[0].villages;
			}else{
				delete $scope.finalUserData.location;
				delete $scope.finalUserData.userDetail.userIdDetail;
				delete $scope.finalUserData.userDetail.dob;
				delete $scope.finalUserData.userDetail.distributorName;
				delete $scope.finalUserData.userDetail.ddmAuuid;
				delete $scope.finalUserData.userDetail.commissionMobileNo;
				delete $scope.finalUserData.userDetail.distributorId;
				delete $scope.finalUserData.userDetail.distributorMsisdn;
				delete $scope.finalUserData.userDetail.idCardNo;
				$scope.finalUserData.userDetail.simSwapAccess = null;
				delete $scope.finalUserData.userDetail.airtelHandsetName;
				delete $scope.finalUserData.userDetail.airtelHandsetImei;
				delete $scope.finalUserData.userDetail.cpContract;
				delete $scope.finalUserData.userDetail.tsmAuuid;
			}
			if($scope.finalUserData.userDetail.userDepartment[0]==null || $scope.finalUserData.userDetail.userDepartment[0]==""){
				$scope.finalUserData.userDetail.userDepartment = null;
			}
			genericFactory.getIp(function(ip){
				$scope.finalUserData.ipAddress = ip;
				httpService.post(API.addUser_API,genericFactory.getEncryption($scope.finalUserData)).then(function(res){
					if(res.data.responseDto && res.data.responseDto.responseCode=="200"){
						angular.element('#editUser').modal('hide');
						if($scope.editMode){
							genericFactory.showNotification("User Successfully Edited");
							$scope.editMode = false;
						}else{
	//						$scope.users.push($scope.finalUserData);
							genericFactory.showNotification("User Successfully Registered");
						}
						$scope.userSearchData.msisdn = null;
						$scope.searchUserEditForm.$submitted = false;
						$scope.searchUser(0);
					}else{
						genericFactory.showNotification(res.data.responseDto.responseDescription?res.data.responseDto.responseDescription:res.data.statusDescription);
					}				
				},function(err){
					genericFactory.showNotification(API.errorMsg);
				})
			});
		}
		$scope.searchUser = function(index){
			$scope.userSearchData.startIndex = index;
			httpService.post(API.searchUser_API,genericFactory.getEncryption($scope.userSearchData)).then(function(res){
				if(res.data.responseDto.responseCode=="200"){
					$scope.users = res.data.searchResponseDto.result;
					$scope.totalCount = res.data.searchResponseDto.totalRecord;
//					if($scope.currentSelectedData.length>0)
//						angular.element(document.getElementById('showNotificationConfirm2')).modal();
//					else{
//						genericFactory.showNotification('Number does not exist');
//					}
				}else{
					genericFactory.showNotification(res.data.responseDto.responseDescription);
//					genericFactory.showNotification("Unable to fetch data");
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg);
			});
		}
		if($state.current.name=="dashboard.userManagement")
			$scope.searchUser(0);
		else{
			$scope.userForm.topRole = $scope.topRole;
			$scope.getSubRoles(22);
		}
		$scope.pagination = function(pg,pg_size,flag){
//			if($scope.sendData.transaction_type!="" && $scope.sendData.transaction_type != $scope.searchData.transaction_type){
//				$scope.searchCases();
//				return;
//			}
//			$scope.userSearchData1.start_index = (pg-1)*pg_size;
//			$scope.userSearchData1.end_index = $scope.userSearchData1.start_index + pg_size;
			$scope.templates.startIndex = (pg-1)*pg_size;
			$scope.searchUser($scope.templates.startIndex);
		}
		$scope.deleteUserPopup = function(id,name,del,val,index){
			$scope.deleteUserObj.userId = id;
			$scope.deleteUserObj.userName = name;
			if(del){
				delete $scope.deleteUserObj.isBlocked;
				$scope.deleteUserObj.isDeleted = null;
				$scope.currentDeleteUserIndex = index;
				angular.element(document.getElementById('showNotificationConfirm2')).modal('hide');
				angular.element(document.getElementById('showNotificationConfirm1')).modal();
			}else{
				delete $scope.deleteUserObj.isDeleted;
				$scope.deleteUserObj.isBlocked = val;
				$scope.deleteUser('blockUser',val,index);				
			}
		}
		$scope.deleteUser = function(action,val,index){
			if(!action){
				action = 'deleteUser';				
			}
			genericFactory.getIp(function(ip){
				$scope.deleteUserObj.ipAddress = ip;
				httpService.post(API.deleteUser_API+action,genericFactory.getEncryption($scope.deleteUserObj)).then(function(res){
					if(res.data.responseDto.responseCode=="200"){
						if(action == 'deleteUser'){
							$scope.users.splice($scope.currentDeleteUserIndex,1);
							genericFactory.showNotification("User Successfully Deleted");
						}else{
							if(val==1)
								genericFactory.showNotification("User Successfully Blocked");
							else
								genericFactory.showNotification("User Successfully Unblocked");
						}
					}else{
						if(action == 'blockUser'){
							$scope.users[index].isBlocked = !val;
							genericFactory.showNotification(res.data.responseDto.responseDescription);
						}else{
							genericFactory.showNotification(res.data.responseDto.responseDescription);
						}
					}
					angular.element(document.getElementById('showNotificationConfirm1')).modal('hide');
				},function(err){
					genericFactory.showNotification(API.errorMsg);
				})
			});
		}
		$scope.resetAddUserForm = function(){
			$scope.addUserForm.$setPristine();
			$scope.addUserForm.$setUntouched();
			$scope.idNoRequired = false;
			$scope.userForm = 
			{
					"userTransactionId": null,
					"userDetails": [{
						"userIdDetail": [{
							"idImage": null,
							"idImageName": "cpImage"
						}]
					}],
					"userDetail": {
						"userDepartment": [],
						"msisdn": null,
						"firstName": null,
						"lastName": null,
						"email": null,
						"auuid": null,
						"dealerCode": null,
						"dob": null,
						"distributorName": null,
						"ddmAuuid": null,
						"commissionMobileNo":null,
						"distributorId": null,
						"distributorMsisdn": null,
						"idCardNo": null,
						"simSwapAccess": "active",
						"airtelHandsetName": "Yes",
						"airtelHandsetImei": null,
						"cpContract": null,
						"tsmAuuid": null,
						"imei" : "GUI"
					},
					"roles": [],
					"subRole": [],
					"location": {
						"provinceIds": [],
						"villageIds": [],
						"districtIds": []
					},
					"userFlag": 0,
					"userName": null
				}
		}
		$scope.editUser = function(user){
			angular.element(document.getElementById('showNotificationConfirm2')).modal('hide');
//			var data = $scope.currentSelectedData;
			$scope.userForm = angular.copy(user);
			if($scope.userForm.userDetail.auuid)
				$scope.mandatory = false;
			else
				$scope.mandatory = true;
			$scope.roles = $filter('filter')(allinfo['roles'],{roleId:user.roles[0].roleId});
			$scope.userForm.topRole = $filter('filter')(allinfo['roles'],{roleId:user.roles[0].roleId})[0];
			$scope.getSubRoles($scope.userForm.topRole.roleId);
			$scope.userForm.subRole = $filter('filter')($scope.subRoles,{roleId:user.roles[0].subRoleId});
			$scope.userForm.userDetail.userDepartment = $filter('filter')($scope.departments,{departmentId:user.userDetail.userDepartment[0].departmentId,departmentName:user.userDetail.userDepartment[0].departmentName});
			$scope.templates.cpImagePath = null;
			if(user.location){
				$scope.userForm.location.provinceIds = $filter('filter')($scope.province,{provinceId:user.location.provinceIds[0].provinceId}); 
				$scope.userForm.location.districtIds = $filter('filter')($scope.userForm.location.provinceIds[0].districts,{districtId:user.location.provinceIds[0].districts.districtId});
				$scope.userForm.location.villageIds = $filter('filter')($scope.userForm.location.provinceIds[0].districts[0].villages,{villageId:user.location.provinceIds[0].districts.villageIds.villageId});	
				$scope.templates.cpImagePath =  genericFactory.getUserImageURL(user.userDetail.userIdDetail[0].idPath,user.userTransactionId,"cpImage");			
			}	
			$scope.editMode = true;
			$scope.templates.addUserModal = "templates/userEditModal.html";
			angular.element('#editUser').modal();
			setTimeout(function(){
				$scope.initImages();
			},1000)
		}
		$scope.getCreatedOn = function(createdOn){
			return new Date(createdOn).toUTCString()
		}
		$scope.isAirtel = function(){
			var roleArray = [49,50,52,53].concat(API.sp_access).concat(API.de_access).concat(API.snd_access)
//			var roleArray = [45,46,27,47,48,49,50,51,52].concat(API.sp_access).concat(API.de_access).concat(API.snd_access)
//			var roleArray = ["CHABEBA","BOOTH","SSO","MINISHOP","AIRTEL_MONEY_EXPRESS_SHOP","COFO_AIRTEL_SHOP","COCO_AIRTEL_SHOP","AGENCY","CHAIN_STORES",];
			if($scope.userForm.subRole[0]){
				if(roleArray.indexOf($scope.userForm.subRole[0].roleId) != -1){
					return true;
				}
			 }
			return false;
		}
		$scope.checkAll = function(role){
			if($scope.disableAll && role.roleName=='CHANNEL_PARTNER'){
				return true;
			}else if($scope.disableAll && role.roleName!='CHANNEL_PARTNER'){
				return false;
			}else{
				return true;
			}
		}
	}])
})