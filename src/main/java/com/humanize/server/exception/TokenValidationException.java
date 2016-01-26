package com.humanize.server.exception;

public class TokenValidationException extends ServerException {
	
	public TokenValidationException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}