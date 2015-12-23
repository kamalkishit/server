package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class WrongTempPasswordException extends ServerException {
	
	public WrongTempPasswordException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
