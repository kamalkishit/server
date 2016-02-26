package com.humanize.server.exception;

public class PasswordVerificationCodeNotFoundException extends ServerException {

	public PasswordVerificationCodeNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
