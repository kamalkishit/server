package com.humanize.server.service;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.exception.EmailSendingException;

@Service
public class EmailServiceImpl implements EmailService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public EmailServiceImpl() {
	}
	
	public boolean sendEmail(MimeMessage mimeMessage) throws EmailSendingException {
		try {
			Transport.send(mimeMessage);
			return true;			
		} catch(MessagingException exception) {
			logger.error("", exception);
			throw new EmailSendingException(0, null);
		} 
	}
}
