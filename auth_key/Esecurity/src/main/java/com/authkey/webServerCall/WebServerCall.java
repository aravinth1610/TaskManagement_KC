package com.authkey.webServerCall;

import java.util.Map;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.uniCore.customeExceptions.ForbiddenException;

import com.unicore.customeExceptions.UnauthorizedException;

import reactor.core.publisher.Mono;

@Component
public class WebServerCall {

	@LoadBalanced
	WebClient.Builder webClientBuild() {
		return WebClient.builder();
	}

	public <T, R> R keyclockAPIExchange(String path, MultiValueMap<String, String> urlEncodedData, Class<R> responseType, HttpHeaders httpHeaders,String methodType) {
		HttpMethod method = MethodType(methodType);
		methodType = method.toString();
		
		WebClient.RequestBodySpec requestSpec = webClientBuild().build().method(method).uri(path)
				.headers(headers -> headers.addAll(httpHeaders));
		WebClient.ResponseSpec responseSpec;
		
		if (methodType.equals("POST") || methodType.equals("PUT") || methodType.equals("PATCH")) {
						
			if(httpHeaders.get("Content-Type").contains("application/x-www-form-urlencoded")) {
				responseSpec = requestSpec.body(BodyInserters.fromFormData(urlEncodedData)).retrieve();
			}else {
				responseSpec = requestSpec.bodyValue(urlEncodedData).retrieve();	
			}			
		} else {
			responseSpec = requestSpec.retrieve();
		}
			
		return responseSpec.onStatus(status -> status.isError(), this::handleError).bodyToMono(responseType).block();
	}

	private Mono<Throwable> handleError(ClientResponse response) {
	 return  response.bodyToMono(Map.class).flatMap(errorResponse -> {
		  
		  String error = (String) errorResponse.get("error");
          String errorDescription = (String) errorResponse.get("error_description");
   
          if (response.statusCode() == HttpStatus.UNAUTHORIZED) {
              return Mono.error(new UnauthorizedException("Unauthorized: " + errorDescription));
          }else if (response.statusCode() == HttpStatus.FORBIDDEN) {
              return Mono.error(new ForbiddenException("Forbidden: " + errorDescription));
          }else if (response.statusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
              return Mono.error(new RuntimeException("Internal Server Error: " + errorDescription));
          }else {
          return Mono.error(new RuntimeException(error + " - " + errorDescription));
          }
      });
	}

	private HttpMethod MethodType(String type) {
		switch (type) {
		case "POST":
			return HttpMethod.POST;
		case "GET":
			return HttpMethod.GET;
		case "PUT":
			return HttpMethod.PUT;
		case "PATCH":
			return HttpMethod.PATCH;
		case "DELETE":
			return HttpMethod.DELETE;
		default:
			throw new IllegalArgumentException("Unexpected Method Type: " + type);
		}
	}

}
