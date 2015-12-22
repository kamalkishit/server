package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.VerificationCodeSendingFailedException;
import com.humanize.server.authentication.exception.VerificationCodeValidationFailedException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class VerificationCodeService {
	
	@Autowired
	VerificationCodeRepositoryService repositoryService;
	
	@Autowired
	RandomStringGeneratorService randomStringGeneratoService;
	
	@Autowired
	EmailService emailService;
	
	public boolean validateVerificationCode(String emailId, String verificationCodeStr) throws VerificationCodeValidationFailedException {
		try {
			VerificationCode verificationCode = repositoryService.findByEmailId(emailId);

			if (verificationCode.getVerificationCode().equals(verificationCodeStr)) {
				return true;
			}
		} catch (Exception exception) {
			throw new VerificationCodeValidationFailedException(ExceptionConfig.WRONG_VERIFICATION_CODE_ERROR_CODE, ExceptionConfig.WRONG_VERIFICATION_CODE_EXCEPTION);
		}
		
		return false;
	}
	

	public boolean sendVerificationCode(String emailId) throws VerificationCodeSendingFailedException {
		try {
			String verificationCodeStr = randomStringGeneratoService.getVerificationCode();
			
			VerificationCode verificationCode = new VerificationCode(emailId, verificationCodeStr);
			emailService.sendEmail(new Message(emailId, "Verification Code", verificationCodeStr));
			
			repositoryService.createOrUpdate(verificationCode);
				
			return true;
		} catch (Exception exception) {
			throw new VerificationCodeSendingFailedException(0, null);
		}
		
	}
	
	


}
