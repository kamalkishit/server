package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserInvitationFailedException extends ServerException {
	
	public UserInvitationFailedException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
