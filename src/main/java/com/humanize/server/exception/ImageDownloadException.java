package com.humanize.server.exception;

public class ImageDownloadException extends ServerException {
	
	public ImageDownloadException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}