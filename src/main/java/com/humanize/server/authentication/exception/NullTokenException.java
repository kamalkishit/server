package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class NullTokenException extends ServerException {
	
	public NullTokenException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
