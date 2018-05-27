define(['angular'],
		function(angular){
			return ['$http','API','genericFactory','$state',function($http,API,genericFactory,$state){
				    this.post = function(url,reqData){
				    	var contentType = null;
				    	var authorization = null;
				    	var transaction_id = null;
				    	var header = null;
			    		if(API.login_API == url){
			    			header={
				    			"Content-Type": 'application/json'
			    			}
			    		}else if(API.addSubscriber_API == url){
			    			header={
					    		"Content-Type": 'application/json',
				    			authorization: 'Bearer ' + window.btoa(genericFactory.decryptionS('token'))
				    		}
			    		}else{
			    			header={
						    		"Content-Type": 'application/json',
						    		authorization:  'Bearer '+ window.btoa(genericFactory.decryptionS('token'))
					    		}
			    		}
			            var reqForm ={
			                method:"POST",
			                url:url,
			                data: reqData,
			                headers:header,
			                timeout:9000000
			            };
//			            console.log(reqForm)
			            return $http(reqForm)
				    }
				    this.get = function(url){
			            var reqForm ={
			                method:"GET",
			                url:url,
			                headers:{
					    		"Content-Type": 'application/json',
			                	'Authorization':'Bearer '+ window.btoa(genericFactory.decryptionS('token'))
			                },
			                timeout:9000000
			            };
			            return $http(reqForm)
				    }

				    this.MultiPartPost = function (url,file,key,data){
				        var fd = new FormData();
				        fd.append('file', file);
				        if(key){
				        	var data = JSON.stringify(data);
				        	fd.append(key,data);
				        }
				        return $http.post(url, fd, {
				          transformRequest: angular.identity,
				          headers: {
				        	  'Content-Type': undefined,
				        	  'Authorization':'Bearer '+ window.btoa(genericFactory.decryptionS('token'))
				          },
			              timeout:9000000
				        })
				    }
				}]
		}
)