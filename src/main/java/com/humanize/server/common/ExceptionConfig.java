package com.humanize.server.common;

public class ExceptionConfig {
	
	// Authentication
	public static final int NULL_EMAIL_ID_ERROR_CODE = 10001;
	public static final int INVALID_EMAIL_ID_ERROR_CODE = 10002;
	public static final int WRONG_EMAIL_ID_ERROR_CODE = 10003;
	
	public static final int NULL_PASSWORD_ERROR_CODE = 10004;
	public static final int INVALID_PASSWORD_ERROR_CODE = 10005;
	public static final int WRONG_PASSWORD_ERROR_CODE = 10006;
	
	public static final int NULL_TEMP_PASSWORD_ERROR_CODE = 10007;
	public static final int WRONG_TEMP_PASSWORD_ERROR_CODE = 10008;
	
	public static final int NULL_INVITATION_CODE_ERROR_CODE = 10009;
	public static final int WRONG_INVITATION_CODE_ERROR_CODE = 10010;
	public static final int INVITATION_CODE_CREATION_ERROR_CODE = 10011;
	public static final int INVITATION_CODE_UPDATION_ERROR_CODE = 10012;
	public static final int INVITATION_CODE_NOT_FOUND_ERROR_CODE = 10013;
	public static final int INVITATION_CODE_DELETION_ERROR_CODE = 10014;
	
	public static final int NULL_VERIFICATION_CODE_ERROR_CODE = 10015;
	public static final int WRONG_VERIFICATION_CODE_ERROR_CODE = 10016;
	public static final int VERIFICATION_CODE_CREATION_ERROR_CODE = 10017;
	public static final int VERIFICATION_CODE_UPDATION_ERROR_CODE = 10018;
	public static final int VERIFICATION_CODE_NOT_FOUND_ERROR_CODE = 10019;
	public static final int VERIFICATION_CODE_DELETION_ERROR_CODE = 10020;
	
	public static final int NULL_USER_ERROR_CODE = 10021;
	public static final int USER_CREATION_ERROR_CODE = 10022;
	public static final int USER_UPDATION_ERROR_CODE = 10023;
	public static final int USER_NOT_FOUND_ERROR_CODE = 10024;
	public static final int USER_DELETION_ERROR_CODE = 10025;
	
	
	// Authentication
	public static final String NULL_EMAIL_ID_EXCEPTION = "EmailId is null";
	public static final String INVALID_EMAIL_ID_EXCEPTION = "EmailId is invalid";
	public static final String WRONG_EMAIL_ID_EXCEPTION = "EmailId is wrong";
	
	public static final String NULL_PASSWORD_EXCEPTION = "Password is null";
	public static final String INVALID_PASSWORD_EXCEPTION ="Password is invalid";
	public static final String WRONG_PASSWORD_EXCEPTION = "Password is wrong";
	
	public static final String NULL_TEMP_PASSWORD_EXCEPTION = "Temp password is null";
	public static final String WRONG_TEMP_PASSWORD_EXCEPTION = "Temp password is wrong";
	
	public static final String NULL_INVITATION_CODE_EXCEPTION = "Invitation code is null";
	public static final String WRONG_INVITATION_CODE_EXCEPTION = "Invitation code is wrong";
	public static final String INVITATION_CODE_CREATION_EXCEPTION ="Invitation code not created successfully";
	public static final String INVITATION_CODE_UPDATION_EXCEPTION = "Invitation code not updated successfully";
	public static final String INVITATION_CODE_NOT_FOUND_EXCEPTION = "Invitation code not found";
	public static final String INVITATION_CODE_DELETION_EXCEPTION = "Invitation code not deleted successfully";

	public static final String NULL_VERIFICATION_CODE_EXCEPTION = "Verification code is null";
	public static final String WRONG_VERIFICATION_CODE_EXCEPTION = "Verification code is wrong";
	public static final String VERIFICATION_CODE_CREATION_EXCEPTION ="Verification code not created successfully";
	public static final String VERIFICATION_CODE_UPDATION_EXCEPTION = "Verification code not updated successfully";
	public static final String VERIFICATION_CODE_NOT_FOUND_EXCEPTION = "Verification code not found";
	public static final String VERIFICATION_CODE_DELETION_EXCEPTION = "Verification code not deleted successfully";
	
	public static final String NULL_USER_EXCEPTION = "User is null";
	public static final String USER_CREATION_EXCEPTION = "User not created successfully";
	public static final String USER_UPDATION_EXCEPTION = "User not updated successfully";
	public static final String USER_NOT_FOUND_EXCEPTION = "User not found";
	public static final String USER_DELETION_EXCEPTION = "User not deleted successfully";
}
