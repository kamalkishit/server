package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class PaperUpdationException extends ServerException {
	
	public PaperUpdationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
