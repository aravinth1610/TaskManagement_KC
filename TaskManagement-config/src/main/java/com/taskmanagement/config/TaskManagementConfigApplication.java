package com.taskmanagement.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TaskManagementConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementConfigApplication.class, args);
	}

}
