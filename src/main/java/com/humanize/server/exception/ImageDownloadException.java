package com.humanize.server.exception;

public class ImageDownloadException extends ServerException {
	
	public ImageDownloadException(ErrorCodes errorCodes) {
		this.errorCode = errorCodes.getErrorCode();
		this.errorMsg = errorCodes.getErrorMsg();
	}
}