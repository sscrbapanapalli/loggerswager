package com.cmacgm.java.bean;

import java.util.Map;

//@JsonIgnoreProperties(ignoreUnknown = true)
@javax.xml.bind.annotation.XmlRootElement(name="LoggerMessage")
public class LoggerMessage
{
  String applicationName;
  String eventStatus;
  String severityLevel;
  String eventTime;
  String errorDescription;
  String errorCode;
  String isSecurityIncident;
  String urlRequested;
  String methodName;
  String currentUser;
  String requestedIp;
  String transactionId;
  String actionMethod;
  String messageFormat;
  
  public LoggerMessage() {}
  
  private Map<String, String> messageParameters = new java.util.HashMap();
  
  public String getApplicationName() {
    return applicationName;
  }
  
  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }
  
  public String getEventStatus() {
    return eventStatus;
  }
  
  public void setEventStatus(String eventStatus) {
    this.eventStatus = eventStatus;
  }
  
  public String getSeverityLevel() {
    return severityLevel;
  }
  
  public void setSeverityLevel(String severityLevel) {
    this.severityLevel = severityLevel;
  }
  
  public String getEventTime() {
    return eventTime;
  }
  
  public void setEventTime(String eventTime) {
    this.eventTime = eventTime;
  }
  
  public String getErrorDescription() {
    return errorDescription;
  }
  
  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }
  
  public String getErrorCode() {
    return errorCode;
  }
  
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  
  public String getIsSecurityIncident() {
    return isSecurityIncident;
  }
  
  public void setIsSecurityIncident(String isSecurityIncident) {
    this.isSecurityIncident = isSecurityIncident;
  }
  
  public String getUrlRequested() {
    return urlRequested;
  }
  
  public void setUrlRequested(String urlRequested) {
    this.urlRequested = urlRequested;
  }
  
  public String getMethodName() {
    return methodName;
  }
  
  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }
  
  public String getCurrentUser() {
    return currentUser;
  }
  
  public void setCurrentUser(String currentUser) {
    this.currentUser = currentUser;
  }
  
  public String getRequestedIp() {
    return requestedIp;
  }
  
  public void setRequestedIp(String requestedIp) {
    this.requestedIp = requestedIp;
  }
  
  public String getTransactionId() {
    return transactionId;
  }
  
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  
  public String getActionMethod() {
    return actionMethod;
  }
  
  public void setActionMethod(String actionMethod) {
    this.actionMethod = actionMethod;
  }
  
  public String getMessageFormat() {
    return messageFormat;
  }
  
  public void setMessageFormat(String messageFormat) {
    this.messageFormat = messageFormat;
  }
  
  public Map<String, String> getMessageParameters() {
    return messageParameters;
  }
  
  public void setMessageParameters(Map<String, String> messageParameters) {
    this.messageParameters = messageParameters;
  }
  
  public String toString()
  {
    return 
    



      "{ApplicationName:" + applicationName + ", EventStatus:" + eventStatus + ", SeverityLevel:" + severityLevel + ", EventTime:" + eventTime + ", ErrorDescription:" + errorDescription + ", ErrorCode:" + errorCode + ", IsSecurityIncident:" + isSecurityIncident + ", UrlRequested:" + urlRequested + ", MethodName:" + methodName + ", CurrentUser:" + currentUser + ", RequestedIp:" + requestedIp + ", TransactionId:" + transactionId + ", ActionMethod:" + actionMethod + ", MessageFormat:" + messageFormat + ", MessageParameters:{" + messageParameters + "}}";
  }
}