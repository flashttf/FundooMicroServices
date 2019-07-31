package com.fundoomicro.useroperations.utility;

public interface ITokenGenerator {
	
	String generateToken(String userID);
	
	String verifyToken(String token);
}
