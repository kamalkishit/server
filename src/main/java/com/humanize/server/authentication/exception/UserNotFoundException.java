package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserNotFoundException extends ServerException {
	
	public UserNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
