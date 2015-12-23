package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeUpdationException extends ServerException {
	
	public InvitationCodeUpdationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
