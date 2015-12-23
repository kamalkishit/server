package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeSendingException extends ServerException {

	public InvitationCodeSendingException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
