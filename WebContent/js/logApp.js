

/* @author Basker Ammu
 * Url Routing configuration
 * $stateProvider,$urlRouterProvider
 */

'use strict';
angular.module('logApp').config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
	      
		$urlRouterProvider.otherwise("/log");
		$stateProvider.state("log", {
			url : '/log',
			templateUrl : 'view/log.html',				
			controller: "logController",			
			
			});
			
			
}]);








