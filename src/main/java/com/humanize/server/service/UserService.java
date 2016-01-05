package com.humanize.server.service;

import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.ForgotPasswordException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDataNotFoundException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.authentication.exception.UserVerificationFailedException;
import com.humanize.server.authentication.exception.VerificationCodeSendingFailedException;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;

public interface UserService {

	public User login(LoginUser loginUser) throws UserNotFoundException;
	public boolean forgotPassword(String emailId) throws ForgotPasswordException;
	public User resetPassword(ResetPasswordUser resetPasswordUser) throws ResetPasswordException;
	public User getUserdata(String emailId) throws UserNotFoundException;
	public User updateUser(User user) throws UserUpdationException;
	public User signup(SignupUser user) throws UserCreationException;
	public boolean inviteUser(String emailId) throws UserInvitationException;
	public boolean recommend(String userId, String contentId, boolean flag) throws UserUpdationException;
	public boolean bookmark(String userId, String contentId, boolean flag) throws UserUpdationException;
}
