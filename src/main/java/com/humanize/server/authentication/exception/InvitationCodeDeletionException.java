package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeDeletionException extends ServerException {

	public InvitationCodeDeletionException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
