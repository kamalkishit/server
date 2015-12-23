package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeCreationException extends ServerException {

	public InvitationCodeCreationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
