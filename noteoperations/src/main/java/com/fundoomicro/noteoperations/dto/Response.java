package com.fundoomicro.noteoperations.dto;

public class Response {
	int statusCode;
	String token;
	String statusMessage;

	// -----------Constructors-----------//

	public Response() {
		
	}

	public Response(int statusCode, String token) {
		
		this.statusCode = statusCode;
		this.token = token;
	}

	public Response(int statusCode, String token, String statusMessage) {
		
		this.statusCode = statusCode;
		this.token = token;
		this.statusMessage = statusMessage;
	}

	//---------------Getters and Setters----------//

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
