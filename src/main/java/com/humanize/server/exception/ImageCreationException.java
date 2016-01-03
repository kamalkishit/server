package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class ImageCreationException extends ServerException {
	
	public ImageCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
