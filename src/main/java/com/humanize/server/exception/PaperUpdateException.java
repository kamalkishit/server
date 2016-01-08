package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class PaperUpdateException extends ServerException {
	
	public PaperUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
