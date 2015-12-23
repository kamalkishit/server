package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ContentCreationException extends ServerException {
	
	public ContentCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
