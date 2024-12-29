package com.authkey.keyClockConfig;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import jakarta.validation.constraints.NotNull;

public class KeyClockAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(@NotNull Jwt jwt) {
    	return resourceRoles(jwt).stream().collect(Collectors.toSet());
    }
    

	@SuppressWarnings("unchecked")
	private Collection<? extends GrantedAuthority> resourceRoles(Jwt jwtToken) {
		Map<String, Object> realmAccess = jwtToken.getClaim("realm_access");
		System.out.println(realmAccess);
		return ((List<String>) realmAccess.get("roles")).stream().map(roleName -> "ROLE_" + roleName)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

	}

}
