package com.mitchell.cosplaycity.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserLogin {
	
	//Class attributes
	
	@NotEmpty(message = "")
	@Email(message = "Please enter a valid email!")
	private String email;
	
	@NotEmpty(message = "")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;
	
	//Generators
	
	public UserLogin() {}

	public UserLogin(
			String email,
			String password) {
		this.email = email;
		this.password = password;
	}
	
	//Getters and Setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
