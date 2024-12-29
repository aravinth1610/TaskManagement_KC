package com.authkey.keyClockConfig;

import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;

public class CreatePolicyEnforceFilter implements ConfigurationResolver {

	@Override
	public PolicyEnforcerConfig resolve(HttpRequest request) {
			try {
				return JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforce.json"), PolicyEnforcerConfig.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("PolicyEnforcerConfig ",e);
			}
	}
}
