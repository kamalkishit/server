package com.humanize.server.authentication.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
	public boolean sendEmail(String to, String content) {
		mimeMessage = javaMailSender.createMimeMessage();
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo("pandey.kishore@gmail.com");
			mimeMessageHelper.setSubject("hihello");
			mimeMessageHelper.setText("gud");
			
			javaMailSender.send(mimeMessage);
			
		} catch(MessagingException exception) {
			exception.printStackTrace();
		}
		
		return true;
	}
}
