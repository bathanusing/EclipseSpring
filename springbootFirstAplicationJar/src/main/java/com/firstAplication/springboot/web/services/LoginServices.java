package com.firstAplication.springboot.web.services;

import org.springframework.stereotype.Service;

//Spring bean
@Service
public class LoginServices {

	
		public boolean validatePassword(String userId, String password) {
			//se usaran usuario jean y password valido juan123
			return userId.equalsIgnoreCase("jean") && password.equalsIgnoreCase("juan123");
		}
}
