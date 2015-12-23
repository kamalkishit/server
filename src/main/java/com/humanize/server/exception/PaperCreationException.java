package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class PaperCreationException extends ServerException {
	
	public PaperCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
