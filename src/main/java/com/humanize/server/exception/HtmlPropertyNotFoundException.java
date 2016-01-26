package com.humanize.server.exception;

public class HtmlPropertyNotFoundException extends ServerException {
	
	public HtmlPropertyNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
