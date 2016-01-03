package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserDeletionException extends ServerException {
	
	public UserDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
