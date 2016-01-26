package com.humanize.server.exception;

public class TokenCreationException extends ServerException {
	
	public TokenCreationException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}