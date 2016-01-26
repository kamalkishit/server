package com.humanize.server.exception;

public class HtmlPropertyParseException extends ServerException {
	
	public HtmlPropertyParseException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
