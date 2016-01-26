package com.humanize.server.exception;

public class ContentUpdateException extends ServerException {
	
	public ContentUpdateException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
