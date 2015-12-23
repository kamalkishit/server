package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class AmazonS3ImageCreationException extends ServerException {
	
	public AmazonS3ImageCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
