 
 var config=angular.module("appConfigApp", [])
.constant("appConstants",{
	clientUrl : "http://10.13.94.42:8080/JupiterLog/rest/log/",	
	/*test*/
	baseUrl : "http://10.13.94.42:8080/JupiterLog/rest/log",
}).run(function($http, $window) {	
	
	//$http.defaults.headers.common['auth_token'] =$window.sessionStorage["auth_token"]?$window.sessionStorage["auth_token"]: null;
});
 
 
 
 angular.module('appConfigApp') 
 .config(function ( $httpProvider) {       

     delete $httpProvider.defaults.headers.common['X-Requested-With'];
 });