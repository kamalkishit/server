package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserDeletionFailedException extends ServerException {
	
	public UserDeletionFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
