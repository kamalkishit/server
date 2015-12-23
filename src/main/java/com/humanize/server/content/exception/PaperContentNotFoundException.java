package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class PaperContentNotFoundException extends ServerException {
	
	public PaperContentNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
