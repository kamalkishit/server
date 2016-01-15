package com.humanize.server.exception;

public class HtmlPropertyContentNotFoundException extends ServerException {
	
	public HtmlPropertyContentNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
