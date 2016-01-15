package com.humanize.server.exception;

public class ContentUpdateException extends ServerException {
	
	public ContentUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
