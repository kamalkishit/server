package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.WrongInvitationCodeException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class InvitationCodeService {
	
	@Autowired
	InvitationCodeRepositoryService repositoryService;
	
	@Autowired
	RandomStringGeneratorService randomStringGeneratorService;
	
	@Autowired
	EmailService emailService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean create(String emailId) throws InvitationCodeCreationException {		
		try {
			String invitationCodeStr = randomStringGeneratorService.getInvitationCode();			
			InvitationCode invitationCode = new InvitationCode(emailId, invitationCodeStr);
			emailService.sendEmail(emailId, invitationCodeStr);
			repositoryService.create(invitationCode);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new InvitationCodeCreationException(0, null);
		}
		
		return true;
	}
	
	public boolean validateInvitationCode(String emailId, String invitationCode) {
		InvitationCode invitationCodeObj = repositoryService.findByEmailId(emailId);
		
		if (invitationCodeObj.getInvitationCode().equals(invitationCode)) {
			return true;
		}
		
		throw new WrongInvitationCodeException(ExceptionConfig.WRONG_INVITATION_CODE_ERROR_CODE, ExceptionConfig.WRONG_INVITATION_CODE_EXCEPTION);
	}
}
