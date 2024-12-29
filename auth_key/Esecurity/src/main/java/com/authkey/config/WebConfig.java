package com.authkey.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.authkey.interceptor.Interceptor;

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
