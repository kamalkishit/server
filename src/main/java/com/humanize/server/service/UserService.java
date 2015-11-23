package com.humanize.server.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationFailedException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.authentication.exception.WrongInvitationCodeException;
import com.humanize.server.authentication.exception.WrongPasswordException;
import com.humanize.server.authentication.service.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public User login(User user) throws UserValidationException {
		User tempUser = userRepositoryService.findByEmailId(user.getEmailId());
		
		if (user.getPassword().equals(tempUser.getPassword())) {
			return tempUser;
		} else {
			throw new WrongPasswordException(ExceptionConfig.WRONG_PASSWORD_ERROR_CODE, ExceptionConfig.WRONG_PASSWORD_EXCEPTION);
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
			user = userRepositoryService.create(user);
			verificationCodeService.sendVerificationCode(user.getEmailId());
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
	
	public boolean verifyUser(String emailId, String verificationCode) {
		
		verificationCodeService.validateVerificationCode(emailId, verificationCode);
		
		User user = userRepositoryService.findByEmailId(emailId);
		user.setVerified(true);
		userRepositoryService.update(user);
		
		return true;
	}
	
	private void validateInvitationCode(String emailId, String invitationCode) {
		InvitationCode invitationCodeObj = invitationCodeRepositoryService.findByEmailId(emailId);
		
		if (!invitationCodeObj.getInvitationCode().equals(invitationCode)) {
			throw new WrongInvitationCodeException(ExceptionConfig.WRONG_INVITATION_CODE_ERROR_CODE, ExceptionConfig.WRONG_INVITATION_CODE_EXCEPTION);
		}
	}
	
	/*@PreAuthorize("hasRole('ROLE_USER')")
	public void temp1() {
		System.out.println("temp1");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void temp2() {
		System.out.println("temp2");
	}*/
}
