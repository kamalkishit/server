package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ContentDeletionException extends ServerException {
	
	public ContentDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
