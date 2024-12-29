package com.authkey.keyClockConfig;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import jakarta.validation.constraints.NotNull;

public class KeyClockJwtAuthenticationConverter  implements Converter<Jwt, AbstractAuthenticationToken>{

	
	public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
		   try {
				return new JwtAuthenticationToken(jwt,
						Stream.concat(new JwtGrantedAuthoritiesConverter().convert(jwt).stream(), resourceRoles(jwt).stream())
								.collect(Collectors.toSet()));
	        } catch (Exception e) {
	            throw new RuntimeException("Keycloak token validation failed", e);
	        }
	}

	private Collection<? extends GrantedAuthority> resourceRoles(Jwt jwtToken) {
		Map<String, Object> realmAccess = jwtToken.getClaim("realm_access");
		System.out.println(realmAccess);
		return ((List<String>) realmAccess.get("roles")).stream().map(roleName -> "ROLE_" + roleName)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

	}

}