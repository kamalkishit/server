package com.humanize.server.exception;

public class UserDataNotFoundException extends ServerException {
	
	public UserDataNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
