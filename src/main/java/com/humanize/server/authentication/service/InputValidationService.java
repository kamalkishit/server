package com.humanize.server.authentication.service;

import org.springframework.stereotype.Service;

import com.humanize.server.authentication.exception.NullEmailIdException;
import com.humanize.server.authentication.exception.NullInvitationCodeException;
import com.humanize.server.authentication.exception.NullPasswordException;
import com.humanize.server.authentication.exception.NullTempPasswordException;
import com.humanize.server.authentication.exception.NullUserException;
import com.humanize.server.authentication.exception.NullVerificationCodeException;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.data.User;

@Service
public class InputValidationService {
	
	public void validateEmailId(String emailId) {
		if (emailId == null || emailId.length() == 0) {
			throw new NullEmailIdException(ExceptionConfig.NULL_EMAIL_ID_ERROR_CODE, ExceptionConfig.NULL_EMAIL_ID_EXCEPTION);
		}
	}
	
	public void validatePassword(String password) {
		if (password == null || password.length() == 0) {
			throw new NullPasswordException(ExceptionConfig.NULL_PASSWORD_ERROR_CODE, ExceptionConfig.NULL_PASSWORD_EXCEPTION);
		}
	}
	
	public void validateTempPassword(String tempPassword) {
		if (tempPassword == null || tempPassword.length() == 0) {
			throw new NullTempPasswordException(ExceptionConfig.NULL_TEMP_PASSWORD_ERROR_CODE, ExceptionConfig.NULL_TEMP_PASSWORD_EXCEPTION);
		}
	}
	
	public void validateInvitationCode(String invitationCode) {
		if (invitationCode == null || invitationCode.length() == 0) {
			throw new NullInvitationCodeException(ExceptionConfig.NULL_INVITATION_CODE_ERROR_CODE, ExceptionConfig.NULL_INVITATION_CODE_EXCEPTION);
		}
	}
	
	public void validateVerificationCode(String verificationCode) {
		if (verificationCode == null || verificationCode.length() == 0) {
			throw new NullVerificationCodeException(ExceptionConfig.NULL_VERIFICATION_CODE_ERROR_CODE, ExceptionConfig.NULL_VERIFICATION_CODE_EXCEPTION);
		}
	}
	
	public void validateUser(User user) {
		if (user == null) {
			throw new NullUserException(ExceptionConfig.NULL_USER_ERROR_CODE, ExceptionConfig.NULL_USER_EXCEPTION);
		}
	}
	
	public void validateSignupUser(User user) {
		validateUser(user);
		validateEmailId(user.getEmailId());
		validatePassword(user.getPassword());
		validateInvitationCode(user.getInvitationCode());
	}
	
	public void validateLoginUser(User user) {
		validateUser(user);
		validateEmailId(user.getEmailId());
		validatePassword(user.getPassword());
	}
	
	public void validatePasswordResetUser(User user) {
		validateUser(user);
		validateEmailId(user.getEmailId());
		validatePassword(user.getPassword());
		validateTempPassword(user.getTempPassword());
	}
}
