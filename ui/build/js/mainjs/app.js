define(['angular','angular-ui-router','ocLazyLoad','ngSanitize','bootstrap','pascalprecht.translate','js/mainjs/routes','js/mainjs/translate','js/mainjs/constants','js/services/httpService','jquery','js/services/genericService','js/services/genericFactory','js/mainjs/otherConstants','js/directives/genericDirective','loading-bar'],
		function(angular,router,oclazyload,sanitize,bootstrap,pascalprecht,routes,translate,constants,httpService,$,genericService,genericFactory,otherConstants,directive,loading){
			'use strict';
			var app = angular.module('zambiaApp', ['ui.router','oc.lazyLoad','ngSanitize','pascalprecht.translate','angular-loading-bar']);
			app.config(routes);
			app.config(translate);
			app.constant('API',constants);
			app.constant('CONSTANTS',otherConstants);
			app.service('httpService',httpService);
			app.service('genericService',genericService)
			app.factory('genericFactory',genericFactory)
			app.config(['$sceProvider','$ocLazyLoadProvider',function($sceProvider,$ocLazyLoadProvider){
				$sceProvider.enabled(false);
				$ocLazyLoadProvider.config({
				  debug: debug
				});
			}])
			// app.config(function($sceDelegateProvider) {
			//   $sceDelegateProvider.resourceUrlWhitelist([
			//     // Allow same origin resource loads.
			//     'self',
			//     // Allow loading from our assets domain.  Notice the difference between * and **.
			//     // 'http://srv*.assets.example.com/**'
			//   ]);
			// })
			app.run(['genericFactory','$window',function(genericFactory,$window){
				try {
					if (top.location.hostname != self.location.hostname) throw 1;
				}catch (e) {
					top.location.href = self.location.href;
				}
				$window.addEventListener("offline", function () {
					genericFactory.showNotification("Check Internet Connection. You are Offline right now");
				}, false);
			    $window.addEventListener("online", function () {
			    	genericFactory.showNotification("You are now Online");
			    }, false);
			}])	
			return app;
})