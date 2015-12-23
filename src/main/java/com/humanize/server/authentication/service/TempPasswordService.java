package com.humanize.server.authentication.service;

import org.springframework.stereotype.Service;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;

@Service
public interface TempPasswordService {
	
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingFailedException;
}
