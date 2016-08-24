package com.hv.userMgmt.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 
 * @author harshul.varshney
 * This initializer class registers the springSecurityFilter with application war.
 * 
 * below is the code in web.xml that it replaces:
 * <filter>
    	<filter-name>springSecurityFilterChain</filter-name>
    	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
   </filter>
   <filter-mapping>
	    <filter-name>springSecurityFilterChain</filter-name>
	    <url-pattern>/*</url-pattern>
   </filter-mapping>
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
