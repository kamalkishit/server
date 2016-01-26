package com.humanize.server.exception;

public class UserDeletionException extends ServerException {
	
	public UserDeletionException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
