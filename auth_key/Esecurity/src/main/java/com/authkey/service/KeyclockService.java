package com.authkey.service;

import java.util.List;

import com.authkey.payload.response.User;

public interface KeyclockService {

	List<User> getMethodName();
	void init(String realm, String clientId);
}
