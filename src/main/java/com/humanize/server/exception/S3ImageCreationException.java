package com.humanize.server.exception;

public class S3ImageCreationException extends ServerException {
	
	public S3ImageCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
