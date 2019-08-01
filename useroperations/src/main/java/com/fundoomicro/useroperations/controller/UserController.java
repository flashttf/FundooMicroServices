package com.fundoomicro.useroperations.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundoomicro.useroperations.dto.LoginDto;
import com.fundoomicro.useroperations.dto.Response;
import com.fundoomicro.useroperations.dto.UserDto;
import com.fundoomicro.useroperations.service.IUserService;


@RestController
@RequestMapping("/usercontroller")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserDto userDto, HttpServletRequest httpServletRequest) {
		Response response = userService.registerUser(userDto, httpServletRequest);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDto){
		Response response=userService.loginUser(loginDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/usercheck/{token}")
	public boolean isUserPresent(@PathVariable String token){
		boolean isUser=userService.isUserPresent(token);
		System.out.println("hello");
		return isUser;
	}
}
