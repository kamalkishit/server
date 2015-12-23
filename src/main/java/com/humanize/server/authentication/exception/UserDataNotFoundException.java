package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserDataNotFoundException extends ServerException {
	
	public UserDataNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
