define(['angular'],function(angular){
	angular.module('zambiaApp')
	.controller('commissionRulesEditController', ['$scope','httpService','API','genericService','genericFactory','$filter','$state', function ($scope,httpService,API,genericService,genericFactory,$filter,$state) {
		 // Editor options.
		  $scope.options = {
		    language: 'en',
		    allowedContent: true,
		    entities: false
		  };
		  $scope.commission = {subRoleId:null,commissionValue:null};
//		  var allinfo = genericService.getAllData();
//		  if((typeof allinfo === 'object' || typeof allinfo === 'function') && typeof allinfo.then === 'function'){
//			allinfo.then(function(data){
//				$scope.allSubRoles = data['sub_role_list'];
//			});	
//		  }else{
			$scope.allSubRoles = allinfo['sub_role_list'];
//		  }
			angular.element('#com').css('min-height', '45vh');
		  $scope.getCommissionRule = function(){
			$scope.sendData = angular.copy($scope.commission)
			delete $scope.sendData.commissionValue;
			httpService.post(API.getCommissionRule_API,genericFactory.getEncryption($scope.sendData)).then(function(res){
				if(res.data.responseDto.responseCode == 200){
					$scope.commission.commissionValue = res.data.body.commissionValue;
				}else{
					genericFactory.showNotification(res.data.status.statusMessage);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg+" Details : Status "+err.status+" "+err.statusText);
			})  
		  }
		  $scope.saveCommissionRule = function(){
			httpService.post(API.saveCommissionRule_API,genericFactory.getEncryption($scope.commission)).then(function(res){
				if(res.data.responseDto.responseCode == 200){
					genericFactory.showNotification("Commission rules saved successfully.");
				}else{
					genericFactory.showNotification(res.data.status.statusMessage);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg+" Details : Status "+err.status+" "+err.statusText);
			})  
		  }
		  if($state.current.name=='dashboard.commissionRules'){
			  $scope.commission.subRoleId = genericFactory.decryptionS('subRoleId');
			  $scope.getCommissionRule();
		  }
	}
	])
})