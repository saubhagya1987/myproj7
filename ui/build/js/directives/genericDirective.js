require(['js/mainjs/app'],
	function(app) {
		app.directive('access', ['genericFactory','$state', function(genericFactory,$state) {
			return {
		       restrict: 'A',
		       link: function (scope, elem, $attrs) {
		    	   var hasrole= false;
		    	   var roles = $attrs.access.split(',');
		    	   if(!genericFactory.decryptionS("roleName")){
	    			   $state.go('login');
	    			   return;
	    		   }
		    	   for(var i=0; i < roles.length; i++ ){
		    		   if (genericFactory.decryptionS("roleName") == roles[i]) {
		    			   hasrole = true;
		    		   }
		    	   }
		    	   if (hasrole) {
		             elem.show();
		           } else {
		             elem.remove();
		    	   }               
		       }
		    }
		}]);
		app.directive('fileModel', ['$parse', function ($parse) {
		    return {
		        restrict: 'A',
		        require: 'ngModel',
		        link: function(scope, element, attrs,ctrl) {
		        	var model = $parse(attrs.ngModel);
		            var modelSetter = model.assign;
		            element.bind('change', function(){
		                scope.$apply(function(){
		                	ctrl.$validators.accept = function(){
		                		var filename = element[0].files[0].name;
		                		var res = filename.split('.');
		                		var acceptvalid = true;
		                		if(res.length>2){
		                			acceptvalid = false;
		                		}
		                		else{
		                			if(element[0].accept.indexOf(res[res.length-1]) != '-1'){
				                		acceptvalid = true;
				                	}
				                	else{
				                		acceptvalid = false;
				                	}
		                		}
		                		
			                	return acceptvalid;
		                	}
		                	
		                	modelSetter(scope, element[0].files[0]);
		                });
		            });
		        }
		    };
		}
		]);
		app.directive('popup',function(){
			return{
				restrict:'E',
				scope:{
					id:"@",
					title:"@",
					message:"@",
					oktext:"@",
					ok:"=",
					ok2text:"@",
					ok2:"=",
					cancel:"@"
				},
				templateUrl:'templates/common/confirmPopUp.html',
				link: function(scope,elem,$attrs){
				}
			}
		})
		app.directive('stats',function() {
	    	return {
	      		templateUrl:'templates/common/stats.html',
	      		restrict:'E',
	      		replace:true,
	      		scope: {
	            'model': '=',
	            'comments': '@',
	            'number': '@',
	            'name': '@',
	            'colour': '@',
	            'details':'@',
	            'type':'@',
	            'hideNo':'@'
	      		}
	    	}
	   })
	   	var EMAIL_REGEXP = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i ;
		app.directive('emailval', function() {
		  return {
		    require: 'ngModel',
		    link: function(scope, elm, attrs, ctrl) {
		      ctrl.$validators.emailval = function(modelValue, viewValue) {
		        if (ctrl.$isEmpty(modelValue)) {
		          // consider empty models to be valid
		          return true;
		        }

		        if (EMAIL_REGEXP.test(viewValue)) {
		          // it is valid
		          return true;
		        }

		        // it is invalid
		        return false;
		      };
		    }
		  };
		});
	   app.directive('filevalid', ["$q",function($q) {
		   return {
		   	 restrict: 'A',
		     require: 'ngModel',
		     link: function(scope, elem, attrs, ctrl) {
		    	 elem.bind("change", function(event){ 
		    		scope.$apply(function(){
//				    	 	ctrl.$validators.filevalid = function(modelValue, viewValue) {
			    	 		var sFileName = elem[0].value;
		  		     		var blnValid = false;
			  		     	if (sFileName && sFileName.length > 0) {
		  		                if (sFileName.split('.').length>2) {
		  		                	blnValid = false;
					  		     	ctrl.$setValidity('filevalid', blnValid);
		  		                }else{
		  		                	var photofile = elem[0].files[0];
		  		                	function mimeType(headerString) {
		  		                	  switch (headerString) {
		  		                	    case "89504e47":
		  		                	      type = "image/png";
		  		                	      break;
		  		                	    case "ffd8ffe0":
		  		                	    case "ffd8ffe1":
		  		                	    case "ffd8ffe2":
		  		                	      type = "image/jpeg";
		  		                	      break;
		  		                	    default:
		  		                	      type = "unknown";
		  		                	      break;
		  		                	  }
		  		                	  return type;
		  		                	}
		  		                	function getBLOBFileHeader(url, blob, callback) {
		  		                	  var fileReader = new FileReader();
		  		                	  fileReader.onloadend = function(e) {
		  		                	    var arr = (new Uint8Array(e.target.result)).subarray(0, 4);
		  		                	    var header = "";
		  		                	    for (var i = 0; i < arr.length; i++) {
		  		                	      header += arr[i].toString(16);
		  		                	    }
		  		                	    callback(header);
		  		                	  };
		  		                	  fileReader.readAsArrayBuffer(blob);
		  		                	}
		  		                	function remoteCallback(url, blob,finalReturn) {
	//	  		                	  printImage(blob);
		  		                	  getBLOBFileHeader(url, blob,  function(type){
		  		                		 if(mimeType(type)=='unknown'){
		  		                			blnValid = true;
			  		                		finalReturn(blnValid);
		  		                		 }else{
		  		                			var reader = new FileReader();
		  			  		      	        reader.onload = function(e) {
		  			  		      	        	if(elem.attr('zoomfn')){
		  			  		      	        			elem.siblings('#imgView').attr('name', e.target.result);
		  					  		      	            var obj = elem.attr('id');
		  					  		      	           	scope.$apply(function(){
		  					  		      	           		scope.imagesObject[obj+'Path'] = e.target.result;
		  					  		      	            })
		  			  		      	        	}
		  			  		      	        	else
		  			  		      	        		elem.siblings('img').attr('src', e.target.result);
		  			  		      	            
		  			  		      	        };
		  			  		      	        reader.readAsDataURL(photofile);
		  		                			blnValid = false;
			  		                		finalReturn(blnValid);
		  		                		 }
		  		                	  })
		  		                	  
		  		                	}
		  		                	remoteCallback(escape(photofile.name), photofile,function(blnValid2){
		  		                		blnValid = blnValid2;
					  		     		ctrl.$setValidity('filevalid', !blnValid);
//			                					return blnValid;
		  		                	});
		  		                }
	//	  		              blnValid = true;
	//	  		              return blnValid;
			  		     	}else{
			  		     		blnValid = true;
			  		     		ctrl.$setValidity('filevalid', !blnValid);
			  		     	}	  		

//				  		     	return blnValid;
//					       }
		    		})
		    	 })
		     }
		   }
	   }]);
//	   app.directive('rotate', function () {
//		    return {
//		        restrict: 'A',
//		        link: function (scope, element, attrs) {
//		            scope.$watch(attrs.degrees, function (rotateDegrees) {
////		                console.log(rotateDegrees);
//		                var r = 'rotate(' + rotateDegrees + 'deg)';
//		                element.css({
//		                    '-moz-transform': r,
//		                    '-webkit-transform': r,
//		                    '-o-transform': r,
//		                    '-ms-transform': r
//		                });
//
//		            });
//		        }
//		    }
//		});
})