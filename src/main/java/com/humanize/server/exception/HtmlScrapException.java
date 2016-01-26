package com.humanize.server.exception;

public class HtmlScrapException extends ServerException {
	
	public HtmlScrapException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
