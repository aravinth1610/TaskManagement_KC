package com.authkey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.authkey.filter.ConditionalPolicyFilter;
import com.authkey.filter.CorsConfigurationFilter;
import com.authkey.handlerException.CustomAccessDeniedHandler;
import com.authkey.handlerException.CustomAuthenticationEntryPoint;

@Configuration
public class WebSecurityConfigure {


	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.cors(corsCustomize -> corsCustomize.configurationSource(new CorsConfigurationFilter()))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			    .addFilterBefore(new ConditionalPolicyFilter(), BearerTokenAuthenticationFilter.class)

				.exceptionHandling(exception -> exception.accessDeniedHandler(new CustomAccessDeniedHandler())
						.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
				.formLogin(formLogin -> formLogin.disable()).httpBasic(httpBasic -> httpBasic.disable());

		return http.build();

	}
}
