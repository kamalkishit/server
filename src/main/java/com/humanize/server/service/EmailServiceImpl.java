package com.humanize.server.service;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.exception.EmailSendingException;
import com.humanize.server.exception.ErrorCodes;

@Service
public class EmailServiceImpl implements EmailService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	private static final String TAG = EmailServiceImpl.class.getSimpleName();
	
	public EmailServiceImpl() {
	}
	
	public boolean sendEmail(MimeMessage mimeMessage) throws EmailSendingException {
		try {
			Transport.send(mimeMessage);
			return true;			
		} catch(MessagingException exception) {
			logger.error(TAG, exception);
			throw new EmailSendingException(ErrorCodes.EMAIL_SENDING_ERROR);
		} 
	}
}
