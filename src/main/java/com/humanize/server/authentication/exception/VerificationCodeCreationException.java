package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class VerificationCodeCreationException extends ServerException {
	
	public VerificationCodeCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
