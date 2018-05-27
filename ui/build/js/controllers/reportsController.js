define(['angular'],function(angular){
	angular.module('zambiaApp')
	.controller('reportsController', ['$scope','httpService','API','genericService','$filter','$state','genericFactory','$stateParams','$sce', function ($scope,httpService,API,genericService,$filter,$state,genericFactory,$stateParams,$sce) {
			$scope.reportHeading = $stateParams.report.replace(/_/g,' ').toUpperCase();
			$scope.kycReport = $sce.trustAsResourceUrl(API.reports_API+$stateParams.report+"&user_name_h="+genericFactory.decryptionS('userId'));
	}])
})
	