package com.hv.userMgmt.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author harshul.varshney
 * Base class for implementations, that register a DispatcherServlet.
 */
public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// This is used to register dispatcher servlet with ServletContext, we do not need to do registration ourself
		// registration is already happening in super class implementation.
		super.onStartup(servletContext);
	}
	
	/**
	 * Configuration classed for root app context can be added here.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppConfiguration.class};
	}

	/**
	 * Configuration class to add dispatcher servlet-context can be added here.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return null;
	}

}
