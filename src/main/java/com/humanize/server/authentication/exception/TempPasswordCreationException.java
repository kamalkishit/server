package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordCreationException extends ServerException {
	
	public TempPasswordCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
