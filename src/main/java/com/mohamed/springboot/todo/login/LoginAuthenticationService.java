package com.mohamed.springboot.todo.login;

import org.springframework.stereotype.Service;

@Service
public class LoginAuthenticationService {

	public boolean authenticate(String name, String password) {
		
		boolean isValidUserName = name.equalsIgnoreCase("Mohamed");
		boolean isValidPassword = password.equalsIgnoreCase("123");
		
		return isValidUserName && isValidPassword;
	}
}
