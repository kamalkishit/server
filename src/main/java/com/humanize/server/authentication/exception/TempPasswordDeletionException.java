package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class TempPasswordDeletionException extends ServerException {
	
	public TempPasswordDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
