define(['angular'],
		function(angular){
			return ['API','httpService','$window','genericFactory',function(API,httpService,$window,genericFactory){
				var allData = null;
				var roles = null;
				var subRoles = null;
				this.getAllData = function(val){
//					if(allData==null){
						return httpService.get(API.getAllData).then(function(res){
							allData = res.data;
							return allData;
						})	
//					}else{
//						return allData;
//					}
				}
				this.getRoles = function(){
					if(roles==null){
						return httpService.get(API.getRoles_API).then(function(res){
							roles = res.data.body;
							return roles;
						})	
					}else{
						return roles;
					}
				}
				this.getSubRoles = function(){
					if(subRoles==null){
						return httpService.get(API.getSubRoles_API).then(function(res){
							subRoles = res.data.body;
							return subRoles;
						})	
					}else{
						return subRoles;
					}
				}
				this.getBase64Image = function(img){
					  var canvas = document.createElement("canvas");
					  canvas.height = img.naturalHeight;
					  canvas.width = img.naturalWidth;
					  var ctx = canvas.getContext("2d");
					  ctx.drawImage(img, 0, 0);
					  var dataURL = canvas.toDataURL("image/png");
					  return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
				}
				this.logout = function(){
		            httpService.get(API.logout_API+genericFactory.decryptionS('token'),null).then(function(res){
		                if(res.data.statusCode==200){
		                	window.sessionStorage.clear();
			                window.localStorage.clear();
			                window.location.href = "#!/login";
		                }else{
		                	genericFactory.showNotification("logout Error - "+res.data.statusDescription);
		                	window.sessionStorage.clear();
			                window.localStorage.clear();
			                window.location.href = "#!/login";
		                }
		            },function(error){
		                var errors = "Unable to connect to server...Please try again.";
		                genericFactory.showNotification(errors);
		            })
		        }
			}]
		}
)