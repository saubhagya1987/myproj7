define(['angular','angular-translate-loader-static-files'], 
    function(angular,trans) {
	    'use strict';
	    return ['$translateProvider',function($translateProvider) {
	    			$translateProvider.useSanitizeValueStrategy('sanitize');
					$translateProvider.useStaticFilesLoader({
						prefix : 'assets/translations/',
						suffix : '.json'
					});
					
					//.preferredLanguage('en');
					$translateProvider.registerAvailableLanguageKeys([ 'en', 'sw' ], {
						'en_US' : 'en',
						'en_UK' : 'en',
						'sw_TZ' : 'sw'
					});
					//auto determine preferred lang
					//$translateProvider.determinePreferredLanguage();
					// tells angular-translate to use the German language
					//$translateProvider.preferredLanguage('sw');
					//when can not determine lang, choose en lang.
					$translateProvider.fallbackLanguage('en');
					
				}]
	}
)