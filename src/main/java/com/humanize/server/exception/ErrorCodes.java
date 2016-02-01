package com.humanize.server.exception;

public enum ErrorCodes {
	
	USER_CREATION_ERROR(10001, "User creation error"),
	USER_NOT_FOUND_ERROR(10002, "User not found error"),
	USER_UPDATE_ERROR(10003, "User update error"),
	USER_DELETION_ERROR(10004, "User deletion error"),
	
	CONTENT_CREATION_ERROR(10005, "Content creation error"),
	CONTENT_NOT_FOUND_ERROR(10006, "Content not found error"),
	CONTENT_UPDATE_ERROR(10007, "Content update error"),
	CONTENT_DELETION_ERROR(10008, "Content deletion error"),
	
	EMAIL_SENDING_ERROR(10009, "Email sending error"),
	IMAGE_DOWNLOAD_ERROR(10010, "Image download error"),
	TOKEN_CREATION_ERROR(10011, "Token creation error"),
	TOKEN_VALIDATION_ERROR(10012, "Token validation error"),
	
	HTML_SCRAP_ERROR(10013, "Html scrap error"),
	HTML_PROPERTY_NOT_FOUND_ERROR(10014, "Html property not found error"),
	HTML_PROPERTY_CONTENT_NOT_FOUND_ERROR(10015, "Html property content not found error"),
	
	S3_IMAGE_CREATION_ERROR(10016, "S3 image creation error"),
	S3_IMAGE_NOT_FOUND_ERROR(10017, "S3 image not found error"),
	
	EXCEL_TO_JSON_CONVERSION_ERROR(10018, "Excel to json conversion error"),
	
	USER_DEVICE_CREATION_ERROR(10019, "User device info creation error"),
	USER_DEVICE_NOT_FOUND_ERROR(10020, "User device info not found error"),
	USER_DEVICE_UPDATE_ERROR(10021, "User device info update error"),
	USER_DEVICE_DELETION_ERROR(10022, "User device deletion error");
	
	private final int errorCode;
	private final String errorMsg;

	ErrorCodes(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
