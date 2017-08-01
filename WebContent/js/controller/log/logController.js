

'use strict';
angular.module('logApp').controller('logController', ['$state', 'logService','globalServices', function($state, logService,globalServices) {
    var currentObj = this;
		currentObj.logJson = {};
		currentObj.messageParameters = {};
		currentObj.logXml = {};
		currentObj.auth_token='';
		currentObj.logXml = {};
		currentObj.username='basker';
	
		currentObj.saveLogJson=saveLogJson;
		currentObj.saveLogXml=saveLogXml;
		currentObj.generateToken=generateToken;	
		currentObj.expireToken=expireToken;	
		  function saveLogJson(){
			  alert("saveJupiterLogJson")
		currentObj.logJson.applicationName="CIRA"
		currentObj.logJson.severityLevel="1"
		currentObj.logJson.eventTime="2017-07-26 11:01:17.2040000"
		currentObj.logJson.errorDescription="Exception"
		currentObj.logJson.errorCode="404"
		currentObj.logJson.isSecurityIncident="True"
		currentObj.logJson.urlRequested="http://10.13.0.2/test/cira_jp_kr/"
		currentObj.logJson.methodName="Login"
		currentObj.logJson.currentUser="basker"
		currentObj.logJson.requestedIp="192.1.3.2.1"
		currentObj.logJson.transactionId="12121212121212"
		currentObj.logJson.actionMethod="POST"
		currentObj.logJson.messageFormat="JSON"
		currentObj.messageParameters.startDate="31-07-2017"
		currentObj.messageParameters.endDate="31-08-2017"
		currentObj.logJson.messageParameters=currentObj.messageParameters;
		console.log(currentObj.logJson)
		
		logService.saveLog(currentObj.logJson,"application/json",currentObj.username,currentObj.auth_token)
		        .then(
		        function(data) {
		        	 alert("Log  inserted")	
		        	  console.log(data)
		        	 
		        },
		        function(errResponse){
		        	 alert("Log Not inserted"+errResponse)	
		        }
		    );
		}
		  
		  function saveLogXml(){
			  alert("saveJupiterLogXml")
		currentObj.logXml="<LoggerMessage><applicationName>USDOCX</applicationName><eventType>insert</eventType> <eventTime>2017-07-26 17:06:17.2040000</eventTime>" +
				"<errorDescription>Exception details</errorDescription> <errorCode>404</errorCode> <isSecurityIncident>True</isSecurityIncident>" +
				"<urlRequested>http://10.13.0.2/test/cira_jp_kr/</urlRequested><methodName>Login</methodName>" +
				"<severityLevel>3</severityLevel><currentUser>ramesh</currentUser> <requestedIp>192.1.3.2.1</requestedIp>" +
				" <transactionId>12121212121212</transactionId> <actionMethod>POST</actionMethod> <messageFormat>XML</messageFormat> " +
				" <messageParameters><entry>	<key>endDate</key>	<value>28-07-2017</value></entry> <entry><key>pageNumber</key><value>1</value></entry>  " +
				"<entry>	<key>startDate</key><value>26-07-2017</value></entry></messageParameters></LoggerMessage>";

		console.log(currentObj.logJson)
		
		logService.saveLog(currentObj.logXml,"application/xml",currentObj.username,currentObj.auth_token)
		        .then(
		        function(data) {		        	
		        	 alert("Log  inserted")	
		        	 console.log(data)
		        },
		        function(errResponse){
		        	 alert("Log Not inserted"+errResponse)	
		        }
		    );
		}
		  function generateToken(){
			 	logService.generateToken(currentObj.username).then(
			        function(data) {
			        console.log(data)
			          currentObj.auth_token=data.auth_token;
			            alert("Token Generated: "+data.auth_token)
			        },
			        function(errResponse){
			        	  alert("Token Not Generated"+errResponse)	
			        }
			    );
		  }
		  
		  function expireToken(){
			 	logService.expireToken(currentObj.username)
			        .then(
			        function(data) {
			        console.log(data)	
			         alert("Token Expired")
			        },
			        function(errResponse){
			        	  alert("Token Not Expired"+errResponse)	
			        }
			    );
		  }
   
  }]);
