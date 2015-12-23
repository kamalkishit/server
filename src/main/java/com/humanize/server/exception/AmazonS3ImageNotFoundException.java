package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class AmazonS3ImageNotFoundException extends ServerException {
	
	public AmazonS3ImageNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
