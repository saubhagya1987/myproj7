'use strict';
define(['angular'], function(angular) {
	angular.module('zambiaApp')
	.controller('mainController', ['$scope','genericService','$state','$translate','genericFactory','httpService','API', function ($scope,genericService,$state,$translate,genericFactory,httpService,API) {
		$translate.use('en').catch(function () {
//    	        alert('key:'+ key);
	        $translate.use($translate.fallbackLanguage());            
	    });		
    	$scope.userFullName = genericFactory.decryptionS('name');	
        $scope.logout = function(){genericService.logout();};
    	genericFactory.closePreloader();
	}]);
})
