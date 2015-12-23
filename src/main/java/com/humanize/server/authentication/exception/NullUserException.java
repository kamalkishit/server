package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class NullUserException extends ServerException {
	
	public NullUserException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
