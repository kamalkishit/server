package com.humanize.server.authentication.service;

import com.humanize.server.Message;
import com.humanize.server.exception.EmailSendingException;

public interface EmailService {
	
	public boolean sendEmail(Message message) throws EmailSendingException;
}
