package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class HtmlPropertyNotFoundException extends ServerException {
	
	public HtmlPropertyNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
