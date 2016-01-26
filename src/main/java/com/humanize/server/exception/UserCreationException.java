package com.humanize.server.exception;

public class UserCreationException extends ServerException {
	
	public UserCreationException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
