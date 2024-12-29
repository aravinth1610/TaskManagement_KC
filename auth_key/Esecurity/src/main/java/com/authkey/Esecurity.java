package com.authkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.authkey.*", "com.unicore.*"})
public class Esecurity {

		public static void main(String[] args) {
			SpringApplication.run(Esecurity.class, args);
		}
}
