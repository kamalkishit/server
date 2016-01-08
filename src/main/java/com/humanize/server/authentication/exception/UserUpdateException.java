package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserUpdateException extends ServerException {
	
	public UserUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
