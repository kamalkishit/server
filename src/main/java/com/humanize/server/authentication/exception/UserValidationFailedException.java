package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserValidationFailedException extends ServerException {
	
	public UserValidationFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
