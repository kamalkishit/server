package com.humanize.server.authentication.service;

import org.springframework.stereotype.Service;

import com.humanize.server.authentication.exception.TempPasswordValidationException;
import com.humanize.server.exception.TempPasswordSendingException;

@Service
public interface TempPasswordService {
	
	boolean sendTempPassword(String emailId) throws TempPasswordSendingException;
	boolean validateTempPassword(String emailId, String tempPassword) throws TempPasswordValidationException;
}
