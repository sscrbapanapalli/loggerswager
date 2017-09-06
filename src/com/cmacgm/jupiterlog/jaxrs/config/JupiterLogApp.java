package com.cmacgm.jupiterlog.jaxrs.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Defines the components of the application.
 * 
 * @author Basker Ammu
 *
 */
@ApplicationPath("/api")
public class JupiterLogApp extends ResourceConfig {

	public JupiterLogApp() {
		packages("com.cmacgm.jupiterlog.jaxrs.*");
		packages("com.fasterxml.jackson.jaxrs.json");
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		//register(com.cmacgm.jupiterlog.jaxrs.config.CORSFilter.class);
		register(com.cmacgm.jupiterlog.jaxrs.log.LogResource.class);

		io.swagger.jaxrs.config.BeanConfig beanConfig = new io.swagger.jaxrs.config.BeanConfig();
		beanConfig.setSchemes(new String[] { "http","https" });
		 InetAddress ip;
	     String hostname;
	        try {
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostAddress();
	            beanConfig.setHost(hostname+":8080");

	        } catch (UnknownHostException e) {
	        	 beanConfig.setHost("locahost:8080");	           
	        }
		
		beanConfig.setBasePath("/JupiterLog/api");
		beanConfig.setResourcePackage("com.cmacgm.jupiterlog.jaxrs");
		beanConfig.setScan(true);
		beanConfig.setPrettyPrint(true);

		io.swagger.models.Info info = new io.swagger.models.Info();
		io.swagger.models.Contact contact = new io.swagger.models.Contact();
		contact.setEmail("ssc.bammu@cmacgm.com");
		contact.setName("Basker Ammu");
		contact.setUrl("http://www.cmacgm.com");
		info.setContact(contact);
		info.setDescription("Logger Framework");
		info.setTitle("Jupiter Log Service");
		info.setVersion("1.0");
		beanConfig.setInfo(info);
	}
}
