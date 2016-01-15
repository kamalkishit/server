package com.humanize.server.exception;

public class HtmlScrapException extends ServerException {
	
	public HtmlScrapException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
