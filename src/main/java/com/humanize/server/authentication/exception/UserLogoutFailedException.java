package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserLogoutFailedException extends ServerException {
	
	public UserLogoutFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
