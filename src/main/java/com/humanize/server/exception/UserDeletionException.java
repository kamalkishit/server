package com.humanize.server.exception;

public class UserDeletionException extends ServerException {
	
	public UserDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
