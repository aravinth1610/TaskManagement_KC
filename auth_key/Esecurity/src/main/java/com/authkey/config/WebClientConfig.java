package com.authkey.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

	@Bean
	WebProperties.Resources resources() {
		return new WebProperties.Resources();
	}

}
