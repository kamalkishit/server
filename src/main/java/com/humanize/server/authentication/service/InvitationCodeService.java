package com.humanize.server.authentication.service;

import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeSendingException;
import com.humanize.server.authentication.exception.InvitationCodeValidationException;

public interface InvitationCodeService {
	
	boolean sendInvitationCode(String emailId, String invitedBy) throws InvitationCodeSendingException;
	boolean validateInvitationCode(String emailId, String invitationCodeStr) throws InvitationCodeValidationException;
	String getInvitedBy(String emailId) throws InvitationCodeNotFoundException;
}
