package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class UrlConnectionException extends ServerException {
	
	public UrlConnectionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
