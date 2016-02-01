package com.humanize.server.exception;

public class UserDeviceNotFoundException extends ServerException {
	
	public UserDeviceNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
