package com.humanize.server.exception;

public class HtmlPropertyParseException extends ServerException {
	
	public HtmlPropertyParseException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}	
}
