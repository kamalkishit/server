package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class PasswordResetFailedException extends ServerException {
	
	public PasswordResetFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
