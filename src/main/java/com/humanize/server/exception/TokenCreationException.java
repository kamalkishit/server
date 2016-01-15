package com.humanize.server.exception;

public class TokenCreationException extends ServerException {
	
	public TokenCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}