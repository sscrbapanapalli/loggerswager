package com.cmacgm.java.web.rest;

import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;
import org.json.JSONObject;

import com.cmacgm.java.bean.LoggerMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

@Path("/log")
public class LogResource {
	public static final String AUTH_TOKEN = "auth_token";
	static final Logger ciralogger = Logger.getLogger("ciralog");
	static final Logger usdocxlogger = Logger.getLogger("usdocxlog");

	public LogResource() {
	}

	@POST
	@Path("jupiterLog")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	@ManagedAsync
	public void login(@Context HttpHeaders httpHeaders, LoggerMessage loggerMessage,
			final @Suspended AsyncResponse response) throws JsonParseException, JsonMappingException, IOException {
		AuthenticatorService demoAuthenticator = AuthenticatorService.getInstance();

		String authTokenKey = httpHeaders.getHeaderString("auth_token");
		String authUser = httpHeaders.getHeaderString("username");
		JSONObject json = new JSONObject();

		try {
			if ((authTokenKey != null) && (authUser != null)) {
				boolean authToken = demoAuthenticator.login(authTokenKey, authUser);
				if ((loggerMessage != null) && (authToken)) {
					runMe(loggerMessage, response);

				}
			}

		}

		catch (LoginException ex) {
			throw new RuntimeException(ex.getMessage());
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		json.put("status", "error");
		json.put("code", Response.Status.NOT_FOUND.getStatusCode());
		json.put("message", "Invalid Crendentails");
		response.resume(json.toString());// Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
	}

	public void runMe(LoggerMessage logVar, AsyncResponse response) {
		JSONObject json = new JSONObject();
		try {
			if ((logVar != null)) {

				Gson jsonObject = new Gson();
				if (logVar.getApplicationName().equals("CIRA")) {
					if (ciralogger.isInfoEnabled()) {
						ciralogger.info(jsonObject.toJson(logVar));
						json.put("status", "success");
						json.put("code", Response.Status.OK.getStatusCode());
						json.put("message", "Log Inserted");
						response.resume(json.toString());
					}
				} else if ((logVar.getApplicationName().equals("USDOCX")) && (usdocxlogger.isInfoEnabled())) {
					usdocxlogger.info(jsonObject.toJson(logVar));
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Log Inserted");
					response.resume(json.toString());
				}
			}
			json.put("status", "error");
			json.put("code", Response.Status.NOT_FOUND.getStatusCode());
			json.put("message", "Invalid Crendentails");
			response.resume(json.toString());

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@POST
	@Path("generateToken")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ManagedAsync
	public void generateToken(@Context HttpHeaders httpHeaders, final @Suspended AsyncResponse response) {

		AuthenticatorService demoAuthenticator = AuthenticatorService.getInstance();
		String authUser = httpHeaders.getHeaderString("username");
		JSONObject json = new JSONObject();
		try {
			if (authUser != null) {
				String authToken = demoAuthenticator.getToken(authUser);
				if ((authToken != "") && (authToken != null)) {
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Token is Generated");
					json.put("auth_token", authToken);
					response.resume(json.toString());
				}

			}

			json.put("status", "error");
			json.put("code", Response.Status.NOT_FOUND.getStatusCode());
			json.put("message", "Invalid Crendentails");
			response.resume(json.toString());
		} catch (Exception ex) {

			throw new RuntimeException(ex.getMessage());
		}

	}

	@POST
	@Path("expireToken")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ManagedAsync
	public void expireToken(@Context HttpHeaders httpHeaders, final @Suspended AsyncResponse response) {
		JSONObject json = new JSONObject();
		String username = httpHeaders.getHeaderString("username");
		AuthenticatorService demoAuthenticator = AuthenticatorService.getInstance();
		try {
			if ((username != null) && (username != "")) {
				boolean removeStatus = demoAuthenticator.expireToken(username);
				if (removeStatus) {
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Token Expired");
					response.resume(json.toString());
				} else {
					json.put("status", "error");
					json.put("code", Response.Status.NOT_FOUND.getStatusCode());
					json.put("message", "Invalid Crendentails");
				}
			} else {
				json.put("status", "error");
				json.put("code", Response.Status.NOT_FOUND.getStatusCode());
				json.put("message", "Invalid Crendentails");

			}
			response.resume(json.toString());

		} catch (Exception ex) {

			throw new RuntimeException(ex.getMessage());
		}

	}
}