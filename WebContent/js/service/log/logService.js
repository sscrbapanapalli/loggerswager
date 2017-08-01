/* @author Basker Ammu
*/
'use strict';
angular.module('logApp').factory('logService', ['$http', '$q', 'appConstants', function($http, $q,appConstants){
 
    var REST_SERVICE_URI = appConstants.baseUrl; 

    var factory = {    		
    		saveLog:saveLog,
    		generateToken:generateToken,
    		expireToken:expireToken,
    		
    };

    return factory;

    
    function saveLog(saveLog,contentType,username,auth_token) {
        var deferred = $q.defer();
        
        $http.post(REST_SERVICE_URI+"/jupiterLog", saveLog, {headers:{'Content-Type': contentType,'username':username,'auth_token':auth_token}})       
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                  deferred.reject(errResponse);
            }
        );   
        return deferred.promise;
    }   
    
    function generateToken(username) {
    	
    	console.log(username)
        var deferred = $q.defer();
    	
    	$http({
    		  method: 'POST',
    		  url: REST_SERVICE_URI+"/generateToken",
    		  headers: {
    		    'Content-Type': 'application/json',
    		    'username': username
    		  }
    		}).then(function(response) {    			
    			 deferred.resolve(response.data);
    		},function(errResponse){
                  deferred.reject(errResponse);
            }
    		);   
    	   return deferred.promise;
       }
    	function expireToken(username) {
        	
        	console.log(username)
            var deferred = $q.defer();
        	
        	$http({
        		  method: 'POST',
        		  url: REST_SERVICE_URI+"/expireToken",
        		  headers: {
        		    'Content-Type': 'application/json',
        		    'username': username
        		  }
        		}).then(function(response) {    			
        			 deferred.resolve(response.data);
        		},function(errResponse){
                      deferred.reject(errResponse);
                }
        		);
        	   return deferred.promise;
    	}
      
     
      

}]);

