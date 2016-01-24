package com.humanize.server.exception;

public class S3ImageNotFoundException extends ServerException {
	
	public S3ImageNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
