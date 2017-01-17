package com.ncu.bean;

public class LoginBean {
	private String userName;
	private String password;
	public String getUserName(){
		return userName;
	}
	public String getPassword(){
		return password;
	}
	public void setUserName(String value){
		this.userName =value;
	}
	public void setPassword(String value){
		this.password = value;
	}
}
