//package com.taskmanagement.gateway.filter;
//
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.server.ServerWebExchange;
//
//import reactor.core.publisher.Mono;
//
//@Component
//@Order(-2) // Ensure it runs before the default error handler
//public class ErrorWebHandler implements ErrorWebExceptionHandler {
//
//	@Override
//	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//		// need to keep logger
//
//		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
//		String dateTime = formatter.format(new Date());
//
//		HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR; // Default to 500
//		String errorMessage =String.format(
//				"{\"status\": \"Failure\",\"message\": \"Resource not found.\",\"data\": [{\"errorCode\": \"ABI002\",\"reason\": \"Not Found\",\"schemaPath\": \"%s\",\"timestamp\": \"%s\"}]}",
//				""+exchange.getRequest().getURI().getPath()+"", ""+dateTime+"");
//
//		if (ex instanceof ResponseStatusException) {
//			ex.printStackTrace();
//			
//			status = ((ResponseStatusException) ex).getStatusCode();
//
//			if (status == HttpStatus.NOT_FOUND) {
//				errorMessage = String.format(
//						"{\"status\": \"Failure\",\"message\": \"Resource not found.\",\"data\": [{\"errorCode\": \"ABI002\",\"reason\": \"Not Found\",\"schemaPath\": \"%s\",\"timestamp\": \"%s\"}]}",
//						""+exchange.getRequest().getURI().getPath()+"", ""+dateTime+"");
//
//			} else if (status == HttpStatus.SERVICE_UNAVAILABLE) {
//				errorMessage = String.format(
//						"{\"status\": \"Failure\",\"message\": \"Services will be Up or Check the Base Path.\",\"data\": [{\"errorCode\": \"ABI002\",\"reason\": \"SERVICE_UNAVAILABLE\",\"schemaPath\": \"%s\",\"timestamp\": \"%s\"}]}",
//						""+exchange.getRequest().getURI().getPath()+"", ""+dateTime+"");
//			}
//		}
//
//		exchange.getResponse().setStatusCode(status);
//		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//		return exchange.getResponse().writeWith(
//				Mono.just(exchange.getResponse().bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8))));
//	}
//
//}
