package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserCreationException extends ServerException {
	
	public UserCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
