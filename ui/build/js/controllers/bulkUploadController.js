define(['angular'],function(angular){
	angular.module('zambiaApp')
	.controller('bulkUploadController', ['$scope','httpService','API','genericService','$filter','$state','genericFactory', function ($scope,httpService,API,genericService,$filter,$state,genericFactory) {
		$scope.API = API;
		$scope.uploadFile = function(url,file,id){
			if(file){
				httpService.MultiPartPost(url,file).then(function(res) {
					if(res.data.responseCode==200){
						genericFactory.showNotification("Successfully Uploaded");
						document.getElementById(id).value = null
						file = null;
					}else
						genericFactory.showNotification("Upload Failed -- "+res.data.statusDescription);
					if(res.data.body!=null){
						var pre="data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,";
						var inp = res.data.body.split("\"").join("");
						var objectUrl= pre+inp;
						window.open(objectUrl);
					}
				},function(err){
					genericFactory.showNotification(API.errorMsg+" Details : Status "+err.status+" "+err.statusText);
				});
			}else{
				genericFactory.showNotification("Please Upload A Valid File");				
			}
		}
	}])
})