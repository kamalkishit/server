package com.humanize.server.exception;

public class UserDeviceDeletionException extends ServerException {
	
	public UserDeviceDeletionException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
