package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.Message;
import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeSendingException;
import com.humanize.server.authentication.exception.InvitationCodeValidationException;

@Service
public class InvitationCodeServiceImpl implements InvitationCodeService {

	@Autowired
	private InvitationCodeRepositoryService repositoryService;
	
	@Autowired
	private RandomStringGeneratorService randomStringGeneratorService;
	
	@Autowired
	private EmailService emailService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean sendInvitationCode(String emailId, String invitedBy) throws InvitationCodeSendingException {		
		try {
			String invitationCodeStr = randomStringGeneratorService.getInvitationCode();
			String str = "http://www.humannize.com/signup?emailId=" + emailId + "&invitationCode=" + invitationCodeStr;
			emailService.sendEmail(new Message(emailId, "Invitation Code", str));
			
			InvitationCode invitationCodeObj = new InvitationCode(emailId, invitedBy, invitationCodeStr);
			
			repositoryService.createOrUpdate(invitationCodeObj);
			
			return true;
		} catch (Exception exception) {
			logger.error("", exception);
			throw new InvitationCodeSendingException(0, null);
		}
	}
	
	public boolean validateInvitationCode(String emailId, String invitationCodeStr) throws InvitationCodeValidationException {
		try {
			InvitationCode invitationCode = repositoryService.findByEmailId(emailId);
			
			if (invitationCode.getInvitationCode().equals(invitationCodeStr)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			logger.error("", exception);
			throw new InvitationCodeValidationException(0, null);
		}
	}
	
	public String getInvitedBy(String emailId) throws InvitationCodeNotFoundException {
		return repositoryService.findByEmailId(emailId).getInvitedBy();
	}
}
