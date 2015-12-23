package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class VerificationCodeDeletionException extends ServerException {
	
	public VerificationCodeDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
