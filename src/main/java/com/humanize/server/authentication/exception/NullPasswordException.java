package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class NullPasswordException extends ServerException {
	
	public NullPasswordException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
