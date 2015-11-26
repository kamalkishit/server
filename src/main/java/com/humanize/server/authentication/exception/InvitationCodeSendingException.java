package com.humanize.server.authentication.exception;

import java.lang.Exception;

public class InvitationCodeSendingException extends Exception {

	private int errorCode;
	private String errorMsg;
	
	public InvitationCodeSendingException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}