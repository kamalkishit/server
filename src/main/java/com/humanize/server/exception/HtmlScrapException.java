package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class HtmlScrapException extends ServerException {
	
	public HtmlScrapException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
