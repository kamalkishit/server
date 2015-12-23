package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class WrongEmailIdException extends ServerException {
	
	public WrongEmailIdException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
