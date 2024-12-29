package com.clockData.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.clockData.interceptor.Interceptor;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final Interceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/**");
	}
}
