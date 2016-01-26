package com.humanize.server.exception;

public class UserUpdateException extends ServerException {
	
	public UserUpdateException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
