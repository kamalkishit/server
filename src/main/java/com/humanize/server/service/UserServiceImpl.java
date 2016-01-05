package com.humanize.server.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.Password;
import com.humanize.server.authentication.exception.ForgotPasswordException;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.InvitationCodeSendingException;
import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.TempPasswordValidationException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.authentication.service.InvitationCodeRepositoryService;
import com.humanize.server.authentication.service.InvitationCodeService;
import com.humanize.server.authentication.service.TempPasswordRepositoryService;
import com.humanize.server.authentication.service.TempPasswordService;
import com.humanize.server.authentication.service.UserRepositoryService;
import com.humanize.server.authentication.service.VerificationCodeService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;
import com.humanize.server.exception.TempPasswordSendingException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositoryService repositoryService;
	
	@Autowired
	private InvitationCodeRepositoryService invitationCodeRepositoryService;
	
	@Autowired
	VerificationCodeService verificationCodeService;
	
	@Autowired
	InvitationCodeService invitationCodeService;
	
	@Autowired
	TempPasswordService tempPasswordService;
	
	@Autowired
	TempPasswordRepositoryService tempPasswordRepositoryService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public User login(LoginUser loginUser) throws UserNotFoundException {
		try {
			User user = repositoryService.findByEmailId(loginUser.getEmailId());
			
			if (Password.check(loginUser.getPassword(), user.getPassword()) /*&& user.isVerified()*/) {
				return user;
			}
			
			throw new UserNotFoundException(0, null);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new UserNotFoundException(0, null);
		}
	}
	
	public boolean forgotPassword(String emailId) throws ForgotPasswordException {
		try {
			return tempPasswordService.sendTempPassword(emailId);
		} catch (TempPasswordSendingException exception) {
			throw new ForgotPasswordException(0, null);
		}
	}
	
	public User resetPassword(ResetPasswordUser resetPasswordUser) throws ResetPasswordException {
		try {
			if (tempPasswordService.validateTempPassword(resetPasswordUser.getEmailId(), resetPasswordUser.getTempPassword())) {
				User user = repositoryService.findByEmailId(resetPasswordUser.getEmailId());
				user.setPassword(resetPasswordUser.getNewPassword());
				return repositoryService.update(user);
			}
			
			throw new ResetPasswordException(0, null);
		} catch (TempPasswordValidationException exception) {
			throw new ResetPasswordException(0, null);
		} catch (UserNotFoundException exception) {
			throw new ResetPasswordException(0, null);
		} catch (UserUpdationException exception) {
			throw new ResetPasswordException(0, null);
		}
	}

	public User getUserdata(String emailId) throws UserNotFoundException {
		return repositoryService.findByEmailId(emailId);
	}

	public User updateUser(User user) throws UserUpdationException {
		return repositoryService.update(user);
	}

	public User signup(SignupUser signupUser) throws UserCreationException {
		try {
			invitationCodeService.validateInvitationCode(signupUser.getEmailId(), signupUser.getInvitationCode());
			User user = new User();
			user.setEmailId(signupUser.getEmailId());
			user.setPassword(Password.getSaltedHash(signupUser.getPassword()));
			user.setUserId(new Timestamp(new Date().getTime()).getTime());
			repositoryService.create(user);
			//verificationCodeService.sendVerificationCode(user.getEmailId());
			invitationCodeRepositoryService.delete(user.getEmailId());
			
			return user;
		} catch (InvitationCodeDeletionException exception) {
			logger.error("", exception);
			throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
		} catch (UserCreationException exception) {
			logger.error("", exception);
			throw exception;
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
		} 
	}
	
	public boolean inviteUser(String emailId) throws UserInvitationException {
		try {
			return invitationCodeService.sendInvitationCode(emailId);
		} catch (InvitationCodeSendingException exception) {
			logger.error("", exception);
			throw new UserInvitationException(ExceptionConfig.USER_INVITATION_FAILED_ERROR_CODE, ExceptionConfig.USER_INVITATION_FAILED_EXCEPTION);
		}
	}
	
	public boolean recommend(String userId, String contentId, boolean flag) throws UserUpdationException {
		try {
			User user = repositoryService.findOne(userId);
			List<String> recommendedContents = user.getRecommended();
			
			if (flag) {
				recommendedContents.add(contentId);
			} else {
				recommendedContents.remove(contentId);
			}
			
			user.setRecommended(recommendedContents);
			repositoryService.update(user);
			return true;
		} catch (UserNotFoundException exception) {
			throw new UserUpdationException(0, null);
		} catch (Exception exception) {
			throw new UserUpdationException(0, null);
		}
	}
	
	public boolean bookmark(String userId, String contentId, boolean flag) throws UserUpdationException {
		try {
			User user = repositoryService.findOne(userId);
			List<String> bookmarkedContents = user.getBookmarked();
			
			if (flag) {
				bookmarkedContents.add(contentId);
			} else {
				bookmarkedContents.remove(contentId);
			}
			
			user.setBookmarked(bookmarkedContents); 
			repositoryService.update(user);
			return true;
		} catch (UserNotFoundException exception) {
			throw new UserUpdationException(0, null);
		}
	}
}
