package com.swing.foodserving.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class MailUtil {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendForgotPasswordOTP(String to,String subject,String msg) {
		System.out.println(javaMailSender);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		
		javaMailSender.send(message);
	}
	
}
