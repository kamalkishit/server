package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class S3ImageNotFoundException extends ServerException {
	
	public S3ImageNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
