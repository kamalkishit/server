package com.humanize.server.service;

import com.humanize.server.authentication.exception.PasswordResetFailedException;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;
import com.humanize.server.authentication.exception.UserCreationFailedException;
import com.humanize.server.authentication.exception.UserDataNotFoundException;
import com.humanize.server.authentication.exception.UserInvitationFailedException;
import com.humanize.server.authentication.exception.UserLoginFailedException;
import com.humanize.server.authentication.exception.UserUpdationFailedException;
import com.humanize.server.authentication.exception.UserVerificationFailedException;
import com.humanize.server.authentication.exception.VerificationCodeSendingFailedException;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;

public interface UserService {

	public User login(LoginUser loginUser) throws UserLoginFailedException;
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingFailedException;
	public boolean sendVerificationCode(String emailId) throws VerificationCodeSendingFailedException;
	public User resetPassword(ResetPasswordUser resetPasswordUser) throws PasswordResetFailedException;
	public boolean logout(User user);
	public User getUserdata(String emailId) throws UserDataNotFoundException;
	public User updateUser(User user) throws UserUpdationFailedException;
	public User signup(SignupUser user) throws UserCreationFailedException;
	public boolean inviteUser(String emailId) throws UserInvitationFailedException;
	public boolean verifyUser(String emailId, String verificationCode) throws UserVerificationFailedException;
	public boolean recommend(String userId, String contentId, boolean flag) throws Exception;
	public boolean bookmark(String userId, String contentId, boolean flag) throws Exception;
}
