package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordNotFoundException extends ServerException {
	
	public TempPasswordNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
