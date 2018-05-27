'use strict';
define(['angular'], function(angular) {
	angular.module('zambiaApp')
	.controller('loginController', ['$scope','API','$state','genericFactory','$http','$ocLazyLoad','$injector','genericService','httpService', function ($scope,API,$state,genericFactory,$http,$ocLazyLoad,$injector,genericService,httpService) {
		$scope.userInfo ={
				auuId:null,
				password:null
		}
		//genericFactory.getEncryption('AES');
		var count;
//		var allinfo = genericService.getAllData();
		$scope.maxDate = new Date().setFullYear(new Date().getFullYear() - 18);
		$scope.login = function(){
			if($scope.loginForm.$invalid){
				return;
			}
			genericFactory.getIp(function(ip){
				$scope.userInfo.ipAddress = ip;
				httpService.post(API.login_API,genericFactory.getEncryption($scope.userInfo)).then(function(response){
					if(response.data.userExist){
			            genericFactory.encryptionS('token',response.data.access_token);
			            genericFactory.encryptionS('userId',response.data.userId);
			            genericFactory.encryptionS('name',response.data.name);
			            genericFactory.encryptionS('roleName',response.data.roles[0].authority);
			            genericFactory.encryptionS('subRoleId',response.data.subRoleId);
			            genericFactory.encryptionS('userName',response.data.userName);
			            if(response.data.isPasswordModified==0){
							angular.element(document.getElementById('resetPassword')).modal({
								backdrop:false
							});
						}else{
							setTimeout(function(){$state.go('dashboard.home');},1);//$state.go('dashboard.subscriberManagement');
						}
		        	}else if(response.data.status=='203'){
		        		if(response.data.failedAttempt){
			        		genericFactory.showNotification(response.data.failedAttempt);
		        		}else{
		        			genericFactory.showNotification('Username or Password is wrong');
		        		}
		        	}else if(response.data.status=='204'){
		        		genericFactory.showNotification('User is Inactive');
		        	}else if(response.data.status=='221'){
		        		genericFactory.showNotification(response.data.failedAttempt);
		        	}else{
		        		genericFactory.showNotification('User Does not Exist');
		        	}
				},function(err){
					if(err.data && err.data.error_description)
						genericFactory.showNotification(JSON.stringify(err.data.error_description))
					else{
						genericFactory.showNotification(API.errorMsg);
					}
				})
			});
		}
		
		$scope.reset = function(){
			$scope.loginForm.$setPristine();
			$scope.loginForm.$setUntouched();
			$scope.userInfo ={
					auuId:null,
					password:null
			}
		}
		$scope.resetLogin ={};
		$scope.resetPassword = function(){
			if($scope.resetLogin.newPassword != $scope.resetLogin.confirmPassword || $scope.resetLogin.newPassword==null){
				genericFactory.showNotification("Password does not match");
				return;
			}
			var userId =  genericFactory.decryptionS('userId');
	        genericFactory.getIp(function(ip){
	        	var encrypted = {
	        		uid:userId,
	        		newPassword: $scope.resetLogin.newPassword,
	  				oldPassword: $scope.resetLogin.oldPassword,
	  				confirmPassword: $scope.resetLogin.confirmPassword,
	  				ipAddress:ip
	  			};
		  		$http({
		    		method: 'POST',
					url: API.resetPassword_API,
					headers: {'Content-Type': 'application/json'},
					data : genericFactory.getEncryption(encrypted)
			        }).then(function(response) {
			        	if(response.data.responseDto.responseCode == 200){
			        		genericFactory.showNotification("Password changed successfully!");
			 		        window.location.href = "#!/login";
			 		        $state.go('login');
			 		        angular.element(document.getElementById('resetPassword')).modal('hide');
			 		    }else{
			 		    	if(count==undefined)
			 		    		count = 1;
			 		    	else
			 		    	{
			 		    		count++;
			 		    		if(count>2){
			 		    			angular.element(document.getElementById('resetPassword')).modal('hide');
			 		    			genericService.logout();
			 		    		}
			 		    	}
			 		    	genericFactory.showNotification(response.data.responseDto.responseDescription);
			        	}
			    });
			});
		}
		$scope.forgotPassword = function(){
			require(['date-picker'], function () {
         		$ocLazyLoad.inject('720kb.datepicker')
    			$ocLazyLoad.load(['libs/vendor/angularjs-datepicker/dist/angular-datepicker.min.css']);
    			$scope.showForgotPasswordModal = true;
    			setTimeout(function(){
//	    			angular.element(document.getElementById('forgotPassword')).on('show.bs.modal', function () {
//		    			$scope.resetForgot();	
//	    			});
	    			angular.element(document.getElementById('forgotPassword')).modal();	
				},loadTimeModal)         		
			})
//			$ocLazyLoad.load(['libs/vendor/angularjs-datepicker/dist/angular-datepicker.min.js','libs/vendor/angularjs-datepicker/dist/angular-datepicker.min.css']).then(function(){
//				$ocLazyLoad.inject('720kb.datepicker');
//				$scope.showForgotPasswordModal = true;
//				setTimeout(function(){
//	    			angular.element(document.getElementById('forgotPassword')).modal();					
//				},1)
//			})
		}
		$scope.forgotLogin = {};
//		$scope.resetForgot = function(){
//			$scope.forgotLogin.forgotForm.$submitted = false;
//			$scope.forgotLogin.forgotForm.$setPristine();
//			$scope.forgotLogin.forgotForm.$setUntouched();
//			$scope.forgotLogin.idNo = null;
//			$scope.userInfo.username = null;
//		}
		$scope.forgotPass = function(){
			if($scope.forgotLogin.forgotForm.$invalid){
				return false;
			}
			require(['js/services/httpService','js/services/genericService'], function (httpService,genericService) {
				app.register.service('httpService',httpService);
				httpService = $injector.get('httpService');
				app.register.service('genericService',genericService);
				var data = {
						"dob": $scope.forgotLogin.dob,
						"idCardNo": $scope.forgotLogin.idNo,
						"msisdn":$scope.userInfo.username
						}
				httpService.post(API.forgot_API,genericFactory.getEncryption(data)).then(function(res){
					if(res.data.responseDto.responseCode=="200"){
						angular.element(document.getElementById('forgotPassword')).modal('hide');
						genericFactory.showNotification("Password Reset Successfully");
					}else{
						genericFactory.showNotification(res.data.responseDto.responseDescription);
					}
				},function(error){
					var errors = "Unable to connect to server...Please try again.";
					genericFactory.showNotification(errors);
				})
			})
		}
		genericFactory.closePreloader();
		// modal backdrop screen solution
		if(angular.element('.modal-backdrop'))
    		angular.element('.modal-backdrop').remove();

    	if(angular.element('.modal-open'))
    		angular.element('.modal-open').removeClass('modal-open');
	}]);
})