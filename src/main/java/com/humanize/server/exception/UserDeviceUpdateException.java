package com.humanize.server.exception;

public class UserDeviceUpdateException extends ServerException {
	
	public UserDeviceUpdateException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
