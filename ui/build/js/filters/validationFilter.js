require(['js/mainjs/app'],
	function(app) {
		app.filter('validation',['CONSTANTS',function(CONSTANTS){
			return function (text) {
	          		return CONSTANTS[text]
	    	};
		}])
		app.filter('unique',['$filter', function($filter) {
			 return function(data,field) {
				 var result = [];
			    if(angular.isArray(data)) {
			    	
			    	var key = {};
		    		if(field)
		    			checkField(data,result)
		    		else
		    			checkWOField(data,result)
			    }
		    	function checkField(data,result){
		    		for(var i=0; i<data.length; i++) {
			    		var val = data[i][field];
			    		if($filter('filter')(result,{field:val}).length==0){
			    			result.push(data[i]);
			    		}
			    	}
		    	}
		    	function checkWOField(data,result){
		    		for(var i=0; i<data.length; i++) {
			    		var val = data[i];
			    		if(result.indexOf(val)===-1){
			    			result.push(data[i]);
			    		}
			    	}
		    	}
		    	return result;
		  }
		}]);
		app.filter('replace', function () {
		    return function (text) {
		      return text ? String(text).replace(/_/g,' ') : text;
		    };
		  })
	}
);