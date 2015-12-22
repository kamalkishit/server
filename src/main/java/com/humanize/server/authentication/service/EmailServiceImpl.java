package com.humanize.server.authentication.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.exception.SendEmailException;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean sendEmail(Message message) throws SendEmailException {
		mimeMessage = javaMailSender.createMimeMessage();
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(message.getTo());
			mimeMessageHelper.setSubject(message.getSubject());
			mimeMessageHelper.setText(message.getBody());
			
			javaMailSender.send(mimeMessage);
			
		} catch(MessagingException exception) {
			logger.error("", exception);
			throw new SendEmailException(0, null);
		}
		
		return true;
	}
}
