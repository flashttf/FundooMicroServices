package com.fundoomicro.noteoperations.utility;

public interface ITokenGenerator {
	String generateToken(String userID);
	
	String verifyToken(String token);
}
