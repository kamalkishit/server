package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordUpdationException extends ServerException {
	
	public TempPasswordUpdationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
