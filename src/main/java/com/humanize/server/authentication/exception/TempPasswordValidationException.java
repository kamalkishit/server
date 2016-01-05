package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordValidationException extends ServerException {
	
	public TempPasswordValidationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}