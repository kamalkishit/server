package com.humanize.server.exception;

public class UrlConnectionException extends ServerException {
	
	public UrlConnectionException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
