package com.taskmanagement.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.taskmanagement.gateway.*", "com.unicore.*"})
public class TaskManagementGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementGateWayApplication.class, args);
	}

}
