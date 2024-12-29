package com.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskManagementRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementRegistryApplication.class, args);
	}

}
