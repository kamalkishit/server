package com.humanize.server.service;

import com.humanize.server.data.ContactUs;
import com.humanize.server.data.InviteUser;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.SuggestArticle;
import com.humanize.server.data.User;
import com.humanize.server.exception.EmailSendingException;
import com.humanize.server.exception.UserCreationException;
import com.humanize.server.exception.UserNotFoundException;

public interface UserService {

	String login(LoginUser loginUser) throws UserNotFoundException;
	User signup(SignupUser user) throws UserCreationException;
	User findByEmailId(String emailId) throws UserNotFoundException;
	User findByToken(String token) throws UserNotFoundException;
	boolean forgotPassword(String emailId) throws EmailSendingException;
	boolean resetPassword(ResetPasswordUser resetPasswordUser) throws Exception;
	boolean invite(InviteUser inviteUser) throws EmailSendingException;
	boolean suggest(SuggestArticle suggestArticle) throws EmailSendingException;
	boolean contactUs(ContactUs contactUs) throws EmailSendingException;
}
