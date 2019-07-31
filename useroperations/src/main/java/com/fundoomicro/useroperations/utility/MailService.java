package com.fundoomicro.useroperations.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.fundoomicro.useroperations.dto.Mail;



@Component
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ITokenGenerator tokenGenerator;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void send(Mail email) {
		System.out.println("Sending e-mail to receiver");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getBody());
		javaMailSender.send(message);
		System.out.println("Mail Sent Successfully");
	}
	
	public String getLink(String link,String id) {
		return link+tokenGenerator.generateToken(id);
	}
}
