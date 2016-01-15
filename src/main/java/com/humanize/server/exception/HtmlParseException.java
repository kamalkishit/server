package com.humanize.server.exception;

public class HtmlParseException extends ServerException {
	
	public HtmlParseException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
