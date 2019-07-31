package com.fundoomicro.useroperations.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	@Id
	private String userId;
	private String userName;
	private String phoneNumber;

	@Indexed(unique = true)
	private String email;
	private String userPassword;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", userPassword=" + userPassword + "]";
	}
	public User(String userId, String userName, String phoneNumber, String email, String userPassword) {
		
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userPassword = userPassword;
	}
	
	public User() {
		
	}
	
	
	
}