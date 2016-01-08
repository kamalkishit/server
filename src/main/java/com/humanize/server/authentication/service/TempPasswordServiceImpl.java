package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordCreationException;
import com.humanize.server.authentication.exception.TempPasswordNotFoundException;
import com.humanize.server.authentication.exception.TempPasswordUpdateException;
import com.humanize.server.authentication.exception.TempPasswordValidationException;
import com.humanize.server.config.Config;
import com.humanize.server.exception.EmailSendingException;
import com.humanize.server.exception.TempPasswordSendingException;

@Service
public class TempPasswordServiceImpl implements TempPasswordService {

	@Autowired
	private RandomStringGeneratorService randomStringGeneratorService;
	
	@Autowired
	private TempPasswordRepositoryService repositoryService;
	
	@Autowired
	private EmailService emailService;
	
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingException {
		try {
			String tempPasswordStr = randomStringGeneratorService.getTempPassword();
			emailService.sendEmail(new Message(emailId, "Temp Password", createPasswordResetString(emailId, tempPasswordStr)));
			
			TempPassword tempPassword = new TempPassword(emailId, tempPasswordStr);
			repositoryService.createOrUpdate(tempPassword);
				
			return true;
		} catch (EmailSendingException exception) {
			exception.printStackTrace();
			throw new TempPasswordSendingException(0, null);
		} catch (TempPasswordCreationException | TempPasswordUpdateException exception) {
			throw new TempPasswordSendingException(0, null);
		}
	}
	
	public boolean validateTempPassword(String emailId, String tempPasswordStr) throws TempPasswordValidationException {
		try {
			TempPassword tempPassword = repositoryService.findByEmailId(emailId);
			
			if (tempPassword.getTempPassword().equals(tempPasswordStr)) {
				return true;
			} else {
				return false;
			}
		} catch (TempPasswordNotFoundException exception) {
			throw new TempPasswordValidationException(0, null);
		}
	}
	
	private String createPasswordResetString(String emailId, String tempPassword) {
		return Config.URL_PASSWORD_RESET + "?emailId=" + emailId + "&tempPassword=" + tempPassword;
	}
}
