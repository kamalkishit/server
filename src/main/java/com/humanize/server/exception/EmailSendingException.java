package com.humanize.server.exception;

public class EmailSendingException extends ServerException {
	
	public EmailSendingException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
