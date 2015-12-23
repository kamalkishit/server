package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class WrongTokenException extends ServerException {
	
	public WrongTokenException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
