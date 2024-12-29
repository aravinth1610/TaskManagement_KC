package com.authkey.keyClockUtils;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityUntil {

	Keycloak keycloak;

	@Value("${oauth2.auth-base-url}")
	private String servletURL;

	@Value("${oauth2.grant-type}")
	private String type;

	@Value("${oauth2.admin.username}")
	private String adminUserName;

	@Value("${oauth2.admin.password}")
	private String adminPassword;

	public Keycloak getKeyCloakInstance(String realm, String adminClientID) {
		System.out.println(realm);
		if (keycloak == null) {
			keycloak = KeycloakBuilder.builder().serverUrl(servletURL).realm(realm).clientId(adminClientID)
					.grantType(type).username(adminUserName).password(adminPassword).build();
		}
		return keycloak;
	}

}
