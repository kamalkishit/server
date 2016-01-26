package com.humanize.server.exception;

public class ContentDeletionException extends ServerException {
	
	public ContentDeletionException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
