package com.humanize.server.exception;

public class ExcelToJsonConversionException extends ServerException {
	
	public ExcelToJsonConversionException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}
