package com.authkey.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.authkey.service.TokenService;
import com.unicore.customeResponse.ResponseEntityWrapper;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/protocol/openid-connect")
@AllArgsConstructor
public class TokenController {
	
	private final TokenService tokenServices;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value ="/realms/{realm}/token",consumes = "application/x-www-form-urlencoded;charset=UTF-8", produces = "application/json")
	private final ResponseEntityWrapper<?>  token(@PathVariable(name= "realm") String realm, String client_id, String client_secret, String grant_type, String username, String password, String refresh_token) {
		Map<String, Object> token = tokenServices.token(realm, client_id, client_secret, grant_type, username, password, refresh_token);
		return new ResponseEntityWrapper<>("Success", token, "Operation completed successfully.");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/token/introspect")
	private final ResponseEntityWrapper<?>  introspect(@RequestHeader(name= "Authorization") String token) {
		Map<String, Object> user = tokenServices.introspect(token);
		return new ResponseEntityWrapper<>("Success", user, "Operation completed successfully.");
	}	
}