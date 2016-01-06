package com.humanize.server.service;

import com.humanize.server.authentication.exception.ForgotPasswordException;
import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;

public interface UserService {

	User login(LoginUser loginUser) throws UserNotFoundException;
	boolean forgotPassword(String emailId) throws ForgotPasswordException;
	User resetPassword(ResetPasswordUser resetPasswordUser) throws ResetPasswordException;
	User getUserdata(String emailId) throws UserNotFoundException;
	User updateUser(User user) throws UserUpdationException;
	User signup(SignupUser user) throws UserCreationException;
	boolean inviteUser(String emailId, String invitedBy) throws UserInvitationException;
	boolean recommend(String userId, String contentId, boolean flag) throws UserUpdationException;
	boolean bookmark(String userId, String contentId, boolean flag) throws UserUpdationException;
}
