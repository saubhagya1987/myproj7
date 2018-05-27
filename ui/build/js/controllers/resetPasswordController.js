'use strict';
define(['angular'], function(angular) {
	angular.module('zambiaApp')
	.controller('resetPasswordController', ['$scope','httpService','API','genericFactory', function ($scope,httpService,API,genericFactory) {
		$scope.resetUserData = {
			"auuId":null
		}
		
		$scope.resetPassword = function(){
			if($scope.resetUserForm.$invalid){
				return;
			}
			httpService.post(API.resetCpPassword_API,genericFactory.getEncryption($scope.resetUserData)).then(function(res){
				if(res.data.responseDto.responseCode==200){
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				}else{
					genericFactory.showNotification(res.data.responseDto.responseDescription);
				}
			},function(err){
				genericFactory.showNotification(API.errorMsg);
			})
		}
	}])
})