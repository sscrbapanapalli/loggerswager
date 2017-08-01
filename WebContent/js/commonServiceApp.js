var commonServices = angular.module("commonServiceApp", ['appConfigApp']);

angular.module('commonServiceApp') 
.config(function ( $httpProvider) {        
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
commonServices.service("globalServices", ['$q', '$http', 'appConstants', '$window', function ($q, $http, appConstants, $window) {
	return {		
		isUserTokenAvailable : function () {
				return $window.sessionStorage["auth_token"];
		}
	}


}]);