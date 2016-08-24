package com.hv.userMgmt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.hv.userMgmt.converter.RoleToUserProfileConverter;

/**
 * @author harshul.varshney
 * This configuration class replaces servlet-context.xml
 *
 */
@Configuration								//tells framework that it is a configuration class
@EnableWebMvc								//replacement of <mvc:annotation-driven/>
public class AppConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private RoleToUserProfileConverter roleToUserProfileConverter;

	/*
     * Configure ResourceHandlers to serve static resources Javascript etc...
     * this replaces <mvc:resources mapping="/resources/**" location="/resources/" /> present in servlet.context.xml
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Bean(name="viewResolver")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
    
    /*
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }
}
