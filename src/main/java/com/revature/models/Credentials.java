package com.revature.models;

public class Credentials {
	
	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}

}
