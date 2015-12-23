package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ContentUpdationException extends ServerException {
	
	public ContentUpdationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
