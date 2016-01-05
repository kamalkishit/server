package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class ResetPasswordException extends ServerException {
	
	public ResetPasswordException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}