package com.humanize.server.exception;

public class UserDeviceCreationException extends ServerException {
	
	public UserDeviceCreationException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}