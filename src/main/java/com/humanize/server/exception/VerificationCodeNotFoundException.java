package com.humanize.server.exception;

public class VerificationCodeNotFoundException extends ServerException {

	public VerificationCodeNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
