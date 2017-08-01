package com.cmacgm.java.web.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		Response.StatusType type = getStatusType(ex);

		Error error = new Error(type.getStatusCode(), type.getReasonPhrase(), ex.getLocalizedMessage());

		return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

	private Response.StatusType getStatusType(Throwable ex) {
		if (ex instanceof WebApplicationException) {
			return ((WebApplicationException) ex).getResponse().getStatusInfo();
		} else {
			return Response.Status.INTERNAL_SERVER_ERROR;
		}
	}
}
