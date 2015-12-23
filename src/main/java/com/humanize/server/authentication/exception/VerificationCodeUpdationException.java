package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class VerificationCodeUpdationException extends ServerException {
	
	public VerificationCodeUpdationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
