package com.humanize.server.authentication.service;

import com.humanize.server.authentication.exception.VerificationCodeSendingFailedException;
import com.humanize.server.authentication.exception.VerificationCodeValidationFailedException;

public interface VerificationCodeService {
	
	public boolean validateVerificationCode(String emailId, String verificationCodeStr) throws VerificationCodeValidationFailedException;
	public boolean sendVerificationCode(String emailId) throws VerificationCodeSendingFailedException;
}
