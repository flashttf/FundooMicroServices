package com.fundoomicro.useroperations.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fundoomicro.useroperations.dto.LoginDto;
import com.fundoomicro.useroperations.dto.Mail;
import com.fundoomicro.useroperations.dto.Response;
import com.fundoomicro.useroperations.dto.UserDto;
import com.fundoomicro.useroperations.model.User;
import com.fundoomicro.useroperations.repository.IUserRepository;
import com.fundoomicro.useroperations.utility.EncryptionUtility;
import com.fundoomicro.useroperations.utility.ITokenGenerator;
import com.fundoomicro.useroperations.utility.MailService;
import com.fundoomicro.useroperations.utility.ResponseUtility;

@Component
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements IUserService{


	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MailService mailService;

	@Autowired
	private EncryptionUtility encryptUtil;

	@Autowired
	private Environment environment;
	
	@Autowired
	private ITokenGenerator tokenGenerator;
	
	@Override
	public Response registerUser(UserDto userDto, HttpServletRequest request) {
		Mail email = new Mail();
		boolean isEmail = userRepository.findByEmail(userDto.getEmail()).isPresent();

		if (isEmail) {
			Response response = ResponseUtility.getResponse(200, "", environment.getProperty("user.mail.alreadyPresent"));
			return response;
		} else {
			User user=modelMapper.map(userDto, User.class);
			String token = tokenGenerator.generateToken(user.getUserId());
			user.setUserPassword(encryptUtil.encryptPassword(userDto.getUserPassword()));
			
			
			userRepository.save(user);
			StringBuffer requestUrl=request.getRequestURL();
			String activationUrl=requestUrl.substring(0,requestUrl.lastIndexOf("/"))+"/activation/"+token;
			email.setSenderEmail("pawansp72@gmail.com");
			email.setTo(user.getEmail());
			email.setSubject("Verification");
			email.setBody("Verification Link: \n"+activationUrl);
			
			mailService.send(email);
//			System.out.println("Sending rabbit MQ Mail");
//			mailService.rabbitSender(email);
			

			Response response = ResponseUtility.getResponse(200, token,
					environment.getProperty("user.register.success"));
			return response;

		}
	}

	@Override
	public Response validateEmail(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response loginUser(LoginDto loginDto) {
		Optional<User> isUser=userRepository.findByEmail(loginDto.getEmail());
		if(!isUser.isPresent()) {
			Response response=ResponseUtility.getResponse(204, "", environment.getProperty("user.login.emailNotRegistered"));
			return response;
			
		}else {
			
			boolean isPassword=encryptUtil.isPassword(loginDto, isUser.get());
			if(isPassword) {
				String token=tokenGenerator.generateToken(isUser.get().getUserId());
				Response response=ResponseUtility.getResponse(200, token, environment.getProperty("user.login.success"));
				return response;
			}else {
				Response response=ResponseUtility.getResponse(204, "", environment.getProperty("user.login.invalidPassword"));
				return response;
			}
		}
	}

	@Override
	public Boolean isUserPresent(String token) {
		String userId=tokenGenerator.verifyToken(token);
		Optional<User> isUser=userRepository.findByUserId(userId);
		if(isUser.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
