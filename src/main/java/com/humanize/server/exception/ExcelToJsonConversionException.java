package com.humanize.server.exception;

public class ExcelToJsonConversionException extends ServerException {
	
	public ExcelToJsonConversionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
