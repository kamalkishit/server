package com.humanize.server.exception;

public class UserCreationException extends ServerException {
	
	public UserCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
