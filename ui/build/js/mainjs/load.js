var loadTimeModal = 1;
require.config({
    baseUrl: '',
    waitSeconds: 0,
    paths: {
    	'angular' : 'libs/vendor/angular/angular.min',
        'angular-ui-router' : 'libs/vendor/angular-ui-router/release/angular-ui-router.min',
        'ocLazyLoad' : 'libs/vendor/oclazyload/dist/ocLazyLoad.min',
        'bootstrap' : 'libs/vendor/bootstrap/dist/js/bootstrap.min',
        'jquery' : 'libs/vendor/jquery/dist/jquery.min',
        'date-picker' : 'libs/vendor/angularjs-datepicker/dist/angular-datepicker.min',
        'ngSanitize' : 'libs/vendor/angular-sanitize/angular-sanitize.min',
        'pascalprecht.translate' : 'libs/vendor/angular-translate/angular-translate.min',
        'angular-translate-loader-static-files' : 'libs/vendor/angular-translate-loader-static-files/angular-translate-loader-static-files.min',
        'pagination' : 'libs/vendor/angular-paging/dist/paging.min',
        'image64' : 'libs/vendor/angular-base64-upload/dist/angular-base64-upload',
        'loading-bar': 'libs/vendor/angular-loading-bar/build/loading-bar.min',
//        'ck':'js/commonjs/ck-editor/ckeditor',
//        'ckeditor':'libs/vendor/angular-ckeditor/angular-ckeditor.min',
        'cm':'js/commonjs/CanvasViewer.min',
	},
	shim:{
        'jquery':{
            exports: '$'
        },
        'angular': {
            deps : ['jquery'],
            exports: 'angular'
        },
        'angular-ui-router':{
            deps: ['angular']
        },
        'ocLazyLoad':{
            deps: ['angular']
        },
        'bootstrap': {
            deps:['jquery']
        },
        'ngSanitize':{
        	deps:['angular']
        },
        'pascalprecht.translate':{
        	deps:['angular']
        },
        'angular-translate-loader-static-files':{
        	deps:['pascalprecht.translate']
        },
        'pagination':{
        	deps:['angular']
        },
        'date-picker':{
        	deps:['jquery','angular','bootstrap']
        },
        'image64':{
        	deps:['angular']
        },
        'loading-bar':{
        	deps:['angular']
        },
//        'ckeditor':{
//        	deps:['angular']
//        },
        'cm':{
        	deps:['angular']
        }
    }
})


require(['js/mainjs/app'],
    function() {
        angular.element(document).ready(function() {
            function htmlbodyHeightUpdate(){
        		var height3 = $( window ).height();
        		var height1 = $('.nav').height()+50;
        		var height2 = $('.main').height();
        		if(height2 > height3){
        			$('html').height(Math.max(height1,height3,height2)+10);
        			$('body').height(Math.max(height1,height3,height2)+10);
        		}
        		else
        		{
        			$('html').height(Math.max(height1,height3,height2));
        			$('body').height(Math.max(height1,height3,height2));
        		}
        		
        	}
    		
            htmlbodyHeightUpdate();
    		$( window ).resize(function() {
    			htmlbodyHeightUpdate();
    		});
    		$( window ).scroll(function() {
    			height2 = $('.main').height();
      			htmlbodyHeightUpdate();
    		});
    		
            angular.bootstrap(document, ['zambiaApp']);
        });
    }
);