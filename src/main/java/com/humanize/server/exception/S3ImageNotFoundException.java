package com.humanize.server.exception;

public class S3ImageNotFoundException extends ServerException {
	
	public S3ImageNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
