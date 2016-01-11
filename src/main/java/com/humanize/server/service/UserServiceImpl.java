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
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeSendingException;
import com.humanize.server.authentication.exception.ResetPasswordException;
import com.humanize.server.authentication.exception.TempPasswordValidationException;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserInvitationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdateException;
import com.humanize.server.authentication.service.InvitationCodeRepositoryService;
import com.humanize.server.authentication.service.InvitationCodeService;
import com.humanize.server.authentication.service.TempPasswordRepositoryService;
import com.humanize.server.authentication.service.TempPasswordService;
import com.humanize.server.authentication.service.UserRepositoryService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.common.JsonWebTokenServiceImpl;
import com.humanize.server.common.TokenService;
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
	private InvitationCodeService invitationCodeService;
	
	@Autowired
	private TempPasswordService tempPasswordService;
	
	@Autowired
	private TempPasswordRepositoryService tempPasswordRepositoryService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String login(LoginUser loginUser) throws UserNotFoundException {
		try {
			User user = repositoryService.findByEmailId(loginUser.getEmailId());
			
			if (Password.check(loginUser.getPassword(), user.getPassword())) {
				return JsonWebTokenServiceImpl.getInstance().createToken(loginUser.getEmailId());
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
				user.setPassword(Password.getSaltedHash(resetPasswordUser.getNewPassword()));
				user = repositoryService.update(user);
				tempPasswordRepositoryService.delete(resetPasswordUser.getEmailId());
				return user;
			}
			
			throw new ResetPasswordException(0, null);
		} catch (TempPasswordValidationException exception) {
			throw new ResetPasswordException(0, null);
		} catch (UserNotFoundException exception) {
			throw new ResetPasswordException(0, null);
		} catch (UserUpdateException exception) {
			throw new ResetPasswordException(0, null);
		} catch (Exception exception) {
			throw new ResetPasswordException(0, null);
		}
	}

	public User getUserdata(String token) throws UserNotFoundException {
		try {
			return getUser(token);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new UserNotFoundException(0, null);
		}
	}

	public User updateUser(String token, User user) throws UserUpdateException {
		try {
			User tempUser = getUser(token);
			if (tempUser.getId() == user.getId()) {
				return repositoryService.update(user);
			}
			
			throw new UserUpdateException(0, null);
		} catch (Exception exception) {
			throw new UserUpdateException(0, null);
		}
	}

	public User signup(SignupUser signupUser) throws UserCreationException {
		try {
			invitationCodeService.validateInvitationCode(signupUser.getEmailId(), signupUser.getInvitationCode());
			User user = new User();
			user.setEmailId(signupUser.getEmailId());
			user.setPassword(Password.getSaltedHash(signupUser.getPassword()));
			user.setUserId(new Timestamp(new Date().getTime()).getTime());
			user.setInvitedBy(invitationCodeService.getInvitedBy(signupUser.getEmailId()));
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
		} catch (InvitationCodeNotFoundException exception) {
			throw new UserCreationException(0, null);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
		} 
	}
	
	public User signupFirst(SignupUser signupUser) throws UserCreationException {
		try {
			User user = new User();
			user.setEmailId(signupUser.getEmailId());
			user.setPassword(Password.getSaltedHash(signupUser.getPassword()));
			user.setUserId(new Timestamp(new Date().getTime()).getTime());
			user.setInvitedBy(user.getEmailId());
			repositoryService.create(user);
			
			return user;
		} catch (InvitationCodeDeletionException exception) {
			logger.error("", exception);
			throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
		} catch (UserCreationException exception) {
			logger.error("", exception);
			throw exception;
		} catch (InvitationCodeNotFoundException exception) {
			throw new UserCreationException(0, null);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
		} 
	}
	
	public boolean inviteUser(String token, String emailId) throws UserInvitationException {
		User user = null;
		
		try {
			//user = getUser(token);
			repositoryService.findByEmailId(emailId);
			throw new UserInvitationException(0, null);
		} catch (UserNotFoundException exception) {
			try {
				return invitationCodeService.sendInvitationCode(emailId, "kamal@ab.com");
			} catch (InvitationCodeSendingException exp) {
				logger.error("", exp);
				throw new UserInvitationException(ExceptionConfig.USER_INVITATION_FAILED_ERROR_CODE, ExceptionConfig.USER_INVITATION_FAILED_EXCEPTION);
			}
		} catch (Exception exception) {
			throw new UserInvitationException(ExceptionConfig.USER_INVITATION_FAILED_ERROR_CODE, ExceptionConfig.USER_INVITATION_FAILED_EXCEPTION);
		}
	}
	
	public boolean recommend(String token, String contentId, boolean flag) throws UserUpdateException {
		try {
			User user = getUser(token);
			List<String> recommendedContents = user.getRecommended();
			
			if (flag && !recommendedContents.contains(contentId)) {
				recommendedContents.add(contentId);
			} else {
				recommendedContents.remove(contentId);
			}
			
			user.setRecommended(recommendedContents);
			repositoryService.update(user);
			return true;
		} catch (UserNotFoundException exception) {
			throw new UserUpdateException(0, null);
		} catch (Exception exception) {
			throw new UserUpdateException(0, null);
		}
	}
	
	public boolean bookmark(String token, String contentId, boolean flag) throws UserUpdateException {
		try {
			User user = getUser(token);
			List<String> bookmarkedContents = user.getBookmarked();
			
			if (flag && !bookmarkedContents.contains(contentId)) {
				bookmarkedContents.add(contentId);
			} else {
				bookmarkedContents.remove(contentId);
			}
			
			user.setBookmarked(bookmarkedContents); 
			repositoryService.update(user);
			return true;
		} catch (UserNotFoundException exception) {
			throw new UserUpdateException(0, null);
		} catch (Exception exception) {
			throw new UserUpdateException(0, null);
		}
	}
	
	private User getUser(String token) throws Exception {
		try {
			String emailId = JsonWebTokenServiceImpl.getInstance().validateToken(token);
			return repositoryService.findByEmailId(emailId);
		} catch (Exception exception) {
			throw exception;
		}		
	}
}
