package com.humanize.server.exception;

public class S3ImageCreationException extends ServerException {
	
	public S3ImageCreationException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
