package com.humanize.server.service;

import javax.mail.internet.MimeMessage;

import com.humanize.server.exception.EmailSendingException;

public interface EmailService {
	
	boolean sendEmail(MimeMessage mimeMessage) throws EmailSendingException;
}
