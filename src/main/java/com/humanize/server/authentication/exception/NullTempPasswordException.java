package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class NullTempPasswordException extends ServerException {

	public NullTempPasswordException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
