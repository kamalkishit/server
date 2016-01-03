package com.humanize.server.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.PasswordResetFailedException;
import com.humanize.server.authentication.exception.TempPasswordSendingFailedException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDataNotFoundException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
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
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.ResetPasswordUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.User;

@Service
public class UserServiceImpl implements UserService {

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

	public User login(LoginUser loginUser) throws UserNotFoundException {
		try {
			User user = repositoryService.findByEmailId(loginUser.getEmailId());
			
			if (user.getPassword().equals(loginUser.getPassword()) /*&& user.isVerified()*/) {
				return user;
			}
			
			throw new UserNotFoundException(0, null);
		} catch (Exception exception) {
			throw new UserNotFoundException(0, null);
		}
	}
	
	public boolean sendTempPassword(String emailId) throws TempPasswordSendingFailedException {
		return tempPasswordService.sendTempPassword(emailId);
	}
	
	public boolean sendVerificationCode(String emailId) throws VerificationCodeSendingFailedException {
		return verificationCodeService.sendVerificationCode(emailId);
	}
	
	public User resetPassword(ResetPasswordUser resetPasswordUser) throws PasswordResetFailedException {
		try {
			TempPassword tempPasswordObj = tempPasswordRepositoryService.findByEmailId(resetPasswordUser.getEmailId());
			
			if (tempPasswordObj.getTempPassword().equals(resetPasswordUser.getTempPassword())) {
				User tempUser = repositoryService.findByEmailId(resetPasswordUser.getEmailId());
				tempUser.setPassword(resetPasswordUser.getNewPassword());
				return repositoryService.update(tempUser);
			}
			
			throw new PasswordResetFailedException(0, null);
		} catch (Exception exception) {
			throw new PasswordResetFailedException(0, null);
		} 
	}
	
	public boolean logout(User user) {
		return true;
	}

	public User getUserdata(String emailId) throws UserNotFoundException {
		try {
			return repositoryService.findByEmailId(emailId);
		} catch (Exception exception) {
			throw new UserNotFoundException(0, null);
		}
	}

	public User updateUser(User user) throws UserUpdationException {
		return repositoryService.update(user);
	}

	public User signup(SignupUser signupUser) throws UserCreationException {
		try {
			invitationCodeService.validateInvitationCode(signupUser.getEmailId(), signupUser.getInvitationCode());
			User user = new User();
			user.setEmailId(signupUser.getEmailId());
			user.setPassword(signupUser.getPassword());
			user.setUserId(UUID.randomUUID().toString());
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
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserInvitationException(ExceptionConfig.USER_INVITATION_FAILED_ERROR_CODE, ExceptionConfig.USER_INVITATION_FAILED_EXCEPTION);
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
