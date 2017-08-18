package com.cmacgm.jupiterlog.jaxrs.log;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ManagedAsync;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Log resource
 * 
 * @author Basker Ammu
 *
 */
@Path("log")
@Api(value = "Jupiter Log", description = "Jupiter Log Service")
public class LogResource {

	//public static final String AUTH_TOKEN = "auth_token";
	//static final Logger emaildeskLogger = Logger.getLogger("emaildesk");
	static final Logger jupiterLogger = Logger.getLogger("jupiter");

/*	@POST
	@Path("generateToken")
	@ApiOperation(value = "generateToken method", notes = "This method should be invoked asynchronously  after the successful user authentication in Jupiter application")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "An internal error occurred"),
			@ApiResponse(code = 404, message = "username not found from Header Details") })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ManagedAsync
	public void generateToken(final @Suspended AsyncResponse response,
			@ApiParam(name = "username", required = true) @HeaderParam("username") String username) {
		AuthenticatorService demoAuthenticator = AuthenticatorService.getInstance();
		JSONObject json = new JSONObject();
		try {
			if (username != null) {
				String authToken = demoAuthenticator.getToken(username);
				if ((authToken != "") && (authToken != null)) {
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Auth Token is Generated");
					json.put("auth_token", authToken);
					// Thread.sleep(5000);
					response.resume(json.toString());
				}

			}

			json.put("status", "error");
			json.put("code", Response.Status.NOT_FOUND.getStatusCode());
			json.put("message", "Invalid credentials!");
			response.resume(json.toString());
		} catch (Exception ex) {

			throw new RuntimeException(ex.getMessage());
		}

	}

	@POST
	@Path("expireToken")
	@ApiOperation(value = "expireToken method", notes = "This method will clear the auth token that has been generated for the active user. This should be invoked asynchronously during session log off event")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "An internal error occurred"),
			@ApiResponse(code = 404, message = "username not found from header Details") })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ManagedAsync
	public void removeToken(@ApiParam(name = "username", required = true) @HeaderParam("username") String username,
			final @Suspended AsyncResponse response) {
		JSONObject json = new JSONObject();
		AuthenticatorService demoAuthenticator = AuthenticatorService.getInstance();
		try {
			if ((username != null) && (username != "")) {
				boolean removeStatus = demoAuthenticator.expireToken(username);
				if (removeStatus) {
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Auth Token Expired");
					response.resume(json.toString());
				}
			}
			json.put("status", "error");
			json.put("code", Response.Status.NOT_FOUND.getStatusCode());
			json.put("message", "Invalid credentials!");

			response.resume(json.toString());

		} catch (Exception ex) {

			throw new RuntimeException(ex.getMessage());
		}

	}*/

	/*@POST
	@Path("jupiterLog")
	@ApiOperation(value = "jupiterLog method", notes = "This auth_token value and username should be used by the system for the logged in user to asynchronously post Log details to the Common Logging Framework")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "An internal error occurred"),
			@ApiResponse(code = 404, message = "username or auth_token not found from Header Details") })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ManagedAsync
	public void jupiterLog(@ApiParam(name = "username", required = true) @HeaderParam("username") String username,
			@ApiParam(name = "auth_token", required = true) @HeaderParam("auth_token") String authToken,
			LoggerMessage loggerMessage, final @Suspended AsyncResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		AuthenticatorService demoAuthenticator = AuthenticatorService.getInstance();

		JSONObject json = new JSONObject();

		try {
			if ((authToken != null) && (username != null)) {
				boolean authTokenStatus = demoAuthenticator.login(authToken, username);
				if ((loggerMessage != null) && (authTokenStatus)) {
					saveLog(loggerMessage, response);

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
		json.put("message", "Invalid credentials!");
		response.resume(json.toString());
	}*/
	@POST
	@Path("jupiterLog")
	@ApiOperation(value = "jupiterLog method", notes = "This auth_token value and username should be used by the system for the logged in user to asynchronously post Log details to the Common Logging Framework")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "An internal error occurred"),
			@ApiResponse(code = 404, message = "username or auth_token not found from Header Details") })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ManagedAsync
	public void jupiterLog(LoggerMessage loggerMessage, @Suspended AsyncResponse response)
			throws JsonParseException, JsonMappingException, IOException {
			JSONObject json = new JSONObject();

		try {			
				if ((loggerMessage != null) ) {
					response=saveLog(loggerMessage, response);

				}			

		}
		catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		json.put("status", "error");
		json.put("code", Response.Status.NOT_FOUND.getStatusCode());
		json.put("message", "Invalid credentials!");
		response.resume(json.toString());
	}
	public AsyncResponse saveLog(LoggerMessage logVar, AsyncResponse response) {
		JSONObject json = new JSONObject();
		try {
			if ((logVar != null)) {

				Gson jsonObject = new Gson();
			  if ((logVar.getApplicationName()!=null && logVar.getApplicationName()!="" && !logVar.getApplicationName().isEmpty() ) && (jupiterLogger.isInfoEnabled())) {
					jupiterLogger.info(logVar.toString());
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Log Inserted");
					response.resume(json.toString());
				}
			}
			json.put("status", "error");
			json.put("code", Response.Status.NOT_FOUND.getStatusCode());
			json.put("message", "Invalid credentials!");
			response.resume(json.toString());

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return response;
	}

	
	@GET
	@Path("pingUrl")
	@ApiOperation(value = "pingUrl method", notes = "This should be invoked asynchronously for ping url test")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "An internal error occurred"),
			@ApiResponse(code = 404, message = "pingUrl not working") })
	@Produces({ MediaType.APPLICATION_JSON})
	@ManagedAsync
	public void pingUrl(final @Suspended AsyncResponse response) {
		JSONObject json = new JSONObject();
			try {		
					json.put("status", "success");
					json.put("code", Response.Status.OK.getStatusCode());
					json.put("message", "Jupiter Application Running Successfully");
					response.resume(json.toString());		

		} catch (Exception ex) {
			json.put("status", "error");
			json.put("code", Response.Status.NOT_FOUND.getStatusCode());
			json.put("message", "Jupiter Application Not Running");
			response.resume(json.toString());
			throw new RuntimeException(ex.getMessage());
		}

	}

}