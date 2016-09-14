package com.hv.common.viewResolver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class CustomJxlsReportViewResolver extends UrlBasedViewResolver implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		setPrefix("file:"+"E:/Boot/CommonWeb/common-web/src/main/resources/");
	}
	
	

}
