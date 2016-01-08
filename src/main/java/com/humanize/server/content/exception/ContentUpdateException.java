package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ContentUpdateException extends ServerException {
	
	public ContentUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
