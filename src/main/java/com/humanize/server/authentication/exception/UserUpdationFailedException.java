package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserUpdationFailedException extends ServerException {
	
	public UserUpdationFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
