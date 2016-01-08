package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordUpdateException extends ServerException {
	
	public TempPasswordUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
