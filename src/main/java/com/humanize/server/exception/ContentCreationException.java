package com.humanize.server.exception;

public class ContentCreationException extends ServerException {
	
	public ContentCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
