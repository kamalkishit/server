package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class InvitationCodeUpdateException extends ServerException {
	
	public InvitationCodeUpdateException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
