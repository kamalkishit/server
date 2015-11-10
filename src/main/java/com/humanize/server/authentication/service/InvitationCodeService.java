package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.WrongInvitationCodeException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class InvitationCodeService {
	
	@Autowired
	InvitationCodeRepositoryService repositoryService;
	
	@Autowired
	EmailService emailService;
	
	public boolean sendInvitationCode(String emailId) {
		String invitationCode = null;
		
		InvitationCode invitationCodeObj = new InvitationCode(emailId, invitationCode);
		emailService.sendEmail(null, null);
		repositoryService.create(invitationCodeObj);
		
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
