package com.humanize.server.service;

import com.humanize.server.authentication.exception.ForgotPasswordException;
import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdateException;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;

public interface UserService {

	String login(LoginUser loginUser) throws UserNotFoundException;
	boolean forgotPassword(String emailId) throws ForgotPasswordException;
	User resetPassword(ResetPasswordUser resetPasswordUser) throws ResetPasswordException;
	User getUserdata(String token) throws UserNotFoundException;
	User updateUser(String token, User user) throws UserUpdateException;
	User signup(SignupUser user) throws UserCreationException;
	User signupFirst(SignupUser user) throws UserCreationException;
	boolean inviteUser(String token, String emailId) throws UserInvitationException;
	boolean recommend(String token, String contentId, boolean flag) throws UserUpdateException;
	boolean bookmark(String token, String contentId, boolean flag) throws UserUpdateException;
}
