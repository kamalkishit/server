package com.humanize.server.authentication.service;

import org.springframework.stereotype.Service;

import com.humanize.server.Message;


public interface EmailService {
	
	public boolean sendEmail(Message message) throws Exception;
}
