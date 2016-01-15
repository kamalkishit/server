package com.humanize.server.exception;

public class UserNotFoundException extends ServerException {
	
	public UserNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
