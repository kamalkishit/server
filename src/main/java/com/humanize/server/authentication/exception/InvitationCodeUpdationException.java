package com.humanize.server.authentication.exception;

public class InvitationCodeUpdationException extends RuntimeException {

	private int errorCode;
	private String errorMsg;
	
	public InvitationCodeUpdationException(int errorCode, String errorMsg) {
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
