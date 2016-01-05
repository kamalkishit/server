package com.humanize.server.authentication.service;

import com.humanize.server.authentication.exception.InvitationCodeSendingException;
import com.humanize.server.authentication.exception.InvitationCodeValidationException;

public interface InvitationCodeService {
	
	public boolean sendInvitationCode(String emailId) throws InvitationCodeSendingException;
	public boolean validateInvitationCode(String emailId, String invitationCodeStr) throws InvitationCodeValidationException;
}
