package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.VerificationCodeSendingException;
import com.humanize.server.authentication.exception.VerificationCodeValidationFailedException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class VerificationCodeService {
	
	@Autowired
	VerificationCodeRepositoryService repositoryService;
	
	@Autowired
	RandomStringGeneratorService ranodmStringGeneratoService;
	
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
	

	public boolean sendVerificationCode(String emailId) throws VerificationCodeSendingException {
		try {
			String verificationCodeStr = ranodmStringGeneratoService.getVerificationCode();
			
			VerificationCode verificationCode = new VerificationCode(emailId, verificationCodeStr);
			emailService.sendEmail(emailId, verificationCodeStr);
			repositoryService.create(verificationCode);
			
			return true;
		} catch (Exception exception) {
			throw new VerificationCodeSendingException(0, null);
		}
		
	}
	
	


}
