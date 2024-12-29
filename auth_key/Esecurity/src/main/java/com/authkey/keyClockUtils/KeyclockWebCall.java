package com.authkey.keyClockUtils;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.authkey.webServerCall.WebServerCall;

@Component
@SuppressWarnings("unchecked")
public class KeyclockWebCall {

	private final WebServerCall webServerCall;

	@Value("${oauth2.auth-base-url}")
	private String serverBaseUri;

	public KeyclockWebCall(WebServerCall webServerCall) {
		super();
		this.webServerCall = webServerCall;
	}

	public  Map<String, Object> keyclockTokenWebCall(String realm, MultiValueMap<String, String> urlEncodedData, HttpHeaders header) {
		return webServerCall.keyclockAPIExchange(uriTokenConstructor(realm, "token"), urlEncodedData, Map.class, header, "POST");
	}
	

	public  Map<String, Object> keyclockTokenIntrospectWebCall(String realm, MultiValueMap<String, String> urlEncodedData, HttpHeaders header) {
		return 	webServerCall.keyclockAPIExchange(uriTokenConstructor(realm, "token/introspect"), urlEncodedData, Map.class, header, "POST");
	}

	public Set<Map<String, Object>> keyclockAdminClientsWebCall(String realm, HttpHeaders header){
		return webServerCall.keyclockAPIExchange(uriAdminConstructor(realm, "clients"), null, Set.class, header,"GET");
	}
	
	public Map<String, Object> keyclockAdminClientSecret(String realm, String clientId, HttpHeaders header){
		return webServerCall.keyclockAPIExchange(uriAdminConstructor(realm, "clients/".concat(clientId).concat("/client-secret")), null, Map.class, header, "GET");
	}
	
	private String uriTokenConstructor(String realm, String endPoint) {
		return String.format("%s/realms/%s/protocol/openid-connect/%s", this.serverBaseUri, realm, endPoint).trim();
	}
	
	private String uriAdminConstructor(String realm, String endPoint) {
		return String.format("%s/admin/realms/%s/%s", this.serverBaseUri, realm, endPoint).trim();
	}
}
