package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordSendingFailedException extends ServerException {
	
	public TempPasswordSendingFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
