package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class WrongPasswordException extends ServerException {
	
	public WrongPasswordException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
