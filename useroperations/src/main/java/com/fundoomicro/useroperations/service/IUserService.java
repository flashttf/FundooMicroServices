package com.fundoomicro.useroperations.service;

import javax.servlet.http.HttpServletRequest;

import com.fundoomicro.useroperations.dto.LoginDto;
import com.fundoomicro.useroperations.dto.Response;
import com.fundoomicro.useroperations.dto.UserDto;



public interface IUserService {
	Response registerUser(UserDto userDto,HttpServletRequest requestUrl);
	Response validateEmail(String token);
	Response loginUser(LoginDto loginDto);
	boolean isUserPresent(String token);
}
