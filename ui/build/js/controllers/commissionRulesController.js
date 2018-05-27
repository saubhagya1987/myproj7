define(['angular'],function(angular){
	angular.module('zambiaApp')
	.controller('managementController', ['$scope','httpService','API','genericService','genericFactory','$filter','$state', function ($scope,httpService,API,genericService,genericFactory,$filter,$state) {
			  httpService.post(API.getManageInput_API,genericFactory.getEncryption(null)).then(function(res){
	//				if(res.data.status.statusCode=="200"){
						$scope.data = res.data.body;
	//				}else{
	//					genericFactory.showNotification(res.data.status.statusMessage);
	//				}
				},function(err){
					genericFactory.showNotification(API.errorMsg+" Details : Status "+err.status+" "+err.statusText);
				})  
			$scope.updateConfigData = function(){
			  	$scope.sendData = angular.copy($scope.data);
				if(!$scope.sendData.blacklistSimSwapDenial){
					$scope.dataFile = null;
					delete $scope.sendData.blacklistSimSwapDenial;
				}else{
					$scope.dataFile = angular.copy($scope.data.blacklistSimSwapDenial);
				}
				httpService.MultiPartPost(API.updateManageInput_API,$scope.dataFile,'configData',$scope.data).then(function(res){
					if(res.data.responseDto.responseCode == 200){
						genericFactory.showNotification("Configuration Updated Successfully");
					}else{
						genericFactory.showNotification(res.data.responseDto.responseDescription);
					}				
				},function(err){
					genericFactory.showNotification(API.errorMsg+" Details : Status "+err.status+" "+err.statusText);
				})
			}
	}])
})