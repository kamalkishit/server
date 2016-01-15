package com.humanize.server.exception;

public class UserDataNotFoundException extends ServerException {
	
	public UserDataNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
