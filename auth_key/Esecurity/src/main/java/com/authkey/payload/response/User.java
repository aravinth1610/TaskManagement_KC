package com.authkey.payload.response;

import lombok.Data;

@Data
public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
}
