package com.peoplesprocessing.hotfoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FilterRegistration extends WebMvcConfigurerAdapter {

	@Autowired
	KeycloakServiceInterceptor keycloakServiceInterceptor;
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new CorsFilter());
	    registration.addUrlPatterns("/*");
//	    registration.addInitParameter("paramName", "paramValue");
	    registration.setName("cors_filter");
	    registration.setOrder(1);
	    return registration;
		
	}
	
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(keycloakServiceInterceptor);
	   }
}
