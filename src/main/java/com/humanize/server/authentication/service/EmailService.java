package com.humanize.server.authentication.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.humanize.server.exception.SendEmailException;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean sendEmail(String to, String content) throws SendEmailException {
		mimeMessage = javaMailSender.createMimeMessage();
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject("hihello");
			mimeMessageHelper.setText(content);
			
			javaMailSender.send(mimeMessage);
			
		} catch(MessagingException exception) {
			logger.error("", exception);
			throw new SendEmailException(0, null);
		}
		
		return true;
	}
}
