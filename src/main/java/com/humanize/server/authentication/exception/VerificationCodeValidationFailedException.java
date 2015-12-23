package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class VerificationCodeValidationFailedException extends ServerException {
	
	public VerificationCodeValidationFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
