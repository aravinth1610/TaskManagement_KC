package com.authkey.service;

import java.util.Map;

public interface TokenService {

	Map<String, Object> token(String realm, String clientId, String clientSecret, String grantType, String userName, String password, String refreshToken);
	
	Map<String, Object> introspect(String token);
}
