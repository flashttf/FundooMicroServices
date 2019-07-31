package com.fundoomicro.noteoperations.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

	
//	@Bean
//	public RabbitMQSender rabbitMQSender() {
//		return new RabbitMQSender();
//	}
}
