package com.fundoomicro.noteoperations.utility;

import org.springframework.stereotype.Component;

import com.fundoomicro.noteoperations.dto.Response;



@Component
public class ResponseUtility {
	public static Response getResponse(int statusCode, String token, String statusMessage) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setToken(token);
		response.setStatusMessage(statusMessage);
		return response;
	}
}
