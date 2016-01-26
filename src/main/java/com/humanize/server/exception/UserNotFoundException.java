package com.humanize.server.exception;

public class UserNotFoundException extends ServerException {
	
	public UserNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
