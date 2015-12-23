package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class HtmlParseException extends ServerException {
	
	public HtmlParseException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
