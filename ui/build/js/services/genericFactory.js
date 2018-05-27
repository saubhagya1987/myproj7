define(['angular','jquery'],
		function(angular,jquery){
			return ['API',function(API){
				return {
					encryptionL : function(field,data){
						window.localStorage.setItem(field,window.btoa(JSON.stringify(data)+API.secret_key));
					},
					decryptionL : function(field){
						return window.atob(JSON.parse(window.localStorage.getItem(field))).replace(API.secret_key,'');
					},
					encryptionS : function(field,data){
						window.sessionStorage.setItem(field,window.btoa(JSON.stringify(data)+API.secret_key));
					},
					decryptionS : function(field){
						if(window.sessionStorage.getItem(field))
						return JSON.parse(window.atob(window.sessionStorage.getItem(field)).replace(API.secret_key,''));
					},
					showNotification: function(message,title,ok){
						if(title)
							angular.element(document.getElementById('showNotificationMessageTitle')).html(title);
						else
							angular.element(document.getElementById('showNotificationMessageTitle')).html("Notification");
						
						if(ok)
							angular.element(document.getElementById('showNotificationOk')).html(ok);
						else
							angular.element(document.getElementById('showNotificationOk')).html("OK");
						if(message)
							angular.element(document.getElementById('showNotificationMessage')).html(message);
						else
							angular.element(document.getElementById('showNotificationMessage')).html(API.blank_msg);
						angular.element(document.getElementById('showNotification')).modal('show');
					},
					showPreloader : function() {
						$("#loader").show();
						$("#popupbackground").show();
					},
					closePreloader : function() {
						$("#popupbackground").hide();
						$("#loader").hide();
					},
					getRandomSpan : function(){
						return Math.floor((Math.random()*100000000)+1);
					},
					getBase64Image : function(img) {
						if(img.localName == 'img'){
							var canvas = document.createElement("canvas");
							canvas.height = img.naturalHeight;
							canvas.width = img.naturalWidth;
							var ctx = canvas.getContext("2d");
							ctx.drawImage(img, 0, 0);
							var dataURL = canvas.toDataURL("image/jpg");
							return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
						}else{
							var dataURL = img.toDataURL("image/jpg");
							return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
						}
					},
//					getBase64Image:  function(url, callback) {
//					    var xhr = new XMLHttpRequest();
//					    xhr.onload = function() {
//					        var reader = new FileReader();
//					        reader.onloadend = function() {
//					            callback(reader.result);
//					        }
//					        reader.readAsDataURL(xhr.response);
//					    };
//					    xhr.open('GET', url);
//					    xhr.responseType = 'blob';
//					    xhr.send();
//					},
					getEncryption:function(data){
						//RSA Encryption
						var encrypt = new JSEncrypt();
						encrypt.setPublicKey(API.RSA_Publickey);
						var timestamp = btoa(new Date().getTime().toString());
						var encrypted = encrypt.encrypt(timestamp);
						var encryptedVal = CryptoJS.AES.encrypt(JSON.stringify(data), timestamp);
						encryptedVal = encryptedVal.toString();
						if(debug)
							console.log(CryptoJS.AES.decrypt(encryptedVal, timestamp).toString(CryptoJS.enc.Utf8));
						return payload =  {
								  encyptredKey: encrypted,
								  encyptedValue: encryptedVal,
								  tokenKey: API.token_key,
								  tokenValue: API.token_val
								}
					},
					getImageURL : function(imgPath,kycId,filename){
							return(API.imageURL+imgPath+'/'+kycId+'/'+filename+'.jpg');
					},
					getUserImageURL : function(imgPath,kycId,filename){
						return(API.userImageURL+imgPath+'/'+kycId+'/'+filename+'.jpg');
					},
					checkUser : function(arr){
						var flag = false;
						for(var i=0;i<arr.length;i++){
							if(arr[i]==this.decryptionS('subRoleId')){
								flag = true;
								break;
							}
							else
								flag = false;
						}
						return flag;
					},
					getIp: function(onNewIP) { //  onNewIp - your listener function for new IPs
					    //compatibility for firefox and chrome
					    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
					    var pc = new myPeerConnection({
					        iceServers: []
					    }),
					    noop = function() {},
					    localIPs = {},
					    ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
					    key;

					    function iterateIP(ip) {
					        if (!localIPs[ip]) onNewIP(ip);
					        localIPs[ip] = true;
					    }

					     //create a bogus data channel
					    pc.createDataChannel("");

					    // create offer and set local description
					    pc.createOffer(function(sdp) {
					        sdp.sdp.split('\n').forEach(function(line) {
					            if (line.indexOf('candidate') < 0) return;
					            line.match(ipRegex).forEach(iterateIP);
					        });
					        
					        pc.setLocalDescription(sdp, noop, noop);
					    }, noop); 

					    //listen for candidate events
					    pc.onicecandidate = function(ice) {
					        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
					        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
					    };
					}
				}
			}]})