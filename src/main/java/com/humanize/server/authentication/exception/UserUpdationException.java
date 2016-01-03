package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserUpdationException extends ServerException {
	
	public UserUpdationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
