package com.lhz.estest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author lhzlhz
 * @create 2021/5/22
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(new UserRoleAuthorizationInterceptor()).addPathPatterns("/pics/**");
		super.addInterceptors(registry);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/","classpath:/templates/");
		super.addResourceHandlers(registry);
	}
}

