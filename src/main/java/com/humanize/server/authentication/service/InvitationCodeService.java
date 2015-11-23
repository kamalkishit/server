package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeSendingException;
import com.humanize.server.authentication.exception.InvitationCodeValidationException;
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
	
	public boolean sendInvitationCode(String emailId) throws Exception {		
		try {
			String invitationCodeStr = randomStringGeneratorService.getInvitationCode();			
			InvitationCode invitationCode = new InvitationCode(emailId, invitationCodeStr);
			emailService.sendEmail(emailId, invitationCodeStr);
			repositoryService.create(invitationCode);
			return true;
		} catch (Exception exception) {
			logger.error("", exception);
			throw exception;
		}
	}
	
	public boolean validateInvitationCode(String emailId, String invitationCodeStr) throws Exception {
		try {
			InvitationCode invitationCode = repositoryService.findByEmailId(emailId);
			
			if (invitationCode != null && invitationCode.getInvitationCode().equals(invitationCodeStr)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			logger.error("", exception);
			throw exception;
		}
	}
}
