package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;

@Service
public class TempPasswordService {
	
	@Autowired
	RandomStringGeneratorService randomStringGeneratorService;
	
	@Autowired
	TempPasswordRepositoryService repositoryService;
	
	@Autowired
	EmailService emailService;
	
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingFailedException {
		try {
			String tempPasswordStr = randomStringGeneratorService.getTempPassword();
			
			TempPassword tempPassword = new TempPassword(emailId, tempPasswordStr);
			emailService.sendEmail(emailId, tempPasswordStr);
			
			repositoryService.createOrUpdate(tempPassword);
				
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new TempPasswordSendingFailedException(0, null);
		}
	}
}
