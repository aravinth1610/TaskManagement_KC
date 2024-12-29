package com.authkey.filter;

import static com.authkey.constant.SecurityConstant.POLICYENFORCE_DISABLE;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.util.List;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authkey.keyClockConfig.CreatePolicyEnforceFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConditionalPolicyFilter extends OncePerRequestFilter {

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        InputStream inputStream = getClass().getResourceAsStream("/policy-enforce.json");
        if (inputStream == null) {
            throw new RuntimeException("policy-enforce.json not found");
        }

        PolicyEnforcerConfig policyEnforcerConfig = JsonSerialization.readValue(inputStream, PolicyEnforcerConfig.class);

        // Check if the request path matches any path with "enforcement-mode: DISABLED" but exclude certain paths
        List<PolicyEnforcerConfig.PathConfig> paths = policyEnforcerConfig.getPaths();
       
    	String requestPath = request.getRequestURI();

        
    	boolean shouldEnforcePolicy = paths.stream().anyMatch(pathConfig ->
    	POLICYENFORCE_DISABLE.equalsIgnoreCase(pathConfig.getEnforcementMode().toString())
    		        && requestPath.contains(pathConfig.getPath().replace("/*", "")));
							
			if (shouldEnforcePolicy) {
				// Directly proceed without applying Keycloak enforcement
				filterChain.doFilter(request, response);
				return;
			}
			
		
		// If not PermitAll, invoke Keycloak policy enforcement
		try {
			ServletPolicyEnforcerFilter keycloakFilter = new ServletPolicyEnforcerFilter(new CreatePolicyEnforceFilter());
			keycloakFilter.doFilter(request, response, filterChain);
		} catch (Exception e) {
			throw new AccessDeniedException("Policy Enforcement Failed");
		}
	}
}
