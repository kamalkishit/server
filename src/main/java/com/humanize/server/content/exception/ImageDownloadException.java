package com.humanize.server.content.exception;

import com.humanize.server.ServerException;

public class ImageDownloadException extends ServerException {
	
	public ImageDownloadException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}