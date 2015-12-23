package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class WrongVerificationCodeException extends ServerException {
	
	public WrongVerificationCodeException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
