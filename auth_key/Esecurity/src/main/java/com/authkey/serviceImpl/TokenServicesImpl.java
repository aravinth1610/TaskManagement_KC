package com.authkey.serviceImpl;

import static com.authkey.constant.SecurityConstant.TOKEN_PREFIX;


import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.authkey.keyClockUtils.KeyclockWebCall;
import com.authkey.keyClockUtils.TokenUtil;
import com.authkey.service.TokenService;
import com.authkey.webServerCall.WebServerCall;
import com.unicore.customeExceptions.CommonCaseException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenServicesImpl implements TokenService{

	private final KeyclockWebCall keyclockWebCall;
	
	public Map<String, Object> token(String realm, String clientId, String clientSecret, String grantType, String userName, String password, String refreshToken) {
		MultiValueMap<String, String> urlEncodedData = new LinkedMultiValueMap<String, String>();

		urlEncodedData.add("client_id", clientId);
		urlEncodedData.add("client_secret", clientSecret);
		urlEncodedData.add("grant_type", grantType);

		if (userName != null && password != null) {
			urlEncodedData.add("username", userName);
			urlEncodedData.add("password", password);
		} else if (refreshToken != null) {
			urlEncodedData.add("refresh_token", refreshToken);
		}
		return keyclockWebCall.keyclockTokenWebCall(realm, urlEncodedData, urlencodedHeaderHandler());
	}
	
	public Map<String, Object> introspect(String token) {
		
		token = token.substring(TOKEN_PREFIX.length()).trim();
	  	
		Map<String, Object> claims = TokenUtil.getDecodedpayload(token);
		
		String realm = TokenUtil.extractRealm(claims);
		String clientId = TokenUtil.extractClientId(claims);
		
	    Set<Map<String, Object>> clients = keyclockWebCall.keyclockAdminClientsWebCall(realm, jsonHeaderHandler(token));
			
	    String clientUUID = getClientUUID(clients, clientId);
	    
	    String clientSecret = getClientSecret(keyclockWebCall.keyclockAdminClientSecret(realm, clientUUID, jsonHeaderHandler(token)));
		
		MultiValueMap<String, String> urlEncodedData = new LinkedMultiValueMap<String, String>();

		urlEncodedData.add("client_id", clientId);
		urlEncodedData.add("client_secret", clientSecret);
		urlEncodedData.add("token", token);
		
		Map<String, Object> user =  keyclockWebCall.keyclockTokenIntrospectWebCall(realm, urlEncodedData, urlencodedHeaderHandler());;
		
	    if(TokenUtil.validateToken(user)) {
	        return Map.of("user", TokenUtil.extractSub(user));
	    }else {
	    	throw new RuntimeException();
	    }			
	}
	
	private String getClientUUID(Set<Map<String, Object>> clients, String clientId) {
		for (Map<String, Object> client : clients) {
			if (clientId.equals(client.get("clientId"))) {
				return client.get("id").toString();
			}
		}
		throw new CommonCaseException("Unknow token");
	}
	
	private String getClientSecret(Map<String, Object> clientSecret) {
		if (clientSecret.get("type").toString().equalsIgnoreCase("secret")) {
			return clientSecret.get("value").toString();
		} else {
			throw new CommonCaseException("Unknow Type");
		}
	}

	private HttpHeaders urlencodedHeaderHandler() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
		return httpHeaders;
	}
	
	private HttpHeaders jsonHeaderHandler(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		httpHeaders.add(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX.concat(token));
		return httpHeaders;
	}
		
}