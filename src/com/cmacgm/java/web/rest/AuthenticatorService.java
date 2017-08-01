package com.cmacgm.java.web.rest;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.security.auth.login.LoginException;




public final class AuthenticatorService
{
  private static AuthenticatorService authenticator = null;
  


  private final Map<String, String> authorizationTokensStorage = new HashMap();
  
  private AuthenticatorService() {}
  
  public static AuthenticatorService getInstance()
  {
    if (authenticator == null) {
      authenticator = new AuthenticatorService();
    }
    
    return authenticator;
  }
  
  public boolean login(String authToken, String username) throws LoginException
  {
    if (authorizationTokensStorage.containsKey(username)) {
      String oldToken = (String)authorizationTokensStorage.get(username);
      if ((oldToken != null) && (oldToken != "")) {
        if (oldToken.equals(authToken)) {
          return true;
        }
        return false;
      }
      return false;
    }
    


    throw new LoginException("In valid User");
  }
  
  public String getToken(String username) throws LoginException
  {
    if ((username != null) && (!authorizationTokensStorage.containsKey(username))) {
      UUID uuid = UUID.randomUUID();
      authorizationTokensStorage.put(username, uuid.toString());
      return uuid.toString(); }
    if (username != null) {
      return (String)authorizationTokensStorage.get(username);
    }
    return "";
  }
  








  public boolean isAuthTokenValid(String authToken)
  {
    if (authorizationTokensStorage.containsKey(authToken)) {
      return true;
    }
    
    return false;
  }
  
  public boolean expireToken(String username)
  {
    if (authorizationTokensStorage.containsKey(username)) {
      String authTokenValue = (String)authorizationTokensStorage.get(username);      
      if (authTokenValue != null)
      {
        authorizationTokensStorage.remove(username);
        return true;
      }
    }
    return false;

  }
}