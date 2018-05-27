var allinfo;
define(['js/mainjs/interceptor'], 
    function(interceptor) {
    'use strict';
    return ['$stateProvider','$urlRouterProvider', '$httpProvider', '$locationProvider','cfpLoadingBarProvider',
        function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider,cfpLoadingBarProvider) {
    		$httpProvider.interceptors.push(interceptor);
    		cfpLoadingBarProvider.includeSpinner = false;
    		cfpLoadingBarProvider.parentSelector = '#loading-bar-container';
	    	delete $httpProvider.defaults.headers.common["X-Requested-With"];// cross domain solution
	        $httpProvider.defaults.useXDomain = true;
	        $httpProvider.defaults.cache = false;
	        $httpProvider.defaults.timeout = 999999;
    		$urlRouterProvider.otherwise('/login');
    		$stateProvider
    		.state('login',{
    	        templateUrl:'templates/login.html',
    	        url:'/login',
    	        controller:'loginController',
    	        resolve:{
    	        	 loadStaticViewCtrl : ['$ocLazyLoad', '$rootScope', '$q', function ($ocLazyLoad, $rootScope, $q) {
                     	var deferred = $q.defer();
                     	require(["js/controllers/loginController"], function () {
                     		$ocLazyLoad.inject('zambiaApp');
//                     		$rootScope.setLoader(false,'');
                            deferred.resolve();
                     	})
                     	return deferred.promise;
                     }]
    	        }
    	    })
		    .state('dashboard', {
		    	url:'/dashboard',
		    	views:{
		    		'':{
				    	templateUrl: 'templates/common/main.html',
				    	controller:'mainController'
		    		},
		    		'header@dashboard':{
				    	templateUrl: 'templates/common/header.html',
				    	controller:'headerController'
		    		}
		    	},
		    	abstract: true,
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','genericService', function ($ocLazyLoad, $q,genericFactory,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.decryptionS('token')!=undefined){
                         	genericService.getAllData().then(function(data){
                         		allinfo = data;
    	                 		require(["js/controllers/mainController","js/controllers/headerController",'pagination','image64','js/filters/validationFilter'], function () {
    	                     		$ocLazyLoad.inject(['zambiaApp','bw.paging','naif.base64']);
    	                            deferred.resolve();
    	                     	})
                         	})
                     	}else{
                     		genericFactory.showNotification('You are not logged in');
                     		window.location.href = '#!/login';
                     	}
                     	return deferred.promise;
                     }]
		        }
		    })
		    .state('dashboard.home', {
		    	url:'/home',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/home.html'
		    		}
		    	}
		    })
		    .state('dashboard.addSubscriber', {
		    	url:'/add-customer',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/subscriberAdd.html',
		    			controller :'subscriberController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberController",'date-picker'], function () {
	                     		$ocLazyLoad.inject(['zambiaApp','720kb.datepicker']);
	                     		$ocLazyLoad.load(['libs/vendor/angularjs-datepicker/dist/angular-datepicker.min.css']);
	                            deferred.resolve();
	                     	})
	                    else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();
	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
		    // .state('dashboard.subscriberManagement', {
		    // 	url:'/subscribers',
		    // 	views:{
		    // 		'content':{
		    // 			templateUrl :'templates/subscriberManagement.html',
		    // 			controller :'subscriberController'
		    // 		}
		    // 	},
		    //     resolve: {
		    //     	loadStaticViewCtrl2 : ['$ocLazyLoad', '$rootScope', '$q', function ($ocLazyLoad, $rootScope, $q) {
      //                	var deferred = $q.defer();
      //                	if(genericFactory.checkUser(API.cp_access))
	     //                 	require(["js/controllers/subscriberController"], function () {
	     //                 		$ocLazyLoad.inject('zambiaApp');
	     //                        deferred.resolve();
	     //                 	})
	     //                else{
	     //                	genericFactory.showNotification('You are not authorised to access this URL');
	     //                	genericService.logout();

	     //                }
      //                	return deferred.promise;
      //                }]
		    //     }
		    // })
		    .state('dashboard.editSubscriber', {
		    	url:'/edit-customer',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/subscriberEdit.html',
		    			controller :'subscriberController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();
	                    }
                     	return deferred.promise;
                     }]
		        }
		    }) 
		    .state('dashboard.editNotApprovedSubscriber', {
		    	url:'/edit-not-approved-customer',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/subscriberEdit.html',
		    			controller :'subscriberController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
		    .state('dashboard.editBarredSubscriber', {
		    	url:'/edit-barred-customer',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/subscriberEdit.html',
		    			controller :'subscriberController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
		    .state('dashboard.addSubscriberNewSim', {
		    	url:'/add-customer-existing-user',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/subscriberEdit.html',
		    			controller :'subscriberController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
		    .state('dashboard.subscriberAmReg', {
		    	url:'/customer-am-registration',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/subscriberAmRegistration.html',
		    			controller :'subscriberAmRegistrationController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberAmRegistrationController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
//		    .state('dashboard.searchSubscriber', {
//		    	url:'/search-customer',
//		    	views:{
//		    		'content':{
//		    			templateUrl :'templates/subscriberSearch.html',
//		    			controller :'subscriberController'
//		    		}
//		    	},
//		        resolve: {
//		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
//                     	var deferred = $q.defer();
//                     	require(["js/controllers/subscriberController"], function () {
//                     		$ocLazyLoad.inject('zambiaApp');
//                            deferred.resolve();
//                     	})
//                     	return deferred.promise;
//                     }]
//		        }
//		    })
		    .state('dashboard.commissionRulesEdit', {
		    	url:'/commission-rules-edit',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/commissionRulesEdit.html',
		    			controller :'commissionRulesEditController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.admin_access))
//	                     	require(['ck','ckeditor',"js/controllers/commissionRulesEditController"], function () {
	                     	require(["js/controllers/commissionRulesEditController"], function () {
//	                     		$ocLazyLoad.inject('ckeditor');
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();
	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
		    .state('dashboard.commissionRules', {
		    	url:'/commission-rules',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/commissionRules.html',
		    			controller :'commissionRulesEditController'
		    		}
		    	},
		        resolve: {
		        	loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/commissionRulesEditController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]
		        }
		    })
		    .state('dashboard.userManagement', {
		    	url:'/users',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/userManagement.html',
		    			controller :'userManagementController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.admin_access.concat(API.snd_access)))
	                     	require(["js/controllers/userManagementController",'date-picker'], function () {
	                     		$ocLazyLoad.inject(['zambiaApp','720kb.datepicker']);
	                     		$ocLazyLoad.load(['libs/vendor/angularjs-datepicker/dist/angular-datepicker.min.css']);
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]    		
		    	}
		    })
		    .state('dashboard.management', {
		    	url:'/manage-data',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/management.html',
		    			controller :'managementController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.admin_access))
	                     	require(["js/controllers/commissionRulesController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]	    		
		    	}
		    })
		    .state('dashboard.resetPassword', {
		    	url:'/reset-password',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/resetPassword.html',
		    			controller :'resetPasswordController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.admin_access))
	                     	require(["js/controllers/resetPasswordController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]	    		
		    	}
		    })
		    .state('dashboard.searchCustomer', {
		    	url:'/search-customers',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/searchCustomer.html',
		    			controller :'subscriberController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.cp_access))
	                     	require(["js/controllers/subscriberController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]	    		
		    	}
		    })
		    .state('dashboard.openCases', {
		    	url:'/open-cases',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/openCase.html',
		    			controller :'openCaseController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.de_access.concat(API.sp_access)))
	                     	require(["js/controllers/openCaseController",'cm'], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                     		$ocLazyLoad.inject('CanvasViewer');
	                            $ocLazyLoad.load('assets/css/CanvasViewer.min.css');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]    		
		    	}
		    })
		    .state('dashboard.closeCases', {
		    	url:'/close-cases',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/closeCase.html',
		    			controller :'closeCaseController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.sp_access))
	                     	require(["js/controllers/closeCaseController",'cm'], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                     		$ocLazyLoad.inject('CanvasViewer');
	                            $ocLazyLoad.load('assets/css/CanvasViewer.min.css');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]	    		
		    	}
		    })
		    .state('dashboard.bulkUpload', {
		    	url:'/bulk-upload',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/bulkUpload.html',
		    			controller :'bulkUploadController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.bulk_access))
	                     	require(["js/controllers/bulkUploadController"], function () {
	                     		$ocLazyLoad.inject('zambiaApp');
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]	    		
		    	}
		    })
		    .state('dashboard.createCp',{
		    	url:'/create-cp',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/createCp.html',
		    			controller :'userManagementController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	if(genericFactory.checkUser(API.snd_access))
	                     	require(["js/controllers/userManagementController",'date-picker'], function () {
	                     		$ocLazyLoad.inject(['zambiaApp','720kb.datepicker']);
	                     		$ocLazyLoad.load(['libs/vendor/angularjs-datepicker/dist/angular-datepicker.min.css']);
	                            deferred.resolve();
	                     	})
                     	else{
	                    	genericFactory.showNotification('You are not authorised to access this URL');
	                    	genericService.logout();

	                    }
                     	return deferred.promise;
                     }]    		
		    	}
		    })
		    .state('dashboard.reportsHome',{
		    	url:'/reports-home',
		    	views:{
		    		'content':{
		    			templateUrl :'templates/reportsHome.html'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	require(["js/controllers/reportsController"], function () {
                     		$ocLazyLoad.inject('zambiaApp');
                            deferred.resolve();
                     	})
                     
                     	return deferred.promise;
                     }]    		
		    	}
		    })
		    .state('dashboard.reports',{
		    	url:'/reports/:report',
		    	params:{
		    		report: null
		    	},
		    	views:{
		    		'content':{
		    			templateUrl :'templates/reports.html',
		    			controller :'reportsController'
		    		}
		    	},
		    	resolve:{
		    		loadStaticViewCtrl : ['$ocLazyLoad', '$q','genericFactory','API','genericService', function ($ocLazyLoad, $q,genericFactory,API,genericService) {
                     	var deferred = $q.defer();
                     	require(["js/controllers/reportsController"], function () {
                     		$ocLazyLoad.inject('zambiaApp');
                            deferred.resolve();
                     	})
                     	
                     	return deferred.promise;
                     }]	    		
		    	}
		    })
		    .state('apk',{
		       templateUrl:'templates/apk.html',
		       url:'/download/apk',
		       controller: ['$scope','genericFactory','loadUrl',function($scope,genericFactory,loadUrl){
		    	   	$scope.url = loadUrl;
		    		genericFactory.closePreloader();
		       }],
		       resolve:{
		    	   loadUrl:['$http','API',function($http,API){
//		    		   return $http.get(API.APK_API).then(function(res){
//		    			   return res.data.apkDownloadUrl
//		    		   })
		    		   return API.APK_LINK;
		    	   }]
		       }
			})
    	}
    ]
})