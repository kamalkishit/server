package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ExcelToJsonConversionException extends ServerException {
	
	public ExcelToJsonConversionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
