package com.cmacgm.java.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

@javax.ws.rs.ApplicationPath("rest")
public class MyRESTServices extends ResourceConfig {
	public MyRESTServices() {
		packages(new String[] { "com.fasterxml.jackson.jaxrs.json" });
		packages(new String[] { "com.cmacgm.java.bean", "com.cmacgm.java.web.rest" });
		register(com.cmacgm.java.web.rest.CORSFilter.class);

	}
}