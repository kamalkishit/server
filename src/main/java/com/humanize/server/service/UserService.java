package com.humanize.server.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.PasswordResetFailedException;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;
import com.humanize.server.authentication.exception.UserCreationFailedException;
import com.humanize.server.authentication.exception.UserDataNotFoundException;
import com.humanize.server.authentication.exception.UserInvitationFailedException;
import com.humanize.server.authentication.exception.UserLoginFailedException;
import com.humanize.server.authentication.exception.UserUpdationFailedException;
import com.humanize.server.authentication.exception.UserValidationFailedException;
import com.humanize.server.authentication.exception.UserVerificationFailedException;
import com.humanize.server.authentication.exception.VerificationCodeDeletionException;
import com.humanize.server.authentication.exception.VerificationCodeSendingFailedException;
import com.humanize.server.authentication.service.InvitationCodeRepositoryService;
import com.humanize.server.authentication.service.InvitationCodeService;
import com.humanize.server.authentication.service.TempPasswordRepositoryService;
import com.humanize.server.authentication.service.TempPasswordService;
import com.humanize.server.authentication.service.UserRepositoryService;
import com.humanize.server.authentication.service.VerificationCodeRepositoryService;
import com.humanize.server.authentication.service.VerificationCodeService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.data.User;

@Service
public class UserService {

	@Autowired
	private UserRepositoryService repositoryService;
	
	@Autowired
	private InvitationCodeRepositoryService invitationCodeRepositoryService;
	
	@Autowired
	private VerificationCodeRepositoryService verificationCodeRepositoryService;
	
	@Autowired
	VerificationCodeService verificationCodeService;
	
	@Autowired
	InvitationCodeService invitationCodeService;
	
	@Autowired
	TempPasswordService tempPasswordService;
	
	@Autowired
	TempPasswordRepositoryService tempPasswordRepositoryService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public User login(String emailId, String password) throws UserLoginFailedException {
		try {
			User user = repositoryService.findByEmailId(emailId);
			
			if (user.getPassword().equals(password) /*&& user.isVerified()*/) {
				return user;
			}
			
			throw new UserValidationFailedException(0, null);
		} catch (Exception exception) {
			throw new UserLoginFailedException(0, null);
		}
	}
	
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingFailedException {
		return tempPasswordService.sendTempPassword(emailId);
	}
	
	public boolean sendVerificationCode(String emailId) throws VerificationCodeSendingFailedException {
		return verificationCodeService.sendVerificationCode(emailId);
	}
	
	public User resetPassword(User user) throws PasswordResetFailedException {
		try {
			TempPassword tempPassword = tempPasswordRepositoryService.findByEmailId(user.getEmailId());
			
			if (tempPassword.getEmailId().equals(user.getTempPassword())) {
				User tempUser = repositoryService.findByEmailId(user.getEmailId());
				tempUser.setPassword(user.getPassword());
				return repositoryService.update(user);
			}
			
			throw new PasswordResetFailedException(0, null);
		} catch (Exception exception) {
			throw new PasswordResetFailedException(0, null);
		} 
	}
	
	public boolean logout(User user) {
		return true;
	}

	public User getUserdata(String emailId) throws UserDataNotFoundException {
		try {
			return repositoryService.findByEmailId(emailId);
		} catch (Exception exception) {
			throw new UserDataNotFoundException(0, null);
		}
	}

	public User updateUser(User user) throws UserUpdationFailedException {
		return repositoryService.update(user);
	}

	public User signup(User user) throws UserCreationFailedException {
		try {
			invitationCodeService.validateInvitationCode(user.getEmailId(), user.getInvitationCode());
			user.setUserId(UUID.randomUUID().toString());
			user.setInvitationCode(null);
			repositoryService.create(user);
			verificationCodeService.sendVerificationCode(user.getEmailId());
			invitationCodeRepositoryService.delete(user.getEmailId());
		} catch (VerificationCodeSendingFailedException | InvitationCodeDeletionException exception) {
			logger.error("", exception);
		} catch (UserCreationFailedException exception) {
			logger.error("", exception);
			throw exception;
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserCreationFailedException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
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
	
	public boolean verifyUser(String emailId, String verificationCode) throws UserVerificationFailedException {
		try {
			verificationCodeService.validateVerificationCode(emailId, verificationCode);
			
			User user = repositoryService.findByEmailId(emailId);
			user.setIsVerified(true);
			repositoryService.update(user);
			verificationCodeRepositoryService.deleteByEmailId(emailId);
		} catch (VerificationCodeDeletionException exception) {
			logger.error("", exception);
		} catch (Exception exception) {
			throw new UserVerificationFailedException(0, null);
		}
		
		return true;
	}
	
	public boolean recommend(String userId, boolean flag) throws Exception {
		return true;
	}
	
	public boolean bookmark(String userId, boolean flag) throws Exception {
		return true;
	}
}
