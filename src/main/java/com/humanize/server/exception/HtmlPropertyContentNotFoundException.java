package com.humanize.server.exception;

public class HtmlPropertyContentNotFoundException extends ServerException {
	
	public HtmlPropertyContentNotFoundException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
