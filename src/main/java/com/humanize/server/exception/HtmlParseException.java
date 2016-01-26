package com.humanize.server.exception;

public class HtmlParseException extends ServerException {
	
	public HtmlParseException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
