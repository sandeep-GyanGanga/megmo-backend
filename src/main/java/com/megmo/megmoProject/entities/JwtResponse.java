package com.megmo.megmoProject.entities;

// iss class ka use ham jwt token ko return karne ke liye use karenge. jab username aur password valid hai tab 
public class JwtResponse {
	
	String token;

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
