package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;

@Service
public class TempPasswordServiceImpl implements TempPasswordService {

	@Autowired
	RandomStringGeneratorService randomStringGeneratorService;
	
	@Autowired
	TempPasswordRepositoryService repositoryService;
	
	@Autowired
	EmailService emailService;
	
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingFailedException {
		try {
			String tempPasswordStr = randomStringGeneratorService.getTempPassword();
			
			String str = "http://www.humannize.com/signup?emailId=" + emailId + "&tempPassword=" + tempPasswordStr; 
			emailService.sendEmail(new Message(emailId, "Temp Password", str));
			
			TempPassword tempPassword = new TempPassword(emailId, tempPasswordStr);
			repositoryService.createOrUpdate(tempPassword);
				
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new TempPasswordSendingFailedException(0, null);
		}
	}
}
