package com.hv.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.hv.common.view.CustomJxlsReportView;
import com.hv.common.viewResolver.CustomJxlsReportViewResolver;

/**
 * 
 * @author harshul.varshney
 *
 */
@Configuration
@EnableWebMvc
public class ServletContext extends WebMvcConfigurerAdapter {

	@Bean(name="viewResolver")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
	
	@Bean(name="jxlsViewResolver")
	public ViewResolver jxlsViewResolver() {
		CustomJxlsReportViewResolver viewResolver = new CustomJxlsReportViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setSuffix(".xlsx");
		viewResolver.setViewNames("*jxls_*");
		viewResolver.setViewClass(CustomJxlsReportView.class);
		
		return viewResolver;
	}
	
	
}
