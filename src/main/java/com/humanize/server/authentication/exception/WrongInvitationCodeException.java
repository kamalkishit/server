package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class WrongInvitationCodeException extends ServerException {
	
	public WrongInvitationCodeException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
