package com.humanize.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.WrongInvitationCodeException;
import com.humanize.server.authentication.exception.WrongPasswordException;
import com.humanize.server.authentication.service.InputValidationService;
import com.humanize.server.authentication.service.InvitationCodeRepositoryService;
import com.humanize.server.authentication.service.UserRepositoryService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.data.User;

@Service
public class UserService {

	@Autowired
	private UserRepositoryService userRepositoryService;
	
	@Autowired
	private InvitationCodeRepositoryService invitationCodeRepositoryService;
	
	@Autowired
	private InputValidationService inputValidationService;

	public User login(User user) {
		inputValidationService.validateLoginUser(user);
		
		User tempUser = userRepositoryService.findByEmailId(user.getEmailId());
		
		if (user.getPassword().equals(tempUser.getPassword())) {
			return tempUser;
		} else {
			throw new WrongPasswordException(ExceptionConfig.WRONG_PASSWORD_ERROR_CODE, ExceptionConfig.WRONG_PASSWORD_EXCEPTION);
		}
	}

	public User getUserdata(String emailId) {
		inputValidationService.validateEmailId(emailId);
		return userRepositoryService.findByEmailId(emailId);
	}

	public User updateUser(User user) {
		return userRepositoryService.update(user);
	}

	public User signup(User user) {
		inputValidationService.validateSignupUser(user);
		validateInvitationCode(user.getEmailId(), user.getInvitationCode());
		user.setUserId(UUID.randomUUID().toString());
		return userRepositoryService.create(user);
	}
	
	private void validateInvitationCode(String emailId, String invitationCode) {
		InvitationCode invitationCodeObj = invitationCodeRepositoryService.findByEmailId(emailId);
		
		if (!invitationCodeObj.getInvitationCode().equals(invitationCode)) {
			throw new WrongInvitationCodeException(ExceptionConfig.WRONG_INVITATION_CODE_ERROR_CODE, ExceptionConfig.WRONG_INVITATION_CODE_EXCEPTION);
		}
	}
}
