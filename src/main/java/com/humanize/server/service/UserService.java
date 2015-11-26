package com.humanize.server.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationFailedException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.authentication.exception.UserValidationFailedException;
import com.humanize.server.authentication.exception.VerificationCodeDeletionException;
import com.humanize.server.authentication.exception.VerificationCodeSendingException;
import com.humanize.server.authentication.service.InvitationCodeRepositoryService;
import com.humanize.server.authentication.service.InvitationCodeService;
import com.humanize.server.authentication.service.UserRepositoryService;
import com.humanize.server.authentication.service.VerificationCodeRepositoryService;
import com.humanize.server.authentication.service.VerificationCodeService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.data.User;

@Service
public class UserService {

	@Autowired
	private UserRepositoryService userRepositoryService;
	
	@Autowired
	private InvitationCodeRepositoryService invitationCodeRepositoryService;
	
	@Autowired
	private VerificationCodeRepositoryService verificationCodeRepositoryService;
	
	@Autowired
	VerificationCodeService verificationCodeService;
	
	@Autowired
	InvitationCodeService invitationCodeService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public User login(User user) throws UserValidationFailedException {
		try {
			User tempUser = userRepositoryService.findByEmailId(user.getEmailId());
			
			if (user.getPassword().equals(tempUser.getPassword()) && user.isVerified()) {
				return tempUser;
			}
			
			throw new UserValidationFailedException(0, null);
		} catch (Exception exception) {
			throw new UserValidationFailedException(0, null);
		}
	}
	
	public boolean logout(User user) {
		return true;
	}

	public User getUserdata(String emailId) throws UserNotFoundException {
		return userRepositoryService.findByEmailId(emailId);
	}

	public User updateUser(User user) throws UserUpdationException {
		return userRepositoryService.update(user);
	}

	public User signup(User user) throws UserCreationException {
		try {
			invitationCodeService.validateInvitationCode(user.getEmailId(), user.getInvitationCode());
			user.setUserId(UUID.randomUUID().toString());
			user.setInvitationCode(null);
			userRepositoryService.create(user);
			verificationCodeService.sendVerificationCode(user.getEmailId());
			invitationCodeRepositoryService.delete(user.getEmailId());
		} catch (VerificationCodeSendingException|InvitationCodeDeletionException exception) {
			logger.error("", exception);
		} catch (UserCreationException exception) {
			logger.error("", exception);
			throw exception;
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
		} 
		 
		return user;
	}
	
	public boolean inviteUser(String emailId) throws UserInvitationFailedException {
		try {
			return invitationCodeService.sendInvitationCode(emailId);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserInvitationFailedException(ExceptionConfig.USER_INVITATION_FAILED_ERROR_CODE, ExceptionConfig.USER_INVITATION_FAILED_EXCEPTION);
		}
		
	}
	
	public boolean verifyUser(String emailId, String verificationCode) throws UserValidationFailedException {
		try {
			verificationCodeService.validateVerificationCode(emailId, verificationCode);
			
			User user = userRepositoryService.findByEmailId(emailId);
			user.setVerified(true);
			userRepositoryService.update(user);
			verificationCodeRepositoryService.deleteByEmailId(emailId);
		} catch (VerificationCodeDeletionException exception) {
			logger.error("", exception);
		} catch (Exception exception) {
			throw new UserValidationFailedException(0, null);
		}
		
		return true;
	}
}
