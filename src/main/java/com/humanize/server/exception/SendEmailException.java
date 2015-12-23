package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class SendEmailException extends ServerException {
	
	public SendEmailException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
