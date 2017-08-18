package com.cmacgm.jupiterlog.jaxrs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Defines the each request based on configured ip only allow to access using
 * config.properties file.
 * 
 * @author Basker Ammu
 */
public class CORSFilter implements javax.servlet.Filter {

	private Properties configProp = new Properties();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException, java.lang.IllegalStateException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		/*InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			throw new RuntimeException("config.properties not loaded properly");
		}*/

		//String originHeader = null;
		
			/*originHeader = request.getRemoteAddr();
	
			if(!originHeader.isEmpty() && originHeader!=null){
			String[] allowDomain = { configProp.getProperty("ip1"), configProp.getProperty("ip2"),
					configProp.getProperty("ip3"), configProp.getProperty("ip4"), configProp.getProperty("ip5") };
			if (allowDomain.length > 0 && originHeader != null && !originHeader.isEmpty()) {

				for (String domain : allowDomain) {

					if (originHeader.equals(domain)) {*/
		try {
						//request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
						response.setHeader("Access-Control-Allow-Origin","*");
						response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
						response.setHeader("Access-Control-Max-Age", "0");
						response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
						response.setHeader("Pragma", "no-cache");
						response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
					/*	break;
					}
				}*/
				filterChain.doFilter(servletRequest, servletResponse);
			
			
			/*}else
				throw new RuntimeException("UnKnown host:" + originHeader + "  Permission Denied to Access Jupiter Log");*/
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
			//throw new RuntimeException("UnKnown host:" + originHeader + "  Permission Denied to Access Jupiter Log");
		}

	}

	@Override
	public void destroy() {
	}
}
