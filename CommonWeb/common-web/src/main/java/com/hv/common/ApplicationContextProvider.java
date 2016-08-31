package com.hv.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware{
	  
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	private static ApplicationContext applicationContext;
	  
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		setAppContext(applicationContext);
	}
	
	public static void setAppContext(ApplicationContext applicationContext)
	{
		 ApplicationContextProvider.applicationContext = applicationContext;
	}
}
