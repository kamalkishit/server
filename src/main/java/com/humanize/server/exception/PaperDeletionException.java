package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class PaperDeletionException extends ServerException {
	
	public PaperDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
