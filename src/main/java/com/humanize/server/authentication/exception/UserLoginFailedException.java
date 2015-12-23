package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserLoginFailedException extends ServerException {
	
	public UserLoginFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
