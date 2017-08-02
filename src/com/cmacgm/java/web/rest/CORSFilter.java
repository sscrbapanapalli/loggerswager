package com.cmacgm.java.web.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	private static final Logger log = Logger.getAnonymousLogger();
	private Properties configProp = new Properties();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException, java.lang.IllegalStateException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		// can be moved to properties

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			throw new RuntimeException("config.properties not loaded properly");
		}

		String originHeader = null;
		try {
			originHeader = request.getHeader("host");
			String[] allowDomain = { configProp.getProperty("ip1"), configProp.getProperty("ip2"),
					configProp.getProperty("ip3"), configProp.getProperty("ip4"), configProp.getProperty("ip5") };
			if (allowDomain.length > 0 && originHeader != null && !originHeader.isEmpty()) {

				for (String domain : allowDomain) {

					if (originHeader.endsWith(domain)) {
						request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
						response.setHeader("Access-Control-Allow-Origin", originHeader);
						response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
						response.setHeader("Access-Control-Max-Age", "0");
						response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
						response.setHeader("Pragma", "no-cache");
						response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
						break;
					}
				}
				filterChain.doFilter(servletRequest, servletResponse);
			}
		} catch (Exception ex) {

			throw new RuntimeException("UnKnown host:" + originHeader + "  Permission Denied");
		}

	}

	@Override
	public void destroy() {
	}
}
