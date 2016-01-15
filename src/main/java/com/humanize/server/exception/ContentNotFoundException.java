package com.humanize.server.exception;

public class ContentNotFoundException extends ServerException {
	
	public ContentNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
