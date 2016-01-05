package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeValidationException extends ServerException {
	
	public InvitationCodeValidationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
