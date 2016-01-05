package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class EmailSendingException extends ServerException {
	
	public EmailSendingException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
