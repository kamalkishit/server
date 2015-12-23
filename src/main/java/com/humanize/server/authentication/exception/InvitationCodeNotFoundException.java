package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeNotFoundException extends ServerException {

	public InvitationCodeNotFoundException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
