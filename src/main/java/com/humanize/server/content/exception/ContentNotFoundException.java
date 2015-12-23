package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ContentNotFoundException extends ServerException {
	
	public ContentNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
