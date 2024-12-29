package com.authkey.keyClockUtils;

import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.shaded.gson.Gson;

@Component
public class TokenUtil {

	// A method to decode the token once and pass it around for further use
	public static Map<String, Object> getDecodedpayload(String token) {
		return decodeToken(token);
	}

	// Extract 'realm' from the token Claims
	public static String extractRealm(Map<String, Object> payload) {
		String issuer = (String) payload.get("iss");
		return issuer.substring(issuer.lastIndexOf("/") + 1);
	}

	// Extract 'client_id' from the token Claims
	public static String extractClientId(Map<String, Object> payload) {
		return (String) payload.get("client_id");
	}

	// Extract 'sub' from the token Claims
	public static String extractSub(Map<String, Object> payload) {
		return (String) payload.get("sub");
	}

	// Extract 'allowed-origins' from the token Claims
	public static Object extractAllowedOrigins(Map<String, Object> payload) {
		return payload.get("allowed-origins");
	}

	// Extract 'clientHost' from the token Claims
	public static String extractClientHost(Map<String, Object> payload) {
		return (String) payload.get("clientHost");
	}

	// Extract 'email_verified' from the token Claims
	public static Boolean extractEmailVerified(Map<String, Object> payload) {
		return (Boolean) payload.get("email_verified");
	}

	// Extract 'clientAddress' from the token Claims
	public static String extractClientAddress(Map<String, Object> payload) {
		return (String) payload.get("clientAddress");
	}

	// Extract 'realm_access' from the token Claims
	public static Map<String, Object> extractRealmAccess(Map<String, Object> payload) {
		return (Map<String, Object>) payload.get("realm_access");
	}

	// Extract 'resource_access' from the token Claims
	public static Map<String, Object> extractResourceAccess(Map<String, Object> payload) {
		return (Map<String, Object>) payload.get("resource_access");
	}
	
	public static boolean validateToken(Map<String, Object> user) {
		if (user == null)
			throw new RuntimeException();
		
		return Boolean.valueOf(user.get("active").toString());
	}


	// Decode the token and return the Claims
	@SuppressWarnings("unchecked")
	private static Map<String, Object> decodeToken(String token) {
		try {
			String[] parts = token.split("\\.");
			if (parts.length != 3) {
				throw new IllegalArgumentException("Invalid JWT token format.");
			}

			// Decode the payload (second part of the token)
			String payload = new String(Base64.getDecoder().decode(parts[1]));
			return new Gson().fromJson(payload, Map.class);

		} catch (Exception e) {
			throw new RuntimeException("Failed to decode JWT token.", e);
		}
	}
}
