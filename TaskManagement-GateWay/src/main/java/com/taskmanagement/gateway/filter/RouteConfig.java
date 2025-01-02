package com.taskmanagement.gateway.filter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

	@Bean
	protected RouteLocator routes(RouteLocatorBuilder builder, AuthenticationPreFilter authFilter) {
		return builder.routes()
				.route("Authkey",
						r -> r.path("/auth-key/**")
						
//								.filters(f ->
								//f.rewritePath("/authentication-service(?<segment>/?.*)", "$\\{segment}")
//								f.filter(authFilter.apply(new AuthenticationPreFilter.Config())))
								.uri("lb://TaskManagement-GateWay")
								)
				.route("TimeSheet",
						r -> r.path("/task-management/**")
//								.filters(f -> 
								        //f.rewritePath("/user-service(?<segment>/?.*)", "$\\{segment}")
//										f.filter(authFilter.apply(new AuthenticationPreFilter.Config())))
								.uri("lb://TimeSheet"))
				.route("ClockData",
						r -> r.path("/clock-data/**")
//								.filters(f -> 
								        //f.rewritePath("/user-service(?<segment>/?.*)", "$\\{segment}")
//										f.filter(authFilter.apply(new AuthenticationPreFilter.Config())))
								.uri("lb://ClockData"))
				.route("TaskManagement-Registry",
						r -> r.path("/task/eureka/web")
						.filters(f ->  f.setPath("/"))
								.uri("http://localhost:9000"))
				.route("discovery-server-static",
						r -> r.path("/eureka/**")
								.uri("http://localhost:9000"))
//				  .route("fallbackRoute", 
//	                        r -> r.alwaysTrue()
//	                              .filters(f -> f.setResponseHeader("Content-Type", "application/json")
//	                                             .rewritePath("/.*", "/")
//	                                             .setStatus(HttpStatus.NOT_FOUND))
//	                              .uri("no://op")) // Use a non-routable URI
				.build();
	}
	
}
