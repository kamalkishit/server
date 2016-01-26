package com.humanize.server.exception;

public class ContentNotFoundException extends ServerException {
	
	public ContentNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
