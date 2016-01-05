package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class TempPasswordSendingException extends ServerException {
	
	public TempPasswordSendingException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
