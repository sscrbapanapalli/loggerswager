package com.cmacgm.jupiterlog.jaxrs.log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.security.auth.login.LoginException;

/**
 * Defines the method and variables of the HashMap token Storage.
 * 
 * @author Basker Ammu
 */
public final class AuthenticatorService {
	private static AuthenticatorService authenticator = null;

	@SuppressWarnings("unchecked")
	private final Map<String, String> authorizationTokensStorage = new HashMap();

	private AuthenticatorService() {
	}

	public static AuthenticatorService getInstance() {
		if (authenticator == null) {
			authenticator = new AuthenticatorService();
		}

		return authenticator;
	}

	public boolean login(String authToken, String username) throws LoginException {
		if (authorizationTokensStorage.containsKey(username)) {
			String oldToken = (String) authorizationTokensStorage.get(username);
			if ((oldToken != null) && (oldToken != "")) {
				if (oldToken.equals(authToken)) {
					return true;
				}
			}

		} else
			return false;

		throw new LoginException("In valid User");
	}

	public String getToken(String username) throws LoginException {
		if ((username != null) && (!authorizationTokensStorage.containsKey(username))) {
			UUID uuid = UUID.randomUUID();
			authorizationTokensStorage.put(username, uuid.toString());
			return uuid.toString();
		} else if (username != null && authorizationTokensStorage.containsKey(username)) {
			return (String) authorizationTokensStorage.get(username);
		} else
			return "";
	}

	public boolean isAuthTokenValid(String authToken) {
		if (authorizationTokensStorage.containsKey(authToken)) {
			return true;
		}

		return false;
	}

	public boolean expireToken(String username) {
		if (authorizationTokensStorage.containsKey(username)) {
			String authTokenValue = (String) authorizationTokensStorage.get(username);
			if (authTokenValue != null) {
				authorizationTokensStorage.remove(username);
				return true;
			}
		}
		return false;

	}
}