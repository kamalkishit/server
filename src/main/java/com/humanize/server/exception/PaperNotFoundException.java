package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class PaperNotFoundException extends ServerException {
	
	public PaperNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
