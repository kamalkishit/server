package com.humanize.server.exception;

import com.humanize.server.ServerException;

public class ImageNotFoundException extends ServerException {
	
	public ImageNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
