package com.humanize.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.InvitationCodeRepository;
import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.WrongInvitationCodeException;
import com.humanize.server.authentication.exception.WrongPasswordException;
import com.humanize.server.authentication.service.InputValidationService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.dao.UserRepository;
import com.humanize.server.data.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvitationCodeRepository invitationCodeRepository;
	
	@Autowired
	private InputValidationService inputValidationService;

	public User login(User user) {
		inputValidationService.validateLoginUser(user);
		
		User tempUser = userRepository.findByEmailId(user.getEmailId());
		
		if (tempUser != null) {
			if (user.getPassword().equals(tempUser.getPassword())) {
				return tempUser;
			} else {
				throw new WrongPasswordException(ExceptionConfig.WRONG_PASSWORD_ERROR_CODE, ExceptionConfig.WRONG_PASSWORD_EXCEPTION);
			}
		}
		
		throw new UserNotFoundException(ExceptionConfig.USER_NOT_FOUND_ERROR_CODE, ExceptionConfig.USER_NOT_FOUND_EXCEPTION);
	}

	public User getUserdata(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public User signup(User user) {
		inputValidationService.validateSignupUser(user);
		validateInvitationCode(user.getEmailId(), user.getInvitationCode());
		user.setUserId(UUID.randomUUID().toString());
		user = userRepository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
	}
	
	private void validateInvitationCode(String emailId, String invitationCode) {
		InvitationCode invitationCodeObj = invitationCodeRepository.findByEmailId(emailId);
		
		if (invitationCodeObj != null) {
			if (!invitationCodeObj.getInvitationCode().equals(invitationCode)) {
				throw new WrongInvitationCodeException(ExceptionConfig.WRONG_INVITATION_CODE_ERROR_CODE, ExceptionConfig.WRONG_INVITATION_CODE_EXCEPTION);
			}
		}
		
		throw new InvitationCodeNotFoundException(ExceptionConfig.INVITATION_CODE_NOT_FOUND_ERROR_CODE, ExceptionConfig.INVITATION_CODE_NOT_FOUND_EXCEPTION); 
	}

	public boolean likeContent(String userId, String contentId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			if (!user.getLikes().contains(contentId)) {
				user.getLikes().add(contentId);
				userRepository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean unlikeContent(String userId, String contentId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			if (user.getLikes().contains(contentId)) {
				user.getLikes().remove(contentId);
				userRepository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean bookmarkContent(String userId, String contentId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			if (!user.getBookmarks().contains(contentId)) {
				user.getBookmarks().add(contentId);
				userRepository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean unbookmarkContent(String userId, String contentId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			if (user.getBookmarks().contains(contentId)) {
				user.getBookmarks().remove(contentId);
				userRepository.save(user);
				return true;
			}
		}

		return false;
	}
}
