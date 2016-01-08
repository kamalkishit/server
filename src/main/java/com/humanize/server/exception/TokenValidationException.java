package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class TokenValidationException extends ServerException {
	
	public TokenValidationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}