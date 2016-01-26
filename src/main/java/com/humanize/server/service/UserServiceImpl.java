package com.humanize.server.service;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.dao.UserRepositoryService;
import com.humanize.server.data.ContactUs;
import com.humanize.server.data.InviteUser;
import com.humanize.server.data.LoginUser;
import com.humanize.server.data.SignupUser;
import com.humanize.server.data.SuggestArticle;
import com.humanize.server.data.User;
import com.humanize.server.exception.EmailSendingException;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.UserCreationException;
import com.humanize.server.exception.UserNotFoundException;
import com.humanize.server.helper.EmailHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositoryService repositoryService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailHelper emailHelper;
	
	@Autowired
	private SaltedHashPasswordService saltedHashPassword;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String TAG = UserServiceImpl.class.getSimpleName();

	public String login(LoginUser loginUser) throws UserNotFoundException {
		try {
			User user = repositoryService.findByEmailId(loginUser.getEmailId());
			
			if (saltedHashPassword.check(loginUser.getPassword(), user.getPassword())) {
				return JsonWebTokenServiceImpl.getInstance().createToken(loginUser.getEmailId());
			}
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new UserNotFoundException(ErrorCodes.USER_NOT_FOUND_ERROR);
		}
		
		throw new UserNotFoundException(ErrorCodes.USER_NOT_FOUND_ERROR);
	}


	public User signup(SignupUser signupUser) throws UserCreationException {
		try {
			User user = new User();
			user.setEmailId(signupUser.getEmailId());
			user.setPassword(saltedHashPassword.getSaltedHash(signupUser.getPassword()));
			user.setUserId(new Timestamp(new Date().getTime()).getTime());
			user.setInvitedBy("kamal@humannize.com");
			return repositoryService.create(user);
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new UserCreationException(ErrorCodes.USER_CREATION_ERROR);
		}
	}
	
	public boolean invite(InviteUser inviteFriend) throws EmailSendingException {
		try {
			return emailService.sendEmail(emailHelper.createInviteFriendMail(inviteFriend));
		} catch (Exception exception) {
			logger.error(TAG, exception);
		}
		
		return false;
	}
	
	public boolean suggest(SuggestArticle suggestArticle) throws EmailSendingException {
		try {
			return emailService.sendEmail(emailHelper.createSuggestArticleMail(suggestArticle));
		} catch (Exception exception) {
			logger.error(TAG, exception);
		}
		
		return false;
	}
	
	public boolean contactUs(ContactUs contactUs) throws EmailSendingException {
		try {
			return emailService.sendEmail(emailHelper.createContactUsMail(contactUs));
		} catch (Exception exception) {
			logger.error(TAG, exception);
		}
		
		return false;
	}
}
