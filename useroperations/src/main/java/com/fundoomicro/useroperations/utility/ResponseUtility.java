package com.fundoomicro.useroperations.utility;

import org.springframework.stereotype.Component;

import com.fundoomicro.useroperations.dto.Response;


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
