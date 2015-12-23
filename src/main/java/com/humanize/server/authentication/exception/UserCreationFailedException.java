package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserCreationFailedException extends ServerException {
	
	public UserCreationFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
