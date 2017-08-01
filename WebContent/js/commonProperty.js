

/*@author Basker Ammu
 * initialize the app module with the name logApp 
 * ui.router- routing based on $stateProvider and $urlRouterProvider(In html page url handling through ui-sref-active=""	ui-sref="")
 * appConfigApp constants to handle comman  rest url
 * commonServiceApp-to get the globally with reusing the services
  */

'use strict';
angular.module("logApp", ["ui.router",'appConfigApp','commonServiceApp']);



/* @author Basker Ammu
 * run
 * $on('$stateChangeSuccess'
 * It help to check the currentUser  details and  token is  undefined when the each state changes
 * */

'use strict';
angular.module('logApp').run([
			'$state','$http','$rootScope','globalServices',function ( $state,$http,$rootScope,globalServices) {
				
	   $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams) {	
	
	        return $state.go('log');	        
	     
	    
	  });
	   

	}]);




