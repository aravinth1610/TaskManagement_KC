package com.taskmanagement.gateway.clientConfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

	@Bean
	@LoadBalanced
	WebClient.Builder webClientBuild() {
		return WebClient.builder();
	}

	public Mono<Void> tokenValidationAPIExchange(HttpHeaders headers, ServerWebExchange exchange,GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		return webClientBuild().build().post().uri("lb://Authkey/auth-key/protocol/openid-connect/token/introspect")
				.headers(header -> header.addAll(headers)).retrieve().bodyToMono(String.class).map(response -> {
					System.out.println("response======="+response);
			//		String user = request.getHeaders().getFirst("X-User-ID");
			//		exchange.getRequest().mutate().header("X-User-ID", user).build();
					return exchange;
				})
				.flatMap(chain::filter).onErrorResume(error -> {
					HttpStatusCode errorCode; String errorMsg;
					
					if (error instanceof WebClientResponseException) {
						WebClientResponseException webClientException = (WebClientResponseException) error;
						errorCode = webClientException.getStatusCode();
						errorMsg = webClientException.getResponseBodyAsString();
					} else {
						errorCode = HttpStatus.BAD_GATEWAY;
						errorMsg = HttpStatus.BAD_GATEWAY.getReasonPhrase();
					}
					return onError(exchange, String.valueOf(errorCode.value()), errorMsg, errorCode);
				});
	}

	private Mono<Void> onError(ServerWebExchange exchange, String errCode, String err, HttpStatusCode httpStatus) {
		DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		try {
			response.getHeaders().add("Content-Type", "application/json");
			byte[] bytes = err.getBytes();
			return response.writeWith(Mono.just(bytes).map(t -> dataBufferFactory.wrap(t)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.setComplete();
	}
}
