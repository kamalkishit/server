package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class VerificationCodeSendingException extends ServerException {
	
	public VerificationCodeSendingException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
