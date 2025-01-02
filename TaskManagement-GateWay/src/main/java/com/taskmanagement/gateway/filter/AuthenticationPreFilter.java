package com.taskmanagement.gateway.filter;

import static com.taskmanagement.gateway.constant.GatewayConstant.TOKEN_PREFIX;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.taskmanagement.gateway.clientConfig.WebClientConfig;


@Component
public class AuthenticationPreFilter extends AbstractGatewayFilterFactory<AuthenticationPreFilter.Config> {

	public static class Config {}	
	
	private final WebClientConfig webClientConfig;
	
	
	private List<String> openApiEndpoints = Arrays.asList("/eureka","/protocol/openid-connect/**");
	
	public AuthenticationPreFilter(WebClientConfig webClientConfig) {
		super();
		this.webClientConfig = webClientConfig;
	}

	 @Override
	 public GatewayFilter apply(Config config) {
	        return (exchange, chain) -> {
	            ServerHttpRequest request = exchange.getRequest();

//	            log.info("**************************************************************************");
//	            log.info("URL is - " + request.getURI().getPath());
	            
	            if(isSecured.test(request)) {
		            String accessToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		      //      accessToken = accessToken.substring(TOKEN_PREFIX.length()).trim();	            	
	                return  webClientConfig.tokenValidationAPIExchange(headerHandler(accessToken), exchange,chain);
	            }
	            return chain.filter(exchange);
	        };
	    }
	 
		private Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
				.map(uri -> uri.replace("/**", "")).noneMatch(uri -> request.getURI().getPath().contains(uri));	 
		
		private HttpHeaders headerHandler(String token) {
	        HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
			httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
			return httpHeaders;
		}

	 
}
