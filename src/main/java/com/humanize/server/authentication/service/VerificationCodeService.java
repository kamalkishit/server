package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.WrongVerificationCodeException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class VerificationCodeService {
	
	@Autowired
	VerificationCodeRepositoryService repositoryService;
	
	@Autowired
	RandomStringGeneratorService ranodmStringGeneratoService;
	
	@Autowired
	EmailService emailService;
	
	public boolean sendVerificationCode(String emailId) {
		String verificationCode = ranodmStringGeneratoService.getVerificationCode();
		
		VerificationCode verificationCodeObj = new VerificationCode(emailId, verificationCode);
		emailService.sendEmail(emailId, verificationCode);
		repositoryService.create(verificationCodeObj);
		
		return true;
	}
	
	public boolean validateVerificationCode(String emailId, String verificationCode) {
		VerificationCode verificationCodeObj = repositoryService.findByEmailId(emailId);
		
		if (verificationCodeObj.getVerificationCode().equals(verificationCode)) {
			return true;
		}
		
		throw new WrongVerificationCodeException(ExceptionConfig.WRONG_VERIFICATION_CODE_ERROR_CODE, ExceptionConfig.WRONG_VERIFICATION_CODE_EXCEPTION);
	}
}
