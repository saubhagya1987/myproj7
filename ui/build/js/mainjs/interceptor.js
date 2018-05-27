define(['angular'], function(angular) {
 'use strict';
    return['$q','genericFactory','API',
	    function($q,genericFactory,API) {
    		return {
    	    request: function (conf){
    	    	var token = genericFactory.decryptionS('token');
    	    	if((conf.url.search(".html") == -1 && conf.url.search(".json") == -1))
    	    		genericFactory.showPreloader();
                if((conf.url.indexOf(".html") != -1 || conf.url.indexOf(".json") != -1)) {
                    if (conf.url.indexOf('?') >= 0) {
                        if (conf.url.substring(0, conf.url.length - 1) === '&') {
                        conf.url += 'version=' + new Date().getTime();
                        }
                        conf.url += '&version=' + new Date().getTime();
                    } else {
                        conf.url += '?version=' + new Date().getTime();
                    }
                    return conf;
                }else
                	return conf;
    	    },
    	    requestError: function (config) {
    	    	genericFactory.closePreloader();
    	    },
    	    responseError: function (rejection) {
    	    	var token = genericFactory.decryptionS('token');
	    	    if (rejection.status == -1 || token===undefined) {
	    	    	if(window.location.href != "#!/login")
	    	    	window.location.href = "#!/login";
	    	    }
    	    	if(rejection.status == 401){
    	    		genericFactory.showNotification(rejection.data.error_description);
    	    	}
    	    	genericFactory.closePreloader();
    	    	return $q.reject(rejection);
    	    },
    	    response: function(res) {
    	    	if((res.config.url.search(".html") == -1 && res.config.url.search(".json") == -1))
    	    		genericFactory.closePreloader();
    	    	if(res.data.encryptedValue){
    	    		// var iterationCount = 1000;
    	    		// var keySize = 128;
    	    		// var passphrase = 'Secret Passphrase';
    	    		// var four = "b4d0febfe0df20d742b12650dd932101";
    	    		// var salt = "3b1c7b4c914daeacc4853ee0ade912f4"
    	    		//var aesUtil = new AesUtil(API.key_, iterationCount);
    	    		res.data = JSON.parse(API.aesUtil.decrypt(API.salt,API.IV,API.passphrase,res.data.encryptedValue));
    	    		if(debug)
    	    			console.log("after Request url---"+res.config.url+" data---",JSON.stringify(res.data))
    	    		return res;
    	    	}else{
    	    		if(res.data.statusDescription == "Something Went wrong. Please try again later."){
    	    			genericFactory.showNotification(res.data.statusDescription);	
    	    		}
    	    		return res;
    	    	}
    	      }
    	  };
	    }
    ]
})