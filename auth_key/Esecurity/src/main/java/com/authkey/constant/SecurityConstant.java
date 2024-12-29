package com.authkey.constant;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;


public interface SecurityConstant {
	
	final String POLICYENFORCE_DISABLE = "DISABLED";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public static final String OPTIONS_HTTP_METHOD = "options";
	public static final String AUTHORITIES = "authorities";
	public static final String CLAIMNAME = "username";
	public static final String TOKEN_UNVERIFIABLE = "Token cannot be verified.";
	public static final String USER_NOT_CREATED = "User is not created.";
	public static final String SAME_EMAIL_EXISTS = "You cannot update with your existing email.";
	public static final String INVALID_PASSWORD = "Invalid Password.";
	public static final String INVALID_OPERATOR = "You cannot perform this operation.";
	public static final String TOKEN_EXPIRED = "User Token is Expired.";
	public static final String EMAIL_EXISTS = "User exists with this email address.";
	public static final String SAME_EMAIL = "You cannot update with your existing email.";
	public static final String USER_NOT_FOUND = "No user found.";
	public static final String EMAIL_NOT_FOUND = "Email address does not exist.";
	public static final String ACCESS_DENIED = "You don't have permission to access this resource.";
	public static final String CORS_ALLOW_Methods[] = { HttpMethod.GET.name(), HttpMethod.POST.name(),
			HttpMethod.PUT.name(), HttpMethod.DELETE.name() };
	public static final String CORS_ALLOW_HEADERS[] = { "Origins", "Accept-Control-Allow-Origin", "Content-Type",
			"Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
			"Access-Control-Request-Headers", "Cookie" };
	public static final String CORS_ALLOW_EXPOSEDHEADERS[] = { "Origins", "Accept-Control-Allow-Origin", "Content-Type",
			"Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
			"Access-Control-Request-Headers", "Cookie" };
	public static final String[] PUBLIC_URLS = { "/signinrequest/authentication/auth/login",
			"/signinrequest/authentication/auth/isexists-email", "/signinrequest/authentication/auth/signup",
			"/signinrequest/authentication/auth/refress-token", "/signinrequest/authentication/logout",
			"/signinrequest/other-app/gt", "/signinrequest/other-app/signup", "/signinrequest/other-app/login",
			"/images/**", "/uploads/**", "/eureka/**" };

	public static final List<String> SHOULDNOTFILTERVALIDATOR = Arrays.asList(PUBLIC_URLS);

}
