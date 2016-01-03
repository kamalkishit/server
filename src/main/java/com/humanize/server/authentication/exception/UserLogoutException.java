package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserLogoutException extends ServerException {
	
	public UserLogoutException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
