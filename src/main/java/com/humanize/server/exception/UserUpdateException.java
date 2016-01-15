package com.humanize.server.exception;

public class UserUpdateException extends ServerException {
	
	public UserUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
