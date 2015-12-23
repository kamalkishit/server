package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class VerificationCodeNotFoundException extends ServerException {
	
	public VerificationCodeNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
