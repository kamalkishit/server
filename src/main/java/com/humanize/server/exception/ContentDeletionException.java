package com.humanize.server.exception;

public class ContentDeletionException extends ServerException {
	
	public ContentDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
