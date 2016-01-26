package com.humanize.server.exception;

public class ContentCreationException extends ServerException {
	
	public ContentCreationException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
