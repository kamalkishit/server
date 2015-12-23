package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserVerificationFailedException extends ServerException {
	
	public UserVerificationFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
