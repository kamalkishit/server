package com.humanize.server.authentication.exception;

import com.humanize.server.ServerException;

public class UserInvitationException extends ServerException {
	
	public UserInvitationException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
