package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class ForgotPasswordException extends ServerException {
	
	public ForgotPasswordException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
