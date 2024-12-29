package com.clockData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.task.*", "com.unicore.*"})
public class ClockData {

	public static void main(String[] args) {
		SpringApplication.run(ClockData.class, args);
	}

}
